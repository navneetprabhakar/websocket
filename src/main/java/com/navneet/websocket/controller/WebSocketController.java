package com.navneet.websocket.controller;

import com.navneet.websocket.model.MessageRequest;
import com.navneet.websocket.model.SocketResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class WebSocketController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public SocketResponse send(MessageRequest request) throws Exception{
        Thread.sleep(500);
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        return new SocketResponse(time+ " -"+HtmlUtils.htmlEscape(request.getName()) +": "+ HtmlUtils.htmlEscape(request.getMessage()));
    }

}
