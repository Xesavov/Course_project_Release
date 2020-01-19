package com.orangeteam.NewAuc.reps;

import com.orangeteam.NewAuc.models.AttrVal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttrValRepository extends JpaRepository<AttrVal, Long> {
    List<AttrVal> findByValue(String value);
}
