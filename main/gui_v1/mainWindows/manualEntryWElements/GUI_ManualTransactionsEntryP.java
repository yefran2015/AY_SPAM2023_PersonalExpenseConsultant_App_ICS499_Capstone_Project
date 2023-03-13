package gui_v1.mainWindows.manualEntryWElements;

import gui_v1.action_processors.ManualEntryProgrammableHandler;
import gui_v1.automation.GUI_ElementCreator;
import gui_v1.data_loaders.GUI_ElementsDataLoader;
import gui_v1.data_loaders.GUI_ElementsOptionLists;
import gui_v1.help_utils.GUI_AY_Calendar;
import gui_v1.gui_logic.GUI_ManualEntryTemporaialHolder;
import gui_v1.mainWindows.GUI_ManualEntryWindow;
import gui_v1.mainWindows.GUI_RecordsWindow;
import gui_v1.mainWindows.GUI_NewAccountWindow;
import gui_v1.settings.GUI_Settings_Variables;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
//import java.util.Calendar;

public class GUI_ManualTransactionsEntryP extends JPanel implements GUI_Settings_Variables, ActionListener, FocusListener, WindowStateListener {
    private final static String[] ERRORS_IN_USER_INPUT = null;
//    private final static int DEFAULT_SELECTED_ITEM = 0;
//    private static int selectedItem;;
    private final static int INPUT_ELEMENTS_ON_VIEW_COUNTER = GUI_ElementsDataLoader.getMEntHelpMsgs().numOfInputElementsManualEntryHas();
    private int[] listsSelectedItems;
    private JTextField jtfDate;
    private JTextField jtfOutputDate;
    private JTextField jtfRefNum;
    private JTextField jtfTransName;
    private JTextField jtfMemo;
    private JTextField jtfAmount;

    private static JComboBox<String> jcmbAccount;
    private static JComboBox<String> jcmbCategory;
    //    private String previousAcctSelection = infoTxtOfInputs[0];

    private JButton jbtnCancel;
    private JButton jbtnAnother;
    private JButton jbtnDone;

    private JButton jbtnFirst;
    private JButton jbtnPrev;
    private JButton jbtnNext;
    private JButton jbtnLast;

    private JComboBox<String> jcmbYears;
    private JComboBox<String> jcmbMonths;
    private JComboBox<String> jcmbDays;

    private void init(){

        jcmbAccount = GUI_ElementCreator.newJComboBoxWithHidenHelp(GUI_ElementsOptionLists.getInstance().getAccountNicksList());
        jcmbAccount.insertItemAt(GUI_ElementsDataLoader.getMEntHelpMsgs().acctNicksSelectionHelpMsg(), DEFAULT_SELECTED_ITEM);
        jcmbCategory = GUI_ElementCreator.newJComboBox(GUI_ElementsOptionLists.getInstance().getTransCategoryist());
        jcmbCategory.insertItemAt(GUI_ElementsDataLoader.getMEntHelpMsgs().categoryOfAccntSelectionHelpMsg(), DEFAULT_SELECTED_ITEM);
        listsSelectedItems = new int[2];
        listsSelectedItems[0]=DEFAULT_SELECTED_ITEM;
        listsSelectedItems[1]=DEFAULT_SELECTED_ITEM;

        jtfDate = GUI_ElementCreator.newTextField(GUI_ElementsDataLoader.getMEntHelpMsgs().dateInputHelpMsg());
        jtfOutputDate = GUI_ElementCreator.newTextField(GUI_ElementsDataLoader.getMEntHelpMsgs().dateOutputHelpMsg());
        jtfRefNum = GUI_ElementCreator.newTextFieldWithHelp(GUI_ElementsDataLoader.getMEntHelpMsgs().referenceInputHelpMsg());
        jtfTransName = GUI_ElementCreator.newTextFieldWithHelp(GUI_ElementsDataLoader.getMEntHelpMsgs().transNameInputHelpMsg());
        jtfMemo = GUI_ElementCreator.newTextFieldWithHelp(GUI_ElementsDataLoader.getMEntHelpMsgs().transMemoInputHelpMsg());
        jtfAmount = GUI_ElementCreator.newTextFieldWithHelp(GUI_ElementsDataLoader.getMEntHelpMsgs().transAmountInputHelpMsg());


        jbtnFirst = GUI_ElementCreator.newJButton("<--First");
        jbtnFirst.setActionCommand("Nav");
        jbtnPrev = GUI_ElementCreator.newJButton("<-Prev");
        jbtnPrev.setActionCommand("Nav");
        jbtnNext = GUI_ElementCreator.newJButton("Next->");
        jbtnNext.setActionCommand("Nav");
        jbtnLast = GUI_ElementCreator.newJButton("Last-->");
        jbtnLast.setActionCommand("Nav");

        jbtnCancel = GUI_ElementCreator.newJButton("Cancel");
        jbtnAnother = GUI_ElementCreator.newJButton("Another");
        jbtnDone = GUI_ElementCreator.newJButton("Done");
    }


