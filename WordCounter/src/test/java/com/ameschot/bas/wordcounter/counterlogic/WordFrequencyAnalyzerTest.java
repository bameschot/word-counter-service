package com.ameschot.bas.wordcounter.counterlogic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordFrequencyAnalyzerTest {
    private final static String TEST_TEXT_BASE = " The sun  shines over    tHe lake!near^the mountains;;;'near4the3Mountains#$#$of@Mountainious,,moUntains^^^moUntAins ";
    private final static String TEST_TEXT_NO_WORDS = "        ..^ *)! 汉";

    WordFrequencyAnalyzer sut = new WordFrequencyAnalyzerImpl();

    @Test
    public void isCharAllowedRanges() {
        Assert.assertTrue(new WordFrequencyAnalyzerImpl().isCharAllowed('a'));
        Assert.assertTrue(new WordFrequencyAnalyzerImpl().isCharAllowed('A'));
        Assert.assertTrue(new WordFrequencyAnalyzerImpl().isCharAllowed('z'));
        Assert.assertTrue(new WordFrequencyAnalyzerImpl().isCharAllowed('Z'));
        Assert.assertTrue(new WordFrequencyAnalyzerImpl().isCharAllowed('b'));
        Assert.assertTrue(new WordFrequencyAnalyzerImpl().isCharAllowed('B'));
    }

    @Test
    public void isCharNotAllowedRanges() {
        Assert.assertFalse(new WordFrequencyAnalyzerImpl().isCharAllowed('6'));
        Assert.assertFalse(new WordFrequencyAnalyzerImpl().isCharAllowed('$'));
        Assert.assertFalse(new WordFrequencyAnalyzerImpl().isCharAllowed('-'));
        Assert.assertFalse(new WordFrequencyAnalyzerImpl().isCharAllowed('!'));
        Assert.assertFalse(new WordFrequencyAnalyzerImpl().isCharAllowed('"'));
        Assert.assertFalse(new WordFrequencyAnalyzerImpl().isCharAllowed('>'));
    }


    @Test
    public void assignWordToFrequencyMapNonPOJOWordFrequency() {
        Map<String, WordFrequency> wordFrequencyMap = new HashMap<>();
        wordFrequencyMap.put("mountain", new WordFrequency() {
            @Override
            public String getWord() {
                return "mountain";
            }

            @Override
            public int getFrequency() {
                return 1;
            }
        });

        new WordFrequencyAnalyzerImpl().assignWordToFrequencyMap("mountain", wordFrequencyMap);

        Assert.assertEquals(wordFrequencyMap.size(), 1);
        Assert.assertEquals(wordFrequencyMap.get("mountain").getFrequency(), 2);
    }


    @Test
    public void analysisNoText() {
        String testText = null;

        Map<String, WordFrequency> frequencyMap = new WordFrequencyAnalyzerImpl().analyze(null);

        Assert.assertNotNull(frequencyMap);
        Assert.assertEquals(frequencyMap.size(), 0);
    }

    @Test
    public void analysisEmptyText() {
        String testText = null;

        Map<String, WordFrequency> frequencyMap = new WordFrequencyAnalyzerImpl().analyze("");

        Assert.assertNotNull(frequencyMap);
        Assert.assertEquals(frequencyMap.size(), 0);
    }

    @Test
    public void analysisNoWords() {
        String testText = null;

        Map<String, WordFrequency> frequencyMap = new WordFrequencyAnalyzerImpl().analyze(TEST_TEXT_NO_WORDS);

        Assert.assertNotNull(frequencyMap);
        Assert.assertEquals(frequencyMap.size(), 0);
    }

    @Test
    public void analysisMixedCaseMixedSeparators() {

        Map<String, WordFrequency> frequencyMap = new WordFrequencyAnalyzerImpl().analyze(TEST_TEXT_BASE);

        Assert.assertNotNull(frequencyMap);
        Assert.assertEquals(frequencyMap.size(), 9);
        Assert.assertEquals(frequencyMap.get("the").getFrequency(), 4);
        Assert.assertEquals(frequencyMap.get("mountains").getFrequency(), 4);
        Assert.assertEquals(frequencyMap.get("near").getFrequency(), 2);


    }

    @Test
    public void calculateHighestFrequencyBase() {
        int res = sut.calculateHighestFrequency(TEST_TEXT_BASE);

        Assert.assertEquals(4, res);
    }

    @Test
    public void calculateHighestFrequencyBaseNullInput() {
        int res = sut.calculateHighestFrequency(null);

        Assert.assertEquals(-1, res);
    }

    @Test
    public void calculateHighestFrequencyBaseNoWords() {
        int res = sut.calculateHighestFrequency(TEST_TEXT_NO_WORDS);

        Assert.assertEquals(-1, res);
    }

    @Test
    public void calculateFrequencyForWordTextNullInput() {
        int res = sut.calculateHighestFrequency(null);

        Assert.assertEquals(-1, res);
    }

    @Test
    public void calculateFrequencyForWordNoWords() {
        int res = sut.calculateHighestFrequency(TEST_TEXT_NO_WORDS);

        Assert.assertEquals(-1, res);
    }

    @Test
    public void calculateFrequencyForWordLowerCaseInput() {
        int res = sut.calculateFrequencyForWord(TEST_TEXT_BASE, "the");

        Assert.assertEquals(4, res);
    }

    @Test
    public void calculateFrequencyForWordUpperCaseInput() {
        int res = sut.calculateFrequencyForWord(TEST_TEXT_BASE, "tHe");

        Assert.assertEquals(4, res);
    }

    @Test
    public void calculateFrequencyForWordNonMatchingInput() {
        int res = sut.calculateFrequencyForWord(TEST_TEXT_BASE, "frequency");

        Assert.assertEquals(-1, res);
    }

    @Test
    public void calculateFrequencyForWordWordNullInput() {
        int res = sut.calculateFrequencyForWord(TEST_TEXT_BASE, null);

        Assert.assertEquals(-1, res);
    }

    @Test
    public void calculateMostFrequentNWordsBase() {
        List<WordFrequency> frequentNWords = sut.calculateMostFrequentNWords(TEST_TEXT_BASE, 3);

        Assert.assertEquals(frequentNWords.size(), 3);

        Assert.assertEquals(frequentNWords.get(0).getWord(), "mountains");
        Assert.assertEquals(frequentNWords.get(1).getWord(), "the");
        Assert.assertEquals(frequentNWords.get(2).getWord(), "near");

    }

    @Test
    public void calculateMostFrequentNWordsOverBoundN() {
        List<WordFrequency> frequentNWords = sut.calculateMostFrequentNWords(TEST_TEXT_BASE, 999);

        Assert.assertEquals(frequentNWords.size(), 9);
        Assert.assertEquals(frequentNWords.get(0).getWord(), "mountains");
        Assert.assertEquals(frequentNWords.get(1).getWord(), "the");
        Assert.assertEquals(frequentNWords.get(2).getWord(), "near");
        Assert.assertEquals(frequentNWords.get(3).getWord(), "lake");
        Assert.assertEquals(frequentNWords.get(4).getWord(), "mountainious");
        Assert.assertEquals(frequentNWords.get(5).getWord(), "of");
        Assert.assertEquals(frequentNWords.get(6).getWord(), "over");
        Assert.assertEquals(frequentNWords.get(7).getWord(), "shines");
        Assert.assertEquals(frequentNWords.get(8).getWord(), "sun");

    }

    @Test
    public void calculateMostFrequentNZeroN() {
        List<WordFrequency> frequentNWords = sut.calculateMostFrequentNWords(TEST_TEXT_BASE, 0);

        Assert.assertEquals(frequentNWords.size(), 0);
    }

    //TODO: why does this not work with liberty open?
//    @Test
//    public void calculateMostFrequentNWordsUnderboundN() {
//        Assert.assertThrows(IllegalArgumentException.class, () -> sut.calculateMostFrequentNWords(TEST_TEXT_BASE, -1));
//    }


}
