package com.ppdd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ppdd.domain.LoginSysUser;

import java.util.List;

public interface SysUserService extends IService<LoginSysUser> {
    List<String> getUserPermissions(Long userId);
}
