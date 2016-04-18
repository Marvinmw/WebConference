package whiteBoard;

import java.io.Serializable;
import java.util.Vector;

public class WhiteBoardHistoryData implements Serializable{
  public	Vector<ActionEventNet> commondHavedoneList;
  public ActionEnum type=ActionEnum.BoardHistoryData;
	public WhiteBoardHistoryData(Vector<ActionEventNet> commondHavedoneList) {
		this.commondHavedoneList=commondHavedoneList;
	}

}
