package com.google.android.material.shape;

import androidx.annotation.NonNull;

/*  INFO: loaded from: classes2.dex */
public class TriangleEdgeTreatment extends EdgeTreatment {
    private final boolean inside;
    private final float size;

    public TriangleEdgeTreatment(float f2, boolean z) {
        this.size = f2;
        this.inside = z;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float f2, float f3, float f4, @NonNull ShapePath shapePath) {
        if (this.inside) {
            shapePath.lineTo(f3 - (this.size * f4), 0.0f);
            float f5 = this.size;
            shapePath.lineTo(f3, f5 * f4, (f5 * f4) + f3, 0.0f);
            shapePath.lineTo(f2, 0.0f);
            return;
        }
        float f6 = this.size;
        shapePath.lineTo(f3 - (f6 * f4), 0.0f, f3, (-f6) * f4);
        shapePath.lineTo(f3 + (this.size * f4), 0.0f, f2, 0.0f);
    }
}
