package com.proje.mobilesales.features.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.enums.ColType;
import com.proje.mobilesales.core.sql.ISqlHelper;
import com.proje.mobilesales.core.utils.IntentExtraName;
import com.proje.mobilesales.features.adapter.MarketingMessageAdapter;
import java.util.ArrayList;
import java.util.List;
import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

public class MarketingMessageViewActivity extends BaseErpActivity {
    private List<String> marketMessage;
    private MarketingMessageAdapter marketingMessageAdapter;
    int messageRef;
    private RecyclerView rvMarketingMessages;
     public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
         this.setContentView(R.layout.activity_view_market_messages);
         this.setToolbar();
        rvMarketingMessages = this.findViewById(R.id.rv_marketing_messages);
        marketingMessageAdapter = new MarketingMessageAdapter(this.getApplicationContext());
        messageRef = this.getIntent().getIntExtra(IntentExtraName.EXTRAS_MESSAGE_REF, 0);
        marketMessage = new ArrayList();
         this.PazarlamaMesajBilgileriniGetir();
    }

    private void PazarlamaMesajBilgileriniGetir() {
        if (0 < this.messageRef) {
            final Cursor rawQuery = baseErp.getLogoSqlHelper().getReadableDatabase().rawQuery("SELECT * FROM MARKETINGMESSAGE WHERE LOGICALREF=" + messageRef, null);
            if (rawQuery.moveToPosition(0)) {
                marketMessage.clear();
                final List<String> list = marketMessage;
                final ISqlHelper logoSqlHelper = baseErp.getLogoSqlHelper();
                final ColType colType = ColType.metin;
                list.add(logoSqlHelper.dbVal(rawQuery, "BEGDATE", colType).toString());
                marketMessage.add(baseErp.getLogoSqlHelper().dbVal(rawQuery, "ENDDATE", colType).toString());
                marketMessage.add(baseErp.getLogoSqlHelper().dbVal(rawQuery, "DEFINITION_", colType).toString());
                marketMessage.add(baseErp.getLogoSqlHelper().dbVal(rawQuery, "NOTE", colType).toString());
                rvMarketingMessages.setLayoutManager(new StickyHeaderLayoutManager());
                rvMarketingMessages.setAdapter(marketingMessageAdapter);
                marketingMessageAdapter.setMarketMessage(marketMessage);
            }
            rawQuery.close();
        }
    }
     public void onDestroy() {
        super.onDestroy();
    }
     public void onBackPressed() {
        super.onBackPressed();
         this.finish();
    }

    public static class SmpWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(final WebView webView, final String str) {
            webView.loadUrl(str);
            return true;
        }
    }
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (16908332 == menuItem.getItemId()) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
