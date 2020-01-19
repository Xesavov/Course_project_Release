package com.orangeteam.NewAuc.reps;

import com.orangeteam.NewAuc.models.UserProd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserProdRepository extends JpaRepository<UserProd, Long> {

    @Query(value = "select up from UserProd up where up.product.id=?1 AND up.userDesc.id=?2")
    UserProd findByProductIdAndUserId(Long productId, Long userId);

    @Query(value = "select up from UserProd up where up.product.id=?1 AND leader=2")
    UserProd findProductLeader(Long productId);

    @Query(value = "select up from UserProd up, Product p where up.product.id=p.id AND p.status='BOOKED' AND p.id=?1")
    UserProd findBookedProduct(Long id);
}
