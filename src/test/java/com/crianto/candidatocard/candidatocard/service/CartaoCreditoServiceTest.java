package com.crianto.candidatocard.candidatocard.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CartaoCreditoServiceTest {

    @InjectMocks
    private CartaoCreditoService service;

    @Test
    public void validaNumeroCartaoCredito() {
        // cartão gerado em: https://www.4devs.com.br/gerador_de_numero_cartao_credito
        String numeroCartao = "4716.6530-7138X8543";

        String numero = service.validaNumeroCartao(numeroCartao);

        assertEquals("4716653071388543", numero);
    }
}
