package com.seeddesafiocdc.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seeddesafiocdc.helper.CpfHelper;
import com.seeddesafiocdc.modelo.Autor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class AutorDto {

    @NotEmpty
    private String nome;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String descricao;

    @NotEmpty
    @CPF
    private String cpf;

    @NotEmpty
    @Past
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataRegistro;

    public AutorDto() {
    }

    public AutorDto(Autor autor) {
        this.nome = autor.getNome();
        this.descricao = autor.getDescricao();
        this.dataRegistro = autor.getDataRegistro();
        this.email = autor.getEmail();
        this.cpf = CpfHelper.parse(autor);
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}
