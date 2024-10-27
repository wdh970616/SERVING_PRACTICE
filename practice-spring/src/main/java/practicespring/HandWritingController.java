package practicespring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import practicespring.dto.ResponseDTO;

@RestController
@RequestMapping("/handwriting")
@RequiredArgsConstructor
@Slf4j
public class HandWritingController {

    private final WebClientService webClientService;

    @GetMapping("/test")
    public String test() {
        log.info("/test -> get 요청 테스트 들어옴");
        return "test success";
    }

    @PostMapping(value = "/webclient",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDTO handwritingByWebClient(@RequestParam("handwriting") MultipartFile handwriting) {

        log.info("손글씨 변환 Controller 요청 들어옴");
        log.info("handwriting: {}", handwriting.getOriginalFilename());

        return webClientService.handwritingToText(handwriting);
    }
}
