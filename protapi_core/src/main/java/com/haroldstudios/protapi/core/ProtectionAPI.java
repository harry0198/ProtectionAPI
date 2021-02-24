package com.haroldstudios.protapi.core;

import com.haroldstudios.protapi.core.octree.Octane;

public final class ProtectionAPI {

    /**
     * Checks if a cuboid overlaps another octane
     *
     * @param oct Octane to check for
     * @param min Bottom corner to check
     * @param max Top corner to check
     * @return true if overlapping / contained within octane
     */
    public static boolean isCuboidOverlapping(Octane oct, Point3D min, Point3D max) {

        Point3D min1 = oct.getMin();
        Point3D max2 = oct.getMax();

        return isCuboidOverlapping(
                new Point3D (min1.x, min1.y, min1.z),
                new Point3D (max2.x, max2.y, max2.z),
                min,
                max
        );
    }

    /**
     * Checks if a cuboid overlaps another cuboid
     *
     * @param min1 Bottom corner to check 1
     * @param max2 Top corner to check 1
     * @param min Bottom corner to check 2
     * @param max Bottom corner to check 2
     * @return true if is overlapping points
     */
    public static boolean isCuboidOverlapping(Point3D min1, Point3D max2, Point3D min, Point3D max) {

        if(!intersectsDimension(min.x, max.x, min1.x, max2.x))
            return false;

        if(!intersectsDimension(min.y, max.y, min1.y, max2.y))
            return false;

        if(!intersectsDimension(min.z, max.z,min1.z, max2.z))
            return false;

        return true;
    }

    private static boolean intersectsDimension(int aMin, int aMax, int bMin, int bMax) {
        return aMin <= bMax && aMax >= bMin;
    }


}