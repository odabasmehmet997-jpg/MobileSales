package com.journeyapps.barcodescanner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.core.view.ViewCompat; 
import com.journeyapps.barcodescanner.camera.CameraInstance;
import com.journeyapps.barcodescanner.camera.CameraSettings;
import com.journeyapps.barcodescanner.camera.CameraSurface;
import com.journeyapps.barcodescanner.camera.CenterCropStrategy;
import com.journeyapps.barcodescanner.camera.DisplayConfiguration;
import com.journeyapps.barcodescanner.camera.FitCenterStrategy;
import com.journeyapps.barcodescanner.camera.FitXYStrategy;
import com.journeyapps.barcodescanner.camera.PreviewScalingStrategy;
import com.proje.mobilesales.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 
public class CameraPreview extends ViewGroup {
    private static final String TAG = "CameraPreview";
    private CameraInstance cameraInstance;
    private CameraSettings cameraSettings;
    private Size containerSize;
    private Size currentSurfaceSize;
    private DisplayConfiguration displayConfiguration;
    private final StateListener fireState;
    private Rect framingRect;
    private Size framingRectSize;
    private double marginFraction;
    private int openedOrientation;
    private boolean previewActive;
    private Rect previewFramingRect;
    private PreviewScalingStrategy previewScalingStrategy;
    private Size previewSize;
    private final RotationCallback rotationCallback;
    private RotationListener rotationListener;
    private final Handler.Callback stateCallback;
    private Handler stateHandler;
    private final List<StateListener> stateListeners;
    private final SurfaceHolder.Callback surfaceCallback;
    private Rect surfaceRect;
    private SurfaceView surfaceView;
    private TextureView textureView;
    private boolean torchOn;
    private boolean useTextureView;
    private WindowManager windowManager;
    public interface StateListener {
        void cameraClosed();

        void cameraError(Exception exc);

        void previewSized();

        void previewStarted();

