package com.google.android.material.shape;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import com.google.android.material.shadow.ShadowRenderer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*  INFO: loaded from: classes2.dex */
public class ShapePath {
    protected static final float ANGLE_LEFT = 180.0f;
    private static final float ANGLE_UP = 270.0f;
    private boolean containsIncompatibleShadowOp;

    @Deprecated
    public float currentShadowAngle;

    @Deprecated
    public float endShadowAngle;

    @Deprecated
    public float endX;

    @Deprecated
    public float endY;
    private final List<PathOperation> operations = new ArrayList();
    private final List<ShadowCompatOperation> shadowCompatOperations = new ArrayList();

    @Deprecated
    public float startX;

    @Deprecated
    public float startY;

    public static abstract class PathOperation {
        protected final Matrix matrix = new Matrix();

        public abstract void applyToPath(Matrix matrix, Path path);
    }

    public ShapePath() {
        reset(0.0f, 0.0f);
    }

    public ShapePath(float f2, float f3) {
        reset(f2, f3);
    }

    public void reset(float f2, float f3) {
        reset(f2, f3, ANGLE_UP, 0.0f);
    }

    public void reset(float f2, float f3, float f4, float f5) {
        setStartX(f2);
        setStartY(f3);
        setEndX(f2);
        setEndY(f3);
        setCurrentShadowAngle(f4);
        setEndShadowAngle((f4 + f5) % 360.0f);
        this.operations.clear();
        this.shadowCompatOperations.clear();
        this.containsIncompatibleShadowOp = false;
    }

    public void lineTo(float f2, float f3) {
        PathLineOperation pathLineOperation = new PathLineOperation();
        pathLineOperation.x = f2;
        pathLineOperation.y = f3;
        this.operations.add(pathLineOperation);
        LineShadowOperation lineShadowOperation = new LineShadowOperation(pathLineOperation, getEndX(), getEndY());
        addShadowCompatOperation(lineShadowOperation, lineShadowOperation.getAngle() + ANGLE_UP, lineShadowOperation.getAngle() + ANGLE_UP);
        setEndX(f2);
        setEndY(f3);
    }

    public void lineTo(float f2, float f3, float f4, float f5) {
        if ((Math.abs(f2 - getEndX()) < 0.001f && Math.abs(f3 - getEndY()) < 0.001f) || (Math.abs(f2 - f4) < 0.001f && Math.abs(f3 - f5) < 0.001f)) {
            lineTo(f4, f5);
            return;
        }
        PathLineOperation pathLineOperation = new PathLineOperation();
        pathLineOperation.x = f2;
        pathLineOperation.y = f3;
        this.operations.add(pathLineOperation);
        PathLineOperation pathLineOperation2 = new PathLineOperation();
        pathLineOperation2.x = f4;
        pathLineOperation2.y = f5;
        this.operations.add(pathLineOperation2);
        InnerCornerShadowOperation innerCornerShadowOperation = new InnerCornerShadowOperation(pathLineOperation, pathLineOperation2, getEndX(), getEndY());
        if (innerCornerShadowOperation.getSweepAngle() > 0.0f) {
            lineTo(f2, f3);
            lineTo(f4, f5);
        } else {
            addShadowCompatOperation(innerCornerShadowOperation, innerCornerShadowOperation.getStartAngle() + ANGLE_UP, innerCornerShadowOperation.getEndAngle() + ANGLE_UP);
            setEndX(f4);
            setEndY(f5);
        }
    }

    @RequiresApi(21)
    public void quadToPoint(float f2, float f3, float f4, float f5) {
        PathQuadOperation pathQuadOperation = new PathQuadOperation();
        pathQuadOperation.setControlX(f2);
        pathQuadOperation.setControlY(f3);
        pathQuadOperation.setEndX(f4);
        pathQuadOperation.setEndY(f5);
        this.operations.add(pathQuadOperation);
        this.containsIncompatibleShadowOp = true;
        setEndX(f4);
        setEndY(f5);
    }

    @RequiresApi(21)
    public void cubicToPoint(float f2, float f3, float f4, float f5, float f6, float f7) {
        this.operations.add(new PathCubicOperation(f2, f3, f4, f5, f6, f7));
        this.containsIncompatibleShadowOp = true;
        setEndX(f6);
        setEndY(f7);
    }

