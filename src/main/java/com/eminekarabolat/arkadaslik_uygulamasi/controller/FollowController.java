package com.eminekarabolat.arkadaslik_uygulamasi.controller;

import com.eminekarabolat.arkadaslik_uygulamasi.dto.request.AddFollowRequestDto;
import com.eminekarabolat.arkadaslik_uygulamasi.dto.response.BaseResponse;
import com.eminekarabolat.arkadaslik_uygulamasi.entity.Follow;
import com.eminekarabolat.arkadaslik_uygulamasi.entity.User;
import com.eminekarabolat.arkadaslik_uygulamasi.service.FollowService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.eminekarabolat.arkadaslik_uygulamasi.constants.RestApis.*;

@RestController
@RequestMapping(FOLLOW)
@RequiredArgsConstructor
public class FollowController {
	private final FollowService followService;
	
	
	/**
	 * HTTP Request Types
	 * 1-GET:Bir sunucuya bilgi almak amaçlı gönderilen istek türüdür.
	 * parametre gönderebilirsiniz ancak güvenli değildir. Genellikle herkese açık ve güvenliği sorun olmadığı
	 * işlemlerde kullanılır.
	 *
	 * 2-POST: Yeni bir veri yaratmak amacıyla bilgi gönderiminde yapılan istek türüdür. Bilgiler isteğin
	 * gövdesinde gönderilir ve veriler güvenli bir şekilde iletilir.
	 * header: www.banka.com
	 * body:{
	 *     userName:123456789
	 *     password: FSDRkl4
	 * }
	 *http -> 80 port
	 * https -> 443 port
	 *
	 * s -> security -ssl
	 */
	@PostMapping(ADDFOLLOW)
	public ResponseEntity<BaseResponse<Boolean>> addFollow(@RequestBody @Valid AddFollowRequestDto dto) {
		followService.addFollow(dto);
		return ResponseEntity.ok(
				BaseResponse.<Boolean>builder()
						.data(true)
						.message("takip işlemi başarı ile tamamlandı")
						.code(200)
						.success(true)
						.build()
		);
	}
	
	/**
	 * A kişisinin takip ettiği kullanıcıların listesi
	 * @param userId
	 * @return
	 */
	
	@GetMapping("/get-my-following")
	public ResponseEntity<BaseResponse<List<User>>> getMyFollowing (Long userId){
	List<User> myFollowing = followService.getMyFollowing(userId);
		return null;
	}
	
	
}