package Books_exercises.JavaReceptury.threads.buzzin;

/*
 * Copyright (c) Ian F. Darwin, http://www.darwinsys.com/, 1996-2002.
 * All rights reserved. Software written by Ian F. Darwin and others.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. All advertising materials mentioning features or use of this software
 *    must display the following acknowledgement:
 *        This product includes software developed by Ian F. Darwin.
 * 4. Neither the name of the author nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
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
 * 
 * Java, the Duke mascot, and all variants of Sun's Java "steaming coffee
 * cup" logo are trademarks of Sun Microsystems. Sun's, and James Gosling's,
 * pioneering role in inventing and promulgating (and standardizing) the Java 
 * language and environment is gratefully acknowledged.
 * 
 * The pioneering role of Dennis Ritchie and Bjarne Stroustrup, of AT&T, for
 * inventing predecessor languages C and C++ is also gratefully acknowledged.
 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

/** Serwlet obsługujący internetowe teleturnieje: pierwsza osoba, która 
 * się zgłosi ma możliwość odpowidzenia na pytanie sprawdzające jej wiedzę.
 * <p>
 * Wcześniejsza wersja tego programu wykorzystywała zmienne statyczne 
 * jednak rozwiązanie takie nie jest niezawodne, gdyż większość 
 * aktualnie dostępnych mechanizmów obsługi serwletów, pozwala 
 * na stosowanie niestandardowych mechanizmów ładujących klasy, które 
 * umożliwiają wielokrotne pobieranie klasy servletu. Właściwym rozwiązaniem
 * jest synchronizowanie obiektu przechowywanego w kontekście apliakcji 
 * serwletu.
 */
// BEGIN main
public class BuzzInServlet extends HttpServlet {

    /** Wykorzystywana w serwlecie nazwa atrybutu. */
    protected final static String WINNER = "buzzin.winner";

    /** Metoda doGet jest wywoływana przez strony WWW poszczególnych graczy.
     * Wykorzystuje ona synchronizowany blok kodu, aby zapewnić, że tylko 
     * jeden z graczy będzie w stanie zmienić stan zmiennej "buzzed".
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        ServletContext application = getServletContext();

        boolean iWon = false;
        String user = request.getRemoteHost() + '@' + request.getRemoteAddr();

        // W pierwszej kolejności synchronizujemy dostęp do zmiennej
        // i wykonujemy inne związane z tym operacje.
        synchronized(application) {
            if (application.getAttribute(WINNER) == null) {
                application.setAttribute(WINNER, user);
                application.log("BuzzInServlet: Zwycięzcą jest " + user);
                iWon = true;
            }
         }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Dziękujemy za wzięcie udziału w zabawie</title></head>");
        out.println("<body bgcolor=\"white\">");

        if (iWon) {
            out.println("<b>Udało ci się</b>");
            // Do zrobienia - zmienić generowany kod HTML, tak by po wyświetleniu
            // strony odtwarzany był plik dźwiękowy:-)
        } else {
                out.println("Dziękujemy za wzięcie udziału w rozgrywce, " + request.getRemoteAddr());
                out.println(", jednak pierwszy był " + application.getAttribute(WINNER) + 
                    ".");
        }
        out.println("</body></html>");
    }

    /** Metoda Post jest wykorzystywana przez stronę administracyjną (która
     * powinna być zainstalowana w katalogu lokalnej witryny administratora).
     * Metoda Post jest używana do celów administracyjnych:
     * 1) aby wyświetlić zwycięzcę;
     * 2) aby przygotować aplikację do zadania następnego pytania.
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        ServletContext application = getServletContext();

        response.setContentType("text/html");
        HttpSession session = request.getSession();

        PrintWriter out = response.getWriter();

        if (request.isUserInRole("host")) {
            out.println("<html><head><title>Witam ponownie, " +
                request.getUserPrincipal().getName() + "</title><head>");
            out.println("<body bgcolor=\"white\">");
            String command = request.getParameter("command");
            if (command.equals("reset")) {

                // Synchronizujemy dokładnie to, co trzeba, nic ponad to.
                synchronized(application) {
                    application.setAttribute(WINNER, null);
                }
                session.setAttribute("buzzin.message", "RESET");
            } else if (command.equals("show")) {
                String winner = null;
                synchronized(application) {
                    winner = (String)application.getAttribute(WINNER);
                }
                if (winner == null) {
                    session.setAttribute("buzzin.message",
                        "<b>Jeszcze nie ma zwycięzcy!</b>");
                } else {
                    session.setAttribute("buzzin.message",
                        "<b>Zwycięzcą jest: </b>" + winner);
                }
            }
            else {
                session.setAttribute("buzzin.message",
                    "BŁĄD: polecenie " + command + " nie jest prawidłowe.");
            }
            RequestDispatcher rd = application.getRequestDispatcher(
                "/hosts/index.jsp");
            rd.forward(request, response);
        } else {
            out.println("<html><head><title>Cóż, spróbowałeś, ale... </title><head>");
            out.println("<body bgcolor=\"white\">");
            out.println(
                "Przykro mi, Dave, ale wiesz, że nie mogę ci na to pozwolić.");
            out.println("Nawet jeśli jesteś " + request.getUserPrincipal());
        }
        out.println("</body></html>");
    }
}
// END main
