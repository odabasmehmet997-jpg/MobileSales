package com.proje.mobilesales.features.model;

import kotlin.jvm.internal.Intrinsics;


public record UserCountModel(int worUserCount, int licenseUserCount, UserCountResult userCountResult) {
    public static UserCountModel copydefault(UserCountModel userCountModel, int i2, int i3, UserCountResult userCountResult, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i2 = userCountModel.worUserCount;
        }
        if ((i4 & 2) != 0) {
            i3 = userCountModel.licenseUserCount;
        }
        if ((i4 & 4) != 0) {
            userCountResult = userCountModel.userCountResult;
        }
        return userCountModel.copy(i2, i3, userCountResult);
    }

    public int component1() {
        return this.worUserCount;
    }

    public int component2() {
        return this.licenseUserCount;
    }

    public UserCountResult component3() {
        return this.userCountResult;
    }

    public UserCountModel copy(int i2, int i3, UserCountResult userCountResult) {
        Intrinsics.checkNotNullParameter(userCountResult, "userCountResult");
        return new UserCountModel(i2, i3, userCountResult);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserCountModel userCountModel)) {
            return false;
        }
        return this.worUserCount == userCountModel.worUserCount && this.licenseUserCount == userCountModel.licenseUserCount && this.userCountResult == userCountModel.userCountResult;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.worUserCount) * 31) + Integer.hashCode(this.licenseUserCount)) * 31) + this.userCountResult.hashCode();
    }

    public String toString() {
        return "UserCountModel(worUserCount=" + this.worUserCount + ", licenseUserCount=" + this.licenseUserCount + ", userCountResult=" + this.userCountResult + ')';
    }

    public UserCountModel {
        Intrinsics.checkNotNullParameter(userCountResult, "userCountResult");
    }

    public boolean hasError() {
        return this.userCountResult != UserCountResult.UserCountInLimits;
    }
}
