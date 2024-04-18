package com.laca.Route.decorator.concreteDecorator;

import com.laca.Route.DurationType;
import com.laca.Route.Route;
import com.laca.Route.decorator.RouteDecorator;

public class ShortRouteDecorator extends RouteDecorator {

    public ShortRouteDecorator(Route routeDecorated) {
        super(routeDecorated);
    }

    @Override
    public Long getRouteID() {
        return super.getRouteID();
    }

    @Override
    public void setRouteID(Long routeID) {
        super.setRouteID(routeID);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public void setDescription(String description) {
        super.setDescription(description);
    }

    @Override
    public int getStartPointID() {
        return super.getStartPointID();
    }

    @Override
    public void setStartPointID(int startPointID) {
        super.setStartPointID(startPointID);
    }

    @Override
    public int getEndPointID() {
        return super.getEndPointID();
    }

    @Override
    public void setEndPointID(int endPointID) {
        super.setEndPointID(endPointID);
    }

    @Override
    public double getShippingCost() {
        return super.getShippingCost() + 10.0;
    }

    @Override
    public void setShippingCost(double shippingCost) {
        super.setShippingCost(shippingCost);
    }

    @Override
    public boolean isApproved() {
        return super.isApproved();
    }

    @Override
    public void setApproved(boolean approved) {
        super.setApproved(approved);
    }

    @Override
    public DurationType getDurationType() {
        return super.getDurationType();
    }

    @Override
    public void setDurationType(DurationType durationType) {
        super.setDurationType(durationType);
    }
}