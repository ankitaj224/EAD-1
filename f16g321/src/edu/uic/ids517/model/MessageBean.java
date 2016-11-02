package edu.uic.ids517.model;


public class MessageBean {
private String errorMessage;

public MessageBean() {
	super();
	errorMessage="";
}

public String getErrorMessage() {
	return errorMessage;
}

public void setErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
}

}
