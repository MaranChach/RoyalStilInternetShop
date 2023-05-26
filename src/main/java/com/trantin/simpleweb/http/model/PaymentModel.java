package com.trantin.simpleweb.http.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class PaymentModel {

    private String id;

    private String status;

    private AmountModel amount;

    private String description;

    private RecipientModel recipient;

    private String created_at;

    private ConfirmationModel confirmation;

    private String test;

    private String paid;

    private String refundable;

    @JsonIgnore
    private Object metadata;

    @JsonIgnore
    private Object cancellation_details;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AmountModel getAmount() {
        return amount;
    }

    public void setAmount(AmountModel amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public ConfirmationModel getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(ConfirmationModel confirmation) {
        this.confirmation = confirmation;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getRefundable() {
        return refundable;
    }

    public void setRefundable(String refundable) {
        this.refundable = refundable;
    }

    public RecipientModel getRecipient() {
        return recipient;
    }

    public void setRecipient(RecipientModel recipient) {
        this.recipient = recipient;
    }

    public Object getMetadata() {
        return metadata;
    }

    public void setMetadata(Object metadata) {
        this.metadata = metadata;
    }

    public Object getCancellation_details() {
        return cancellation_details;
    }

    public void setCancellation_details(Object cancellation_details) {
        this.cancellation_details = cancellation_details;
    }
}
