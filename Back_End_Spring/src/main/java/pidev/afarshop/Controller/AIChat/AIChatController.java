package pidev.afarshop.Controller.AIChat;

import pidev.afarshop.Controller.Chatgbt.CompletionRequest;
import pidev.afarshop.Controller.Chatgbt.CompletionResponse;
import pidev.afarshop.Controller.Chatgbt.OpenAiApiClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AIChatController {

    @Autowired private ObjectMapper jsonMapper;
    @Autowired private OpenAiApiClient client;

    @GetMapping("/chat")
    public ResponseEntity<String> chatWithGpt3(@RequestParam String message) throws Exception {
        var completion = CompletionRequest.defaultWith(message);
        var postBodyJson = jsonMapper.writeValueAsString(completion);
        var responseBody = client.postToOpenAiApi(postBodyJson, OpenAiApiClient.OpenAiService.GPT_3);
        var completionResponse = jsonMapper.readValue(responseBody, CompletionResponse.class);

        var result = completionResponse.firstAnswer();
        return ResponseEntity.ok(result);
    }
}