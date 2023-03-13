package gui_v1.mainWindows.newAccountWElements;

import gui_v1.action_processors.NewAccountProgrammableHandler;
import gui_v1.automation.GUI_ElementCreator;
import gui_v1.data_loaders.GUI_ElementsDataLoader;
import gui_v1.data_loaders.GUI_ElementsOptionLists;
import gui_v1.mainWindows.GUI_ManualEntryWindow;
import gui_v1.mainWindows.GUI_NewAccountWindow;
import gui_v1.mainWindows.GUI_NewBankWindow;
import gui_v1.settings.GUI_Settings_Variables;
import main_logic.PEC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

public class GUI_NewAccountP extends JPanel implements GUI_Settings_Variables, ActionListener, WindowStateListener {
    private final static String[] ERRORS_IN_USER_INPUT = null;
    private static int selectedItem;


    private static JComboBox<String>  jcmbBank;
    private JTextField jtfAcctNum;
    private JTextField jtfAcctNick;
    private JButton jbtnAdd;


    private void init(){
        jcmbBank = GUI_ElementCreator.newJComboBox(GUI_ElementsOptionLists.getInstance().getBanksList());
        jcmbBank.insertItemAt(GUI_ElementsDataLoader.getNAHelpMsgs().bankSelectionHelpMsg(),DEFAULT_SELECTED_ITEM);
        selectedItem = DEFAULT_SELECTED_ITEM;
        jtfAcctNum= GUI_ElementCreator.newTextFieldWithHelp(GUI_ElementsDataLoader.getNAHelpMsgs().accontInputHelpMsg());
        jtfAcctNick= GUI_ElementCreator.newTextFieldWithHelp(GUI_ElementsDataLoader.getNAHelpMsgs().nicknameInputHelpMsg());
        jbtnAdd = GUI_ElementCreator.newJButton("Add This Account");
    }

    public GUI_NewAccountP(){
        init();

        setLayout(new BorderLayout());
        add(GUI_ElementCreator.newTitle("Enter New Account Info"), BorderLayout.NORTH);
        JPanel pBox = new JPanel(new GridLayout(3,2));
        pBox.add(GUI_ElementCreator.newTextLabel("Account #:"));
        pBox.add(jtfAcctNum);
        pBox.add(GUI_ElementCreator.newTextLabel("Account Nick:"));
        pBox.add(jtfAcctNick);
        pBox.add(GUI_ElementCreator.newTextLabel("Bank:"));
        jcmbBank.addActionListener(this);
        jcmbBank.setSelectedIndex(DEFAULT_SELECTED_ITEM);
        pBox.add(jcmbBank);
        add(pBox, BorderLayout.CENTER);
        jbtnAdd.addActionListener(this);
        add(jbtnAdd, BorderLayout.SOUTH);
        pBox.requestFocusInWindow();
        jbtnAdd.setFocusTraversalPolicyProvider(true);
//        pBox.setRequestFocusEnabled(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== jbtnAdd){
            processAddBtnClick();
        }else  if(e.getSource() == jcmbBank){
            processBankSelection();
        }
    }
    private void processBankSelection(){
        selectedItem = jcmbBank.getSelectedIndex();

        // if(jcmbBank.getSelectedItem().toString().trim().compareToIgnoreCase(bankList[bankList.length-1])==0){
        if((jcmbBank.getSelectedItem()+"").trim().compareToIgnoreCase(PEC.NEW_BANK)==0){
            JOptionPane.showOptionDialog(null, "New Bank Window will be created soon", "New Bank",
                    JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, JOptionPane.YES_OPTION);
            GUI_NewBankWindow.getInstance().showNewBankWindow();
            GUI_NewAccountWindow.getInstance().hideNewAccntWindow();
        }
    }
    private void processAddBtnClick(){

        String msg = "Do you really want to save this account:";
        msg+="\n";
        msg+="Account #: "+ jtfAcctNum.getText().trim();
        msg+="\n";
        msg+="Account Nick:"+ jtfAcctNick.getText().trim();
        msg+="\n";
        msg+="Bank of Account:"+ (jcmbBank.getSelectedItem()+"").trim();
        msg+="\n";
        msg+="After Clicking Yes button this account will be added to your accounts";
        int answr = JOptionPane.showOptionDialog(null, msg, "Adding and Storing Account!",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, JOptionPane.NO_OPTION);
        if(answr == JOptionPane.YES_OPTION){

            new NewAccountProgrammableHandler(jtfAcctNum.getText().trim(), jtfAcctNick.getText().trim(),
                    (jcmbBank.getSelectedItem()+"").trim());

            GUI_NewAccountWindow.getInstance().hideNewAccntWindow();
            GUI_ManualEntryWindow.getInstance().showManualEntryWindow();
        } else {
            //     GUI_ManualTransactionsEntryP.setAcctSelection(GUI_ManualTransactionsEntryP.getPreviousAcctSelection());
        }
    }
    public static void addAccountNickToComboBox(String acctNick) {
        jcmbBank.insertItemAt(acctNick, jcmbBank.getItemCount() - 1);
        jcmbBank.setSelectedItem(acctNick);
    }
    @Override
    public Component getComponent() {
        return null;
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        System.out.println("JJJJ");
        jcmbBank.setSelectedIndex(selectedItem);
    }
}
