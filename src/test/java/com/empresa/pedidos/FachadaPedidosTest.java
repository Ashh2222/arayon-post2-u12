package com.empresa.pedidos;

import com.empresa.pedidos.adaptadores.facade.FachadaPedidos;
import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.TipoPedido;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class FachadaPedidosTest {

    @Autowired
    private FachadaPedidos fachada;

    @Test
    void debeCrearPedido() {

        Pedido pedido = new Pedido();

        pedido.setSubtotal(100.0);
        pedido.setTipo(TipoPedido.ESTANDAR);

        Pedido resultado =
                fachada.crearPedido(pedido);

        assertNotNull(resultado);
    }
}