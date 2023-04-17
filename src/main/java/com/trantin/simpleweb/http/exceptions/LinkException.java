package com.trantin.simpleweb.http.exceptions;

public class LinkException extends Exception{

    private static final String message = "Невозможно удалить, на объект сохранены ссылки";

    public LinkException() {
        super(message);
    }
}
