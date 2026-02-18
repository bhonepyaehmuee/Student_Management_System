package com.example.demo.enums;

import lombok.Getter;


public enum InstructorStatus {
    ACTIVE,
    ON_LEAVE,
    TERMINATED,
    INACTIVE;

    public boolean canTransitionTo(InstructorStatus target)
    {
        if (this == TERMINATED) return false;
        if (this == INACTIVE && target == ON_LEAVE) return false;
        return true;
    }
}
