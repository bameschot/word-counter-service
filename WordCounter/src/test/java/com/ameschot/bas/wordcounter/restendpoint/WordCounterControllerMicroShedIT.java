package com.ameschot.bas.wordcounter.restendpoint;



import com.ameschot.bas.wordcounter.counterlogic.WordFrequencyImpl;
import com.ameschot.bas.wordcounter.restendpoint.dto.WordFrequencyDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.microshed.testing.jaxrs.RESTClient;
import org.microshed.testing.jupiter.MicroShedTest;
import org.microshed.testing.testcontainers.ApplicationContainer;
import org.mockito.Mockito;
import org.testcontainers.junit.jupiter.Container;

import java.util.Arrays;
import java.util.List;

@MicroShedTest
//!!!MUST END WITH IT for this to work with liberty open
public class WordCounterControllerMicroShedIT
{
    @RESTClient
    public static WordCounterController wordCounterController;

    @Container
    public static ApplicationContainer app = new ApplicationContainer()
            .withAppContextRoot("/word-counter")
            .withReadinessPath("/health/ready");

    @Test
    public void testHighestFrequency()
    {
        int res = wordCounterController.highestFrequency("laks kals laks");

        Assert.assertEquals(res,2);
    }

    @Test
    public void calculateMostFrequentNWords()
    {
        List<WordFrequencyDTO> res = wordCounterController.nFrequency("laks kals laks",4);

        List<WordFrequencyDTO> checkRes = Arrays.asList(new WordFrequencyDTO("laks",2),new WordFrequencyDTO("kals",1));

        Assert.assertEquals(checkRes,res);
    }

    @Test
    public void calculateFrequencyForWord()
    {
        int res = wordCounterController.wordFrequency("laks kals laks","laks");

        Assert.assertEquals(res,2);
    }

}
