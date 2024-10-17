package com.eminekarabolat.arkadaslik_uygulamasi.exception;

import lombok.*;

import java.util.List;


@Builder
@Getter
@Setter
public class ErrorMessage {
	int code;
	String message;
	Boolean success;
	List<String> fields;

}