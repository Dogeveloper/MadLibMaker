package com.robbertt.MadLibMaker;

import java.io.File;

public class MadLibFileFilter extends javax.swing.filechooser.FileFilter {
    public boolean accept(File name) {
        return name.getName().toLowerCase().endsWith(Main.EXTENSION);
    }

    public String getDescription() {
        return Main.EXTENSION;
    }
}
