package com.laca.transportUnits.pattern.strategy;

import com.laca.paquete.Paquete;

public class TruckPackageValidation implements ITransportPackageValidation {
    @Override
    public boolean validate(Paquete packageToValidate) {
        double maxWeight = 10000;
        double maxSizeHeight = 400;
        double maxSizeWidth = 250;

        if (packageToValidate.getWeight() > maxWeight) {
            System.out.println("Error: Package exceeds the maximum weight limit for a truck.");
            return false;
        }

        if (packageToValidate.getSizeHeight() > maxSizeHeight || packageToValidate.getSizeWidth() > maxSizeWidth) {
            System.out.println("Error: Package exceeds the maximum size limit for a truck.");
            return false;
        }

        System.out.println("Package is valid for a truck.");
        return true;
    }
}
