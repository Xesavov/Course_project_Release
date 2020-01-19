package com.orangeteam.NewAuc.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERPROD")
public class UserProd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERDESC_ID")
    private UserDesc userDesc;

    // lider - 2, not leader - 1
    private Integer leader;
    // like - 1, else - 0
    private Integer liked;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userProd")
    private List<Event> events;
}
