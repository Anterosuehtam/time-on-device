package com.project.time_on_device.repository;

import com.project.time_on_device.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Integer> {
    // Busca atividades de um usuário que começaram entre uma data inicial e uma data final
    List<ActivityLog> findByUserIdAndStartTimeBetween(Integer userId, LocalDateTime inicio, LocalDateTime fim);

}
