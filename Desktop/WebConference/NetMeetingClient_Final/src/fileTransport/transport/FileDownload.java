package fileTransport.transport;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileDownload extends Thread {
	private String ip;
	private int downloadPort;
	private Socket downloadSocket = null;
	private DataOutputStream dos;
	private DataInputStream dis;
	private String downloadFileName;
	private String meetingFileName;

	public FileDownload(String ip, int downloadPort, String downloadFileName,
			String meetingFileName) {
		this.meetingFileName = meetingFileName;
		this.ip = ip;
		this.downloadPort = downloadPort;
		this.downloadFileName = downloadFileName;
		initNetwork();
	}

	private void initNetwork() {
		try {
			downloadSocket = new Socket(ip, downloadPort);
			dos = new DataOutputStream(downloadSocket.getOutputStream());
			dis = new DataInputStream(downloadSocket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		downloadFile();
	}

	public void downloadFile() {
		try {
			int result = 0;
			String savePath = null;
			JFileChooser jfc = new JFileChooser();
			jfc.setFileSelectionMode(JFileChooser.SAVE_DIALOG
					| JFileChooser.DIRECTORIES_ONLY);
			result = jfc.showSaveDialog(jfc);
			if (result == JFileChooser.APPROVE_OPTION) {
				savePath = jfc.getSelectedFile().getAbsolutePath()
						+ File.separator + downloadFileName;
				int bufferSize = 30000;
				byte[] buf = new byte[bufferSize];
				int passedlen = 0;
				long len = 0;
				DataOutputStream fileOut = new DataOutputStream(
						new BufferedOutputStream(new BufferedOutputStream(
								new FileOutputStream(savePath))));
				dos.writeUTF("MeetingList" + File.separator + meetingFileName
						+ File.separator + downloadFileName);
				dos.flush();
				len = dis.readLong();

				System.out.println("下载文件的长度为:" + len + "\n");
				System.out.println("开始下载文件!" + "\n");

				while (true) {
					int read = 0;
					if (dis != null) {
						read = dis.read(buf);
					}
					passedlen += read;
					System.out.println("文件下载了" + (passedlen * 100 / len)
							+ "%\n");
					fileOut.write(buf, 0, read);
					if (passedlen == len) {
						break;
					}
				}
				System.out.println("下载完成，文件存为" + savePath + "\n");
				fileOut.close();
				JOptionPane.showMessageDialog(null,
						"Download file successfully!");
			} else if (result == JFileChooser.CANCEL_OPTION) {
				JOptionPane.showMessageDialog(null, "Cancel");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Receive message error!");
			System.out.println("接收消息错误" + "\n");
			return;
		}
	}

	public void stopFileDownload() {
		try {
			downloadSocket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
