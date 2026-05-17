package com.empresa.pedidos.eventos;

import com.empresa.pedidos.dominio.Pedido;

public record PedidoProcesadoEvent(Pedido pedido) {
}