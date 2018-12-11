package Books_exercises.JavaReceptury.json;

import java.util.List;

public class SoftwareInfo {
	public String name;
	public String version;
	public String description;
	public String className;
	public List<String> contributors;
	
	@Override
	public String toString() {
		return String.format("Program: %s (%s); Autorzy: %s", name, version, contributors);
	}
}
