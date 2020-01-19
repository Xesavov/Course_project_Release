package com.orangeteam.NewAuc.models;

import com.orangeteam.NewAuc.enums.Activity;
import com.orangeteam.NewAuc.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    private String name;
    private Double assVal;
    private Double startPrice;
    private Double step;
    private Double currPrice;
//    @ElementCollection(targetClass = ProductStatus.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "ProductStatus", joinColumns = @JoinColumn(name = "product_id"))
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    private LocalDateTime dateBeg;
    private LocalDateTime dateEnd;
    private LocalDateTime datePay;
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProdAttr> prodAttrs;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Image> images;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<CatProd> catProds;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<UserProd> userProds;

    public static final Comparator<Product> COMPARE_BY_DATEEND = new Comparator<Product>() {
        @Override
        public int compare(Product product, Product t1) {
            return product.getDateEnd().isBefore(t1.getDateEnd())?-1:1;
        }
    };

    public static final Comparator<Product> COMPARE_BY_NAME = new Comparator<Product>() {
        @Override
        public int compare(Product product, Product t1) {
            return product.getName().compareTo(t1.getName());
        }
    };
    public static final Comparator<Product> COMPARE_BY_PRICE = new Comparator<Product>() {
        @Override
        public int compare(Product product, Product t1) {
            return (int)(product.getCurrPrice()-t1.getCurrPrice());
        }
    };
    public static final Comparator<Product> COMPARE_BY_COST = new Comparator<Product>() {
        @Override
        public int compare(Product product, Product t1) {
            return (int)(product.getAssVal()-t1.getAssVal());
        }
    };
    public static final Comparator<Product> COMPARE_BY_DATEBEG = new Comparator<Product>() {
        @Override
        public int compare(Product product, Product t1) {
            return product.getDateBeg().isBefore(t1.getDateBeg())?-1:1;
        }
    };
}
