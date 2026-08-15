package com.sistema_financeiro.service;

import java.util.function.Consumer;

public class VerificadorDeNulos {
    public static <T> void atualizarSeNaoNulo(T valorNovo, Consumer<T> setter) {
        if (valorNovo != null) {
            setter.accept(valorNovo);
        }
    }
}
