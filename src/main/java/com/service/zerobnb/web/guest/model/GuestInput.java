package com.service.zerobnb.web.guest.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuestInput {
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
        message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상 포함된 8 ~ 20자의 비밀번호여야 합니다.")
    private String password;

    @NotEmpty
    @NotBlank
    private String name;

    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^\\d{4}\\.(0[1-9]|1[012])\\.(0[1-9]|[12][0-9]|3[-1])$",
        message = "생일은 yyyy.MM.dd 의 형식이어야 합니다.")
    private String birth;

    @NotEmpty
    @NotBlank
    @Pattern(regexp = "(\\d{3})-(\\d{3,4})-(\\d{4})",
        message = "휴대폰 번호는 ddd-ddd(dddd)-dddd 형식이어야 합니다.")
    private String phone;

    private String profileImage;
}
