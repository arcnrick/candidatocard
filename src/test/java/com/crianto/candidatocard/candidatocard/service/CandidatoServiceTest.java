package com.crianto.candidatocard.candidatocard.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CandidatoServiceTest {

    @InjectMocks
    private CandidatoService service;

    @Test
    public void validarNumeroCPF() {
        // cpf gerado em: https://www.4devs.com.br/gerador_de_cpf
        String numeroCPF = "338.287.298-60";

//        String numero = service.cpfValido(numeroCPF) + "123";
        String numero = service.cpfValido(numeroCPF);

        assertEquals("33828729860", numero);
    }
}