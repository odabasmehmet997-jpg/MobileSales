package com.google.android.gms.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR})
@KeepForSdk
@Retention(RetentionPolicy.CLASS)
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public @interface HideFirstParty {
}
