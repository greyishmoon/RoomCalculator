package com.chris;

/**
 * Class implements a simple room that calculates room properties based on room dimensions
 * @author Chris Blythe
 * @version 1.0
 */

public class Room {

    // Room dimensions
    private double width;                // metres - precision 0.01 (cm)
    private double length;               // metres - precision 0.01 (cm)
    private double height;               // metres - precision 0.01 (cm)
    // Room properties
    private double floorArea = 0;        // square metres - precision 0.0001 (cm2)
    private double wallArea = 0;         // square metres - precision 0.0001 (cm2)
    private double volume = 0;           // cubic metres - precision 0.000001 (cm3)
    private double coverage = 12;        // Paint coverage - square meters per litre - default 12 m2 (wall emulsion)

    /**
     * Constructs a room based on dimensions W.WW x L.LL x H.HH in metres,
     * and calculates room properties of floor area, room volume and wall area
     * @param dimensions array of 3 doubles of room dimensions (Width, Length, Height) in metres to the nearest cm
     * @throws IllegalArgumentException if array doesn't have 3 elements, or if any dimension is less than 1cm
     */
    public Room(double[] dimensions) {
        // Validate arguments
        if (dimensions.length != 3) {
            throw new IllegalArgumentException("Dimensions array requires 3 doubles - (Width, Length, Height)");
        }
        for (double elem:dimensions) {
            if (elem < 0.01) {
                throw new IllegalArgumentException("Dimensions must be 1cm or greater (>= 0.01)");
            }
        }
        width = Math.round (dimensions[0] * 100.0) / 100.0;
        length = Math.round (dimensions[1] * 100.0) / 100.0;
        height = Math.round (dimensions[2] * 100.0) / 100.0;
        calcRoom();
    }

    /**
     * @return width of room in metres - precision 0.01 (cm)
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return length of room in metres - precision 0.01 (cm)
     */
    public double getLength() {
        return length;
    }

    /**
     * @return height of room in metres - precision 0.01 (cm)
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return floor area of room in square metres - precision 0.0001 (cm2)
     */
    public double getFloorArea() {
        return floorArea;
    }

    /**
     * @return total area of rooms walls in square metres - precision 0.0001 (cm2)
     */
    public double getWallArea() {
        return wallArea;
    }

    /**
     * @return volume of room in cubic metres - precision 0.000001 (cm3)
     */
    public double getVolume() {
        return volume;
    }

    /**
     * Coverage is the area in square metres a litre of paint will cover in a single coat.
     * Defaults to 12 m2/litre on room instantiation.
     * @param coverage square metres per litre
     */
    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    /**
     * @return current setting for paint coverage in square metres per litre
     */
    public double getCoverage() {
        return coverage;
    }

    /**
     * Calculates the number of litres of paint required to paint all walls in room, rounded up to nearest whole litre,
     * based on total wall area and paint coverage setting
     * @return
     */
    public int getPaintRequired() {
        return (int)Math.ceil(wallArea / coverage);
    }

    /**
     * Prints calculated room properties of floor area, paint required for walls and room volume
     */
    public void printRoomProperties() {
        System.out.println("\nFor the room dimensions: " + width + " x " + length + " x " + height + " m:");
        System.out.println("Floor area = " + floorArea + " m2.");
        System.out.println("Walls require " + getPaintRequired() + " litres of paint for a single coat at " + coverage + " m2 per litre.");
        System.out.println("Room volume = " + volume + " m3.");
    }

    /**
     * Calculates and sets room areas and volume from dimensions
     */
    private void calcRoom() {
        floorArea = Math.round(width * length * 10000.0)/10000.0;
        wallArea = Math.round(((width * 2) + (length * 2)) * height * 10000.0)/10000.0;
        volume = Math.round(width * length * height *1000000.0)/1000000.0;
    }
}
