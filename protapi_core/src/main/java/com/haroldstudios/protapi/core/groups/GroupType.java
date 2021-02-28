package com.haroldstudios.protapi.core.groups;

/**
 Group type definitions.
 Groups are smartly sorted from each plugin into the closest respective
 group. For example; 'moderators' will be assigned into the 'Member'
 group type.
 Flags get overwritten as priority goes up //TODO
 */
public enum GroupType {

    MEMBER(3),
    OWNER(4),
    NON_MEMBER(1),
    NON_OWNER(2),
    GLOBAL(0) ;

    private final int priority;

    GroupType(final int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}