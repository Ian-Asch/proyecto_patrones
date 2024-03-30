package com.laca.Route;

import com.laca.Route.prototype.RoutePrototype;

public class Route implements RoutePrototype {
    private Long RouteID;
    private String Name;
    private String Description;
    private int StartPointID;
    private int EndPointID;
    private double ShippingCost;
    private boolean Approved;
    private String DurationType;

    public Route() {
    }

    public Route(Long routeID, String name, String description, int startPointID, int endPointID, double shippingCost, boolean approved, String durationType) {
        RouteID = routeID;
        Name = name;
        Description = description;
        StartPointID = startPointID;
        EndPointID = endPointID;
        ShippingCost = shippingCost;
        Approved = approved;
        DurationType = durationType;
    }

    public Long getRouteID() {
        return RouteID;
    }

    public void setRouteID(Long routeID) {
        RouteID = routeID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getStartPointID() {
        return StartPointID;
    }

    public void setStartPointID(int startPointID) {
        this.StartPointID = startPointID;
    }

    public int getEndPointID() {
        return EndPointID;
    }

    public void setEndPointID(int endPointID) {
        EndPointID = endPointID;
    }

    public double getShippingCost() {
        return ShippingCost;
    }

    public void setShippingCost(double shippingCost) {
        ShippingCost = shippingCost;
    }

    public boolean isApproved() {
        return Approved;
    }

    public void setApproved(boolean approved) {
        Approved = approved;
    }

    public String getDurationType() {
        return DurationType;
    }

    public void setDurationType(String durationType) {
        DurationType = durationType;
    }

    @Override
    public RoutePrototype clone() {
        return new Route(this.RouteID, this.Name, this.Description, this.StartPointID, this.EndPointID, this.ShippingCost, this.Approved, this.DurationType);
    }
}
