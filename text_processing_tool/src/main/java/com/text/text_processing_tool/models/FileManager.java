package com.text.text_processing_tool.models;

import java.io.File;
import java.io.FileNotFoundException;

public interface FileManager {
    String importFile() throws FileNotFoundException;
    void saveTextToFile(File file, String text);
    void saveFileAs();
}
