package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArraySet;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.signin.SignInOptions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

import java.util.*;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class ClientSettings {
    private final Account zaa;
    private final Set zab;
    private final Set zac;
    private final Map zad;
    private final int zae;
    private final View zaf;
    private final String zag;
    private final String zah;
    private final SignInOptions zai;
    private Integer zaj;

    @KeepForSdk
    /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
    public static final class Builder {
        private Account zaa;
        private ArraySet zab;
        private String zac;
        private String zad;
        private final SignInOptions zae = SignInOptions.zaa;

        @NonNull
        @KeepForSdk
        public ClientSettings build() {
            return new ClientSettings(this.zaa, this.zab, null, 0, null, this.zac, this.zad, this.zae, false);
        }

        @NonNull
        @KeepForSdk
        @CanIgnoreReturnValue
        public Builder setRealClientPackageName(@NonNull String str) {
            this.zac = str;
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder zaa(@NonNull Collection collection) {
            if (null == zab) {
                this.zab = new ArraySet();
            }
            this.zab.addAll(collection);
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder zab(Account account) {
            this.zaa = account;
            return this;
        }

        @NonNull
        @CanIgnoreReturnValue
        public Builder zac(@NonNull String str) {
            this.zad = str;
            return this;
        }
    }

    @KeepForSdk
    @Nullable
    public Account getAccount() {
        return this.zaa;
    }

    @NonNull
    @KeepForSdk
    public Account getAccountOrDefault() {
        Account account = this.zaa;
        return null != account ? account : new Account("<<default account>>", "com.google");
    }

    @NonNull
    @KeepForSdk
    public Set<Scope> getAllRequestedScopes() {
        return this.zac;
    }

    @NonNull
    @KeepForSdk
    public String getRealClientPackageName() {
        return this.zag;
    }

    @NonNull
    @KeepForSdk
    public Set<Scope> getRequiredScopes() {
        return this.zab;
    }

    @NonNull
    public SignInOptions zaa() {
        return this.zai;
    }

    @Nullable
    public Integer zab() {
        return this.zaj;
    }

    @Nullable
    public String zac() {
        return this.zah;
    }

    @NonNull
    public Map zad() {
        return this.zad;
    }

    public void zae(@NonNull Integer num) {
        this.zaj = num;
    }

    public ClientSettings(Account account, @NonNull Set set, @NonNull Map map, int i2, View view, @NonNull String str, @NonNull String str2, SignInOptions signInOptions, boolean z) {
        this.zaa = account;
        Set emptySet = null == set ? Collections.emptySet() : Collections.unmodifiableSet(set);
        this.zab = emptySet;
        map = null == map ? Collections.emptyMap() : map;
        this.zad = map;
        this.zaf = view;
        this.zae = i2;
        this.zag = str;
        this.zah = str2;
        this.zai = null == signInOptions ? SignInOptions.zaa : signInOptions;
        HashSet hashSet = new HashSet(emptySet);
        for (zab zab2 : map.values()) {
            hashSet.addAll(zab2.zaa());
        }
        this.zac = Collections.unmodifiableSet(hashSet);
    }
}
