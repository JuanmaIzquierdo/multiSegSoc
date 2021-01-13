package views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

import controller.loginController;

public class Login extends JFrame {	
	static Login frame;
	private Utilities utilities= new Utilities();
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	static loginController controller;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					controller = new loginController("localhost", 5013);
					frame = new Login();
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
		setBounds(100, 100, 389, 439);
		contentPane = new JPanel();
		contentPane.setBackground(utilities.getGrey());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(15, 31, 358, 60);
		panel.setBackground(utilities.getGreen());
		panel.setForeground(utilities.getWhite());
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblLoginSeguridadSocial = new JLabel("Acceso de usuarios");
		lblLoginSeguridadSocial.setBounds(66, 11, 226, 37);
		lblLoginSeguridadSocial.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblLoginSeguridadSocial.setForeground(utilities.getWhite());
		panel.add(lblLoginSeguridadSocial);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(15, 104, 358, 310);
		panel_1.setBackground(utilities.getGrey());
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblIntroduceCorreo = new JLabel("Correo");
		lblIntroduceCorreo.setForeground(utilities.getWhite());
		lblIntroduceCorreo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIntroduceCorreo.setBounds(144, 26, 70, 34);
		panel_1.add(lblIntroduceCorreo);
		
		textField = new JTextField();
		textField.setBounds(58, 72, 241, 34);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblIntroduceContrasea = new JLabel("Contrase\u00F1a");
		lblIntroduceContrasea.setForeground(utilities.getWhite());
		lblIntroduceContrasea.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIntroduceContrasea.setBounds(125, 118, 107, 43);
		panel_1.add(lblIntroduceContrasea);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.login(textField.getText(), passwordField.getPassword());
			}
		});
		btnEntrar.setForeground(utilities.getWhite());
		btnEntrar.setBackground(utilities.getGreen());
		btnEntrar.setBounds(112, 228, 134, 43);
		panel_1.add(btnEntrar);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(58, 167, 242, 34);
		panel_1.add(passwordField);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton.setBorderPainted(false);
		btnNewButton.setOpaque(false);
		ImageIcon icono =new ImageIcon("src//images//pngwing.com(1)(1).png");
		btnNewButton.setIcon(new ImageIcon("src//images//pngwing.com(1)(1).png"));
		btnNewButton.setBackground(new Color(240, 240, 240));
		btnNewButton.setBounds(365, 0, 24, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblSeguridadSocial = new JLabel("Seguridad Social");
		lblSeguridadSocial.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSeguridadSocial.setForeground(Color.WHITE);
		lblSeguridadSocial.setBounds(66, 0, 131, 33);
		contentPane.add(lblSeguridadSocial);
	}
	
	public static void hacerInvisible() {
		frame.setVisible(false);
	}
}