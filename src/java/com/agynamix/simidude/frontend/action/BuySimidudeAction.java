/*
 * Class ExitAction
 * created on 01.06.2004
 *
 */
package com.agynamix.simidude.frontend.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.program.Program;



/**
 * @version $Revision$ $Date: 2004-11-17 13:30:10 +0100 (Mi, 17 Nov 2004) $
 * @author tuhlmann
 * @since V0404
 */
public class BuySimidudeAction extends Action {
  
  final ApplicationWindow window;
  
  public BuySimidudeAction(ApplicationWindow w) {
    this.window = w;
    setText("&Buy Simidude");
    setToolTipText("Opens the Simidude order page. Go ahead and buy...");    
//    setImageDescriptor(PlatformIcons.getDescriptor(PlatformIcons.HELP));  Ø    
  }
  
  
  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  public void run() {
    Program.launch("https://sites.fastspring.com/agynamix/instant/simidude");
  }

}


// $Log$