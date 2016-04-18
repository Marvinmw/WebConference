package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import serverStartup.StartServer;

import network.LoginServer;

import data.*;

public class ServerFrame extends JFrame {

	// Variables declaration - do not modify
	private JScrollPane jScrollPane1;
	private JButton jb_addUser;
	private JButton jb_deleteUser;
	private JButton jb_modifyUser;
	private JButton jb_on_or_off;
	private JLabel jl_admin;
	private JLabel jl_title;
	private JPanel jp_manageUser;
	private JPanel jp_serverInfo;
	private JTextArea jta_serverInfo;
	
	private Staff staff;

	/** Creates new form MainFrame */
	public ServerFrame(Staff staff) {
		this.staff = staff;
		initComponents();
	}

	private void initComponents() {

		jp_manageUser = new javax.swing.JPanel();
		jb_addUser = new javax.swing.JButton();
		jb_deleteUser = new javax.swing.JButton();
		jb_modifyUser = new javax.swing.JButton();
		jl_admin = new javax.swing.JLabel();
		jb_on_or_off = new javax.swing.JButton();
		jl_title = new javax.swing.JLabel();
		jp_serverInfo = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jta_serverInfo = new javax.swing.JTextArea();

		jl_admin.setText("Adminstrator：" + staff.getName());

		
		jp_manageUser.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User Management", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("微软雅黑", 0, 12))); // NOI18N

        jb_addUser.setFont(new java.awt.Font("微软雅黑", 0, 18));
        jb_addUser.setText("Add User");

        jb_deleteUser.setFont(new java.awt.Font("微软雅黑", 0, 18));
        jb_deleteUser.setText("Delete User");

        jb_modifyUser.setFont(new java.awt.Font("微软雅黑", 0, 18));
        jb_modifyUser.setText("Modify User");

        javax.swing.GroupLayout jp_manageUserLayout = new javax.swing.GroupLayout(jp_manageUser);
        jp_manageUser.setLayout(jp_manageUserLayout);
        jp_manageUserLayout.setHorizontalGroup(
            jp_manageUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_manageUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_manageUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jb_addUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(jb_deleteUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(jb_modifyUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jp_manageUserLayout.setVerticalGroup(
            jp_manageUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_manageUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jb_addUser)
                .addGap(18, 18, 18)
                .addComponent(jb_deleteUser)
                .addGap(18, 18, 18)
                .addComponent(jb_modifyUser)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jl_admin.setFont(new java.awt.Font("微软雅黑", 0, 18));

        jb_on_or_off.setFont(new java.awt.Font("微软雅黑", 0, 14));
        jb_on_or_off.setText("Start Server");

        jl_title.setFont(new java.awt.Font("微软雅黑", 0, 24));
        jl_title.setText("Net Meeting Server");

        jp_serverInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Server Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("微软雅黑", 0, 12))); // NOI18N

        jta_serverInfo.setColumns(20);
        jta_serverInfo.setFont(new java.awt.Font("微软雅黑", 0, 14));
        jta_serverInfo.setRows(5);
        //jta_serverInfo.setText("The server has started......");
        jScrollPane1.setViewportView(jta_serverInfo);

        javax.swing.GroupLayout jp_serverInfoLayout = new javax.swing.GroupLayout(jp_serverInfo);
        jp_serverInfo.setLayout(jp_serverInfoLayout);
        jp_serverInfoLayout.setHorizontalGroup(
            jp_serverInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
        );
        jp_serverInfoLayout.setVerticalGroup(
            jp_serverInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jl_admin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                                .addComponent(jb_on_or_off))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jp_manageUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jp_serverInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jl_title)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jl_title)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_admin)
                    .addComponent(jb_on_or_off))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jp_serverInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jp_manageUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        this.setTitle("Net Meeting System Server 2012");
		this.pack();
		this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		jb_on_or_off.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartServer server = new StartServer();	
				server.startAllServer();
				jb_on_or_off.setEnabled(false);
				jta_serverInfo.setText("The server has started......");
			}
		});
		
		// 添加用户信息按钮反应事件
		jb_addUser.addActionListener(new Adduser());

		// 删除用户信息按钮反应事件
		jb_deleteUser.addActionListener(new Deleteuser());

		// 修改用户信息按钮反应事件
		jb_modifyUser.addActionListener(new Modifyuser());
		
	}

	// 修改用户信息监听类
	private class Modifyuser implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ModifyUser modifyUser = new ModifyUser();
			modifyUser.setVisible(true);
		}

	}

	// 添加用户按钮反应事件类
	private class Adduser implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			AddUser addUser = new AddUser();
			addUser.setVisible(true);
		}
	}

	// 删除用户按钮反应事件类
	private class Deleteuser implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			DeleteUser deleteUser = new DeleteUser();
			deleteUser.setVisible(true);
		}
	}

	// 服务器开启监听事件类
	private class ServerNet implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new StartServer();
			jta_serverInfo.setText("服务器已开启。。。\n");
			jb_on_or_off.setEnabled(false);
		}
	}
}
