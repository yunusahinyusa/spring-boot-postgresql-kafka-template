package com.ysahin.spring.boot.repository;

import com.ysahin.spring.boot.entity.ApiS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApisRepository extends JpaRepository<ApiS,Long> {
}
