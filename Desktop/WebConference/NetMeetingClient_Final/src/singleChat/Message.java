/*
 * ���Ƿ��������ظ��ͻ��˵���Ϣ��
 * */
package singleChat;

public class Message implements java.io.Serializable{
	private String messType;		//type=1˵���ǽ�������ȷ����Ϣ��type=2˵��������ʧ����Ϣ��type=3˵������ͨ��Ϣ
	private String senderName;		//���ͷ���name
	private String getterName;		//���շ���name
	private String con;				//����
	private String time;			//���͵�ʱ��
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
