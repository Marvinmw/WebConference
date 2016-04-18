package whiteBoard;

import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Vector;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import network.ConnectConfig;

import whiteBoard.draw.DiagramElement;
import whiteBoard.ui.Board;
import whiteBoard.ui.StaticStructureEditorToolbarManager;
import whiteBoard.ui.diagram.DiagramEditor;

public class Client implements Runnable {
	private static Socket client;
	private static String ip = ConnectConfig.serverIP;
	private static int port = ConnectConfig.whiteBoardPort;

	private static ObjectOutputStream output;
	private static ObjectInputStream input;
	private DiagramEditor digrameditor;
	private StaticStructureEditorToolbarManager staticToolbarManager;
	private Board bo;

	public Client(DiagramEditor edior,
			StaticStructureEditorToolbarManager staticToolbarManager,
			Board board) {

		try {
			digrameditor = edior;
			this.staticToolbarManager = staticToolbarManager;
			bo = board;
			client = new Socket(ip, port);
			output = new ObjectOutputStream(client.getOutputStream());
			input = new ObjectInputStream(client.getInputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void writeObject(Object ob) {
		try {
			System.out.println("Command write" + ob);
			output.writeObject(ob);
		} catch (IOException e) {
			restartClient();
			e.printStackTrace();
		}
	}

	private static void restartClient() {
		try {
			client.close();

			client = new Socket(ip, port);
			output = new ObjectOutputStream(client.getOutputStream());
			input = new ObjectInputStream(client.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		while (true) {
			try {
				Object ob = input.readObject();

				if (ob != null) {
					if (ob instanceof WhiteBoardHistoryData)
						readHistory(((WhiteBoardHistoryData) ob).commondHavedoneList);
					else {
						// System.out.println("Command read "+ob);
						dispach(ob);
					}
				}
			} catch (IOException e) {
				restartClient();
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				restartClient();
				e.printStackTrace();
			}
		}
	}

	private void readHistory(Vector<ActionEventNet> commondHavedoneList) {
		if (!commondHavedoneList.isEmpty())
			for (int i = 0; i < commondHavedoneList.size(); i++)
				dispach(commondHavedoneList.get(i));

	}

	private void dispach(Object ob) {
		// System.out.println("Command dispach "+ob);
		ActionEventNet e = (ActionEventNet) ob;
		ActionEnum actiontype = e.actiontype;

		switch (actiontype) {
		case DMOUSEPRESS:
			this.digrameditor.mousePressedNet((MouseEvent) e.awtevent);
			break;
		case DMOUSECLICKED:
			this.digrameditor.mouseClickedNet((MouseEvent) e.awtevent);
			break;
		case DMOUSERELEASED:
			this.digrameditor.mouseReleasedNet((MouseEvent) e.awtevent);
			break;
		case DMOUSEENTER:
			this.digrameditor.mouseEnteredNet((MouseEvent) e.awtevent);
			break;
		case DMOUSEEXIT:
			this.digrameditor.mouseExitedNet((MouseEvent) e.awtevent);
			break;
		case DMOUSEMOVED:
			this.digrameditor.mouseMovedNet((MouseEvent) e.awtevent);
			break;
		case DMOUSEDRAGGED:
			this.digrameditor.mouseDraggedNet((MouseEvent) e.awtevent);
			break;
		case SACTIONEVENT:
			staticToolbarManager.actionPerformedNet((ActionEvent) e.awtevent);
			break;
		case DACTIONEVENT:
			this.digrameditor.actionPerformedNet((ActionEvent) e.awtevent);
		case CACTIONEVENT:
			this.digrameditor.selectionHandler.menubuilder
					.actionPerformedNet((ActionEvent) e.awtevent);
		case MainToolbar:
			this.bo.toolbarmanager.actionPerformedNet((ActionEvent) e.awtevent);
			break;
		case SETLABLE:
			this.digrameditor.setElementLable(((SetLable) e).text);
		default:
			break;
		}
	}

}
