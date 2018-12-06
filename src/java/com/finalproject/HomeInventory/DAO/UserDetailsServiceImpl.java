package com.finalproject.HomeInventory.DAO;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finalproject.HomeInventory.Utility.Constants;
import com.finalproject.HomeInventory.model.User;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
    @Autowired
    private UserDAOImpl userService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if(Constants.ACTIVE == user.getActive()){
            if(Constants.ROLE_ADMIN == user.getIsadmin()){
                grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
            }else {
                grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
            }
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}