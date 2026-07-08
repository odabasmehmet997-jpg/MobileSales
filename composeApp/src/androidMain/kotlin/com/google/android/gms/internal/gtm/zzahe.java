package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzahe implements zzaci {
    zzahe() {
    }

    public zzach zza(final int i2) {
        if (100 == i2) {
            return zzahf.LOGSID_OTHER_IDENTIFYING_USER_INFO;
        }
        switch (i2) {
            case 0:
                return zzahf.LOGSID_NONE;
            case 1:
                return zzahf.LOGSID_IP_ADDRESS;
            case 2:
                return zzahf.LOGSID_IP_ADDRESS_INTERNAL;
            case 3:
                return zzahf.LOGSID_USER_AGENT;
            case 4:
                return zzahf.LOGSID_SENSITIVE_TIMESTAMP;
            case 5:
                return zzahf.LOGSID_SENSITIVE_LOCATION;
            case 6:
                return zzahf.LOGSID_COARSE_LOCATION;
            case 7:
                return zzahf.LOGSID_OTHER_VERSION_ID;
            case 8:
                return zzahf.LOGSID_REFERER;
            case 9:
                return zzahf.LOGSID_OTHER_LOCATION;
            case 10:
                return zzahf.LOGSID_OTHER_PSEUDONYMOUS_ID;
            case 11:
                return zzahf.LOGSID_PREF;
            case 12:
                return zzahf.LOGSID_ZWIEBACK;
            case 13:
                return zzahf.LOGSID_BISCOTTI;
            case 14:
                return zzahf.LOGSID_CUSTOM_SESSION_ID;
            case 15:
                return zzahf.LOGSID_APPROXIMATE_LOCATION;
            case 16:
                return zzahf.LOGSID_THIRD_PARTY_PARAMETERS;
            default:
                switch (i2) {
                    case 20:
                        return zzahf.LOGSID_OTHER_PERSONAL_ID;
                    case 21:
                        return zzahf.LOGSID_GAIA_ID;
                    case 22:
                        return zzahf.LOGSID_EMAIL;
                    case 23:
                        return zzahf.LOGSID_USERNAME;
                    case 24:
                        return zzahf.LOGSID_PHONE_NUMBER;
                    default:
                        switch (i2) {
                            case 30:
                                return zzahf.LOGSID_OTHER_AUTHENTICATED_ID;
                            case 31:
                                return zzahf.LOGSID_OTHER_UNAUTHENTICATED_ID;
                            case 32:
                                return zzahf.LOGSID_PARTNER_OR_CUSTOMER_ID;
                            case 33:
                                return zzahf.LOGSID_DASHER_ID;
                            case 34:
                                return zzahf.LOGSID_FOCUS_GROUP_ID;
                            case 35:
                                return zzahf.LOGSID_PUBLISHER_ID;
                            default:
                                switch (i2) {
                                    case 50:
                                        return zzahf.LOGSID_OTHER_MOBILE_DEVICE_ID;
                                    case 51:
                                        return zzahf.LOGSID_GSERVICES_ANDROID_ID;
                                    case 52:
                                        return zzahf.LOGSID_HARDWARE_ID;
                                    case 53:
                                        return zzahf.LOGSID_MSISDN_ID;
                                    case 54:
                                        return zzahf.LOGSID_BUILD_SERIAL_ID;
                                    case 55:
                                        return zzahf.LOGSID_UDID_ID;
                                    case 56:
                                        return zzahf.LOGSID_ANDROID_LOGGING_ID;
                                    case 57:
                                        return zzahf.LOGSID_SECURE_SETTINGS_ANDROID_ID;
                                    default:
                                        switch (i2) {
                                            case 200:
                                                return zzahf.LOGSID_USER_INPUT;
                                            case 201:
                                                return zzahf.LOGSID_DEMOGRAPHIC_INFO;
                                            case 202:
                                                return zzahf.LOGSID_GENERIC_KEY;
                                            case 203:
                                                return zzahf.LOGSID_GENERIC_VALUE;
                                            case 204:
                                                return zzahf.LOGSID_COOKIE;
                                            case 205:
                                                return zzahf.LOGSID_URL;
                                            case 206:
                                                return zzahf.LOGSID_HTTPHEADER;
                                            case 207:
                                                return zzahf.LOGSID_GAIA_ID_PUBLIC;
                                            default:
                                                return null;
                                        }
                                }
                        }
                }
        }
    }
}
