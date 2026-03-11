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

@Entity
@Table(name = "Device")
@Data
@NoArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração de ID único dentro do Banco de Dados
    private Integer id;

    @Column(name = "device_name", nullable = false, length = 100)
    private String name;

    @Column(name = "device_type", nullable = false, length = 50)
    private String deviceType;

    //Cardinalidade N -> 1, e estamos relacionando esta tabela com a tabela User, com a coluna "user_id"
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
