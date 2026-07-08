package com.proje.mobilesales.core.utils;

import java.io.File;
import java.io.FileFilter;

public class DirectoryFileFilter implements FileFilter {
    public boolean accept(File file) {
        return file.isDirectory();
    }
}
