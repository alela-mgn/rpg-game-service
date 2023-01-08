package com.rgs.repository;

import com.rgs.entity.Player;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findAll(Specification<Player> specification, Pageable pageable);

    List<Player> findAll(Specification<Player> specification);
}
