package com.proje.mobilesales.core.utils;

import com.proje.mobilesales.features.model.ErrorModel;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class UiState<T> {
    public UiState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
    public static final class Loading extends UiState {
        public static final Loading INSTANCE = new Loading();
        private Loading() {
            super(null);
        }
    }
    private UiState() {}
    public static final class Error extends UiState {
        private final ErrorModel errorModel;

        public static Error copydefault(Error error, ErrorModel errorModel, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                errorModel = error.errorModel;
            }
            return error.copy(errorModel);
        }

        public ErrorModel component1() {
            return this.errorModel;
        }

        public Error copy(ErrorModel errorModel) {
            Intrinsics.checkNotNullParameter(errorModel, "errorModel");
            return new Error(errorModel);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Error) && Intrinsics.areEqual(this.errorModel, ((Error) obj).errorModel);
        }

        public int hashCode() {
            return this.errorModel.hashCode();
        }

        public String toString() {
            return "Error(errorModel=" + this.errorModel + ')';
        }

        
        public Error(ErrorModel errorModel) {
            super(null);
            Intrinsics.checkNotNullParameter(errorModel, "errorModel");
            this.errorModel = errorModel;
        }

        public ErrorModel getErrorModel() {
            return this.errorModel;
        }

        public Error(String str) {
            this(new ErrorModel(str, null, 2, null));
        }

        public Error(int i2) {
            this(new ErrorModel(null, CollectionsKt.mutableListOf(Integer.valueOf(i2))));
        }

        public Error(String str, int i2) {
            this(new ErrorModel(str, CollectionsKt.mutableListOf(Integer.valueOf(i2))));
        }
        public Error(List<Integer> errorResourceList) {
            this(new ErrorModel(null, errorResourceList));
            Intrinsics.checkNotNullParameter(errorResourceList, "errorResourceList");
        }
    }
    public static final class Success<T> extends UiState<T> {
        private final T result;

        
        public static Success copydefault(Success success, Object obj, int i2, Object obj2) {
            if ((i2 & 1) != 0) {
                obj = success.result;
            }
            return success.copy(obj);
        }

        public T component1() {
            return this.result;
        }

        public Success<T> copy(T t) {
            return new Success<>(t);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Success) && Intrinsics.areEqual(this.result, ((Success) obj).result);
        }

        public int hashCode() {
            T t = this.result;
            if (t == null) {
                return 0;
            }
            return t.hashCode();
        }

        public String toString() {
            return "Success(result=" + this.result + ')';
        }

        public Success(T t) {
            super(null);
            this.result = t;
        }

        public T getResult() {
            return this.result;
        }
    }
}
