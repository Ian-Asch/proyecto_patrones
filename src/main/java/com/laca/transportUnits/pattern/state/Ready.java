package com.laca.transportUnits.pattern.state;

import com.laca.transportUnits.Transport;

public class Ready extends TransportState {
    public Ready(Transport transport) {
        super(transport);
    }

    @Override
    public void asignarEntrega() {
        transport.changeState(new OnDelivery(transport));
    }

    public void deshabilitar() {
        transport.changeState(new Disabled(transport));
    }
}
