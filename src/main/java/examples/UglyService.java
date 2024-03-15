package examples;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UglyService {
    @Inject
    private HttpClient httpClient;
    @Inject
    private Logger logger;
    @Inject
    private BeanRepo repo;
    @Inject
    private Gson jsonParser;

    public int sendBeans() {
        try {
            var asJson = jsonParser.toJson(repo.getAllBeans());
            logger.info("sending beans");
            var request = HttpRequest.newBuilder()
                    .uri(URI.create("http ://localhost:4567"))
                    .PUT(HttpRequest.BodyPublishers.ofString(asJson))
                    .build();
            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            var processed = jsonParser.fromJson(response.body(), new TypeToken<List<CoolBean>>(){});
            repo.saveBeans(processed);
            return response.statusCode();
        }catch (Exception e) {
            logger.log(Level.SEVERE, "oops", e);
            return 500;
        }
    }
}
