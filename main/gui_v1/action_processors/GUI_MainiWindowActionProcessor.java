package gui_v1.action_processors;

import javax.swing.*;

public class GUI_MainiWindowActionProcessor {

    public GUI_MainiWindowActionProcessor(){
        startAppTerminationProcess();
    }
    public void startAppTerminationProcess(){
        JOptionPane.showMessageDialog(null,"TThis is Action Handler for Termination of APP","This is App Termination Process Handler",JOptionPane.INFORMATION_MESSAGE);
        System.exit(JFrame.EXIT_ON_CLOSE);
    }
}
