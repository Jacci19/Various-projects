package Books_exercises.JavaReceptury.i18npl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.text.*;
import java.util.*;

/**
 * To rozwiązanie zostało opracowane na podsatwie informacji dostępnych 
 * na serwisie Stackoverflow.com na stronie: 
 * http://stackoverflow.com/questions/4659929/how-to-use-utf-8-in-resource-properties-with-resourcebundle
 * 
 */

// BEGIN main
public class MessageFormatDemoIntlBundleControl {

    private static Date date = new Date();
    private static String fileName = "myfile.txt";

    public static void main(String[] args) throws UnsupportedEncodingException {
        
        /**
         * W tym miejscu, tworząc obiekt wiązki zasobów przekazujemy do niej
         * zmodyfikowaną wersję obiektu ResourceBundle.Control, któraj potrafi
         * prawidłowo odczytywać pliki właściwości zapisane przy użyciu 
         * kodowania UTF-8. 
         */
        ResourceBundle rb = ResourceBundle.getBundle("Widgets", new UTF8Control());
        
        String format = rb.getString("filedialogs.cantopen.format");
        String result = MessageFormat.format(format, date, fileName);
        System.out.println(result);
    }
}

/**
 * Poniższa klasa umożliwia odczytywanie plików właściwości zapisanych przy 
 * użyciu kodowania UTF-8. 
 */

class UTF8Control extends ResourceBundle.Control {
    public ResourceBundle newBundle
        (String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
            throws IllegalAccessException, InstantiationException, IOException
    {
        // Poniższy kod jest kopią domyślnej implementacji klasy.
        String bundleName = toBundleName(baseName, locale);
        String resourceName = toResourceName(bundleName, "properties");
        ResourceBundle bundle = null;
        InputStream stream = null;
        if (reload) {
            URL url = loader.getResource(resourceName);
            if (url != null) {
                URLConnection connection = url.openConnection();
                if (connection != null) {
                    connection.setUseCaches(false);
                    stream = connection.getInputStream();
                }
            }
        } else {
            stream = loader.getResourceAsStream(resourceName);
        }
        if (stream != null) {
            try {
                // Zmodyfikowano tylko ten wiersz kodu, by własciwości były odczytywane 
                // przy użyciu kodowania UTF-8.
                bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
            } finally {
                stream.close();
            }
        }
        return bundle;
    }
}