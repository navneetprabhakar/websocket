package com.navneet.websocket.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageRequest {

    private String name;
    private String message;
}
