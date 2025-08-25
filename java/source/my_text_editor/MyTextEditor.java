package cn.edu.seu.cose.javacourse.ch07.texteditor;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MyTextEditor {
	private JFrame f;
	private JMenuBar mb;
	private JMenu m;
	private JTextArea ta;
	private JMenuItem openItem, saveItem, closeItem;
	private FileDialog openDia, saveDia;
	private File file;
	private String title; // the original title of a file
	private boolean allowChangingTitle = true; // a state that shows whether the title has been added with an asterisk;

	public MyTextEditor() {
		f = new JFrame("Text Editor");
		f.setBounds(300, 100, 650, 600);
		f.setVisible(true);
		mb = new JMenuBar();
		ta = new JTextArea();
		m = new JMenu("File");
		openItem = new JMenuItem("Open");
		saveItem = new JMenuItem("Save");
		closeItem = new JMenuItem("Exit");
		m.add(openItem);
		m.add(saveItem);
		m.add(closeItem);
		mb.add(m);
		title = "untitled";
		addEvent();
		openDia = new FileDialog(f, "Open...", FileDialog.LOAD);
		saveDia = new FileDialog(f, "Save.", FileDialog.SAVE);
		f.setJMenuBar(mb);
		f.setTitle(title);
		f.add(ta);
	}

	private void addEvent() {
		openItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allowChangingTitle = false; // preventing the modification of title during the open operation;
				System.out.println("opening");
				openDia.setVisible(true);
				String dirPath = openDia.getDirectory();
				String fileName = openDia.getFile();
				if (dirPath == null || fileName == null)
					return;
				ta.setText("");
				file = new File(dirPath, fileName);
				title = fileName;
				f.setTitle(title);
				try {
					BufferedReader bufr = new BufferedReader(new FileReader(file));
					String line = null;
					while ((line = bufr.readLine()) != null) {
						ta.append(line + "\r\n");
					}
					bufr.close();
				} catch (Exception e1) {
					throw new RuntimeException("error reading");
				} finally {
					allowChangingTitle = true; // allowing modification on title; 
				}
			}
		});

		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allowChangingTitle = false; // preventing the modification of title during the save operation;
				System.out.println("saving");
				if (file == null) {
					saveDia.setVisible(true);
					String dirPath = saveDia.getDirectory();
					String fileName = saveDia.getFile();
					if (dirPath == null || fileName == null)
						return;
					file = new File(dirPath, fileName);
				}
				BufferedWriter buf;
				try {
					buf = new BufferedWriter(new FileWriter(file));
					String text = ta.getText();
					buf.write(text);
					buf.write("\n");
					buf.flush();
					buf.close();
					f.setTitle(title);
				} catch (IOException e1) {
					throw new RuntimeException("error writing");
				} finally {
					allowChangingTitle = false; // allowing modification on title;
				}

			}
		});

		closeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		ta.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent arg0) {
				addAsterisk();
			}

			public void insertUpdate(DocumentEvent arg0) {
				addAsterisk();
			}

			public void removeUpdate(DocumentEvent arg0) {
				addAsterisk();
			}

			private void addAsterisk() {
				System.out.println("changing");
				if (allowChangingTitle) {
					f.setTitle("*" + title);
					allowChangingTitle = false;
				}
			}
		});
	}

	public static void main(String[] args) {
		new MyTextEditor();
	}
}