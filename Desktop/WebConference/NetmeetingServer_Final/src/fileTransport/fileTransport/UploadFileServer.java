package fileTransport.fileTransport;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import network.ConnectConfig;

public class UploadFileServer implements Runnable {
	int port = ConnectConfig.fileUploadPort;
	String fileName = null;
	ServerSocket ss;
	ArrayList<UploadFileServerService> uploadFilelist = new ArrayList<UploadFileServerService>();

	public UploadFileServer() {
		try {
			ss = new ServerSocket(port);
			System.out.println("UploadFile server has started.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			Socket socket = null;
			try {
				socket = ss.accept();
				UploadFileServerService th = new UploadFileServerService(socket);
				th.start();
				uploadFilelist.add(th);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String args[]) {
		UploadFileServer upload = new UploadFileServer();
		Thread uploadServer = new Thread(upload);
		uploadServer.start();
	}

	private class UploadFileServerService extends Thread {
		private Socket socket;

		private UploadFileServerService(Socket so) {
			socket = so;
		}

		public void run() {

			DataInputStream inputStream = null;
			try {
				inputStream = new DataInputStream(new BufferedInputStream(
						socket.getInputStream()));
			} catch (Exception e) {
				System.out.println("接收消息缓存错误!");
				return;
			}

			try {
				int bufferSize = 30000;
				byte[] buf = new byte[bufferSize];
				int passedlen = 0;
				long len = 0;
				String savePath = inputStream.readUTF();
				File file = new File(savePath);
				if (!file.exists())
					file.createNewFile();

				DataOutputStream fileOut = new DataOutputStream(
						new BufferedOutputStream(new BufferedOutputStream(
								new FileOutputStream(file))));
				len = inputStream.readLong();

				System.out.println("上传文件的长度为:" + len + "\n");
				System.out.println("服务器开始接收文件!" + "\n");
				if (savePath != null & fileOut != null) {
					while (true) {
						int read = 0;
						if (inputStream != null) {
							try {
								read = inputStream.read(buf);
							} catch (Exception ex) {
								break;
							}
						}
						passedlen += read;
						if (read == -1) {
							break;
						}
						System.out.println("文件接收了" + (passedlen * 100 / len)
								+ "%\n");
						fileOut.write(buf, 0, read);
					}
					System.out.println("服务器接收文件完成，文件存为" + savePath + "\n");
					fileOut.flush();
					fileOut.close();

				}
			} catch (Exception e) {
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//e.printStackTrace();

			}

			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
