package views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

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
	FtpDirectoryView directoryView;

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
		directoryView = new FtpDirectoryView();
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

		JMenuItem mntmBorrarArchivo = new JMenuItem("Borrar Archivo");
		mntmBorrarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JList filesList = directoryView.getList(controller.getFilesNames());
				
				menuListaFicherosFtp(filesList);
				
				//vaciarVentana();
				//panelFile.add(filesList);
				//String boton = "borrar";
				//contentPane.updateUI();
				//menuFilechooser(boton);
			}
		});
		mntmBorrarArchivo.setBackground(new Color(60, 179, 113));
		mntmBorrarArchivo.setOpaque(true);
		mntmBorrarArchivo.setForeground(new Color(255, 255, 255));
		mnNewMenu.add(mntmBorrarArchivo);

		JMenuItem mntmModificarArchvo = new JMenuItem("Modificar archvo");
		mntmModificarArchvo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String boton = "modificar";
				menuFilechooserSubirFichero(boton);
			}
		});
		mntmModificarArchvo.setBackground(new Color(60, 179, 113));
		mntmModificarArchvo.setOpaque(true);
		mntmModificarArchvo.setForeground(new Color(255, 255, 255));
		mnNewMenu.add(mntmModificarArchvo);

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
		//a
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
	
	public void menuListaFicherosFtp(JList list) {
		vaciarVentana();
		panelFicherosFtp = new JPanel();
		contentPane.updateUI();
		panelFicherosFtp.setBounds(0, 27, 677, 376);
		contentPane.add(panelFicherosFtp);
		panelFicherosFtp.add(list);
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