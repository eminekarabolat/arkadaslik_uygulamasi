package com.eminekarabolat.arkadaslik_uygulamasi.controller;

import static com.eminekarabolat.arkadaslik_uygulamasi.constants.RestApis.*;

import com.eminekarabolat.arkadaslik_uygulamasi.constants.RestApis;
import com.eminekarabolat.arkadaslik_uygulamasi.dto.request.RegisterRequestDto;
import com.eminekarabolat.arkadaslik_uygulamasi.dto.request.UpdateUserProfileDto;
import com.eminekarabolat.arkadaslik_uygulamasi.dto.response.BaseResponse;
import com.eminekarabolat.arkadaslik_uygulamasi.entity.Gender;
import com.eminekarabolat.arkadaslik_uygulamasi.entity.User;
import com.eminekarabolat.arkadaslik_uygulamasi.service.UserService;
import com.eminekarabolat.arkadaslik_uygulamasi.views.VwUser;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller sınıfları son kullanıcı ile iletişime geçiş için gelen isterleri handle edecek sınıflardır. Burada web
 * için gerekli erişimler tanımlanır ve istekler işlenir.
 *
 * @Controller -> web MCV için kullanılır
 * @RestController -> restApı kullanımları için sınıftan bean yaratılacağını bildirmek için kullanılır. bir diğer nokta
 * ise, bu sınıf bir web iletişim sınıfı olduğu için internet ya da intranet üzerinden gelen isteklerin yakalanabilmesi
 * için PATH tanımıu yapılması gereklidir. İlgili sınıf için tanımlama;
 * @RequestMapping("/[PATH_NAME]") şeklinde yapılır. bu işlem şunu ifade eder; http://[IP Adres ya da
 * DomainName]:[PORT]/[PATH_NAME] userController için erişim; http://localhost:9090/user
 */

@RestController
@RequestMapping(USER)
public class UserController {
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * http://localhost:9090/user/add-user
	 */
	@GetMapping(ADDUSER)
	public String addUser() {
		userService.addUser("eminekarabolat", "http://picsum.photos/100/100", Gender.FEMALE);
		userService.addUser("deniz", "http://picsum.photos/100/100", Gender.MALE);
		userService.addUser("bahar", "http://picsum.photos/100/100", Gender.FEMALE);
		userService.addUser("ahmet", "http://picsum.photos/100/100", Gender.MALE);
		return "Kayıtlar başarıyla eklendi.";
	}
	
	/**
	 * http://localhost:9090/user/get-all-users
	 */
	@GetMapping(GETALLUSERS)
	public List<User> getAllUsers() {
		return userService.getAllUser();
	}
	
	//http://localhost:9090/user/get-all-users-by-f
	@GetMapping(GETALLUSERSF)
	public List<User> getAllUsersByFemale() {
		return userService.kadinKullaniciListesi();
	}
	
	@PostMapping(LOGIN)
	public void doLogin(String userName, String password) {
		System.out.println("Kullanıcı adı.....:" + userName);
		System.out.println("Şifre.............:" + password);
	}
	
	/**
	 * create-user adında bir metod oluşturun. userName,password,email,name method kullanıcı oluşturma işlevini
	 * yapacak.
	 */
	@PostMapping(CREATEUSER)
	public User register(String userName, String password, String email, String name) {
		User user = (User.builder().userName(userName).password(password).email(email).name(name).build());
		return userService.createUser(user);
		
	}
	
	/**
	 * 1-kullanıcı adını (bir kısmı da olabilir örn: ahm) alarak liste dönen bir metod yazınız (GET) 2- id'si verilen
	 * kullanıcıyı dönen metod
	 */
	
	@GetMapping(USERNAME)
	public List<User> getUsersByUserName(String userName) {
		return userService.findUsersByUsername(userName);
	}
	
	@GetMapping(USERID)
	public User getUserById(@RequestParam Long id) {
		return userService.findUserById(id);
	}
	
	/**
	 * Data Transfer Object - DTO Bilgi alırken ya da gönderirken -> Request-Response Bilgileri alırken kısıtlı tutmak
	 * ve ihtiyaca göre talep etmek zorundayız. Gelen verilerin tutarlılığını Controller katmanında incelemek
	 * zorundayız. Kullanıcıyta dönüş yapacağımız bilgileri kısıtlamak zorundayız zira, güvenlik için gizlenmesi
	 * gereken
	 * bilgiler vardır. Ayrıca ne kadar çok veri gönderimi o kadar kaynak kullanımı demektir.
	 */
	
	@PostMapping(REGISTER)
	public ResponseEntity<User> register(@RequestBody @Valid RegisterRequestDto dto) {
		if (!dto.getPassword().equals(dto.getRePassword()))
			return ResponseEntity.badRequest().body(null);
		User user = userService.register(dto);
		return ResponseEntity.ok(user);
		
	}
	
	@GetMapping("/get-all-user-list")
	public ResponseEntity<List<VwUser>> getAllUserList() {
		return ResponseEntity.ok(userService.getAllUserList());
	}
	
	@GetMapping("/get-all-view-users")
	public ResponseEntity<BaseResponse<List<VwUser>>> getAllViewUser() {
		return ResponseEntity.ok(BaseResponse.<List<VwUser>>builder()
		                                     .success(true)
		                                     .code(200)
		                                     .message("Kullanıcı listesi başarılı bir şekilde getirildi.")
		                                     .data(userService.getAllUserList()).build());
	}
	
	
	/**
	 * HTTP Request tiplerini araştırarak ne işe yaradıklarını terimsel olarak ifade ediniz.
	 * Idempotent, Idempotency bu kavramlar nedir?
	 *
	 */

	@PutMapping("/update-user-profile")
	public ResponseEntity<BaseResponse<Boolean>> updateUserProfile(@RequestBody @Valid UpdateUserProfileDto dto){
		userService.updateUserProfile(dto);
		return ResponseEntity.ok(BaseResponse.<Boolean>builder()
		                                     .success(true)
		                                     .code(200)
		                                     .message("Güncelleme başarılı.")
		                                     .data(true).build());
		
	}
	
}