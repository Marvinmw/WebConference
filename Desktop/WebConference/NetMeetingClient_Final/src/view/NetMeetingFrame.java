package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.text.SimpleDateFormat;

//import singleChatServer.ManageClientThread;
import textChat.*;
import singleChat.*;
import voiceChat.*;
import fileTransport.transport.*;
import whiteBoard.ui.Board;
import javax.swing.*;

import network.UpdateUserlistClient;

import data.Staff;

public class NetMeetingFrame extends JFrame {

	private Staff staff;
	private ClientConServer ccs;
	private TextChatDealer chatdealer;
	private VoiceChat voiceChat;
	private FileClient fileClient;
	private FileUpload fileUpload;
	private static String meetingName;
	private UpdateUserlistClient updateUserlistClient;

	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JToolBar.Separator jSeparator1;
	private javax.swing.JToolBar.Separator jSeparator2;
	private javax.swing.JToolBar.Separator jSeparator3;
	private javax.swing.JToolBar jToolBar1;
	private javax.swing.JButton jb_downloadFile;
	private javax.swing.JButton jb_send;
	private javax.swing.JButton jb_singleChat;
	private javax.swing.JButton jb_uploadFile;
	private javax.swing.JButton jb_voiceChat;
	private javax.swing.JLabel jl_fileList;
	private javax.swing.JLabel jl_title;
	private javax.swing.JLabel jl_userList;
	private javax.swing.JLabel jl_welcome;
	private JList jlist_file_upload;
	private AbstractListModel alm_fileList;
	private JList jlist_user;
	private AbstractListModel alm_userList;
	private javax.swing.JPanel jp_textChat;
	private javax.swing.JPanel jp_title;
	private javax.swing.JPanel jp_user_file_list;
	private javax.swing.JPanel jp_whiteBoard;
	private javax.swing.JTextArea jtf_text_in;
	private javax.swing.JTextArea jtf_text_out;

	private ImageIcon ii_startVoice = new ImageIcon("icon/voice1.png");
	private ImageIcon ii_stopVoice = new ImageIcon("icon/voice2.png");

	public NetMeetingFrame(Staff staff, String meetingFileName) {
		this.staff = staff;
		this.meetingName = meetingFileName;
		initComponents();
		chatdealer = new TextChatDealer(jtf_text_out);
		fileClient = new FileClient(jlist_file_upload, meetingFileName);
		updateUserlistClient = new UpdateUserlistClient(jlist_user,
				staff.getName());
		updateUserlistClient.start();
		voiceChat = new VoiceChat();
		ccs = new ClientConServer(staff.getName());

	}

