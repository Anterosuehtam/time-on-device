package com.project.time_on_device.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Category")
@Data // Gera todos os Getters, Setters, equals(), hashCode() e toString()
@NoArgsConstructor // Gera o construtor vazio, que é obrigatório para o JPA funcionar
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable = false, length = 100)
    private String name;

    @Column (name = "is_productive", nullable = false)
    private Boolean isProductive;

}
