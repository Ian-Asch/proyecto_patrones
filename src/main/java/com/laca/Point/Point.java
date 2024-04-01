package com.laca.Point;

public class Point {
    private Long PointID;
    private String Name;
    private String Description;
    private double Longitude;
    private double Latitude;
    private String Coordinates;

    public Point() {
    }

    public Point(Long pointID, String name, String description, double longitude, double latitude) {
        PointID = pointID;
        Name = name;
        Description = description;
        Longitude = longitude;
        Latitude = latitude;
    }

    public Long getPointID() {
        return PointID;
    }

    public void setPointID(Long pointID) {
        PointID = pointID;
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

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public String getCoordinates() {
        return "Point(" + Longitude + " " + Latitude + ")";
    }
}
