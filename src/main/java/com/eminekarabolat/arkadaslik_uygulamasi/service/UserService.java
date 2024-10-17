package com.eminekarabolat.arkadaslik_uygulamasi.service;

import com.eminekarabolat.arkadaslik_uygulamasi.dto.request.RegisterRequestDto;
import com.eminekarabolat.arkadaslik_uygulamasi.dto.request.UpdateUserProfileDto;
import com.eminekarabolat.arkadaslik_uygulamasi.dto.response.BaseResponse;
import com.eminekarabolat.arkadaslik_uygulamasi.entity.Gender;
import com.eminekarabolat.arkadaslik_uygulamasi.entity.User;
import com.eminekarabolat.arkadaslik_uygulamasi.mapper.UserMapper;
import com.eminekarabolat.arkadaslik_uygulamasi.repository.UserRepository;
import com.eminekarabolat.arkadaslik_uygulamasi.views.VwUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * DİKKATTT!!!!!!!!!!! spring boot bir sınıfın referansını oluşturmak için mutlaka o sınıfın işaretli olmasını talep
 * eder, yani bir sınıftan nesne türetilecek mi bilmek ister. Bunu yapabilmek için anatasyon kullanırız. Böylece spring
 * componentScan sistemi ile işaretli sınıfları Bean olarak işaretler ve çalışma zamanında bu sınıflardan birer bean
 * yaratır. servis sınıfları için @Service anatasyonu kullanılır.
 */


@Service
public class UserService {
	/**
	 * Servis temel işlevleri yerine getirir. Gerekli gördüğü bilgileri repositorye aktarır.
	 * Bu nedenle servis sınıfında repository nesnelerine ihtiyaç duyulur.
	 * Burada repository değişkeni tanımlanmalı cve nesnesi oluşturulmalıdır.
	 */

//	@Autowired
//	private UserRepository userRepository;
//
	
	/**
	 * Dependency Injection / DI Spring boot ta bağımlılıkları sağlamak injekte etmek için farklı yöntemler kullanılır.
	 * en bilindikleri, 1- @Autowired anatasyonu ile kullanım bağlılık atfedilen değişkeninin üzerine eklenir ise,
	 * spring boot ApplicationContext içerisinde ilgili sınıftan oluşturulmuş olan nesne referansını ilgili değişkene
	 * atar.
	 * <p>
	 * 2-  Constructor Injection ile kullanımı Spring boot bir sınıfın oluşturulabilmesi içişn gerekli olan Constructor
	 * metodlarını incelerken eğer metod nesne talep ediyor ise, ApplicationContext içindeki nesnelerden bunu sağlamaya
	 * çalışır ve nesne referanslarını bu constructor a parametre olarak geçer.
	 */
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void addUser(String userName, String avatar, Gender gender) {
		User user = User.builder().avatar(avatar).gender(gender).name(userName).build();
		userRepository.save(user);
	}
	
	public List<User> getAllUser() {
		return userRepository.findAll();
	}
	
	public List<User> kadinKullaniciListesi() {
		return userRepository.findAllByGender(Gender.FEMALE);
	}
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public List<User> findUsersByUsername(String username) {
		return userRepository.findAllByUserNameContaining(username);
	}
	
	public User findUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public User register(RegisterRequestDto dto) {
		return userRepository.save(User.builder()
		                        .userName(dto.getUserName())
		                        .email(dto.getEmail())
		                        .password(dto.getPassword())
		                        .build());
		
	}
	
	public List<VwUser> getAllUserList() {
		return userRepository.tumKullanicilariListele();
	}
	
	
	//Aranılan kullanıcı id'sini db'de sorgular kayıt var ise true yok ise false döner.
	public boolean existById(Long userId) {
		//return userRepository.findById(userId).isPresent();
		return userRepository.existsById(userId);
	}
	
	//Aranılan kullanıcı idsi ile optional olarak kullanıcıyı döner
	public Optional<User> findOptionalById(Long id) {
		return userRepository.findById(id);
	}
	
	
	public User findById(Long followId) {
		return userRepository.findById(followId).orElse(null);
	}
	
	
	public List<User> findAllByIdIn(List<Long> followingIds) {
		return userRepository.findAllById(followingIds);
	}
	
	public void updateUserProfile(UpdateUserProfileDto dto) {
	//	User user = User.builder().name("murat").build();// kesinlikle yeni kayıt yapılır.
	//	User user1 = User.builder().id(3L).name("murat").build();//Eğer 3 id var ise üzerine yazar.
	//	User user2 = User.builder().id(3L).name("murat").build();// önceki kaydın üzerine yazar.
		/**
		 * Burada dto -> user'a dönüşüm yaptık ve elle oluşturduk.
		 * peki 50 adet alan olsaydı 50 alan için böyle dönüşüm mü yapacaktık.
		 */
//		User user = User.builder()
//				.id(dto.getId())
//				.name(dto.getName())
//				.userName(dto.getUserName())
//				.password(dto.getPassword())
//				.avatar(dto.getAvatar())
//				.email(dto.getEmail())
//				.phone(dto.getPhone())
//				.age(dto.getAge())
//				.build();
//		userRepository.save(user);
		
		User user = UserMapper.INSTANCE.fromUpdateProfileDto(dto);
		userRepository.save(user);
	}
}