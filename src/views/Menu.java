package views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import controller.MenuController;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	static Menu frame;
	JPanel panelFile;
	JPanel panelMenu;
	JPanel panelFicherosFtp;
	JFileChooser fc ;
	MenuController controller;

	/**
	 * Launch the application.
	 */
	// constructor que pida por parametro un menuController*****************+
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					frame = new Menu();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
//	 public Menu() {
//		// TODO Auto-generated constructor stub
//	//}
	public Menu(MenuController controller) {
		this.controller = controller;
		fc= new JFileChooser();
		setBackground(UIManager.getColor("Button.shadow"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(255, 255, 255));
		menuBar.setBackground(new Color(60, 179, 113));
		menuBar.setBounds(0, 0, 677, 26);
		contentPane.add(menuBar);
		JMenu mnNewMenu = new JMenu("Archivos");
		mnNewMenu.setForeground(new Color(255, 255, 255));
		menuBar.add(mnNewMenu);
		JMenuItem mntmNuevoArchivo = new JMenuItem("Subir Archivo");
		mntmNuevoArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {								
				String boton = "subir";
				fc.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator")+ "Documentos"));
				menuFilechooserSubirFichero(boton);			
			}
		});
		mntmNuevoArchivo.setBackground(new Color(60, 179, 113));
		mntmNuevoArchivo.setOpaque(true);
		mntmNuevoArchivo.setForeground(new Color(255, 255, 255));
		mnNewMenu.add(mntmNuevoArchivo);

		JMenuItem mntmBorrarArchivo = new JMenuItem("Archivos FTP");
		mntmBorrarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menuListaFicherosFtp(controller.getHomeDirectory());
			}
		});
		mntmBorrarArchivo.setBackground(new Color(60, 179, 113));
		mntmBorrarArchivo.setOpaque(true);
		mntmBorrarArchivo.setForeground(new Color(255, 255, 255));
		mnNewMenu.add(mntmBorrarArchivo);

//		JMenuItem mntmModificarArchvo = new JMenuItem("Modificar archvo");
//		mntmModificarArchvo.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				String boton = "modificar";
//				menuFilechooserSubirFichero(boton);
//			}
//		});
//		mntmModificarArchvo.setBackground(new Color(60, 179, 113));
//		mntmModificarArchvo.setOpaque(true);
//		mntmModificarArchvo.setForeground(new Color(255, 255, 255));
//		mnNewMenu.add(mntmModificarArchvo);

		JMenu mnEmail = new JMenu("E-mail");
		mnEmail.setForeground(new Color(255, 255, 255));
		menuBar.add(mnEmail);

		JMenu mnAcercaDe = new JMenu("Acerca de");
		mnAcercaDe.setForeground(new Color(255, 255, 255));
		menuBar.add(mnAcercaDe);

		JMenu menu = new JMenu("");
		mnAcercaDe.add(menu);

		JMenu menu_1 = new JMenu("");
		menuBar.add(menu_1);
	}

	public void menuFilechooserSubirFichero(String boton) {	
		vaciarVentana();
		panelFile = new JPanel();
		contentPane.updateUI();
		panelFile.setBounds(0, 27, 677, 376);
		contentPane.add(panelFile);
		// Creamos el objeto JFileChooser
		// Abrimos la ventana, guardamos la opcion seleccionada por el usuario
		panelFile.add(fc);
		JButton btnNewButton = new JButton(boton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File fichero = fc.getSelectedFile();
				controller.uploadFile(fichero);
			}
		});
		btnNewButton.setBounds(123, 327, 97, 25);
		panelFile.add(btnNewButton);
		panelFile.setVisible(false);
		panelFile.setVisible(true);
	}
	
	public void menuListaFicherosFtp(String homeDirectory) {
		vaciarVentana();

		panelFicherosFtp = new JPanel();
		panelFicherosFtp.setLayout(null);
		contentPane.updateUI();
		panelFicherosFtp.setBounds(25, 27, 677, 376);
		contentPane.add(panelFicherosFtp);
		
		DefaultMutableTreeNode home = new DefaultMutableTreeNode(homeDirectory);
		DefaultTreeModel model = new DefaultTreeModel(home);
		controller.createDirectoryTree(model, "", home);
		JTree tree = new JTree(model);
		tree.setBounds(25, 25, 400, 325);
		
		JButton btnRemove = new JButton("Eliminar");
		btnRemove.setBounds(475, 25, 110, 35);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.deleteFile(controller.getTreePath(tree.getSelectionPath(), 0));
				menuListaFicherosFtp(homeDirectory);
			}
		});
		
		JButton btnRename = new JButton("Renombrar");
		btnRename.setBounds(475, 85, 110, 35);
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newName = JOptionPane.showInputDialog("Nuevo nombre");
				if(newName != null || !newName.equalsIgnoreCase("")) {
					newName = controller.getTreePath(tree.getSelectionPath(), 1) + newName;
					controller.renameFile(controller.getTreePath(tree.getSelectionPath(), 0), newName);
					menuListaFicherosFtp(homeDirectory);
				}
			}
		});
		
		JButton btnDownload = new JButton("Descargar");
		btnDownload.setBounds(475, 145, 110, 35);
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String path = controller.getTreePath(tree.getSelectionPath(), 0);
				String[] pathComponents = path.split("/");
				controller.downloadFile(path, System.getProperty("user.home") 
						+ System.getProperty("file.separator")+ "Documents"
						,pathComponents [pathComponents.length - 1]);
			}
		});
		
		JButton btnDirectory = new JButton("Nuevo Directorio");
		btnDirectory.setBounds(475, 205, 110, 35);
		btnDirectory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String path = JOptionPane.showInputDialog("Nombre de carpeta");
                if(path != null || !path.equalsIgnoreCase("")) {
                    path = controller.getTreePath(tree.getSelectionPath(), 0) + path+"//";
                    controller.createDirectory(path);
                }
                menuListaFicherosFtp(homeDirectory);
            }
        });
			
		panelFicherosFtp.add(tree);
		panelFicherosFtp.add(btnRemove);
		panelFicherosFtp.add(btnRename);
		panelFicherosFtp.add(btnDownload);
		panelFicherosFtp.add(btnDirectory);
		panelFicherosFtp.setVisible(true);
	}
	
	public void vaciarVentana() {
		try{
			contentPane.remove(panelFile);
		}catch(java.lang.NullPointerException e) {}
		
		try{
			contentPane.remove(panelMenu);
		}catch(java.lang.NullPointerException e) {}
		
		try{
			contentPane.remove(panelFicherosFtp);
		}catch(java.lang.NullPointerException e) {}
		
		contentPane.setVisible(false);
		contentPane.setVisible(true);
	}

}