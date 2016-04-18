package data;

import java.io.Serializable;


public class User extends Staff implements Serializable{
	private static final long serialVersionUID = 3227073443464965601L;
	private User user;
	
	public User(){
		this("None","-1","None","None");
	}
	
	public User(String n, String id, String p, String t){
		name = n;
		ID = id;
		password = p;
		position = t;
	}
	
	//�޸��Լ�������
	public void amendPassword(String p){
		this.setPassword(p);
	}
	// ���ʱ���ã������ı�
	public String toString() {
		return name + "/" + ID + "/" + password + "/" + position;
	}

}
