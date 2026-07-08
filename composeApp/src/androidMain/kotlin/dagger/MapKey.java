package dagger;

public @interface MapKey {
    boolean unwrapValue() default true;
}
