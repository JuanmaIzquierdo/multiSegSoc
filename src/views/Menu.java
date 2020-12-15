package views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JMenuItem;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	static Menu frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 696, 450);
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
		
		
		JMenuItem mntmNuevoArchivo = new JMenuItem("Nuevo Archivo");
		mntmNuevoArchivo.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				JPanel panelFile = new JPanel();
				panelFile.setBounds(0, 27, 677, 376);
				contentPane.add(panelFile);
				// Creamos el objeto JFileChooser
				JFileChooser fc = new JFileChooser();
				// Abrimos la ventana, guardamos la opcion seleccionada por el usuario				
				panelFile.add(fc);					
				File fichero = fc.getSelectedFile();			       
			    panelFile.setVisible(false);
			    panelFile.setVisible(true);
			}
			
		});
		
		mntmNuevoArchivo.setBackground(new Color(60, 179, 113));
		mntmNuevoArchivo.setOpaque(true);
		mntmNuevoArchivo.setForeground(new Color(255, 255, 255));
		mnNewMenu.add(mntmNuevoArchivo);
		
		JMenuItem mntmBorrarArchivo = new JMenuItem("Borrar Archivo");
		mntmBorrarArchivo.setBackground(new Color(60, 179, 113));
		mntmBorrarArchivo.setOpaque(true);
		mntmBorrarArchivo.setForeground(new Color(255, 255, 255));
		mnNewMenu.add(mntmBorrarArchivo);
		
		JMenuItem mntmModificarArchvo = new JMenuItem("Modificar archvo");
		mntmModificarArchvo.setBackground(new Color(60, 179, 113));
		mntmModificarArchvo.setOpaque(true);
		mntmModificarArchvo.setForeground(new Color(255, 255, 255));
		mnNewMenu.add(mntmModificarArchvo);
		
		JMenu mnEmail = new JMenu("E-mail");
		mnEmail.setForeground(new Color(255, 255, 255));
		menuBar.add(mnEmail);
		
		JMenu mnRegistro = new JMenu("Registro");
	
		mnRegistro.setForeground(new Color(255, 255, 255));
		menuBar.add(mnRegistro);
		
		JMenuItem mntmNuevoUsuario = new JMenuItem("Nuevo Usuario");
		mntmNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JPanel panelMenu = new JPanel();
				panelMenu.revalidate();
				panelMenu.setBackground(new Color(192, 192, 192));
				panelMenu.setBounds(0, 23, 677, 380);
				contentPane.add(panelMenu);
				panelMenu.setLayout(null);
				
				JLabel label = new JLabel("");
				label.setBounds(338, 5, 0, 0);
				panelMenu.add(label);
				
				JLabel lblNewLabel = new JLabel("Nombre");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
				lblNewLabel.setForeground(new Color(255, 255, 255));
				lblNewLabel.setBounds(113, 34, 150, 16);
				panelMenu.add(lblNewLabel);
				
				JLabel lblNewLabel_1 = new JLabel("Apellido");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
				lblNewLabel_1.setForeground(new Color(255, 255, 255));
				lblNewLabel_1.setBounds(113, 88, 150, 16);
				panelMenu.add(lblNewLabel_1);
				
				JLabel lblNewLabel_2 = new JLabel("Email");
				lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
				lblNewLabel_2.setForeground(new Color(255, 255, 255));
				lblNewLabel_2.setBounds(113, 142, 150, 16);
				panelMenu.add(lblNewLabel_2);
				
				JLabel lblNewLabel_3 = new JLabel("Contrase\u00F1a");
				lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
				lblNewLabel_3.setForeground(new Color(255, 255, 255));
				lblNewLabel_3.setBounds(113, 196, 150, 16);
				panelMenu.add(lblNewLabel_3);
				
				JLabel lblNewLabel_4 = new JLabel("Repita contrase\u00F1a");
				lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
				lblNewLabel_4.setForeground(new Color(255, 255, 255));
				lblNewLabel_4.setBounds(113, 250, 150, 16);
				panelMenu.add(lblNewLabel_4);
				
				textField = new JTextField();
				textField.setBounds(275, 34, 273, 22);
				panelMenu.add(textField);
				textField.setColumns(10);
				
				textField_1 = new JTextField();
				textField_1.setBounds(275, 89, 273, 22);
				panelMenu.add(textField_1);
				textField_1.setColumns(10);
				
				textField_2 = new JTextField();
				textField_2.setBounds(275, 144, 273, 22);
				panelMenu.add(textField_2);
				textField_2.setColumns(10);
				
				textField_3 = new JTextField();
				textField_3.setBounds(275, 199, 273, 22);
				panelMenu.add(textField_3);
				textField_3.setColumns(10);
				
				textField_4 = new JTextField();
				textField_4.setBounds(275, 254, 273, 22);
				panelMenu.add(textField_4);
				textField_4.setColumns(10);
				
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.setBounds(290, 314, 97, 25);
				panelMenu.add(btnRegistrar);
				contentPane.setVisible(false);
				contentPane.setVisible(true);
			}
		});
		mnRegistro.add(mntmNuevoUsuario);
		
		JMenu mnAcercaDe = new JMenu("Acerca de");
		mnAcercaDe.setForeground(new Color(255, 255, 255));
		menuBar.add(mnAcercaDe);
		
		JMenu menu = new JMenu("");
		mnAcercaDe.add(menu);
		
		JMenu menu_1 = new JMenu("");
		menuBar.add(menu_1);
		
		
		
		
	}
}