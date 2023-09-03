package com.seeddesafiocdc.controller.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seeddesafiocdc.modelo.Autor;

public class DetalhesAutorDto {

    private String nome;
    private String cpf;
    private String email;
    private String descricao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataRegistro;

    public DetalhesAutorDto() {
    }

    public DetalhesAutorDto(String nome, Long cpf, String email, String descricao, LocalDate dataRegistro) {
        this.nome = nome;
        this.descricao = descricao;
        this.cpf = String.valueOf(cpf)
                .replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})","$1.$2.$3-$4");
        this.email = email;
        this.dataRegistro = dataRegistro;
    }

    public DetalhesAutorDto(Autor autor) {
        this(autor.getNome(),
                autor.getCpf(),
                autor.getEmail(),
                autor.getDescricao(),
                autor.getDataRegistro());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
