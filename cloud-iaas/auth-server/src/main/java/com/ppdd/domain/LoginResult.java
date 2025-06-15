package com.ppdd.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description="登录结果统一对象")
public class LoginResult {

    @Schema(description="token")
    private String token;
    @Schema(description="有效时长")
    private Long expire;

}
