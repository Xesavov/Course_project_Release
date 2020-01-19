package com.orangeteam.NewAuc.services;

import com.orangeteam.NewAuc.enums.Role;
import com.orangeteam.NewAuc.models.Event;
import com.orangeteam.NewAuc.models.Product;
import com.orangeteam.NewAuc.models.UserDesc;
import com.orangeteam.NewAuc.reps.EventRepository;
import com.orangeteam.NewAuc.reps.ProductRepository;
import com.orangeteam.NewAuc.reps.UserDescRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    UserDescRepository userDescRepository;
    ProductRepository productRepository;
    EventRepository eventRepository;

    public UserDesc getUserByLogin(String login) {
        return userDescRepository.findUserDescByLogin(login);
    }

    public List<Product> getActivProductsByLogin(String login) {
        return productRepository.getActivProductsByLogin(login);
    }

    public List<Product> getLikedProductsByLogin(String login){
        return productRepository.getLikedProductsByLogin(login);
    }

    public List<Product> getPurProductsByLogin(String login){
        return productRepository.getPurProductsByLogin(login);
    }

    public String getGeneralRole(String login) {
        UserDesc userDesc = userDescRepository.findUserDescByLogin(login);
        if (userDesc == null) {
            return null;
        }
        Set<Role> roles = userDesc.getRoles();
        if (roles.contains(Role.ROLE_ADMIN)) {
            return Role.ROLE_ADMIN.toString();
        } else if (roles.contains(Role.ROLE_MODER)) {
            return Role.ROLE_MODER.toString();
        } else if (roles.contains(Role.ROLE_TETENKA)) {
            return Role.ROLE_TETENKA.toString();
        } else {
            return Role.ROLE_USER.toString();
        }
    }

    public void createUser(UserDesc user){
        user.setActivity(true);
        Set<Role> role = new HashSet<>();
        role.add(Role.ROLE_USER);
        user.setRoles(role);
        userDescRepository.save(user);
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setUserDescRepository(UserDescRepository userDescRepository) {
        this.userDescRepository = userDescRepository;
    }

    @Autowired
    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
}
