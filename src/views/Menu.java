package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import Models.Message;
import Models.SendEmailRequest;
import controller.MenuController;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	static Menu frame;
	JPanel panelInicio;
	JPanel panelFile;
	JPanel panelMenu;
	JPanel panelFicherosFtp;
	private JPanel panelEmail;
	JPanel panelAcercaDe;
	JFileChooser fc;
	MenuController controller;
	private final Action action = new SwingAction();
	private JTextField emailTo;
	private JTextField emailSub;
	private ArrayList<JPanel> panelEmails = new ArrayList<JPanel>();
	private JMenuItem mntmRecibirCorreo;
	private JScrollPane panelPane;
	private JPanel emailIndex;
	private JTextArea details;
	private JCheckBox emailCheckBox;

	public Menu(MenuController controller) {
		setTitle("Seguridad Social");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"src//images//logoIcon.png"));
		this.controller = controller;
		fc = new JFileChooser();
		setBackground(UIManager.getColor("Button.shadow"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 498);
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
				panelInicio.setVisible(false);
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
				panelInicio.setVisible(false);
				menuListaFicherosFtp(controller.getHomeDirectory());
			}
		});
		mntmBorrarArchivo.setBackground(new Color(60, 179, 113));
		mntmBorrarArchivo.setOpaque(true);
		mntmBorrarArchivo.setForeground(new Color(255, 255, 255));
		mnNewMenu.add(mntmBorrarArchivo);

		JMenu mnEmail = new JMenu("E-mail");
		mnEmail.setForeground(new Color(255, 255, 255));
		menuBar.add(mnEmail);

		JMenuItem mntmEnviarCorreo = new JMenuItem("Enviar Correo");
		mntmEnviarCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menuEnvioDeCorreo();
			}
		});
		mntmEnviarCorreo.setBackground(new Color(60, 179, 113));
		mntmEnviarCorreo.setOpaque(true);
		mntmEnviarCorreo.setForeground(new Color(255, 255, 255));
		mnEmail.add(mntmEnviarCorreo);
		
		mntmRecibirCorreo = new JMenuItem("Recibir Correo");
