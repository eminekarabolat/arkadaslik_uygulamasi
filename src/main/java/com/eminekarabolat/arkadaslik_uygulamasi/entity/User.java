package com.eminekarabolat.arkadaslik_uygulamasi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tbl_user")

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String name;
	String avatar;
	Gender gender;
	@Column(length = 64, unique = true)
	String userName;
	String password;
	String email;
	String phone;
	String address;
	Integer age;
	Integer weight;
	Integer height;
	Integer followerCount;
	Integer followingCount;
	Boolean isActive;
}