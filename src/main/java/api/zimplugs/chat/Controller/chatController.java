package api.zimplugs.chat.Controller;

import api.zimplugs.chat.Util.chatUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class chatController {
    @Value("${gemini.prokey}")
    private String prokey;
    @PostMapping("/sendMessage")
    @ResponseBody

    public ResponseEntity<Map<String, String>> sendMessage(@RequestBody String message) {

        // TODO: Send message to Gemini Pro API in the future (when available)
        String res= chatUtil.getGeminiBody(message,prokey);

        String aiResponse = chatUtil.getOutput(res);
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("response", aiResponse);
        return ResponseEntity.ok(responseMap);
    }
}
