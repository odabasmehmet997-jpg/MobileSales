package dagger;

public @interface Binds {
    Class<?> type() default Object.class;
}
