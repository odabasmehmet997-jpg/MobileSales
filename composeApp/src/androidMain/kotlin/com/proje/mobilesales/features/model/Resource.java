package com.proje.mobilesales.features.model;

import kotlin.jvm.internal.DefaultConstructorMarker;

public class Resource<T> {
    private final T data;
    private String exception = "";
    private final Status status;

    public Resource(final Object obj, final String str, final Status status, final DefaultConstructorMarker defaultConstructorMarker) {
        this((T) obj, str, status);
    }

    private Resource(final T t, final String str, final Status status) {
        data = t;
        exception = str;
        this.status = status;
    }

    public final T getData() {
        return data;
    }

    public final String getException() {
        return exception;
    }

    public final Status getStatus() {
        return status;
    }
    public static final class Loading<T> extends Resource<T> {
        public Loading() {
            super(null, null, Status.LOADING, null);
        }
    }
    public static final class Success<T> extends Resource<T> {
        public Success(final T t) {
            super(t, null, Status.SUCCESS, null);
        }
    }
    public static final class Error<T> extends Resource<T> {
        public Error(final String str) {
            super(null, str, Status.ERROR, null);
        }
    }
}