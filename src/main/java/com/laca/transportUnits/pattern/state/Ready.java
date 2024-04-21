package com.laca.transportUnits.pattern.state;

import com.laca.transportUnits.Transport;

public class Ready extends TransportState {
    public Ready(Transport transport) {
        super(transport);
    }

    @Override
    public boolean asignarEntrega() {
        transport.changeState(new OnDelivery(transport));
        System.out.println("Paquete asignado");
        return true;
    }

    @Override
    public void terminarEntrega() {
        System.out.println("No hay una entrega para este vehiculo");
    }

    public void deshabilitar() {
        transport.changeState(new Disabled(transport));
        System.out.println("vehiculo deshabilitado");
    }
}
