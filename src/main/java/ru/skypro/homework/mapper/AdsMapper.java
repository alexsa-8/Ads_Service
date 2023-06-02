package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateAdsDTO;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.entity.Ad;

import java.util.Collection;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdsMapper {

    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);

    @Mapping(source = "id",target = "pk")
    @Mapping(source = "author.id",target = "author")
    @Mapping(source = "image.id",target = "image")
    AdsDTO adToAdsDTO(Ad ad);

    Ad adsDtoToAd(CreateAdsDTO createAdsDTO);

    @Mapping(source = "id",target = "pk")
    @Mapping(source = "author.firstName",target = "authorFirstName")
    @Mapping(source = "author.lastName",target = "authorLastName")
    @Mapping(source = "author.email",target = "email")
    @Mapping(source = "author.phone",target = "phone")
    @Mapping(source = "image.id",target = "image")
    FullAdsDto adToFullAdsDto(Ad ad);

    Collection<AdsDTO> adsToAdsListDto(Collection<Ad> adsCollection);
}