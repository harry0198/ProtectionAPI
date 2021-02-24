package com.haroldstudios.protapi.core.octree;

import com.haroldstudios.protapi.core.Point3D;

public final class Octane {

    private final Point3D min,
            max;

    /**
     * Class Constructor
     * @param min Minimum corner of octane
     * @param max Maximum corner of octane
     */
    public Octane(Point3D min, Point3D max){
        this.min = min;
        this.max = max;
    }

    /**
     * Gets Maximum corner of octane
     * @return Maximum point of Octane as Point3D
     */
    public Point3D getMax() {
        return max;
    }

    /**
     * Gets Minimum corner of octane
     * @return Minimum point of Octane as Point3D
     */
    public Point3D getMin() {
        return min;
    }

    /**
     * Checks if the vector point is between the Octane Bounds
     * @param p Point to check
     * @return If point is between the octane's boundaries
     */
    public boolean isBetweenAxis(Point3D p){
        return ((p.x > min.x) && (p.x < max.x) && (p.y > min.y) && (p.y < max.y) && (p.z > min.z) && (p.z < max.z));
    }


}