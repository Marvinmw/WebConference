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
package whiteBoard.draw;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * This class is a neutral UmlShape.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public final class NullElement extends AbstractNode   
implements DiagramElement , Serializable{

  private static final long serialVersionUID = -2561664634429197055L;
  private static NullElement instance = new NullElement();

  /**
   * Returns the singleton instance.
   * @return the singleton instance
   */
  public static NullElement getInstance() { return instance; }

  /**
   * Private constructor.
   */
  private NullElement() { }

  /**
   * {@inheritDoc}
   */
  public void recalculateSize(DrawingContext drawingContext) { }

  /**
   * {@inheritDoc}
   */
  public void draw(DrawingContext drawingContext) { }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isVisible(Rectangle2D clipBounds) { return false; }

  /**
   * {@inheritDoc}
   */
  @Override
  public Selection getSelection(DiagramOperations operations) {
    return NullSelection.getInstance();
  }

  /**
   * {@inheritDoc}
   */
  public Label getLabelAt(double mx, double my) { return null; }
}
