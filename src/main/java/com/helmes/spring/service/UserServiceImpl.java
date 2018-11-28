package com.helmes.spring.service;

import com.helmes.spring.dao.RoleDAO;
import com.helmes.spring.dao.UserDAO;
import com.helmes.spring.model.Role;
import com.helmes.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;




    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new ArrayList<Role>(this.roleDAO.findAll()));
        this.userDAO.save(user);
    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return this.userDAO.findByUsername(username);
    }
}
