package com.service.zerobnb.web.host.model;


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
public class HostInput {
    @NotEmpty(message = "사업자 연락처는 빈 값이 올 수 없습니다.")
    @NotBlank(message = "사업자 연락처는 공백만 올 수 없습니다.")
    @Size(max = 255, message = "사업자 연락처는 최대 255글자 까지 작성 가능합니다.")
    private String businessContact;

    @NotEmpty(message = "사업자 등록번호는 빈 값이 올 수 없습니다.")
    @NotBlank(message = "사업자 등록번호는 공백만 올 수 없습니다.")
    @Size(max = 255, message = "사업자 등록번호는 최대 255글자 까지 작성 가능합니다.")
    private String companyRegistrationNumber;

    @Size(max = 255, message = "이미지 URL은 최대 255글자 까지 작성 가능합니다.")
    private String profileImage;

    // 추후 Principal (로그인 인증상태)로 대체
    private String email;
}
