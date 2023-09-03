package com.seeddesafiocdc.helper;

import com.seeddesafiocdc.controller.dto.AutorDto;
import com.seeddesafiocdc.modelo.Autor;

public class CpfHelper {

    private CpfHelper() {
    }

    public static Long parse(AutorDto dto) {
        return Long.valueOf(dto.getCpf().replaceAll("[^0-9]", ""));
    }

    public static String parse(Autor autor) {
        return String.valueOf(autor.getCpf())
                .replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

}
