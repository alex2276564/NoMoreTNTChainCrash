package uz.alex2276564.nomoretntchaincrash.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class HttpUtils {

    private static final Duration DEFAULT_REQUEST_TIMEOUT = Duration.ofSeconds(10);
    private static final Duration CONNECT_TIMEOUT = Duration.ofSeconds(5);

    private final HttpClient client;

    public HttpUtils() {
        this.client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(CONNECT_TIMEOUT)
                .build();
    }

    public record HttpResponse(int statusCode, JSONObject jsonBody) {
    }

    public HttpResponse getJson(String urlString, String userAgent) throws IOException, InterruptedException {
        return getJson(urlString, userAgent, DEFAULT_REQUEST_TIMEOUT);
    }

    public HttpResponse getJson(String urlString, String userAgent, Duration timeout)
            throws IOException, InterruptedException {

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .timeout(timeout)
                .GET()
                .header("Accept", "application/json");

        if (userAgent != null) {
            builder.header("User-Agent", userAgent);
        }

        java.net.http.HttpResponse<String> httpResponse = client.send(
                builder.build(),
                java.net.http.HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8)
        );

        JSONObject json = parseJsonQuietly(httpResponse.body());
        return new HttpResponse(httpResponse.statusCode(), json);
    }

    public HttpResponse postJson(String urlString, JSONObject body, String userAgent)
            throws IOException, InterruptedException {
        return postJson(urlString, body, userAgent, DEFAULT_REQUEST_TIMEOUT);
    }

    public HttpResponse postJson(String urlString, JSONObject body, String userAgent, Duration timeout)
            throws IOException, InterruptedException {

        String payload = (body == null) ? "" : body.toJSONString();

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .timeout(timeout)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload, StandardCharsets.UTF_8));

        if (userAgent != null) {
            builder.header("User-Agent", userAgent);
        }

        java.net.http.HttpResponse<String> httpResponse = client.send(
                builder.build(),
                java.net.http.HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8)
        );

        JSONObject json = parseJsonQuietly(httpResponse.body());
        return new HttpResponse(httpResponse.statusCode(), json);
    }

    private JSONObject parseJsonQuietly(String body) {
        if (body == null || body.isEmpty()) {
            return new JSONObject();
        }
        try {
            JSONObject obj = JSON.parseObject(body);
            return (obj != null) ? obj : new JSONObject();
        } catch (Exception ignored) {
            return new JSONObject();
        }
    }
}