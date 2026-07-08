package com.fasterxml.jackson.core;

import java.io.Serializable;

public class Version implements Comparable<Version>, Serializable {
    private static final Version UNKNOWN_VERSION = new Version(0, 0, 0, null, null, null);
    private static final long serialVersionUID = 1;
    protected final String _artifactId;
    protected final String _groupId;
    protected final int _majorVersion;
    protected final int _minorVersion;
    protected final int _patchLevel;
    protected final String _snapshotInfo;
    public Version(final int i2, final int i3, final int i4, final String str) {
        this(i2, i3, i4, str, null, null);
    }
    public Version(final int i2, final int i3, final int i4, final String str, final String str2, final String str3) {
        _majorVersion = i2;
        _minorVersion = i3;
        _patchLevel = i4;
        _snapshotInfo = str;
        _groupId = null == str2 ? "" : str2;
        _artifactId = null == str3 ? "" : str3;
    }
    public static Version unknownVersion() {
        return Version.UNKNOWN_VERSION;
    }
    public boolean isUnknownVersion() {
        return this == Version.UNKNOWN_VERSION;
    }
    public boolean isSnapshot() {
        final String str = _snapshotInfo;
        return null != str && 0 < str.length();
    }
    public boolean isUknownVersion() {
        return this.isUnknownVersion();
    }
    public int getMajorVersion() {
        return _majorVersion;
    }
    public int getMinorVersion() {
        return _minorVersion;
    }
    public int getPatchLevel() {
        return _patchLevel;
    }
    public String getGroupId() {
        return _groupId;
    }
    public String getArtifactId() {
        return _artifactId;
    }
    public String toFullString() {
        return _groupId + '/' + _artifactId + '/' + this;
    }
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(_majorVersion);
        sb.append('.');
        sb.append(_minorVersion);
        sb.append('.');
        sb.append(_patchLevel);
        if (this.isSnapshot()) {
            sb.append('-');
            sb.append(_snapshotInfo);
        }
        return sb.toString();
    }
    public int hashCode() {
        return _artifactId.hashCode() ^ (((_groupId.hashCode() + _majorVersion) - _minorVersion) + _patchLevel);
    }
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (null == obj || obj.getClass() != this.getClass()) {
            return false;
        }
        final Version version = (Version) obj;
        return version._majorVersion == _majorVersion && version._minorVersion == _minorVersion && version._patchLevel == _patchLevel && version._artifactId.equals(_artifactId) && version._groupId.equals(_groupId);
    }

    public int compareTo(final Version version) {
        if (version == this) {
            return 0;
        }
        final int iCompareTo = _groupId.compareTo(version._groupId);
        if (0 != iCompareTo) {
            return iCompareTo;
        }
        final int iCompareTo2 = _artifactId.compareTo(version._artifactId);
        if (0 != iCompareTo2) {
            return iCompareTo2;
        }
        final int i2 = _majorVersion - version._majorVersion;
        if (0 != i2) {
            return i2;
        }
        final int i3 = _minorVersion - version._minorVersion;
        return 0 == i3 ? _patchLevel - version._patchLevel : i3;
    }
}
