package com.eminekarabolat.arkadaslik_uygulamasi.repository;

import com.eminekarabolat.arkadaslik_uygulamasi.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}