package com.proje.mobilesales.core.base;

import android.Manifest;
import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.*;
import com.proje.mobilesales.core.interfaces.AlertDialogBuilder;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.utils.FTypeControlUtils;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.features.about.view.activity.AboutActivity;
import com.proje.mobilesales.features.activity.LoginActivity;
import com.proje.mobilesales.features.activity.MainActivity;
import com.proje.mobilesales.features.activity.TransferActivity;
import com.proje.mobilesales.features.customer.model.CustomerOperation;
import com.proje.mobilesales.features.customer.view.list.CustomerListActivity;
import com.proje.mobilesales.features.dailyinformation.view.activity.DailyFicheListActivity;
import com.proje.mobilesales.features.notification.view.list.NotificationListActivity;
import com.proje.mobilesales.features.product.view.list.ProductListActivity;
import com.proje.mobilesales.features.reports.view.activity.ReportAllActivity;
import com.proje.mobilesales.features.sales.view.distribution.DistributionListActivity;
import com.proje.mobilesales.features.sales.view.list.SalesListActivity;
import com.proje.mobilesales.features.sales.view.newadd.SalesActivityNew;
import com.proje.mobilesales.features.sales.view.validation.OrderValidationListActivity;
import com.proje.mobilesales.features.settings.view.activity.SettingsActivity;
import com.proje.mobilesales.features.tools.view.activity.OtherMenuActivity;

import static com.proje.mobilesales.core.utils.AppUtils.exitApplication;


public abstract class BaseDrawerActivity extends BaseErpActivity {
 
    AlertDialogBuilder mAlertDialogBuilder;
    private View mDrawer;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Class<? extends Activity> mPendingNavigation;
    private Bundle mPendingNavigationExtras;
    String dagitim_emir_no = "";
    int dagitim_emir_id = 0;

