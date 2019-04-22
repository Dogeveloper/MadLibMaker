package com.robbertt.MadLibMaker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorDialog extends JFrame {
    private JTextPane textPane1;
    private JButton continueButton;
    private JButton exitButton;
    private JPanel rootPanel;

    protected ErrorDialog(Exception ex) {
        setTitle("Madlib Maker");
        setSize(Main.scaleWindowSize(0.6, 0.5));
        setResizable(false);
        setContentPane(rootPanel);
        rootPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        textPane1.setText(stackTraceToString(ex));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        continueButton.addActionListener(e -> {
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });
        exitButton.addActionListener(e -> {
            System.exit(0);
        });
    }

    private static String stackTraceToString(Exception e) {
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        e.printStackTrace(pw);
        return(writer.toString());
    }
}
