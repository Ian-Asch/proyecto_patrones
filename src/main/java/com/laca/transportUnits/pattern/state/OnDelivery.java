package com.laca.transportUnits.pattern.state;

import com.laca.transportUnits.Transport;

public class OnDelivery extends TransportState{
    public OnDelivery(Transport transport) {
        super(transport);
    }

    @Override
    public void terminarEntrega() {
        transport.changeState(new Ready(transport));
    }

    public void cancelarEntrega() {
        transport.changeState(new Ready(transport));
    }
}
