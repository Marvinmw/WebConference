package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;

import data.*;

import network.UpdateMeetingClient;

public class UserInfoFrame extends JFrame {
	private UpdateMeetingClient updateMeetingClient;

	private JScrollPane jScrollPane1;
	private JToolBar.Separator jSeparator1;
	private JToolBar jToolBar1;
	private JButton jb_enterMeeting;
	private JButton jb_lanchMeeting;
	private JButton jb_refresh;
	private JLabel jl_head;
	private JLabel jl_name;
	private JList jlist_meetinglist;
	private JPanel jp_head;
	private JPanel jp_info;
	private JPanel jp_meetingHistory;
	private JPanel jp_refresh;

	private AbstractListModel nameModel;
	private ArrayList<String> meetingNameList = null;
	private String newMeetingName;
	private Staff staff;
	private String meetingFileName;

	public UserInfoFrame(Staff staff) throws Exception {
		this.staff = staff;
		updateMeetingClient = new UpdateMeetingClient();
		meetingNameList = updateMeetingClient.getMeetingNameList();
		initComponents();
		updateMeetingClient.setJList(jlist_meetinglist);
	}

	private void initComponents() {
		jp_meetingHistory = new JPanel();
		jp_refresh = new JPanel();
		jb_refresh = new JButton();
		jScrollPane1 = new JScrollPane();
		jlist_meetinglist = new JList();
		jp_info = new JPanel();
		jp_head = new JPanel();
		jl_head = new JLabel();
		jl_name = new JLabel();
		jToolBar1 = new JToolBar();
		jb_lanchMeeting = new JButton();
		jSeparator1 = new JToolBar.Separator();
		jb_enterMeeting = new JButton();

		jb_refresh.setFont(new java.awt.Font("Œ¢»Ì—≈∫⁄", 0, 12));
		jb_refresh.setText("Refresh");

		jlist_meetinglist.setFont(new java.awt.Font("Œ¢»Ì—≈∫⁄", 0, 24));

		nameModel = new AbstractListModel() {

			String[] strings = getMeetingNames();

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		};

		jlist_meetinglist.setModel(nameModel);

		jScrollPane1.setViewportView(jlist_meetinglist);

		javax.swing.GroupLayout jp_refreshLayout = new javax.swing.GroupLayout(
				jp_refresh);
		jp_refresh.setLayout(jp_refreshLayout);
		jp_refreshLayout.setHorizontalGroup(jp_refreshLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jp_refreshLayout.createSequentialGroup()
								.addGap(105, 105, 105).addComponent(jb_refresh)
								.addContainerGap(111, Short.MAX_VALUE)));
		jp_refreshLayout.setVerticalGroup(jp_refreshLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jb_refresh));

		javax.swing.GroupLayout jp_meetingHistoryLayout = new javax.swing.GroupLayout(
				jp_meetingHistory);
		jp_meetingHistory.setLayout(jp_meetingHistoryLayout);
		jp_meetingHistoryLayout.setHorizontalGroup(jp_meetingHistoryLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jp_refresh, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(jScrollPane1,
						javax.swing.GroupLayout.DEFAULT_SIZE, 293,
						Short.MAX_VALUE));
		jp_meetingHistoryLayout
				.setVerticalGroup(jp_meetingHistoryLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jp_meetingHistoryLayout
										.createSequentialGroup()
										.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												420, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jp_refresh,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)));

		jp_head.setBackground(new java.awt.Color(255, 255, 255));

		javax.swing.GroupLayout jp_headLayout = new javax.swing.GroupLayout(
				jp_head);
		jp_head.setLayout(jp_headLayout);
		jp_headLayout.setHorizontalGroup(jp_headLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jl_head, javax.swing.GroupLayout.DEFAULT_SIZE, 80,
				Short.MAX_VALUE));
		jp_headLayout.setVerticalGroup(jp_headLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jl_head, javax.swing.GroupLayout.DEFAULT_SIZE, 75,
				Short.MAX_VALUE));

		jl_name.setFont(new java.awt.Font("Œ¢»Ì—≈∫⁄", 0, 14));
		jl_name.setText(staff.getName() + "(" + staff.getID() + ")");

		jToolBar1.setRollover(true);

		jb_lanchMeeting.setFont(new java.awt.Font("Œ¢»Ì—≈∫⁄", 0, 12)); // NOI18N
		jb_lanchMeeting.setText("Launch Meeting");
		jb_lanchMeeting.setFocusable(false);
		jb_lanchMeeting
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jb_lanchMeeting
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

		jToolBar1.add(jb_lanchMeeting);
		jToolBar1.add(jSeparator1);

		jb_enterMeeting.setFont(new java.awt.Font("Œ¢»Ì—≈∫⁄", 0, 12));
		jb_enterMeeting.setText("Enter Meeting");
		jb_enterMeeting.setFocusable(false);
		jb_enterMeeting
				.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jb_enterMeeting
				.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

		jToolBar1.add(jb_enterMeeting);

		javax.swing.GroupLayout jp_infoLayout = new javax.swing.GroupLayout(
				jp_info);
		jp_info.setLayout(jp_infoLayout);
		jp_infoLayout
				.setHorizontalGroup(jp_infoLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jp_infoLayout
										.createSequentialGroup()
										.addComponent(
												jp_head,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(
												jp_infoLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jp_infoLayout
																		.createSequentialGroup()
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jToolBar1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jp_infoLayout
																		.createSequentialGroup()
																		.addGap(30,
																				30,
																				30)
																		.addComponent(
																				jl_name,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				134,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))));
		jp_infoLayout
				.setVerticalGroup(jp_infoLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jp_infoLayout
										.createSequentialGroup()
										.addContainerGap(13, Short.MAX_VALUE)
										.addComponent(jl_name)
										.addGap(18, 18, 18)
										.addComponent(
												jToolBar1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												25,
												javax.swing.GroupLayout.PREFERRED_SIZE))
						.addComponent(jp_head,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jp_meetingHistory, 0,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(jp_info, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jp_info,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jp_meetingHistory,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));

		this.pack();
		this.setTitle("Net Meeting System 2012");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// œ¬√Ê…Ë÷√Õ∑œÒ
		jp_head.setOpaque(false);
		ImageIcon img_head = new ImageIcon("icon/user.png");
		jl_head.setIcon(img_head);
		jl_head.setBounds(0, 0, img_head.getIconWidth(),
				img_head.getIconHeight());

		// …Ë÷√À¢–¬Õº±Í
		jb_refresh.setIcon(new ImageIcon("icon/refresh.png"));

		jlist_meetinglist.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 1) {
					int index = list.locationToIndex(evt.getPoint());
					meetingFileName = (String) jlist_meetinglist.getModel()
							.getElementAt(index);
					System.out.println(meetingFileName);
				}
			}
		});

		jb_lanchMeeting.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				LaunchFrame launchFrame = new LaunchFrame(staff,
						updateMeetingClient);
				launchFrame.setVisible(true);
			}
		});

		jb_refresh.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateMeetingClient.refresh("");
			}
		});

		jb_enterMeeting.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if (meetingFileName != null) {
					try {
						NetMeetingFrame meetingFrame = new NetMeetingFrame(
								staff, meetingFileName);
						meetingFrame.setVisible(true);
						//UserInfoFrame.this.setVisible(false);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Please choose meeting first£°");
				}
			}
		});

	}

	public String[] getMeetingNames() {
		String meetingName[] = new String[meetingNameList.size()];
		for (int i = 0; i < meetingNameList.size(); i++) {
			meetingName[i] = meetingNameList.get(i);
		}
		return meetingName;
	}

}
