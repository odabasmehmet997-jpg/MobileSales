package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.Scope;

import java.io.Serializable;
import java.util.Comparator;

final class zac implements Comparator, Serializable {
    zac() {
    }

    public int compare(final Object obj, final Object obj2) {
        return ((Scope) obj).getScopeUri().compareTo(((Scope) obj2).getScopeUri());
    }
}
