package com.laca.transportUnits.pattern.state;

import com.laca.transportUnits.Transport;

public class OnDelivery extends TransportState{
    public OnDelivery(Transport transport) {
        super(transport);
    }

    @Override
    public void terminarEntrega() {
        transport.changeState(new Ready(transport));
        System.out.println("Entrega terminada");
    }

    @Override
    public boolean asignarEntrega() {
        System.out.println("Ya se asigno una entrega");
        return false;
    }

    @Override
    public void deshabilitar() {
        transport.changeState(new Disabled(transport));
        System.out.println("vehiculo deshabilitado");
    }
}
