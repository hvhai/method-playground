package com.codehunter.methodplayground.respository;

import com.codehunter.methodplayground.entity.H2WebHookEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebHookEventRepository extends JpaRepository<H2WebHookEvent, Long> {
    List<H2WebHookEvent> findAllByOrderByIdDesc();
}
