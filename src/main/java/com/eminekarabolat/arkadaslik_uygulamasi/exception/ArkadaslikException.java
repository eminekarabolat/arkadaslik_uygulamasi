package com.eminekarabolat.arkadaslik_uygulamasi.exception;

import lombok.Getter;

@Getter
public class ArkadaslikException extends RuntimeException{
	private ErrorType errorType;
	public ArkadaslikException(ErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}
}