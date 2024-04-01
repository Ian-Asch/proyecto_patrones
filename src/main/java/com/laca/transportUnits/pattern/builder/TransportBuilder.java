package com.laca.transportUnits.pattern.builder;

import com.laca.transportUnits.Transport;
import com.laca.transportUnits.enums.TransportType;

public class TransportBuilder implements ITransport {

    private Transport transport;

    public TransportBuilder() {
        this.transport = new Transport();
    }

    private int UnitID;
    private String Name;
    private String Plate;
    private int SizeHeight;
    private int SizeWidth;
    private TransportType Type;
    private int MaxWeight;
    private int CarrierID;

    @Override
    public void setUnitID(int unitID) {
        transport.setUnitID(unitID);
    }
    @Override
    public void setName(String name) {
        transport.setName(name);
    }
    @Override
    public void setPlate(String plate) {
        transport.setPlate(plate);
    }
    @Override
    public void setSizeHeight(int sizeHeight) {
        transport.setSizeHeight(sizeHeight);
    }
    @Override
    public void setSizeWidth(int sizeWidth) {
        transport.setSizeWidth(sizeWidth);
    }
    @Override
    public void setType(String type) {
        transport.setType(type);
    }
    @Override
    public void setMaxWeight(int maxWeight) {
        transport.setMaxWeight(maxWeight);
    }
    @Override
    public void setCarrierID(int carrierID) {
        transport.setCarrierID(carrierID);
    }

    public Transport build() {
        return transport;
    }
}
