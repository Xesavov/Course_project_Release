package com.orangeteam.NewAuc.services;

import com.orangeteam.NewAuc.enums.Activity;
import com.orangeteam.NewAuc.enums.ProductStatus;
import com.orangeteam.NewAuc.models.*;
import com.orangeteam.NewAuc.reps.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UpService {
    ProductRepository productRepository;
    UserDescRepository userDescRepository;
    UserProdRepository userProdRepository;
    EventRepository eventRepository;
    BidRepository bidRepository;

    public Boolean up(Long productId, int steps, String login){
        Product product = productRepository.findById(productId).orElse(null);
        if(product==null){
            return false;
        }
        if(product.getStatus()!= ProductStatus.IN_TRADES){
            return false;
        }
        UserDesc user = userDescRepository.findUserDescByLogin(login);
        Boolean duplicate = false;
        UserProd leader = userProdRepository.findProductLeader(productId);
        if(leader!=null && leader.getUserDesc().getLogin().equals(login)){
            duplicate=true;
        }
        if(!duplicate && leader!=null){
            leader.setLeader(1);
            userProdRepository.save(leader);
            Event e1 = new Event();
            e1.setUserProd(leader);
            e1.setActivity(Activity.NOTLEADER);
            e1.setDate(LocalDateTime.now());
            eventRepository.save(e1);
        }
        UserProd nLead = userProdRepository.findByProductIdAndUserId(productId, user.getId());
        if(nLead==null){
            nLead = new UserProd();
            nLead.setProduct(product);
            nLead.setUserDesc(user);
        }
        nLead.setLeader(2);
        userProdRepository.save(nLead);
        Event e2 = new Event();
        e2.setUserProd(nLead);
        e2.setActivity(Activity.LEADER);
        e2.setDate(LocalDateTime.now());
        eventRepository.save(e2);
        Bid bid = new Bid(null, e2, steps);
        bidRepository.save(bid);

        product.setCurrPrice(product.getCurrPrice()+product.getStep()*steps);
        LocalDateTime end = product.getDateEnd();
        LocalDateTime nEnd = LocalDateTime.now().plusSeconds(60);
        if(end.isBefore(nEnd)){
            product.setDateEnd(nEnd);
        }
        productRepository.save(product);
        return true;
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
    public void setUserProdRepository(UserProdRepository userProdRepository) {
        this.userProdRepository = userProdRepository;
    }

    @Autowired
    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Autowired
    public void setBidRepository(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }
}
