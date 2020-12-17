package views;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import controller.MenuController;

public class FtpDirectoryView {
	
	static JList filesList;
	
	public FtpDirectoryView() {
		filesList = new JList();
		filesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	public JList getList(String[] filesNames) {
		DefaultListModel listModel = new DefaultListModel();
		filesList.removeAll();
		for(int i = 0; i < filesNames.length; i++) {
			listModel.addElement(filesNames[i]);
		}
		filesList.setModel(listModel);
		return filesList;
	}
	
}