package com.orangeteam.NewAuc.reps;

import com.orangeteam.NewAuc.models.UserDesc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDescRepository extends JpaRepository<UserDesc, Long> {
    UserDesc findUserDescByLogin(String login);
}
