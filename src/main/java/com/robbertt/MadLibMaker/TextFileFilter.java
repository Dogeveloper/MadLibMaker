package com.robbertt.MadLibMaker;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class TextFileFilter extends FileFilter {
    public boolean accept(File f) {
        return f.getName().toLowerCase().contains(".txt");
    }

    public String getDescription() {
        return ".txt";
    }
}
