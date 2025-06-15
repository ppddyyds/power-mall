package com.ppdd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ppdd.domain.LoginSysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface SysUserMapper extends BaseMapper<LoginSysUser> {

    @Select("""
select  perms   from sys_menu t1
join sys_role_menu t2 join sys_user_role t3
on (t1.menu_id = t2.menu_id and t2.role_id = t3.role_id)
where t3.user_id = #{userId} and t1.type=2
""")
    Set<String> getUserPermissions(Long userId);
}
