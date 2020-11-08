package com.orders.demo.models;

import javax.persistence.*;

/**
 * @author IsuruP
 */
@Entity
@Table(name = "user")
@Inheritance(
        strategy = InheritanceType.JOINED
)
public class SysUser {

    @Id
    @GeneratedValue
    private int Id;
    @Column(name="user_first_name",unique = true)
    private String userFirstName;
    @Column(name="user_last_name",unique = true)
    private String userLastName;
    @Column(name="user_email",unique = true)
    private String userEmail;
    @Column(name="password")
    private String password;
    @Column(name="user_role")
    private String userRole;



    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }
}
