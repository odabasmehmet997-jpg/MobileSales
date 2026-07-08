package com.journeyapps.barcodescanner;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.google.zxing.ResultPoint;  
import java.util.ArrayList;
import java.util.List;
import com.google.zxing.client.android.R;
import org.kxml2.wap.Wbxml;

public class ViewfinderView extends View {
    protected static final int[] SCANNER_ALPHA = {0, 64, 128, Wbxml.EXT_0, 255, Wbxml.EXT_0, 128, 64};
    protected CameraPreview cameraPreview;
    protected Rect framingRect;
    protected final int laserColor;
    protected boolean laserVisibility;
    protected List<ResultPoint> lastPossibleResultPoints;
    protected int maskColor;
    protected final Paint paint;
    protected List<ResultPoint> possibleResultPoints;
    protected Size previewSize;
    protected Bitmap resultBitmap;
    protected final int resultColor;
    protected final int resultPointColor;
    protected int scannerAlpha;
    public ViewfinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.paint = new Paint(1);
        Resources resources = getResources();
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.zxing_finder);
        this.maskColor = typedArrayObtainStyledAttributes.getColor(R.styleable.zxing_finder_zxing_viewfinder_mask, resources.getColor(R.color.zxing_viewfinder_mask));
        this.resultColor = typedArrayObtainStyledAttributes.getColor(R.styleable.zxing_finder_zxing_result_view, resources.getColor(R.color.zxing_result_view));
        this.laserColor = typedArrayObtainStyledAttributes.getColor(R.styleable.zxing_finder_zxing_viewfinder_laser, resources.getColor(R.color.zxing_viewfinder_laser));
        this.resultPointColor = typedArrayObtainStyledAttributes.getColor(R.styleable.zxing_finder_zxing_possible_result_points, resources.getColor(R.color.zxing_possible_result_points));
        this.laserVisibility = typedArrayObtainStyledAttributes.getBoolean(R.styleable.zxing_finder_zxing_viewfinder_laser_visibility, true);
        typedArrayObtainStyledAttributes.recycle();
        this.scannerAlpha = 0;
        this.possibleResultPoints = new ArrayList<>(20);
        this.lastPossibleResultPoints = new ArrayList<>(20);
    }
    public void setCameraPreview(CameraPreview cameraPreview) {
        this.cameraPreview = cameraPreview;
        cameraPreview.addStateListener(new CameraPreview.StateListener() { 
            public void cameraClosed() {
            }

            
            public void cameraError(Exception exc) {
            }

            
            public void previewStarted() {
            }

            
            public void previewStopped() {
            }

            
            public void previewSized() {
                ViewfinderView.this.refreshSizes();
                ViewfinderView.this.invalidate();
            }
        });
    }
    protected void refreshSizes() {
        CameraPreview cameraPreview = this.cameraPreview;
        if (cameraPreview == null) {
            return;
        }
        Rect framingRect = cameraPreview.getFramingRect();
        Size previewSize = this.cameraPreview.getPreviewSize();
        if (framingRect == null || previewSize == null) {
            return;
        }
        this.framingRect = framingRect;
        this.previewSize = previewSize;
    }
    public void onDraw(Canvas canvas) {
        Size size;
        refreshSizes();
        Rect rect = this.framingRect;
        if (rect == null || (size = this.previewSize) == null) {
            return;
        }
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        this.paint.setColor(this.resultBitmap != null ? this.resultColor : this.maskColor);
        float f2 = width;
        canvas.drawRect(0.0f, 0.0f, f2, rect.top, this.paint);
        canvas.drawRect(0.0f, rect.top, rect.left, rect.bottom + 1, this.paint);
        canvas.drawRect(rect.right + 1, rect.top, f2, rect.bottom + 1, this.paint);
        canvas.drawRect(0.0f, rect.bottom + 1, f2, height, this.paint);
        if (this.resultBitmap != null) {
            this.paint.setAlpha(160);
            canvas.drawBitmap(this.resultBitmap, null, rect, this.paint);
            return;
        }
        if (this.laserVisibility) {
            this.paint.setColor(this.laserColor);
            Paint paint = this.paint;
            int[] r4 = SCANNER_ALPHA;
            paint.setAlpha(r4[this.scannerAlpha]);
            this.scannerAlpha = (this.scannerAlpha + 1) % r4.length;
            int r2 = (rect.height() / 2) + rect.top;
            canvas.drawRect(rect.left + 2, r2 - 1, rect.right - 1, r2 + 2, this.paint);
        }
        float width2 = getWidth() / size.width();
        float height2 = getHeight() / size.height();
        if (!this.lastPossibleResultPoints.isEmpty()) {
            this.paint.setAlpha(80);
            this.paint.setColor(this.resultPointColor);
            for (ResultPoint resultPoint : this.lastPossibleResultPoints) {
                canvas.drawCircle((int) (resultPoint.getX() * width2), (int) (resultPoint.getY() * height2), 3.0f, this.paint);
            }
            this.lastPossibleResultPoints.clear();
        }
        if (!this.possibleResultPoints.isEmpty()) {
            this.paint.setAlpha(160);
            this.paint.setColor(this.resultPointColor);
            for (ResultPoint resultPoint2 : this.possibleResultPoints) {
                canvas.drawCircle((int) (resultPoint2.getX() * width2), (int) (resultPoint2.getY() * height2), 6.0f, this.paint);
            }
            List<ResultPoint> list = this.possibleResultPoints;
            List<ResultPoint> list2 = this.lastPossibleResultPoints;
            this.possibleResultPoints = list2;
            this.lastPossibleResultPoints = list;
            list2.clear();
        }
        postInvalidateDelayed(80L, rect.left - 6, rect.top - 6, rect.right + 6, rect.bottom + 6);
    }
    public void addPossibleResultPoint(ResultPoint resultPoint) {
        if (this.possibleResultPoints.size() < 20) {
            this.possibleResultPoints.add(resultPoint);
        }
    }
    public void setMaskColor(int r1) {
        this.maskColor = r1;
    }
    public void setLaserVisibility(boolean z) {
        this.laserVisibility = z;
    }
}
