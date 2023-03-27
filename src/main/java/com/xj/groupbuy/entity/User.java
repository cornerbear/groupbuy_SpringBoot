package com.xj.groupbuy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangxiaojian
 * @since 2021-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("`USER`")
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @TableId(value = "USER_ID",type = IdType.ASSIGN_ID)
    private String userId;

    @TableField("COMMUNITY_ID")
    private Integer communityId;
    
    @TableField("NAME")
    private String name;
    
    @TableField("USERNAME")
    private String username;

    @TableField("PASSWORD")
    private String password;

    @TableField("SEX")
    private Boolean sex;

    @TableField("PHONE")
    private String phone;

    @TableField("detail_ADDRESS")
    private String detailAddress;

    @TableField("ENABLED")
    @Getter(AccessLevel.NONE)
    private Boolean enabled;

    @TableField(exist = false)
    private List<Role> roles;
    

    public User() {

    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
