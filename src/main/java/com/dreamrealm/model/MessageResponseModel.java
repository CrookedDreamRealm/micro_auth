package com.dreamrealm.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponseModel {
    private String message;

    public MessageResponseModel(String message) {
        this.message = message;
    }
}
