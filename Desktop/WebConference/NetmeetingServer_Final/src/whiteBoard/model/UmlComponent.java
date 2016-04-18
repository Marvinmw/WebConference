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
package whiteBoard.model;

/**
 * This class represents an UML component.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public final class UmlComponent extends AbstractUmlModelElement {

  private static final long serialVersionUID = -3174177772377718441L;
  private static UmlComponent prototype = new UmlComponent();

  /**
   * Return the prototype instance.
   * @return the prototype instance
   */
  public static UmlComponent getPrototype() { return prototype; }

  /**
   * Constructor.
   */
  private UmlComponent() { }
}
