#! /usr/bin/perl
# Wywołujemy kod Javy ze skryptu napisanego w Perlu i na odwrót.

use strict;
use warnings;

use Text::Levenshtein qw();
  # Moduł Perl z archiwum CPAN, mierzący podobieństwo łańcuchów znaków.

use Inline 0.44 "JAVA" => "DATA";  # Wskaźnik na kod źródłowy w Javie.
use Inline::Java qw(caught);  # Funkcja pomocnicza do określania typu wyjątku.

my $show = new Showit;     # Tworzymy obiekt Javy, używając składni Perla
$show->show("Kolejny hacker używający Perla ");    
                                  # Wywołujemy metodę tego obiektu.

eval {
  # Wywołujemy metodę, która odwoła się do kodu w Perlu;
  # przechwytujemy wyjątki, jeśli takie się pojawią.
  print "Sprawdzamy: ", $show->match("Japh", shift || "Java"), 
    " (wyświetlone z poziomu kodu w Perlu)\n";
};
if ($@) {
  print STDERR "Wyjątek:", caught($@), "\n";
  if (caught("java.lang.Exception")) {
    my $msg = $@->getMessage();
    print STDERR "$msg\n";
  } else {
    die $@;
  }
}


__END__

__JAVA__
// Tu się zaczyna kod w Javie.
import javax.swing.*;
import org.perl.inline.java.*;

class Showit extends InlineJavaPerlCaller {
  // Rozszerzenie jest potrzebne wyłącznie w przypadku wywoływania
  // kodu napisanego w Perlu.

  /** Prosta klasa Javy, którą będziemy wywoływali z kodu napisanego 
   *  w Perlu i która będzie wywoływać kod napisany w Perlu.   
   */
  public Showit() throws InlineJavaException { }

  /** Przykładowa metoda. */
  public void show(String str) {
    System.out.println(str + " w kodzie Javy");
  }

  /** Metoda wywołująca kod napisany w Perlu. */
  public int match(String target, String pattern)
      throws InlineJavaException, InlineJavaPerlException {

    // Wywołujemy funkcję napisaną w Perlu.
    String str = (String)CallPerl("Text::Levenshtein", "distance",
          new Object [] {target, pattern});

    // Wyświetlamy wyniki.
    JOptionPane.showMessageDialog(null, "Odległość edycyjna pomiędzy łańcuchami '" 
        + target + "' oraz '" + pattern + "' wynosi: " + str,
        "Swingując z Perlem", JOptionPane.INFORMATION_MESSAGE);
    return Integer.parseInt(str);
  }

}
