package com.eminekarabolat.arkadaslik_uygulamasi.service;

import com.eminekarabolat.arkadaslik_uygulamasi.entity.Gender;
import com.eminekarabolat.arkadaslik_uygulamasi.entity.User;
import com.eminekarabolat.arkadaslik_uygulamasi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DİKKATTT!!!!!!!!!!! spring boot bir sınıfın referansını oluşturmak için mutlaka
 * o sınıfın işaretli olmasını talep eder, yani bir sınıftan nesne türetilecek mi bilmek ister.
 * Bunu yapabilmek için anatasyon kullanırız. Böylece spring
 * componentScan sistemi ile işaretli sınıfları Bean olarak işaretler ve çalışma zamanında bu sınıflardan birer bean yaratır.
 * servis sınıfları için @Service anatasyonu kullanılır.
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
	 * Dependency Injection / DI
	 * Spring boot ta bağımlılıkları sağlamak injekte etmek için farklı yöntemler
	 * kullanılır. en bilindikleri,
	 * 1- @Autowired anatasyonu ile kullanım bağlılık atfedilen değişkeninin üzerine eklenir ise, spring boot ApplicationContext
	 * içerisinde ilgili sınıftan oluşturulmuş olan nesne referansını ilgili değişkene atar.
	 *
	 * 2-  Constructor Injection ile kullanımı
	 * Spring boot bir sınıfın oluşturulabilmesi içişn gerekli olan Constructor metodlarını incelerken eğer metod nesne talep ediyor ise, ApplicationContext
	 * içindeki nesnelerden bunu sağlamaya çalışır ve nesne referanslarını bu constructor a
	 * parametre olarak geçer.

	 */
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	public void addUser(String userName, String avatar, Gender gender) {
		User user = User.builder().avatar(avatar).gender(gender).name(userName).build();
		userRepository.save(user);
	}

}