        void previewStopped();
    }
    protected void previewStarted() {
    }
    private TextureView.SurfaceTextureListener surfaceTextureListener() {
        return new TextureView.SurfaceTextureListener() {  
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                return false;
            }
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            } 
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int r2, int r3) {
                onSurfaceTextureSizeChanged(surfaceTexture, r2, r3);
            } 
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int r3, int r4) {
                CameraPreview.this.currentSurfaceSize = new Size(r3, r4);
                CameraPreview.this.startPreviewIfReady();
            }
        };
    }
    class C25614 implements RotationCallback {
        C25614() {
        } 
        public  void lambdaonRotationChanged0() {
            CameraPreview.this.rotationChanged();
        } 
        public void onRotationChanged(int r4) {
            CameraPreview.this.stateHandler.postDelayed(new Runnable() {
                private C25614 f0;

                public void run() {
                    this.f0.lambdaonRotationChanged0();
                }
            }, 250L);
        }
    }
    public CameraPreview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.useTextureView = false;
        this.previewActive = false;
        this.openedOrientation = -1;
        this.stateListeners = new ArrayList();
        this.cameraSettings = new CameraSettings();
        this.framingRect = null;
        this.previewFramingRect = null;
        this.framingRectSize = null;
        this.marginFraction = 0.1d;
        this.previewScalingStrategy = null;
        this.torchOn = false;
        this.surfaceCallback = new SurfaceHolder.Callback() {  
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
            } 
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                CameraPreview.this.currentSurfaceSize = null;
            }

            public void surfaceChanged(SurfaceHolder surfaceHolder, int r2, int r3, int r4) {
                if (surfaceHolder == null) {
                    Log.e(CameraPreview.TAG, "*** WARNING *** surfaceChanged() gave us a null surface!");
                    return;
                }
                CameraPreview.this.currentSurfaceSize = new Size(r3, r4);
                CameraPreview.this.startPreviewIfReady();
            }
        };
        this.stateCallback = new Handler.Callback() {  
            public boolean handleMessage(Message message) {
                int r0 = message.what;
                if (r0 == R.id.zxing_prewiew_size_ready) {
                    CameraPreview.this.previewSized((Size) message.obj);
                    return true;
                }
                if (r0 == R.id.zxing_camera_error) {
                    Exception exc = (Exception) message.obj;
                    if (!CameraPreview.this.isActive()) {
                        return false;
                    }
                    CameraPreview.this.pause();
                    CameraPreview.this.fireState.cameraError(exc);
                    return false;
                }
                if (r0 != R.id.zxing_camera_closed) {
                    return false;
                }
                CameraPreview.this.fireState.cameraClosed();
                return false;
            }
        };
        this.rotationCallback = new C25614();
        this.fireState = new StateListener() {
            public void previewSized() {
                Iterator it = CameraPreview.this.stateListeners.iterator();
                while (it.hasNext()) {
                    ((StateListener) it.next()).previewSized();
                }
            }
            public void previewStarted() {
                Iterator it = CameraPreview.this.stateListeners.iterator();
                while (it.hasNext()) {
                    ((StateListener) it.next()).previewStarted();
                }
            }
            public void previewStopped() {
                Iterator it = CameraPreview.this.stateListeners.iterator();
                while (it.hasNext()) {
                    ((StateListener) it.next()).previewStopped();
                }
            }
            public void cameraError(Exception exc) {
                Iterator it = CameraPreview.this.stateListeners.iterator();
                while (it.hasNext()) {
                    ((StateListener) it.next()).cameraError(exc);
                }
            }
            public void cameraClosed() {
                Iterator it = CameraPreview.this.stateListeners.iterator();
                while (it.hasNext()) {
                    ((StateListener) it.next()).cameraClosed();
                }
            }
        };
        initialize(context, attributeSet, 0, 0);
    }
    private void initialize(Context context, AttributeSet attributeSet, int r3, int r4) {
        if (getBackground() == null) {
            setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        }
        initializeAttributes(attributeSet);
        this.windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        this.stateHandler = new Handler(this.stateCallback);
        this.rotationListener = new RotationListener();
    }
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setupSurfaceView();
    }
    protected void initializeAttributes(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.zxing_camera_preview);
        int dimension = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.zxing_camera_preview_zxing_framing_rect_width, -1.0f);
        int dimension2 = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.zxing_camera_preview_zxing_framing_rect_height, -1.0f);
        if (dimension > 0 && dimension2 > 0) {
            this.framingRectSize = new Size(dimension, dimension2);
        }
        this.useTextureView = typedArrayObtainStyledAttributes.getBoolean(R.styleable.zxing_camera_preview_zxing_use_texture_view, true);
        int integer = typedArrayObtainStyledAttributes.getInteger(R.styleable.zxing_camera_preview_zxing_preview_scaling_strategy, -1);
        if (integer == 1) {
            this.previewScalingStrategy = new CenterCropStrategy();
        } else if (integer == 2) {
            this.previewScalingStrategy = new FitCenterStrategy();
        } else if (integer == 3) {
            this.previewScalingStrategy = new FitXYStrategy();
        }
        typedArrayObtainStyledAttributes.recycle();
    }
    public void rotationChanged() {
        if (!isActive() || getDisplayRotation() == this.openedOrientation) {
            return;
        }
        pause();
        resume();
    }
    private void setupSurfaceView() {
        if (this.useTextureView) {
            TextureView textureView = new TextureView(getContext());
            this.textureView = textureView;
            textureView.setSurfaceTextureListener(surfaceTextureListener());
            addView(this.textureView);
            return;
        }
        SurfaceView surfaceView = new SurfaceView(getContext());
        this.surfaceView = surfaceView;
        surfaceView.getHolder().addCallback(this.surfaceCallback);
        addView(this.surfaceView);
    }
    public void addStateListener(StateListener stateListener) {
        this.stateListeners.add(stateListener);
    }
    private void calculateFrames() {
        Size size;
        DisplayConfiguration displayConfiguration;
        Size size2 = this.containerSize;
        if (size2 == null || (size = this.previewSize) == null || (displayConfiguration = this.displayConfiguration) == null) {
            this.previewFramingRect = null;
            this.framingRect = null;
            this.surfaceRect = null;
            throw new IllegalStateException("containerSize or previewSize is not set yet");
        }
        int r4 = size.width();
        int r5 = size.height();
        int r6 = size2.width();
        int r0 = size2.height();
        Rect rectScalePreview = displayConfiguration.scalePreview(size);
        if (rectScalePreview.width() <= 0 || rectScalePreview.height() <= 0) {
            return;
        }
        this.surfaceRect = rectScalePreview;
        this.framingRect = calculateFramingRect(new Rect(0, 0, r6, r0), this.surfaceRect);
        Rect rect = new Rect(this.framingRect);
        Rect rect2 = this.surfaceRect;
        rect.offset(-rect2.left, -rect2.top);
        Rect rect3 = new Rect((rect.left * r4) / this.surfaceRect.width(), (rect.top * r5) / this.surfaceRect.height(), (rect.right * r4) / this.surfaceRect.width(), (rect.bottom * r5) / this.surfaceRect.height());
        this.previewFramingRect = rect3;
        if (rect3.width() <= 0 || this.previewFramingRect.height() <= 0) {
            this.previewFramingRect = null;
            this.framingRect = null;
            Log.w(TAG, "Preview frame is too small");
            return;
        }
        this.fireState.previewSized();
    }
    public void setTorch(boolean z) {
        this.torchOn = z;
        CameraInstance cameraInstance = this.cameraInstance;
        if (cameraInstance != null) {
            cameraInstance.setTorch(z);
        }
    }
    private void containerSized(Size size) {
        this.containerSize = size;
        CameraInstance cameraInstance = this.cameraInstance;
        if (cameraInstance == null || cameraInstance.getDisplayConfiguration() != null) {
            return;
        }
        DisplayConfiguration displayConfiguration = new DisplayConfiguration(getDisplayRotation(), size);
        this.displayConfiguration = displayConfiguration;
        displayConfiguration.setPreviewScalingStrategy(getPreviewScalingStrategy());
        this.cameraInstance.setDisplayConfiguration(this.displayConfiguration);
        this.cameraInstance.configureCamera();
        boolean z = this.torchOn;
        if (z) {
            this.cameraInstance.setTorch(z);
        }
    }
    public void setPreviewScalingStrategy(PreviewScalingStrategy previewScalingStrategy) {
        this.previewScalingStrategy = previewScalingStrategy;
    }
    public PreviewScalingStrategy getPreviewScalingStrategy() {
        PreviewScalingStrategy previewScalingStrategy = this.previewScalingStrategy;
        if (previewScalingStrategy != null) {
            return previewScalingStrategy;
        }
        if (this.textureView != null) {
            return new CenterCropStrategy();
        }
        return new FitCenterStrategy();
    }
    public void previewSized(Size size) {
        this.previewSize = size;
        if (this.containerSize != null) {
            calculateFrames();
            requestLayout();
            startPreviewIfReady();
        }
    }
    protected Matrix calculateTextureTransform(Size size, Size size2) {
        float f2;
        float f3 = size.width() / size.height();
        float f4 = size2.width() / size2.height();
        float f5 = 1.0f;
        if (f3 < f4) {
            float f6 = f4 / f3;
            f2 = 1.0f;
            f5 = f6;
        } else {
            f2 = f3 / f4;
        }
        Matrix matrix = new Matrix();
        matrix.setScale(f5, f2);
        int r0 = size.width();
        int r4 = size.height();
        matrix.postTranslate((r0 - (r0 * f5)) / 2.0f, (r4 - (r4 * f2)) / 2.0f);
        return matrix;
    }
    public void startPreviewIfReady() {
        Rect rect;
        Size size = this.currentSurfaceSize;
        if (size == null || this.previewSize == null || (rect = this.surfaceRect) == null) {
            return;
        }
        if (this.surfaceView != null && size.equals(new Size(rect.width(), this.surfaceRect.height()))) {
            startCameraPreview(new CameraSurface(this.surfaceView.getHolder()));
            return;
        }
        TextureView textureView = this.textureView;
        if (textureView == null || textureView.getSurfaceTexture() == null) {
            return;
        }
        if (this.previewSize != null) {
            this.textureView.setTransform(calculateTextureTransform(new Size(this.textureView.getWidth(), this.textureView.getHeight()), this.previewSize));
        }
        startCameraPreview(new CameraSurface(this.textureView.getSurfaceTexture()));
    }
    protected void onLayout(boolean z, int r2, int r3, int r4, int r5) {
        containerSized(new Size(r4 - r2, r5 - r3));
        SurfaceView surfaceView = this.surfaceView;
        if (surfaceView != null) {
            Rect rect = this.surfaceRect;
            if (rect == null) {
                surfaceView.layout(0, 0, getWidth(), getHeight());
                return;
            } else {
                surfaceView.layout(rect.left, rect.top, rect.right, rect.bottom);
                return;
            }
        }
        TextureView textureView = this.textureView;
        if (textureView != null) {
            textureView.layout(0, 0, getWidth(), getHeight());
        }
    }
    public Rect getFramingRect() {
        return this.framingRect;
    }
    public Rect getPreviewFramingRect() {
        return this.previewFramingRect;
    }
    public Size getPreviewSize() {
        return this.previewSize;
    }
    public CameraSettings getCameraSettings() {
        return this.cameraSettings;
    }
    public void setCameraSettings(CameraSettings cameraSettings) {
        this.cameraSettings = cameraSettings;
    }
    public void resume() {
        Util.validateMainThread();
        Log.d(TAG, "resume()");
        initCamera();
        if (this.currentSurfaceSize != null) {
            startPreviewIfReady();
        } else {
            SurfaceView surfaceView = this.surfaceView;
            if (surfaceView != null) {
                surfaceView.getHolder().addCallback(this.surfaceCallback);
            } else {
                TextureView textureView = this.textureView;
                if (textureView != null) {
                    if (textureView.isAvailable()) {
                        surfaceTextureListener().onSurfaceTextureAvailable(this.textureView.getSurfaceTexture(), this.textureView.getWidth(), this.textureView.getHeight());
                    } else {
                        this.textureView.setSurfaceTextureListener(surfaceTextureListener());
                    }
                }
            }
        }
        requestLayout();
        this.rotationListener.listen(getContext(), this.rotationCallback);
    }
    public void pause() {
        TextureView textureView;
        SurfaceView surfaceView;
        Util.validateMainThread();
        Log.d(TAG, "pause()");
        this.openedOrientation = -1;
        CameraInstance cameraInstance = this.cameraInstance;
        if (cameraInstance != null) {
            cameraInstance.close();
            this.cameraInstance = null;
            this.previewActive = false;
        } else {
            this.stateHandler.sendEmptyMessage(R.id.zxing_camera_closed);
        }
        if (this.currentSurfaceSize == null && (surfaceView = this.surfaceView) != null) {
            surfaceView.getHolder().removeCallback(this.surfaceCallback);
        }
        if (this.currentSurfaceSize == null && (textureView = this.textureView) != null) {
            textureView.setSurfaceTextureListener(null);
        }
        this.containerSize = null;
        this.previewSize = null;
        this.previewFramingRect = null;
        this.rotationListener.stop();
        this.fireState.previewStopped();
    }
    public void pauseAndWait() throws InterruptedException {
        CameraInstance cameraInstance = getCameraInstance();
        pause();
        long jNanoTime = System.nanoTime();
        while (cameraInstance != null && !cameraInstance.isCameraClosed() && System.nanoTime() - jNanoTime <= 2000000000) {
            try {
                Thread.sleep(1L);
            } catch (InterruptedException unused) {
                return;
            }
        }
    }
    public Size getFramingRectSize() {
        return this.framingRectSize;
    }
    public void setFramingRectSize(Size size) {
        this.framingRectSize = size;
    }
    public double getMarginFraction() {
        return this.marginFraction;
    }
    public void setMarginFraction(double d2) {
        if (d2 >= 0.5d) {
            throw new IllegalArgumentException("The margin fraction must be less than 0.5");
        }
        this.marginFraction = d2;
    }
    public void setUseTextureView(boolean z) {
        this.useTextureView = z;
    }
    protected boolean isActive() {
        return this.cameraInstance != null;
    }
    private int getDisplayRotation() {
        return this.windowManager.getDefaultDisplay().getRotation();
    }
    private void initCamera() {
        if (this.cameraInstance != null) {
            Log.w(TAG, "initCamera called twice");
            return;
        }
        CameraInstance cameraInstanceCreateCameraInstance = createCameraInstance();
        this.cameraInstance = cameraInstanceCreateCameraInstance;
        cameraInstanceCreateCameraInstance.setReadyHandler(this.stateHandler);
        this.cameraInstance.open();
        this.openedOrientation = getDisplayRotation();
    }
    protected CameraInstance createCameraInstance() {
        CameraInstance cameraInstance = new CameraInstance(getContext());
        cameraInstance.setCameraSettings(this.cameraSettings);
        return cameraInstance;
    }
    private void startCameraPreview(CameraSurface cameraSurface) {
        if (this.previewActive || this.cameraInstance == null) {
            return;
        }
        Log.i(TAG, "Starting preview");
        this.cameraInstance.setSurface(cameraSurface);
        this.cameraInstance.startPreview();
        this.previewActive = true;
        previewStarted();
        this.fireState.previewStarted();
    }
    public CameraInstance getCameraInstance() {
        return this.cameraInstance;
    }
    public boolean isPreviewActive() {
        return this.previewActive;
    }
    protected Rect calculateFramingRect(Rect rect, Rect rect2) {
        Rect rect3 = new Rect(rect);
        rect3.intersect(rect2);
        if (this.framingRectSize != null) {
            rect3.inset(Math.max(0, (rect3.width() - this.framingRectSize.width()) / 2), Math.max(0, (rect3.height() - this.framingRectSize.height()) / 2));
            return rect3;
        }
        int r5 = (int) Math.min(rect3.width() * this.marginFraction, rect3.height() * this.marginFraction);
        rect3.inset(r5, r5);
        if (rect3.height() > rect3.width()) {
            rect3.inset(0, (rect3.height() - rect3.width()) / 2);
        }
        return rect3;
    }
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelableOnSaveInstanceState = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putParcelable("super", parcelableOnSaveInstanceState);
        bundle.putBoolean("torch", this.torchOn);
        return bundle;
    }
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof Bundle bundle)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        super.onRestoreInstanceState(bundle.getParcelable("super"));
        setTorch(bundle.getBoolean("torch"));
    }
    public boolean isCameraClosed() {
        CameraInstance cameraInstance = this.cameraInstance;
        return cameraInstance == null || cameraInstance.isCameraClosed();
    }
}
