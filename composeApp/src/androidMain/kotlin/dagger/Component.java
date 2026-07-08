package dagger;

public @interface Component {

    @interface Builder {
    }
    @interface Factory {
    }

    Class<?>[] dependencies() default {};

    Class<?>[] modules() default {};
}
