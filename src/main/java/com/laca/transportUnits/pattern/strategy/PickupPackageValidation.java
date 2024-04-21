package com.laca.transportUnits.pattern.strategy;

import com.laca.paquete.Paquete;

public class PickupPackageValidation implements ITransportPackageValidation {
    @Override
    public boolean validate(Paquete packageToValidate) {
        double maxWeight = 500;
        double maxSizeHeight = 275;
        double maxSizeWidth = 175;

        if (packageToValidate.getWeight() > maxWeight) {
            System.out.println("Error: Package exceeds the maximum weight limit for a pickup.");
            return false;
        }

        if (packageToValidate.getSizeHeight() > maxSizeHeight || packageToValidate.getSizeWidth() > maxSizeWidth) {
            System.out.println("Error: Package exceeds the maximum size limit for a pickup.");
            return false;
        }

        System.out.println("Package is valid for a pickup.");
        return true;
    }
}
