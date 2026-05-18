package com.empresa.pedidos;

import com.empresa.pedidos.adaptadores.procesadores.ProcesadorPedidoEstandar;
import com.empresa.pedidos.dominio.Pedido;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProcesadorPedidoEstandarTest {

    @Test
    void debeProcesarPedidoEstandar() {

        ProcesadorPedidoEstandar procesador =
                new ProcesadorPedidoEstandar();

        Pedido pedido = new Pedido();
        pedido.setSubtotal(100.0);

        procesador.procesar(pedido);

        assertEquals(110.0, pedido.getCosto(), 0.01);
    }
}