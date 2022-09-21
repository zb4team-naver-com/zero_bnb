package com.service.zerobnb.web.accommodation.mapper;

import com.service.zerobnb.util.type.AccommodationType;
import com.service.zerobnb.web.accommodation.dto.AccommodationMainDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccommodationMapper {
    List<AccommodationMainDto> nearDistAccommodation(Double lat, Double lnt);
    List<AccommodationMainDto> typeAccommodation(AccommodationType accommodationType);
    List<AccommodationMainDto> typeAndDistAccommodation(Double lat, Double lnt, AccommodationType accommodationType);
}
