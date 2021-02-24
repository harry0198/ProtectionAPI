package com.haroldstudios.protapi.core.components;

import com.haroldstudios.protapi.core.Point3D;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;

public class MathUtil {

    /**
     * Gets the lesser point from world bordfer
     * @param world World to get worldborder points from
     * @return Point3D lesser and greater value of worldborder [0] = Lesser [1] = Greater
     */
    public static Point3D[] getPointsOfWorldBorder(final World world) {
        final Point3D[] points = new Point3D[2];

        final WorldBorder wb = world.getWorldBorder();
        final int radius = (int) wb.getSize() / 2;
        final Location center = wb.getCenter();

        points[0] = new Point3D(center.getBlockX() - radius, -64, center.getBlockZ() - radius);
        points[1] = new Point3D(center.getBlockX() + radius, world.getMaxHeight(), center.getBlockZ() + radius);
        return points;
    }

    // Method for getting the maximum value
    public static int getMax(int[] inputArray){
        int maxValue = inputArray[0];
        for(int i=1;i < inputArray.length;i++){
            if(inputArray[i] > maxValue){
                maxValue = inputArray[i];
            }
        }
        return maxValue;
    }

    // Method for getting the minimum value
    public static int getMin(int[] inputArray){
        int minValue = inputArray[0];
        for(int i=1;i<inputArray.length;i++){
            if(inputArray[i] < minValue){
                minValue = inputArray[i];
            }
        }
        return minValue;
    }



}