    public GUI_ManualTransactionsEntryP() {
        init();
        setLayout(new BorderLayout());
        String headingTitle = "Transaction Manual Entry";
        add(GUI_ElementCreator.newSubHead(headingTitle), BorderLayout.NORTH);

        JPanel mainBoxP = new JPanel(new BorderLayout());
        String manualEntryTitleMessage = "Enter Transaction Information";
        mainBoxP.add(GUI_ElementCreator.newTitle(manualEntryTitleMessage), BorderLayout.NORTH);

        JPanel userInputElementsBox = new JPanel(new GridLayout(7, 2));
        userInputElementsBox.add(GUI_ElementCreator.newTextLabel("Account:"));
        jcmbAccount.addActionListener(this);
        jcmbAccount.setSelectedIndex(DEFAULT_SELECTED_ITEM);

        userInputElementsBox.add(jcmbAccount);

        JPanel dateBoxP = new JPanel(new BorderLayout());
        dateBoxP.add(GUI_ElementCreator.newTextLabel("Date:"), BorderLayout.WEST);

        jtfOutputDate.setEditable(false);
        jtfOutputDate.setBackground(null);
        JPanel datOutBoxP = new JPanel();
        jtfOutputDate.setPreferredSize(new Dimension(120, txtSize_JTextField + 4));
        datOutBoxP.add(GUI_ElementCreator.newOutputTextFieldLabel("Selected Date:"));
        datOutBoxP.add(jtfOutputDate);
        datOutBoxP.add(GUI_ElementCreator.newFieldNameLabel(" "));
        dateBoxP.add(datOutBoxP, BorderLayout.EAST);
        userInputElementsBox.add(dateBoxP);
        userInputElementsBox.add(new DateSelectionsP());


        userInputElementsBox.add(GUI_ElementCreator.newTextLabel("Reference #"));
        userInputElementsBox.add(jtfRefNum);
        jtfRefNum.addFocusListener(this);
        userInputElementsBox.add(GUI_ElementCreator.newTextLabel("Transaction Name:"));
        userInputElementsBox.add(jtfTransName);
        jtfTransName.addFocusListener(this);
        userInputElementsBox.add(GUI_ElementCreator.newTextLabel("Memo:"));
        userInputElementsBox.add(jtfMemo);
        jtfMemo.addFocusListener(this);
        userInputElementsBox.add(GUI_ElementCreator.newTextLabel("Amount:"));
        userInputElementsBox.add(jtfAmount);
        jtfAmount.addFocusListener(this);

        userInputElementsBox.add(GUI_ElementCreator.newTextLabel("Category:"));
//        jcmbCategory.removeItem(manyalEntryDefaultJTextFieldText[manyalEntryDefaultJTextFieldText.length - 1]);
//        jcmbCategory.insertItemAt(manyalEntryDefaultJTextFieldText[manyalEntryDefaultJTextFieldText.length - 1], 0);
//        jcmbCategory.setSelectedItem(PEC.instance().OTHER);

        jcmbCategory.setSelectedIndex(DEFAULT_SELECTED_ITEM);
        jcmbCategory.addActionListener(this);
        userInputElementsBox.add(jcmbCategory);
        mainBoxP.add(userInputElementsBox, BorderLayout.CENTER);
        add(mainBoxP, BorderLayout.CENTER);


        jbtnFirst.addActionListener(this);
        jbtnPrev.addActionListener(this);
        jbtnNext.addActionListener(this);
        jbtnLast.addActionListener(this);


        jbtnDone.addActionListener(this);
        jbtnCancel.addActionListener(this);
        jbtnAnother.addActionListener(this);

        JPanel buttonsBox = new JPanel(new GridLayout(2, 4));
        buttonsBox.add(jbtnFirst);
        buttonsBox.add(jbtnPrev);
        buttonsBox.add(jbtnNext);
        buttonsBox.add(jbtnLast);
        buttonsBox.add(jbtnCancel);
        buttonsBox.add(jbtnAnother);
        buttonsBox.add(jbtnDone);
        add(buttonsBox, BorderLayout.SOUTH);
    }


//    public static String getPreviousAcctSelection() {
//        return previousAcctSelection;
//    }

