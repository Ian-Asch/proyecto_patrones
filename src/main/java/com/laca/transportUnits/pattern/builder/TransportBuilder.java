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
        UnitID = unitID;
    }
    @Override
    public void setName(String name) {
        Name = name;
    }
    @Override
    public void setPlate(String plate) {
        Plate = plate;
    }
    @Override
    public void setSizeHeight(int sizeHeight) {
        SizeHeight = sizeHeight;
    }
    @Override
    public void setSizeWidth(int sizeWidth) {
        SizeWidth = sizeWidth;
    }
    @Override
    public void setType(TransportType type) {
        Type = type;
    }
    @Override
    public void setMaxWeight(int maxWeight) {
        MaxWeight = maxWeight;
    }
    @Override
    public void setCarrierID(int carrierID) {
        CarrierID = carrierID;
    }

    public Transport getTransport() {
        return new Transport(this.UnitID, this.Name, this.Plate, this.SizeHeight, this.SizeWidth, this.Type, this.MaxWeight, this.CarrierID);
    }
}
