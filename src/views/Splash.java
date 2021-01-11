package views;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Splash extends JFrame {

	public Splash() {
		dibujarVentana();
	}

	private void dibujarVentana() {
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Splash.class.getResource("/images/loaderPdf.gif")));
		label.setBounds(101, 202, 195, 168);
		panel.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("SEGURIDAD");
		lblNewLabel_1.setForeground(new Color(60, 179, 113));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 35));
		lblNewLabel_1.setBounds(73, 28, 258, 130);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Bienvenido, espere mientras la aplicaci\u00F3n se inicia.");
		lblNewLabel_2.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(12, 347, 376, 40);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("SOCIAL");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(new Color(60, 179, 113));
		lblNewLabel_1_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 35));
		lblNewLabel_1_1.setBounds(73, 80, 258, 130);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Splash.class.getResource("/images/fondoBlanco.jpg")));
		lblNewLabel.setBounds(0, 0, 400, 400);
		panel.add(lblNewLabel);
		this.setSize(400, 400);
		this.setTitle("Splash");
		this.setUndecorated(true);
		this.setLocation(300, 300);
		ImageIcon gifCarga = new ImageIcon("/images/gifCarga.gif");
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Runnable ejecutable = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Splash splash = new Splash();
				splash.setVisible(true);
				try {
					Thread.sleep(4 * 1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				splash.dispose();

			}
		};

		Thread tarea = new Thread(ejecutable);
		tarea.start();

	}

}

