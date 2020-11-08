package com.orders.demo.resources;

import com.orders.demo.dao.HibernateDAOFactory;
import com.orders.demo.models.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author IsuruP
 */
@Service
public class SysUserDetailService implements UserDetailsService {

    @Autowired
    private HibernateDAOFactory hibernateDaoFactory;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return convertToModuleData(this.hibernateDaoFactory.getUserDAO().getUserDetails(s));
    }

    private User convertToModuleData(SysUser userData) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userData.getUserRole()));
        return new User( userData.getUserName(),  userData.getPassword(), authorities);
    }
}