    public static void setAcctSelection(String selection) {
        jcmbAccount.setSelectedItem(selection);
    }

    private void setDateOutputTextFieldWithDateFromComboBoxes() {
        jtfOutputDate.setText(jcmbMonths.getSelectedItem() + "/" + jcmbDays.getSelectedItem() + "/" + jcmbYears.getSelectedItem());
    }


//    private void o(Object o) {
//        System.out.println(o + "");
//    }

    private void replaceItemsAtJCMBoWith(JComboBox<String> jcb, String[] items) {
        jcb.removeAllItems();
        for (String i : items) {
            jcb.addItem(i);
        }
    }

    public static void addAccountNickToComboBox(String acctNick) {
        jcmbAccount.insertItemAt(acctNick, jcmbAccount.getItemCount() - 1);
        jcmbAccount.setSelectedItem(acctNick);
    }

    public void setElementsDefaultHelpTexts() {
        if(jcmbAccount.getItemAt(DEFAULT_SELECTED_ITEM).compareToIgnoreCase(GUI_ElementsDataLoader.getMEntHelpMsgs().acctNicksSelectionHelpMsg())!=0){
            jcmbAccount.insertItemAt(GUI_ElementsDataLoader.getMEntHelpMsgs().acctNicksSelectionHelpMsg(), DEFAULT_SELECTED_ITEM);
        }
        if(jcmbCategory.getItemAt(DEFAULT_SELECTED_ITEM).compareToIgnoreCase(GUI_ElementsDataLoader.getMEntHelpMsgs().categoryOfAccntSelectionHelpMsg())!=0){
            jcmbCategory.insertItemAt(GUI_ElementsDataLoader.getMEntHelpMsgs().categoryOfAccntSelectionHelpMsg(), DEFAULT_SELECTED_ITEM);
        }
        jtfDate.setText(GUI_ElementsDataLoader.getMEntHelpMsgs().dateInputHelpMsg());
        jtfOutputDate.setText(GUI_ElementsDataLoader.getMEntHelpMsgs().dateOutputHelpMsg());
        jtfRefNum.setText(GUI_ElementsDataLoader.getMEntHelpMsgs().referenceInputHelpMsg());
        jtfTransName.setText(GUI_ElementsDataLoader.getMEntHelpMsgs().referenceInputHelpMsg());
        jtfMemo.setText(GUI_ElementsDataLoader.getMEntHelpMsgs().transMemoInputHelpMsg());
        jtfAmount.setText(GUI_ElementsDataLoader.getMEntHelpMsgs().transAmountInputHelpMsg());

    }

    public void setAllElementsCustomHelpTexts(String accountCombo_helpText, String date_helpText, String refnum_helpText, String transName_helpText,
                                           String memo_helpText, String amount_helpText, String categoryCombo_helpText) {
        setJTFCustom_Texts(date_helpText, refnum_helpText, transName_helpText, memo_helpText, amount_helpText);
        setJCMBs_Custom_UserHelpTexts(accountCombo_helpText, categoryCombo_helpText);
    }

    private void setJCMBs_Custom_UserHelpTexts(String accountComboHelpText, String categoryComboHelpText) {
        jcmbAccount.setSelectedItem(accountComboHelpText);
        jcmbCategory.setSelectedItem(categoryComboHelpText);
    }

