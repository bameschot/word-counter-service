package com.ameschot.bas.wordcounter.restendpoint.dto;

import com.ameschot.bas.wordcounter.counterlogic.WordFrequency;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

public class WordFrequencyDTO implements WordFrequency {
    /*Attributes*/
    protected String word;

    protected int frequency;

    /*Constructor*/
    public WordFrequencyDTO() {

    }

    public WordFrequencyDTO(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    /*Methods*/
    public void setWord(String word) {
        this.word = word;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String getWord() {
        return word;
    }

    @Override
    public int getFrequency() {
        return frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordFrequencyDTO that = (WordFrequencyDTO) o;
        return frequency == that.frequency && Objects.equals(word, that.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, frequency);
    }
}
