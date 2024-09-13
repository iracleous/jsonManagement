/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.codehub.eshoped.jsonmanagement.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.codehub.eshoped.jsonmanagement.model.Chapter;
import gr.codehub.eshoped.jsonmanagement.model.Line;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Optional;

/**
 *
 * @author DimitrisIracleous
 * @since September 2024
 * @version 0.0.1
 */
public class UrlReader {

    /**
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public static void Reader() throws IOException, InterruptedException {
        String url = "https://raw.githubusercontent.com/grokify/kibana-tutorial-go/master/shakespeare.json";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String data = response.body();
        String lines[] = data.split("\n");
        HashMap<String, Integer> plays = new HashMap<>();
        int chapterNumber = 0;
        int linesNumber = 0;
        for (String line : lines) {
            Optional<Line> myLine = lineToJson(line, Line.class);
            if (myLine.isPresent()) {
                String play_name = myLine
                        .get()
                        .getPlay_name();
                plays.put(play_name, plays.getOrDefault(play_name, 0) + 1);
                linesNumber ++;
            } else {
                Optional<Chapter> chapter = lineToJson(line, Chapter.class);
                if (chapter.isPresent()) {
                    chapterNumber++;
                }
            }
        }
        System.out.println("total file lines = " + lines.length);
        System.out.println("playlinesNumber = " + linesNumber);
        System.out.println("chapterNumber = " + chapterNumber);
        System.out.println("check = "+ (linesNumber + chapterNumber));
        System.out.println("number of pays " + plays.size());
        
        System.out.println("=================");
        System.out.println(plays);
    }

    /**
     *
     * @param <T>
     * @param line
     * @param classType
     * @return
     */
    public static <T extends Object> Optional<T> lineToJson(String line, Class<T> classType) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return Optional.of(objectMapper.readValue(line, classType));
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }
}
