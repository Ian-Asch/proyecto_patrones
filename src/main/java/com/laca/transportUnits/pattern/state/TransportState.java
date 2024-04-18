package com.laca.transportUnits.pattern.state;

import com.laca.transportUnits.Transport;

public abstract class TransportState {
    protected Transport transport;

    public TransportState(Transport transport) {
        this.transport = transport;
    }

    public void asignarEntrega(){}
    public void terminarEntrega(){}
    //public void asignarPaquete(){}
    //public void asignarRuta(){}
}