    public void addArc(float f2, float f3, float f4, float f5, float f6, float f7) {
        PathArcOperation pathArcOperation = new PathArcOperation(f2, f3, f4, f5);
        pathArcOperation.setStartAngle(f6);
        pathArcOperation.setSweepAngle(f7);
        this.operations.add(pathArcOperation);
        ArcShadowOperation arcShadowOperation = new ArcShadowOperation(pathArcOperation);
        float f8 = f6 + f7;
        boolean z = f7 < 0.0f;
        if (z) {
            f6 = (f6 + ANGLE_LEFT) % 360.0f;
        }
        addShadowCompatOperation(arcShadowOperation, f6, z ? (ANGLE_LEFT + f8) % 360.0f : f8);
        double d2 = f8;
        setEndX(((f2 + f4) * 0.5f) + (((f4 - f2) / 2.0f) * ((float) Math.cos(Math.toRadians(d2)))));
        setEndY(((f3 + f5) * 0.5f) + (((f5 - f3) / 2.0f) * ((float) Math.sin(Math.toRadians(d2)))));
    }

    public void applyToPath(Matrix matrix, Path path) {
        int size = this.operations.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.operations.get(i2).applyToPath(matrix, path);
        }
    }

    @NonNull
    ShadowCompatOperation createShadowCompatOperation(Matrix matrix) {
        addConnectingShadowIfNecessary(getEndShadowAngle());
        final Matrix matrix2 = new Matrix(matrix);
        final ArrayList arrayList = new ArrayList(this.shadowCompatOperations);
        return new ShadowCompatOperation() { // from class: com.google.android.material.shape.ShapePath.1
            @Override // com.google.android.material.shape.ShapePath.ShadowCompatOperation
            public void draw(Matrix matrix3, ShadowRenderer shadowRenderer, int i2, Canvas canvas) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((ShadowCompatOperation) it.next()).draw(matrix2, shadowRenderer, i2, canvas);
                }
            }
        };
    }

    private void addShadowCompatOperation(ShadowCompatOperation shadowCompatOperation, float f2, float f3) {
        addConnectingShadowIfNecessary(f2);
        this.shadowCompatOperations.add(shadowCompatOperation);
        setCurrentShadowAngle(f3);
    }

    boolean containsIncompatibleShadowOp() {
        return this.containsIncompatibleShadowOp;
    }

    private void addConnectingShadowIfNecessary(float f2) {
        if (getCurrentShadowAngle() == f2) {
            return;
        }
        float currentShadowAngle = ((f2 - getCurrentShadowAngle()) + 360.0f) % 360.0f;
        if (currentShadowAngle > ANGLE_LEFT) {
            return;
        }
        PathArcOperation pathArcOperation = new PathArcOperation(getEndX(), getEndY(), getEndX(), getEndY());
        pathArcOperation.setStartAngle(getCurrentShadowAngle());
        pathArcOperation.setSweepAngle(currentShadowAngle);
        this.shadowCompatOperations.add(new ArcShadowOperation(pathArcOperation));
        setCurrentShadowAngle(f2);
    }

    float getStartX() {
        return this.startX;
    }

    float getStartY() {
        return this.startY;
    }

    float getEndX() {
        return this.endX;
    }

    float getEndY() {
        return this.endY;
    }

    private float getCurrentShadowAngle() {
        return this.currentShadowAngle;
    }

    private float getEndShadowAngle() {
        return this.endShadowAngle;
    }

    private void setStartX(float f2) {
        this.startX = f2;
    }

    private void setStartY(float f2) {
        this.startY = f2;
    }

    private void setEndX(float f2) {
        this.endX = f2;
    }

    private void setEndY(float f2) {
        this.endY = f2;
    }

    private void setCurrentShadowAngle(float f2) {
        this.currentShadowAngle = f2;
    }

    private void setEndShadowAngle(float f2) {
        this.endShadowAngle = f2;
    }

    static abstract class ShadowCompatOperation {
        static final Matrix IDENTITY_MATRIX = new Matrix();
        final Matrix renderMatrix = new Matrix();

        public abstract void draw(Matrix matrix, ShadowRenderer shadowRenderer, int i2, Canvas canvas);

        ShadowCompatOperation() {
        }

        public final void draw(ShadowRenderer shadowRenderer, int i2, Canvas canvas) {
            draw(IDENTITY_MATRIX, shadowRenderer, i2, canvas);
        }
    }

    static class LineShadowOperation extends ShadowCompatOperation {
        private final PathLineOperation operation;
        private final float startX;
        private final float startY;

        public LineShadowOperation(PathLineOperation pathLineOperation, float f2, float f3) {
            this.operation = pathLineOperation;
            this.startX = f2;
            this.startY = f3;
        }

        @Override // com.google.android.material.shape.ShapePath.ShadowCompatOperation
        public void draw(Matrix matrix, @NonNull ShadowRenderer shadowRenderer, int i2, @NonNull Canvas canvas) {
            RectF rectF = new RectF(0.0f, 0.0f, (float) Math.hypot(this.operation.y - this.startY, this.operation.x - this.startX), 0.0f);
            this.renderMatrix.set(matrix);
            this.renderMatrix.preTranslate(this.startX, this.startY);
            this.renderMatrix.preRotate(getAngle());
            shadowRenderer.drawEdgeShadow(canvas, this.renderMatrix, rectF, i2);
        }

        float getAngle() {
            return (float) Math.toDegrees(Math.atan((this.operation.y - this.startY) / (this.operation.x - this.startX)));
        }
    }

    static class InnerCornerShadowOperation extends ShadowCompatOperation {
        private final PathLineOperation operation1;
        private final PathLineOperation operation2;
        private final float startX;
        private final float startY;

        public InnerCornerShadowOperation(PathLineOperation pathLineOperation, PathLineOperation pathLineOperation2, float f2, float f3) {
            this.operation1 = pathLineOperation;
            this.operation2 = pathLineOperation2;
            this.startX = f2;
            this.startY = f3;
        }

        @Override // com.google.android.material.shape.ShapePath.ShadowCompatOperation
        public void draw(Matrix matrix, ShadowRenderer shadowRenderer, int i2, Canvas canvas) {
            float sweepAngle = getSweepAngle();
            if (sweepAngle > 0.0f) {
                return;
            }
            double dHypot = Math.hypot(this.operation1.x - this.startX, this.operation1.y - this.startY);
            double dHypot2 = Math.hypot(this.operation2.x - this.operation1.x, this.operation2.y - this.operation1.y);
            float fMin = (float) Math.min(i2, Math.min(dHypot, dHypot2));
            double d2 = fMin;
            double dTan = Math.tan(Math.toRadians((-sweepAngle) / 2.0f)) * d2;
            if (dHypot > dTan) {
                RectF rectF = new RectF(0.0f, 0.0f, (float) (dHypot - dTan), 0.0f);
                this.renderMatrix.set(matrix);
                this.renderMatrix.preTranslate(this.startX, this.startY);
                this.renderMatrix.preRotate(getStartAngle());
                shadowRenderer.drawEdgeShadow(canvas, this.renderMatrix, rectF, i2);
            }
            float f2 = 2.0f * fMin;
            RectF rectF2 = new RectF(0.0f, 0.0f, f2, f2);
            this.renderMatrix.set(matrix);
            this.renderMatrix.preTranslate(this.operation1.x, this.operation1.y);
            this.renderMatrix.preRotate(getStartAngle());
            this.renderMatrix.preTranslate((float) ((-dTan) - d2), (-2.0f) * fMin);
            shadowRenderer.drawInnerCornerShadow(canvas, this.renderMatrix, rectF2, (int) fMin, 450.0f, sweepAngle, new float[]{(float) (d2 + dTan), f2});
            if (dHypot2 > dTan) {
                RectF rectF3 = new RectF(0.0f, 0.0f, (float) (dHypot2 - dTan), 0.0f);
                this.renderMatrix.set(matrix);
                this.renderMatrix.preTranslate(this.operation1.x, this.operation1.y);
                this.renderMatrix.preRotate(getEndAngle());
                this.renderMatrix.preTranslate((float) dTan, 0.0f);
                shadowRenderer.drawEdgeShadow(canvas, this.renderMatrix, rectF3, i2);
            }
        }

        float getStartAngle() {
            return (float) Math.toDegrees(Math.atan((this.operation1.y - this.startY) / (this.operation1.x - this.startX)));
        }

        float getEndAngle() {
            return (float) Math.toDegrees(Math.atan((this.operation2.y - this.operation1.y) / (this.operation2.x - this.operation1.x)));
        }

        float getSweepAngle() {
            float endAngle = ((getEndAngle() - getStartAngle()) + 360.0f) % 360.0f;
            return endAngle <= ShapePath.ANGLE_LEFT ? endAngle : endAngle - 360.0f;
        }
    }

    static class ArcShadowOperation extends ShadowCompatOperation {
        private final PathArcOperation operation;

        public ArcShadowOperation(PathArcOperation pathArcOperation) {
            this.operation = pathArcOperation;
        }

        @Override // com.google.android.material.shape.ShapePath.ShadowCompatOperation
        public void draw(Matrix matrix, @NonNull ShadowRenderer shadowRenderer, int i2, @NonNull Canvas canvas) {
            shadowRenderer.drawCornerShadow(canvas, matrix, new RectF(this.operation.getLeft(), this.operation.getTop(), this.operation.getRight(), this.operation.getBottom()), i2, this.operation.getStartAngle(), this.operation.getSweepAngle());
        }
    }

    public static class PathLineOperation extends PathOperation {
        private float x;
        private float y;

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(@NonNull Matrix matrix, @NonNull Path path) {
            Matrix matrix2 = this.matrix;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.lineTo(this.x, this.y);
            path.transform(matrix);
        }
    }

    public static class PathQuadOperation extends PathOperation {

        @Deprecated
        public float controlX;

        @Deprecated
        public float controlY;

        @Deprecated
        public float endX;

        @Deprecated
        public float endY;

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(@NonNull Matrix matrix, @NonNull Path path) {
            Matrix matrix2 = this.matrix;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.quadTo(getControlX(), getControlY(), getEndX(), getEndY());
            path.transform(matrix);
        }

        private float getEndX() {
            return this.endX;
        }

        /*  INFO: Access modifiers changed from: private */
        public void setEndX(float f2) {
            this.endX = f2;
        }

        private float getControlY() {
            return this.controlY;
        }

        /*  INFO: Access modifiers changed from: private */
        public void setControlY(float f2) {
            this.controlY = f2;
        }

        private float getEndY() {
            return this.endY;
        }

        /*  INFO: Access modifiers changed from: private */
        public void setEndY(float f2) {
            this.endY = f2;
        }

        private float getControlX() {
            return this.controlX;
        }

        /*  INFO: Access modifiers changed from: private */
        public void setControlX(float f2) {
            this.controlX = f2;
        }
    }

    public static class PathArcOperation extends PathOperation {
        private static final RectF rectF = new RectF();

        @Deprecated
        public float bottom;

        @Deprecated
        public float left;

        @Deprecated
        public float right;

        @Deprecated
        public float startAngle;

        @Deprecated
        public float sweepAngle;

        @Deprecated
        public float top;

        public PathArcOperation(float f2, float f3, float f4, float f5) {
            setLeft(f2);
            setTop(f3);
            setRight(f4);
            setBottom(f5);
        }

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(@NonNull Matrix matrix, @NonNull Path path) {
            Matrix matrix2 = this.matrix;
            matrix.invert(matrix2);
            path.transform(matrix2);
            RectF rectF2 = rectF;
            rectF2.set(getLeft(), getTop(), getRight(), getBottom());
            path.arcTo(rectF2, getStartAngle(), getSweepAngle(), false);
            path.transform(matrix);
        }

        /*  INFO: Access modifiers changed from: private */
        public float getLeft() {
            return this.left;
        }

        /*  INFO: Access modifiers changed from: private */
        public float getTop() {
            return this.top;
        }

        /*  INFO: Access modifiers changed from: private */
        public float getRight() {
            return this.right;
        }

        /*  INFO: Access modifiers changed from: private */
        public float getBottom() {
            return this.bottom;
        }

        private void setLeft(float f2) {
            this.left = f2;
        }

        private void setTop(float f2) {
            this.top = f2;
        }

        private void setRight(float f2) {
            this.right = f2;
        }

        private void setBottom(float f2) {
            this.bottom = f2;
        }

        /*  INFO: Access modifiers changed from: private */
        public float getStartAngle() {
            return this.startAngle;
        }

        /*  INFO: Access modifiers changed from: private */
        public float getSweepAngle() {
            return this.sweepAngle;
        }

        /*  INFO: Access modifiers changed from: private */
        public void setStartAngle(float f2) {
            this.startAngle = f2;
        }

        /*  INFO: Access modifiers changed from: private */
        public void setSweepAngle(float f2) {
            this.sweepAngle = f2;
        }
    }

    public static class PathCubicOperation extends PathOperation {
        private float controlX1;
        private float controlX2;
        private float controlY1;
        private float controlY2;
        private float endX;
        private float endY;

        public PathCubicOperation(float f2, float f3, float f4, float f5, float f6, float f7) {
            setControlX1(f2);
            setControlY1(f3);
            setControlX2(f4);
            setControlY2(f5);
            setEndX(f6);
            setEndY(f7);
        }

        @Override // com.google.android.material.shape.ShapePath.PathOperation
        public void applyToPath(@NonNull Matrix matrix, @NonNull Path path) {
            Matrix matrix2 = this.matrix;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.cubicTo(this.controlX1, this.controlY1, this.controlX2, this.controlY2, this.endX, this.endY);
            path.transform(matrix);
        }

        private float getControlX1() {
            return this.controlX1;
        }

        private void setControlX1(float f2) {
            this.controlX1 = f2;
        }

        private float getControlY1() {
            return this.controlY1;
        }

        private void setControlY1(float f2) {
            this.controlY1 = f2;
        }

        private float getControlX2() {
            return this.controlX2;
        }

        private void setControlX2(float f2) {
            this.controlX2 = f2;
        }

        private float getControlY2() {
            return this.controlY1;
        }

        private void setControlY2(float f2) {
            this.controlY2 = f2;
        }

        private float getEndX() {
            return this.endX;
        }

        private void setEndX(float f2) {
            this.endX = f2;
        }

        private float getEndY() {
            return this.endY;
        }

        private void setEndY(float f2) {
            this.endY = f2;
        }
    }
}
