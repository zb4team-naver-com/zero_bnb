package com.service.zerobnb.web.accommodation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventInput {
    @NotEmpty(message = "이벤트 항목은 빈 값이 올 수 없습니다.")
    @NotBlank(message = "이벤트 항목은 공백만 올 수 없습니다.")
    @Size(max = 255, message = "이벤트 항목은 최대 255글자 까지 작성 가능합니다.")
    private String description;
}
