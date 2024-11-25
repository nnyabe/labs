package com.text.text_processing_tool.services;

import com.text.text_processing_tool.models.RegexProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexService implements RegexProcessor {
    /**
     * @param text 
     * @param regex
     * @return
     */
    @Override
    public boolean hasMatch(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

    /**
     * @param text 
     * @param regex
     * @return
     */
    @Override
    public int countMatches(String text, String regex) {
        int count = 0;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()){
            count++;
        }

        return count;
    }

    /**
     * @param text 
     * @param regex
     * @param replaceWord
     * @return
     */
    @Override
    public String replaceText(String text, String regex, String replaceWord) {
        return text.replaceFirst(regex, replaceWord);
    }

    /**
     * @param text 
     * @param regex
     * @param replaceWord
     * @return
     */
    @Override
    public String replaceAllMatches(String text, String regex, String replaceWord) {
        return text.replaceAll(regex, replaceWord);
    }

    /**
     * @param text 
     * @param regex
     * @return
     */
    @Override
    public List<String> findRegexMatches(String text, String regex) {
        List<String> matches = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while(matcher.find()){
            matches.add(matcher.group());
        }

        return matches;
    }
}
