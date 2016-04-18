package whiteBoard;

import java.awt.AWTEvent;
import java.io.Serializable;

public class ActionEventNet implements Serializable{
public	AWTEvent  awtevent;
public  ActionEnum actiontype;
public  ActionEventNet(){
	this.awtevent=null;
	this.actiontype=null;
}
public  ActionEventNet(AWTEvent  awtevent, ActionEnum actiontype){
	this.awtevent=awtevent;
	this.actiontype=actiontype;
} 
}
