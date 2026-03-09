package com.project.time_on_device.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "User")
@Data // Gera todos os Getters, Setters, equals(), hashCode() e toString()
@NoArgsConstructor // Gera o construtor vazio, que é obrigatório para o JPA funcionar
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 150, unique = true)
    private String email;

    @Column(name = "daily_limit_minutes")
    private Integer dailyLimitMinutes;

    @CreationTimestamp // O Spring preenche automaticamente a data/hora exata em que o registro for salvo
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}