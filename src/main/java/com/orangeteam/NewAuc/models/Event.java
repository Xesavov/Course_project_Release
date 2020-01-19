package com.orangeteam.NewAuc.models;

import com.orangeteam.NewAuc.enums.Activity;
import com.orangeteam.NewAuc.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EVENT")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERPROD_ID")
    private UserProd userProd;
//    @ElementCollection(targetClass = Activity.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "activity", joinColumns = @JoinColumn(name = "event_id"))
    @Enumerated(EnumType.STRING)
    private Activity activity;

    private LocalDateTime date;

    @OneToOne(mappedBy = "event", fetch = FetchType.LAZY)
    private Bid bid;
}
