package com.ppdd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private Long userId;
    private String username;
    private Integer status;
    private Long shopId;
    private String loginType;
    private Set<String> permissions;
}
