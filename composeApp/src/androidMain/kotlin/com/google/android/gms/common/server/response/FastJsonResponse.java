package com.google.android.gms.common.server.response;

import android.os.Parcel;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.server.converter.zaa;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.MapUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ShowFirstParty
@KeepForSdk
/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public abstract class FastJsonResponse {

    @ShowFirstParty
    @KeepForSdk
    @SafeParcelable.Class
    /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
    public static class Field<I, O> extends AbstractSafeParcelable {
        public static final zaj CREATOR = new zaj();
        @SafeParcelable.Field
        protected final int zaa;
        @SafeParcelable.Field
        protected final boolean zab;
        @SafeParcelable.Field
        protected final int zac;
        @SafeParcelable.Field
        protected final boolean zad;
        @SafeParcelable.Field
        @NonNull
        protected final String zae;
        @SafeParcelable.Field
        protected final int zaf;
        @Nullable
        protected final Class zag;
        @SafeParcelable.Field
        @Nullable
        protected final String zah;
        @SafeParcelable.VersionField
        private final int zai;
        private zan zaj;
        
        @SafeParcelable.Field
        @Nullable
        public final FieldConverter zak;

        @SafeParcelable.Constructor
        Field(@SafeParcelable.Param int i2, @SafeParcelable.Param int i3, @SafeParcelable.Param boolean z, @SafeParcelable.Param int i4, @SafeParcelable.Param boolean z2, @SafeParcelable.Param String str, @SafeParcelable.Param int i5, @SafeParcelable.Param @Nullable String str2, @SafeParcelable.Param @Nullable zaa zaa2) {
            this.zai = i2;
            this.zaa = i3;
            this.zab = z;
            this.zac = i4;
            this.zad = z2;
            this.zae = str;
            this.zaf = i5;
            if (null == str2) {
                this.zag = null;
                this.zah = null;
            } else {
                this.zag = SafeParcelResponse.class;
                this.zah = str2;
            }
            if (null == zaa2) {
                this.zak = null;
            } else {
                this.zak = zaa2.zab();
            }
        }

        @KeepForSdk
        public int getSafeParcelableFieldId() {
            return this.zaf;
        }

        @NonNull
        public final String toString() {
            Objects.ToStringHelper add = Objects.toStringHelper(this).add("versionCode", Integer.valueOf(this.zai)).add("typeIn", Integer.valueOf(this.zaa)).add("typeInArray", Boolean.valueOf(this.zab)).add("typeOut", Integer.valueOf(this.zac)).add("typeOutArray", Boolean.valueOf(this.zad)).add("outputFieldName", this.zae).add("safeParcelFieldId", Integer.valueOf(this.zaf)).add("concreteTypeName", zag());
            Class cls = this.zag;
            if (null != cls) {
                add.add("concreteType.class", cls.getCanonicalName());
            }
            FieldConverter fieldConverter = this.zak;
            if (null != fieldConverter) {
                add.add("converterName", fieldConverter.getClass().getCanonicalName());
            }
            return add.toString();
        }

        public final void writeToParcel(@NonNull Parcel parcel, int i2) {
            int i3 = this.zai;
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 1, i3);
            SafeParcelWriter.writeInt(parcel, 2, this.zaa);
            SafeParcelWriter.writeBoolean(parcel, 3, this.zab);
            SafeParcelWriter.writeInt(parcel, 4, this.zac);
            SafeParcelWriter.writeBoolean(parcel, 5, this.zad);
            SafeParcelWriter.writeString(parcel, 6, this.zae, false);
            SafeParcelWriter.writeInt(parcel, 7, zaf);
            SafeParcelWriter.writeString(parcel, 8, zag(), false);
            SafeParcelWriter.writeParcelable(parcel, 9, zaa(), i2, false);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }

        
        @Nullable
        public final zaa zaa() {
            FieldConverter fieldConverter = this.zak;
            if (null == fieldConverter) {
                return null;
            }
            return zaa.zaa(fieldConverter);
        }

        @NonNull
        public final Object zaf(@NonNull Object obj) {
            Preconditions.checkNotNull(this.zak);
            return this.zak.zad(obj);
        }

        
        @Nullable
        public final String zag() {
            String str = this.zah;
            return str;
        }

        @NonNull
        public final Map zah() {
            Preconditions.checkNotNull(this.zah);
            Preconditions.checkNotNull(this.zaj);
            return Preconditions.checkNotNull(this.zaj.zab(this.zah));
        }

        public final void zai(zan zan) {
            this.zaj = zan;
        }

        public final boolean zaj() {
            return null != zak;
        }
    }

    @ShowFirstParty
    /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
    public interface FieldConverter<I, O> {
        @NonNull
        Object zad(@NonNull Object obj);
    }

    @NonNull
    protected static final Object zaD(@NonNull Field field, @Nullable Object obj) {
        return null != field.zak ? field.zaf(obj) : obj;
    }

    private static final void zaF(StringBuilder sb, Field field, Object obj) {
        int i2 = field.zaa;
        if (11 == i2) {
            Class cls = field.zag;
            Preconditions.checkNotNull(cls);
            sb.append(cls.cast(obj).toString());
        } else if (7 == i2) {
            sb.append("\"");
            sb.append(JsonUtils.escapeString((String) obj));
            sb.append("\"");
        } else {
            sb.append(obj);
        }
    }

    @NonNull
    @KeepForSdk
    public abstract Map<String, Field<?, ?>> getFieldMappings();

    
    @KeepForSdk
    @Nullable
    public abstract Object getValueObject(@NonNull String str);

    
    @KeepForSdk
    public abstract boolean isPrimitiveFieldSet(@NonNull String str);

    @NonNull
    @KeepForSdk
    public String toString() {
        Map<String, Field<?, ?>> fieldMappings = getFieldMappings();
        StringBuilder sb = new StringBuilder(100);
        for (String next : fieldMappings.keySet()) {
            Field field = fieldMappings.get(next);
            if (isFieldSet(field)) {
                Object zaD = zaD(field, getFieldValue(field));
                if (0 == sb.length()) {
                    sb.append("{");
                } else {
                    sb.append(",");
                }
                sb.append("\"");
                sb.append(next);
                sb.append("\":");
                if (null != zaD) {
                    switch (field.zac) {
                        case 8:
                            sb.append("\"");
                            sb.append(Base64Utils.encode((byte[]) zaD));
                            sb.append("\"");
                            break;
                        case 9:
                            sb.append("\"");
                            sb.append(Base64Utils.encodeUrlSafe((byte[]) zaD));
                            sb.append("\"");
                            break;
                        case 10:
                            MapUtils.writeStringMapToJson(sb, (HashMap) zaD);
                            break;
                        default:
                            if (!field.zab) {
                                zaF(sb, field, zaD);
                                break;
                            } else {
                                ArrayList arrayList = (ArrayList) zaD;
                                sb.append("[");
                                int size = arrayList.size();
                                for (int i2 = 0; i2 < size; i2++) {
                                    if (0 < i2) {
                                        sb.append(",");
                                    }
                                    Object obj = arrayList.get(i2);
                                    if (null != obj) {
                                        zaF(sb, field, obj);
                                    }
                                }
                                sb.append("]");
                                break;
                            }
                    }
                } else {
                    sb.append("null");
                }
            }
        }
        if (0 < sb.length()) {
            sb.append("}");
        } else {
            sb.append("{}");
        }
        return sb.toString();
    }

    
    @KeepForSdk
    @Nullable
    public Object getFieldValue(@NonNull Field field) {
        String str = field.zae;
        if (null == field.zag) {
            return getValueObject(str);
        }
        Preconditions.checkState(null == this.getValueObject(str), "Concrete field shouldn't be value object: %s", field.zae);
        try {
            char upperCase = Character.toUpperCase(str.charAt(0));
            String substring = str.substring(1);
            return getClass().getMethod("get" + upperCase + substring, (Class[]) null).invoke(this, (Object[]) null);
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    
    @KeepForSdk
    public boolean isFieldSet(@NonNull Field field) {
        if (11 != field.zac) {
            return isPrimitiveFieldSet(field.zae);
        }
        if (field.zad) {
            throw new UnsupportedOperationException("Concrete type arrays not supported");
        }
        throw new UnsupportedOperationException("Concrete types not supported");
    }
}
