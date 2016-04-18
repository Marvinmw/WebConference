package fileTransport.transport;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;

import network.ConnectConfig;

public class FileClient {
	private String downloadFileName;
	private FileUpload fu = null;
	private String ip = ConnectConfig.serverIP;
	private int uploadPort = ConnectConfig.fileUploadPort;
	private int updatePort = ConnectConfig.fileUpdatePort;
	private int downloadPort = ConnectConfig.fileDownloadPort;
	private FileDownload downloadClient;
	private String meetingFileName;

	public FileClient(JList jlist_file_upload, String meetingFileName) {
		this.meetingFileName = meetingFileName;
		try {
			createConnection(jlist_file_upload);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public FileUpload getFileUpload() {
		return fu;
	}

	private boolean createConnection(JList jlist_file_upload) {
		fu = new FileUpload(ip, uploadPort, updatePort, downloadPort,
				jlist_file_upload, meetingFileName);
		try {
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			fu.CreateConnection();
			System.out.print("连接服务器成功!" + "\n");
			return true;
		} catch (Exception e) {
			System.out.print("连接服务器失败!" + "\n");
			return false;
		}
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	public String getDownloadFileName() {
		return downloadFileName;
	}

	public void uploadFile() {
		if (fu == null)
			return;
		try {

			fu.upload(meetingFileName);
		} catch (Exception e) {
			System.out.print("上传文件失败!" + "\n");
		}
	}

	public void downloadFile() {
		downloadClient = new FileDownload(ip, downloadPort, downloadFileName,
				meetingFileName);
		downloadClient.start();
	}

	public void stop() {
	}
}
