package view;

/*
 * SingleChat.java
 *
 * Created on 2012-4-8, 22:02:09
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import javax.swing.*;

import singleChat.*;

public class SingleChatFrame extends javax.swing.JFrame {
	private String senderName;
	private String getterName;
	private PrintWriter out;
	private BufferedReader in;
	Socket theSocket;

	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JButton jb_send;
	private javax.swing.JPanel jp_singleChat;
	private javax.swing.JTextArea jtf_text_in;
	private javax.swing.JTextArea jtf_text_out;

	public SingleChatFrame(String sender, String getter) {
		initComponents();
		this.senderName = sender;
		this.getterName = getter;
		this.setTitle(sender + "   to   " + getter);
	}

	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jp_singleChat = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jtf_text_out = new javax.swing.JTextArea();
		jScrollPane2 = new javax.swing.JScrollPane();
		jtf_text_in = new javax.swing.JTextArea();
		jb_send = new javax.swing.JButton();

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 100,
				Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 100,
				Short.MAX_VALUE));

		jtf_text_out.setColumns(20);
		jtf_text_out.setRows(5);
		jtf_text_out.setEditable(false);
		jScrollPane1.setViewportView(jtf_text_out);

		jtf_text_in.setColumns(20);
		jtf_text_in.setRows(5);
		jScrollPane2.setViewportView(jtf_text_in);

		jb_send.setFont(new java.awt.Font("微软雅黑", 0, 12));
		jb_send.setText("Send");

		javax.swing.GroupLayout jp_singleChatLayout = new javax.swing.GroupLayout(
				jp_singleChat);
		jp_singleChat.setLayout(jp_singleChatLayout);
		jp_singleChatLayout.setHorizontalGroup(jp_singleChatLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						jp_singleChatLayout.createSequentialGroup()
								.addContainerGap().addComponent(jb_send))
				.addComponent(jScrollPane2,
						javax.swing.GroupLayout.DEFAULT_SIZE, 374,
						Short.MAX_VALUE)
				.addComponent(jScrollPane1,
						javax.swing.GroupLayout.DEFAULT_SIZE, 374,
						Short.MAX_VALUE));
		jp_singleChatLayout
				.setVerticalGroup(jp_singleChatLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jp_singleChatLayout
										.createSequentialGroup()
										.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												294, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												106,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jb_send)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jp_singleChat, javax.swing.GroupLayout.PREFERRED_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jp_singleChat, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		this.pack();

		jb_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = jtf_text_in.getText();
				jtf_text_in.setText("");

				Message ms = new Message();
				ms.setCon(s);
				ms.setGetterName(getterName);
				ms.setSenderName(senderName);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyyMMddHH:mm:ss");
				String time = simpleDateFormat.format(new Date());
				String ss = time.substring(8, 16);
				ms.setTime(ss);

				jtf_text_out.append(senderName + "：" + ms.getTime() + "\n"
						+ "    " + s + "\n");

				// 发送给服务器
				try {
					ObjectOutputStream oos = new ObjectOutputStream(
							ManageClientConServerThread
									.getClientConServerThread(senderName)
									.getSocket().getOutputStream());
					oos.writeObject(ms);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				if (JOptionPane.showConfirmDialog((JFrame) arg0.getSource(),
						"Are you sure to log out single-to-single frame?",
						"Exit Confirm", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}

		});
	}

	public void showMessage(Message ms) {
		String s = ms.getSenderName() + ":" + ms.getTime() + "\n" + ms.getCon();
		jtf_text_out.append(s + "\n");

	}
}
