package com.orangeteam.NewAuc.reps;

import com.orangeteam.NewAuc.models.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
    Attribute findByName(String name);
}
