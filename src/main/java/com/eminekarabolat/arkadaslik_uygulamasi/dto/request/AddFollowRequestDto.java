package com.eminekarabolat.arkadaslik_uygulamasi.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddFollowRequestDto {
	/**
	 * String userName;
	 * kullanıcı tarafından bu değerin hiç gönderilmemesi
	 * body{
	 *     //burada hiçbir keyword yok ise
	 * }
	 * NotNull -> userName = null
	 * body{
	 *     userName = ""
	 * }
	 * NotEmpty -> userName = ""
	 */
	@NotNull
//	@NotEmpty
	Long userId;
	@NotNull
//	@NotEmpty
	Long followingId;

}