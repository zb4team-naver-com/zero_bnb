package com.service.zerobnb.web.accommodation.model;

import com.service.zerobnb.util.LocationPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationInput {
    @NotEmpty(message = "숙박업소 유형은 빈 값이 올 수 없습니다.")
    @NotBlank(message = "숙박업소 유형은 공백만 올 수 없습니다.")
    @Size(max = 255, message = "숙박업소 유형은 최대 255글자 까지 작성 가능합니다.")
    private String type;

    @NotEmpty(message = "숙박업소 주소는 빈 값이 올 수 없습니다.")
    @NotBlank(message = "숙박업소 주소는 공백만 올 수 없습니다.")
    @Size(max = 255, message = "숙박업소 주소는 최대 255글자 까지 작성 가능합니다.")
    private String address;

    @NotEmpty(message = "숙박업소 상세설명은 빈 값이 올 수 없습니다.")
    @NotBlank(message = "숙박업소 상세설명은 공백만 올 수 없습니다.")
    private String description;

    private LocationPosition locationPosition;

    @NotEmpty(message = "숙박업소명은 빈 값이 올 수 없습니다.")
    @NotBlank(message = "숙박업소명은 공백만 올 수 없습니다.")
    @Size(max = 255, message = "숙박업소명은 최대 255글자 까지 작성 가능합니다.")
    private String name;

    @NotEmpty(message = "숙박업소 공지는 빈 값이 올 수 없습니다.")
    @NotBlank(message = "숙박업소 공지는 공백만 올 수 없습니다.")
    private String notice;

    @NotEmpty(message = "숙박업소 정책은 빈 값이 올 수 없습니다.")
    @NotBlank(message = "숙박업소 정책은 공백만 올 수 없습니다.")
    private String policy;

    private List<EventInput> eventInputs;

    private List<PopularFacilityServiceInput> popularFacilityServiceInputs;

    private List<AccommodationImageInput> accommodationImageInputs;

    private Long hostId;
}
