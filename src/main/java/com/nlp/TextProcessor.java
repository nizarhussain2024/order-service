package com.nizar.nlp;

import java.util.*;
import java.util.stream.Collectors;

public class TextProcessor {
    
    public static List<String> tokenize(String text) {
        if (text == null || text.isEmpty()) {
            return Collections.emptyList();
        }
        
        return Arrays.stream(text.toLowerCase().split("\\s+"))
            .map(word -> word.replaceAll("[^a-z0-9]", ""))
            .filter(word -> word.length() > 0)
            .collect(Collectors.toList());
    }
    
    public static double calculateSimilarity(String text1, String text2) {
        List<String> tokens1 = tokenize(text1);
        List<String> tokens2 = tokenize(text2);
        
        if (tokens1.isEmpty() || tokens2.isEmpty()) {
            return 0.0;
        }
        
        Set<String> set1 = new HashSet<>(tokens1);
        Set<String> set2 = new HashSet<>(tokens2);
        
        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        
        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);
        
        if (union.isEmpty()) {
            return 0.0;
        }
        
        return (double) intersection.size() / union.size();
    }
}
