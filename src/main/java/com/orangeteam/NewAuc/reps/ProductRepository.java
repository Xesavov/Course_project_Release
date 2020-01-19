package com.orangeteam.NewAuc.reps;

import com.orangeteam.NewAuc.enums.ProductStatus;
import com.orangeteam.NewAuc.models.Event;
import com.orangeteam.NewAuc.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select p.leader from UserProd p, UserDesc d where p.userDesc.id=d.id AND d.login=?1 AND p.product.id=?2")
    Integer isProductLeader(String username, Long id);

    @Query(value = "select p.liked from UserProd p, UserDesc d where p.userDesc.id=d.id AND d.login=?1 AND p.product.id=?2")
    Integer isProductLike(String username, Long productId);

    @Query(value = "select p from Product p, UserProd up, UserDesc u where p.id=up.product.id AND up.userDesc.id=u.id AND u.login=?1 AND (up.leader=1 OR up.leader=2) AND p.status='IN_TRADES'")
    List<Product> getActivProductsByLogin(String login);

    @Query(value = "select p from Product p, CatProd cp where cp.product.id=p.id AND cp.category.id=?1")
    List<Product> getProductsByCategoryId(Long categoryId);

    @Query(value = "select p from Product p, UserProd up, UserDesc u where p.id=up.product.id AND up.userDesc.id=u.id AND u.login=?1 AND (up.liked=1)")
    List<Product> getLikedProductsByLogin(String login);

    @Query(value = "select p from Product p, UserProd up, UserDesc u where p.id=up.product.id AND up.userDesc.id=u.id AND u.login=?1 AND (p.status='BOOKED' OR p.status='SOLD') AND up.leader=2")
    List<Product> getPurProductsByLogin(String login);

    @Query(value = "select p from Product p where p.status=?1")
    List<Product> getProductsWithStatus(ProductStatus status);


}
