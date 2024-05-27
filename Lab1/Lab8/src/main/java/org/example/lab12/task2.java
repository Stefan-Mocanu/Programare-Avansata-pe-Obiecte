package org.example.lab12;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

public class task2 {
    public static final String URL = "https://jsonplaceholder.typicode.com/posts";
    public static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {
        try{
            int id = new Random().nextInt(100)+1;
            getPostById(id);
            String jsonPostArg = """
                    {
                        "nume": "Stefan",
                        "ocupatie": "student",
                        "anNastere": 2003
                    }
                    """;
            createPost(jsonPostArg);

        }catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void getPostById(int id)throws IOException, InterruptedException{
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(URL + "/" + id))
                .GET()
                .build();
        HttpResponse<String> res = client.send(req,HttpResponse.BodyHandlers.ofString());

        System.out.println("GET response for ID " + id + ":");
        System.out.println(res.body());
    }
    public static void createPost(String json)throws IOException, InterruptedException{
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println("POST req body:");
        System.out.println(json);
        System.out.println("POST res:");
        System.out.println(res);
    }
}
