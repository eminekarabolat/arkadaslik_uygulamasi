package com.eminekarabolat.arkadaslik_uygulamasi.dto.response;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BaseResponse<T> {
	Boolean success;
	String message;
	Integer code;
	T data;
	
}