    public void setJTFCustom_Texts(String date_helpText, String refnum_helpText, String TransName_helpText,
                                   String memo_helpText, String amount_helpText) {
        jtfDate.setText(date_helpText);
        jtfRefNum.setText(refnum_helpText);
        jtfTransName.setText(TransName_helpText);
        jtfMemo.setText(memo_helpText);
        jtfAmount.setText(amount_helpText);
    }

    public void setManualEntriesValues(String[] tmpManualEntries) {
        if(tmpManualEntries==null){
            return;
        }
        String acctNick = tmpManualEntries[0];
        String date = tmpManualEntries[1];
        String refNum = tmpManualEntries[2];
        String transName = tmpManualEntries[3];
        String memo = tmpManualEntries[4];
        String amount = tmpManualEntries[5];
        String category = tmpManualEntries[6];
        setAllElementsCustomHelpTexts(acctNick, date, refNum, transName, memo, amount, category);
        setManualEntriesCalendarValues(date.split("/"));
    }

    public void setManualEntriesCalendarValues(String[] calArrYMD) {
        jcmbYears.setSelectedItem(calArrYMD[2]);
        jcmbMonths.setSelectedItem(calArrYMD[0]);
        jcmbDays.setSelectedItem(calArrYMD[1]);
    }
    private String[] getAllInputElementsData() {
        String account = (jcmbAccount.getSelectedItem() + "").trim();
        String custom_category = (jcmbCategory.getSelectedItem() + "").trim();
        String[] dateArr = jtfOutputDate.getText().split("/");
        if (dateArr[0].length() == 1) {
            dateArr[0] = "0" + dateArr[0];
        }
        if (dateArr[1].length() == 1) {
            dateArr[1] = "0" + dateArr[1];
        }
        String date = dateArr[0] + "/" + dateArr[1] + "/" + dateArr[2];
        String refN = jtfRefNum.getText().trim();
        String descr = jtfTransName.getText().trim();
        String memo = jtfMemo.getText().trim();
        String amount = jtfAmount.getText().trim();

        return new String[]{account, date, refN, descr, memo, amount, custom_category};
    }
    private void clearrErrorArr(String[] errArr){
        clearStrArr( errArr);

    }
    private void clearErrorBorders() {
        jcmbAccount.setBorder(new LineBorder(null, 0));
        jcmbCategory.setBorder(new LineBorder(null, 0));
    }
    private boolean checkInputData(String[] inputData){
        if(inputData.length != INPUT_ELEMENTS_ON_VIEW_COUNTER){
            return false;
        }
        String account = inputData[0].trim();
        String custom_category = inputData[6].trim();
        if(account.compareToIgnoreCase(jcmbAccount.getItemAt(DEFAULT_SELECTED_ITEM))==0){
           return false;
        }
        if(custom_category.compareToIgnoreCase(jcmbCategory.getItemAt(DEFAULT_SELECTED_ITEM))==0){
           return false;
        }
        return true;
    }



    private void processCategorySelection() {
        listsSelectedItems[1] =  jcmbCategory.getSelectedIndex();
        listsSelectedItems[0] =  jcmbAccount.getSelectedIndex();
    }

