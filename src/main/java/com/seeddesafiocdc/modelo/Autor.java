package com.seeddesafiocdc.modelo;

import com.seeddesafiocdc.config.validacao.CampoUnico;
import com.seeddesafiocdc.config.validacao.CampoValidacao;
import com.seeddesafiocdc.controller.dto.AutorDto;
import com.seeddesafiocdc.helper.CpfHelper;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    @CampoUnico(campo = CampoValidacao.EMAIL)
    private String email;

    @Column(unique = true, nullable = false)
    @CampoUnico(campo = CampoValidacao.CPF)
    private Long cpf;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String descricao;

    @Column(nullable = false)
    private LocalDate dataRegistro;

    public Autor() {
    }

    public Autor(AutorDto dto) {
        this.nome = dto.getNome();
        this.email = dto.getEmail();
        this.cpf = CpfHelper.parse(dto);
        this.descricao = dto.getDescricao();
        this.dataRegistro = dto.getDataRegistro();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}
