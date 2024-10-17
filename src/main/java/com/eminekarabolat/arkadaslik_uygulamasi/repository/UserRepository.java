package com.eminekarabolat.arkadaslik_uygulamasi.repository;

import com.eminekarabolat.arkadaslik_uygulamasi.entity.Gender;
import com.eminekarabolat.arkadaslik_uygulamasi.entity.User;
import com.eminekarabolat.arkadaslik_uygulamasi.views.VwUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Spring data JPA, spring bootun yapısı gereği her bir interface için gerekli olan IMP sınıflarını generate
 * ederek onların nesne referanslarını (yani beanlerini) application contextinin içerisine atar. Gerekli olduğunda
 * buradan kullanırsınız.
 */
public interface UserRepository extends JpaRepository<User, Long> {
	//List<User> gelSenButunDatalariBanaVer();
	
	/**
	 * Kadın kullanıcıların listesi
	 */
	//YANLIS: List<User> kadinKullanicilariGetir();
	
	/**
	 * JPA Repository belli keywordler ile sorgu metodları oluşturmanıza izin verir.
	 * -- JPA kendine ait metodların gövdelerini yazabilir.
	 * -- JPA kendi kelimeleri ile oluşturulan metodların da gövdelerini yazabilir ancak
	 * kurallara uygun yazılır ise.
	 * ÖRN: cinsiyete göre kullanıcı listesi select * from tbl_user where gender = ?
	 * 1-find (sorgu türünü belirtiyoruz.) - All(opsiyoneldir)
	 * 2-by - where komutuna denk gelir.
	 * 3-Entity içerisinde tanımlanmış değişken adını yazıyorsunuz. Ancak değişkenin ilk harfi büyük harfle başlamalı.
	 * 4-Eğer sorgu bir eğer talep ediyorsa talep edilen değişkenin türünde bir değer değişkeni metod parametresi
	 * olarak tanımlanır.
	 */
	
	List<User> findAllByGender(Gender gender);
	//değişken adını gender verdik buraya kafamıza göre bir şey yazabiliriz.
	
	/**
	 * birden fazla alan için sorgu yapmak istersek select * from tbluser where gender =?1 and age =?2 select * from
	 * tbluser where gender = ?1 or name = ?2 OR - AND
	 */
	List<User> findAllByGenderAndAge(Gender gender, int age);
	
	List<User> findAllByGenderOrName(Gender gender, String name);
	
	/**
	 * sorgu sadece tek bir sonuç dönecek ise return type Entity ya da Optionak<Entity> şeklinde kullanılır.
	 */
	//bu sorgu kullanıcıyı bulursa değer döner yok ise null döner.
	User findByUserName(String userName);
	
	Optional<User> findOptionalByUserName(String userName);
	
	/**
	 * belli bir yaşın üzerindeki kullanıcıları bulmak select * from tbluser where age>?1 select * from tbluser where
	 * age>=?1
	 */
	
	List<User> findALlByAge(int age); //bu yaşı buna eşit olanları listele
	
	List<User> findAllByAgeGreaterThan(int age);//yaşı bundan büyük olanlar
	
	List<User> findAllByAgeGreaterThanEqual(int age);//yaşı bundan büyük ve eşit olanlar
	
	List<User> findAllByAgeLessThan(int age); // yaşı bundan küçük olanlar
	
	List<User> findAllByAgeLessThanEqual(int age); // yaşı bundan küçük ve eşit olanlar
	
	
	/**
	 * kullanıcı adlarından belli bir kelimeye göre içerikte arama yapmak
	 * select * from tbluser where userName like '%[VALUE]%' içeriyorsa
	 * select * from tbluser where userName like '[VALUE]%' buunla başlıyorsa
	 * select * from tbluser where userName like '%[VALUE]' bununla bitiyorsa
	 * select * from tbluser where userName like '--- [VALUE]%' 3 harf ile başlayıp sonra boşluk sonra aranan
	 * like bire bir eşleşme arar emine ==emine olmalı / like ile Emine == emine olamaz
	 * ilike
	 * findAll
	 */
	List<User> findAllByUserNameLike(String userName);
	List<User> findAllByUserNameLikeIgnoreCase(String userName);
	
	/**
	 * bununla başlayanlar -> startWith
	 * bununla bitenler -> endWith
	 * bunu içerenler -> containing
	 */
	List<User> findAllByUserNameStartingWith(String userName);
	List<User> findAllByUserNameEndingWith(String userName);
	List<User> findAllByUserNameContaining(String userName);
	
	List<User> findAllByUserNameContainingIgnoreCaseAndAgeGreaterThanAndEmailEndingWith(String userName,
	                                                                                    int age,String email);
	
