package com.laca.transportUnits;

import com.laca.paquete.Paquete;
import com.laca.transportUnits.pattern.state.Ready;
import com.laca.transportUnits.pattern.state.TransportState;
import com.laca.transportUnits.pattern.strategy.ITransportPackageValidation;

public class Transport {

    private int UnitID;
    private String Name;
    private String Plate;
    private int SizeHeight;
    private int SizeWidth;
    private String Type;
    private int MaxWeight;
    private int CarrierID;

    private TransportState state;
    private Paquete paquete;

    private ITransportPackageValidation packageValidationStrategy;

    public Transport() {
    }
    public Transport(int UnitID, String Name, String Plate, int SizeHeight, int SizeWidth, String Type, int MaxWeight, int CarrierID) {
        this.UnitID = UnitID;
        this.Name = Name;
        this.Plate = Plate;
        this.SizeHeight = SizeHeight;
        this.SizeWidth = SizeWidth;
        this.Type = Type;
        this.MaxWeight = MaxWeight;
        this.CarrierID = CarrierID;

        this.state = new Ready(this);
    }
    public Transport(ITransportPackageValidation packageValidationStrategy) {
        this.packageValidationStrategy = packageValidationStrategy;
    }

    public void changeState(TransportState state) {
        this.state = state;
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

    public void setType(String type) {
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

    public Paquete getPaquete() {
        return paquete;
    }
    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }
    public boolean validatePackage(Paquete packageToValidate) {
        return packageValidationStrategy.validate(packageToValidate);
    }

    public String getType() {
        return Type;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "UnitID=" + UnitID +
                ", Name='" + Name + '\'' +
                ", Plate='" + Plate + '\'' +
                ", SizeHeight=" + SizeHeight +
                ", SizeWidth=" + SizeWidth +
                ", Type=" + Type +
                ", MaxWeight=" + MaxWeight +
                ", CarrierID=" + CarrierID +
                '}';
    }
}