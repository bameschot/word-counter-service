package com.ameschot.bas.wordcounter.counterlogic.comparators;

import com.ameschot.bas.wordcounter.counterlogic.WordFrequency;

import java.util.Comparator;

public class WordFrequencyDescendingFrequencyComparator implements Comparator<WordFrequency> {
    @Override
    public int compare(WordFrequency o1, WordFrequency o2) {
        if (o1.getFrequency() < o2.getFrequency())
            return 1;
        else if (o1.getFrequency() > o2.getFrequency())
            return -1;
        return 0;
    }
}
