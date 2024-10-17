package com.eminekarabolat.arkadaslik_uygulamasi.controller;


import com.eminekarabolat.arkadaslik_uygulamasi.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DİKKAT!!!!!!!!
 * ConstructorInjection işlemi, sınıf için gerekli constructorı ve talepp edilen değişkeni
 * yazarak tanımlayabiliriz. Bakınız. UserController
 * ancak bu işlemi Lombok kullanarakta yapoabiliriz. Bunun için lombokun @RequiredArgConstructor anatasyonu kullanılır.
 */
@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
	private final MessageService messageService;
}