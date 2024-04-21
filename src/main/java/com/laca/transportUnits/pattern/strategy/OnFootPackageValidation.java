package com.laca.transportUnits.pattern.strategy;

import com.laca.paquete.Paquete;

public class OnFootPackageValidation implements ITransportPackageValidation {
    @Override
    public boolean validate(Paquete packageToValidate) {
        double maxWeight = 50;
        double maxSizeHeight = 50;
        double maxSizeWidth = 50;

        if (packageToValidate.getWeight() > maxWeight) {
            System.out.println("Error: Package exceeds the maximum weight limit for on foot.");
            return false;
        }

        if (packageToValidate.getSizeHeight() > maxSizeHeight || packageToValidate.getSizeWidth() > maxSizeWidth) {
            System.out.println("Error: Package exceeds the maximum size limit for on foot.");
            return false;
        }

        System.out.println("Package is valid for on foot.");
        return true;
    }
}