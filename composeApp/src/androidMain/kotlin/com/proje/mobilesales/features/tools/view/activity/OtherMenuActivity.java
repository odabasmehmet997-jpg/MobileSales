package com.proje.mobilesales.features.tools.view.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.documentfile.provider.DocumentFile;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.enums.AnalyticsEventType;
import com.proje.mobilesales.core.interfaces.RecyclerViewClickListener;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.PermissionUtils;
import com.proje.mobilesales.core.view.RecyclerViewTouchListener;
import com.proje.mobilesales.features.adapter.ListMenuAdapter;
import com.proje.mobilesales.features.gpsinfo.view.activity.GpsInfoActivity;
import com.proje.mobilesales.features.tools.model.enums.OtherActivityMenu;
import com.proje.mobilesales.features.tools.repository.OtherMenuRepository;
import com.proje.mobilesales.features.tools.viewmodel.OtherMenuViewModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;


public final class OtherMenuActivity extends BaseErpActivity {
    public static final Companion Companion = new Companion(null);
    private final OtherMenuActivity r1 = null;
    private RecyclerView mRecyclerView;
    private List<OtherActivityMenu> menuList;
    private final OtherMenuRepository repository = new OtherMenuRepository();
    private final OtherMenuViewModel viewModel = new OtherMenuViewModel(this.repository);
    public List<OtherActivityMenu> getMenuList() {
        return this.menuList;
    }
    public void setMenuList(List<OtherActivityMenu> list) {
        this.menuList = list;
    }
    public RecyclerView getMRecyclerView() {
        return this.mRecyclerView;
    }
    public void setMRecyclerView(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
    }
    public void onCreate(Bundle bundle) {
        this.analyticsEventType = AnalyticsEventType.UTILS;
        super.onCreate(bundle);
        setContentView(R.layout.activity_menu_list);
        setToolbar();
        initRecycleView();
    }
    private void initMenu() {
        this.menuList = new ArrayList();
        if (this.viewModel.isGpsLocationInfo()) {
            List<OtherActivityMenu> list = this.menuList;
            Intrinsics.checkNotNull(list, "null cannot be cast to non-null type java.util.ArrayList<com.proje.mobilesales.features.tools.model.enums.OtherActivityMenu>{ kotlin.collections.TypeAliasesKt.ArrayList<com.proje.mobilesales.features.tools.model.enums.OtherActivityMenu> }");
            list.add(OtherActivityMenu.GPS_KONUM_BILGILERI);
        }
        if (this.viewModel.isWorkInfo()) {
            List<OtherActivityMenu> list2 = this.menuList;
            Intrinsics.checkNotNull(list2, "null cannot be cast to non-null type java.util.ArrayList<com.proje.mobilesales.features.tools.model.enums.OtherActivityMenu>{ kotlin.collections.TypeAliasesKt.ArrayList<com.proje.mobilesales.features.tools.model.enums.OtherActivityMenu> }");
            list2.add(OtherActivityMenu.START_INFO);
        }
        if (this.viewModel.isInvoiceAvgCalc()) {
            List<OtherActivityMenu> list3 = this.menuList;
            Intrinsics.checkNotNull(list3, "null cannot be cast to non-null type java.util.ArrayList<com.proje.mobilesales.features.tools.model.enums.OtherActivityMenu>{ kotlin.collections.TypeAliasesKt.ArrayList<com.proje.mobilesales.features.tools.model.enums.OtherActivityMenu> }");
            list3.add(OtherActivityMenu.AVERAGE_CALC);
        }
        List<OtherActivityMenu> list4 = this.menuList;
        Intrinsics.checkNotNull(list4, "null cannot be cast to non-null type java.util.ArrayList<com.proje.mobilesales.features.tools.model.enums.OtherActivityMenu>{ kotlin.collections.TypeAliasesKt.ArrayList<com.proje.mobilesales.features.tools.model.enums.OtherActivityMenu> }");
        list4.add(OtherActivityMenu.DB_EXPORT);
    }
    private void initRecycleView() {
        View findViewById = findViewById(R.id.rcwMenuList);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView");
        this.mRecyclerView = (RecyclerView) findViewById;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        RecyclerView recyclerView = this.mRecyclerView;
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        initMenu();
        ArrayList arrayList = new ArrayList();
        List<OtherActivityMenu> list = this.menuList;
        Intrinsics.checkNotNull(list);
        for (OtherActivityMenu otherActivityMenu : list) {
            String string = getString(otherActivityMenu.getStringMenuId());
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            arrayList.add(string);
        }
        ListMenuAdapter listMenuAdapter = new ListMenuAdapter(arrayList);
        RecyclerView recyclerView2 = this.mRecyclerView;
        Intrinsics.checkNotNull(recyclerView2);
        recyclerView2.setAdapter(listMenuAdapter);
        RecyclerView recyclerView3 = this.mRecyclerView;
        Intrinsics.checkNotNull(recyclerView3);
        recyclerView3.setItemAnimator(new DefaultItemAnimator());
        RecyclerView recyclerView4 = this.mRecyclerView;
        Intrinsics.checkNotNull(recyclerView4);
        recyclerView4.addOnItemTouchListener(new RecyclerViewTouchListener(this, this.mRecyclerView, new RecyclerViewClickListener(this) {
            final  OtherMenuActivity this0;
            {
                this.this0 = r1;
            }
            public void onClickLong(View view, int i2) {
                Intrinsics.checkNotNullParameter(view, "v");
            }
            public void onClick(View view, int i2) {
                Intrinsics.checkNotNullParameter(view, "v");
                List<OtherActivityMenu> menuList = this.this0.getMenuList();
                Intrinsics.checkNotNull(menuList);
                OtherActivityMenu otherActivityMenu2 = menuList.get(i2);
                if (otherActivityMenu2.getDestActivity() != null) {
                    if (!Intrinsics.areEqual(otherActivityMenu2.getDestActivity(), GpsInfoActivity.class)
                            || PermissionUtils.checkPermission(ContextUtils.getmActivity(), "android.permission.ACCESS_FINE_LOCATION",
                            this.this0.getString(R.string.str_location_permission_for_see_on_map)) ||
                            PermissionUtils.checkPermission(ContextUtils.getmActivity(), "android.permission.ACCESS_COARSE_LOCATION",
                                    this.this0.getString(R.string.str_location_permission_for_see_on_map))) {
                        this.this0.startActivity(otherActivityMenu2.getDestActivity());
                    }
                } else if (otherActivityMenu2.getStringMenuId() == R.string.str_export_database) {
                    Intent intent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
                    intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
                    this.this0.startActivityForResult(intent, 40);
                }
            }
        }, 1000));
    }
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }
    private boolean copyDbFilesToSelectedPath(Uri uri) {
        File[] listFiles;
        int length;
        String dbName = this.baseErp.getDbName();
        if (dbName == null) {
            return false;
        }
        File databasePath = getDatabasePath(dbName);
        if (!databasePath.exists()) {
            return false;
        }
        String parent = databasePath.getParent();
        if (TextUtils.isEmpty(parent)) {
            return false;
        }
        Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type kotlin.String");
        File file = new File(parent);
        if (!file.exists() || !file.isDirectory() || (listFiles = file.listFiles(new FilenameFilter(dbName) {
            public final  String f0 = "";
            public boolean accept(File file2, String str) {
                return OtherMenuActivity.copyDbFilesToSelectedPathlambda0(this.f0, file2, str);
            }
        })) == null || (length = listFiles.length) == 0) {
            return false;
        }
        try {
            try {
                try {
                    Intrinsics.checkNotNull(listFiles);
                    for (File file2 : listFiles) {
                        int length2 = (int) file2.length();
                        byte[] bArr = new byte[length2];
                        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file2));
                        bufferedInputStream.read(bArr, 0, length2);
                        bufferedInputStream.close();
                        Intrinsics.checkNotNull(uri);
                        DocumentFile fromTreeUri = DocumentFile.fromTreeUri(this, uri);
                        Intrinsics.checkNotNull(fromTreeUri);
                        DocumentFile createFile = fromTreeUri.createFile("application/*", file2.getName());
                        ContentResolver contentResolver = getContentResolver();
                        Intrinsics.checkNotNull(createFile);
                        OutputStream openOutputStream = contentResolver.openOutputStream(createFile.getUri());
                        Intrinsics.checkNotNull(openOutputStream);
                        openOutputStream.write(bArr);
                        openOutputStream.close();
                    }
                    return true;
                } catch (IOException e2) {
                    Log.e("OtherMenuActivity", "copyDbFilesToSelectedPath", e2);
                    return false;
                }
            } catch (Exception e4) {
                Log.e("OtherMenuActivity", "copyDbFilesToSelectedPath", e4);
                return false;
            }
        } catch (Throwable ignored) {

        }
        return false;
    }
    public static boolean copyDbFilesToSelectedPathlambda0(String str, File file, String str2) {
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNull(str);
        return StringsKt.startsWith(str2, str, false);
    }
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i3 == -1 && i2 == 40 && intent != null) {
            ContentResolver contentResolver = getContentResolver();
            Uri data = intent.getData();
            Intrinsics.checkNotNull(data);
            contentResolver.takePersistableUriPermission(data, Intent.FLAG_GRANT_READ_URI_PERMISSION & Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Toast.makeText(this, copyDbFilesToSelectedPath(intent.getData()) ? R.string.str_data_transfer_completed : R.string.str_transfer_failed, Toast.LENGTH_LONG).show();
        }
    }
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
        private Companion() {
        }
    }
}
