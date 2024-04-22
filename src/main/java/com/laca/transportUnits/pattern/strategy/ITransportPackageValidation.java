package com.laca.transportUnits.pattern.strategy;

import com.laca.paquete.Paquete;

public interface ITransportPackageValidation {
    boolean validate(Paquete packageToValidate);
}
