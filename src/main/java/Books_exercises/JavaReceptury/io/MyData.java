package Books_exercises.JavaReceptury.io;

import java.io.Serializable;

// BEGIN main
/** Prosta klasa danych, które będą serializowane. */
public class MyData implements Serializable {

    private static final long serialVersionUID = -4965296908339881739L;
    String userName;
    String passwordCypher;
    transient String passwordClear;

    /** Ten konstruktor jest zazwyczaj wymagany. */
    public MyData() {
        // Tu nic nie robimy.
    }

    public MyData(String name, String clear) {
        setUserName(name);
        setPassword(clear);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String s) {
        this.userName = s;
    }

    public String getPasswordCypher() {
        return passwordCypher;
    }

    /** W obiekcie przechowujemy hasło zapisane otwartym tekstem,
     * nie będzie ono serializowane. Musimy także zapisać
     * hasło zaszyfrowane! Szyfrowanie nie zostało tu przedstawione.
     */
    public void setPassword(String s) {
        this.passwordClear = s;
        passwordCypher = encrypt(passwordClear);
    }

    public String toString() {
        return "MyData[" + userName + ",------]";
    }
// END main

    /** W prawdziwym programie należałoby tu skorzystać z API
     * do szyfrowania. */
    protected String encrypt(String s) {
        return "fjslkjlqj2TOP+SECRETkjlskl";
    }
}
