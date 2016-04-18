package view;

import javax.swing.*;

import data.Staff;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import network.UpdateMeetingClient;

public class LaunchFrame extends JFrame {

    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jb_cancel;
    private javax.swing.JButton jb_confirm;
    private javax.swing.JLabel jl_lanuchTime;
    private javax.swing.JLabel jl_lanucher;
    private javax.swing.JLabel jl_title;
    private javax.swing.JTextField jtf_launchTime;
    private javax.swing.JTextField jtf_launcher;
    private javax.swing.JTextField jtf_title;

	private String newMeetingName;
	private Staff staff;
	private static UpdateMeetingClient updateMeetingClient;

	public LaunchFrame(Staff staff, UpdateMeetingClient updateMeetingClient) {
		this.staff = staff;
		this.updateMeetingClient = updateMeetingClient;
		initComponents();
		
	}

	private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jtf_launcher = new javax.swing.JTextField();
        jl_lanucher = new javax.swing.JLabel();
        jl_lanuchTime = new javax.swing.JLabel();
        jtf_launchTime = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jl_title = new javax.swing.JLabel();
        jtf_title = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jb_confirm = new javax.swing.JButton();
        jb_cancel = new javax.swing.JButton();
        
        jtf_launcher.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 18));
        jtf_launcher.setText(staff.getName());
        jtf_launcher.setEditable(false);
        
        jtf_launchTime.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 18));
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());				
		String s = time.substring(0, 10);
        jtf_launchTime.setText(s);       
        jtf_launchTime.setEditable(false);

        jl_lanucher.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 18));
        jl_lanucher.setText("Launcher£º");

        jl_lanuchTime.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 18));
        jl_lanuchTime.setText("Time£º");

        jtf_launchTime.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 18));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jl_lanucher)
                    .addComponent(jl_lanuchTime))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtf_launchTime)
                    .addComponent(jtf_launcher, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_launcher, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl_lanucher))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_launchTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl_lanuchTime)))
        );

        jl_title.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 18));
        jl_title.setText("Enter the name of the new meeting£º");

        jtf_title.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 18));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jtf_title, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jl_title, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jl_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jtf_title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jb_confirm.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 14));
        jb_confirm.setText("Confirm");
        

        jb_cancel.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 14));
        jb_cancel.setText("Cancel");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jb_confirm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(jb_cancel)
                .addGap(47, 47, 47))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jb_confirm)
                .addComponent(jb_cancel))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        
		this.setTitle("Launch New Meeting");
		this.setLocationByPlatform(true);
		this.pack();

		jb_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				newMeetingName = jtf_title.getText();
				System.out.println(" ´´½¨ÐÂµÄ»áÒé");
				if(!newMeetingName.equalsIgnoreCase("")){
					updateMeetingClient.refresh(newMeetingName);	
					LaunchFrame.this.setVisible(false);
				}else{
					JOptionPane.showMessageDialog(null, "Meeting name can not be empty!");
				}				
			}
		});

		jb_cancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				LaunchFrame.this.setVisible(false);		
			}
		});
	}

	public String getMeetingName() {
		return newMeetingName;
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
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(LaunchFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(LaunchFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(LaunchFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(LaunchFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				//new LaunchFrame(null).setVisible(true);
			}
		});
	}

}
