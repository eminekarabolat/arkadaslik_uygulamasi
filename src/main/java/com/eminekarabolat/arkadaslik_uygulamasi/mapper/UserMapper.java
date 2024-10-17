package com.eminekarabolat.arkadaslik_uygulamasi.mapper;

import com.eminekarabolat.arkadaslik_uygulamasi.dto.request.UpdateUserProfileDto;
import com.eminekarabolat.arkadaslik_uygulamasi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Herhangi bir dto'dan user entity'sine dönüştürme işlemi için kullanılacak
 */
//COMPONENTMODEL: yaratılan beanin spring tarafından kullanılacağını gösterir,springe uygun nesne yaratılmasını sağlar.
//unmappedTargetPolicy = ReportingPolicy.IGNORE: KAYNAKTAN HEDEFE YA DA HEDEFTEN KAYNAĞA EŞLEŞME YAPILIRKEN BOŞTA
// KALAN HEDEFLERİ GÖRMEZDEN GELMEK İÇİN  KULLANILIR.
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
	//bu interface üzerinden oluşturulacak olan impl sınıfının nesnesini yaratarak
	//referans adresini atamak için kullanıyoruz.
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	//User -> Hedef
	//Dto -> Kaynak
	User fromUpdateProfileDto(final UpdateUserProfileDto dto);
}