package kotlin.coroutines.jvm.internal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* compiled from: DebugMetadata.kt */
/*  WARN: Method from annotation default annotation not found: i */
/*  WARN: Method from annotation default annotation not found: n */
/*  WARN: Method from annotation default annotation not found: s */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface DebugMetadata {
    /* renamed from: c */
    String m23c() default "";

    /* renamed from: f */
    String m22f() default "";

    /* renamed from: l */
    int[] m21l() default {};

    /* renamed from: m */
    String m20m() default "";

    /* renamed from: v */
    int m19v() default 1;
}
