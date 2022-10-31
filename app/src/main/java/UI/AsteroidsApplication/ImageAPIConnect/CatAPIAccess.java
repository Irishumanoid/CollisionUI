package UI.AsteroidsApplication.ImageAPIConnect;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CatAPIAccess {
    private static String APIKey = "https://api.thecatapi.com/v1/images/search?limit=30&api_key=live_AroJ9AKFu1yuBlerfvXSjypGO0NWbl5dGYMQ3l1hzdl4hJo5mPNIn7WoTUricaFP";

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(APIKey)).header("accept", "application/json").build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
        .thenApply(HttpResponse::body)
        .thenAccept(System.out::println)
        .join();
     }
}
