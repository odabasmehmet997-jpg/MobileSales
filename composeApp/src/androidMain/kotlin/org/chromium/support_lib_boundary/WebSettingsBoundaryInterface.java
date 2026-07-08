package org.chromium.support_lib_boundary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import java.util.Set;


public interface WebSettingsBoundaryInterface {
    @Retention(RetentionPolicy.SOURCE)
    @interface AttributionBehavior {
        int APP_SOURCE_AND_APP_TRIGGER = 3;
        int APP_SOURCE_AND_WEB_TRIGGER = 1;
        int DISABLED = 0;
        int WEB_SOURCE_AND_WEB_TRIGGER = 2;
    }
    @Retention(RetentionPolicy.SOURCE)
    @interface ForceDarkBehavior {
        int FORCE_DARK_ONLY = 0;
        int MEDIA_QUERY_ONLY = 1;
        int PREFER_MEDIA_QUERY_OVER_FORCE_DARK = 2;
    }
    @Retention(RetentionPolicy.SOURCE)
    @interface SpeculativeLoadingStatus {
        int DISABLED = 0;
        int PRERENDER_ENABLED = 1;
    }
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    @interface WebViewMediaIntegrityApiStatus {
        int DISABLED = 0;
        int ENABLED = 2;
        int ENABLED_WITHOUT_APP_IDENTITY = 1;
    }
    @Retention(RetentionPolicy.SOURCE)
    @interface WebauthnSupport {
        int APP = 1;
        int BROWSER = 2;
        int NONE = 0;
    }
    int getAttributionBehavior();
    boolean getBackForwardCacheEnabled();
    int getDisabledActionModeMenuItems();
    boolean getEnterpriseAuthenticationAppLinkPolicyEnabled();
    int getForceDark();
    int getForceDarkBehavior();
    boolean getOffscreenPreRaster();
    Set<String> getRequestedWithHeaderOriginAllowList();
    boolean getSafeBrowsingEnabled();
    int getSpeculativeLoadingStatus();
    Map<String, Object> getUserAgentMetadataMap();
    int getWebViewMediaIntegrityApiDefaultStatus();
    Map<String, Integer> getWebViewMediaIntegrityApiOverrideRules();

    int getWebauthnSupport();

    boolean getWillSuppressErrorPage();

    boolean isAlgorithmicDarkeningAllowed();

    void setAlgorithmicDarkeningAllowed(boolean z);

    void setAttributionBehavior(int i2);

    void setBackForwardCacheEnabled(boolean z);

    void setDisabledActionModeMenuItems(int i2);

    void setEnterpriseAuthenticationAppLinkPolicyEnabled(boolean z);

    void setForceDark(int i2);

    void setForceDarkBehavior(int i2);

    void setOffscreenPreRaster(boolean z);

    void setRequestedWithHeaderOriginAllowList(Set<String> set);

    void setSafeBrowsingEnabled(boolean z);

    void setSpeculativeLoadingStatus(int i2);

    void setUserAgentMetadataFromMap(Map<String, Object> map);

    void setWebViewMediaIntegrityApiStatus(int i2, Map<String, Integer> map);

    void setWebauthnSupport(int i2);

    void setWillSuppressErrorPage(boolean z);
}
