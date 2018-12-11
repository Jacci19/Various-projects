package Books_exercises.JavaReceptury.xml.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

// BEGIN main
/**
 * Demonstracja generowania kodu XML przy użyciu JAXB; klasa stworzona,
 * by reprezentować niektóre pola (wiele!) z ustawieniami użytkownika 
 * w typowej aplikacji z graficznym interfejsem użytkownika;
 * informacje te są używane w konfiguracji aplikacji (a nie 
 * konfiguracji JAXB; są one wykorzystywane w większej aplikacji).
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "configuration", 
    propOrder={"screenName", "webProxy", "verbose", "colorName"})
@XmlRootElement(name = "config")
public class Configuration {

    private String webProxy;
    private boolean verbose;
    private String colorName;
    private String screenName;
    
    public String getColorName() {
        return colorName;
    }
    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    // Pozostałe metody dostępowe, hashCode(), equals() nie są interesujące.
    // END main
    public boolean isVerbose() {
        return verbose;
    }
    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }
    
    public String getWebProxy() {
        return webProxy;
    }
    public void setWebProxy(String webProxy) {
        this.webProxy = webProxy;
    }
    
    public String getScreenName() {
        return screenName;
    }
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((colorName == null) ? 0 : colorName.hashCode());
        result = PRIME * result + ((screenName == null) ? 0 : screenName.hashCode());
        result = PRIME * result + (verbose ? 1231 : 1237);
        result = PRIME * result + ((webProxy == null) ? 0 : webProxy.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Configuration other = (Configuration) obj;
        if (colorName == null) {
            if (other.colorName != null)
                return false;
        } else if (!colorName.equals(other.colorName))
            return false;
        if (screenName == null) {
            if (other.screenName != null)
                return false;
        } else if (!screenName.equals(other.screenName))
            return false;
        if (verbose != other.verbose)
            return false;
        if (webProxy == null) {
            if (other.webProxy != null)
                return false;
        } else if (!webProxy.equals(other.webProxy))
            return false;
        return true;
    }
}
