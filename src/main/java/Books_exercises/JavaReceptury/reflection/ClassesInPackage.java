package Books_exercises.JavaReceptury.reflection;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Program wyświetla listę klas należących do podanego pakietu.
 * Tak na prawdę, w Javie nie można tego zrobić w każdym z potencjalnych
 * przypadków, a przedstawiony program będzie działał jedynie w niektórych
 * spośród nich.
 * @author Ian Darwin
 */
// BEGIN main
public class ClassesInPackage {

    /** To rozwiązanie zostało początkowo opublikowane 
     * przez Paula Kuita w witrynie Stackoverflow 
     * (http://stackoverflow.com/questions/1456930/), lecz w swojej 
     * pierwotnej postaci obsługiwało tylko pojedyncze pliki umieszczone
     * w katalogu podanym w ścieżce dostępu do klas, a nie w plikach JAR.
     * Swoją drogą, nie obsługuje ono klas systemowych!
     * @param packageName
     * @return
     * @throws IOException
     */
    public static String[] getPackageContent(String packageName)
        throws IOException {

        final String packageAsDirName = packageName.replace(".", "/");
        final List<String> list = new ArrayList<>();
        final Enumeration<URL> urls = 
                Thread.currentThread().
                getContextClassLoader().
                getResources(packageAsDirName);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            // System.out.println("Adres URL = " + url);
            String file = url.getFile();
            switch (url.getProtocol()) {
            case "file":
                // To jest łatwy przypadek: "file" jest pełną ścieżką  
                // do katalogu podanego w ścieżce dostępu do klas.
                File dir = new File(file);
                for (File f : dir.listFiles()) {
                    list.add(packageAsDirName + "/" + f.getName());
                }
                break;
            case "jar":
                // To jest trudniejszy przypadek - dla niektórych 
                // plików JAR zawierających co najmniej jedną klasę 
                // z podanego pakietu "file" może przybierać postać 
                // "jar:/home/ian/bleah/darwinsys.jar!com/darwinsys/io".
                int colon = file.indexOf(':');
                int bang = file.indexOf('!');
                String jarFileName = file.substring(colon + 1, bang);
                JarFile jarFile = new JarFile(jarFileName);
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry e = entries.nextElement();
                    String jarEntryName = e.getName();
                    if (!jarEntryName.endsWith("/") &&
                        jarEntryName.startsWith(packageAsDirName)) {
                        list.add(jarEntryName);
                    }
                }
                break;
            default:
                throw new IllegalStateException(
                "Nie wiem, co zrobić z adresem URL " + url);
            }
        }
        return list.toArray(new String[] {});
    }
    
    public static void main(String[] args) throws IOException {
        String[] names = getPackageContent("com.darwinsys.io");
        for (String name : names) {
            System.out.println(name);
        }
        System.out.println("Gotowe.");
    }
}
// END main
