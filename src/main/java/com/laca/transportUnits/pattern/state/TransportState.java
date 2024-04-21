package com.laca.transportUnits.pattern.state;

import com.laca.transportUnits.Transport;

public abstract class TransportState {
    protected Transport transport;

    public TransportState(Transport transport) {
        this.transport = transport;
    }

    public boolean asignarEntrega() {
        return false;
    }
    public void terminarEntrega() {}
    public void deshabilitar() {}
    public void habilitar() {}
}
