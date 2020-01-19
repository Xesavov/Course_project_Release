package com.orangeteam.NewAuc.models;

import com.orangeteam.NewAuc.enums.AttrType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ATTRIBUTE")
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @ElementCollection(targetClass = AttrType.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "ATTRTYPE", joinColumns = @JoinColumn(name = "attribute_id"))
    @Enumerated(EnumType.STRING)
    private AttrType type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "attribute")
    private Set<ProdAttr> prodAttrs;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "attribute")
    private Set<AttrVal> attrVals;
}
