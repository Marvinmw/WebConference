package fileTransport.fileTransport;

public class FileTransportServer {
	
	public void startServer(){
		this.startUpload();
		this.startUpdate();
		this.startDownload();
	}
	
	public void startUpload(){
		UploadFileServer upload = new UploadFileServer();
		Thread uploadServer = new Thread(upload);
		uploadServer.start();
	}
	
	public void startUpdate(){
		UpdateServer update = new UpdateServer();
		Thread updateServer = new Thread(update);
		updateServer.start();
	}
	
	public void startDownload(){
		DownloadFileServer download = new DownloadFileServer();
		Thread downloadServer = new Thread(download);
		downloadServer.start();
	}
}
