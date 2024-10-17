package com.eminekarabolat.arkadaslik_uygulamasi.dto.request;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RegisterRequestDto {
	@NotNull(message = "Kullanıcı adı boş geçilemez,")
	@Size(min = 3, max = 64, message = "Kullanıcı adı , 3-64 arasında karakter kısıtlamasına sahiptir.")
	String userName;
	@NotNull
	@NotEmpty
	@Size(min = 8,max = 64)
	@Pattern(message = "Şifreniz en 8 en fazla 64 karakter olmalı, Şifrenizde en az bir büyük bir küçük harf ve özel " +
			"karaktere olmalıdır.", regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$")
	String password;
	@NotNull
	@NotEmpty
	String rePassword;
	@Email(message = "Lütfen geçerli bir e-posta adresi giriniz.")
	String email;
}