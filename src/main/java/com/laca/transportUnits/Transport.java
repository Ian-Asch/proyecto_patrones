package com.laca.transportUnits;

import com.laca.Route.Route;
import com.laca.paquete.Paquete;
import com.laca.transportUnits.enums.TransportType;
import com.laca.transportUnits.pattern.state.Ready;
import com.laca.transportUnits.pattern.state.TransportState;

public class Transport {

    private int UnitID;
    private String Name;
    private String Plate;
    private int SizeHeight;
    private int SizeWidth;
    private String Type;
    private int MaxWeight;
    private int CarrierID;

    private Paquete paquete;
    private Route route;

    private TransportState state;

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

    public void changeState(TransportState state) {
        this.state = state;
    }


    public void asignarEntrega(Paquete paquete, Route route) {
        if (this.state.asignarEntrega()) {
            setPaquete(paquete);
            setRoute(route);
        }
    }

    public void terminarEntrega() {
        this.state.terminarEntrega();
    }

    public void deshabilitar() {
        this.state.deshabilitar();
    }

    public void habilitar() {
        this.state.habilitar();
    }


    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public Route getRoute() {
        return route;
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