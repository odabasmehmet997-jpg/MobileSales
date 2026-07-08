package com.google.android.gms.tagmanager;

import android.content.Context;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.example.privacy_policy_lib.core.model.PrivacyPolicyState;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.internal.gtm.zzap;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@VisibleForTesting
@ShowFirstParty
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
public final class zzfq extends zzfn {
    private static final String zza = zza.UNIVERSAL_ANALYTICS.toString();
    private static final String zzb = zzb.ANALYTICS_PASS_THROUGH.toString();
    private static final String zzc = zzb.ENABLE_ECOMMERCE.toString();
    private static final String zzd = zzb.ECOMMERCE_USE_DATA_LAYER.toString();
    private static final String zze = zzb.ECOMMERCE_MACRO_DATA.toString();
    private static final String zzf = zzb.ANALYTICS_FIELDS.toString();
    private static final String zzg = zzb.TRACK_TRANSACTION.toString();
    private static final String zzh = zzb.TRANSACTION_DATALAYER_MAP.toString();
    private static final String zzi = zzb.TRANSACTION_ITEM_DATALAYER_MAP.toString();
    private static final List zzj = Arrays.asList("detail", "checkout", FirebaseAnalytics.Param.CHECKOUT_OPTION, "click", "add", "remove", FirebaseAnalytics.Event.PURCHASE, FirebaseAnalytics.Event.REFUND);
    private static final Pattern zzk = Pattern.compile("dimension(\\d+)");
    private static final Pattern zzl = Pattern.compile("metric(\\d+)");
    private static Map zzm;
    private static Map zzn;
    private final Set zzo;
    private final zzfm zzp;
    private final DataLayer zzq;

    
    public zzfq(Context context, DataLayer dataLayer) {
        super(zza);
        zzfm zzfm = new zzfm(context);
        this.zzq = dataLayer;
        this.zzp = zzfm;
        HashSet hashSet = new HashSet();
        this.zzo = hashSet;
        hashSet.add("");
        hashSet.add("0");
        hashSet.add("false");
    }

    private String zzd(String str) {
        Object obj = this.zzq.get(str);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    private Map zzh(zzap zzap) {
        if (zzap == null) {
            return new HashMap();
        }
        Map zzm2 = zzm(zzap);
        if (zzm2 == null) {
            return new HashMap();
        }
        String str = (String) zzm2.get("&aip");
        if (str != null && this.zzo.contains(str.toLowerCase())) {
            zzm2.remove("&aip");
        }
        return zzm2;
    }

    private static void zzi(Map map, String str, String str2) {
        if (str2 != null) {
            map.put(str, str2);
        }
    }

    private static boolean zzj(Map map, String str) {
        zzap zzap = (zzap) map.get(str);
        if (zzap == null) {
            return false;
        }
        return zzfp.zzf(zzfp.zzk(zzap)).booleanValue();
    }

    private static Double zzk(Object obj) {
        if (obj instanceof String) {
            try {
                return Double.valueOf((String) obj);
            } catch (NumberFormatException e2) {
                throw new RuntimeException("Cannot convert the object to Double: ".concat(String.valueOf(e2.getMessage())));
            }
        } else if (obj instanceof Integer) {
            return Double.valueOf(((Integer) obj).doubleValue());
        } else {
            if (obj instanceof Double) {
                return (Double) obj;
            }
            throw new RuntimeException("Cannot convert the object to Double: ".concat(obj.toString()));
        }
    }

    private static Integer zzl(Object obj) {
        if (obj instanceof String) {
            try {
                return Integer.valueOf((String) obj);
            } catch (NumberFormatException e2) {
                throw new RuntimeException("Cannot convert the object to Integer: ".concat(String.valueOf(e2.getMessage())));
            }
        } else if (obj instanceof Double) {
            return Integer.valueOf(((Double) obj).intValue());
        } else {
            if (obj instanceof Integer) {
                return (Integer) obj;
            }
            throw new RuntimeException("Cannot convert the object to Integer: ".concat(obj.toString()));
        }
    }

    private static Map zzm(zzap zzap) {
        Object zzk2 = zzfp.zzk(zzap);
        if (!(zzk2 instanceof Map)) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : ((Map) zzk2).entrySet()) {
            linkedHashMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return linkedHashMap;
    }

    private static Product zzn(Map map) {
        Product product = new Product();
        Object obj = map.get(Name.MARK);
        if (obj != null) {
            product.setId(obj.toString());
        }
        Object obj2 = map.get("name");
        if (obj2 != null) {
            product.setName(obj2.toString());
        }
        Object obj3 = map.get("brand");
        if (obj3 != null) {
            product.setBrand(obj3.toString());
        }
        Object obj4 = map.get("category");
        if (obj4 != null) {
            product.setCategory(obj4.toString());
        }
        Object obj5 = map.get("variant");
        if (obj5 != null) {
            product.setVariant(obj5.toString());
        }
        Object obj6 = map.get(FirebaseAnalytics.Param.COUPON);
        if (obj6 != null) {
            product.setCouponCode(obj6.toString());
        }
        Object obj7 = map.get(PrivacyPolicyState.POSITION);
        if (obj7 != null) {
            product.setPosition(zzl(obj7).intValue());
        }
        Object obj8 = map.get(FirebaseAnalytics.Param.PRICE);
        if (obj8 != null) {
            product.setPrice(zzk(obj8).doubleValue());
        }
        Object obj9 = map.get(FirebaseAnalytics.Param.QUANTITY);
        if (obj9 != null) {
            product.setQuantity(zzl(obj9).intValue());
        }
        for (String str : map.keySet()) {
            Matcher matcher = zzk.matcher(str);
            if (matcher.matches()) {
                try {
                    product.setCustomDimension(Integer.parseInt(matcher.group(1)), String.valueOf(map.get(str)));
                } catch (NumberFormatException unused) {
                    Log.w("GoogleTagManager", "illegal number in custom dimension value: ".concat(str));
                }
            } else {
                Matcher matcher2 = zzl.matcher(str);
                if (matcher2.matches()) {
                    try {
                        product.setCustomMetric(Integer.parseInt(matcher2.group(1)), zzl(map.get(str)).intValue());
                    } catch (NumberFormatException unused2) {
                        Log.w("GoogleTagManager", "illegal number in custom metric value: ".concat(str));
                    }
                }
            }
        }
        return product;
    }

    public boolean zzb() {
        return false;
    }

    public void zzc(java.util.Map r15) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzfq.zzc(java.util.Map):void");
    }
}
