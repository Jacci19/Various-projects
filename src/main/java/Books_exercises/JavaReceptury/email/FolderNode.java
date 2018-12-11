package Books_exercises.JavaReceptury.email;

import javax.mail.*;
import javax.swing.tree.*;

public class FolderNode extends DefaultMutableTreeNode {
	Folder f;
	FolderNode(Folder f) {
		this.f = f;
	}
	public String toString() {
		return f.getName();
	}
}
