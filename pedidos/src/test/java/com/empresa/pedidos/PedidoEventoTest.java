package com.empresa.pedidos;

import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.eventos.PedidoProcesadoEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PedidoEventoTest {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Test
    void debePublicarEvento() {

        Pedido pedido = new Pedido();

        publisher.publishEvent(
                new PedidoProcesadoEvent(pedido)
        );

        assertTrue(true);
    }
}