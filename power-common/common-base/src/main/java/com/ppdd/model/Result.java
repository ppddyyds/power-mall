package com.ppdd.model;

import com.ppdd.constants.BusinessEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    @Schema(description = "返回码")
    private Integer code;
    @Schema(description = "返回信息")
    private String msg;
    @Schema(description = "返回数据")
    private T data;


    public static <T> Result<T> error(Integer code, String msg, T data) {
        return new Result<>(code, msg, data);
    }
    public static <T> Result<T> error(BusinessEnum businessEnum) {
        return new Result<>(businessEnum.getCode(), businessEnum.getDesc(), null);
    }
}
