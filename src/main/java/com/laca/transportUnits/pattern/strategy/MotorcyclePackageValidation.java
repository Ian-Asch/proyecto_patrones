package com.laca.transportUnits.pattern.strategy;

import com.laca.paquete.Paquete;

public class MotorcyclePackageValidation implements ITransportPackageValidation {
    @Override
    public boolean validate(Paquete packageToValidate) {
        double maxWeight = 200;
        double maxSizeHeight = 150;
        double maxSizeWidth = 100;

        if (packageToValidate.getWeight() > maxWeight) {
            System.out.println("Error: Package exceeds the maximum weight limit for a motorcycle.");
            return false;
        }

        if (packageToValidate.getSizeHeight() > maxSizeHeight || packageToValidate.getSizeWidth() > maxSizeWidth) {
            System.out.println("Error: Package exceeds the maximum size limit for a motorcycle.");
            return false;
        }

        System.out.println("Package is valid for a motorcycle.");
        return true;
    }
}
