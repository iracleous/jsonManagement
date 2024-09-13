/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gr.codehub.eshoped.jsonmanagement.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

/**
 *
 * @author DimitrisIracleous
 */
public class UrlRader {
    public static void Reader() throws IOException, InterruptedException{
        String url = "https://raw.githubusercontent.com/grokify/kibana-tutorial-go/master/shakespeare.json";
        HttpClient client = HttpClient.newHttpClient();    
        HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String data =  response.body();
        String lines[] = data.split("\n");
        HashMap<String, Integer> plays = new HashMap<>();
        for(String line:lines){ 
            String play_name = lineToJson(line);
            if (play_name != null)
                plays.put(play_name, plays.getOrDefault(play_name, 0) + 1);
        }
        
        System.out.println(lines.length);
        System.out.println(plays.size());
        System.out.println("=================");
        System.out.println(plays);
    }
    
    public static String lineToJson(String line) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(line);
        JsonNode data = jsonNode.get("play_name");
        if (data!= null) return data.asText();
        return null;
    }
      
}
