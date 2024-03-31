package com.laca.transportUnits.pattern.director;

import com.laca.transportUnits.pattern.builder.ITransport;
import com.laca.transportUnits.enums.TransportType;

public class TransportBuilderDirector {
    public void buildOnFoot1(ITransport transport) {
        transport.setUnitID(1);
        transport.setName("Miguel");
        transport.setPlate("AAA-000");
        transport.setSizeHeight(0);
        transport.setSizeWidth(0);
        transport.setType(TransportType.On_Foot);
        transport.setMaxWeight(25);
        transport.setCarrierID(1);
    }

    public void buildMotorcycle1(ITransport transport) {
        transport.setUnitID(2);
        transport.setName("ASPID 150 CC");
        transport.setPlate("AAA-111");
        transport.setSizeHeight(1150);
        transport.setSizeWidth(2050);
        transport.setType(TransportType.Motorcycle);
        transport.setMaxWeight(150);
        transport.setCarrierID(2);
    }

    public void buildTruck1(ITransport transport) {
        transport.setUnitID(3);
        transport.setName("Hino 616 300 Automatic");
        transport.setPlate("AAA-222");
        transport.setSizeHeight(2200);
        transport.setSizeWidth(2300);
        transport.setType(TransportType.Truck);
        transport.setMaxWeight(4500);
        transport.setCarrierID(3);
    }

    public void buildPickup1(ITransport transport) {
        transport.setUnitID(4);
        transport.setName("Ford Ranger");
        transport.setPlate("AAA-333");
        transport.setSizeHeight(1884);
        transport.setSizeWidth(2208);
        transport.setType(TransportType.Pickup);
        transport.setMaxWeight(350);
        transport.setCarrierID(4);
    }
}