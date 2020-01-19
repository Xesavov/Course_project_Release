package com.orangeteam.NewAuc.services;

import com.orangeteam.NewAuc.enums.Activity;
import com.orangeteam.NewAuc.models.Event;
import com.orangeteam.NewAuc.models.Product;
import com.orangeteam.NewAuc.models.UserDesc;
import com.orangeteam.NewAuc.models.UserProd;
import com.orangeteam.NewAuc.reps.EventRepository;
import com.orangeteam.NewAuc.reps.ProductRepository;
import com.orangeteam.NewAuc.reps.UserDescRepository;
import com.orangeteam.NewAuc.reps.UserProdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LikeService {
    UserProdRepository userProdRepository;
    UserDescRepository userDescRepository;
    ProductRepository productRepository;
    EventRepository eventRepository;

    public void setLike(Long productId, String login) {
        UserDesc user = userDescRepository.findUserDescByLogin(login);
        Product product = productRepository.findById(productId).orElse(null);
        if (user == null || product == null) {
            return;
        }
        UserProd relation = userProdRepository.findByProductIdAndUserId(productId, user.getId());
        if (relation == null) {
            relation = new UserProd();
            relation.setUserDesc(user);
            relation.setProduct(product);
        }
        relation.setLiked(1);
        userProdRepository.save(relation);
        Event event = new Event();
        event.setUserProd(relation);
        event.setActivity(Activity.LIKE);
        event.setDate(LocalDateTime.now());
        eventRepository.save(event);
    }

    public void setUnLike(Long productId, String login) {
        UserDesc user = userDescRepository.findUserDescByLogin(login);
        Product product = productRepository.findById(productId).orElse(null);
        if (user == null || product == null) {
            return;
        }
        UserProd relation = userProdRepository.findByProductIdAndUserId(productId, user.getId());
        if (relation == null) {
            return;
        }
        relation.setLiked(0);
        userProdRepository.save(relation);
        Event event = new Event();
        event.setUserProd(relation);
        event.setActivity(Activity.UNLIKE);
        event.setDate(LocalDateTime.now());
        eventRepository.save(event);
    }

    @Autowired
    public void setUserProdRepository(UserProdRepository userProdRepository) {
        this.userProdRepository = userProdRepository;
    }

    @Autowired
    public void setUserDescRepository(UserDescRepository userDescRepository) {
        this.userDescRepository = userDescRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
}
