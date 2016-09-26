package com.service;

import com.bean.Visitor;
import com.db.VisitorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerUserDetailService implements UserDetailsService {

    private VisitorDao userDao;

    @Autowired
    public CustomerUserDetailService(VisitorDao visitorDao) {
        this.userDao = visitorDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails userDetails = null;

        Optional<Visitor> visitorOptional = userDao.getUserByName(username);

        if(visitorOptional.isPresent()){
            System.out.println();
            Visitor visitor = visitorOptional.get();
            userDetails = new User(visitor.getName(), visitor.getPassword(), getGrantedAuthorities(visitor.getRole()));
        }

        return userDetails;
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> authList = new ArrayList<>();

        authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return authList;
    }
}
