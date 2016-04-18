/*
 * 这是服务器返回给客户端的信息，
 * */
package singleChat;

public class Message implements java.io.Serializable{
	private String messType;		//type=1说明是建立连接确认信息，type=2说明是连接失败信息，type=3说明是普通信息
	private String senderName;		//发送方的name
	private String getterName;		//接收方的name
	private String con;				//内容
	private String time;			//发送的时间
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getGetterName() {
		return getterName;
	}

	public void setGetterName(String getterName) {
		this.getterName = getterName;
	}

	public String getCon() {
		return con;
	}

	public void setCon(String con) {
		this.con = con;
	}

	
	public String getMessType() {
		return messType;
	}

	public void setMesstype(String messType) {
		this.messType = messType;
	}
	
}
