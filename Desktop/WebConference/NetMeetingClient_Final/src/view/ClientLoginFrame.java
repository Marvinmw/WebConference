package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import login.LoginClient;

import data.Staff;

public class ClientLoginFrame extends JFrame {
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JButton jb_exit;
	private javax.swing.JButton jb_login;
	private javax.swing.JLabel jl_ID;
	private javax.swing.JLabel jl_background;
	private javax.swing.JLabel jl_psw;
	private javax.swing.JLabel jl_title;
	private javax.swing.JTextField jtf_ID;
	private javax.swing.JPasswordField jpf_psw;

	private LoginClient clientNet;

	public ClientLoginFrame() {
		initComponents();
	}

	private void initComponents() {
		jPanel4 = new javax.swing.JPanel();
		jPanel1 = new javax.swing.JPanel();
		jtf_ID = new javax.swing.JTextField();
		jpf_psw = new javax.swing.JPasswordField();
		jl_ID = new javax.swing.JLabel();
		jl_psw = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		jb_login = new javax.swing.JButton();
		jb_exit = new javax.swing.JButton();
		jPanel3 = new javax.swing.JPanel();
		jl_title = new javax.swing.JLabel();
		jl_background = new javax.swing.JLabel();

		jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder());

		jtf_ID.setFont(new java.awt.Font("微软雅黑", 0, 18));

		jpf_psw.setFont(new java.awt.Font("微软雅黑", 0, 18));

		jl_ID.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jl_ID.setText("UserID");

		jl_psw.setFont(new java.awt.Font("微软雅黑", 0, 18));
		jl_psw.setText("Password");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jl_ID)
														.addComponent(jl_psw))
										.addGap(18, 18, 18)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(jpf_psw)
														.addComponent(
																jtf_ID,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																154,
																Short.MAX_VALUE))
										.addContainerGap(15, Short.MAX_VALUE)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGap(23, 23, 23)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jtf_ID,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jl_ID))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jpf_psw,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jl_psw))
										.addContainerGap(12, Short.MAX_VALUE)));

		jb_login.setFont(new java.awt.Font("微软雅黑", 0, 12));
		jb_login.setText("Login");

		jb_exit.setFont(new java.awt.Font("微软雅黑", 0, 12));
		jb_exit.setText("Exit");

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addComponent(
												jb_login,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												79,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												94, Short.MAX_VALUE)
										.addComponent(
												jb_exit,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												77,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(jb_login).addComponent(jb_exit)));

		jl_title.setFont(new java.awt.Font("微软雅黑", 0, 24));
		jl_title.setText("Net Meeting System");

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout.createSequentialGroup().addGap(20, 20, 20)
						.addComponent(jl_title)
						.addContainerGap(22, Short.MAX_VALUE)));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout
						.createSequentialGroup()
						.addComponent(jl_title)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(
				jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout
				.setHorizontalGroup(jPanel4Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel4Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																false)
														.addComponent(
																jPanel1,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jPanel3,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																jPanel4Layout
																		.createSequentialGroup()
																		.addGap(10,
																				10,
																				10)
																		.addComponent(
																				jPanel2,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jPanel4Layout
				.setVerticalGroup(jPanel4Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel4Layout
										.createSequentialGroup()
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(
												jPanel3,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												40,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jPanel1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(
												jPanel2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(99, 99, 99)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jl_background,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										209,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(340, Short.MAX_VALUE))
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(241, Short.MAX_VALUE)
								.addComponent(jPanel4,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup()
						.addComponent(jl_background,
								javax.swing.GroupLayout.DEFAULT_SIZE, 52,
								Short.MAX_VALUE)
						.addGap(27, 27, 27)
						.addComponent(jPanel4,
								javax.swing.GroupLayout.PREFERRED_SIZE, 237,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		this.setTitle("Net Meeting Login");
		this.setLocationByPlatform(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.pack();

		// 设置背景图片
		jPanel1.setOpaque(false);
		jPanel2.setOpaque(false);
		jPanel3.setOpaque(false);
		jPanel4.setOpaque(false);
		ImageIcon img_background = new ImageIcon("icon/netMeeting.png");
		jl_background.setIcon(img_background);
		jl_background.setBounds(0, 0, img_background.getIconWidth(),
				img_background.getIconHeight());
		this.getLayeredPane()
				.add(jl_background, new Integer(Integer.MIN_VALUE));
		((JPanel) this.getContentPane()).setOpaque(false);

		jb_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!jtf_ID.getText().equals("")
						&& !jpf_psw.getText().equals("")) {
					String idNumber = jtf_ID.getText();
					String password = jpf_psw.getText();
					clientNet = new LoginClient(idNumber, password);
					Staff checkStaff = clientNet.getCheckStaff();

					if (checkStaff == null) {
						JOptionPane.showMessageDialog(null,
								"Wrong login information,please try again!");
					} else {
						UserInfoFrame userInfo;
						try {
							userInfo = new UserInfoFrame(checkStaff);
							userInfo.setVisible(true);
							ClientLoginFrame.this.setVisible(false);
						} catch (Exception e1) {

						}
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Pleade input your information completely!");
				}
			}
		});

		jb_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null,
						"Are you sure to log out？", "yes",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					ClientLoginFrame.this.setVisible(false);
					System.exit(0);
				}
			}
		});

	}

	public static void main(String args[]) {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}

			UIManager
					.setLookAndFeel("org.jvnet.substance.skin.SubstanceFieldOfWheatLookAndFeel");
			UIManager.put("swing.boldMetal", false);
			if (System.getProperty("substancelaf.useDecorations") == null) {
				JFrame.setDefaultLookAndFeelDecorated(true);
				JDialog.setDefaultLookAndFeelDecorated(true);
			}
			System.setProperty("sun.awt.noerasebackground", "true");
			// 设置当前的主题风格，同样我们还可以设置当前的按钮形状，水印风格等等
			// SubstanceLookAndFeel.setCurrentTheme(new
			// SubstanceLightAquaTheme());
			
			//Mac风格
			//javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
			//com.birosoft.liquid.LiquidLookAndFeel.setLiquidDecorations(true);
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger
					.getLogger(ClientLoginFrame.class.getName()).log(
							java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger
					.getLogger(ClientLoginFrame.class.getName()).log(
							java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger
					.getLogger(ClientLoginFrame.class.getName()).log(
							java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger
					.getLogger(ClientLoginFrame.class.getName()).log(
							java.util.logging.Level.SEVERE, null, ex);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ClientLoginFrame().setVisible(true);
			}
		});
	}

}
