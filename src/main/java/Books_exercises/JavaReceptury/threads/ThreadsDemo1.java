package Books_exercises.JavaReceptury.threads;

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

/** 
 * Demonstracyjna aplikacja wielowątkowa, napisana jako klasa potomna 
 * klasy Thread.
 *
 * @author    Ian Darwin
 */
// BEGIN main
public class ThreadsDemo1 extends Thread {
    private String mesg;
    private int count;

    /** Metoda run() wykonuje całą robotę: wyświetla komunikat 
     * tyle razy ile wynosi wartość zmiennej count
     */ 
    public void run() {
        while (count-- > 0) {
            System.out.println(mesg);
            try {
                Thread.sleep(100);    // 100 ms
            } catch (InterruptedException e) {
                return;
            }
        }
        System.out.println(mesg + " wszystko gotowe.");
    }

    /**
     * Tworzymy obiekt ThreadsDemo1.
     * param m Komunikat do wyświetlenia.
     * @param n Ile razy należy go wyświetlić.
     */
    public ThreadsDemo1(final String mesg, int n) {
        this.mesg = mesg;
        count = n;
        setName(mesg + "Wątek roboczy nr " + n);
    }

    /**
     * Program główny testujący działanie klasy ThreadsDemo1.
     */
    public static void main(String[] argv) {
        // Alternatywnie można by napisać 
        //   new ThreadsDemo1("Pozdrowienia z wątku X", 10).run();
        //   new ThreadsDemo1("Pozdrowienia z wątku Y", 15).run();
        // Lecz w takim przypadku nie byłby to program wielowątkowy.
        new ThreadsDemo1("Pozdrowienia z wątku X", 10).start();
        new ThreadsDemo1("Pozdrowienia z wątku Y", 15).start();
    }
}
// END main
