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
@Table(name = "CATEGORY")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category parent;

    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
    private List<Category> subsidiary;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<CatProd> catProds;

    public Category(String name, Category parent) {
        this.parent = parent;
        this.name = name;
    }
}
