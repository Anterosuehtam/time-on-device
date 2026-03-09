package com.project.time_on_device.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "ActivityLog")
@Data
@NoArgsConstructor
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne // Muitas ActivityLog podem ter somente um usuário
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne // Muitas ActivityLog podem ter somente uma category
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne // Muitas ActivityLog podem ter somente um device
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    @Column(name = "app_or_site_name", nullable = false, length = 150)
    private String appOrSiteName;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;


}
