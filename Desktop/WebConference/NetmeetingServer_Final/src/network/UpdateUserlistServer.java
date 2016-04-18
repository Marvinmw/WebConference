package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import userManagement.UserlistManage;

import data.Staff;

public class UpdateUserlistServer implements Runnable {
	private int port = ConnectConfig.updateUserPort;
	private ArrayList<Staff> userList = new ArrayList<Staff>();
	private Hashtable<Socket, ObjectOutputStream> socketHash = new Hashtable<Socket, ObjectOutputStream>();
	private ServerSocket ss;

	public UpdateUserlistServer() {
		try {
			ss = new ServerSocket(port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			while (true) {
				Socket socket = ss.accept();
				
				ObjectOutputStream oos = new ObjectOutputStream(
						socket.getOutputStream());
				socketHash.put(socket, oos);
				Update task = new Update(socket, oos);
				new Thread(task).start();
			}
		} catch (Exception ex) {
			System.err.println(ex);
		}

	}

	private class Update implements Runnable {
		private Socket socket;
		private ObjectInputStream ois;
		private ObjectOutputStream oos;
		private UserlistManage userlistManage;

		private MeetingCommand meetingCommand;
		private boolean serverUpdateUser = true;

		private String staffName;

		public Update(Socket socket, ObjectOutputStream oos) {
			this.socket = socket;
			this.oos = oos;
			try {
				ois = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			userlistManage = new UserlistManage();
		}

		public void run() {
			try {
				while (serverUpdateUser) {
					meetingCommand = (MeetingCommand) ois.readObject();
					switch (meetingCommand) {
					case JoinMeeting:
						staffName = (String) ois.readUTF();
						userList = userlistManage.read();
						conveyList();
						break;
					case LeaveMeeting:
						System.out.println("client");
						userlistManage.removeLeaveUser(staffName);
						conveyStaff();
						serverUpdateUser = false;
						socketHash.remove(socket);
						break;
					}
				}
				ois.close();
				oos.close();
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void conveyStaff() {
			Enumeration<Socket> socketEnum = socketHash.keys();
			Enumeration<ObjectOutputStream> oosEnum = socketHash.elements();
			while (oosEnum.hasMoreElements()) {
				Socket tempSocket = socketEnum.nextElement();
				ObjectOutputStream tempOos = oosEnum.nextElement();
				try {
					if (!tempSocket.equals(socket)) {
						tempOos.writeObject(MeetingCommand.RemoveStaff);
						tempOos.writeUTF(staffName);
						tempOos.flush();
					} else {
						tempOos.writeObject(MeetingCommand.LeaveMeeting);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		private void conveyList() {
			Enumeration<Socket> socketEnum = socketHash.keys();
			Enumeration<ObjectOutputStream> oosEnum = socketHash.elements();
			while (oosEnum.hasMoreElements()) {
				Socket tempSocket = socketEnum.nextElement();
				ObjectOutputStream tempOos = oosEnum.nextElement();
				try {
					if (tempSocket.equals(socket)) {
						tempOos.writeObject(MeetingCommand.JoinMeeting);
						tempOos.writeObject(userList);
						tempOos.flush();
					} else {
						tempOos.writeObject(MeetingCommand.AddStaff);
						tempOos.writeUTF(staffName);
						tempOos.flush();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String args[]) {
		new UpdateUserlistServer();
	}

}
