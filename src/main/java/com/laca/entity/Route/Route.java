package com.laca.entity.Route;

public class Route implements RoutePrototype {
    private Long id;
    private int startingPoint;
    private int pointArrival;
    private String name;
    private String description;
    private String type;

    public Route() {
    }

    public Route(int startingPoint, int pointArrival, String name, String description, String type) {
        this.startingPoint = startingPoint;
        this.pointArrival = pointArrival;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public Long getId() {
        return id;
    }
    public int getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(int startingPoint) {
        this.startingPoint = startingPoint;
    }

    public int getPointArrival() {
        return pointArrival;
    }

    public void setPointArrival(int pointArrival) {
        this.pointArrival = pointArrival;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public RoutePrototype clone() {
        return new Route(this.startingPoint, this.pointArrival, this.name, this.description, this.type);
    }

    @Override
    public String toString() {
        return "ConcreteRoute{" +
                "startingPoint=" + startingPoint +
                ", pointArrival=" + pointArrival +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
