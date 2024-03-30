package com.laca.entity.transport.builder;

import com.laca.entity.transport.Transport;
import com.laca.entity.transport.enums.TransportType;

public interface ITransport {

    void setUnitID(int UnitID);
    void setName(String Name);
    void setPlate(String Plate);
    void setSizeHeight(int SizeHeight);
    void setSizeWidth(int SizeWidth);
    void setType(TransportType Type);
    void setMaxWeight(int MaxWeight);
    void setCarrierID(int CarrierID);

    Transport getTransport();
}