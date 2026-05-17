package com.empresa.pedidos;

import com.empresa.pedidos.adaptadores.factory.ProcesadorPedidoFactory;
import com.empresa.pedidos.dominio.TipoPedido;
import com.empresa.pedidos.dominio.puertos.ProcesadorPedido;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProcesadorPedidoFactoryTest {

    @Autowired
    private ProcesadorPedidoFactory factory;

    @Test
    void debeRetornarProcesadorExpress() {

        ProcesadorPedido procesador =
                factory.obtener(TipoPedido.EXPRESS);

        assertEquals(
                TipoPedido.EXPRESS,
                procesador.getTipo()
        );
    }
}