package fileTransport.fileTransport;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import network.ConnectConfig;

public class DownloadFileServer implements Runnable {
	private int port = ConnectConfig.fileDownloadPort;
	private String fileName = null;
	private String filePath;
	private ServerSocket ss;

	public DownloadFileServer() {
		try {
			ss = new ServerSocket(port);
			System.out.println("DownloadFile server has started.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while (true) {
			try {
				Socket socket = ss.accept();
			new 	DownloadFileServerService(socket).start();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void main(String args[]) {
		DownloadFileServer download = new DownloadFileServer();
		Thread downloadServer = new Thread(download);
		downloadServer.start();
	}
private  class DownloadFileServerService  extends Thread{
	private Socket socket;
	private DownloadFileServerService(Socket so){
		socket=so;
	}
	public void run() {

		while (true) {
			try {
				
				DataInputStream dis = new DataInputStream(socket
						.getInputStream());
				String filePath=dis.readUTF();
				File file = new File(filePath);
				System.out.println("服务器传给客户端的文件长度:" + (int) file.length());
				System.out.println("建立socket链接");
				DataInputStream fis = new DataInputStream(
						new BufferedInputStream(new FileInputStream(filePath)));
				DataOutputStream ps = new DataOutputStream(socket
						.getOutputStream());
				ps.writeLong((long) file.length());
				ps.flush();
				int bufferSize = 300000;
				byte[] buf = new byte[bufferSize];
				while (true) {
					int read = 0;
					if (fis != null) {
						read = fis.read(buf);
					}
					if (read == -1) {
						break;
					}
					ps.write(buf, 0, read);
				}
				ps.flush();
				System.out.println("文件传输完成");
				System.out.println("文件下载成功");

			} catch (Exception ex) {
				break;
			}
		}
	}
}
}