    public static void lambdasetUpDrawer13(DialogInterface dialogInterface, int i2) {
    }
    @RequiresPermission(allOf = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WAKE_LOCK})
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivityComponent().inject(this);
        super.setContentView(R.layout.activity_drawer);
        this.mDrawer = findViewById(R.id.drawer);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        this.mDrawerLayout = drawerLayout;
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer) {  
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                if (!view.equals(BaseDrawerActivity.this.mDrawer) || BaseDrawerActivity.this.mPendingNavigation == null) {
                    return;
                }
                BaseDrawerActivity baseDrawerActivity = BaseDrawerActivity.this;
                Intent intent = new Intent(baseDrawerActivity, baseDrawerActivity.mPendingNavigation);
                if (BaseDrawerActivity.this.mPendingNavigationExtras != null) {
                    intent.putExtras(BaseDrawerActivity.this.mPendingNavigationExtras);
                    BaseDrawerActivity.this.mPendingNavigationExtras = null;
                }
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                BaseDrawerActivity.this.startActivity(intent);
                BaseDrawerActivity.this.mPendingNavigation = null;
            }
        };
        this.mDrawerToggle = actionBarDrawerToggle;
        this.mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        setUpDrawer();
    } 
    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        this.mDrawerToggle.syncState();
    }
     public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mDrawerToggle.onConfigurationChanged(configuration);
    }

    
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return this.mDrawerToggle.onOptionsItemSelected(menuItem) || super.onOptionsItemSelected(menuItem);
    }

    
    public void onBackPressed() {
        try {
            if (this.mDrawerLayout.isDrawerOpen(this.mDrawer)) {
                closeDrawers();
            } else if (isTaskRoot()) {
                moveTaskToBack(true);
            } else {
                super.onBackPressed();
            }
        } catch (Exception e2) {
            Log.e("Drawer", "onBackPressed: ", e2);
            super.onBackPressed();
        }
    }
    public void onDestroy() {
        super.onDestroy();
        this.mDrawerLayout.removeDrawerListener(this.mDrawerToggle);
    }
    public void setContentView(int i2) {
        ViewGroup viewGroup = findViewById(R.id.drawer_layout);
        viewGroup.addView(getLayoutInflater().inflate(i2, viewGroup, false), 0);
    }
    public void closeDrawers() {
        this.mDrawerLayout.closeDrawers();
    }

    void setUpDrawer() {
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        AppCompatTextView appCompatTextView = findViewById(R.id.drawer_order);
        AppCompatTextView appCompatTextView2 = findViewById(R.id.drawer_request);
        AppCompatTextView appCompatTextView3 = findViewById(R.id.drawer_distribution);
        AppCompatTextView appCompatTextView4 = findViewById(R.id.drawer_product);
        AppCompatTextView appCompatTextView5 = findViewById(R.id.drawer_report);
        AppCompatTextView appCompatTextView6 = findViewById(R.id.drawer_util);
        AppCompatTextView appCompatTextView7 = findViewById(R.id.drawer_fiche);
        AppCompatTextView appCompatTextView8 = findViewById(R.id.drawer_settings);
        AppCompatTextView appCompatTextView9 = findViewById(R.id.drawer_vehicle_transfer);
        AppCompatTextView appCompatTextView10 = findViewById(R.id.drawer_notifications);
        findViewById(R.id.drawer_main).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BaseDrawerActivity.this.lambdasetUpDrawer0(view);
            }
        });
        appCompatTextView7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BaseDrawerActivity.this.lambdasetUpDrawer1(view);
            }
        });
        if (baseErp.getUser().getType().equals(ExifInterface.GPS_MEASUREMENT_2D)) {
            appCompatTextView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BaseDrawerActivity.this.lambdasetUpDrawer2(view);
                }
            });
        } else {
            appCompatTextView.setVisibility(View.GONE);
        }
        if (baseErp.isDemand()) {
            appCompatTextView2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BaseDrawerActivity.this.lambdasetUpDrawer3(view);
                }
            });
        } else {
            appCompatTextView2.setVisibility(View.GONE);
        }
        if (baseErp.isWhTransfer()) {
            appCompatTextView9.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BaseDrawerActivity.this.lambdasetUpDrawer4(view);
                }
            });
        } else {
            appCompatTextView9.setVisibility(View.GONE);
        }
        if (baseErp.isDist() && baseErp.getErpType() != ErpType.NETSIS) {
            appCompatTextView3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BaseDrawerActivity.this.lambdasetUpDrawer5(view);
                }
            });
        } else {
            appCompatTextView3.setVisibility(View.GONE);
        }
        findViewById(R.id.drawer_customer).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BaseDrawerActivity.this.lambdasetUpDrawer6(view);
            }
        });
        if (baseErp.isReport()) {
            appCompatTextView5.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BaseDrawerActivity.this.lambdasetUpDrawer7(view);
                }
            });
        } else {
            appCompatTextView5.setVisibility(View.GONE);
        }
        if (baseErp.isProducts()) {
            appCompatTextView4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BaseDrawerActivity.this.lambdasetUpDrawer8(view);
                }
            });
        } else {
            appCompatTextView4.setVisibility(View.GONE);
        }
        if (baseErp.isOtherMenu()) {
            appCompatTextView6.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BaseDrawerActivity.this.lambdasetUpDrawer9(view);
                }
            });
        } else {
            appCompatTextView6.setVisibility(View.GONE);
        }
        findViewById(R.id.drawer_transfer).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BaseDrawerActivity.this.lambdasetUpDrawer10(view);
            }
        });
        findViewById(R.id.drawer_about).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BaseDrawerActivity.this.lambdasetUpDrawer11(view);
            }
        });
        appCompatTextView8.setOnClickListener(new View.OnClickListener() { 
            public void onClick(View view) {
                BaseDrawerActivity.this.lambdasetUpDrawer12(view);
            }
        });
        findViewById(R.id.drawer_quit).setOnClickListener(new View.OnClickListener() { 
            public void onClick(View view) {
                BaseDrawerActivity.this.lambdasetUpDrawer15(view);
            }
        });
        if (!Preferences.isDemo(this)) {
            appCompatTextView10.setOnClickListener(new View.OnClickListener() {  
                public void onClick(View view) {
                    BaseDrawerActivity.this.lambdasetUpDrawer16(view);
                }
            });
        } else {
            appCompatTextView10.setVisibility(View.GONE);
        }
    }
    public void lambdasetUpDrawer0(View view) {
        navigate(MainActivity.class);
    }
    public void lambdasetUpDrawer1(View view) {
        FTypeControlUtils.setMainFType(FType.siparis);
        checkDailyInformationReceived(DailyFicheListActivity.class, false);
    }
    public void lambdasetUpDrawer2(View view) {
        navigate(OrderValidationListActivity.class);
    }

    
    public void lambdasetUpDrawer3(View view) {
        openRequest();
    }

    
    public void lambdasetUpDrawer4(View view) {
        openWhTransfer();
    }

    
    public void lambdasetUpDrawer5(View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(IntentExtraName.EXTRA_SALES_TYPE, SalesType.FREE);
        navigate(DistributionListActivity.class, bundle);
    }

    
    public void lambdasetUpDrawer6(View view) {
        checkDailyInformationReceived(CustomerListActivity.class, false);
    }

    
    public void lambdasetUpDrawer7(View view) {
        navigate(ReportAllActivity.class);
    }

    
    public void lambdasetUpDrawer8(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt(ProductListActivity.EXTRA_WAREHOUSE_NR, -1);
        navigate(ProductListActivity.class, bundle);
    }

    
    public void lambdasetUpDrawer9(View view) {
        navigate(OtherMenuActivity.class);
    }

    
    public void lambdasetUpDrawer10(View view) {
        navigate(TransferActivity.class);
    }

    
    public void lambdasetUpDrawer11(View view) {
        navigate(AboutActivity.class);
    }

    
    public void lambdasetUpDrawer12(View view) {
        navigate(SettingsActivity.class);
    }

    
    public void lambdasetUpDrawer15(View view) {
        this.mAlertDialogBuilder.setMessage(R.string.str_exit_app).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i2) {
                BaseDrawerActivity.lambdasetUpDrawer13(dialogInterface, i2);
            }
        }).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i2) {
                BaseDrawerActivity.this.lambdasetUpDrawer14(dialogInterface, i2);
            }
        }).create().show();
    }

    
    public void lambdasetUpDrawer14(DialogInterface dialogInterface, int i2) {
        exitApplication(this, LoginActivity.class);
    }

    
    public void lambdasetUpDrawer16(View view) {
        navigate(NotificationListActivity.class);
    }

    protected void openRequest() {
        closeDrawers();
        Intent intent = new Intent(this, SalesListActivity.class);
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_REF, -1);
        CustomerOperation customerOperation = new CustomerOperation();
        customerOperation.setFicheMode(FicheMode.NEW);
        customerOperation.setSalesType(SalesType.DEMAND);
        customerOperation.setFicheType(FicheType.DEMAND);
        customerOperation.setActivity(SalesActivityNew.class);
        customerOperation.setHasSubMenu(false);
        customerOperation.setOperationName(getString(R.string.str_sales_demand));
        customerOperation.setMenuType(CustomerMenuType.SALES_DEMAND);
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE, customerOperation);
        startActivity(intent);
    }

    protected void openWhTransfer() {
        closeDrawers();
        Intent intent = new Intent(this, SalesListActivity.class);
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_REF, -1);
        CustomerOperation customerOperation = new CustomerOperation();
        customerOperation.setFicheMode(FicheMode.NEW);
        customerOperation.setSalesType(SalesType.WHTRANSFER);
        customerOperation.setFicheType(FicheType.WHTRANSFER);
        customerOperation.setActivity(SalesActivityNew.class);
        customerOperation.setHasSubMenu(false);
        customerOperation.setOperationName(getString(R.string.str_sales_transfer));
        customerOperation.setMenuType(CustomerMenuType.SALES_WHTRANSFER);
        intent.putExtra(IntentExtraName.EXTRA_CUSTOMER_OPERATION_TYPE, customerOperation);
        startActivity(intent);
    }

    protected void queryDistribution() {
        View inflate = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.dialog_distribution_inquire, findViewById(R.id.layout_root));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(inflate);
        final AlertDialog create = builder.create();
        create.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                create.hide();
            }
        });
        AppCompatButton appCompatButton = inflate.findViewById(R.id.btnOk);
        AppCompatButton appCompatButton2 = inflate.findViewById(R.id.btnCancel);
        final AppCompatEditText appCompatEditText = inflate.findViewById(R.id.etDistributionNo);
        AppCompatTextView appCompatTextView = inflate.findViewById(R.id.tvVehicleCode);
        AppCompatTextView appCompatTextView2 = inflate.findViewById(R.id.tvLicensePlate);
        appCompatTextView.setText("");
        appCompatTextView2.setText("");
        appCompatEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                if (BaseDrawerActivity.this.baseErp.getErpRights().isDemo()) {
                    BaseDrawerActivity baseDrawerActivity = BaseDrawerActivity.this;
                    Toast.makeText(baseDrawerActivity, baseDrawerActivity.getString(R.string.str_not_use_demo), Toast.LENGTH_SHORT).show();
                } else {
                    BaseDrawerActivity.this.dagitim_emir_no = appCompatEditText.getText().toString();
                }
            }
        });
        appCompatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BaseDrawerActivity.this.lambdaqueryDistribution18(create, view);
            }
        });
        appCompatButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                create.hide();
            }
        });
        create.show();
    }
    public void lambdaqueryDistribution18(AlertDialog alertDialog, View view) {
        Toast.makeText(this, getString(R.string.str_not_found_distribution_ordinance), Toast.LENGTH_LONG).show();
    }

    private void navigate(Class<? extends Activity> cls) {
        navigate(cls, null);
    }

    private void navigate(Class<? extends Activity> cls, @Nullable Bundle bundle) {
        if (getClass().equals(cls)) {
            cls = null;
        }
        this.mPendingNavigation = cls;
        this.mPendingNavigationExtras = bundle;
        closeDrawers();
    }

    public void checkDailyInformationReceived(final Class<? extends Activity> cls, final boolean z) {
        if (!this.baseErp.isDBReceivedToday()) {
            if (this.baseErp.getControlReceivedDailyInformation() == RiskAlert.NOTIFY) {
                this.mAlertDialogBuilder.setMessage(R.string.str_question_retrieval_process_information).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        dialogInterface.dismiss();
                    }
                }).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        BaseDrawerActivity.this.lambdacheckDailyInformationReceived21(cls, z, dialogInterface, i2);
                    }
                }).create().show();
                return;
            } else if (this.baseErp.getControlReceivedDailyInformation() == RiskAlert.ABORT) {
                this.mAlertDialogBuilder.setMessage(R.string.str_not_retrieval_process_information).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        dialogInterface.dismiss();
                    }
                }).create().show();
                return;
            } else {
                StartOrNavigateActivity(cls, z);
                return;
            }
        }
        StartOrNavigateActivity(cls, z);
    }

    
    public void lambdacheckDailyInformationReceived21(Class cls, boolean z, DialogInterface dialogInterface, int i2) {
        StartOrNavigateActivity(cls, z);
    }

    public void StartOrNavigateActivity(Class<? extends Activity> cls, boolean z) {
        if (z) {
            startActivity(cls);
        } else {
            navigate(cls);
        }
    }
}
