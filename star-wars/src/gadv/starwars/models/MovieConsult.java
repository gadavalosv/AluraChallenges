package gadv.starwars.models;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MovieConsult {
    public static Movie searchMovie(int movieId) {
        String requestUrl = "https://swapi.dev/api/films/" + movieId + "/"; //URLEncoder.encode(movieToSearch, StandardCharsets.UTF_8)
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(requestUrl))
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            return new Gson().fromJson(json, Movie.class);
        } catch (Exception e) {//(IOException | InterruptedException e) {
            throw new RuntimeException("Pelicula no encontrada");
        }
    }
}
