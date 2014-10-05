/*
 * Copyright by AGYNAMIX(R). All rights reserved. 
 * This file is made available under the terms of the
 * license this product is released under.
 * 
 * For details please see the license file you should have
 * received, or go to:
 * 
 * http://www.agynamix.com
 * 
 * Contributors: agynamix.com (http://www.agynamix.com)
 */
package swt.snippets;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class CustomGradientSelection {

  public static void main(String [] args) {
    final Display display = new Display();
    Shell shell = new Shell(display);
    shell.setText("Custom gradient selection for Table");
    shell.setLayout(new FillLayout());
    final Table table = new Table(shell, SWT.MULTI | SWT.FULL_SELECTION);
    table.setHeaderVisible(true);
    table.setLinesVisible(true);
    int columnCount = 3;
    for (int i=0; i<columnCount; i++) {
      TableColumn column = new TableColumn(table, SWT.NONE);
      column.setText("Column " + i);  
    }
    int itemCount = 8;
    for(int i = 0; i < itemCount; i++) {
      TableItem item = new TableItem(table, SWT.NONE);
      item.setText(new String[] {"item "+i+" a", "item "+i+" b", "item "+i+" c"});
    }   
    /*
     * NOTE: MeasureItem, PaintItem and EraseItem are called repeatedly.
     * Therefore, it is critical for performance that these methods be
     * as efficient as possible.
     */
    table.addListener(SWT.EraseItem, new Listener() {
      public void handleEvent(Event event) {
        event.detail &= ~SWT.HOT; 
        if((event.detail & SWT.SELECTED) != 0) {
          GC gc = event.gc;
          Rectangle area = table.getClientArea();
          /*
           * If you wish to paint the selection beyond the end of
           * last column, you must change the clipping region.
           */
          int columnCount = table.getColumnCount();
          if (event.index == columnCount - 1 || columnCount == 0) {
            int width = area.x + area.width - event.x;
            if (width > 0) {
              Region region = new Region();
              gc.getClipping(region);
              region.add(event.x, event.y, width, event.height); 
              gc.setClipping(region);
              region.dispose();
            }
          }
          gc.setAdvanced(true);
          if (gc.getAdvanced()) gc.setAlpha(127);               
          Rectangle rect = event.getBounds();
          Color foreground = gc.getForeground();
          Color background = gc.getBackground();
          gc.setForeground(display.getSystemColor(SWT.COLOR_RED));
          gc.setBackground(display.getSystemColor(SWT.COLOR_LIST_BACKGROUND));
          gc.fillGradientRectangle(0, rect.y, 500, rect.height, false);
          // restore colors for subsequent drawing
          gc.setForeground(foreground);
          gc.setBackground(background);
          event.detail &= ~SWT.SELECTED;          
        }           
      }
    });   
    for (int i=0; i<columnCount; i++) {
      table.getColumn(i).pack();
    } 
    table.setSelection(table.getItem(0));
    shell.setSize(500, 200);
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) display.sleep();
    }
    display.dispose();  
  }
  }

