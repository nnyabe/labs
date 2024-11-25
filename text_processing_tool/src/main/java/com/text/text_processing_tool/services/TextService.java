package com.text.text_processing_tool.services;

import com.text.text_processing_tool.models.TextProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextService implements TextProcessor {
    @Override
    public int countOccurrences(String text, String word) {
        int count = 0;
        count = (int) Arrays.stream(text.split("\\s+")).filter(currentWord -> currentWord.equalsIgnoreCase(word)).count();
        return count;
    }

    @Override
    public String replaceText(String text, String findWord, String replaceWord) {
        return text.replaceFirst(findWord, replaceWord);
    }

    @Override
    public String replaceAllText(String text, String findWord, String replaceWord) {
        return text.replace(findWord, replaceWord);
    }

    @Override
    public List<String> findMatches(String text, String word) {
        List<String> matches = new ArrayList<>();
        Matcher matcher = Pattern.compile(Pattern.quote(word), Pattern.CASE_INSENSITIVE).matcher(text);
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return matches;
    }

    public static class RegexService {
    }
}
