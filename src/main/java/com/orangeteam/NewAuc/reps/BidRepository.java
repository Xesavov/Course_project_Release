package com.orangeteam.NewAuc.reps;

import com.orangeteam.NewAuc.models.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {
}
