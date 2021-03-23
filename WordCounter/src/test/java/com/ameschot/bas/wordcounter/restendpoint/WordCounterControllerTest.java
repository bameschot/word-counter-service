package com.ameschot.bas.wordcounter.restendpoint;

import com.ameschot.bas.wordcounter.counterlogic.WordFrequencyAnalyzer;
import com.ameschot.bas.wordcounter.counterlogic.WordFrequencyAnalyzerImpl;
import com.ameschot.bas.wordcounter.counterlogic.WordFrequencyImpl;
import com.ameschot.bas.wordcounter.restendpoint.dto.WordFrequencyDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class WordCounterControllerTest
{
    @Mock
    WordFrequencyAnalyzerImpl wordFrequencyAnalyzer;

    @InjectMocks
    WordCounterController sut = new WordCounterController();

    @Test
    public void testHighestFrequency()
    {
        Mockito.when(wordFrequencyAnalyzer.calculateHighestFrequency("laks kals laks")).thenReturn(2);

        int res = sut.highestFrequency("laks kals laks");

        Assert.assertEquals(res,2);
    }

    @Test
    public void calculateMostFrequentNWords()
    {
        Mockito.when(wordFrequencyAnalyzer.calculateMostFrequentNWords("laks kals laks",4)).thenReturn(Arrays.asList(new WordFrequencyImpl("laks",2),new WordFrequencyImpl("kals",1)));

        List<WordFrequencyDTO> res = sut.nFrequency("laks kals laks",4);

        List<WordFrequencyDTO> checkRes = Arrays.asList(new WordFrequencyDTO("laks",2),new WordFrequencyDTO("kals",1));

        Assert.assertEquals(checkRes,res);
    }

    @Test
    public void calculateFrequencyForWord()
    {
        Mockito.when(wordFrequencyAnalyzer.calculateFrequencyForWord("laks kals laks","laks")).thenReturn(2);

        int res = sut.wordFrequency("laks kals laks","laks");

        Assert.assertEquals(res,2);
    }


}
