package data;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class Staff implements Serializable {

	private static final long serialVersionUID = 6519245762549193759L;

	public static int amount;

	String name = "";
	String ID = "";
	String password = "";
	String position = "";

	public Staff() {
		this("None", "-1", "None", "None");
	}

	public Staff(String id, String p) {

		ID = id;
		password = p;

	}

	public Staff(String n, String id, String p, String t) {
		name = n;
		ID = id;
		password = p;
		position = t;
	}

	public String getName() {
		return name;
	}

	public String getID() {
		return ID;
	}

	public String getPassword() {
		return password;
	}

	public String getPosition() {
		return position;
	}

	public void setName(String n) {
		name = n;
	}

	public void setID(String id) {
		ID = id;
	}

	public void setPassword(String p) {
		password = p;
	}

	public void setPosition(String t) {
		password = t;
	}

	// 输出时调用，生成文本
	public String toString() {
		return name + "/" + ID + "/" + password + "/" + position;
	}

	public boolean checkPassWord(String password) {
		if (this.getPassword().equals(password))
			return true;
		else
			return false;
	}

}
