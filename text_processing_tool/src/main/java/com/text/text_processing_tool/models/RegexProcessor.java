package com.text.text_processing_tool.models;

import java.util.List;

public interface RegexProcessor {
    boolean hasMatch(String text, String regex);
    int countMatches(String text, String regex);
    String replaceText(String text, String regex, String replaceWord);
    String replaceAllMatches(String text, String regex, String replaceWord);
    List<String> findRegexMatches(String text, String regex);
}