    private void processAcountSelectoion() {
        listsSelectedItems[1] =  jcmbCategory.getSelectedIndex();
        listsSelectedItems[0] =  jcmbAccount.getSelectedIndex();
        if ((jcmbAccount.getSelectedItem() + "").trim().compareToIgnoreCase(jcmbAccount.getItemAt(jcmbAccount.getItemCount()-1)) == 0) {
            GUI_NewAccountWindow.getInstance().showNewAccntWindow();
            GUI_ManualEntryWindow.getInstance().hideManualEntryWindow();
        } else {
            //       previousAcctSelection = (String) jcmbAccount.getSelectedItem();
        }
    }
    private void errorInInput(){

    }
    private void testingOutput(ActionEvent e) {
        o(e);
        o(getAllInputElementsData());

    }
    private void processAnotherClick() {
        String[] userInputs =getAllInputElementsData();
         if(!checkInputData(userInputs)){
             errorInInput();
             return;
         }

        GUI_ManualEntryTemporaialHolder.getInstance().addTempUserManualEntry(userInputs[0], userInputs[1], userInputs[2],
                userInputs[3], userInputs[4], userInputs[5], userInputs[6]);
        setElementsDefaultHelpTexts();
    }
    private void processDoneBtnClick(){
        //          new ManualEntryProgrammableHandler(accoutn, date, refN,descr, memo, amount,custom_category);
//          processAnotherClick();

        GUI_ManualEntryTemporaialHolder.getInstance().addTempUserManualEntry(getAllInputElementsData());
        new ManualEntryProgrammableHandler(GUI_ManualEntryTemporaialHolder.getInstance().getManuallYEnterredAccounts());
        GUI_ManualEntryTemporaialHolder.getInstance().clearTemporalManualEntryList();
        GUI_ManualEntryWindow.getInstance().disposeManualEntryWindow();
        GUI_RecordsWindow.getInstance().showRecordsWindow();
    }
    private void processFirstBtnClick(){
        if(!(GUI_ManualEntryTemporaialHolder.getInstance().getNumOfItems()>0)){
            return;
        }
        String[] firstManualEntryArr = GUI_ManualEntryTemporaialHolder.getInstance().getFirst();
        setManualEntriesValues(firstManualEntryArr);
    }
    private void processPrevBtnClick(){
        if(!(GUI_ManualEntryTemporaialHolder.getInstance().getNumOfItems()>0)){
            return;
        }
        String[] previousManualEntryArr = GUI_ManualEntryTemporaialHolder.getInstance().getPrevNoCycles();
        setManualEntriesValues(previousManualEntryArr);
    }
    private void processNextBtnClick(){
        if(!(GUI_ManualEntryTemporaialHolder.getInstance().getNumOfItems()>0)){
            return;
        }
        String[] nextManualEntryArr = GUI_ManualEntryTemporaialHolder.getInstance().getNextNoCycles();
        setManualEntriesValues(nextManualEntryArr);
    }
    private void processLastBtnClick(){
        if(!(GUI_ManualEntryTemporaialHolder.getInstance().getNumOfItems()>0)){
            return;
        }
        String[] lastManualEntryArr = GUI_ManualEntryTemporaialHolder.getInstance().getLast();
        setManualEntriesValues(lastManualEntryArr);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        testingOutput(e);
        if (e.getSource() == jbtnDone) {
            processDoneBtnClick();
        }else if (e.getSource() == jbtnAnother) {
            processAnotherClick();


        }  else if (e.getSource() == jbtnFirst) {
            processFirstBtnClick();
        } else if (e.getSource() == jbtnPrev) {
            processPrevBtnClick();
        } else if (e.getSource() == jbtnNext) {
            processNextBtnClick();
        } else if (e.getSource() == jbtnLast) {
            processLastBtnClick();

        } else if (e.getSource() == jcmbAccount) {
            processAcountSelectoion();
        } else if (e.getSource() == jcmbCategory) {
            processCategorySelection();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        ((JTextField) e.getSource()).selectAll();
    }

    @Override
    public void focusLost(FocusEvent e) {
    }

    @Override
    public Component getComponent() {
        return null;
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        jcmbAccount.setSelectedIndex(listsSelectedItems[0]);
        jcmbAccount.setSelectedIndex(listsSelectedItems[1]);

    }

    private class DateSelectionsP extends JPanel {
        DateSelectionsP() {
            setLayout(new GridLayout(1, 6));

            jcmbYears = GUI_ElementCreator.newJComboBox(GUI_AY_Calendar.getYearsDescendingArr());
            jcmbYears.setActionCommand("YEAR");
            jcmbYears.setSelectedItem(GUI_AY_Calendar.getTodayYear());
            jcmbYears.addActionListener(actionListenerForDatesJCMboxes());

            jcmbMonths = GUI_ElementCreator.newJComboBox(GUI_AY_Calendar.monthsArr);
            jcmbMonths.setSelectedItem(GUI_AY_Calendar.getTodayMonth());
            jcmbMonths.addActionListener(actionListenerForDatesJCMboxes());
            jcmbMonths.setActionCommand("MONTH");

            jcmbDays = GUI_ElementCreator.newJComboBox(GUI_AY_Calendar.getDaysAsStrArrForMountOfYear(Integer.parseInt(jcmbMonths.getSelectedItem() + ""),
                    Integer.parseInt(jcmbYears.getSelectedItem() + "")));
            jcmbDays.setSelectedItem(GUI_AY_Calendar.getTodayDayOfMonth());
            jcmbDays.addActionListener(actionListenerForDatesJCMboxes());
            jcmbDays.setActionCommand("DAY");

            add(GUI_ElementCreator.newTextLabel("Year"));
            add(jcmbYears);
            add(GUI_ElementCreator.newFieldNameLabel("Month"));
            add(jcmbMonths);
            add(GUI_ElementCreator.newFieldNameLabel("Day"));
            add(jcmbDays);

            setDateOutputTextFieldWithDateFromComboBoxes();
        }

        private ActionListener actionListenerForDatesJCMboxes() {
            ActionListener a = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getActionCommand().compareToIgnoreCase("YEAR") == 0) {
                        jcmbMonths.setSelectedIndex(0);
                    } else if (e.getActionCommand().compareToIgnoreCase("MONTH") == 0) {
                        replaceItemsAtJCMBoWith(jcmbDays, GUI_AY_Calendar.getDaysAsStrArrForMountOfYear(Integer.parseInt(jcmbMonths.getSelectedItem() + ""),
                                Integer.parseInt(jcmbYears.getSelectedItem() + "")));
                    } else if (e.getActionCommand().compareToIgnoreCase("DAY") == 0) {
                    }
                    setDateOutputTextFieldWithDateFromComboBoxes();
                }
            };
            return a;
        }
    }

}


