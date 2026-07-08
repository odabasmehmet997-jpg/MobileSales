package com.proje.mobilesales.core.utils;

import java.util.ArrayList;
import java.util.List;

public class Version {
    private final String originalString;
    private final List<Integer> subversionNumbers;
    private String suffix;
    public Version(String str) {
        this(str, false);
    }
    public Version(String str, boolean z) {
        this.subversionNumbers = new ArrayList<>();
        this.suffix = "";
        if (z) {
            if (str == null) {
                throw new NullPointerException("Argument versionString is null");
            }
            if (VersionComparator.startsNumeric(str)) {
                throw new IllegalArgumentException("Argument versionString is no valid version");
            }
        }
        this.originalString = str;
        initVersion();
    }
    public int getMajor() {
        if (this.subversionNumbers.size() > 0) {
            return this.subversionNumbers.get(0);
        }
        return 0;
    }
    public int getMinor() {
        if (this.subversionNumbers.size() > 1) {
            return this.subversionNumbers.get(1);
        }
        return 0;
    }
    public int getPatch() {
        if (this.subversionNumbers.size() > 2) {
            return this.subversionNumbers.get(2);
        }
        return 0;
    }
    public List<Integer> getSubversionNumbers() {
        return this.subversionNumbers;
    }
    public String getSuffix() {
        return this.suffix;
    }
    public String getOriginalString() {
        return this.originalString;
    }
    public boolean isHigherThan(String str) {
        return isHigherThan(new Version(str));
    }
    public boolean isHigherThan(Version version) {
        int compareSubversionNumbers = VersionComparator.compareSubversionNumbers(this.subversionNumbers, version.subversionNumbers);
        return compareSubversionNumbers > 0 || (compareSubversionNumbers == 0 && VersionComparator.compareSuffix(this.suffix, version.suffix) > 0);
    }

    public boolean isLowerThan(String str) {
        return isLowerThan(new Version(str));
    }

    public boolean isLowerThan(Version version) {
        int compareSubversionNumbers = VersionComparator.compareSubversionNumbers(this.subversionNumbers, version.subversionNumbers);
        return compareSubversionNumbers < 0 || (compareSubversionNumbers == 0 && VersionComparator.compareSuffix(this.suffix, version.suffix) < 0);
    }

    public boolean isEqual(String str) {
        return isEqual(new Version(str));
    }

    public boolean isEqual(Version version) {
        return VersionComparator.compareSubversionNumbers(this.subversionNumbers, version.subversionNumbers) == 0 && VersionComparator.compareSuffix(this.suffix, version.suffix) == 0;
    }
    public boolean isAtLeast(String str) {
        return isAtLeast(new Version(str));
    }
    public boolean isAtLeast(Version version) {
        return isAtLeast(version, false);
    }
    public boolean isAtLeast(String str, boolean z) {
        return isAtLeast(new Version(str), z);
    }
    public boolean isAtLeast(Version version, boolean z) {
        int compareSubversionNumbers = VersionComparator.compareSubversionNumbers(this.subversionNumbers, version.subversionNumbers);
        return (compareSubversionNumbers != 0 || z) ? compareSubversionNumbers >= 0 : VersionComparator.compareSuffix(this.suffix, version.suffix) >= 0;
    }

    private void initVersion() {
        String str = this.originalString;
        if (str == null || VersionComparator.startsNumeric(str)) {
            return;
        }
        StringBuilder sb = null;
        boolean z = false;
        for (String str2 : this.originalString.replaceAll("\\s", "").split("\\.")) {
            if (z) {
                sb.append(".");
                sb.append(str2);
            } else if (VersionComparator.isNumeric(str2)) {
                this.subversionNumbers.add(Integer.valueOf(VersionComparator.safeParseInt(str2)));
            } else {
                int i2 = 0;
                while (true) {
                    if (i2 >= str2.length()) {
                        break;
                    }
                    if (Character.isDigit(str2.charAt(i2))) {
                        i2++;
                    } else {
                        sb = new StringBuilder();
                        if (i2 > 0) {
                            this.subversionNumbers.add(Integer.valueOf(VersionComparator.safeParseInt(str2.substring(0, i2))));
                            sb.append(str2.substring(i2));
                        } else {
                            sb.append(str2);
                        }
                        z = true;
                    }
                }
            }
        }
        if (sb != null) {
            this.suffix = sb.toString();
        }
    }
}
