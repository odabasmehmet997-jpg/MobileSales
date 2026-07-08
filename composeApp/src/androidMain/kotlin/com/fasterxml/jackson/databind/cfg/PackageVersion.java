package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.util.VersionUtil;

public enum PackageVersion {
    ;
    public static final Version VERSION = VersionUtil.parseVersion("2.12.2", "com.fasterxml.jackson.core", "jackson-databind");
}