	private void initComponents() {

		jp_whiteBoard = new whiteBoard.ui.Board();

		jp_user_file_list = new javax.swing.JPanel();
		jScrollPane3 = new javax.swing.JScrollPane();
		jlist_user = new javax.swing.JList();
		jScrollPane4 = new javax.swing.JScrollPane();
		jlist_file_upload = new javax.swing.JList();
		jl_userList = new javax.swing.JLabel();
		jl_fileList = new javax.swing.JLabel();
		jp_title = new javax.swing.JPanel();
		jl_title = new javax.swing.JLabel();
		jl_welcome = new javax.swing.JLabel();
		jp_textChat = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jtf_text_out = new javax.swing.JTextArea();
		jtf_text_out.setEditable(false);
		jScrollPane2 = new javax.swing.JScrollPane();
		jtf_text_in = new javax.swing.JTextArea();
		jb_send = new javax.swing.JButton();
		jToolBar1 = new javax.swing.JToolBar();
		jb_singleChat = new javax.swing.JButton();
		jSeparator2 = new javax.swing.JToolBar.Separator();
		jb_uploadFile = new javax.swing.JButton();
		jSeparator1 = new javax.swing.JToolBar.Separator();
		jb_downloadFile = new javax.swing.JButton();
		jSeparator3 = new javax.swing.JToolBar.Separator();
		jb_voiceChat = new javax.swing.JButton();

		jlist_user.setFont(new java.awt.Font("微软雅黑", 0, 12));

		alm_userList = new AbstractListModel() {
			String[] strings = {};

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		};
		jlist_user.setModel(alm_userList);

		jScrollPane3.setViewportView(jlist_user);

		jlist_file_upload.setFont(new java.awt.Font("微软雅黑", 0, 12));
		alm_fileList = new AbstractListModel() {
			String[] fileStrings = {};

			public int getSize() {
				return fileStrings.length;
			}

			public Object getElementAt(int i) {
				return fileStrings[i];
			}
		};
		jlist_file_upload.setModel(alm_fileList);

		jScrollPane4.setViewportView(jlist_file_upload);

		jl_userList.setFont(new java.awt.Font("微软雅黑", 0, 14));
		jl_userList.setText("List of participants");

		jl_fileList.setFont(new java.awt.Font("微软雅黑", 0, 14));
		jl_fileList.setText("List of uploaded files");

		javax.swing.GroupLayout jp_user_file_listLayout = new javax.swing.GroupLayout(
				jp_user_file_list);
		jp_user_file_list.setLayout(jp_user_file_listLayout);
		jp_user_file_listLayout.setHorizontalGroup(jp_user_file_listLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jp_user_file_listLayout.createSequentialGroup()
								.addComponent(jl_userList).addContainerGap())
				.addComponent(jScrollPane3,
						javax.swing.GroupLayout.DEFAULT_SIZE, 165,
						Short.MAX_VALUE)
				.addGroup(
						jp_user_file_listLayout.createSequentialGroup()
								.addComponent(jl_fileList).addContainerGap())
				.addComponent(jScrollPane4,
						javax.swing.GroupLayout.DEFAULT_SIZE, 165,
						Short.MAX_VALUE));
		jp_user_file_listLayout
				.setVerticalGroup(jp_user_file_listLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jp_user_file_listLayout
										.createSequentialGroup()
										.addComponent(jl_userList)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane3,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												219,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jl_fileList)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane4,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												219, Short.MAX_VALUE)));

		jl_title.setFont(new java.awt.Font("微软雅黑", 0, 36));
		jl_title.setText("Net Meeting System");

		jl_welcome.setFont(new java.awt.Font("微软雅黑", 0, 18)); // NOI18N
		jl_welcome.setText("Welcome:");

		javax.swing.GroupLayout jp_titleLayout = new javax.swing.GroupLayout(
				jp_title);
		jp_title.setLayout(jp_titleLayout);
		jp_titleLayout
				.setHorizontalGroup(jp_titleLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jp_titleLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(jl_title)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												168, Short.MAX_VALUE)
										.addComponent(jl_welcome)
										.addGap(94, 94, 94)));
		jp_titleLayout
				.setVerticalGroup(jp_titleLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jp_titleLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jp_titleLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jl_title)
														.addComponent(
																jl_welcome))
										.addContainerGap(18, Short.MAX_VALUE)));

		jtf_text_out.setColumns(20);
		jtf_text_out.setFont(new java.awt.Font("微软雅黑", 0, 12));
		jtf_text_out.setRows(5);
		jScrollPane1.setViewportView(jtf_text_out);

		jtf_text_in.setColumns(20);
		jtf_text_in.setFont(new java.awt.Font("微软雅黑", 0, 12));
		jtf_text_in.setRows(5);
		jScrollPane2.setViewportView(jtf_text_in);

		jb_send.setFont(new java.awt.Font("微软雅黑", 0, 12));
		jb_send.setText("Send");

		javax.swing.GroupLayout jp_textChatLayout = new javax.swing.GroupLayout(
				jp_textChat);
		jp_textChat.setLayout(jp_textChatLayout);
		jp_textChatLayout
				.setHorizontalGroup(jp_textChatLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jp_textChatLayout
										.createSequentialGroup()
										.addGroup(
												jp_textChatLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jScrollPane2,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																240,
																Short.MAX_VALUE)
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																240,
																Short.MAX_VALUE)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jp_textChatLayout
																		.createSequentialGroup()
																		.addContainerGap(
																				179,
																				Short.MAX_VALUE)
																		.addComponent(
																				jb_send)))
										.addContainerGap()));
		jp_textChatLayout
				.setVerticalGroup(jp_textChatLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jp_textChatLayout
										.createSequentialGroup()
										.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												342, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												117,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(4, 4, 4).addComponent(jb_send)));

		jToolBar1.setRollover(true);

		jb_singleChat.setFont(new java.awt.Font("微软雅黑", 0, 12));
		// jb_singleChat.setText("单人交流");
		jb_singleChat.setFocusable(false);
		jb_singleChat
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jb_singleChat
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jToolBar1.add(jb_singleChat);
		jToolBar1.add(jSeparator2);

		jb_uploadFile.setFont(new java.awt.Font("微软雅黑", 0, 12));
		// jb_uploadFile.setText("上传文件");
		jb_uploadFile.setFocusable(false);
		jb_uploadFile
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jb_uploadFile
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jToolBar1.add(jb_uploadFile);
		jToolBar1.add(jSeparator1);

		jb_downloadFile.setFont(new java.awt.Font("微软雅黑", 0, 12));
		// jb_downloadFile.setText("下载文件");
		jb_downloadFile.setFocusable(false);
		jb_downloadFile
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jb_downloadFile
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		jToolBar1.add(jb_downloadFile);
		jToolBar1.add(jSeparator3);

		jb_voiceChat.setFont(new java.awt.Font("微软雅黑", 0, 12));
		// jb_voiceChat.setText("语音聊天");
		jb_voiceChat.setFocusable(false);
		jb_voiceChat
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jb_voiceChat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

		jToolBar1.add(jb_voiceChat);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addComponent(jp_user_file_list,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jp_whiteBoard,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jp_textChat,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jToolBar1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										107, Short.MAX_VALUE)
								.addComponent(jp_title,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(
														jToolBar1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														77,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jp_title,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(
														jp_textChat,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														jp_whiteBoard,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														jp_user_file_list,
														javax.swing.GroupLayout.Alignment.LEADING,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
								.addContainerGap()));

		this.pack();
		this.setTitle(meetingName);

		jl_welcome.setText("Welcome : " + staff.getName());
		jb_voiceChat.setIcon(ii_stopVoice);
		jb_singleChat.setIcon(new ImageIcon("icon/singleChat.png"));
		jb_uploadFile.setIcon(new ImageIcon("icon/upload.png"));
		jb_downloadFile.setIcon(new ImageIcon("icon/download.png"));

		// 若在与会人员列表里选中用户并双击，则进行单人交流
		jlist_user.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (jlist_user.getSelectedIndex() != -1) {
					if (e.getClickCount() == 2) {
						doubleClick(jlist_user.getSelectedValue());
					}
				}
			}

			private void doubleClick(Object selectedValue) {
				String getterName = (String) selectedValue;
				SingleChatFrame scf = new SingleChatFrame(staff.getName(),
						getterName);
				scf.setVisible(true);
				ManageSingleChater.addSingleChat(staff.getName() + " "
						+ getterName, scf);
			}
		});

		jb_singleChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jlist_user.getSelectedIndex() != -1) {

					String getterName = (String) jlist_user.getSelectedValue();
					SingleChatFrame scf = new SingleChatFrame(staff.getName(),
							getterName);
					scf.setVisible(true);
					ManageSingleChater.addSingleChat(staff.getName() + " "
							+ getterName, scf);

				} else {
					JOptionPane.showMessageDialog(null,
							"Choose the person who you want to chat with!");
				}
			}
		});

		// 在上传文件列表里选中文件并双击进行文件的下载
		jlist_file_upload.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {
					int index = list.locationToIndex(evt.getPoint());
					System.out.println(index);
					String downloadFileName = (String) jlist_file_upload
							.getModel().getElementAt(index);
					System.out.println(downloadFileName);
					fileClient.setDownloadFileName(downloadFileName);
					fileClient.downloadFile();
				}
			}
		});

		jb_downloadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jlist_file_upload.getSelectedIndex() != -1) {

					String downloadFileName = (String) jlist_file_upload
							.getSelectedValue();
					System.out.println(downloadFileName);
					fileClient.setDownloadFileName(downloadFileName);
					fileClient.downloadFile();

				} else {
					JOptionPane.showMessageDialog(null,
							"Choose file which you want to download!");
				}
			}
		});

		// 发送文字信息按钮监听事件
		jb_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH:mm:ss");
				String time = sdf.format(new Date());
				String s = staff.getName() + "(" + staff.getID() + ")  "
						+ time.substring(8, 16) + "\n" + "   "
						+ jtf_text_in.getText();
				if (sendMessage(s)) {
					jtf_text_out.append(s + "\n");
					jtf_text_in.setText("");
				} else {
					jtf_text_in.setText("send error.\n");
				}
			}

			private boolean sendMessage(String message) {
				try {
					TextChatClient.writeObject(message);
					return true;
				} catch (Exception e) {
					return false;
				}
			}
		});

		// 语音聊天按钮监听事件
		jb_voiceChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (jb_voiceChat.getIcon() == ii_stopVoice) {
					jb_voiceChat.setIcon(ii_startVoice);
					voiceChat.startVoiceChat();
				} else {
					jb_voiceChat.setIcon(ii_stopVoice);
					voiceChat.stopVoiceChat();
				}
			}
		});

		// 上传文件按钮监听事件
		jb_uploadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fileClient.uploadFile();
					update();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			public void update() throws Exception {
				fileClient.getFileUpload().update();
			}
		});

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				chatdealer.stop();
				fileClient.stop();
				updateUserlistClient.leaveMeeting();
				updateUserlistClient.stop();
				ccs.stop();
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(NetMeetingFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(NetMeetingFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(NetMeetingFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(NetMeetingFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				new NetMeetingFrame(null, null).setVisible(true);
			}

		});

	}
}
