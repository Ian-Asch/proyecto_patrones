package com.laca.transportUnits.pattern.state;

import com.laca.transportUnits.Transport;

public class Disabled extends TransportState{
    public Disabled(Transport transport) {
        super(transport);
    }

    public void habilitar() {
        transport.changeState(new Ready(transport));
    }

    @Override
    public boolean asignarEntrega() {
        System.out.println("Este vehiculo esta deshabilitado");
        return false;
    }
}
