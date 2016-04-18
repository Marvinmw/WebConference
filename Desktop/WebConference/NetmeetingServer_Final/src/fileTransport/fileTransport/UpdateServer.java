package fileTransport.fileTransport;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JOptionPane;

import network.ConnectConfig;

import fileTransport.command.FileCommand;


public class UpdateServer implements Runnable {
	private Hashtable<Socket, ObjectOutputStream> hashtable;
	private int port = ConnectConfig.fileUpdatePort;
	ServerSocket ss;

	public UpdateServer() {		
		try {
			ss = new ServerSocket(port);
			System.out.println("UpdateFile server has started.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		hashtable = new Hashtable<Socket, ObjectOutputStream>();
	}
	
	public void run() {
		while (true) {
			Socket socket = null;
			try{
				socket = ss.accept();
				
				
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				UpdateServerService updateClient=new UpdateServerService(socket,oos);
				updateClient.start();
				hashtable.put(socket, oos);	
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}		
	}

	private void sendFileNames(ArrayList<String> fileList) {
		Enumeration<ObjectOutputStream> oosEnum = hashtable.elements();
		while (oosEnum.hasMoreElements()) {
			ObjectOutputStream oos = (ObjectOutputStream) oosEnum
					.nextElement();
			try {
				oos.writeObject(fileList);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private ArrayList<String> getFileNames(String meetingFileName) {
		File fileDir = new File("MeetingList"+File.separator+meetingFileName+File.separator);
		File[] fileList = fileDir.listFiles();
		ArrayList<String> fileNames = new ArrayList<String>();
		for (int i = 0; i < fileList.length; i++) {
			// 判断是否是目录
			if (fileList[i].isDirectory()) {
				fileNames.add(fileList[i].getName() + "is a dir ");
			}
			// 判断是否是文件
			if (fileList[i].isFile()) {
				fileNames.add(fileList[i].getName());
			}
		}
		return fileNames;
	}
	
	
	private class UpdateServerService  extends Thread{
		private Socket socket;
		private ObjectOutputStream outstream ;
	private	UpdateServerService(Socket so,ObjectOutputStream oos ){
		socket=so;
		outstream=oos;
	}
		public void run() {
			while (true) {
				ObjectInputStream ois;
				FileCommand fileCommand;
				boolean isLive = true;
				
				try {
					ois = new ObjectInputStream(socket.getInputStream());
					
				
					String meetingFileName=ois.readUTF();
					if(meetingFileName!=null)
					while (isLive) { 			
						fileCommand = (FileCommand) ois.readObject();
						System.out.println(fileCommand);
						if(fileCommand!=null)
						switch (fileCommand) {
						case Update:
							ArrayList<String> fileList = getFileNames(meetingFileName);
							sendFileNames(fileList);
							break;
						case Leave:
							//hashtable.remove(socket);
							isLive = false;
							break;
						}
					}
				} catch (Exception e) {
				
					this.interrupt();
					break;
					//e.printStackTrace();
				}	
			}
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	}
}
