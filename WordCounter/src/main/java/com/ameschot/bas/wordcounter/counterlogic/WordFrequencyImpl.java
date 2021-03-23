package com.ameschot.bas.wordcounter.counterlogic;

public class WordFrequencyImpl implements WordFrequency {

    /*Attributes*/
    private String word;
    private int frequency;


    /*Constructor*/

    public WordFrequencyImpl(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    /*Methods*/
    @Override
    public String getWord() {
        return word;
    }

    @Override
    public int getFrequency() {
        return frequency;
    }

    void raiseFrequency()
    {
        frequency++;
    }

    @Override
    public String toString() {
        return "WordFrequencyPOJO{" +
                "word='" + word + '\'' +
                ", frequency=" + frequency +
                '}';
    }
}
