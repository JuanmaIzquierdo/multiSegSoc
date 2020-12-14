package seguridadSocial;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Frame;
import javax.swing.JPasswordField;

public class Login extends JFrame {	
	private Utilities utilities= new Utilities();
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setUndecorated(true);
		setType(Type.UTILITY);
		setBackground(utilities.getGreen());
		setForeground(utilities.getWhite());
		setFont(new Font("Arial", Font.PLAIN, 14));
		setTitle("Seguridad Social");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 418);
		contentPane = new JPanel();
		contentPane.setBackground(utilities.getGrey());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 358, 47);
		panel.setBackground(utilities.getGreen());
		panel.setForeground(utilities.getWhite());
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblLoginSeguridadSocial = new JLabel("Acceso");
		lblLoginSeguridadSocial.setBounds(133, 5, 91, 37);
		lblLoginSeguridadSocial.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblLoginSeguridadSocial.setForeground(utilities.getWhite());
		panel.add(lblLoginSeguridadSocial);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(277, 5, 97, 25);
		
		panel.add(btnNewButton);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 52, 358, 314);
		panel_1.setBackground(utilities.getGrey());
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblIntroduceCorreo = new JLabel("Introduce Correo");
		lblIntroduceCorreo.setForeground(utilities.getWhite());
		lblIntroduceCorreo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIntroduceCorreo.setBounds(101, 26, 156, 34);
		panel_1.add(lblIntroduceCorreo);
		
		textField = new JTextField();
		textField.setBounds(59, 72, 241, 34);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblIntroduceContrasea = new JLabel("Introduce Contrase\u00F1a");
		lblIntroduceContrasea.setForeground(utilities.getWhite());
		lblIntroduceContrasea.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIntroduceContrasea.setBounds(81, 118, 197, 43);
		panel_1.add(lblIntroduceContrasea);
		
		JLabel lblNuevoUsuario = new JLabel("Registrarse");
		lblNuevoUsuario.setForeground(utilities.getBlue());
		lblNuevoUsuario.setBounds(144, 284, 70, 16);
		panel_1.add(lblNuevoUsuario);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setForeground(utilities.getWhite());
		btnEntrar.setBackground(utilities.getGreen());
		btnEntrar.setBounds(112, 228, 134, 43);
		panel_1.add(btnEntrar);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(66, 174, 234, 27);
		panel_1.add(passwordField);
	}
}
 //hola
//Aaaaaaaaaaaaa
//eeeee