//		mntmRecibirCorreo.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				menuReciboDeCorreo(new ArrayList<Message>());
//			}
//		});
		mntmRecibirCorreo.setBackground(new Color(60, 179, 113));
		mntmRecibirCorreo.setOpaque(true);
		mntmRecibirCorreo.setForeground(new Color(255, 255, 255));
		mnEmail.add(mntmRecibirCorreo);
		

		JMenu mnAcercaDe = new JMenu("Acerca de");
		mnAcercaDe.setForeground(new Color(255, 255, 255));
		menuBar.add(mnAcercaDe);

		JMenuItem mntmNewMenuItem = new JMenuItem("Acerca De");
		mntmNewMenuItem.setForeground(Color.WHITE);
		mntmNewMenuItem.setBackground(new Color(60, 179, 113));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelInicio.setVisible(false);
				vaciarVentana();
				menuAcercaDe();
			}
		});
		mnAcercaDe.add(mntmNewMenuItem);
		
		panelInicio = new JPanel();
		panelInicio.setBounds(0, 27, 677, 436);
		contentPane.add(panelInicio);
		panelInicio.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("SEGURIDAD SOCIAL");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblNewLabel_2.setBounds(12, 174, 541, 81);
		panelInicio.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Menu.class.getResource("/images/logoIcon.png")));
		lblNewLabel_3.setBounds(558, 157, 101, 123);
		panelInicio.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Menu.class.getResource("/images/fondoBlancoSi.jpg")));
		lblNewLabel_1.setBounds(-17, 0, 694, 436);
		panelInicio.add(lblNewLabel_1);

	}
	
	public void updateEmailIndex(ArrayList<Message> emails) {
		panelEmails.clear();
		emailIndex.removeAll();
		for (Message email : emails) {
			JPanel panelEmailWrapper = new JPanel(new GridLayout(0, 1));
			panelEmails.add(panelEmailWrapper);
			JLabel from = new JLabel("De: " + email.getFrom());
			JLabel sub = new JLabel("Asunto: " + email.getSubject());
			panelEmailWrapper.add(from);
			panelEmailWrapper.add(sub);

			panelEmailWrapper.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {

				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseClicked(MouseEvent e) {
					details.setText("From: " + email.getFrom() + " \n Subject: " + email.getSubject() +
							" \n MessageBody: " + email.getMessageBody() + " \n Date: " + email.getDate() 
					);
					controller.flagAsAdded(email);
				}
			});
			emailIndex.add(panelEmails.get(panelEmails.size() - 1));
		}
		contentPane.updateUI();
	}

	public void menuReciboDeCorreo(ArrayList<Message> emails) {
		vaciarVentana();
		panelEmail = new JPanel();
		contentPane.updateUI();
		panelEmail.setBounds(0, 27, 650, 356);
		panelPane = new JScrollPane(panelEmail);
		panelPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panelPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelPane.setBounds(0, 27, 650, 356);
		panelPane.setVisible(true);
		contentPane.add(panelPane);
		panelEmail.setLayout(new GridLayout(0, 1));
		emailCheckBox = new JCheckBox("Get all emails");
		emailCheckBox.setBounds(10,10,150,30);
		emailCheckBox.setSelected(true);
		emailCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				controller.changeStateOfRecievingEmails(((JCheckBox)e.getSource()).isSelected());
			}
		});
		panelEmail.add(emailCheckBox);
		if(emailIndex == null) {
			emailIndex = new JPanel(new GridLayout(0, 1));
			emailIndex.setBounds(0, 27, 325, 356);
		}
		JPanel emailDetails = new JPanel(new GridLayout(0, 1));
		emailDetails.setBounds(325, 27, 325, 356);
		details = new JTextArea(50, 10);
		
		updateEmailIndex(emails);
		emailDetails.add(details);

		panelEmail.add(emailIndex);
		panelEmail.add(emailDetails);
		panelEmail.setVisible(true);
	}
	
	public void menuEnvioDeCorreo() {
		vaciarVentana();
		panelFile = new JPanel();
		contentPane.updateUI();
		panelFile.setBounds(0, 27, 677, 376);
		contentPane.add(panelFile);
		panelFile.setLayout(null);

		JLabel lblNewLabel = new JLabel("Asunto:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));

		lblNewLabel.setBounds(26, 62, 83, 16);
		panelFile.add(lblNewLabel);


		JTextField emailSub = new JTextField();
		emailSub.setBorder(new LineBorder(Color.BLACK));
		emailSub.setFont(new Font("Tahoma", Font.PLAIN, 20));
		emailSub.setBounds(157, 53, 493, 35);
		panelFile.add(emailSub);
		emailSub.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Para:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(28, 20, 54, 16);
		panelFile.add(lblNewLabel_1);

		JTextField emailTo = new JTextField();
		emailTo.setBorder(new LineBorder(Color.BLACK));
		emailTo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		emailTo.setBounds(157, 13, 493, 32);
		panelFile.add(emailTo);
		emailTo.setColumns(10);


		JLabel lblNewLabel_2 = new JLabel("Mensaje");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(26, 103, 74, 25);
		panelFile.add(lblNewLabel_2);

		JTextArea emailMsg = new JTextArea();
		emailMsg.setBorder(new LineBorder(new Color(0, 0, 0)));
		emailMsg.setTabSize(4);
		emailMsg.setFont(new Font("Monospaced", Font.PLAIN, 20));
		emailMsg.setRows(4);
		emailMsg.setBounds(19, 141, 646, 123);
		panelFile.add(emailMsg);


		JPanel errorWrapper = new JPanel();
		errorWrapper.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		errorWrapper.setBounds(208, 284, 256, 53);
		errorWrapper.setLayout(new BoxLayout(errorWrapper, BoxLayout.Y_AXIS));


		JPanel response = new JPanel();
		response.setFont(new Font("Tahoma", Font.PLAIN, 20));
		response.setBounds(0, 350, 677, 25);

		JButton btnSend = new JButton("Enviar");
		btnSend.setForeground(Color.WHITE);
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSend.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				errorWrapper.removeAll();
				boolean isCorrecrSub = checkValue("Cabecera", emailSub.getText(), false, errorWrapper);
				boolean isCorrecrEmail = checkValue("Email", emailTo.getText(), true, errorWrapper);
				boolean isCorrecrMsg = checkValue("Mensaje", emailMsg.getText(), false, errorWrapper);
				contentPane.updateUI();
				if(isCorrecrEmail && isCorrecrMsg && isCorrecrSub) {
							System.out.println("All ok");
							SendEmailRequest emailRequest = new SendEmailRequest("", "", emailTo.getText(), 
											emailSub.getText(), emailMsg.getText());
							String sendEmailResponse = controller.sendEmail(emailRequest);
							JLabel label = new JLabel(sendEmailResponse);
							response.add(label);
						} else {
							System.out.println("Failure");
						}
					}
				});
				btnSend.setBackground(new Color(60, 179, 113));
				btnSend.setBounds(12, 277, 160, 60);
				panelFile.add(btnSend);

				JButton btnReset = new JButton("Resetear Valores");
				btnReset.setForeground(Color.WHITE);
				btnReset.setFont(new Font("Tahoma", Font.PLAIN, 20));
				btnReset.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
				btnReset.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						emailSub.setText("");
						emailTo.setText("");
						emailMsg.setText("");
					}
				});
				btnReset.setBackground(new Color(60, 179, 113));
				btnReset.setBounds(505, 277, 160, 60);
				panelFile.add(btnReset);
				panelFile.setVisible(true);
				panelFile.add(errorWrapper);
				panelFile.add(response);
	}
	
	private boolean checkValue(String labelName, String value, boolean isEmail, JPanel errorWrapper) {
		if(value.trim().equalsIgnoreCase("")) {
			JLabel label = new JLabel(labelName + " no puede estar vacio");
			errorWrapper.add(label);
			return false;
		}
		if(isEmail) {
			if(isValidEmailAddress(value)) {
				return true;
			} else {
				JLabel label = new JLabel(labelName + " no es correcto. Debe contener...");
				errorWrapper.add(label);
				return false;
			}
		}
		return true;
	}

	public boolean isValidEmailAddress(String email) {
		Pattern  regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        Matcher regMatcher = regexPattern.matcher(email);
        return regMatcher.matches();
 }

	//
	public void menuAcercaDe() {
		vaciarVentana();
		
		getContentPane().setLayout(null);

		panelAcercaDe = new JPanel();
		panelAcercaDe.setBounds(0, 0, 653, 411);
		getContentPane().add(panelAcercaDe);
		panelAcercaDe.setLayout(null);

		JLabel lblNombre_1 = new JLabel("Alejandro S\u00E1nchez Rodr\u00EDguez");
		lblNombre_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNombre_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre_1.setBounds(12, 169, 300, 20);
		panelAcercaDe.add(lblNombre_1);

		JLabel lblNombre_1_1 = new JLabel("Pablo M\u00E9rida Egea");
		lblNombre_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNombre_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre_1_1.setBounds(12, 202, 300, 20);
		panelAcercaDe.add(lblNombre_1_1);

		JLabel lblNombre_1_2 = new JLabel("Vitaliy Bay");
		lblNombre_1_2.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNombre_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre_1_2.setBounds(22, 235, 300, 20);
		panelAcercaDe.add(lblNombre_1_2);

		JLabel lblNombre_1_3 = new JLabel("Juan Manuel Izquierdo");
		lblNombre_1_3.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNombre_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre_1_3.setBounds(12, 268, 300, 20);
		panelAcercaDe.add(lblNombre_1_3);

		JLabel lblGrupoDam = new JLabel("GRUPO 2 D.A.M.");
		lblGrupoDam.setFont(new Font("Arial", Font.PLAIN, 20));
		lblGrupoDam.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrupoDam.setBounds(37, 111, 232, 45);
		panelAcercaDe.add(lblGrupoDam);

		JLabel lblVersion = new JLabel("Version 1.0");
		lblVersion.setBounds(576, 382, 65, 16);
		panelAcercaDe.add(lblVersion);

		JLabel lblCopyright = new JLabel("Copyright \u00A9 (2020-2021)");
		lblCopyright.setBounds(12, 376, 220, 24);
		panelAcercaDe.add(lblCopyright);
		lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		lblCopyright.setFont(new Font("Arial", Font.PLAIN, 20));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(
				"src//images//logoPng.png"));
		lblNewLabel.setBounds(408, 67, 200, 221);
		panelAcercaDe.add(lblNewLabel);
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
				try {
					controller.uploadFile(fichero);
				}catch(java.lang.NullPointerException e) {
					Utilities.showMessage("Error de subida. Solo se pueden subir ficheros", true);
				}
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
		btnRemove.setBounds(475, 25, 140, 35);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.deleteFile(controller.getTreePath(tree.getSelectionPath(), 0));
				menuListaFicherosFtp(homeDirectory);
			}
		});
		
		JButton btnRename = new JButton("Renombrar");
		btnRename.setBounds(475, 85, 140, 35);
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newName = JOptionPane.showInputDialog("Nuevo nombre");
				if(newName != null && !newName.equalsIgnoreCase("")) {
					newName = controller.getTreePath(tree.getSelectionPath(), 1) + newName;
					controller.renameFile(controller.getTreePath(tree.getSelectionPath(), 0), newName);
					menuListaFicherosFtp(homeDirectory);
				}
			}
		});
		
		JButton btnDownload = new JButton("Descargar");
		btnDownload.setBounds(475, 145, 140, 35);
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String path = controller.getTreePath(tree.getSelectionPath(), 0);
				String[] pathComponents = path.split("/");
				controller.downloadFile(path, System.getProperty("user.home") 
						+ System.getProperty("file.separator")+ "Documents"
						,pathComponents [pathComponents.length - 1]);
			}
		});
		//
		JButton btnDirectory = new JButton("Nuevo Directorio");
		btnDirectory.setBounds(475, 205, 140, 35);
		btnDirectory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String path = JOptionPane.showInputDialog("Nombre de carpeta");
                if(path != null && !path.equalsIgnoreCase("")) {
                    path = controller.getTreePath(tree.getSelectionPath(), 0) + path+"//";
                    try {
                        controller.createDirectory(path);
    				}catch(java.lang.NullPointerException e) {
    					Utilities.showMessage("Error al crear directorio. El nombre no puede estar repetido.", true);
    				}
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
		} catch (java.lang.NullPointerException e) {}

		try {
			contentPane.remove(panelMenu);
		} catch (java.lang.NullPointerException e) {}

		try {
			contentPane.remove(panelFicherosFtp);
		} catch (java.lang.NullPointerException e) {}
		
		try{
			contentPane.remove(panelEmail);
		}catch(java.lang.NullPointerException e) {}

		try{
			contentPane.remove(panelPane);
		}catch(java.lang.NullPointerException e) {}
		
		try {
			contentPane.remove(panelAcercaDe);
		} catch (java.lang.NullPointerException e) {}
		
		try {
			contentPane.remove(panelInicio);
		}catch (java.lang.NullPointerException e) {}

		contentPane.setVisible(false);
		contentPane.setVisible(true);
	}

	public JPanel getEmailPanel() {
		return panelEmail;
	}
	public JMenuItem getMntmRecibirCorreo() {
		return mntmRecibirCorreo;
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	
}