package br.saasmania.economizae.transcription.presentation;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api")
public class ChatModelController {
    private static final Logger log =
        LoggerFactory.getLogger(ChatModelController.class);

    private final OpenAiChatModel openAiChatModel;

    public ChatModelController(OpenAiChatModel openAiChatModel){
        this.openAiChatModel = openAiChatModel;
    }

    @GetMapping("/chat-model")
    String chat(@RequestParam String prompt){
        log.info("Received prompt: {}", prompt);

        var response = this.openAiChatModel.call(prompt);

        log.info("Response received");

        return response;
    }

    @PostMapping("/two-sum")
    public int[] twoSum(@RequestBody TwoSumRequest request) {
        int[] nums = request.nums();
        int target = request.target();
        int[] ret = {0,0};

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        
        return ret;
    }
}

