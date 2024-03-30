package com.laca.entity.transport;

import com.laca.entity.transport.enums.TransportType;

public class Transport {

    private int UnitID;
    private String Name;
    private String Plate;
    private int SizeHeight;
    private int SizeWidth;
    private TransportType Type;
    private int MaxWeight;
    private int CarrierID;

    public Transport() {
    }
    public Transport(int UnitID, String Name, String Plate, int SizeHeight, int SizeWidth, TransportType Type, int MaxWeight, int CarrierID) {
        this.UnitID = UnitID;
        this.Name = Name;
        this.Plate = Plate;
        this.SizeHeight = SizeHeight;
        this.SizeWidth = SizeWidth;
        this.Type = Type;
        this.MaxWeight = MaxWeight;
        this.CarrierID = CarrierID;
    }

    public int getUnitID() {
        return UnitID;
    }
    public void setUnitID(int unitID) {
        UnitID = unitID;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public String getPlate() {
        return Plate;
    }
    public void setPlate(String plate) {
        Plate = plate;
    }

    public int getSizeHeight() {
        return SizeHeight;
    }
    public void setSizeHeight(int sizeHeight) {
        SizeHeight = sizeHeight;
    }

    public int getSizeWidth() {
        return SizeWidth;
    }
    public void setSizeWidth(int sizeWidth) {
        SizeWidth = sizeWidth;
    }

    public void setType(TransportType type) {
        Type = type;
    }

    public int getMaxWeight() {
        return MaxWeight;
    }
    public void setMaxWeight(int maxWeight) {
        MaxWeight = maxWeight;
    }

    public int getCarrierID() {
        return CarrierID;
    }
    public void setCarrierID(int carrierID) {
        CarrierID = carrierID;
    }

    public TransportType getType() {
        return Type;
    }
}