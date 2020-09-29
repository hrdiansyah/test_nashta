package com.learning.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class notFoundException extends Exception {
	
	public notFoundException(String pesan) {
		// TODO Auto-generated constructor stub
		super(pesan);
	}
}
