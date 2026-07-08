package com.proje.mobilesales.core.utils;

import androidx.exifinterface.media.ExifInterface;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.proje.mobilesales.features.model.CheckSeriGroup;
import com.proje.mobilesales.features.sales.model.database.Serilot;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

public class SeriLotUtils {
    public static ArrayList<CheckSeriGroup> extractAvailableSeriGroups(ArrayList<CheckSeriGroup> arrayList, final String str, ArrayList<Serilot> arrayList2) {
        int i2;
        Iterator it;
        ArrayList<CheckSeriGroup> arrayList3 = arrayList;
        if (arrayList3 == null || arrayList.size() == 0) {
            return null;
        }
        HashSet hashSet = new HashSet();
        Iterator<CheckSeriGroup> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            hashSet.add(Integer.valueOf(it2.next().getLogicalRef()));
        }
        ArrayList<CheckSeriGroup> arrayList4 = new ArrayList<>();
        ArrayList arrayList5 = new ArrayList(hashSet);
        Collections.sort(arrayList5);
        Iterator it3 = arrayList5.iterator();
        while (it3.hasNext()) {
            final Integer num = (Integer) it3.next();
            Collection filter = Collections2.filter(arrayList3, new Predicate() { // from class: com.proje.mobilesales.core.utils.SeriLotUtilsExternalSyntheticLambda0
                @Override // com.google.common.base.Predicate
                public boolean apply(Object obj) {
                    boolean lambdaextractAvailableSeriGroups0;
                    lambdaextractAvailableSeriGroups0 = SeriLotUtils.lambdaextractAvailableSeriGroups0(num, (CheckSeriGroup) obj);
                    return lambdaextractAvailableSeriGroups0;
                }
            });
            CheckSeriGroup checkSeriGroup = (CheckSeriGroup) Iterables.get(filter, 0);
            String orgGrpBegCode = checkSeriGroup.getOrgGrpBegCode();
            int length = orgGrpBegCode.length() - 1;
            while (true) {
                if (length < 0) {
                    length = 0;
                    break;
                }
                if (Character.isLetter(orgGrpBegCode.charAt(length))) {
                    break;
                }
                length--;
            }
            String orgGrpEndCode = checkSeriGroup.getOrgGrpEndCode();
            int i3 = length + 1;
            String substring = orgGrpBegCode.substring(0, i3);
            String substring2 = orgGrpBegCode.substring(i3);
            String substring3 = orgGrpEndCode.substring(i3);
            int parseInt = Integer.parseInt(substring2);
            int length2 = substring3.length();
            if (checkSeriGroup.getUsedGrpBegCode() == null || checkSeriGroup.getUsedGrpBegCode().isEmpty()) {
                CheckSeriGroup checkSeriGroup2 = new CheckSeriGroup();
                checkSeriGroup2.setGrpBegCode(checkSeriGroup.getOrgGrpBegCode());
                checkSeriGroup2.setGrpEndCode(checkSeriGroup.getOrgGrpEndCode());
                checkSeriGroup2.setAvailableAmount(checkSeriGroup.getOrgAmount());
                checkSeriGroup2.setRemAmount(checkSeriGroup.getRemAmount());
                checkSeriGroup2.setOrgAmount(checkSeriGroup.getOrgAmount());
                checkSeriGroup2.setOrgGrpBegCode(checkSeriGroup.getOrgGrpBegCode());
                checkSeriGroup2.setOrgGrpEndCode(checkSeriGroup.getOrgGrpEndCode());
                checkSeriGroup2.setExpDate(checkSeriGroup.getExpDate());
                checkSeriGroup2.setExpDate(DateAndTimeUtils.convertYMDToDMY(checkSeriGroup2.getExpDate().split(ExifInterface.GPS_DIRECTION_TRUE)[0]));
                checkSeriGroup2.setLocationCode(checkSeriGroup.getLocationCode());
                checkSeriGroup2.setLogicalRef(checkSeriGroup.getLogicalRef());
                checkSeriGroup2.setUnit(checkSeriGroup.getUnit());
                checkSeriGroup2.setStTransRef(checkSeriGroup.getStTransRef());
                checkSeriGroup2.setSeriLotNo(checkSeriGroup.getSeriLotNo());
                checkSeriGroup2.setSourceUnitRef(checkSeriGroup.getSourceUnitRef());
                arrayList4.add(checkSeriGroup2);
            } else {
                Iterator it4 = filter.iterator();
                while (it4.hasNext()) {
                    CheckSeriGroup checkSeriGroup3 = (CheckSeriGroup) it4.next();
                    int parseInt2 = Integer.parseInt(checkSeriGroup3.getUsedGrpBegCode().substring(i3));
                    if (parseInt == parseInt2) {
                        CheckSeriGroup checkSeriGroup4 = new CheckSeriGroup();
                        checkSeriGroup4.setLogicalRef(num.intValue());
                        checkSeriGroup4.setGrpBegCode(checkSeriGroup3.getUsedGrpBegCode());
                        checkSeriGroup4.setGrpEndCode(checkSeriGroup3.getUsedGrpEndCode());
                        i2 = parseInt;
                        it = it4;
                        checkSeriGroup4.setAvailableAmount(0.0d);
                        checkSeriGroup4.setName(checkSeriGroup3.getName());
                        checkSeriGroup4.setUnit(checkSeriGroup3.getUnit());
                        checkSeriGroup4.setLogicalRef(checkSeriGroup3.getLogicalRef());
                        checkSeriGroup4.setExpDate(checkSeriGroup3.getExpDate());
                        checkSeriGroup4.setExpDate(DateAndTimeUtils.convertYMDToDMY(checkSeriGroup3.getExpDate().split(ExifInterface.GPS_DIRECTION_TRUE)[0]));
                        checkSeriGroup4.setLocationCode(checkSeriGroup3.getLocationCode());
                        checkSeriGroup4.setStTransRef(checkSeriGroup3.getStTransRef());
                        checkSeriGroup4.setSeriLotNo(checkSeriGroup3.getSeriLotNo());
                        checkSeriGroup4.setSourceUnitRef(checkSeriGroup3.getSourceUnitRef());
                        arrayList4.add(checkSeriGroup4);
                    } else {
                        i2 = parseInt;
                        it = it4;
                        CheckSeriGroup checkSeriGroup5 = new CheckSeriGroup();
                        checkSeriGroup5.setLogicalRef(num.intValue());
                        checkSeriGroup5.setGrpEndCode(String.format("%s%s", substring, StringUtils.padRight(Integer.toString(parseInt2 - 1), length2).replaceAll(" ", "0")));
                        if (arrayList4.size() > 0 && arrayList4.get(arrayList4.size() - 1).getLogicalRef() == num.intValue()) {
                            CheckSeriGroup checkSeriGroup6 = arrayList4.get(arrayList4.size() - 1);
                            if (checkSeriGroup5.getGrpBegCode() == null) {
                                checkSeriGroup5.setGrpBegCode(generateNextGroupNumber(checkSeriGroup6.getGrpEndCode(), length, length2));
                            }
                            setAmountsAndInfo(checkSeriGroup5, checkSeriGroup, length);
                            arrayList4.add(checkSeriGroup5);
                        } else {
                            checkSeriGroup5.setGrpBegCode(orgGrpBegCode);
                            setAmountsAndInfo(checkSeriGroup5, checkSeriGroup, length);
                            arrayList4.add(checkSeriGroup5);
                        }
                        CheckSeriGroup checkSeriGroup7 = new CheckSeriGroup();
                        checkSeriGroup7.setLogicalRef(num.intValue());
                        checkSeriGroup7.setGrpBegCode(checkSeriGroup3.getUsedGrpBegCode());
                        checkSeriGroup7.setGrpEndCode(checkSeriGroup3.getUsedGrpEndCode());
                        checkSeriGroup7.setAvailableAmount(0.0d);
                        checkSeriGroup7.setExpDate(DateAndTimeUtils.convertYMDToDMY(checkSeriGroup3.getExpDate().split(ExifInterface.GPS_DIRECTION_TRUE)[0]));
                        checkSeriGroup7.setLocationCode(checkSeriGroup3.getLocationCode());
                        checkSeriGroup7.setStTransRef(checkSeriGroup3.getStTransRef());
                        checkSeriGroup7.setSeriLotNo(checkSeriGroup3.getSeriLotNo());
                        checkSeriGroup7.setSourceUnitRef(checkSeriGroup3.getSourceUnitRef());
                        arrayList4.add(checkSeriGroup7);
                    }
                    parseInt = i2;
                    it4 = it;
                }
                if (arrayList4.size() > 0 && arrayList4.get(arrayList4.size() - 1).getLogicalRef() == num.intValue()) {
                    CheckSeriGroup checkSeriGroup8 = arrayList4.get(arrayList4.size() - 1);
                    if (!checkSeriGroup8.getGrpEndCode().equals(checkSeriGroup.getOrgGrpEndCode())) {
                        CheckSeriGroup checkSeriGroup9 = new CheckSeriGroup();
                        checkSeriGroup9.setLogicalRef(num.intValue());
                        checkSeriGroup9.setGrpBegCode(generateNextGroupNumber(checkSeriGroup8.getGrpEndCode(), length, length2));
                        checkSeriGroup9.setGrpEndCode(checkSeriGroup.getOrgGrpEndCode());
                        setAmountsAndInfo(checkSeriGroup9, checkSeriGroup, length);
                        arrayList4.add(checkSeriGroup9);
                    }
                }
            }
            arrayList3 = arrayList;
        }
        Iterator<CheckSeriGroup> it5 = arrayList4.iterator();
        while (it5.hasNext()) {
            if (it5.next().getAvailableAmount() == 0.0d) {
                it5.remove();
            }
        }
        if (!str.equals("")) {
            Object[] array = Collections2.filter(arrayList4, new Predicate() { // from class: com.proje.mobilesales.core.utils.SeriLotUtilsExternalSyntheticLambda1
                @Override // com.google.common.base.Predicate
                public boolean apply(Object obj) {
                    boolean lambdaextractAvailableSeriGroups1;
                    lambdaextractAvailableSeriGroups1 = SeriLotUtils.lambdaextractAvailableSeriGroups1(str, (CheckSeriGroup) obj);
                    return lambdaextractAvailableSeriGroups1;
                }
            }).toArray();
            arrayList4.clear();
            for (Object obj : array) {
                arrayList4.add((CheckSeriGroup) obj);
            }
        }
        if (arrayList2 != null) {
            Iterator<Serilot> it6 = arrayList2.iterator();
            while (it6.hasNext()) {
                Serilot next = it6.next();
                Iterator<CheckSeriGroup> it7 = arrayList4.iterator();
                while (true) {
                    if (it7.hasNext()) {
                        CheckSeriGroup next2 = it7.next();
                        if (next.logicalRef == next2.getLogicalRef() && checkStartNumberInRange(next.grpBegCode, next2.getGrpBegCode(), next2.getGrpEndCode())) {
                            next2.setUsedGrpBegCode(next.grpBegCode);
                            next2.setUsedGrpEndCode(next.grpEndCode);
                            next2.setUsedAmount(next.amount);
                            next2.setId(next.f1277id);
                            next2.setDetailRef(next.detailRef);
                            next2.setLocationCode(next.locationCode);
                            next2.setSeriLotNo(next.code);
                            next2.setStTransRef(next.stTransRef);
                            next2.setSourceUnitRef(next.sourceUnitRef);
                            next2.setChecked(true);
                            break;
                        }
                    }
                }
            }
            arrayList2.clear();
        }
        return arrayList4;
    }
    public static boolean lambdaextractAvailableSeriGroups0(Integer num, CheckSeriGroup checkSeriGroup) {
        return checkSeriGroup.getLogicalRef() == num;
    }
    public static boolean lambdaextractAvailableSeriGroups1(String str, CheckSeriGroup checkSeriGroup) {
        return checkSeriGroup.getGrpBegCode().toUpperCase().contains(str.toUpperCase());
    }
    public static boolean checkStartNumberInRange(String str, String str2, String str3) {
        try {
            int length = str2.length() - 1;
            while (true) {
                if (length < 0) {
                    length = 0;
                    break;
                }
                if (Character.isLetter(str2.charAt(length))) {
                    break;
                }
                length--;
            }
            int i2 = length + 1;
            String substring = str.substring(0, i2);
            String substring2 = str.substring(i2);
            int parseInt = Integer.parseInt(substring2);
            str2.substring(0, i2);
            int parseInt2 = Integer.parseInt(str2.substring(i2));
            String substring3 = str3.substring(0, i2);
            String substring4 = str3.substring(i2);
            int parseInt3 = Integer.parseInt(substring4);
            if (substring3.equals(substring)) {
                return substring2.length() == substring4.length() && parseInt >= parseInt2 && parseInt <= parseInt3;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
    private static String generateNextGroupNumber(String str, int i2, int i3) {
        int i4 = i2 + 1;
        return String.format("%s%s", str.substring(0, i4), StringUtils.padRight(Integer.toString(Integer.parseInt(str.substring(i4)) + 1), i3).replaceAll(" ", "0"));
    }
    private static void setAmountsAndInfo(CheckSeriGroup checkSeriGroup, CheckSeriGroup checkSeriGroup2, int i2) {
        int i3 = i2 + 1;
        checkSeriGroup.setAvailableAmount((Integer.parseInt(checkSeriGroup.getGrpEndCode().substring(i3)) - Integer.parseInt(checkSeriGroup.getGrpBegCode().substring(i3))) + 1);
        checkSeriGroup.setOrgAmount(checkSeriGroup2.getOrgAmount());
        checkSeriGroup.setRemAmount(checkSeriGroup2.getRemAmount());
        checkSeriGroup.setName(checkSeriGroup2.getName());
        checkSeriGroup.setUnit(checkSeriGroup2.getUnit());
        checkSeriGroup.setLogicalRef(checkSeriGroup2.getLogicalRef());
        checkSeriGroup.setOrgGrpBegCode(checkSeriGroup2.getOrgGrpBegCode());
        checkSeriGroup.setOrgGrpEndCode(checkSeriGroup2.getOrgGrpEndCode());
        checkSeriGroup.setExpDate(DateAndTimeUtils.convertYMDToDMY(checkSeriGroup2.getExpDate().split(ExifInterface.GPS_DIRECTION_TRUE)[0]));
        checkSeriGroup.setLocationCode(checkSeriGroup2.getLocationCode());
        checkSeriGroup.setStTransRef(checkSeriGroup2.getStTransRef());
        checkSeriGroup.setSeriLotNo(checkSeriGroup2.getSeriLotNo());
        checkSeriGroup.setSourceUnitRef(checkSeriGroup2.getSourceUnitRef());
    }
}
