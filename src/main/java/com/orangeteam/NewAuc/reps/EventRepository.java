package com.orangeteam.NewAuc.reps;

import com.orangeteam.NewAuc.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query(value = "select e from Event e, UserProd up, UserDesc u where e.userProd.id=up.id AND up.userDesc.id=u.id AND u.login=?1")
    List<Event> getHistoryByLogin(String login);
}
