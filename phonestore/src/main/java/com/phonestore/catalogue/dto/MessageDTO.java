package com.phonestore.catalogue.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An (error) message.
 * classe utilisée pour informer de la reussite ou l'echec d'une insertion ou une modification d'instance
 * modeletelephone ou association ou prestation
 * Cet objet sera donc renvoyer dans une reponse http suite à un appel de l'api Rest
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