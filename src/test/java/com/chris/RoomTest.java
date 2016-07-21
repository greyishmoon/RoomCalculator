package com.chris;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Chris on 18/07/2016.
 */
public class RoomTest {

    private Room metreRoom;
    private Room cmRoom;
    private Room mmRoom;
    private Room smallRoom;
    private Room largeRoom;

    @Before
    public void initRooms() {
        metreRoom = new Room(new double[] {3, 4, 2});
        cmRoom = new Room(new double[] {3.73, 4.99, 2.20});
        mmRoom = new Room(new double[] {3.7555, 4.9999, 2.2777});
        smallRoom = new Room(new double[] {0.01, 0.01, 0.01});
        largeRoom = new Room(new double[] {993.7444, 994.4666, 992.2777});
    }


    @Test
    public void testGetFloorArea() throws Exception {
        assertEquals("error in floorArea metreRoom", 12, metreRoom.getFloorArea(), 0.000000001);
        assertEquals("error in floorArea cmRoom", 18.6127, cmRoom.getFloorArea(), 0.000000001);
        assertEquals("error in floorArea mmRoom", 18.8, mmRoom.getFloorArea(), 0.000000001);
        assertEquals("error in floorArea smallRoom", 0.0001, smallRoom.getFloorArea(), 0.000000001);
        assertEquals("error in floorArea largeRoom", 988244.6178, largeRoom.getFloorArea(), 0.000000001);
    }

    @Test
    public void testGetWallArea() throws Exception {
        assertEquals("error in getWallArea metreRoom", 28, metreRoom.getWallArea(), 0.000000001);
        assertEquals("error in getWallArea cmRoom", 38.3680, cmRoom.getWallArea(), 0.000000001);
        assertEquals("error in getWallArea mmRoom", 39.9456, mmRoom.getWallArea(), 0.000000001);
        assertEquals("error in getWallArea smallRoom", 0.0004, smallRoom.getWallArea(), 0.000000001);
        assertEquals("error in getWallArea largeRoom", 3945722.0376, largeRoom.getWallArea(), 0.000000001);
    }

    @Test
    public void testGetVolume() throws Exception {
        assertEquals("error in getVolume metreRoom", 24, metreRoom.getVolume(), 0.000000001);
        assertEquals("error in getVolume cmRoom", 40.947940, cmRoom.getVolume(), 0.000000001);
        assertEquals("error in getVolume mmRoom", 42.864000, mmRoom.getVolume(), 0.000000001);
        assertEquals("error in getVolume smallRoom", 0.000001, smallRoom.getVolume(), 0.000000001);
        assertEquals("error in getVolume largeRoom", 980615369.350584, largeRoom.getVolume(), 0.000000001);
    }

    @Test
    public void testGetPaintRequired() throws Exception {
        assertEquals("error in getVolume metreRoom", 3, metreRoom.getPaintRequired());
        assertEquals("error in getVolume cmRoom", 4, cmRoom.getPaintRequired());
        assertEquals("error in getVolume mmRoom", 4, mmRoom.getPaintRequired());
        assertEquals("error in getVolume smallRoom", 1, smallRoom.getPaintRequired());
        assertEquals("error in getVolume largeRoom", 328811, largeRoom.getPaintRequired());
        // Test with altered coverage value
        metreRoom.setCoverage(3);
        largeRoom.setCoverage(15);
        assertEquals("error in getVolume metreRoom altered coverage", 10, metreRoom.getPaintRequired());
        assertEquals("error in getVolume largeRoom altered coverage", 263049, largeRoom.getPaintRequired());
    }

    @Test
    public void testConstructorValidation() {
        try {
            new Room(new double[] {1, 2});
            fail("Expected an IllegalArgumentException (incorrect # elements in array) to be thrown");
        } catch (IllegalArgumentException anIllegalArgumentException) {
            assertEquals("Dimensions array requires 3 doubles - (Width, Length, Height)", anIllegalArgumentException.getMessage());
        }

        try {
            new Room(new double[] {1, 2, 3, 4});
            fail("Expected an IllegalArgumentException (incorrect # elements in array) to be thrown");
        } catch (IllegalArgumentException anIllegalArgumentException) {
            assertEquals("Dimensions array requires 3 doubles - (Width, Length, Height)", anIllegalArgumentException.getMessage());
        }

        try {
            new Room(new double[] {1, 2, -1});
            fail("Expected an IllegalArgumentException (non-positive dimension) to be thrown");
        } catch (IllegalArgumentException anIllegalArgumentException) {
            assertEquals("Dimensions must be 1cm or greater (>= 0.01)", anIllegalArgumentException.getMessage());
        }

        try {
            new Room(new double[] {1, 0, 3});
            fail("Expected an IllegalArgumentException (non-positive dimension) to be thrown");
        } catch (IllegalArgumentException anIllegalArgumentException) {
            assertEquals("Dimensions must be 1cm or greater (>= 0.01)", anIllegalArgumentException.getMessage());
        }

        try {
            new Room(new double[] {0.009, 2, 3});
            fail("Expected an IllegalArgumentException (non-positive dimension) to be thrown");
        } catch (IllegalArgumentException anIllegalArgumentException) {
            assertEquals("Dimensions must be 1cm or greater (>= 0.01)", anIllegalArgumentException.getMessage());
        }
    }


}
