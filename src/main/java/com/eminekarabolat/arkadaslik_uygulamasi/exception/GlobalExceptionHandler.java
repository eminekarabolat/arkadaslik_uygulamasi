package com.eminekarabolat.arkadaslik_uygulamasi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j // loglamayı sağlıyor. konsolda ınfo warning error gibi ifadeleri vermeye yarıyor.
public class GlobalExceptionHandler {
	
	 //Tanımlaması yapılmayan diğer tüm hataları yakalamak için RunTimeException yakalayın.
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorMessage> runtimeExceptionHandler( RuntimeException exception) {
//		log.error("Beklenmeyen hata.........:" + exception.getMessage());
//		return new ResponseEntity<>(ErrorMessage.builder()
//		                                        .success(false)
//		                                        .message("Sunucuda beklenmeyen hata oluştu...:"+ exception.getMessage())
//		                                        .code(500)
//		                                        .build(),HttpStatus.INTERNAL_SERVER_ERROR );
		return createResponseEntity(ErrorType.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR,null);
	}
	
	@ExceptionHandler(ArkadaslikException.class)//Oluşan hataları yakalar,metoda parametre olarak geçer
	@ResponseBody
	public ResponseEntity<ErrorMessage> arkadaslikExceptionHandler(ArkadaslikException exception){
		
		//ResponseEntity.ok.ok.build; -> 200 Ok. Success her şey yolunda.
		//ResponseEntity.badRequest(); -> 400 BadRequest yani gelen istek hatalı.
		//ResponseEntity.internalServerError(); -> 500 sunucu tarafında bir hata oluştu.

//		return new ResponseEntity<>(BaseResponse.<Boolean>builder()
//				                            .success(false)
//				                            .code(400)
//				                            .message(exception.getMessage())
//				                            .data(false).build(), HttpStatus.BAD_REQUEST);
//
//		return new ResponseEntity<>(ErrorMessage.builder()
//				                            .code(exception.getErrorType().getCode())
//				                            .message(exception.getErrorType().getMessage())
//				                            .success(false)
//		                                        .build(), exception.getErrorType().getHttpStatus());
		
		return createResponseEntity(exception.getErrorType(),exception.getErrorType().getHttpStatus(),null);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	//Hatanın kaynaklandığı detayı bize döndüren metodtur.
	public ResponseEntity<ErrorMessage> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){
		List<String> fieldErrors = new ArrayList<>();
		exception.getBindingResult().getFieldErrors()
				.forEach(f ->{
					         //f.getField() -> hata fırlatan nesnenin değişken adı : örn email
					//f.getDefaultMessage() -> hataya ait detay bilgisi örn: geçerli bir email giriniz.
					fieldErrors.add("Değişken adı...:" + f.getField() + " -Hata Detayı....: " + f.getDefaultMessage());
				         }
						);
//		return new ResponseEntity<>(ErrorMessage.builder()
//				                            .code(400)
//				                            .message("Girilen parametreler geçersizdir. Lütfen kontrol ederek tekrar deneyiniz.")
//				                            .fields(fieldErrors)
//		                                        .build(), HttpStatus.BAD_REQUEST);
		
		return createResponseEntity(ErrorType.VALIDATION_ERROR,HttpStatus.BAD_REQUEST,fieldErrors);
	}
	
	public ResponseEntity<ErrorMessage> createResponseEntity(ErrorType errorType, HttpStatus httpStatus,List<String> fields){
		log.error("TÜM HATALARIN GEÇTİĞİ NOKTA.....:"+ errorType.getMessage()+ fields);
		return  new ResponseEntity<>(ErrorMessage.builder()
		                                         .fields(fields)
		                                         .success(false)
		                                         .message(errorType.getMessage())
		                                         .code(errorType.getCode())
		                                         .build(),httpStatus);
	}
}