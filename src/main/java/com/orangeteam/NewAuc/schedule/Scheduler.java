package com.orangeteam.NewAuc.schedule;

import com.orangeteam.NewAuc.enums.Activity;
import com.orangeteam.NewAuc.enums.ProductStatus;
import com.orangeteam.NewAuc.models.Event;
import com.orangeteam.NewAuc.models.Product;
import com.orangeteam.NewAuc.models.UserProd;
import com.orangeteam.NewAuc.reps.EventRepository;
import com.orangeteam.NewAuc.reps.ProductRepository;
import com.orangeteam.NewAuc.reps.UserProdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class Scheduler {
    ProductRepository productRepository;
    UserProdRepository userProdRepository;
    EventRepository eventRepository;

    @Scheduled(fixedDelay = 1000)
    public void mark() {
        List<Product> announced = new ArrayList<>();
        announced.addAll(productRepository.getProductsWithStatus(ProductStatus.ANNOUNCED));
        for (Product p : announced) {
            if (p.getDateBeg().isBefore(LocalDateTime.now())) {
                p.setStatus(ProductStatus.IN_TRADES);
                productRepository.save(p);
            }
        }

        List<Product> inTrades = new ArrayList<>();
        inTrades.addAll(productRepository.getProductsWithStatus(ProductStatus.IN_TRADES));
        for (Product p : inTrades) {
            if (p.getDateEnd().isBefore(LocalDateTime.now())) {
                UserProd leader = userProdRepository.findProductLeader(p.getId());
                if (leader != null) {
                    p.setStatus(ProductStatus.BOOKED);
                    productRepository.save(p);
                    Event book = new Event();
                    book.setUserProd(leader);
                    book.setActivity(Activity.WON);
                    book.setDate(LocalDateTime.now());
                    eventRepository.save(book);
                } else {
                    p.setStatus(ProductStatus.WITHDRAWN);
                    productRepository.save(p);
                }
            }
        }
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setUserProdRepository(UserProdRepository userProdRepository) {
        this.userProdRepository = userProdRepository;
    }

    @Autowired
    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
}
