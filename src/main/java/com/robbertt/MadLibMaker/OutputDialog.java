package com.robbertt.MadLibMaker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.File;
import java.io.FileWriter;

public class OutputDialog extends JFrame {
    private JTextPane textPane1;
    private JButton quitButton;
    private JButton saveButton;
    private JPanel rootPane;

    protected OutputDialog(String output) {
        setTitle("Madlib Maker");
        setSize(400, 500);
        setContentPane(rootPane);
        rootPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        //fill in values
        textPane1.setText(output);
        quitButton.addActionListener((event) -> {
            System.exit(0);
        });
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        saveButton.addActionListener((event) -> {
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogTitle("Save Text");
            jfc.setFileFilter(new TextFileFilter());
            jfc.setAcceptAllFileFilterUsed(false);
            jfc.setApproveButtonText("Save");
            jfc.setMultiSelectionEnabled(false);
            int i = jfc.showSaveDialog(this);
            File fileToWrite = null;
            if(i == JFileChooser.APPROVE_OPTION) {
                fileToWrite = jfc.getSelectedFile();
            }
            //write file
            if(fileToWrite != null) {
                if(!fileToWrite.getName().toLowerCase().endsWith(".txt")) {
                    //append .txt
                    fileToWrite = new File(fileToWrite.getPath() + ".txt");
                }
                try {
                    FileWriter fw = new FileWriter(fileToWrite);
                    fw.write(output);
                    fw.close();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public JTextPane getTextPane() {
        return textPane1;
    }
}
