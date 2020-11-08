package com.orders.demo.dataaccess;

import com.orders.demo.dao.HibernateDAOFactory;
import com.orders.demo.dto.UserDTO;
import com.orders.demo.models.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author IsuruP
 */
@Component
public class UserDataAccess {
    @Autowired
    private HibernateDAOFactory hibernateDaoFactory;

    public int addUserData(UserDTO userDTO) {
        return this.hibernateDaoFactory.getUserDAO().addUserData(convertToUserData(userDTO));
    }

    public SysUser getUserData(String userName) {
        return this.hibernateDaoFactory.getUserDAO().getUserDetails(userName);
    }

    public List<SysUser> getAllUserData() {
        return this.hibernateDaoFactory.getUserDAO().getAllUserDetails();
    }

    private SysUser convertToUserData(UserDTO userDTO) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        SysUser sysUser = new SysUser();
        sysUser.setUserFirstName(userDTO.getUserFirstName());
        sysUser.setUserLastName(userDTO.getUserLastName());
        sysUser.setPassword(userDTO.getPassword());
        sysUser.setUserEmail(userDTO.getUserEmail());
        sysUser.setUserRole(userDTO.getUserRole());
        return sysUser;
    }
}
