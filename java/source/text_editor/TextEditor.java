package cn.edu.seu.cose.javacourse.ch07;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TextEditor {
	private Frame f;
	private MenuBar mb;
	private Menu m;
	private TextArea ta;
	private MenuItem openItem, saveItem, closeItem;
	private FileDialog openDia, saveDia;
	private File file;

	public TextEditor() {
		f = new Frame("Text Editor");
		f.setBounds(300, 100, 650, 600);
		f.setVisible(true);
		mb = new MenuBar();	
		ta = new TextArea();
		m = new Menu("File");
		openItem = new MenuItem("Open");
		saveItem = new MenuItem("Save");
		closeItem = new MenuItem("Exit");
		m.add(openItem);
		m.add(saveItem);
		m.add(closeItem);
		mb.add(m);
		addEvent();
		openDia = new FileDialog(f, "Open...", FileDialog.LOAD);
		saveDia = new FileDialog(f, "Save.", FileDialog.SAVE);
		f.setMenuBar(mb);
		f.add(ta);
	}

	private void addEvent() {
		openItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openDia.setVisible(true);
				String dirPath = openDia.getDirectory();
				String fileName = openDia.getFile();
				if (dirPath == null || fileName == null)
					return;
				ta.setText("");
				file = new File(dirPath, fileName);
				try {
					BufferedReader bufr = new BufferedReader(new FileReader(file));
					String line = null;
					while ((line = bufr.readLine()) != null) {
						ta.append(line + "\r\n");
					}
					bufr.close();
				} catch (Exception e1) {
					throw new RuntimeException("error reading");
				}
			}
		});

		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					buf.flush();
					buf.close();
				} catch (IOException e1) {
					throw new RuntimeException("∂¡»° ß∞‹");
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
	} // this is the closing brace of method addEvent

	public static void main(String[] args) {
		new TextEditor();
	}
}