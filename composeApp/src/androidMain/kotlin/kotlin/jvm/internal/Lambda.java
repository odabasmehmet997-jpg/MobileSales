package kotlin.jvm.internal;

import java.io.Serializable;

public abstract class Lambda<R> implements FunctionBase<R>, Serializable {
    private final int arity;
    public Lambda(int i) {
        this.arity = i;
    }

    protected Lambda() {
    }

    public int getArity() {
        return this.arity;
    }
    public String toString() {
        String renderLambdaToString = Reflection.renderLambdaToString(this);
        Intrinsics.checkNotNullExpressionValue(renderLambdaToString, "renderLambdaToString(...)");
        return renderLambdaToString;
    }
}
