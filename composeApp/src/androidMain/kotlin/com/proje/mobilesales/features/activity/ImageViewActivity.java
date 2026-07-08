package com.proje.mobilesales.features.activity;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.RequiresPermission;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.MobileSales;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.tigerwcf.TigerServiceResult;
import com.proje.mobilesales.core.utils.CompressUtil;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.view.PanAndZoomListener;
import com.proje.mobilesales.core.widget.AppBarSwipeRefreshLayout;
import com.proje.mobilesales.features.model.Image;
import com.proje.mobilesales.features.sales.model.Sales;

import javax.inject.Inject;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static com.proje.mobilesales.core.utils.AppUtils.isConnected;

public class ImageViewActivity extends BaseErpActivity {
    public static final String EXTRA_CUSTOMER = ImageViewActivity.class.getName() + ".EXTRA_CUSTOMER";
    private static final String STATE_BITMAP = "state:bitmap";
    private static final String STATE_CUSTOMER = "state:customer";
    private static final String STATE_ITEM_REF = "state:itemRef";
    private ImageView imageView;
    private Bitmap mBitmap;
    private boolean mCustomerImage;
    private FrameLayout mFrameLayout;
    private int mLogicalRef;
    @Inject 
    ProgressDialogBuilder mProgressDialogBuilder;
    private AppBarSwipeRefreshLayout mSwipeRefreshLayout;
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK, Manifest.permission.ACCESS_WIFI_STATE})
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_image_view);
        setToolbar();
        initView();
        createDialog();
        if (bundle != null) {
            this.mLogicalRef = bundle.getInt(STATE_ITEM_REF);
            this.mBitmap = bundle.getParcelable(STATE_BITMAP);
            this.mCustomerImage = bundle.getBoolean(STATE_CUSTOMER, false);
        } else {
            this.mLogicalRef = getIntent().getIntExtra(IntentExtraName.EXTRA_ITEM_ID, -1);
            this.mBitmap = null;
            this.mCustomerImage = getIntent().getBooleanExtra(EXTRA_CUSTOMER, false);
            lambdainitView0();
        }
        setTitle(this.mCustomerImage ? R.string.activity_title_customer_image : R.string.activity_title_product_image);
        setImage();
    }
    private void initView() {
        this.mFrameLayout = findViewById(R.id.content_frame);
        this.mSwipeRefreshLayout = findViewById(R.id.swipe_layout);
        ImageView imageView = findViewById(R.id.img_item);
        this.imageView = imageView;
        imageView.setOnTouchListener(new PanAndZoomListener(this.mFrameLayout, imageView, 1));
        this.mSwipeRefreshLayout.setColorSchemeResources(R.color.white);
        this.mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.redA200);
        this.mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            private ImageViewActivity f0;

            @RequiresPermission(allOf = {Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE})
            public void onRefresh() {
                this.f0.lambdainitView0();
            }
        });
    }
    private void createDialog() {
        this.mProgressDialogBuilder.setMessage(getString(R.string.str_please_wait));
    }
    private void setImage() {
        Bitmap bitmap = this.mBitmap;
        if (bitmap == null) {
            this.imageView.setImageResource(R.drawable.noimage);
        } else {
            this.imageView.setImageBitmap(bitmap);
        }
    } 
    @RequiresPermission(allOf = {Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE})
    public void lambdainitView0() {
        if (isConnected(this)) {
            this.mProgressDialogBuilder.show();
            if (this.mCustomerImage) {
                this.baseErp.getCustomerImage(this.mLogicalRef, new ImageViewActivityListener(this) {
                    @Override
                    public void onResponse(Boolean aBoolean) {

                    }

                    @Override
                    public void onResponse(Sales sales) {

                    }

                    @Override
                    public void onResponse(TigerServiceResult tigerServiceResult) {

                    }

                    @Override
                    public void onResponse(List<Image> obj) {

                    }

                    @Override
                    public void onResponse(ArrayList<List<Image>> obj) {

                    }

                    @Override
                    public void onResponse() {

                    }

                    @Override
                    public void onResponse(Integer integer) {

                    }
                });
            } else {
                this.baseErp.getItemImage(this.mLogicalRef, new ImageViewActivityListener(this) {
                    @Override
                    public void onResponse(Boolean aBoolean) {

                    }

                    @Override
                    public void onResponse(Sales sales) {

                    }

                    @Override
                    public void onResponse(TigerServiceResult tigerServiceResult) {

                    }

                    @Override
                    public void onResponse(List<Image> obj) {

                    }

                    @Override
                    public void onResponse(ArrayList<List<Image>> obj) {

                    }

                    @Override
                    public void onResponse() {

                    }

                    @Override
                    public void onResponse(Integer integer) {

                    }
                });
            }
        }
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE})
    public void onStart() {
        super.onStart();
    }
    public void onDestroy() {
        super.onDestroy();
    } 
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
    public void onGetImage(List<Image> list) {
        resetSwipe();
        if (list != null && list.size() != 0) {
            try {
                this.mBitmap = CompressUtil.deCompressBitmap(CompressUtil.base64DecodeArray(list.get(0).getImage()));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            setImage();
        }
        this.mProgressDialogBuilder.dismiss();
    } 
    public void resetSwipe() {
        if (this.mSwipeRefreshLayout.isRefreshing()) {
            this.mSwipeRefreshLayout.setRefreshing(false);
        }
    }
    public void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean(STATE_CUSTOMER, this.mCustomerImage);
        bundle.putParcelable(STATE_BITMAP, this.mBitmap);
        bundle.putInt(STATE_ITEM_REF, this.mLogicalRef);
        super.onSaveInstanceState(bundle);
    }
    public abstract class ImageViewActivityListener(WeakReference<ImageViewActivity> mImageViewActivity) implements ResponseListener<List<Image>> {
        private final WeakReference<ImageViewActivity> mImageViewActivity;
        private ImageViewActivityListener(ImageViewActivity mImageViewActivity) {
                this.mImageViewActivity = new WeakReference<>(mImageViewActivity);
            }
        public void onResponse(PrintSlipModel list) {
                if (this.mImageViewActivity.get() == null || this.mImageViewActivity.get().isActivityDestroyed()) {
                    return;
                }
                this.mImageViewActivity.get().onGetImage((List<Image>) list);
            }
        public void onFailure(Throwable throwable) {
                Log.d(MobileSales.TAG, "onFailure: " + throwable.getMessage());
            }
        public void onError(String str) {
                Log.d(MobileSales.TAG, "onError: " + str);
                if (this.mImageViewActivity.get() == null || this.mImageViewActivity.get().isActivityDestroyed()) {
                    return;
                }
                this.mImageViewActivity.get().resetSwipe();
                this.mImageViewActivity.get().mProgressDialogBuilder.dismiss();
                this.mImageViewActivity.get().showToast(str);
            }
        }
}
