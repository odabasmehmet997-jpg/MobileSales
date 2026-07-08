package com.proje.mobilesales.features.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import androidx.appcompat.widget.AppCompatTextView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.core.utils.StringUtils;

public class MarketingMessageListActivity extends BaseErpActivity {
    private static final String TAG = "MMEssageActivity";
    ListView lvList;
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.listnosearch);
        lvList = this.findViewById(R.id.lvList_listnosearch);
        this.setToolbar();
        lvList.setEmptyView(this.findViewById(R.id.noEntry));
        this.registerForContextMenu(lvList);
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView adapterView, final View view, final int i2, final long j2) {
                lambdaonCreate0(adapterView, view, i2, j2);
            }
        });
        this.PazarlamaMesajListesiniGetir();
    }

    public  void lambdaonCreate0(final AdapterView adapterView, final View view, final int i2, final long j2) {
        final int convertStringToInt = StringUtils.convertStringToInt(String.valueOf(((AppCompatTextView) view.findViewById(R.id.MMID)).getText()));
        final Intent intent = new Intent(this, MarketingMessageViewActivity.class);
        intent.putExtra(IntentExtraName.EXTRAS_MESSAGE_REF, convertStringToInt);
        this.startActivityForResult(intent, 0);
    }

    public void PazarlamaMesajListesiniGetir() {
        try {
            final String[] strArr = {"_id", "DEFINITION_"};
            final Cursor rawQuery = baseErp.getLogoSqlHelper().getReadableDatabase().rawQuery(this.getString(R.string.qry_markettingMessageList), null);
            final AppCompatTextView appCompatTextView = this.findViewById(R.id.NAME);
            if (0 >= rawQuery.getCount()) {
                appCompatTextView.setVisibility(View.VISIBLE);
            } else {
                appCompatTextView.setVisibility(View.GONE);
            }
            ((ListView) this.findViewById(R.id.lvList_listnosearch)).setAdapter(new SimpleCursorAdapter(this, R.layout.rowmarketingmessagelist, rawQuery, strArr, new int[]{R.id.MMID, R.id.MMDESC}));
        } catch (final Exception e2) {
            Log.e(MarketingMessageListActivity.TAG, "PazarlamaMesajListesiniGetir: ", e2);
        }
    }
     public void onBackPressed() {
        super.onBackPressed();
         this.finish();
    }
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (16908332 == menuItem.getItemId()) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