//
//
//    private boolean checkInputData(String[] inputData){
//        boolean[] result = new boolean[INPUT_ELEMENTS_ON_VIEW_COUNTER];
//        if(inputData.length != INPUT_ELEMENTS_ON_VIEW_COUNTER){
//            return false;
//        }
//
//        setAllFalse(result);
//
//        String account = inputData[0].trim();
//
//        String custom_category = inputData[6].trim();
//        if(account.compareToIgnoreCase(jcmbAccount.getItemAt(DEFAULT_SELECTED_ITEM))==0){
//            result[0]=false;
//        }
//        if(custom_category.compareToIgnoreCase(jcmbCategory.getItemAt(DEFAULT_SELECTED_ITEM))==0){
//            result[6]=false;
//        }
//
//        return true;
//    }












//    private String[] getAllInputs() {
//        String account = (jcmbAccount.getSelectedItem() + "").trim();
//        String custom_category = (jcmbCategory.getSelectedItem() + "").trim();
////        boolean err = false;
////        if (account.compareToIgnoreCase(manyalEntryDefaultJTextFieldText[0]) == 0) {
////            jcmbAccount.setBorder(new LineBorder(Color.RED, 1));
////            err = true;
////
////        }
////        if (custom_category.compareToIgnoreCase(manyalEntryDefaultJTextFieldText[manyalEntryDefaultJTextFieldText.length - 1]) == 0) {
////            jcmbCategory.setBorder(new LineBorder(Color.RED, 1));
////            err = true;
////        }
////        if (err) {
////            return ERROR_IN_USER_INPUT;
////        }
////        String date= jtfOutputDate.getText().trim();
//        String[] dateArr = jtfOutputDate.getText().split("/");
//        if (dateArr[0].length() == 1) {
//            dateArr[0] = "0" + dateArr[0];
//        }
//        if (dateArr[1].length() == 1) {
//            dateArr[1] = "0" + dateArr[1];
//        }
//
//        // String date= dateArr[2]+"/"+dateArr[0]+"/"+dateArr[1];
//        String date = dateArr[0] + "/" + dateArr[1] + "/" + dateArr[2];
//
//        String refN = jtfRefNum.getText().trim();
//        String descr = jtfTransName.getText().trim();
//        String memo = jtfMemo.getText().trim();
//        String amount = jtfAmount.getText().trim();
////        GUI_ManualEntryTemporaialHolder.addTempUserManualEntry(account, date, refN,descr, memo, amount,custom_category);
//
//        return new String[]{account, date, refN, descr, memo, amount, custom_category};
//    }