package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import data.User;

public class AddUser extends javax.swing.JFrame {

	private javax.swing.JButton jb_OK;
	private javax.swing.JButton jb_cancel;
	private javax.swing.JLabel jl_ID;
	private javax.swing.JLabel jl_name;
	private javax.swing.JLabel jl_password;
	private javax.swing.JLabel jl_position;
	private javax.swing.JLabel jl_title;
	private javax.swing.JPanel jp_modifyInfo;
	private javax.swing.JPasswordField jpf_password;
	private javax.swing.JTextField jtf_ID;
	private javax.swing.JTextField jtf_name;
	private javax.swing.JTextField jtf_position;

	public AddUser() {
		initComponents();
	}

	private void initComponents() {

		jl_title = new javax.swing.JLabel();
		jb_cancel = new javax.swing.JButton();
		jp_modifyInfo = new javax.swing.JPanel();
		jl_ID = new javax.swing.JLabel();
		jl_password = new javax.swing.JLabel();
		jl_name = new javax.swing.JLabel();
		jl_position = new javax.swing.JLabel();
		jtf_ID = new javax.swing.JTextField();
		jpf_password = new javax.swing.JPasswordField();
		jtf_name = new javax.swing.JTextField();
		jtf_position = new javax.swing.JTextField();
		jb_OK = new javax.swing.JButton();

		// setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jl_title.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 24));
        jl_title.setText("Add New User");

        jb_cancel.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 14));
        jb_cancel.setText("Cancel");

        jl_ID.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 18));
        jl_ID.setText("UserID");

        jl_password.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 18));
        jl_password.setText("Password");

        jl_name.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 18));
        jl_name.setText("Name");

        jl_position.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 18));
        jl_position.setText("Position");

        jtf_ID.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 18));

        jpf_password.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 18));

        jtf_name.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 18));

        jtf_position.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 18));

        javax.swing.GroupLayout jp_modifyInfoLayout = new javax.swing.GroupLayout(jp_modifyInfo);
        jp_modifyInfo.setLayout(jp_modifyInfoLayout);
        jp_modifyInfoLayout.setHorizontalGroup(
            jp_modifyInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_modifyInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_modifyInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jl_password)
                    .addComponent(jl_ID)
                    .addComponent(jl_name)
                    .addComponent(jl_position))
                .addGap(18, 18, 18)
                .addGroup(jp_modifyInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtf_position, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                    .addComponent(jtf_name, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                    .addComponent(jtf_ID, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                    .addComponent(jpf_password, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
                .addContainerGap())
        );
        jp_modifyInfoLayout.setVerticalGroup(
            jp_modifyInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_modifyInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_modifyInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_ID)
                    .addComponent(jtf_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jp_modifyInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_password)
                    .addComponent(jpf_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jp_modifyInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jl_name)
                    .addComponent(jtf_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jp_modifyInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtf_position, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl_position))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jb_OK.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 14));
        jb_OK.setText("Confirm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jb_OK)
                        .addGap(29, 29, 29)
                        .addComponent(jb_cancel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jp_modifyInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jl_title)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jl_title)
                .addGap(18, 18, 18)
                .addComponent(jp_modifyInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_OK)
                    .addComponent(jb_cancel))
                .addContainerGap(21, Short.MAX_VALUE))
        );

		this.pack();
		this.setTitle("Add New User");

		jb_OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = jtf_name.getText();
				String id = jtf_ID.getText();
				String password = jpf_password.getText();
				String position = jtf_position.getText();

				if (name.equals("") || id.equals("") || password.equals("")
						|| position.equals(""))
					JOptionPane.showMessageDialog(null, "Incompletely input£¬please try again!");
				else {
					User user = new User(name, id, password, position);
					ServerLoginFrame.infoManageService.addStaffService(user);
					JOptionPane.showMessageDialog(null, "Success");
					AddUser.this.setVisible(false);

				}

			}
		});

		jb_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUser.this.setVisible(false);
			}
		});
	}

}
