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
package whiteBoard.ui.commands;

import java.io.File;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


//import org.apache.batik.svggen.SVGGeneratorContext;
//import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import whiteBoard.ui.diagram.DiagramEditor;

/**
 * This class exports a diagram to SVG format.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public class SvgExporter extends FileWriter implements Serializable{

  /**
   * Writes the specified diagram editor as SVG.
   * @param editor a DiagramEditor
   * @param file the file to write to
   * @throws IOException if error occurred
   */
  public void writeSVG(DiagramEditor editor, File file) throws IOException {
    FileOutputStream out = null;
    OutputStreamWriter writer = null;
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      DOMImplementation domImpl = builder.getDOMImplementation();
      Document doc =
        domImpl.createDocument("http://www.w3.org/2000/svg", "svg", null);
      SVGGeneratorContext ctx = SVGGeneratorContext.createDefault(doc);
     // ctx.setComment("Generated by TinyUML with Batik SVG Generator");
     // ctx.setEmbeddedFontsOn(true);
     // SVGGraphics2D g2d = new SVGGraphics2D(ctx, true);
     // g2d.setSVGCanvasSize(editor.getTotalCanvasSize());
     // editor.paintComponentNonScreen(g2d);

      // write the file
      File theFile = getFileWithExtension(file);
      if (canWrite(editor, theFile)) {
        out = new FileOutputStream(theFile);
        writer = new OutputStreamWriter(out, "UTF-8");
      //  g2d.stream(writer);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally  {
      if (writer != null) { writer.close(); }
      if (out != null) { out.close(); }
    }
  }

  /**
   * {@inheritDoc}
   */
  protected String getSuffix() { return ".svg"; }
}
