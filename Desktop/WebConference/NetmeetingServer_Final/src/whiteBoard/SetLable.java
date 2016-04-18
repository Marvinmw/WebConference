package whiteBoard;

import java.awt.AWTEvent;

public class SetLable extends ActionEventNet{
	public String text;
	public SetLable(AWTEvent awtevent, String text) {
	   this.awtevent=null;
	   this.actiontype=ActionEnum.SETLABLE;
	   this.text=text;
	}

}
