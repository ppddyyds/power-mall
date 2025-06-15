package com.ppdd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义用户信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityUser implements UserDetails {
    //商城后台管理系统用户
    private Long userId;
    private String username;
    private String password;
    private Integer status;
    private Long shopId;
    private String loginType;
    private Set<String> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.loginType+":"+getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.status == 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status == 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.status == 1;
    }

    @Override
    public boolean isEnabled() {
        return this.status == 1;
    }
    public Set<String> getPermissions() {

        return permissions.stream()
                .flatMap(p -> Arrays.stream(p.split(","))) // 拆分包含逗号的
                .map(String::trim)                         // 去掉前后空格
                .filter(s -> !s.isEmpty())                 // 去掉空字符串
                .collect(Collectors.toSet());
    }

}
