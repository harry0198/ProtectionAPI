package com.haroldstudios.protapi.core;

import com.haroldstudios.protapi.core.octree.Octree;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LIMBOMETHODS {

    // METHODS THAT NEED RELOCATING

//    /**
//     * Gets which regions intersect with this region from provided list
//     * @param regions List of regions to compare against
//     * @return Collection of regions that intersect
//     */
//    public Collection<Region> getIntersectingRegions(Collection<Region> regions) {
//
//        List<Region> intersectingRegions = new ArrayList<>();
//
//        regions = regions.stream()
//                .collect(Collectors.groupingBy(Region::getWorld)).get(this.getWorld());
//
//
//        // If lots of regions, use octree and check for nearest regions instead of running intersection detection on every region
//        // We must consider cost to init octree and populate
//        if (regions.size() > 25) {
//            Octree tree = new Octree(world);
//            regions.forEach(tree::insert);
//            List<Region> nearestRegions = tree.nearestRegions(this);
//            if (nearestRegions.size() < 1) return Collections.emptyList();
//            regions = nearestRegions;
//        }
//
//        Area area = getHyperPlane3().getArea();
//
//        for (Region region : regions) {
//            if (intersects(region, area)) {
//                intersectingRegions.add(region);
//            }
//        }
//
//        return intersectingRegions;
//    }
//
//    /**
//     * Checks if a Region intersects with Area
//     * @param region  Region to check against
//     * @param thisArea Area to check against
//     * @return If region intersects with area
//     */
//    public boolean intersects(Region region, Area thisArea) {
//        // By checking this we remove "unnecessary" heavier checks
//        if (intersectsBoundingBox(region)) {
//            Area testArea = region.getHyperPlane3().getArea();
//            testArea.intersect(thisArea);
//            return !thisArea.isEmpty();
//        } else {
//            return false;
//        }
//    }
//
//    public boolean intersects(Region region, Region thisRegion) {
//        return intersects(region, thisRegion.getHyperPlane3().getArea());
//    }
//
//    private boolean intersectsBoundingBox(Region region) {
//        // Readability
//        Point3D min = hyperPlane3.getMin();
//        Point3D max = hyperPlane3.getMax();
//        Point3D rMaxP = region.hyperPlane3.getMax();
//        Point3D rMinP = region.hyperPlane3.getMin();
//
//        if (rMaxP.x < min.x) return false;
//        if (rMaxP.y < min.y) return false;
//        if (rMaxP.z < min.z) return false;
//
//        if (rMinP.x > max.x) return false;
//        if (rMinP.y > max.y) return false;
//        if (rMinP.z > max.z) return false;
//
//        return true;
//    }
}