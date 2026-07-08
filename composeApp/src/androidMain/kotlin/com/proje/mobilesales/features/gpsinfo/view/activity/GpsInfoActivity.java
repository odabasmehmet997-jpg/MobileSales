package com.proje.mobilesales.features.gpsinfo.view.activity;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseInjectableActivity;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.interfaces.ProgressDialogBuilder;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.utils.Connectivity;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.features.customer.model.database.ClCard;
import com.proje.mobilesales.features.gpsinfo.model.database.CustGpsInfo;
import com.proje.mobilesales.features.gpsinfo.repository.GpsInfoRepository;
import com.proje.mobilesales.features.gpsinfo.viewmodel.GpsInfoViewModel;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import com.proje.mobilesales.features.sales.model.Sales;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import permissions.dispatcher.PermissionRequest;
import static org.apache.harmony.awt.internal.nls.Messages.bundle;

public final class GpsInfoActivity extends BaseInjectableActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener,
                    GoogleMap.OnMapClickListener, GoogleMap.OnMarkerDragListener, GoogleMap.OnMarkerClickListener {
    private static final String GPS_INTENT_FILTER = "android.location.PROVIDERS_CHANGED";
    public static final int SHIP_ADDRESS_CODE = 998;
    private static final String STATE_CUSTGPSINFO = "state:custGpsInfo";
    private static final String STATE_CUSTOMER_CODE = "state:customerCode";
    private static final String STATE_CUSTOMER_REF = "state:customerRef";
    private static final String STATE_CUSTOMER_TITLE = "state:customerTitle";
    private static final String STATE_EDIT_MODE = "state:editMode";
    private static final String STATE_MARKER_ADD = "state:markerAdd";
    private static final String STATE_SEND_CUSTOMER_LOCATION = "state:sendCustomerLocation";
    private static final String STATE_SHIPPING_CODE = "state:shippingCode";
    private static final String STATE_SHIPPING_INFO = "state:shippingInfo";
    private static final String STATE_SHIPPING_REF = "state:shippingRef";
    private static final String TAG = "GpsInfoFragmentActivity";
    private LocationCallback locationCallback;
    public AlertDialogBuilder<?> mAlertDialogBuilder;
    private AlertDialogBuilder<?> mAlertDialogBuilderSave;
    private AppCompatCheckBox mChkBoxOpenEditModeMap;
    private CustGpsInfo mCustGpsInfo;
    private String mCustomerCode;
    private int mCustomerRef;
    private String mCustomerTitle;
    private FloatingActionButton mFabRouteCustomer;
    private FusedLocationProviderClient mFusedLocationClient;
    private GoogleMap mGoogleMap;
    private Location mLastLocation;
    private LocationRequest mLocationRequest;
    private Marker mMarker;
    private boolean mMarkerAdd;
    private MenuItem mMenuHowToUse;
    private MenuItem mMenuSendItem;
    private boolean mOpenEditMode;
    public ProgressDialogBuilder<?> mProgressDialogBuilder;
    private boolean mSendCustomerLocation;
    private String mShippingCode;
    private String mShippingInfo;
    private int mShippingRef;
    public static final Companion Companion = new Companion(null);
    public static final String EXTRA_CUSTOMER_REF = GpsInfoActivity.class.getName() + ".EXTRA_CUSTOMER_REF";
    private static final String EXTRA_CUSTOMER_TITLE = GpsInfoActivity.class.getName() + ".EXTRA_CUSTOMER_TITLE";
    private static final String EXTRA_CUSTOMER_CODE = GpsInfoActivity.class.getName() + ".EXTRA_CUSTOMER_CODE";
    public static final String EXTRA_SHIPPING_REF = GpsInfoActivity.class.getName() + ".EXTRA_SHIPPING_REF";
    public static final String EXTRA_SHIPPING_INFO = GpsInfoActivity.class.getName() + ".EXTRA_SHIPPING_INFO";
    public static final String EXTRA_SHIPPING_CODE = GpsInfoActivity.class.getName() + ".EXTRA_SHIPPING_CODE";
    private final GpsInfoRepository repository = new GpsInfoRepository();
    private final GpsInfoViewModel viewModel = new GpsInfoViewModel(this.repository);
    private final int INTERVAL = 10000;
    private final int FAST_INTERVAL = 1000;
    private final float CAMERA_ZOOM = 12.0f;
    public Location getMLastLocation() {
        return this.mLastLocation;
    }
    public void setMLastLocation(Location location) {
        this.mLastLocation = location;
    }
    public AlertDialogBuilder<?> getMAlertDialogBuilder() {
        AlertDialogBuilder<?> alertDialogBuilder = this.mAlertDialogBuilder;
        if (alertDialogBuilder != null) {
            return alertDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mAlertDialogBuilder");
        return null;
    }
    public void setMAlertDialogBuilder(AlertDialogBuilder<?> alertDialogBuilder) {
        Intrinsics.checkNotNullParameter(alertDialogBuilder, "<set-?>");
        this.mAlertDialogBuilder = alertDialogBuilder;
    }
    public ProgressDialogBuilder<?> getMProgressDialogBuilder() {
        ProgressDialogBuilder<?> progressDialogBuilder = this.mProgressDialogBuilder;
        if (progressDialogBuilder != null) {
            return progressDialogBuilder;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mProgressDialogBuilder");
        return null;
    }
    public void setMProgressDialogBuilder(ProgressDialogBuilder<?> progressDialogBuilder) {
        Intrinsics.checkNotNullParameter(progressDialogBuilder, "<set-?>");
        this.mProgressDialogBuilder = progressDialogBuilder;
    }
     public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_gps_info);
        Activity activity = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        setMAlertDialogBuilder(new AlertDialogBuilder.Impl(this, (BaseInjectableActivity) activity));
        Activity activity2 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity2, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        this.mAlertDialogBuilderSave = new AlertDialogBuilder.Impl(this, (BaseInjectableActivity) activity2);
        Activity activity3 = ContextUtils.getmActivity();
        Intrinsics.checkNotNull(activity3, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseInjectableActivity");
        setMProgressDialogBuilder(new ProgressDialogBuilder.Impl(this, (BaseInjectableActivity) activity3));
        this.mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        setToolbar();
        if (bundle != null) {
            restoreState(bundle);
        } else {
            this.mCustomerRef = getIntent().getIntExtra(EXTRA_CUSTOMER_REF, -1);
            this.mCustomerTitle = getIntent().getStringExtra(EXTRA_CUSTOMER_TITLE);
            this.mCustomerCode = getIntent().getStringExtra(EXTRA_CUSTOMER_CODE);
            this.mShippingRef = getIntent().getIntExtra(EXTRA_SHIPPING_REF, 0);
            this.mShippingInfo = getIntent().getStringExtra(EXTRA_SHIPPING_INFO);
            this.mShippingCode = getIntent().getStringExtra(EXTRA_SHIPPING_CODE);
            this.mMarkerAdd = false;
            this.mCustGpsInfo = new CustGpsInfo();
            this.mSendCustomerLocation = false;
            this.mOpenEditMode = false;
        }
        View findViewById = findViewById(R.id.chk_openEditModeMap);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.appcompat.widget.AppCompatCheckBox");
        this.mChkBoxOpenEditModeMap = (AppCompatCheckBox) findViewById;
        if (this.mCustomerRef == -1 || !this.viewModel.getBaseErp().getErpRights().isPro() || !this.viewModel.getCustomerGpsEditMode()) {
            AppCompatCheckBox appCompatCheckBox = this.mChkBoxOpenEditModeMap;
            Intrinsics.checkNotNull(appCompatCheckBox);
            appCompatCheckBox.setVisibility(View.GONE);
        } else {
            AppCompatCheckBox appCompatCheckBox2 = this.mChkBoxOpenEditModeMap;
            Intrinsics.checkNotNull(appCompatCheckBox2);
            appCompatCheckBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {  
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    GpsInfoActivity.onCreatelambda0(GpsInfoActivity.this, compoundButton, z);
                }
            });
        }
        Fragment findFragmentById = getFragmentManager().findFragmentById(R.id.frgGoogleMap);
        Intrinsics.checkNotNull(findFragmentById, "null cannot be cast to non-null type com.google.android.gms.maps.MapFragment");
        ((MapFragment) findFragmentById).getMapAsync(this);
        createRequest();
        this.locationCallback = new LocationCallback() {
            final  GpsInfoActivity this0;
            {
                this.this0 = GpsInfoActivity.this;
            }
            public void onLocationResult(LocationResult locationResult) {
                Intrinsics.checkNotNullParameter(locationResult, "locationResult");
                if (locationResult != null) {
                    this.this0.setMLastLocation(locationResult.getLastLocation());
                }
            }
        };
        View findViewById2 = findViewById(R.id.fab_routeCustomer);
        FloatingActionButton floatingActionButton = findViewById2 instanceof FloatingActionButton ? (FloatingActionButton) findViewById2 : null;
        this.mFabRouteCustomer = floatingActionButton;
        if (floatingActionButton != null) {
            floatingActionButton.setOnClickListener(new View.OnClickListener() {  
                public  void onClick(View view) {
                    GpsInfoActivity.onCreatelambda1(GpsInfoActivity.this, view);
                }
            });
        }
        Log.d(TAG, "onCreate: customer ref :" + this.mCustomerRef);
        Log.d(TAG, "onCreate: customer name :" + this.mCustomerTitle);
    } 
    public static void onCreatelambda0(GpsInfoActivity gpsInfoActivity, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(gpsInfoActivity, "this0");
        if (compoundButton != null) {
            GpsInfoActivityPermissionsDispatcher.clickCompoundButtonWithPermissionCheck(gpsInfoActivity, compoundButton);
        }
    } 
    public static void onCreatelambda1(GpsInfoActivity gpsInfoActivity, View view) {
        Intrinsics.checkNotNullParameter(gpsInfoActivity, "this0");
        gpsInfoActivity.clickRouteToCustomer();
    }
    public  void clickCompoundButton(CompoundButton compoundButton) {
        Intrinsics.checkNotNullParameter(compoundButton, "compoundButton");
        boolean isChecked = compoundButton.isChecked();
        this.mOpenEditMode = isChecked;
        if (isChecked) {
            Log.d(TAG, "onClick: open gps");
            if (!Connectivity.isGpsConnect(this)) {
                this.mOpenEditMode = false;
                ContextUtils.checkGpsConnection();
            } else if (this.mMarker == null) {
                if (this.mLastLocation != null) {
                    Location location = this.mLastLocation;
                    Intrinsics.checkNotNull(location);
                    double latitude = location.getLatitude();
                    Location location2 = this.mLastLocation;
                    Intrinsics.checkNotNull(location2);
                    addMarkerOnMap(new LatLng(latitude, location2.getLongitude()));
                } else {
                    Toast.makeText(this, getString(R.string.str_customer_gps_location_not_found), Toast.LENGTH_LONG).show();
                }
            }
        }
        compoundButton.setChecked(this.mOpenEditMode);
        setCheckBoxClick();
        if (compoundButton.isChecked()) {
            compoundButton.setText(getString(R.string.str_customer_gps_open_edit_mode));
        } else {
            compoundButton.setText(getString(R.string.str_customer_gps_close_edit_mode));
        }
    }
    @SuppressLint("ResourceType")
    public  void showRationaleForAllPermission(PermissionRequest permissionRequest) {
        Intrinsics.checkNotNullParameter(permissionRequest, "request");
        new AlertDialog.Builder(this).setMessage(getString(R.string.str_use_gps_permission)).setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i2) {
                GpsInfoActivity.showRationaleForAllPermissionlambda2(permissionRequest, dialogInterface, i2);
            }
        }).setNegativeButton(17039360, new DialogInterface.OnClickListener() {
            public  void onClick(DialogInterface dialogInterface, int i2) {
                GpsInfoActivity.showRationaleForAllPermissionlambda3(permissionRequest, dialogInterface, i2);
            }
        }).show();
    }
    public static void showRationaleForAllPermissionlambda2(PermissionRequest permissionRequest, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(permissionRequest, "request");
        permissionRequest.proceed();
    }
    public static void showRationaleForAllPermissionlambda3(PermissionRequest permissionRequest, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(permissionRequest, "request");
        permissionRequest.cancel();
    }
    public void showDeniedForStorage() {
        Toast.makeText(this, getString(R.string.str_permission_deny), Toast.LENGTH_SHORT).show();
    }
    public void showNeverAskForStorage() {
        Toast.makeText(this, getString(R.string.str_permission_never_ask), Toast.LENGTH_SHORT).show();
    }
    private void setCheckBoxClick() {
        setMenuItemVisible();
        GoogleMap googleMap = this.mGoogleMap;
        if (googleMap == null) {
            return;
        }
        if (this.mOpenEditMode) {
            Intrinsics.checkNotNull(googleMap);
            googleMap.setOnMarkerDragListener(this);
            GoogleMap googleMap2 = this.mGoogleMap;
            Intrinsics.checkNotNull(googleMap2);
            googleMap2.setOnMapClickListener(this);
            Marker marker = this.mMarker;
            if (marker != null) {
                Intrinsics.checkNotNull(marker);
                marker.setDraggable(true);
                return;
            }
            return;
        }
        Intrinsics.checkNotNull(googleMap);
        googleMap.setOnMarkerDragListener(null);
        GoogleMap googleMap3 = this.mGoogleMap;
        Intrinsics.checkNotNull(googleMap3);
        googleMap3.setOnMapClickListener(null);
        Marker marker2 = this.mMarker;
        if (marker2 != null) {
            Intrinsics.checkNotNull(marker2);
            marker2.setDraggable(false);
        }
    }
    private void setVisibilityRouteToCustomerButton() {
        if (this.mMarkerAdd) {
            FloatingActionButton floatingActionButton = this.mFabRouteCustomer;
            Intrinsics.checkNotNull(floatingActionButton);
            floatingActionButton.setVisibility(View.VISIBLE);
        }
    }
    private void clickRouteToCustomer() {
        Marker marker = this.mMarker;
        Intrinsics.checkNotNull(marker);
        String valueOf = String.valueOf(marker.getPosition().latitude);
        Marker marker2 = this.mMarker;
        Intrinsics.checkNotNull(marker2);
        String valueOf2 = String.valueOf(marker2.getPosition().longitude);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("google.navigation:q=" + valueOf + ',' + valueOf2 + "&mode=d"));
        intent.setPackage("com.google.android.apps.maps");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    private void setMenuItemVisible() {
        MenuItem menuItem = this.mMenuSendItem;
        if (menuItem != null) {
            Intrinsics.checkNotNull(menuItem);
            menuItem.setVisible(this.mOpenEditMode);
        }
        MenuItem menuItem2 = this.mMenuHowToUse;
        if (menuItem2 != null) {
            Intrinsics.checkNotNull(menuItem2);
            menuItem2.setVisible(this.mOpenEditMode);
        }
    }
    private void createRequest() {
        this.mLocationRequest = LocationRequest.create().setPriority(104).setInterval(this.INTERVAL).setFastestInterval(this.FAST_INTERVAL);
    }  
    public void onMapReady(GoogleMap googleMap) {
        Intrinsics.checkNotNullParameter(googleMap, "googleMap");
        this.mGoogleMap = googleMap;
        GpsInfoActivityPermissionsDispatcher.initMapWithPermissionCheck(this);
    } 
    @RequiresPermission(anyOf = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    public void initMap() {
        GoogleMap googleMap = this.mGoogleMap;
        Intrinsics.checkNotNull(googleMap);
        googleMap.setMapType(1);
        GoogleMap googleMap2 = this.mGoogleMap;
        Intrinsics.checkNotNull(googleMap2);
        googleMap2.setTrafficEnabled(true);
        GoogleMap googleMap3 = this.mGoogleMap;
        Intrinsics.checkNotNull(googleMap3);
        googleMap3.setIndoorEnabled(true);
        GoogleMap googleMap4 = this.mGoogleMap;
        Intrinsics.checkNotNull(googleMap4);
        googleMap4.setBuildingsEnabled(true);
        GoogleMap googleMap5 = this.mGoogleMap;
        Intrinsics.checkNotNull(googleMap5);
        googleMap5.getUiSettings().setZoomControlsEnabled(true);
        GoogleMap googleMap6 = this.mGoogleMap;
        Intrinsics.checkNotNull(googleMap6);
        googleMap6.getUiSettings().setCompassEnabled(true);
        GoogleMap googleMap7 = this.mGoogleMap;
        Intrinsics.checkNotNull(googleMap7);
        googleMap7.setMyLocationEnabled(true);
        GoogleMap googleMap8 = this.mGoogleMap;
        Intrinsics.checkNotNull(googleMap8);
        googleMap8.setOnMyLocationButtonClickListener(this);
        if (this.mShippingRef == 0) {
            getCustomerLocations();
            setVisibilityRouteToCustomerButton();
        } else {
            getCustomerShippingLocation();
            setVisibilityRouteToCustomerButton();
        }
        if (this.mCustomerRef != -1 && this.viewModel.getBaseErp().getErpRights().isPro()) {
            GoogleMap googleMap9 = this.mGoogleMap;
            Intrinsics.checkNotNull(googleMap9);
            googleMap9.setOnMarkerDragListener(this);
            GoogleMap googleMap10 = this.mGoogleMap;
            Intrinsics.checkNotNull(googleMap10);
            googleMap10.setOnMapClickListener(this);
        }
        FusedLocationProviderClient fusedLocationProviderClient = this.mFusedLocationClient;
        Intrinsics.checkNotNull(fusedLocationProviderClient);
        LocationRequest locationRequest = this.mLocationRequest;
        Intrinsics.checkNotNull(locationRequest);
        LocationCallback locationCallback = this.locationCallback;
        Intrinsics.checkNotNull(locationCallback);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager. PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }
    @RequiresPermission(anyOf = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    public void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            FusedLocationProviderClient fusedLocationProviderClient = this.mFusedLocationClient;
            Intrinsics.checkNotNull(fusedLocationProviderClient);
            LocationRequest locationRequest = this.mLocationRequest;
            Intrinsics.checkNotNull(locationRequest);
            LocationCallback locationCallback = this.locationCallback;
            Intrinsics.checkNotNull(locationCallback);
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
        }
    }
    @RequiresPermission(allOf = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    public void onPause() {
        super.onPause();
        googleApiDisconnectAndRemoveListener();
    } 
    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        if (this.mCustomerRef != -1) {
            getMenuInflater().inflate(R.menu.menu_gps, menu);
            this.mMenuSendItem = menu.findItem(R.id.menu_save);
            this.mMenuHowToUse = menu.findItem(R.id.menu_how_to_use);
        }
        return super.onCreateOptionsMenu(menu);
    } 
    public boolean onPrepareOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        if (this.mCustomerRef != -1 && !this.viewModel.getBaseErp().getErpRights().isPro()) {
            this.mOpenEditMode = false;
        }
        setMenuItemVisible();
        return super.onPrepareOptionsMenu(menu);
    } 
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
        } else if (menuItem == this.mMenuSendItem) {
            saveCustomerGpsLocationDialog();
        } else if (menuItem == this.mMenuHowToUse) {
            openHowToUseDialog();
        }
        return super.onOptionsItemSelected(menuItem);
    }
    @RequiresPermission(allOf = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    private Unit getCustomerShippingLocation() {
        fillCustomerInfoWhenShippingRefExists();
        List table = this.viewModel.getBaseErp().getLogoSqlHelper().getTable(CustGpsInfo.class, " CLIENTREF = ? AND SHIPINFOREF = ? ", new String[]{String.valueOf(this.mCustomerRef), String.valueOf(this.mShippingRef)});
        if (table != null && !table.isEmpty()) {
            this.mCustGpsInfo = (CustGpsInfo) table.get(0);
            MarkerOptions markerOptions = new MarkerOptions();
            CustGpsInfo custGpsInfo = this.mCustGpsInfo;
            Intrinsics.checkNotNull(custGpsInfo);
            double d2 = custGpsInfo.latitude;
            CustGpsInfo custGpsInfo2 = this.mCustGpsInfo;
            Intrinsics.checkNotNull(custGpsInfo2);
            markerOptions.position(new LatLng(d2, custGpsInfo2.longtitude)).title(TextUtils.isEmpty(this.mShippingInfo) ? " " : this.mShippingInfo).snippet(this.mShippingCode);
            GoogleMap googleMap = this.mGoogleMap;
            Intrinsics.checkNotNull(googleMap);
            this.mMarker = googleMap.addMarker(markerOptions);
            this.mMarkerAdd = true;
        }
        return Unit.INSTANCE;
    }
    @RequiresPermission(anyOf = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    private void fillCustomerInfoWhenShippingRefExists() {
        List table = this.viewModel.getBaseErp().getLogoSqlHelper().getTable(ClCard.class, " LOGICALREF = ? ", new String[]{String.valueOf(this.mCustomerRef)});
        if (table != null && !table.isEmpty()) {
            this.mCustomerCode = ((ClCard) table.get(0)).getCode();
            this.mCustomerTitle = ((ClCard) table.get(0)).getDefinition();
        }
    }
    private void getCustomerLocations() {
        String str;
        String str2;
        if (this.mCustomerRef != -1) {
            ErpType erpType = this.viewModel.getBaseErp().getErpType();
            ErpType erpType2 = ErpType.NETSIS;
            String str3 = erpType == erpType2 ? " CLCODE = ?  AND SHIPINFOREF = 0 " : " CLIENTREF = ? AND SHIPINFOREF = 0 ";
            if (this.viewModel.getBaseErp().getErpType() == erpType2) {
                str2 = this.viewModel.getBaseErp().getCustomerClCode(this.mCustomerRef);
            } else {
                str2 = String.valueOf(this.mCustomerRef);
            }
            List table = this.viewModel.getBaseErp().getLogoSqlHelper().getTable(CustGpsInfo.class, str3, new String[]{str2});
            if (table != null && !table.isEmpty()) {
                this.mCustGpsInfo = (CustGpsInfo) table.get(0);
                CustGpsInfo custGpsInfo = this.mCustGpsInfo;
                Intrinsics.checkNotNull(custGpsInfo);
                double d2 = custGpsInfo.latitude;
                CustGpsInfo custGpsInfo2 = this.mCustGpsInfo;
                Intrinsics.checkNotNull(custGpsInfo2);
                addMarkerOnMapCustGps(new LatLng(d2, custGpsInfo2.longtitude));
            }
        } else {
            ArrayList<CustGpsInfo> customerLocations = this.viewModel.getCustomerLocations();
            if (!customerLocations.isEmpty()) {
                Log.d(TAG, "getCustomerLocations: size : " + customerLocations.size());
                for (CustGpsInfo next : customerLocations) {
                    MarkerOptions markerOptions = new MarkerOptions();
                    MarkerOptions position = markerOptions.position(new LatLng(next.latitude, next.longtitude));
                    String str4 = next.clName;
                    if (str4 != null) {
                        Intrinsics.checkNotNull(str4);
                        if (!str4.isEmpty()) {
                            str = next.clName;
                            position.title(str).snippet(next.clCode);
                            GoogleMap googleMap = this.mGoogleMap;
                            Intrinsics.checkNotNull(googleMap);
                            googleMap.addMarker(markerOptions);
                        }
                    }
                    str = " ";
                    position.title(str).snippet(next.clCode);
                    GoogleMap googleMap = this.mGoogleMap;
                    Intrinsics.checkNotNull(googleMap);
                    googleMap.addMarker(markerOptions);
                }
            }
        }
    }
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public void finish() {
    }
    public boolean onMyLocationButtonClick() {
        try {
            if (this.mLastLocation == null) {
                return false;
            }
            Location location = this.mLastLocation;
            Intrinsics.checkNotNull(location);
            double latitude = location.getLatitude();
            Location location2 = this.mLastLocation;
            Intrinsics.checkNotNull(location2);
            LatLng latLng = new LatLng(latitude, location2.getLongitude());
            GoogleMap googleMap = this.mGoogleMap;
            Intrinsics.checkNotNull(googleMap);
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, this.CAMERA_ZOOM));
            addMarkerOnMap(latLng);
            return false;
        } catch (Exception e2) {
            Log.e(TAG, "onMyLocationButtonClick: ", e2);
            return false;
        }
    } 
    public void onMarkerDragStart(Marker marker) {
        Intrinsics.checkNotNullParameter(marker, "marker");
        Log.d(TAG, "onMarkerDragStart..." + marker.getPosition().latitude + "..." + marker.getPosition().longitude);
    } 
    public void onMarkerDrag(Marker marker) {
        Intrinsics.checkNotNullParameter(marker, "marker");
        Log.d(TAG, "onMarkerDragEnd..." + marker.getPosition().latitude + "..." + marker.getPosition().longitude);
        GoogleMap googleMap = this.mGoogleMap;
        Intrinsics.checkNotNull(googleMap);
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
    } 
    public void onMarkerDragEnd(Marker marker) {
        Intrinsics.checkNotNullParameter(marker, "marker");
        Log.i(TAG, "onMarkerDrag...");
    } 
    public boolean onMarkerClick(Marker marker) {
        Intrinsics.checkNotNullParameter(marker, "marker");
        GoogleMap googleMap = this.mGoogleMap;
        Intrinsics.checkNotNull(googleMap);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), this.CAMERA_ZOOM));
        return false;
    }
    @RequiresPermission(anyOf = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION})
    private void googleApiDisconnectAndRemoveListener() {
        FusedLocationProviderClient fusedLocationProviderClient = this.mFusedLocationClient;
        Intrinsics.checkNotNull(fusedLocationProviderClient);
        LocationCallback locationCallback = this.locationCallback;
        Intrinsics.checkNotNull(locationCallback);
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        super.onSaveInstanceState(bundle);
    }
    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        bundle.putInt("state:customerRef", this.mCustomerRef);
        bundle.putString(STATE_CUSTOMER_TITLE, this.mCustomerTitle);
        bundle.putBoolean(STATE_MARKER_ADD, this.mMarkerAdd);
        bundle.putParcelable(STATE_CUSTGPSINFO, this.mCustGpsInfo);
        bundle.putString("state:customerCode", this.mCustomerCode);
        bundle.putBoolean("state:editMode", this.mOpenEditMode);
        bundle.putBoolean(STATE_SEND_CUSTOMER_LOCATION, this.mSendCustomerLocation);
        bundle.putInt(STATE_SHIPPING_REF, this.mShippingRef);
        bundle.putString(STATE_SHIPPING_INFO, this.mShippingInfo);
        bundle.putString(STATE_SHIPPING_CODE, this.mShippingCode);
        super.onSaveInstanceState(bundle);
    } 
    protected void onRestoreInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "savedInstanceState");
        super.onRestoreInstanceState(bundle);
        restoreState(bundle);
    }
    private void restoreState(Bundle bundle) {
        if (bundle != null) {
            this.mCustomerRef = bundle.getInt("state:customerRef", -1);
            this.mCustomerTitle = bundle.getString(STATE_CUSTOMER_TITLE);
            this.mMarkerAdd = bundle.getBoolean(STATE_MARKER_ADD, false);
            this.mCustGpsInfo = bundle.getParcelable(STATE_CUSTGPSINFO);
            this.mCustomerCode = bundle.getString("state:customerCode");
            this.mSendCustomerLocation = bundle.getBoolean(STATE_SEND_CUSTOMER_LOCATION);
            this.mOpenEditMode = bundle.getBoolean("state:editMode", this.mOpenEditMode);
            this.mShippingRef = bundle.getInt(STATE_SHIPPING_REF, 0);
            this.mShippingInfo = bundle.getString(STATE_SHIPPING_INFO);
            this.mShippingCode = bundle.getString(STATE_SHIPPING_CODE);
        }
    }
    @SuppressLint("ResourceType")
    private void addMarkerOnMapCustGps(@NonNull LatLng latLng) {
        String str;
        GoogleMap googleMap = this.mGoogleMap;
        Intrinsics.checkNotNull(googleMap);
        MarkerOptions position = new MarkerOptions().position(latLng);
        String str2 = this.mCustomerTitle;
        if (str2 != null) {
            Intrinsics.checkNotNull(str2);
            if (str2.length() != 0) {
                str = this.mCustomerTitle;
                this.mMarker = googleMap.addMarker(position.title(str).snippet(this.mCustomerCode));
                this.mMarkerAdd = true;
            }
        }
        str = " ";
        this.mMarker = googleMap.addMarker(position.title(str).snippet(this.mCustomerCode));
        this.mMarkerAdd = true;
    }
    private  void addMarkerOnMap(LatLng latLng) {
        if (this.mOpenEditMode) {
            if (!this.viewModel.getBaseErp().getErpRights().isPro()) {
                Toast.makeText(this, R.string.str_gps_use_licence, Toast.LENGTH_LONG).show();
            } else if (this.mCustomerRef != -1 && !this.mMarkerAdd) {
                GoogleMap googleMap = this.mGoogleMap;
                Intrinsics.checkNotNull(googleMap);
                MarkerOptions markerOptions = new MarkerOptions();
                Intrinsics.checkNotNull(latLng);
                this.mMarker = googleMap.addMarker(markerOptions.position(latLng).title(this.mCustomerTitle).snippet(this.mCustomerCode).draggable(true));
                this.mMarkerAdd = true;
            } else if (latLng != null) {
                Marker marker = this.mMarker;
                Intrinsics.checkNotNull(marker);
                marker.setPosition(latLng);
                Marker marker2 = this.mMarker;
                Intrinsics.checkNotNull(marker2);
                marker2.setTitle(this.mCustomerTitle);
                Marker marker3 = this.mMarker;
                Intrinsics.checkNotNull(marker3);
                marker3.setDraggable(true);
            }
        }
    } 
    public void onMapClick(LatLng latLng) {
        Intrinsics.checkNotNullParameter(latLng, "latLng");
        Log.d(TAG, "onMapClick: onMap click");
        addMarkerOnMap(latLng);
    }
    @SuppressLint("ResourceType")
    private  void openHowToUseDialog() {
        getMAlertDialogBuilder().setTitle(R.string.str_customer_gps_how_to_use_title).setMessage(R.string.str_customer_gps_how_to_use_message).setNegativeButton(17039370,
                new DialogInterface.OnClickListener() {
            public  void onClick(DialogInterface dialogInterface, int i2) {
                GpsInfoActivity.openHowToUseDialoglambda4(dialogInterface, i2);
            }
        }).create().show();
    }
    public static void openHowToUseDialoglambda4(DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        dialogInterface.dismiss();
    }
    private void saveCustomerGpsLocationDialog() {
       
        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.gpsinfo.view.activity.GpsInfoActivity.saveCustomerGpsLocationDialog():void");
    }
    public static  void saveCustomerGpsLocationDialoglambda5(DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        dialogInterface.dismiss();
    }
    public static  void saveCustomerGpsLocationDialoglambda6(GpsInfoActivity gpsInfoActivity, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(gpsInfoActivity, "this0");
        gpsInfoActivity.saveCustomerGpsLocation();
    }
    public static  void saveCustomerGpsLocationDialoglambda7(DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(dialogInterface, "dialogInterface");
        dialogInterface.dismiss();
    }
    public static  void saveCustomerGpsLocationDialoglambda8(GpsInfoActivity gpsInfoActivity, DialogInterface dialogInterface, int i2) {
        Intrinsics.checkNotNullParameter(gpsInfoActivity, "this0");
        gpsInfoActivity.updateCustomerGpsLocation();
    } 
    private  void updateCustomerGpsLocation() {
        getMProgressDialogBuilder().setMessage(getString(R.string.str_please_wait)).setCancelable(false).show();
        CustGpsInfo custGpsInfo = this.mCustGpsInfo;
        Intrinsics.checkNotNull(custGpsInfo);
        Marker marker = this.mMarker;
        Intrinsics.checkNotNull(marker);
        custGpsInfo.latitude = marker.getPosition().latitude;
        CustGpsInfo custGpsInfo2 = this.mCustGpsInfo;
        Intrinsics.checkNotNull(custGpsInfo2);
        Marker marker2 = this.mMarker;
        Intrinsics.checkNotNull(marker2);
        custGpsInfo2.longtitude = marker2.getPosition().longitude;
        CustGpsInfo custGpsInfo3 = this.mCustGpsInfo;
        Intrinsics.checkNotNull(custGpsInfo3);
        custGpsInfo3.isTransfer = 0;
        GpsInfoViewModel gpsInfoViewModel = this.viewModel;
        CustGpsInfo custGpsInfo4 = this.mCustGpsInfo;
        Intrinsics.checkNotNull(custGpsInfo4);
        gpsInfoViewModel.updateCustomerLocation(custGpsInfo4, new GpsSaveResponseListener(this));
    }
    private void saveCustomerGpsLocation() {
        getMProgressDialogBuilder().setMessage(getString(R.string.str_please_wait)).setCancelable(false).show();
        if (this.mMarker == null) {
            Toast.makeText(this, getString(R.string.str_customer_gps_location_not_found), Toast.LENGTH_LONG).show();
            return;
        }
        CustGpsInfo custGpsInfo = this.mCustGpsInfo;
        Intrinsics.checkNotNull(custGpsInfo);
        Marker marker = this.mMarker;
        Intrinsics.checkNotNull(marker);
        custGpsInfo.latitude = marker.getPosition().latitude;
        CustGpsInfo custGpsInfo2 = this.mCustGpsInfo;
        Intrinsics.checkNotNull(custGpsInfo2);
        Marker marker2 = this.mMarker;
        Intrinsics.checkNotNull(marker2);
        custGpsInfo2.longtitude = marker2.getPosition().longitude;
        CustGpsInfo custGpsInfo3 = this.mCustGpsInfo;
        Intrinsics.checkNotNull(custGpsInfo3);
        custGpsInfo3.isTransfer = 0;
        CustGpsInfo custGpsInfo4 = this.mCustGpsInfo;
        Intrinsics.checkNotNull(custGpsInfo4);
        custGpsInfo4.clName = this.mCustomerTitle;
        CustGpsInfo custGpsInfo5 = this.mCustGpsInfo;
        Intrinsics.checkNotNull(custGpsInfo5);
        custGpsInfo5.clientRef = this.mCustomerRef;
        CustGpsInfo custGpsInfo6 = this.mCustGpsInfo;
        Intrinsics.checkNotNull(custGpsInfo6);
        custGpsInfo6.clCode = this.mCustomerCode;
        CustGpsInfo custGpsInfo7 = this.mCustGpsInfo;
        Intrinsics.checkNotNull(custGpsInfo7);
        custGpsInfo7.shippingRef = this.mShippingRef;
        setVisibilityRouteToCustomerButton();
        GpsInfoViewModel gpsInfoViewModel = this.viewModel;
        CustGpsInfo custGpsInfo8 = this.mCustGpsInfo;
        Intrinsics.checkNotNull(custGpsInfo8);
        gpsInfoViewModel.saveCustomerLocation(custGpsInfo8, new GpsSaveResponseListener(this));
    }
    public void onCustomerGpsSaveResponse(Boolean bool, String str) {
        getMProgressDialogBuilder().dismiss();
        Intrinsics.checkNotNull(bool);
        if (bool.booleanValue()) {
            Toast.makeText(this, R.string.str_customer_gps_save_success, Toast.LENGTH_SHORT).show();
            this.mSendCustomerLocation = true;
        } else {
            Toast.makeText(this, R.string.str_customer_gps_save_failed, Toast.LENGTH_SHORT).show();
        }
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(this, str, Toast.LENGTH_LONG).show();
        }
    } 
    public static final class GpsSaveResponseListener implements ResponseListener<Boolean> {
        private final WeakReference<GpsInfoActivity> mGpsSaveResponseListener;
        public GpsSaveResponseListener(GpsInfoActivity gpsInfoActivity) {
            this.mGpsSaveResponseListener = new WeakReference<>(gpsInfoActivity);
        }
        public void onResponse(Boolean bool) {
            if (this.mGpsSaveResponseListener.get() != null) {
                GpsInfoActivity gpsInfoActivity = this.mGpsSaveResponseListener.get();
                Intrinsics.checkNotNull(gpsInfoActivity);
                if (!gpsInfoActivity.isActivityDestroyed()) {
                    GpsInfoActivity gpsInfoActivity2 = this.mGpsSaveResponseListener.get();
                    Intrinsics.checkNotNull(gpsInfoActivity2);
                    gpsInfoActivity2.onCustomerGpsSaveResponse(bool, "");
                }
            }
        }
        public void onResponse(Sales sales) {
        }
        public void onError(String str) {
            Intrinsics.checkNotNullParameter(str, "errorMessage");
            if (this.mGpsSaveResponseListener.get() != null) {
                GpsInfoActivity gpsInfoActivity = this.mGpsSaveResponseListener.get();
                Intrinsics.checkNotNull(gpsInfoActivity);
                if (!gpsInfoActivity.isActivityDestroyed()) {
                    GpsInfoActivity gpsInfoActivity2 = this.mGpsSaveResponseListener.get();
                    Intrinsics.checkNotNull(gpsInfoActivity2);
                    gpsInfoActivity2.onCustomerGpsSaveResponse(Boolean.FALSE, str);
                }
            }
        }
        public void onResponse(PrintSlipModel t) {
        }
        public void onFailure(Throwable throwable) {
        }
    } 
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        Intrinsics.checkNotNullParameter(strArr, "permissions");
        Intrinsics.checkNotNullParameter(iArr, "grantResults");
        super.onRequestPermissionsResult(i2, strArr, iArr);
        GpsInfoActivityPermissionsDispatcher.onRequestPermissionsResult(this, i2, iArr);
    } 
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
        public String getEXTRA_CUSTOMER_TITLE() {
            return GpsInfoActivity.EXTRA_CUSTOMER_TITLE;
        }
        public String getEXTRA_CUSTOMER_CODE() {
            return GpsInfoActivity.EXTRA_CUSTOMER_CODE;
        }
    }
}