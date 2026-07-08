package com.google.android.gms.auth.api.signin;

import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;

import java.io.Serializable;
import java.util.Comparator;

public final class zaa implements Comparator, Serializable {
    public static final zaa zaa = new zaa();

    private zaa() {
    }

    public int compare(final Object obj, final Object obj2) {
        final Parcelable.Creator<GoogleSignInAccount> creator = GoogleSignInAccount.CREATOR;
        return ((Scope) obj).getScopeUri().compareTo(((Scope) obj2).getScopeUri());
    }
}
