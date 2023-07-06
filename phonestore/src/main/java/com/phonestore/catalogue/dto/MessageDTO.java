package com.phonestore.catalogue.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An (error) message.
 */

public class MessageDTO {
    private String message;

    @JsonCreator
    public MessageDTO(
        @JsonProperty("message") String message
    ) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
}