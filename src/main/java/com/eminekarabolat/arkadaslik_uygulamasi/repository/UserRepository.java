package com.eminekarabolat.arkadaslik_uygulamasi.repository;

import com.eminekarabolat.arkadaslik_uygulamasi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring data JpA, spring bootun yapısı gerteği her bir interface için gerekliş olan IMP sınıflarını generate ederek
 * onların nesne referanslarını (yani beanlerini) application contextinin içerisine atar. Gerekli olduğunda buradan
 * kullanırsınız.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}