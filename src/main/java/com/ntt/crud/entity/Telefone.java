package com.ntt.crud.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Telefone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "apelido")
    private String apelido;

    @Column(name = "ddd")
    private int ddd;

    @Column(name = "numero")
    private String numero;

    @Column(name = "ativo")
    private Boolean ativo;
}