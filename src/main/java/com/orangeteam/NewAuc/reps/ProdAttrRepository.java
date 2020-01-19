package com.orangeteam.NewAuc.reps;

import com.orangeteam.NewAuc.models.ProdAttr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdAttrRepository extends JpaRepository<ProdAttr, Long> {

    ProdAttr findByProductIdAndAttributeId(Long productId, Long attributeId);
}
