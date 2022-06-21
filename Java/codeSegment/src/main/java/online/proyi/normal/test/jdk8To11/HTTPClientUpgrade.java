package online.proyi.normal.test.jdk8To11;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

/**
 * 标准HTTP Client 升级
 *
 * 完全支持异步非阻塞
 * 包名由 jdk.incubator.http 改为 java.net.http
 * 该 API 通过 CompleteableFutures 提供非阻塞请求和响应语义，可以联合使用以触发相应的动作
 * 提供了对 HTTP/2 等业界前沿标准的支持，同时也向下兼容 HTTP/1.1，精简而又友好的 API 接口
 * 是 Java 在 Reactive-Stream 方面的第一个生产实践，其中广泛使用了 Java Flow API
 */
public class HTTPClientUpgrade {
    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://openjdk.java.net/"))
                .build();
        httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8))
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }
}
