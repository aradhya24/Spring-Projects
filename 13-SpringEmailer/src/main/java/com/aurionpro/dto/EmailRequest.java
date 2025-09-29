package com.aurionpro.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public class EmailRequest {


@NotBlank
private String subject;


@NotBlank
private String body;


@NotBlank
@Email
private String to;


public EmailRequest() {}


public String getSubject() { return subject; }
public void setSubject(String subject) { this.subject = subject; }


public String getBody() { return body; }
public void setBody(String body) { this.body = body; }


public String getTo() { return to; }
public void setTo(String to) { this.to = to; }
}