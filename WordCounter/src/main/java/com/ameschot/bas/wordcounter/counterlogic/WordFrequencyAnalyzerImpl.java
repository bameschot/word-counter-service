package com.ameschot.bas.wordcounter.counterlogic;

import com.ameschot.bas.wordcounter.counterlogic.comparators.WordFrequencyDescendingFrequencyAndTextAlphabeticComparator;
import com.ameschot.bas.wordcounter.counterlogic.comparators.WordFrequencyDescendingFrequencyComparator;

import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyAnalyzerImpl implements WordFrequencyAnalyzer {

    /*Constants*/

    /*Attributes*/

    /*Constructor*/
    public WordFrequencyAnalyzerImpl() {

    }

    /*Methods*/

    protected Map<String, WordFrequency> analyze(String text) {

        //setup the wordFrequency map used for keeping track of the records
        Map<String, WordFrequency> wordFrequencyMap = new HashMap<>();

        //if nothing to process return the empty map
        if (text == null || text.isEmpty())
            return wordFrequencyMap;

        //lowercase the input text before processing
        char[] lowerCaseTextChars = text.toLowerCase(Locale.ROOT).toCharArray();


        //loop over the individual characters
        char currentC;
        StringBuilder wordBuilder = new StringBuilder();
        for (int i = 0; i < lowerCaseTextChars.length; i++) {
            //assign the current character
            currentC = lowerCaseTextChars[i];

            //check if the character is alphabetic
            if (isCharAllowed(currentC)) {
                wordBuilder.append(currentC);
            } else {
                //assign the word to the map
                assignWordToFrequencyMap(wordBuilder.toString(), wordFrequencyMap);

                //reset the wordBuilder for re-use
                wordBuilder.setLength(0);
            }
        }

        //assign the remainder of the text to the map
        assignWordToFrequencyMap(wordBuilder.toString(), wordFrequencyMap);

        //return result
        return wordFrequencyMap;
    }

    protected boolean isCharAllowed(char c) {

        //limit allowed characters on the A-Z and a-z range
        if (c >= 65 && c <= 90 || (c >= 97 && c <= 172))
            return true;
        return false;
    }

    protected void assignWordToFrequencyMap(String word, Map<String, WordFrequency> wordFrequencyMap) {
        //do not assign empty values
        if (word == null || word.isEmpty())
            return;

        //get/assign the word frequency from the map
        WordFrequency wf = wordFrequencyMap.computeIfAbsent(word, w -> new WordFrequencyImpl(w, 0));

        //if it is the our own defined WordFrequencyImpl raise the frequency without instantiating a new object
        if (wf instanceof WordFrequencyImpl) {
            ((WordFrequencyImpl) wf).raiseFrequency();
        }
        //if not raise the frequency by instantiating a new WordFrequencyImpl object
        else {
            wordFrequencyMap.put(word, new WordFrequencyImpl(word, wf.getFrequency() + 1));
        }
    }


    @Override
    public int calculateHighestFrequency(String text) {
        List<WordFrequency> wfs = analyze(text).values().stream().sorted(new WordFrequencyDescendingFrequencyComparator()).collect(Collectors.toList());
        if (!wfs.isEmpty())
            return wfs.get(0).getFrequency();
        return -1;
    }

    @Override
    public int calculateFrequencyForWord(String text, String word) {
        if (word == null)
            return -1;

        WordFrequency wf = analyze(text).get(word.toLowerCase(Locale.ROOT));
        if (wf != null)
            return wf.getFrequency();
        return -1;
    }

    @Override
    public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
        List<WordFrequency> wfs = analyze(text).values().stream().sorted(new WordFrequencyDescendingFrequencyAndTextAlphabeticComparator()).collect(Collectors.toList());
        if (wfs.size() > n)
            return wfs.subList(0, n);
        else
            return wfs;
    }


}
