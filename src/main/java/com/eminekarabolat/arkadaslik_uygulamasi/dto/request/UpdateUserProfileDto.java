package com.eminekarabolat.arkadaslik_uygulamasi.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UpdateUserProfileDto {
	@NotNull
	Long id;
	@Size(min = 2, max = 100)
	String name;
	String avatar;
	@Size(min = 2, max = 64)
	String userName;
	@Pattern(message = "Şifreniz en 8 en fazla 64 karakter olmalı, Şifrenizde en az bir büyük bir küçük harf ve özel " +
			"karaktere olmalıdır.", regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$")
	String password;
	String email;
	String phone;
	Integer age;
}