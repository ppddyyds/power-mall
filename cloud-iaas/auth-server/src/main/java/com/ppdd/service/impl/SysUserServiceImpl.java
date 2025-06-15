package com.ppdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ppdd.domain.LoginSysUser;
import com.ppdd.mapper.SysUserMapper;
import com.ppdd.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, LoginSysUser> implements SysUserService {
    @Override
    public List<String> getUserPermissions(Long userId) {
        return List.of();
    }
}
