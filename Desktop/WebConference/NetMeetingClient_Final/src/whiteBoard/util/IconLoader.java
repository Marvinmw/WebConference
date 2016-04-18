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
package whiteBoard.util;

import java.awt.Toolkit;
import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * This class accesses images for icons from the class path.
 * 
 * @author Wei-ju Wu
 * @version 1.0
 */
public final class IconLoader implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static Toolkit tk = Toolkit.getDefaultToolkit();

	private Map<IconType, String> urlMap = new HashMap<IconType, String>();
	private Map<IconType, Icon> iconMap = new HashMap<IconType, Icon>();
	private Map<String, IconType> iconTypeMap = new HashMap<String, IconType>();

	private static IconLoader instance = new IconLoader();

	/**
	 * Private constructor.
	 */
	private IconLoader() {
		for (IconType iconType : IconType.values()) {
			iconTypeMap.put(iconType.toString(), iconType);
		}
		urlMap.put(IconType.MOUSE_POINTER, "/whiteBoard/ui/mousepointer.png");
		urlMap.put(IconType.CLASS, "/whiteBoard/ui/class.png");
		urlMap.put(IconType.PACKAGE, "/whiteBoard/ui/package.png");
		urlMap.put(IconType.COMPONENT, "/whiteBoard/ui/component.png");

		urlMap.put(IconType.ACTOR, "/whiteBoard/ui/actor.png");

		urlMap.put(IconType.SYSTEMBUOND, "/whiteBoard/ui/systembound.png");
		urlMap.put(IconType.RECT, "/whiteBoard/ui/rect.png");
		urlMap.put(IconType.OVAL, "/whiteBoard/ui/oval.png");

		urlMap.put(IconType.DEPENDENCY, "/whiteBoard/ui/dependency.png");
		urlMap.put(IconType.ASSOCIATION, "/whiteBoard/ui/association.png");
		urlMap.put(IconType.NOTE, "/whiteBoard/ui/note.png");
		urlMap
				.put(IconType.NOTE_CONNECTOR,
						"/whiteBoard/ui/note-connector.png");
		urlMap.put(IconType.AGGREGATION, "/whiteBoard/ui/aggregation.png");
		urlMap.put(IconType.COMPOSITION, "/whiteBoard/ui/composition.png");
		urlMap.put(IconType.INHERITANCE, "/whiteBoard/ui/inheritance.png");
		urlMap.put(IconType.INTERFACE_REALIZATION,
				"/whiteBoard/ui/interface-realization.png");
		urlMap.put(IconType.REDO, "/whiteBoard/ui/redo.gif");
		urlMap.put(IconType.UNDO, "/whiteBoard/ui/undo.gif");
		urlMap.put(IconType.OPEN, "/whiteBoard/ui/open.gif");
		urlMap.put(IconType.SAVE, "/whiteBoard/ui/save.gif");

		// urlMap.put(IconType.NEW, "/whiteBoard/Office2003/new.gif");
		// urlMap.put(IconType.CUT, "/whiteBoard/Office2003/cut.gif");
		// urlMap.put(IconType.COPY, "/whiteBoard/Office2003/copy.gif");
		// urlMap.put(IconType.PASTE, "/whiteBoard/Office2003/paste.gif");
		// urlMap.put(IconType.DELETE, "/whiteBoard/Office2003/delete.gif");
		// urlMap.put(IconType.ABOUT, "/whiteBoard/Office2003/about.gif");
	}

	/**
	 * Returns the singleton instance.
	 * 
	 * @return the singleton instance
	 */
	public static IconLoader getInstance() {
		return instance;
	}

	/**
	 * Returns the icon for the specified icon type.
	 * 
	 * @param type
	 *            the icon type
	 * @return the icon
	 */
	public Icon getIcon(IconType type) {
		if (!iconMap.containsKey(type)) {
			String urlstr = urlMap.get(type);
			if (urlstr != null) {
				iconMap.put(type, new ImageIcon(IconLoader.tk
						.getImage(IconLoader.class.getResource(urlstr))));
			}
		}
		return iconMap.get(type);
	}

	/**
	 * Returns the icon for the specified icon type name.
	 * 
	 * @param typeName
	 *            the icon type name
	 * @return the icon
	 */
	public Icon getIcon(String typeName) {
		return getIcon(iconTypeMap.get(typeName));
	}
}
