/**
 * Copyright 2007 Wei-ju Wu
 *
 * This file is part of TinyUML.
 *
 * TinyUML is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * TinyUML is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TinyUML; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package whiteBoard.ui.diagram;

import java.awt.geom.Point2D;


import java.io.Serializable;

import whiteBoard.Client;
import whiteBoard.draw.DiagramElement;
import whiteBoard.draw.DrawingContext;
import whiteBoard.draw.LineConnectMethod;
import whiteBoard.model.RelationEndType;
import whiteBoard.model.RelationType;
import whiteBoard.ui.diagram.commands.AddConnectionCommand;
import whiteBoard.umldraw.shared.UmlConnection;
import whiteBoard.umldraw.shared.UmlNode;






/**
 * This class is a handler for line shaped elements.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public class LineHandler implements EditorMode , Serializable{

  private DiagramEditor editor;
  private Point2D anchor = new Point2D.Double();
  private Point2D tmpPos = new Point2D.Double();
  private UmlNode source;
  private boolean isDragging;
  private RelationType relationType;
  private LineConnectMethod connectMethod;
  
  /**
   * Constructor.
   * @param anEditor a DiagramEditor
   */
  public LineHandler(DiagramEditor anEditor) {
    editor = anEditor;
  }

  /**
   * Sets the association type.
   * @param anAssociationType the association type
   * @param aConnectMethod the connect method
   */
  public void setRelationType(RelationType anAssociationType,
    LineConnectMethod aConnectMethod) {
    connectMethod = aConnectMethod;
    relationType = anAssociationType;
  }

  /**
   * Returns the isDragging property for testing.
   * @return the status for the isDragging property
   */
  public boolean isDragging() { return isDragging; }

  /**
   * {@inheritDoc}
   */
  public void stateChanged() { }

  /**
   * {@inheritDoc}
   */
  public void cancel() { isDragging = false; }

  /**
   * Determines whether the specified element is a valid s ource for the
   * connection.
   * @param elem the target element
   * @return true if valid source, false otherwise
   */
  private boolean isValidSource(DiagramElement elem) {
    return elem instanceof UmlNode &&
        ((UmlNode) elem).acceptsConnection(relationType,
           RelationEndType.SOURCE, null);
  }

  /**
   * Determines whether the specified element is a valid target for the
   * connection.
   * @param elem the target element
   * @return true if valid, false otherwise
   */
  private boolean isValidTarget(DiagramElement elem) {
    return elem instanceof UmlNode && elem != source &&
      ((UmlNode) elem).acceptsConnection(relationType,
          RelationEndType.TARGET, source);
  }

  /**
   * {@inheritDoc}
   */
  public void mousePressed(EditorMouseEvent event) {
    double mx = event.getX(), my = event.getY();
    DiagramElement elem = editor.getDiagram().getChildAt(mx, my);
    if (isValidSource(elem)) {
      anchor.setLocation(mx, my);
      isDragging = true;
      source = (UmlNode) elem;
    }
  }

  /**
   * {@inheritDoc}
   */
  public void mouseReleased(EditorMouseEvent event) {
    double mx = event.getX(), my = event.getY();
    DiagramElement elem = editor.getDiagram().getChildAt(mx, my);
    if (source != null && isValidTarget(elem)) {
      UmlConnection conn =
        editor.getDiagram().getElementFactory().createConnection(relationType,
          (UmlNode) source, (UmlNode) elem);
      connectMethod.generateAndSetPointsToConnection(conn, source,
        (UmlNode) elem, anchor, tmpPos);
      AddConnectionCommand command = new AddConnectionCommand(editor,
        editor.getDiagram(), conn);
      
 
     
      editor.execute(command);
    }
    isDragging = false;
    editor.redraw();
  }

  public void mouseReleasedNet(DiagramElement elem ,UmlNode source) {
	      if (source != null && isValidTarget(elem)) {
	      UmlConnection conn =
	        editor.getDiagram().getElementFactory().createConnection(relationType,
	          (UmlNode) source, (UmlNode) elem);
	      connectMethod.generateAndSetPointsToConnection(conn, source,
	        (UmlNode) elem, anchor, tmpPos);
	      AddConnectionCommand command = new AddConnectionCommand(editor,
	        editor.getDiagram(), conn);
	    
	  editor.execute(command);
	    }
	    isDragging = false;
	    editor.redraw();
	  }


/**
   * {@inheritDoc}
   */
  public void mouseClicked(EditorMouseEvent event) { }


  /**
   * {@inheritDoc}
   */
  public void mouseDragged(EditorMouseEvent event) {
    double mx = event.getX(), my = event.getY();
    tmpPos.setLocation(mx, my);
    editor.redraw();
  }

  /**
   * {@inheritDoc}
   */
  public void mouseMoved(EditorMouseEvent event) { }

  /**
   * {@inheritDoc}
   */
  public void draw(DrawingContext drawingContext) {
    if (isDragging) {
      connectMethod.drawLineSegments(drawingContext, anchor, tmpPos);
    }
  }
  
  
  
}
