package views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import controller.MenuController;

import java.awt.Dimension;

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
	JFileChooser fc;
	MenuController controller;

	/**
	 * Launch the application.
	 */
	// constructor que pida por parametro un menuController*****************+
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// frame = new Menu();
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */
	// public Menu() {
	// // TODO Auto-generated constructor stub
	// //}
	public Menu(MenuController controller) {
		this.controller = controller;
		fc = new JFileChooser();
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
				fc.setCurrentDirectory(new File(System.getProperty("user.home")
						+ System.getProperty("file.separator") + "Documentos"));
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

		// JMenuItem mntmModificarArchvo = new JMenuItem("Modificar archvo");
		// mntmModificarArchvo.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent arg0) {
		// String boton = "modificar";
		// menuFilechooserSubirFichero(boton);
		// }
		// });
		// mntmModificarArchvo.setBackground(new Color(60, 179, 113));
		// mntmModificarArchvo.setOpaque(true);
		// mntmModificarArchvo.setForeground(new Color(255, 255, 255));
		// mnNewMenu.add(mntmModificarArchvo);

		JMenu mnEmail = new JMenu("E-mail");
		mnEmail.setForeground(new Color(255, 255, 255));
		menuBar.add(mnEmail);

		JMenu mnNewMenu_1 = new JMenu("A\u00F1adir emails aqui");
		mnEmail.add(mnNewMenu_1);

		JMenu mnAcercaDe = new JMenu("Acerca de");
		mnAcercaDe.setForeground(new Color(255, 255, 255));
		menuBar.add(mnAcercaDe);

		JMenuItem mntmNewMenuItem = new JMenuItem("Acerca De");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnAcercaDe.add(mntmNewMenuItem);

		JMenu menu_1 = new JMenu("");
		menuBar.add(menu_1);

		JPanel panel = new JPanel();
		panel.setBounds(10, 39, 653, 411);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNombre_1 = new JLabel("Nombre 1");
		lblNombre_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre_1.setBounds(176, 49, 300, 20);
		panel.add(lblNombre_1);

		JLabel lblNombre_1_1 = new JLabel("Nombre 2");
		lblNombre_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre_1_1.setBounds(176, 82, 300, 20);
		panel.add(lblNombre_1_1);

		JLabel lblNombre_1_2 = new JLabel("Nombre 3");
		lblNombre_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre_1_2.setBounds(176, 115, 300, 20);
		panel.add(lblNombre_1_2);

		JLabel lblNombre_1_3 = new JLabel("Nombre 4");
		lblNombre_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre_1_3.setBounds(176, 148, 300, 20);
		panel.add(lblNombre_1_3);

		JLabel lblGrupoDam = new JLabel("GRUPO 3 D.A.M.");
		lblGrupoDam.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrupoDam.setBounds(0, 366, 136, 45);
		panel.add(lblGrupoDam);

		JLabel lblVersion = new JLabel("Version 1.0");
		lblVersion.setBounds(588, 395, 65, 16);
		panel.add(lblVersion);

		JLabel lblCopyright = new JLabel("Copyright \u00A9 (2020-2021)");
		lblCopyright.setBounds(12, 224, 150, 16);
		panel.add(lblCopyright);

		JLabel lblSeguridadSocial = new JLabel("Seguridad Social");
		lblSeguridadSocial.setBounds(548, 224, 105, 16);
		panel.add(lblSeguridadSocial);
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
				controller.deleteFile(controller.getTreePath(
						tree.getSelectionPath(), 0));
				menuListaFicherosFtp(homeDirectory);
			}
		});

		JButton btnRename = new JButton("Renombrar");
		btnRename.setBounds(475, 85, 110, 35);
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newName = JOptionPane.showInputDialog("Nuevo nombre");
				if (newName != null || !newName.equalsIgnoreCase("")) {
					newName = controller.getTreePath(tree.getSelectionPath(), 1)
							+ newName;
					controller.renameFile(
							controller.getTreePath(tree.getSelectionPath(), 0),
							newName);
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
						+ System.getProperty("file.separator") + "Documents",
						pathComponents[pathComponents.length - 1]);
			}
		});
		//
		JButton btnDirectory = new JButton("Nuevo Directorio");
		btnDirectory.setBounds(475, 205, 110, 35);
		btnDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String path = JOptionPane.showInputDialog("Nombre de carpeta");
				if (path != null || !path.equalsIgnoreCase("")) {
					path = controller.getTreePath(tree.getSelectionPath(), 0)
							+ path + "//";
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
		try {
			contentPane.remove(panelFile);
		} catch (java.lang.NullPointerException e) {
		}

		try {
			contentPane.remove(panelMenu);
		} catch (java.lang.NullPointerException e) {
		}

		try {
			contentPane.remove(panelFicherosFtp);
		} catch (java.lang.NullPointerException e) {
		}

		contentPane.setVisible(false);
		contentPane.setVisible(true);
	}
}