package com.eminekarabolat.arkadaslik_uygulamasi.views;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VwUser {
	String username;
	String name;
	String avatar;
	
}