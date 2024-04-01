package com.laca.transportUnits.pattern.director;

import com.laca.transportUnits.pattern.builder.ITransport;
import com.laca.transportUnits.enums.TransportType;

public class TransportBuilderDirector {

    private int UnitID;
    private String Name;
    private String Plate;
    private int SizeHeight;
    private int SizeWidth;
    private String Type;
    private int MaxWeight;
    private int CarrierID;

    public void buildOnFoot(ITransport transport) {
        transport.setUnitID(UnitID);
        transport.setName(Name);
        transport.setPlate(Plate);
        transport.setSizeHeight(SizeHeight);
        transport.setSizeWidth(SizeWidth);
        transport.setType("On Foot");
        transport.setMaxWeight(MaxWeight);
        transport.setCarrierID(CarrierID);
    }

    public void buildMotorcycle(ITransport transport) {
        transport.setUnitID(UnitID);
        transport.setName(Name);
        transport.setPlate(Plate);
        transport.setSizeHeight(SizeHeight);
        transport.setSizeWidth(SizeWidth);
        transport.setType("Motorcycle");
        transport.setMaxWeight(MaxWeight);
        transport.setCarrierID(CarrierID);
    }

    public void buildTruck(ITransport transport) {
        transport.setUnitID(UnitID);
        transport.setName(Name);
        transport.setPlate(Plate);
        transport.setSizeHeight(SizeHeight);
        transport.setSizeWidth(SizeWidth);
        transport.setType("Truck");
        transport.setMaxWeight(MaxWeight);
        transport.setCarrierID(CarrierID);
    }

    public void buildPickup(ITransport transport) {
        transport.setUnitID(UnitID);
        transport.setName(Name);
        transport.setPlate(Plate);
        transport.setSizeHeight(SizeHeight);
        transport.setSizeWidth(SizeWidth);
        transport.setType("Pickup");
        transport.setMaxWeight(MaxWeight);
        transport.setCarrierID(CarrierID);
    }


    public void pickValues(int UnitID, String Name, String Plate, int SizeHeight, int SizeWidth, int MaxWeight, int CarrierID) {
        this.UnitID = UnitID;
        this.Name = Name;
        this.Plate = Plate;
        this.SizeHeight = SizeHeight;
        this.SizeWidth = SizeWidth;
        this.MaxWeight = MaxWeight;
        this.CarrierID = CarrierID;
    }
}