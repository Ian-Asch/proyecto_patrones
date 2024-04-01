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
    private DurationType durationType;

    public Route() {
    }

    public Route(Long routeID, String name, String description, int startPointID, int endPointID, double shippingCost, boolean approved, DurationType durationType) {
        this.RouteID = routeID;
        this.Name = name;
        this.Description = description;
        this.StartPointID = startPointID;
        this.EndPointID = endPointID;
        this.ShippingCost = shippingCost;
        this.Approved = approved;
        this.durationType = durationType;
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
        StartPointID = startPointID;
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

    public DurationType getDurationType() {
        return durationType;
    }

    public void setDurationType(DurationType duration_Type) {
        this.durationType = duration_Type;
    }

    @Override
    public RoutePrototype clone() {
        return new Route(this.RouteID, this.Name, this.Description, this.StartPointID, this.EndPointID, this.ShippingCost, this.Approved, this.durationType);
    }
}
