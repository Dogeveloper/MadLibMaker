package com.robbertt.MadLibMaker;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    //lang
    private static final String SELECT_FILE = "Select a .madlib file to load.";
    protected static final String EXTENSION = ".madlib";
    private static final String FILE_SELECT_BUTTON_TEXT = "Use this Madlib";

    public static void main(String[] args) {
        //totally not copied from stackoverflow. anyways, it makes the interface look more like the operating system interface.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        //prompt user to pick a file to load
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.setApproveButtonText(FILE_SELECT_BUTTON_TEXT);
        jfc.setDialogTitle(SELECT_FILE);
        jfc.setFileFilter(new MadLibFileFilter());
        jfc.setMultiSelectionEnabled(false);
        int returnedd = jfc.showOpenDialog(null);
        File selectedFile = null;
        if(JFileChooser.APPROVE_OPTION == returnedd) {
            selectedFile = jfc.getSelectedFile();
        }
        if(selectedFile == null) {
            //quit if they exit out.
            System.exit(0);
        }
        //ask the user if this is the madlib they want to load.
        int confirmResult = JOptionPane.showConfirmDialog(null, "Would you like to load the Madlib " + selectedFile.getName() + "?");
        if(confirmResult != 0) {
            System.exit(0);
        }
        //display user options and replace.
        try {
            FileInputStream fis = new FileInputStream(selectedFile);
            byte[] info = new byte[(int) selectedFile.length()];
            fis.read(info);
            fis.close();
            String madlib = new String(info, "UTF-8");
            //look for paren
            Matcher m = Pattern.compile("<(.*?)>").matcher(madlib);
            while(m.find()) {
                String template = m.group();
                //extract brackets
                String templateNoBrackets = template.replace("<", "");
                templateNoBrackets = templateNoBrackets.replace(">", "");
                //get user input.
                String str = JOptionPane.showInputDialog(templateNoBrackets);
                //replace original string with new info.
                madlib = madlib.replace(template, str);
            }
            OutputDialog o = new OutputDialog(madlib);
            o.setVisible(true);
        }
        catch(Exception e) {

        }

    }
}
