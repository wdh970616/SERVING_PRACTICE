package practicespring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import practicespring.dto.ResponseDTO;

@Service
@Slf4j
public class WebClientService {

    private final WebClient webClient;

    private final String FAST_API_SERVER_URL = "http://localhost:8000";


    public WebClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl(FAST_API_SERVER_URL)
                .build();
    }

    public ResponseDTO handwritingToText(MultipartFile handWriting) {
        ResponseDTO responseDTO = webClient.post()
                .uri("/handwriting")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData("handwriting", handWriting.getResource()))
                .retrieve()
                .bodyToMono(ResponseDTO.class)
                .doOnSuccess(response -> log.info("변환 완료"))
                .doOnError(error -> log.error("변환 API 호출 에러"))
                .block();

        return responseDTO;
    }
}
