package gui_v1.mainWindows;
import gui_v1.action_processors.GUI_MainiWindowActionProcessor;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public interface GUI_MainWidowsSharedBehaviors {


    WindowListener mainW =  new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            int answr = JOptionPane.showOptionDialog(null, "Do you want to terminate run of Consultant", "Cation System termination",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null, JOptionPane.NO_OPTION);
            if(answr == JOptionPane.YES_OPTION){
                new GUI_MainiWindowActionProcessor();
//                System.exit(JFrame.EXIT_ON_CLOSE);
            }
        }
    };
    WindowListener w = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            int answr = JOptionPane.showOptionDialog(null, "Do you Really want to close this window and return to Manual Entry?", "This window will be close",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null, JOptionPane.NO_OPTION);
            if(answr == JOptionPane.YES_OPTION){
                GUI_ManualEntryWindow.getInstance().showManualEntryWindow();
            }
        }
    };

   WindowListener w2 = new WindowAdapter() {
       @Override
       public void windowClosing(WindowEvent e) {
           int answr = JOptionPane.showOptionDialog(null, "Do you Really want to close this window, and return to main?", "This window will be close.",
                   JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null, JOptionPane.NO_OPTION);
           if(answr == JOptionPane.YES_OPTION){
               GUI_ManualEntryWindow.getInstance().disposeManualEntryWindow();
               GUI_RecordsWindow.getInstance().showRecordsWindow();
           }
       }
   };
    WindowListener w3 = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            int answr = JOptionPane.showOptionDialog(null, "Do you Really want to close this window, and return to Transactions Window?", "This window will be close.",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null, JOptionPane.NO_OPTION);
            if(answr == JOptionPane.YES_OPTION){
                GUI_NewAccountWindow.getInstance().showNewAccntWindow();
            }
        }
    };
}


