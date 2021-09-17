package com.example.touristagency.email;

public class EmailMessage {
    private String subject;
    private String toAddress;
    private String bodySubject;

    public EmailMessage(){
        super();
        // TODO Auto-generated constructor stub
    }

    public EmailMessage(String subject, String toAddress, String bodySubject){
        super();
        this.subject = subject;
        this.toAddress = toAddress;
        this.bodySubject = bodySubject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getBodySubject() {
        return bodySubject;
    }

    public void setBodySubject(String bodySubject) {
        this.bodySubject = bodySubject;
    }
}
