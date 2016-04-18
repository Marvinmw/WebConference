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
	
	//修改自己的密码
	public void amendPassword(String p){
		this.setPassword(p);
	}
	// 输出时调用，生成文本
	public String toString() {
		return name + "/" + ID + "/" + password + "/" + position;
	}

}
