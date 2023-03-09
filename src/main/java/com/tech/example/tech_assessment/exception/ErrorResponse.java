package com.tech.example.tech_assessment.exception;

public class ErrorResponse {

	private int httpStatusCode;
	private String message;
	private String translationKey;
	
	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ErrorResponse(int httpStatusCode, String message, String translationKey) {
		super();
		this.httpStatusCode = httpStatusCode;
		this.message = message;
		this.translationKey = translationKey;
	}
	public int getHttpStatusCode() {
		return httpStatusCode;
	}
	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTranslationKey() {
		return translationKey;
	}
	public void setTranslationKey(String translationKey) {
		this.translationKey = translationKey;
	}
	@Override
	public String toString() {
		return "ErrorResponse [httpStatusCode=" + httpStatusCode + ", message=" + message + ", translationKey="
				+ translationKey + "]";
	}
}
