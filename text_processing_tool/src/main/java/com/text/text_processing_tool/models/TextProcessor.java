package com.text.text_processing_tool.models;

import java.util.List;

public interface TextProcessor {
    int countOccurrences(String text, String word);
    String replaceText(String text, String findWord, String replaceWord);
    String replaceAllText(String text, String findWord, String replaceWord);
    List<String> findMatches(String text, String word);
}
