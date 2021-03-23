package com.ameschot.bas.wordcounter.counterlogic;

import com.ameschot.bas.wordcounter.counterlogic.WordFrequency;
import com.ameschot.bas.wordcounter.counterlogic.WordFrequencyImpl;
import com.ameschot.bas.wordcounter.counterlogic.comparators.WordFrequencyDescendingFrequencyAndTextAlphabeticComparator;
import com.ameschot.bas.wordcounter.counterlogic.comparators.WordFrequencyDescendingFrequencyComparator;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class WordFrequencyComparatorsTest
{
    @Test
    public void testDescendingSort()
    {
        WordFrequency wf1 = new WordFrequencyImpl("over",1);
        WordFrequency wf2 = new WordFrequencyImpl("the",5);
        WordFrequency wf3 = new WordFrequencyImpl("horizon",3);

        List<WordFrequency> testList = Arrays.asList(wf1,wf2,wf3);
        testList.sort(new WordFrequencyDescendingFrequencyComparator());

        Assert.assertEquals(Arrays.asList(wf2,wf3,wf1),testList);
    }

    @Test
    public void testDescendingSortOnFrequencyAndAlphabeticText()
    {
        WordFrequency wf1 = new WordFrequencyImpl("over",1);
        WordFrequency wf2 = new WordFrequencyImpl("the",5);
        WordFrequency wf3 = new WordFrequencyImpl("mountain",5);

        List<WordFrequency> testList = Arrays.asList(wf1,wf2,wf3);
        testList.sort(new WordFrequencyDescendingFrequencyAndTextAlphabeticComparator());

        Assert.assertEquals(Arrays.asList(wf3,wf2,wf1),testList);
    }

    @Test
    public void testDescendingSortNullInput()
    {
        WordFrequency wf1 = new WordFrequencyImpl("over",1);
        WordFrequency wf2 = new WordFrequencyImpl(null,5);
        WordFrequency wf3 = new WordFrequencyImpl("mountain",5);

        List<WordFrequency> testList = Arrays.asList(wf1,wf2,wf3);
        testList.sort(new WordFrequencyDescendingFrequencyAndTextAlphabeticComparator());

        Assert.assertEquals(Arrays.asList(wf2,wf3,wf1),testList);
    }

    @Test
    public void testDescendingSortManyNullInput()
    {
        WordFrequency wf1 = new WordFrequencyImpl(null,1);
        WordFrequency wf2 = new WordFrequencyImpl(null,5);
        WordFrequency wf3 = new WordFrequencyImpl(null,5);
        WordFrequency wf4 = new WordFrequencyImpl("a",1);


        List<WordFrequency> testList = Arrays.asList(wf4,wf1,wf2,wf3);
        testList.sort(new WordFrequencyDescendingFrequencyAndTextAlphabeticComparator());

        Assert.assertEquals(Arrays.asList(wf2,wf3,wf1,wf4),testList);
    }

}
