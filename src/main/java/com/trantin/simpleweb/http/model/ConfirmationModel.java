package com.trantin.simpleweb.http.model;

public class ConfirmationModel {

    private String type;

    private String confirmation_url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConfirmation_url() {
        return confirmation_url;
    }

    public void setConfirmation_url(String confirmation_url) {
        this.confirmation_url = confirmation_url;
    }
}
