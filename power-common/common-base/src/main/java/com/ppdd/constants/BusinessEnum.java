package com.ppdd.constants;

import lombok.Getter;
import lombok.Setter;

/**
 * 业务状态码枚举
 */


@Getter
public enum BusinessEnum {

    OPERATION_FAIL(-1, "操作失败"),
    SERVER_INTERNAL_ERROR(500, "服务器内部错误"),
    UN_AUTHORIZED(401, "未授权"),
    ACCESS_DENIED(403, "无权限访问");


    private Integer code;

    private String desc;
    BusinessEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
