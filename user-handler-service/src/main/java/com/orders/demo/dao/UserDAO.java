package com.orders.demo.dao;

import com.orders.demo.models.SysUser;

/**
 * @author IsuruP
 */
public interface UserDAO  {
    int addUserData(final SysUser sysUser);
    SysUser getUserDetails(final String userName);
}
