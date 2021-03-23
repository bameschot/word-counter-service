package com.ameschot.bas.wordcounter.counterlogic.comparators;

import com.ameschot.bas.wordcounter.counterlogic.WordFrequency;

public class WordFrequencyDescendingFrequencyAndTextAlphabeticComparator extends WordFrequencyDescendingFrequencyComparator {
    @Override
    public int compare(WordFrequency o1, WordFrequency o2) {
        int r = super.compare(o1,o2);
        if(r!=0)
            return r;

        if(o1.getWord()==null && o2.getWord()==null) {
            return 0;
        }
        else if(o1.getWord()==null && o2.getWord() != null) {
            return -1;
        }
        else if(o1.getWord()!=null && o2.getWord() == null) {
            return 1;
        }

        return o1.getWord().compareTo(o2.getWord());
    }
}
