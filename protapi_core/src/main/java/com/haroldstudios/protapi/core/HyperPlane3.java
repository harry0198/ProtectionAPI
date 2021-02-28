package com.haroldstudios.protapi.core;

import com.google.common.collect.ImmutableList;
import com.haroldstudios.protapi.core.components.MathUtil;

import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

/*
    For naming context, HyperPlane typically refers to a dimensional space higher than 2.
    This class' naming is HyperPlane<dimension>. 2 Dimensional is referred as Plane.
    Because the 4th dimension is coming!!!
    It sounded nice...
 */
public final class HyperPlane3 {

    private final ImmutableList<Point3D> points;
    // lazy init
    private Point3D min, max;
    private Area area;

    public HyperPlane3(final List<Point3D> points) {
        if (points.size() < 2) throw new IllegalArgumentException("More than 2 points required!");
        // If we only have min and max corner of area
        if (points.size() == 2) {
            List<Point3D> pointList = new ArrayList<>();
            // get all 4 corners
            Point3D p1 = new Point3D(points.get(0).x, points.get(0).y, points.get(0).z);
            Point3D p2 = new Point3D(points.get(1).x, points.get(1).y, points.get(1).z);
            Point3D p3 = new Point3D(p1.x, Math.min(p1.y,p2.y), p2.y); // Sets Y to Min point of Y
            Point3D p4 = new Point3D(p2.x, Math.max(p1.y,p2.y), p1.y); // Sets Y to Max point Y

            pointList.add(p1);
            pointList.add(p2);
            pointList.add(p3);
            pointList.add(p4);

            this.points = ImmutableList.copyOf(pointList);

            return;
        }
        this.points = ImmutableList.copyOf(points);
    }

    public Area getArea() {
        if (area != null) return area;

        int numPoints = getPoints().size();
        int[] xCoords = new int[numPoints];
        int[] zCoords = new int[numPoints];

        int i = 0;
        for (Point3D point : getPoints()) {
            xCoords[i] = point.x; // X
            zCoords[i] = point.z; // Z
            i++;
        }

        Polygon polygon = new Polygon(xCoords, zCoords, numPoints);
        area = new Area(polygon);
        return area;
    }

    public Point3D getMin() {
        if (min != null) return min;

        min = new Point3D(
                MathUtil.getMin(getPoints().stream().mapToInt(p -> p.x).toArray()),
                MathUtil.getMin(getPoints().stream().mapToInt(p -> p.y).toArray()),
                MathUtil.getMin(getPoints().stream().mapToInt(p -> p.z).toArray())
        );
        return min;
    }

    public Point3D getMax() {
        if (max != null) return max;
        max = new Point3D(
                MathUtil.getMax(getPoints().stream().mapToInt(p -> p.x).toArray()),
                MathUtil.getMax(getPoints().stream().mapToInt(p -> p.y).toArray()),
                MathUtil.getMax(getPoints().stream().mapToInt(p -> p.z).toArray())
        );
        return max;
    }

    public ImmutableList<Point3D> getPoints() {
        return this.points;
    }
}