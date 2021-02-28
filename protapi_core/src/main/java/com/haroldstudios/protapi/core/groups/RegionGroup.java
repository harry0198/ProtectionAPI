package com.haroldstudios.protapi.core.groups;

import com.google.common.collect.ImmutableList;
import com.haroldstudios.protapi.core.RegionFlag;

import java.util.List;

public abstract class RegionGroup {

    private final GroupType groupType;
    private final ImmutableList<RegionFlag> groupFlags;

    public RegionGroup(final GroupType groupType, final List<RegionFlag> flags) {
        this.groupType = groupType;
        this.groupFlags = ImmutableList.copyOf(flags);
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public ImmutableList<RegionFlag> getGroupFlags() {
        return groupFlags;
    }
}