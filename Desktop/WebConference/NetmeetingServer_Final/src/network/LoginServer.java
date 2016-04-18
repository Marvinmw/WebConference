package network;

import java.net.*;
import java.util.*;
import java.io.*;

import userManagement.InfoManageService;
import userManagement.UserlistManage;

import data.Staff;

public class LoginServer implements Runnable {
	private int port = ConnectConfig.loginPort;
	private Socket socket;
	private Staff checkStaff;
	private ArrayList<Socket> socketlist = new ArrayList<Socket>();
	private ArrayList<Staff> userlist = new ArrayList<Staff>();
	ServerSocket serverSocket;
	private BufferedReader b;
	private InfoManageService infoManageService;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private ObjectInputStream inputFromClient;
	private ObjectOutputStream outputToClient;
	private UserlistManage userlistManage;

	public LoginServer() {
		try {
			infoManageService = new InfoManageService();
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			try {
				socket = serverSocket.accept();
//				HandleAClient task = new HandleAClient(socket);
				socketlist.add(socket);
//				Thread t = new Thread(task);
//				t.start();
				b = new BufferedReader(new InputStreamReader(socket
						.getInputStream()));
				inputFromClient = new ObjectInputStream(socket.getInputStream());
				Staff staff;
			
					staff = (Staff) inputFromClient.readObject();
				
				checkStaff = infoManageService.checkUser(staff.getID(), staff
						.getPassword());
				userlistManage = new UserlistManage();
				userlist.add(checkStaff);
				for(int i=0; i<userlist.size();i++){
					for(int j=userlist.size()-1;j>i;j--){
						if(userlist.get(j).equals(userlist.get(i)))
							userlist.remove(userlist.get(j));
					}
				}
				userlistManage.writeBack(userlist);

				outputToClient = new ObjectOutputStream(socket
						.getOutputStream());
				outputToClient.writeObject(checkStaff);
			} catch (Exception ex) {
				System.err.println(ex);
			}
		}
	}

//	class HandleAClient implements Runnable {
//		private Socket socket;
//		private BufferedReader b;
//		private InfoManageService infoManageService;
//		private ObjectInputStream ois;
//		private ObjectOutputStream oos;
//		private ObjectInputStream inputFromClient;
//		private ObjectOutputStream outputToClient;
//		private UserlistManage userlistManage;
//
//		public HandleAClient(Socket socket) {
//			this.socket = socket;
//			infoManageService = new InfoManageService();
//
//		}
//
//		public void run() {
//			try {
//				b = new BufferedReader(new InputStreamReader(socket
//						.getInputStream()));
//				inputFromClient = new ObjectInputStream(socket.getInputStream());
//				Staff staff = (Staff) inputFromClient.readObject();
//				checkStaff = infoManageService.checkUser(staff.getID(), staff
//						.getPassword());
//				userlistManage = new UserlistManage();
//				userlist.add(checkStaff);
//				userlistManage.writeBack(userlist);
//
//				outputToClient = new ObjectOutputStream(socket
//						.getOutputStream());
//				outputToClient.writeObject(checkStaff);
//
//			} catch (ClassNotFoundException ex) {
//				ex.printStackTrace();
//			} catch (IOException ex) {
//				ex.printStackTrace();
//			}
//		}
//
//	}

}
