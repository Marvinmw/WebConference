package fileTransport.transport;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

import javax.swing.AbstractListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;

import fileTransport.command.FileCommand;

public class FileUpload {
	private String ip;
	private int uploadPort;
	private int updatePort;
	private int downloadPort;
	private Socket uploadSocket = null;
	private Socket updateSocket = null;
	private Socket downloadSocket = null;
	DataOutputStream out = null;
	DataInputStream getMessageStream = null;
	private ObjectOutputStream updateOos;
	private ObjectInputStream updateOis;
	ArrayList<String> fileList = null;
	AbstractListModel alm_fileList;
	JList jlist_file_upload;
	private String meetingFileName;
	private boolean isRun = true;

	public FileUpload(String ip, int uploadPort, int updatePort,
			int downloadPort, JList jlist_file_upload, String meetingFileName) {
		this.meetingFileName = meetingFileName;
		this.ip = ip;
		this.uploadPort = uploadPort;
		this.updatePort = updatePort;
		this.downloadPort = downloadPort;
		this.jlist_file_upload = jlist_file_upload;
	}

	public void CreateConnection() throws Exception {
		try {
			uploadSocket = new Socket(ip, uploadPort);
			updateSocket = new Socket(ip, updatePort);

			updateOis = new ObjectInputStream(updateSocket.getInputStream());
			updateOos = new ObjectOutputStream(updateSocket.getOutputStream());
			if (updateSocket.isConnected())
				System.out
						.println("updateSocket.Connected()==========================================");
			UpdateConnect updateConnect = new UpdateConnect();
			updateConnect.start();

		} catch (Exception e) {
			e.printStackTrace();
			if ((uploadSocket != null) && (downloadSocket != null)) {
				uploadSocket.close();
			}
			downloadSocket.close();
			throw e;
		} finally {
		}
	}

	private class UpdateConnect extends Thread {

		public void run() {
			try {
				if (uploadSocket.isClosed())
					uploadSocket = new Socket(ip, uploadPort);
				if (updateSocket.isClosed())
					updateSocket = new Socket(ip, updatePort);

				updateOos.writeUTF(meetingFileName);
				updateOos.writeObject(FileCommand.Update);
				while (isRun) {
					fileList = (ArrayList<String>) updateOis.readObject();
					System.out.println(fileList.size());
					final String[] fileName = new String[fileList.size()];
					for (int i = 0; i < fileList.size(); i++) {
						fileName[i] = fileList.get(i);
					}
					alm_fileList = new AbstractListModel() {
						String[] fileStrings = fileName;

						public int getSize() {
							return fileStrings.length;
						}

						public Object getElementAt(int i) {
							return fileStrings[i];
						}
					};
					jlist_file_upload.setModel(alm_fileList);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void update() {
		try {
			updateOos.writeObject(FileCommand.Update);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void upload(String meetingFileName) throws Exception {
		try {
			if (uploadSocket.isClosed())
				uploadSocket = new Socket(ip, uploadPort);
			if (updateSocket.isClosed())
				updateSocket = new Socket(ip, updatePort);

			int result = 0;
			JFileChooser chooser = new JFileChooser();
			result = chooser.showOpenDialog(chooser);
			if (result == JFileChooser.APPROVE_OPTION) {
				File fi = chooser.getSelectedFile();
				System.out.println("客户端传给服务器的文件长度:" + (int) fi.length());
				DataInputStream fis = new DataInputStream(
						new BufferedInputStream(new FileInputStream(
								fi.getPath())));
				if (uploadSocket.isClosed())
					uploadSocket.connect(new InetSocketAddress(ip, uploadPort));

				DataOutputStream ps = new DataOutputStream(
						uploadSocket.getOutputStream());
				ps.writeUTF("MeetingList" + File.separator + meetingFileName
						+ File.separator + fi.getName());
				ps.flush();
				ps.writeLong((long) fi.length());
				ps.flush();
				int bufferSize = 3000000;
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
				ps.close();
				System.out.println("文件传输完成");
				JOptionPane
						.showMessageDialog(null, "Upload file successfully!");
				fis.close();
			} else if (result == JFileChooser.CANCEL_OPTION) {
				JOptionPane.showMessageDialog(null, "Cancel");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	public DataInputStream getMessageStream() throws Exception {
		try {
			getMessageStream = new DataInputStream(new BufferedInputStream(
					downloadSocket.getInputStream()));
			return getMessageStream;
		} catch (Exception e) {
			e.printStackTrace();
			if (getMessageStream != null)
				getMessageStream.close();
			throw e;
		} finally {
		}
	}

	public void shutDownDownloadConnection() {
		try {
			downloadSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//
	// public void shutDownConnection() {
	// try {
	// if (out != null)
	// out.close();
	// if (getMessageStream != null)
	// getMessageStream.close();
	// if (uploadSocket != null)
	// uploadSocket.close();
	// } catch (Exception e) {
	// }
	// }

	public void stop() {
		isRun = false;
		try {
			uploadSocket.close();
			updateSocket.close();
			downloadSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
	}
}
