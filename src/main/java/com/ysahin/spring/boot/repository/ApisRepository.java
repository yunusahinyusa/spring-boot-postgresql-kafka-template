package com.ysahin.spring.boot.repository;

import com.ysahin.spring.boot.entity.ApiS;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ApisRepository extends JpaRepository<ApiS,Long> {
    @Query("SELECT a FROM ApiS a WHERE a.createdDate >= ?1")
    List<ApiS> findAllWithCreationDateTimeBefore(LocalDateTime createdDate);
}
