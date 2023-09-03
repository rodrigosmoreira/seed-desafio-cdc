package com.seeddesafiocdc.controller;

import com.seeddesafiocdc.controller.dto.AutorDto;
import com.seeddesafiocdc.controller.dto.DetalhesAutorDto;
import com.seeddesafiocdc.exception.AutorNaoCadastradoException;
import com.seeddesafiocdc.modelo.Autor;
import com.seeddesafiocdc.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping
    public ResponseEntity<AutorDto> cadastrarAutor(@RequestBody @Valid AutorDto form, UriComponentsBuilder uriBuilder) {
        var autor = new Autor(form);

        autorRepository.save(autor);

        var uri = uriBuilder.path("/autor/{email}").buildAndExpand(autor.getEmail()).toUri();

        return ResponseEntity.created(uri).body(form);
    }

    @GetMapping("/{email}")
    public ResponseEntity<DetalhesAutorDto> detalhesCadastroAutor(@PathVariable @NotEmpty @Email String email) {
        var autorOptional = autorRepository.findByEmail(email);

        if (autorOptional.isPresent()) {
            return ResponseEntity.ok(new DetalhesAutorDto(autorOptional.get()));
        }

        throw new AutorNaoCadastradoException();
    }

}
