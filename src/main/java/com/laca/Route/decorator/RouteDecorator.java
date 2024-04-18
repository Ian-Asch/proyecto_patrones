package com.laca.Route.decorator;

import com.laca.Route.DurationType;
import com.laca.Route.Route;
import com.laca.Route.RouteInterface;

public abstract class RouteDecorator implements RouteInterface {
    protected Route routeDecorated;

    public RouteDecorator(Route routeDecorated) {
        this.routeDecorated = routeDecorated;
    }

    @Override
    public Long getRouteID() {
        return routeDecorated.getRouteID();
    }

    @Override
    public void setRouteID(Long routeID) {
        routeDecorated.setRouteID(routeID);
    }

    @Override
    public String getName() {
        return routeDecorated.getName();
    }

    @Override
    public void setName(String name) {
        routeDecorated.setName(name);
    }

    @Override
    public String getDescription() {
        return routeDecorated.getDescription();
    }

    @Override
    public void setDescription(String description) {
        routeDecorated.setDescription(description);
    }

    @Override
    public int getStartPointID() {
        return routeDecorated.getStartPointID();
    }

    @Override
    public void setStartPointID(int startPointID) {
        routeDecorated.setStartPointID(startPointID);
    }

    @Override
    public int getEndPointID() {
        return routeDecorated.getEndPointID();
    }

    @Override
    public void setEndPointID(int endPointID) {
        routeDecorated.setEndPointID(endPointID);
    }

    @Override
    public double getShippingCost() {
        return routeDecorated.getShippingCost();
    }

    @Override
    public void setShippingCost(double shippingCost) {
        routeDecorated.setShippingCost(shippingCost);
    }

    @Override
    public boolean isApproved() {
        return routeDecorated.isApproved();
    }

    @Override
    public void setApproved(boolean approved) {
        routeDecorated.setApproved(approved);
    }

    @Override
    public DurationType getDurationType() {
        return routeDecorated.getDurationType();
    }

    @Override
    public void setDurationType(DurationType durationType) {
        routeDecorated.setDurationType(durationType);
    }
}
