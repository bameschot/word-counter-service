package com.ameschot.bas.wordcounter.restendpoint;

import com.ameschot.bas.wordcounter.counterlogic.WordFrequency;
import com.ameschot.bas.wordcounter.counterlogic.WordFrequencyAnalyzer;
import com.ameschot.bas.wordcounter.counterlogic.WordFrequencyImpl;
import com.ameschot.bas.wordcounter.restendpoint.dto.WordFrequencyDTO;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@RequestScoped
@Path("word-counter")
public class WordCounterController {

    /*Attributes*/
    @Inject
    WordFrequencyAnalyzer wordFrequencyAnalyzer;

    /*Methods*/

    @GET
    @Path("highest-frequency")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(
            responseCode = "200",
            description = "host:Highest word frequency calculated, no persistent effect",
            content = @Content(
                    mediaType = "application/json", schema = @Schema(type = SchemaType.INTEGER)
            ))
    @Operation(
            summary = "Calculates the highest count of an individual word of words provided in the text, returns -1 if no words where found",
            description = "")
    public int highestFrequency(
            @Parameter(description = "The text to parse", required = true, example = "the-white butterfly@flies-near-the white4mountaintops", schema = @Schema(type = SchemaType.STRING))
            @QueryParam("text") String text) {
        //calculate highest frequency and return
        return wordFrequencyAnalyzer.calculateHighestFrequency(text);
    }

    @GET
    @Path("n-frequency")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(
            responseCode = "200",
            description = "host:Highest n word frequency calculated, no persistent effect",
            content = @Content(
                    mediaType = "application/json", schema = @Schema(type = SchemaType.ARRAY, implementation = WordFrequencyDTO.class)
            ))
    @Operation(
            summary = "Calculates the highest counts of an individual words in the provided text and returns the n highest results, returns -1 if nothing was found and all the words if n was larger than the number of unique words listed.",
            description = "")
    public List<WordFrequencyDTO> nFrequency(@Parameter(description = "The text to parse", required = true, example = "the-white butterfly@flies-near-the white4mountaintops", schema = @Schema(type = SchemaType.STRING))
                                             @QueryParam("text") String text,
                                             @Parameter(description = "The number of words to return", required = true, example = "8", schema = @Schema(type = SchemaType.INTEGER))
                                             @QueryParam("n") @Min(0) int n) {

        //calculate n frequency
        List<WordFrequency> res = wordFrequencyAnalyzer.calculateMostFrequentNWords(text, n);

        //map response to dto object
        List<WordFrequencyDTO> dtoRes = new ArrayList<>(res.size());
        for (WordFrequency wf : res) {
            dtoRes.add(new WordFrequencyDTO(wf.getWord(), wf.getFrequency()));
        }

        //return
        return dtoRes;
    }

    @GET
    @Path("word-frequency")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(
            responseCode = "200",
            description = "host:Requests the frequency of a word in the provided text, no persistent effect",
            content = @Content(
                    mediaType = "application/json", schema = @Schema(type = SchemaType.INTEGER)
            ))
    @Operation(
            summary = "Calculates the frequency of a word in the provided text and returns that frequency, returns -1 if no match was found.",
            description = "")

    public int wordFrequency(@Parameter(description = "The text to parse", required = true, example = "the-white butterfly@flies-near-the white4mountaintops", schema = @Schema(type = SchemaType.STRING))
                             @QueryParam("text") String text,
                             @Parameter(description = "The word to find the frequency of", required = true, example = "white", schema = @Schema(type = SchemaType.STRING))
                             @QueryParam("word") String word) {

        //calculate word frequency and return
        return wordFrequencyAnalyzer.calculateFrequencyForWord(text, word);
    }


}
