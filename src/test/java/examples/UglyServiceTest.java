package examples;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UglyServiceTest {

    @Mock
    private HttpClient httpClient;
    @Mock
    private Logger logger;
    @Mock
    private BeanRepo repo;
    @Mock
    private Gson jsonParser;
    @Mock
    private HttpResponse<String> response;
    @Captor
    private ArgumentCaptor<HttpRequest> requestArgumentCaptor;
    @Captor
    private ArgumentCaptor<List<CoolBean>> beanCaptor;
    @InjectMocks
    private UglyService uglyService;

    @Test
    void canSendRequest() throws Exception {
        var beans = List.of(new CoolBean("Fran"), new CoolBean("Tim"));
        when(repo.getAllBeans()).thenReturn(beans);
        when(jsonParser.toJson(beans)).thenReturn("[{\"name\": \"Fran\"}, {\"name\": \"Tim\"}]");
        when(httpClient.send(requestArgumentCaptor.capture(), eq(HttpResponse.BodyHandlers.ofString())))
                .thenReturn(response);
        when(response.body()).thenReturn("[{\"name\": \"Fran\"}, {\"name\": \"Tim\"}]");
        when(jsonParser.fromJson(anyString(), any(TypeToken.class))).thenReturn(beans);

        uglyService.sendBeans();

        var request = requestArgumentCaptor.getValue();
        assertEquals("http://localhost:4567", request.uri().toString());
        verify(repo).saveBeans(beans);
    }
}