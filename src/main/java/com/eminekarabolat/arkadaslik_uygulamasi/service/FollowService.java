package com.eminekarabolat.arkadaslik_uygulamasi.service;

import com.eminekarabolat.arkadaslik_uygulamasi.dto.request.AddFollowRequestDto;
import com.eminekarabolat.arkadaslik_uygulamasi.dto.response.BaseResponse;
import com.eminekarabolat.arkadaslik_uygulamasi.entity.Follow;
import com.eminekarabolat.arkadaslik_uygulamasi.entity.User;
import com.eminekarabolat.arkadaslik_uygulamasi.exception.ArkadaslikException;
import com.eminekarabolat.arkadaslik_uygulamasi.exception.ErrorType;
import com.eminekarabolat.arkadaslik_uygulamasi.exception.GlobalExceptionHandler;
import com.eminekarabolat.arkadaslik_uygulamasi.repository.FollowRepository;
import com.eminekarabolat.arkadaslik_uygulamasi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowService {
	
	
	private final FollowRepository followRepository;
	private final UserService userService;
	
	
	public FollowService(FollowRepository followRepository, UserService userService) {
		this.followRepository = followRepository;
		this.userService = userService;
		
	}
	
	
	public Follow addFollow(AddFollowRequestDto dto) {
		
		if (!userService.existById(dto.getUserId()) || !userService.existById(dto.getFollowingId()))
			throw new ArkadaslikException(ErrorType.FOLLOW_USERID_FOLLOWINGID_NOTFOUND);
		
		return followRepository.save(Follow.builder()
		                                   .followId(dto.getFollowingId())
		                                   .userId(dto.getUserId())
		                                   .build());
	}
	
	/**
	 * -> Bir kullanıcının takipte olduğu kullanıcıların listesi
	 * follow tablosundan bulunur.
	 * select *  from tblfollow where userId=?
	 * -> bulduğunuz follow listesinin içerisindeki takip edilen kişilerin idlerini alıp user tablosuna sormak
	 * -> user tablosundan bulunan kullanıcıları geri dönmek üzere bir listenin içerisine koymak
	 */
	public List<User> getMyFollowing(Long userId) {
		/**
		 * Bu kullanımda DB den follow tablosuna ait tüm kolonları ihtiya eden dataları çekeriz
		 * bu nedenle ihtiyacımız olmayan %66'lık bir veriyi gereksiz çekmek durumunda kalırız.
		 */
		// List<Follow> takipciler = followRepository.findAllByUserId(userId);
		/**
		 * Bu kullanım daha efektif ve sadece ihtiyacımız olan dataları getirir.
		 */
		List<Long> followingIds = followRepository.findAllFollowIdByUserId(userId);
		/**
		 * Buradaki kullanımda 2 noktada sorunludur.
		 * 1.kısım ihtiyacımız olmadığı halde çektiğimiz follow bilgisinin içinde sadece id bilgisi almak
		 * için tüm datayı for ile dönerek gereksiz bir işlem gücü sarfederiz.
		 * 2. her bir kayıt için tekrar tekrar DB sorgusu atıyoruz. yani userservis üzerinde her döngüde kullanıcı
		 * sorguluyoruz.
		 * ÖRNEK HESAP
		 * 10.000 kullanıcı
		 * Long -> 8 byte
		 * -> follow list[id,userid,followid] -> 24byte * 10.000 -> 240kb
		 * -> long list -> 80 kb
		 * ---------------------
		 * Zaman Maliyeti;
		 * 10000 kayıt için for döngüsü 1 sn ye işlem yapsın. ancak her bir döngüde kullanıcı bilgisi
		 * sorgulamak zorunda. Bu nedenle her döngü DBye istek atıp bilgi çekmeli.
		 *1 sorgu işlemi 1 ms sürsün
		 * 10k -> 10 sn
		 */
//		List<User> takipciListesi = new ArrayList<>();
//
//		 takipciler.forEach(t->{
//			 System.out.println(t.getFollowId()); // takip ettiklerinin id'si
//			 User user = userService.findById(t.getFollowId());
//			 takipciListesi.add(user);
//		 });
//
		/**
		 * Çözüm:
		 * tüm kullanıcı id lerini tek seferde DB de sorgulatmak doğru olacaktır.
		 * select* from tbluser where id in(54,3562,435,35)
		 */
		List<User> followingUserList = userService.findAllByIdIn(followingIds);
		return followingUserList;
	}
}