/* Copyright (c) Ian F. Darwin, http://www.darwinsys.com/, 2004-2006.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS''
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package Books_exercises.JavaReceptury.darwinsys.sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;

import com.darwinsys.util.Verbosity;

/** Klasa służąca do wykonywania skryptów SQL. Przypomina nieco programy 
 * pslq(1), SQL*Plus lub podobne.
 * W wierszu wywołania można podawać opcje: -c opcjaKonfiguracyjna 
 * [-f plikKonfiguracyjny] [plikSkryptu]. 
 * <p>Wejściowy język poleceń: polecnia muszą się zaczynać od znaku odwrotnego
 * ukośnika (\) i MUSZĄ kończyć średnikie;); można także używać standardowych
 * poleceń SQL, przy czym także one muszą się kończyć średnikiem.
 * standard SQL statements which must also end with semi-colon);
 * <p>Sekwencje sterujące:
 * <ul>
 * <li> \m (tryb wyjściowy), można wpisać znak t reprezentujący tekst,
 * h - html, s -r sql lub x - xml (nie obsługiwany w tej wersji programu)
 * (dane wynikowe zapisywane w formie kodu SQL są przeznaczone do użycia 
 * w celu wstawiania tych samych danych do innej, identycznej tabeli; jednak
 * możliwość ta nie została dokładnie przetestowana!). 
 * <li> \o plikWynikowy, przekierowanie danych wyjściowych.
 * <li> \q zakończenie działania programu.
 * </ul>
 * <p>Tej klasy można także używać z poziomu innych programów, takich jak 
 * serwlety itd. Przykład takiego użycia tej klasy można znaleźć w programie 
 * SQLRunnerGUI.
 * <p>Na przyklad, poniższe poleceni i dane wejściowe:
 * <pre>
 * SQLrunner -c testdb
 * \ms;
 * select * from person where person_key=4;
 * </pre>
 * Mogłyby wygenerować następujące wyniki:
 * <pre>
 * Executing : select * from person where person_key=4
 * insert into PERSON(PERSON_KEY,  FIRST_NAME, INITIAL, LAST_NAME, ... )
 * values (4, 'Ian', 'F', 'Darwin', ...);
 * </pre>
 * <p>TODO Poprawić analizę danych wejściowych tak by wiersze z \\ nie musiały 
 * kończyć się średnikiem.
 * <p>TODO Dodać tryb "ręcznego zatwierdzania" (lub "cofnięcie niemożliwe"), 
 * działający zarówno w razie używania z poziomu wiersza poleceń, jak 
 * i w aplikacjach o graficznym interfejsie użytkownika.
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
// package com.darwinsys.sql;
public class SQLRunner {

	OutputMode outputMode = OutputMode.t;

	private static boolean okToExit = false;

	public static void setOkToExit(final boolean setting) {
		okToExit = setting;
	}

	public static boolean isOkToExit() {
		return okToExit;
	}

	public static void exit(final int exitStatus) {
		if (okToExit) {
			System.exit(exitStatus);
		} else {
			// Nic nie robimy.
		}
	}

	/** Połączenie z bazą danych. */
	private Connection conn;

	private DatabaseMetaData dbMeta;

	/** Polecenie SQL. */
	private Statement statement;

	/** Strumień wyjściowy wyników. */
	private PrintWriter out;

	private ResultsDecorator currentDecorator;

	/** Ten obiekt musi zostać określony na początku. */
	private ResultsDecorator textDecorator =
		new ResultsDecoratorText(out, verbosity);

	private ResultsDecorator sqlDecorator;

	private ResultsDecorator htmlDecorator;

	private ResultsDecorator xmlDecorator;

	private ResultsDecorator jtableDecorator;

	private boolean debug;

	private boolean escape;

    /** Baza DB2 jest aktualnie jedyną, o której wiem, że wymaga,
     * by nazwy tabel podawanych przy pobieraniu metadanych były
     * zapisywane wielkimi literami.
     */
	private boolean upperCaseTableNames;

	private SQLRunnerGUI gui;

	private static Verbosity verbosity = Verbosity.QUIET;

    /** Konstruktor obiektu SQLRunner.
     * @param driver Łańcuch znaków określający sterownik JDBC.
     * @param dbUrl Łańcuch znaków określający adres URL źródła JDBC.
     * @param user Łańcuch znaków - nazwa użytkownika.
     * @param password Łańcuch znaków - hasło podane otwartym tekstem.
     * @param outputMode Jedna ze stałych MODE_XXX.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
	public SQLRunner(String driver, String dbUrl, String user, String password,
			String outputFile, String outputMode)
			throws IOException, ClassNotFoundException, SQLException {
		conn = ConnectionUtil.getConnection(driver, dbUrl, user, password);
		commonSetup(outputFile, outputMode);
	}

	public SQLRunner(Connection c, String outputFile, String outputModeName)
		throws IOException, SQLException {

		// Pobieramy dane wejściowe SQL.
		conn = c;
		commonSetup(outputFile, outputModeName);
	}

	private void commonSetup(String outputFileName, String outputModeName)
		throws IOException, SQLException {

		dbMeta = conn.getMetaData();
		upperCaseTableNames =
			dbMeta.getDatabaseProductName().indexOf("DB2") >= 0;
		String dbName = dbMeta.getDatabaseProductName();
		System.out.println("SQLRunner: Connected to " + dbName);
		statement = conn.createStatement();
		
		if (outputFileName == null) {
			out = new PrintWriter(System.out);
		} else {
			out = new PrintWriter(new FileWriter(outputFileName));
		}

		setOutputMode(outputModeName);
	}

    /** Określenie trybu generowania wyników.
     * param outputMode Musi być jedną z wartości MODE_XXX.
     * @throws IllegalArgumentException Wyjątek zgłaszany, w przypadku gdy
     *    podano niewłaściwy tryb.
     */
	void setOutputMode(String outputModeName) {
		if (outputModeName == null ||
			outputModeName.length() == 0) {
			System.err.println(
			"invalid mode: " + outputMode + "; must be t, h or s"); }

		outputMode = OutputMode.valueOf(outputModeName);
		setOutputMode(outputMode);
	}

    /** Przypisuje odpowiedni obiekt ResultsDecorator, tworząc je na 
     * bieżąco, zgodnie z zasadami "leniwego przetwarzania".
     */
	void setOutputMode(OutputMode outputMode) {
		ResultsDecorator newDecorator = null;
		switch (outputMode) {
			case t:
				newDecorator = textDecorator;
				break;
			case h:
				if (htmlDecorator == null) {
					htmlDecorator = new ResultsDecoratorHTML(out, verbosity);
				}
				newDecorator = htmlDecorator;
				break;
			case s:
				if (sqlDecorator == null) {
					sqlDecorator = new ResultsDecoratorSQL(out, verbosity);
				}
				newDecorator = sqlDecorator;
				break;
			case x:
				if (xmlDecorator == null) {
					xmlDecorator = new ResultsDecoratorXML(out, verbosity);
				}
				newDecorator = xmlDecorator;
				break;
			case j:
				if (jtableDecorator == null) {
					if (gui == null) {
						throw new IllegalArgumentException(
						"Can't set mode to JTable before calling setGUI()");
					}
					jtableDecorator =
						new ResultsDecoratorJTable(gui.getJTable(), out, verbosity);
				}
				newDecorator = jtableDecorator;
				break;
			default:
				System.err.println("invalid mode: "
								+ outputMode + "; must be one of: ");
				for (OutputMode t : OutputMode.values()) {
					out.print(t); out.print(' ');
				}
				out.println();
		}
		if (currentDecorator != newDecorator) {
			currentDecorator = newDecorator;
			if (debug)
				System.out.println("Mode set to  " + outputMode);
		}
		currentDecorator.setWriter(out);
	}

    /** Metoda uruchamia jeden plik skryptu, określony przy użyciu nazwy.
     * Wywołujemy ją z poziomu wiersza poleceń w main lub z kodu 
     * użytkownika. Odrzuciłem ją ze względu na słabe możliwości 
     * obsługi błędów; lepiej byłoby tworzyć obiekt Reader w kodzie
     * obsługi interfejsu użytkownika, a następnie używać kodu o postaci:
     * <pre>while ((stmt = SQLRunner.getStatement(is)) != null) {
            stmt = stmt.trim();
            try {
                myRunner.runStatement(stmt);
            } catch (Exception e) {
                // Wyświetlamy komunikat o błędzie...
            }
        }
     * </pre>
     * @throws SyntaxException
     */
	@Deprecated
	public void runScript(String scriptFile)
	throws IOException, SQLException, SyntaxException {

		BufferedReader is;

        // W pierwszej kolejności wczytujemy plik skryptu, 
        // najprawdopodobniej będzie to błąd.
		is = new BufferedReader(new FileReader(scriptFile));

		runScript(is, scriptFile);
	}

    /** Metoda wykonuje jeden skrypt określony na podstawie nazwy, używając
     * przy tym przekazanego obiektu BufferedReader.
     * Odrzuciłem ją ze względu na słabe możliwości obsługi błędów; lepiej 
     * byłoby tworzyć obiekt Reader w kodzie obsługi interfejsu użytkownika, 
     * a następnie używać kodu o postaci:
     * <pre>while ((stmt = SQLRunner.getStatement(is)) != null) {
            stmt = stmt.trim();
            try {
                myRunner.runStatement(stmt);
            } catch (Exception e) {
                // Wyświetlamy komunikat o błędzie...
            }
        }
     * </pre>
     * @throws SyntaxException
     */
	@Deprecated
	public void runScript(BufferedReader is, String name)
	throws IOException, SQLException, SyntaxException {
		String stmt;

		while ((stmt = getStatement(is)) != null) {
			stmt = stmt.trim();
			runStatement(stmt);
		}
	}

    /**
     * Metoda przetwarza sekwencje specjalne, takie jak "\ms;" 
     * reprezentującą włączenie trybu sql.
     * @throws SyntaxException
     */
	private void doEscape(String str)
		throws IOException, SQLException, SyntaxException  {

		String rest = null;
		if (str.length() > 2) {
			rest = str.substring(2);
		}

		if (str.startsWith("\\d")) {	// Wyświetlanie
			if (rest == null){
				throw new SyntaxException("\\d needs display arg");
			}
			display(rest);
		} else if (str.startsWith("\\m")) {	// TRYB
			if (rest == null){
				throw new SyntaxException("\\m needs output mode arg");
			}
			setOutputMode(rest);
		} else if (str.startsWith("\\o")){
			if (rest == null){
				throw new SyntaxException("\\o needs output file arg");
			}
			setOutputFile(rest);
		} else if (str.startsWith("\\q")){
			exit(0);
		} else {
			throw new SyntaxException("Unknown escape: " + str);
		}
	}

    /**
     * Display - generuje wyniki sekwencji specjalnych, takich jak \dt.
     * @param rest - co ma być wyświetlone - argument z usuniętymi znakami \d
     * XXX: przenieść więcej kodu formatującego do metod klasy 
     *   ResultsDecorator: listTables(rs) oraz listColumns(rs).
     */
	private void display(String rest)
		throws IOException, SQLException, SyntaxException {

		// setOutputMode(OutputMode.t);
		if (rest.equals("t")) {
			// Wyświetlamy listę tabel.
			DatabaseMetaData md = conn.getMetaData();
			ResultSet rs = 
				md.getTables(null, null, "%", new String[]{"TABLE","VIEW"});
			textDecorator.setWriter(out);
			textDecorator.write(rs);
			textDecorator.flush();
		} else if (rest.startsWith("t")) {
            // Wyświetlamy jedną tabelę. Niektóre implementacje klasy 
            // DatabaseMeta nie ignorują wielkości liter, dlatego całą
            // nazwę zapisujemy wielkimi literami.
			String tableName = rest.substring(1).trim();
			if (upperCaseTableNames) {
				tableName = tableName.toUpperCase();
			}
			System.out.println("-- Display table " + tableName);
			DatabaseMetaData md = conn.getMetaData();
			ResultSet rs = md.getColumns(null, null, tableName, "%");
			currentDecorator.displayTable(tableName, rs);
			textDecorator.flush();
		} else
			throw new SyntaxException("\\d"  + rest + " invalid");
	}

	/**
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private static CachedRowSet cacheResultSet(ResultSet rs) throws SQLException {
		CachedRowSet rows = null;//new com.sun.rowset.WebRowSetImpl();
		rows.populate(rs);
		return rows;
	}

	/** Metoda określa plik, w którym będą zapisywane wyniki.
	 * @param fileName
	 */
	public void setOutputFile(String fileName) throws IOException {
		if (fileName == null) {
			/* Określamy plik wynikowy z powrotem jako System.out */
			setOutputFile(new PrintWriter(System.out, true));
		} else {
			File file = new File(fileName);
			setOutputFile(new PrintWriter(new FileWriter(file), true));
			System.out.println("Output set to " + file.getCanonicalPath());
		}
	}

    /** Metoda nakazuje zapisywanie wyników w przekazanym obiekcie Writer; 
     * jednocześnie od razu aktualizuje pole textDecorator, by prawidłowo
     * działała sekwencja \dt.
     * @param writer
	 */
	public void setOutputFile(PrintWriter writer) {
		out = writer;
		currentDecorator.setWriter(out);
	}

    /** Metoda wykonuje polecenie, a w przypadku poleceń Update i Query
     * także formatuje wyniki. Uruchamiana ze skryptu lub kodu użytkownika.
     * @throws SyntaxException
     */
	public void runStatement(final String rawString)
		throws IOException, SQLException, SyntaxException {

		final String inString = rawString.trim();

		if (verbosity != Verbosity.QUIET) {
			out.println("Executing : <<" + inString + ">>");
			out.flush();
		}
		currentDecorator.println(
			String.format("-- output from command -- \"%s\"%n", inString));

		escape = false;
		if (inString.startsWith("\\")) {
			escape = true;
			doEscape(inString);
			return;
		}

		boolean hasResultSet = 
			statement.execute(inString);		// DO IT - wykonujemy polecenie.

		if (!hasResultSet) {
			currentDecorator.printRowCount(statement.getUpdateCount());
		} else {
			int n = currentDecorator.write(cacheResultSet(statement.getResultSet()));
			if (verbosity == Verbosity.VERBOSE || verbosity == Verbosity.DEBUG) {
				currentDecorator.printRowCount(n);
			}
		}
		currentDecorator.flush();
	}

	/** Odczytuje jedno polecenie z przekazanego obiektu Reader.
	 * Ignoruje komentarze i puste wiersze.
	 * @return Polecenie SQL kończące się przed znakiem ";".
	 * Może zwracać null, jeśli nie znaleziono poleceń.
	 */
	public static String getStatement(BufferedReader is)
	throws IOException {
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = is.readLine()) != null) {
			if (verbosity == Verbosity.DEBUG) {
				System.out.println("SQLRunner.getStatement(): LINE " + line);
			}
			if (line == null || line.length() == 0) {
				continue;
			}
			line = line.trim();
			if (line.startsWith("#") || line.startsWith("--")) {
				continue;
			}
			if (line.startsWith("\\")) {
				if (sb.length() != 0) {
					throw new IllegalArgumentException(
						"Escape command found inside statement");
				}
			}
			sb.append(line);
			int nb = sb.length();

			// Jeśli bufor kończy się znakiem ';', zwracamy go.
			if (nb > 0 && sb.charAt(nb-1) == ';') {
				if (nb == 1) {
					return null;
				}
				sb.setLength(nb-1);
				return sb.toString();
			}
			// Dodajemy odstęp na wypadek, gdyby kod SQL został 
			// wygenerowany przez narzędzie, które nie pamięta o dodawaniu
			// odstępów (miejmy nadzieję, że to nie przeszkodzi w działaniu
			// narzędzi wyświetlających znaki nowego wiersza wewnątrz 
			// łańcuchów zapisanych w cudzysłowach!).
			sb.append(' ');
		}
		return null;
	}

	public void close() throws SQLException {
		if (statement != null) {
			statement.close();
		}
		if (conn != null) {
			conn.close();
		}
		out.flush();
		out.close();
	}

	public static Verbosity getVerbosity() {
		return verbosity;
	}

	public static void setVerbosity(Verbosity verbosity) {
		SQLRunner.verbosity = verbosity;
	}

	public void setErrorHandler(SQLRunnerErrorHandler eHandler) {
		gui.setErrorHandler(eHandler);
	}

	public void setGUI(SQLRunnerGUI gui) {
		this.gui = gui;
	}

	public String toString() {
		return "sqlrunner";
	}

	public boolean isEscape() {
		return escape;
	}
}
// END main
