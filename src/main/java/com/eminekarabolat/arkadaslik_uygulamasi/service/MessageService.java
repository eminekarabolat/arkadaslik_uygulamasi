package com.eminekarabolat.arkadaslik_uygulamasi.service;

import com.eminekarabolat.arkadaslik_uygulamasi.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
	private final MessageRepository messageRepository;
}