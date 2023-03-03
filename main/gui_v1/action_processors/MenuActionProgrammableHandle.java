package gui_v1.action_processors;


import ay_local_settings.ay_local_variables;
import gui_v1.RecordsTable;
import gui_v1.mainWindows.GUI_HowToWindow;
import gui_v1.mainWindows.GUI_ManualEntryWindow;
import gui_v1.mainWindows.GUI_RecordsWindow;
import main_logic.PEC;
import main_logic.Request;
import main_logic.Result;
import parsers.OFXParser;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ListIterator;

//import static gui_v1.settings.GUI_Static_Settings.pathToFile;


public class MenuActionProgrammableHandle {


    void doHowToStartProcessing(){
        GUI_HowToWindow.showRecordsWindow();
    }

    /**
     *  this method is
     *  getting file of user choose and
     *  stronger it in File named chosenFile
     *  for future processing..
     *  Code in method is just example of use, how to get file from user, and
     *  can be used anywhere.
     */
    public  void doParsOFXFileProcessing(){
      //  GUI_RecordsFrame records = new GUI_RecordsFrame();
        GUI_RecordsWindow.createRecordViewWindow();
//        File f = null;

        File f = ay_local_variables.work_dir;
        File chosenFile= GUI_FileChooser.getFileOrDirectory(f);
        if(chosenFile == null ){
            JOptionPane.showMessageDialog(null, "File not Selected","Info", JOptionPane.ERROR_MESSAGE);
        } else{
            out( "OFX Chosen File for parsing is " + chosenFile.getAbsolutePath() + ".");
            Request request = Request.instance();
            request.reset();
            ListIterator<Result> it;
            request.setFileWithPath(chosenFile.getAbsolutePath());
            /*
            //For limiting the time window for parsed Transactions.
            request.setFrom(...);
            request.setTo(...);
            // technically, we don't want more than 3 months of Transactions:
            // date.add(Calendar.MONTH, 3);
            */
            it = PEC.instance().parseOFX(request);
            /*
            //Applying sorting, if a Transaction column header/title has been clicked.
            //This may have to go to a different method.
            request.setButton(Request.Button.NAME); // sorted by different attribute (NAME)
            it = PEC.instance().sortedColumnSwitched(request);
            */
            Result result = new Result();
            result = it.next();
            if (result.getCode()!=Result.Code.SUCCESS) {
                JOptionPane.showMessageDialog(null, "The file is not OFX/QFX\nfile or could NOT be read.","Error", JOptionPane.INFORMATION_MESSAGE);
            } else {
                RecordsTable.addRowToTable(result.getTDate(), result.getTRef(), result.getTDesc(),
                        result.getTMemo(), result.getTAmount(), result.getTCat());
            }
            while(it.hasNext()){
                result = it.next();
                RecordsTable.addRowToTable(result.getTDate(), result.getTRef(), result.getTDesc(),
                        result.getTMemo(), result.getTAmount(), result.getTCat());
                out("Date>>>> "+result.getTDate());
            }
        }
//        records.setVisible(true);

        GUI_RecordsWindow.showRecordsWindow();
    }

    public void out(Object o){
        System.out.println(o+"");

    }

    void doAdvisingProcessing(){

    }
    void doManualEntryProcessing(){
        GUI_ManualEntryWindow.showManualEntryWindow();
    }
    void doGenerateSummaryProcessing(){

    }
    void doSettingsProcessing(){

    }
    void dologOutProcessing(){

    }


}





        /*
            request.setButton(Request.Button.NAME); // sorted by different attribute (NAME)
            it = PEC.instance().sortedColumnSwitched(request);
            */

    /*
    void doParsOFXFileProcessing() {

        GUI_RecordsFrame records = new GUI_RecordsFrame();
        File f = null;
//         GUI_FileChooser.getFileOrDirectory(f);
        File chosenFile= GUI_FileChooser.getFileOrDirectory(f);
        if(chosenFile == null ){
            JOptionPane.showMessageDialog(null, "File not Selected","Info", JOptionPane.ERROR_MESSAGE);
            return;
        }
        TransactionList t;
        instance();
        try {
            t = OFXParser.ofxParser(chosenFile);

            ListIterator<Transaction> i = t.listIterator(); // t.sort(Transaction.DESCRIPTION);
            int count  = 0;
            while (i.hasNext()) {
                //RecordsTable.addRowToTable(i.next());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        records.setVisible(true);

    }
*/


//
//    void doParsOFXFileProcessing(){
//        File chosenFile= GUI_FileChooser.getFileOrDirectory();
//
//        if(chosenFile == null ){
//            JOptionPane.showMessageDialog(null, "File not Selected","Info", JOptionPane.ERROR_MESSAGE);
//        } else{
//
//            out( "OFX Chosen File for pParse is" + chosenFile.getName() );
//
//            Request request = Request.instance();
//            ListIterator<Transaction> it;
//            request.setFileWithPath(chosenFile.getAbsolutePath());
//            it = PEC.instance().parseOFX(request);
////            ListIterator<Transaction> i = t.listIterator();
//            while (it.hasNext()) {
//                RecordsTable.addRowToTable((Transaction)(it.next()));
//            }
//        }
//    }
//
//    TransactionList t;
//    //        File file = new File("/home/");
//    instance();
//
//    File file = new File("CreditCardSAMPLE.qfx");
//    t = OFXParser.ofxParser(file);
//            ListIterator<Transaction> i = t.listIterator(); // t.sort(Transaction.DESCRIPTION);
//        int count  = 0;
//        while (i.hasNext()) {
////            System.out.println(i.next());
//        addRowToTable(i.next(),m);