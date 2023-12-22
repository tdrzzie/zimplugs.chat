package api.zimplugs.chat.Controller;

import api.zimplugs.chat.Util.chatUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class chatController {

    @Value("${gemini.prokey}")
    private String prokey;

    @PostMapping("/sendMessage")
    public String sendMessage(String text)
    {
        String res= chatUtil.getGeminiBody(text,prokey);
        if(res.indexOf("candidates")<=0){
            return res;
        }
        return chatUtil.getOutput(res);
    }

}