package com.haroldstudios.protapi.core;

import com.google.common.collect.ImmutableList;
import com.haroldstudios.protapi.core.groups.RegionGroup;
import com.haroldstudios.protapi.core.octree.Octree;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Region {

    private final World world;
    private final HyperPlane3 hyperPlane3;
    private final ImmutableList<RegionGroup> groups;

    public Region(final HyperPlane3 hyperPlane3, final World world, final List<RegionGroup> groups) {
        this.hyperPlane3 = hyperPlane3;
        this.world = world;
        this.groups = ImmutableList.copyOf(groups);
    }

    public ImmutableList<RegionGroup> getGroups() {
        return groups;
    }

    public HyperPlane3 getHyperPlane3() {
        return hyperPlane3;
    }

    public World getWorld() {
        return world;
    }

    public boolean canBuild(final Player player) {
        return false;
    }


}