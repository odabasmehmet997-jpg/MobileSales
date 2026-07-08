package com.google.gson.annotations;


public @interface Expose {
    boolean deserialize() default true;

    boolean serialize() default true;
}
