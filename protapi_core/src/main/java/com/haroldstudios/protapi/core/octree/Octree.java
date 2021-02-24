package com.haroldstudios.protapi.core.octree;

import com.haroldstudios.protapi.core.Point3D;
import com.haroldstudios.protapi.core.ProtectionAPI;
import com.haroldstudios.protapi.core.Region;
import com.haroldstudios.protapi.core.components.MathUtil;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class Octree {

    private static final int MAX_JUMPS = 17; // Maximum subdivisions
    private static final int MAX_CAPACITY = 2; // Max regions inside an octane
    private final Octane bounds;
    private final int jumps; //how many stages down the tree?

    private final List<Node> nodeList = new ArrayList<>();
    private final World world;

    private Octree frontNorthWest,
            frontNorthEast,
            frontSouthWest,
            frontSouthEast,
            backNorthWest,
            backNorthEast,
            backSouthWest,
            backSouthEast = null;

    public Octree(int jumps, World world, Octane octane){
        this.world = world;
        this.bounds = octane;
        this.jumps = jumps;
    }

    public Octree(World world, Location corner1, Location corner2) {
        this(1, world, new Octane(
                new Point3D(corner1.getBlockX(), corner1.getBlockY(), (corner1.getBlockZ())),
                new Point3D(corner2.getBlockX(), corner2.getBlockY(), corner2.getBlockZ()))
        );
    }

    public Octree(World world) {
        this(1,world, new Octane(MathUtil.getPointsOfWorldBorder(world)[0], MathUtil.getPointsOfWorldBorder(world)[1]));
    }

    public void subDivide() {

        int jump = jumps + 1;
        if (jump > MAX_JUMPS) return;

        Point3D min = this.bounds.getMin();
        Point3D max = this.bounds.getMax();

        // Middle points
        int xOffset = min.x + (max.z - min.z) / 2;
        int yOffset = min.y + (max.y - min.y) / 2;
        int zOffset = min.z + (max.z - min.z) / 2;

        backNorthWest = new Octree(jump, world, new Octane(
                new Point3D(min.x, yOffset, zOffset),
                new Point3D(xOffset, max.y, max.z)));// Max corner

        frontNorthWest = new Octree(jump, world, new Octane(
                new Point3D( min.x, yOffset, min.z), // Minimum corner
                new Point3D( xOffset, max.y, zOffset)));// Max corner

        backNorthEast = new Octree(jump, world, new Octane(
                new Point3D( xOffset, yOffset, zOffset), // Minimum corner
                max));// Max corner

        frontNorthEast = new Octree(jump, world, new Octane(
                new Point3D( xOffset, yOffset, min.z), // Minimum corner
                new Point3D( max.x, max.y, zOffset)));// Max corner

        backSouthEast = new Octree(jump, world, new Octane(
                new Point3D( xOffset, min.y, zOffset), // Minimum corner
                new Point3D( max.x, yOffset, zOffset)));// Max corner

        frontSouthEast = new Octree(jump, world, new Octane(
                new Point3D( xOffset, min.y, min.z), // Minimum corner
                new Point3D( max.x, yOffset, zOffset)));// Max corner

        backSouthWest = new Octree(jump, world, new Octane(
                new Point3D(min.x, min.y, zOffset), // Minimum corner
                new Point3D( xOffset, yOffset, max.z)));// Max corner

        frontSouthWest = new Octree(jump, world, new Octane(
                min, // Minimum corner
                new Point3D(xOffset, yOffset, zOffset)));// Max corner
    }

    /**
     * Insert the region based off of min and max points
     *
     * @param region UniversalRegion object to insert
     */
    public void insert(Region region) {

        Point3D minLocation = region.getHyperPlane3().getMin();
        Point3D maxLocation = region.getHyperPlane3().getMax();

        if (!ProtectionAPI.isCuboidOverlapping(bounds, minLocation, maxLocation)) return;

        Node node = new Node(region);
        if (nodeList.size() < MAX_CAPACITY) {
            nodeList.add(node);
            return;
        }


        if (frontNorthEast == null){
            subDivide();
        }



        if (ProtectionAPI.isCuboidOverlapping(frontNorthEast.bounds, minLocation, maxLocation)) {
            frontNorthEast.insert(region);
        }
        if (ProtectionAPI.isCuboidOverlapping(backNorthEast.bounds, minLocation, maxLocation)) {
            backNorthEast.insert(region);
        }

        if (ProtectionAPI.isCuboidOverlapping(frontSouthEast.bounds, minLocation, maxLocation)) {
            frontSouthEast.insert(region);
        }

        if (ProtectionAPI.isCuboidOverlapping(backSouthEast.bounds, minLocation, maxLocation)) {
            backSouthEast.insert(region);
        }

        if (ProtectionAPI.isCuboidOverlapping(frontSouthWest.bounds, minLocation, maxLocation)) {
            frontSouthWest.insert(region);
        }

        if (ProtectionAPI.isCuboidOverlapping(backSouthWest.bounds, minLocation, maxLocation)) {
            backSouthWest.insert(region);
        }

        if (ProtectionAPI.isCuboidOverlapping(frontNorthWest.bounds, minLocation, maxLocation)) {
            frontNorthWest.insert(region);
        }

        if (ProtectionAPI.isCuboidOverlapping(backNorthWest.bounds, minLocation, maxLocation)) {
            backNorthWest.insert(region);
        }
    }

    private static final List<Octree> tmpOctreeList = new ArrayList<>();

    private static void dfs(Octree tree, Region region) {
        if (tree == null)
            return;

        if (isBoundsInOctane(tree.bounds, region)) {
            dfs(tree.backNorthEast, region);
            dfs(tree.frontNorthEast, region);
            dfs(tree.backNorthWest, region);
            dfs(tree.frontNorthWest, region);
            dfs(tree.backSouthEast, region);
            dfs(tree.frontSouthEast, region);
            dfs(tree.backSouthWest, region);
            dfs(tree.frontSouthWest, region);
        }

        tmpOctreeList.add(tree);
    }

    /**
     * Find the nearest regions using the octree
     *
     * @param region UniversalRegion object
     * @return A list of the nearest universalregions
     */
    public List<Region> nearestRegions(Region region) {

        dfs(this, region);

        List<Octree> octreeList = tmpOctreeList.stream().distinct().collect(Collectors.toList());

        List<Region> r = new ArrayList<>();

        if (octreeList.size() != 0) {
            octreeList.forEach(e -> e.nodeList.forEach(f -> r.add(f.region)));
            return r;
        }

        return Collections.emptyList();
    }

    public static List<Region> getIntersectingRegions(Octree tree) {
        return null;
    }

    public static boolean isBoundsInOctane(Octane octane, Region region) {
        if (octane == null) {
            return false;
        }

        return ProtectionAPI.isCuboidOverlapping(octane, region.getHyperPlane3().getMin(), region.getHyperPlane3().getMax());

    }
    static class Node {
        public Point3D minLocation, maxLocation;
        public Region region;

        Node(Region region) {
            this.region = region;
            this.minLocation = region.getHyperPlane3().getMin();
            this.maxLocation = region.getHyperPlane3().getMax();
        }
    }
}