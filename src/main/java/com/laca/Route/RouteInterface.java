package com.laca.Route;

public interface RouteInterface {
    Long getRouteID();
    void setRouteID(Long routeID);

    String getName();
    void setName(String name);

    String getDescription();
    void setDescription(String description);

    int getStartPointID();
    void setStartPointID(int startPointID);

    int getEndPointID();
    void setEndPointID(int endPointID);

    double getShippingCost();
    void setShippingCost(double shippingCost);

    boolean isApproved();
    void setApproved(boolean approved);

    DurationType getDurationType();
    void setDurationType(DurationType durationType);
}
