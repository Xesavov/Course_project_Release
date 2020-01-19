package com.orangeteam.NewAuc.reps;

import com.orangeteam.NewAuc.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
