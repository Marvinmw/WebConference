package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class DeleteUser extends javax.swing.JFrame {

	private javax.swing.JButton jb_OK;
    private javax.swing.JButton jb_cancel;
    private javax.swing.JLabel jl_enter_in_ID;
    private javax.swing.JTextField jtf_userID;
	
    public DeleteUser() {
        initComponents();
    }
            
    private void initComponents() {

        jl_enter_in_ID = new javax.swing.JLabel();
        jb_OK = new javax.swing.JButton();
        jb_cancel = new javax.swing.JButton();
        jtf_userID = new javax.swing.JTextField();

        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jl_enter_in_ID.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 18));
        jl_enter_in_ID.setText("Enter the userID to delete ");

        jb_OK.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 14));
        jb_OK.setText("Confirm");

        jb_cancel.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 14));
        jb_cancel.setText("Cancel");
        

        jtf_userID.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 0, 18));
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jb_OK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addComponent(jb_cancel))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jl_enter_in_ID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtf_userID, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))))
                .addGap(65, 65, 65))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jl_enter_in_ID)
                .addGap(18, 18, 18)
                .addComponent(jtf_userID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_OK)
                    .addComponent(jb_cancel))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        
        this.pack();
        this.setTitle("Delete User");
        
        jb_OK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String id = jtf_userID.getText();
				if(ServerLoginFrame.infoManageService.deleteStaffService(id)){
					JOptionPane.showMessageDialog(null, "Delete successfully");
					DeleteUser.this.setVisible(false);	
				}else{
					JOptionPane.showMessageDialog(null, "Delete insuccessfully");
				}
			}
		});
        
        jb_cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				DeleteUser.this.setVisible(false);	
			}
		});
    }                   

}