	/**
	 * sorgu neticesinde dönen verilerde sıralama yapmak
	 * select * from tbluser orderby age [DESC,ASC]
	 * select * from tbluser where name = ?1 orderby age desc
	 */
	
	List<User> findAllByOrderByAge();
	List<User> findAllByOrderByAgeDesc();
	List<User> findAllByNameOrderByAgeDesc(String name);
	
	
	/**
	 * sorgular genellikle kısıtlanarak kullanılır, böylece sorgu performansı arttırılmış olur
	 * select * from tbluser limit 5 [TOP]
	 * YAŞI EN BÜYÜK OLAN KULLANICI KİM?
	 * select * from tbluser order by age desc limit 1
	 * -> find, findAll,finTop
	 * yaşı en küçük ilk 5 kullanıcı
	 */
	User findTopByOrderByAgeDesc();
	List<User> findTop5ByOrderByAge();
	
	
	/**
	 * belli aralıkları sorgulamak
	 * select * from tbluser where age>5 and age<25 ->(5,25)
	 * select * from tbluser where age>=5 and age<=25 ->[5,25]
	 *
	 */
	
	List<User> findAllByAgeBetween(int start, int end); // [start,end]
	List<User> findAllByUserNameContainingAndAgeBetween(String userName, int startAge, int endAge);
	
	/**
	 * boolean değeri sorgularken kullanılacak keyword
	 * hesabı aktif olan kullanıcılar
	 */
	List<User> findAllByIsActive(boolean isActive);
	List<User> findAllByIsActiveTrue(); // Aktif olanlar
	List<User> findAllByIsActiveFalse();// Pasif olanlar
	
	
	/**
	 * mükerrer kayıtları tekilleştirmek
	 * uygulamamızda kullanılan farklı isimlerin listesi
	 */
	
	//List<User> findDistinctBy();
	List<String> findDistinctByName(String name);
	
	
	List<User> findAllByIsActiveIsNull();//Aktif bilgisi null olanlar
	List<User> findAllByIsActiveIsNotNull(); //Aktif bilgisi null olmayanlar
	
	
	/**
	 * Belli kullanıcıların listesine ihtiyaç duymaktayız. burada id kullanılacak diyelim.
	 * id si 5-9-98-541-24-58-89-44-85
	 * bir kişinin takipçilerinin idler elimizde olsun, bu kullanıcıların bilgilerine ihtiyacımıvar
	 * select * from tbluser where id in (5,9,98,.....)
	 * select * from tbluser where id in(select followerid from tblfollow where id =5)
	 *
	 */
	List<User> findAllByIdIn(List<String> ids);
	
	/**
	 * JPQL -> Jakarta Persistence Query Language
	 * HQL -> Hibernate Query Language
	 * NATIVESQL -> bildiğimiz SQL sorguları
	 *
	 * jpql -> select u from User u
	 * HQL -> from User
	 * NativeSql -> select * from User
	 *
	 * Aşağıdaki metod içi Spring gövde oluştururken, Query içerisine yazılan sorguyu kullanacak ve metoda geçilen
	 * değerler sorguya işlenerek sorgunun sonucu methodun return typei ile dönecektir.
	 */
	@Query("select u from User u where u.name=?1")
	List<User> banaAdiSuOlanKullanicilariGetir(String ad);
	
	@Query("select u from User u where u.age=?1 and u.email like ?2")
	List<User> bulYasiSuOlanVeMailAdresiBuOlan(int yas,String mailAdres);
	
	@Query(nativeQuery = true,value = "select * from User where age>?1")
	List<User> yasiBuyukOlanlariGetir(int yas);
	
	
	/**
	 * mesela login olacak bir kullanıcı UN,PWD girmeli bu kritere uyan bir kayıt var ise giris yapmalı.
	 *
	 * select COUNT(*)>0 from tbluseruserName = ? and password=?
	 */
	
	@Query("select COUNT(u) from User u where u.userName=?1 and u.password=?2")
	Boolean buKullaniciVarMi(String userName,String password);
	
	
	Boolean existsByUserNameAndPassword(String userName, String password);
	
	
	/**
	 *
	 * select return elemanları ile result type uyumlu olmalıdır.
	 *
	 */
	@Query("select new com.eminekarabolat.arkadaslik_uygulamasi.views.VwUser(u.userName,u.name,u.avatar) from User u")
	List<VwUser> tumKullanicilariListele();
	
	
	/**
	 * JpaRepository -> içerisindeki tüm metodların kodlamaları standarttır. Bu nedenle
	 * Spring ilgili sınıfların mesela UserRepository için UserRepositoryImpl sınıfnı oluşturabilir.
	 * ancak, burada tanımı yapılan metod nasıl oluşturulacağı hakkında bir bilgi,
	 * içermediği için nesne yaratma sürecini baltaladı.
	 */
}