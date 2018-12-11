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

import java.io.IOException;
import java.util.LinkedList;

/** Producent i konsument w Javie, wersja II.
 */
// BEGIN main
public class ProdCons2 {

    /** W tej wersji programu obiektem używanym do synchronizacji 
     * działania wątków jest this, a zatem także on będzie 
     * wykorzystywany do wywoływania metod wait() oraz notifyAll().
     */
    protected LinkedList<Object> list = new LinkedList<>();
    protected int MAX = 10;
    protected boolean done = false; // Także chroniony przez blokadę listy.

    /** Klasa wewnętrzna reprezentująca wątek producenta. */
    class Producer extends Thread {

        public void run() {
            while (true) {
                Object justProduced = getRequestFromNetwork();
                // Pobieramy żądanie z sieci - poza synchronizowanym 
                // fragmentem kodu. W naszym programie metoda ta symuluje 
                // odczyt danych przesyłanych przez klienta, a to może 
                // trwać wiele godzin (jeśli klient właśnie wyszedł na kawę).
                synchronized(list) {
                    while (list.size() == MAX) { // Kolejka jest "pełna".
                        try {
                            System.out.println("Producent CZEKA");
                            list.wait();     // Ograniczamy wielkość.
                        } catch (InterruptedException ex) {
                            System.out.println(
                                 "DZIAŁANIE PRODUCENTA ZOSTAŁO PRZERWANE");
                        }
                    }
                    list.addFirst(justProduced);
                    list.notifyAll();    // Musimy mieć blokadę.
                    System.out.println("Wyprodukowano 1 obiekt; aktualna wielkość listy " + list.size());

                    if (done)
                        break;
                    // yield();    // Przydatna w przypadku wątków i programów 
                                   // demonstracyjnych.
                }
            }
        }

        Object getRequestFromNetwork() {    // Symulacja odczytu danych  
                                            // z klienta.

            // try {
            //     Thread.sleep(10);        // Symulujemy czas, jaki zabiera 
                                            // odczyt danych.
            // } catch (InterruptedException ex) {
            //     System.out.println(
            //                "Odczyt danych od producenta został PRZERWANY");
            // }
            return(new Object());
        }
    }

    /** Klasa wewnętrzna reprezentująca wątek konsumenta. */
    class Consumer extends Thread {
        public void run() {
            while (true) {
                Object obj = null;
                synchronized(list) {
                    while (list.size() == 0) {
                        try {
                            System.out.println("KONSUMENT CZEKA");
                            list.wait();    // Musimy mieć blokadę.
                        } catch (InterruptedException ex) {
                            System.out.println(
                                  "DZIAŁANIE KONSUMENTA ZOSTAŁO PRZERWANE");
                        }
                    }
                    obj = list.removeLast();
                    list.notifyAll();
                    int len = list.size();
                    System.out.println("Aktualna wielkość listy " + len);
                    if (done)
                        break;
                }
                process(obj);    // Poza synchronizowanym blokiem kodu (może 
                                 // trwać długo).
                //yield();
            }
        }

        void process(Object obj) {
            // Thread.sleep(1234) // Symulujemy upływający czas.
            System.out.println("Wykorzystujemy obiekt " + obj);
        }
    }

    ProdCons2(int nP, int nC) {
        for (int i=0; i<nP; i++)
            new Producer().start();
        for (int i=0; i<nC; i++)
            new Consumer().start();
    }

    public static void main(String[] args)
    throws IOException, InterruptedException {

        // Uruchamiamy wątki producentów i konsumentów. 
        int numProducers = 4;
        int numConsumers = 3;
        ProdCons2 pc = new ProdCons2(numProducers, numConsumers);

        // Niech wątki działają przez, powiedzmy, 10 sekund.
        Thread.sleep(10*1000);

        // Koniec symulacji - kończymy ją w "delikatny" sposób.
        synchronized(pc.list) {
            pc.done = true;
            pc.list.notifyAll();
        }
    }
}
// END main
