package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
		setBounds(100, 100, 695, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 677, 26);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Archivos");
		
		menuBar.add(mnNewMenu);
		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(0, 23, 677, 311);
		contentPane.add(panelMenu);
		
		JMenu mnNuevoFichero = new JMenu("Nuevo Archivos");
		mnNuevoFichero.addActionListener(new ActionListener() {
			JFileChooser fc = new JFileChooser();

			// Abrimos la ventana, guardamos la opcion seleccionada por el usuario
			int seleccion = fc.showOpenDialog(contentPane);

			// Si el usuario, pincha en aceptar
			if (seleccion == JFileChooser.APPROVE_OPTION) {

				// Seleccionamos el fichero
				File fichero = fc.getSelectedFile();

				// Ecribe la ruta del fichero seleccionado en el campo de texto
				 ruta=fichero.getAbsolutePath();
				 ruta=ruta.replace("\\","\\\\");
				textField.setText(ruta);

				try (FileReader fr = new FileReader(fichero)) {
					String cadena = "";
					int valor = fr.read();
					while (valor != -1) {
						cadena = cadena + (char) valor;
						valor = fr.read();
					}
					textArea.setText(cadena);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		
		
		
		mnNewMenu.add(mnNuevoFichero);
		
		JMenu mnBorrarmodificarArchivos = new JMenu("Borrar/Modificar Archivos");
		mnNewMenu.add(mnBorrarmodificarArchivos);
		
		JMenu mnEmail = new JMenu("E-mail");
		menuBar.add(mnEmail);
		
		JMenu mnRegistro = new JMenu("Registro");
		menuBar.add(mnRegistro);
		
		JMenu mnAcercaDe = new JMenu("Acerca de");
		menuBar.add(mnAcercaDe);
		
		JMenu menu = new JMenu("");
		mnAcercaDe.add(menu);
		
		JMenu menu_1 = new JMenu("");
		menuBar.add(menu_1);
		
		
	}
}
