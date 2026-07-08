package kotlin.jvm.internal;

import androidx.webkit.ProxyConfig;
import java.util.List;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMapping;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;

/* compiled from: TypeReference.kt */

public final class TypeReference implements KType {
    public static final Companion Companion = new Companion(null);
    private final List<KTypeProjection> arguments;
    private final KClassifier classifier;
    private final int flags;
    private final KType platformTypeUpperBound;

    /* compiled from: TypeReference.kt */
    
    public class WhenMappings {
        public static final int[] EnumSwitchMapping0;

        static {
            int[] iArr = new int[KVariance.values().length];
            try {
                iArr[KVariance.INVARIANT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[KVariance.f2037IN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[KVariance.OUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            EnumSwitchMapping0 = iArr;
        }
    }

    public KClassifier getClassifier() {
        return this.classifier;
    }

    public List<KTypeProjection> getArguments() {
        return this.arguments;
    }

    public boolean isMarkedNullable() {
        return (this.flags & 1) != 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof TypeReference typeReference) {
            return Intrinsics.areEqual(getClassifier(), typeReference.getClassifier()) && Intrinsics.areEqual(getArguments(), typeReference.getArguments()) && Intrinsics.areEqual(this.platformTypeUpperBound, typeReference.platformTypeUpperBound) && this.flags == typeReference.flags;
        }
        return false;
    }

    public int hashCode() {
        return (((getClassifier().hashCode() * 31) + getArguments().hashCode()) * 31) + Integer.hashCode(this.flags);
    }

    public String toString() {
        return asString(false) + " (Kotlin reflection is not available)";
    }

    private String asString(boolean z) {
        String str;
        String str2;
        KClassifier classifier = getClassifier();
        Class<?> cls = null;
        KClass kClass = classifier instanceof KClass ? (KClass) classifier : null;
        if (kClass != null) {
            cls = JvmClassMapping.getJavaClass(kClass);
        }
        if (cls == null) {
            str = getClassifier().toString();
        } else if ((this.flags & 4) != 0) {
            str = "kotlin.Nothing";
        } else if (cls.isArray()) {
            str = getArrayClassName(cls);
        } else if (!z || !cls.isPrimitive()) {
            str = cls.getName();
        } else {
            KClassifier classifier2 = getClassifier();
            Intrinsics.checkNotNull(classifier2, "null cannot be cast to non-null type kotlin.reflect.KClass<*>");
            str = JvmClassMapping.getJavaObjectType((KClass) classifier2).getName();
        }
        String str3 = "";
        if (getArguments().isEmpty()) {
            str2 = str3;
        } else {
            str2 = CollectionsKt.joinToStringdefault(getArguments(), ", ", "<", ">", 0, null, new Function1<KTypeProjection, CharSequence>(this) { // from class: kotlin.jvm.internal.TypeReferenceasStringargs1
                final TypeReference this0;

                
                {
                    this.this0 = r1;
                }

                public CharSequence invoke(Object kTypeProjection) {
                    Intrinsics.checkNotNullParameter(kTypeProjection, "it");
                    return TypeReference.accessasString(this.this0, kTypeProjection);
                }
            }, 24, null);
        }
        if (isMarkedNullable()) {
            str3 = "?";
        }
        String str4 = str + str2 + str3;
        KType kType = this.platformTypeUpperBound;
        if (!(kType instanceof TypeReference)) {
            return str4;
        }
        String asString = ((TypeReference) kType).asString(true);
        if (Intrinsics.areEqual(asString, str4)) {
            return str4;
        }
        if (Intrinsics.areEqual(asString, str4 + '?')) {
            return str4 + '!';
        }
        return '(' + str4 + ".." + asString + ')';
    }

    private String getArrayClassName(Class<?> cls) {
        if (Intrinsics.areEqual(cls, boolean[].class)) {
            return "kotlin.BooleanArray";
        }
        if (Intrinsics.areEqual(cls, char[].class)) {
            return "kotlin.CharArray";
        }
        if (Intrinsics.areEqual(cls, byte[].class)) {
            return "kotlin.ByteArray";
        }
        if (Intrinsics.areEqual(cls, short[].class)) {
            return "kotlin.ShortArray";
        }
        if (Intrinsics.areEqual(cls, int[].class)) {
            return "kotlin.IntArray";
        }
        if (Intrinsics.areEqual(cls, float[].class)) {
            return "kotlin.FloatArray";
        }
        if (Intrinsics.areEqual(cls, long[].class)) {
            return "kotlin.LongArray";
        }
        if (Intrinsics.areEqual(cls, double[].class)) {
            return "kotlin.DoubleArray";
        }
        return "kotlin.Array";
    }

    
    public String asString(KTypeProjection kTypeProjection) {
        String str;
        if (kTypeProjection.getVariance() == null) {
            return ProxyConfig.MATCH_ALL_SCHEMES;
        }
        KType type = kTypeProjection.getType();
        TypeReference typeReference = type instanceof TypeReference ? (TypeReference) type : null;
        if (typeReference == null || (str = typeReference.asString(true)) == null) {
            str = String.valueOf(kTypeProjection.getType());
        }
        int i = WhenMappings.EnumSwitchMapping0[kTypeProjection.getVariance().ordinal()];
        if (i == 1) {
            return str;
        }
        if (i == 2) {
            return "in " + str;
        } else if (i == 3) {
            return "out " + str;
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    /* compiled from: TypeReference.kt */
    
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
