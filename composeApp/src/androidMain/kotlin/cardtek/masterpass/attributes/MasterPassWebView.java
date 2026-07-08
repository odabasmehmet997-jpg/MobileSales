package cardtek.masterpass.attributes;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;
import cardtek.masterpass.interfaces.Transaction3DListener;
import cardtek.masterpass.management.ServiceResponse;
import cardtek.masterpass.response.InternalError;
import cardtek.masterpass.util.MasterPassInfo;
import cardtek.masterpass.util.a;

public class MasterPassWebView extends WebView {
    Transaction3DListener callback;
    PageLoadListener pageLoadListener;
    ServiceResponse response;
    int startedJobs = 0;
    public MasterPassWebView(Context context) {
        super(context);
        initialize();
    }
    public MasterPassWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize();
    }
    public MasterPassWebView(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        initialize();
    }
    private void initialize() {
        setWebViewClient(new c(this));
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setGeolocationEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        setWebChromeClient(new d(this));
    }
    public void loadUrl(ServiceResponse serviceResponse, Transaction3DListener transaction3DListener) {
        if (serviceResponse.getUrl3D().isEmpty()) {
            InternalError internalError = new InternalError();
            a aVar = a.E009;
            internalError.setErrorCode(aVar.name);
            internalError.setErrorDesc(aVar.value);
            transaction3DListener.onInternalError(internalError);
            return;
        }
        this.callback = transaction3DListener;
        this.response = serviceResponse;
        this.startedJobs++;
        if (MasterPassInfo.getResultUrl3D() == null || MasterPassInfo.getResultUrl3D().isEmpty()) {
            loadUrl(serviceResponse.getUrl3D());
            return;
        }
        loadUrl(serviceResponse.getUrl3D() + "&mobileReturnUrl=" + MasterPassInfo.getResultUrl3D());
    }
    public void setPageLoadCallback(PageLoadListener pageLoadListener) {
        this.pageLoadListener = pageLoadListener;
    }
}
