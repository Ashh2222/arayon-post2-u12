package com.empresa.pedidos.dominio.puertos;

import com.empresa.pedidos.eventos.PedidoProcesadoEvent;

public interface ServicioNotificacion {

    void notificar(PedidoProcesadoEvent evento);
}