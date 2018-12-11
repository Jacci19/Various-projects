package Books_exercises.JavaReceptury.email;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class AliasBean extends JPanel {
	protected Vector aliVector;
	protected JList aliJList;
	private JTextField nameTF, addrTF;

	public AliasBean() {
		aliVector = new Vector();
		aliJList = new JList();

		aliJList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				int i = aliJList.getSelectedIndex();
				if (i<0) return;
				Alias al = (Alias)aliVector.get(i);
				nameTF.setText(al.getName());
				addrTF.setText(al.getAddress());
			}
		});

		setLayout(new BorderLayout());
		add(BorderLayout.WEST, new JScrollPane(aliJList));

		JPanel rightPanel = new JPanel();
		add(BorderLayout.EAST, rightPanel);
		rightPanel.setLayout(new GridLayout(0, 1));

		JPanel buttons = new JPanel();
		rightPanel.add(buttons);
		buttons.setLayout(new GridLayout(0, 1, 15, 15));
		JButton b;
		buttons.add(b = new JButton("Set"));
		b.setToolTipText("Dodaj lub zmień alias");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int i = aliJList.getSelectedIndex();
				if (i<0) {
					//dialog z informacją o błędzie
					return;
				}
				setAlias(i, nameTF.getText(), addrTF.getText());
			}
		});
		buttons.add(b = new JButton("Delete"));
		b.setToolTipText("Usuń wybrany alias");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int i = aliJList.getSelectedIndex();
				if (i<0) {
					return;
				}
				deleteAlias(i);
			}
		});
		buttons.add(b = new JButton("Zastosuj"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.err.println("JESZCZE NIE ZAPISANY");
			}
		});

		JPanel fields = new JPanel();
		rightPanel.add(fields);
		fields.setLayout(new GridLayout(2,2));
		fields.add(new JLabel("Nazwa"));
		fields.add(nameTF = new JTextField(10));
		fields.add(new JLabel("Adres"));
		fields.add(addrTF = new JTextField(20));
	}

	public String expandAlias(String ali) {
		Alias a = findAlias(ali);
		if (a == null)
			return null;
		return a.getAddress();
	}

	public Alias findAlias(String ali) {
		for (int i=0; i<aliVector.size(); i++) {
			Alias a = (Alias)aliVector.get(i);
			if (a.getName().equals(ali))
				return a;
		}
		return null;	// Nie znaleziono
	}

	/** Dodaj alias */
	public void addAlias(Alias a) {
		Alias al = findAlias(a.getName());
		if (al == null) {
			aliVector.addElement(a);
		} else {
			// 
		}
		aliJList.setListData(aliVector);
	}

	/** Dodaj nowy alias za pomocą składowych */
	public void addAlias(String nn, String addr) {
		addAlias(new Alias(nn, addr));
	}

	/** Zmień alias */
	public void setAlias(int n, String nam, String addr) {
		aliVector.setElementAt(new Alias(nam, addr), n);
		aliJList.setListData(aliVector);
	}

	public void deleteAlias(int i) {
		aliVector.removeElementAt(i);
		aliJList.setListData(aliVector);
	}
}
