package cardtek.masterpass.attributes;

import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import cardtek.masterpass.management.ServiceResponse;
import cardtek.masterpass.response.ServiceError;
import cardtek.masterpass.results.ValidateTransaction3DResult;
import cardtek.masterpass.util.MasterPassInfo;
import cardtek.masterpass.util.a;

/* loaded from: classes.dex */
final class c extends WebViewClient {
    final /* synthetic */ MasterPassWebView n;

    
    public c(MasterPassWebView masterPassWebView) {
        this.n = masterPassWebView;
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        PageLoadListener pageLoadListener = this.n.pageLoadListener;
        if (pageLoadListener != null) {
            pageLoadListener.onPageFinishLoad();
        }
        MasterPassWebView masterPassWebView = this.n;
        if (masterPassWebView.startedJobs != 0) {
            if (masterPassWebView.response.getUrl3DError() != null && str.startsWith(this.n.response.getUrl3DError())) {
                Uri parse = Uri.parse(str);
                String queryParameter = parse.getQueryParameter("responseCode");
                ServiceError serviceError = new ServiceError();
                if (!queryParameter.isEmpty()) {
                    serviceError.setResponseCode(queryParameter);
                    serviceError.setMdStatus(parse.getQueryParameter("mdStatus"));
                    serviceError.setMdErrorMsg(parse.getQueryParameter("mdErrorMsg"));
                } else {
                    serviceError.setResponseCode(a.E010.name);
                }
                serviceError.setResponseDesc(a.E010.value);
                this.n.callback.onServiceError(serviceError);
            } else if (this.n.response.getUrl3DSuccess() != null && str.startsWith(this.n.response.getUrl3DSuccess())) {
                Uri parse2 = Uri.parse(str);
                String queryParameter2 = parse2.getQueryParameter("responseCode");
                String queryParameter3 = parse2.getQueryParameter("token");
                String queryParameter4 = parse2.getQueryParameter("message");
                if (queryParameter2.equalsIgnoreCase("5001") || queryParameter2.equalsIgnoreCase("5002") || queryParameter2.equalsIgnoreCase("5007") || queryParameter2.equalsIgnoreCase("5008")) {
                    ServiceResponse serviceResponse = new ServiceResponse();
                    serviceResponse.setToken(queryParameter3);
                    serviceResponse.setResponseCode(queryParameter2);
                    serviceResponse.setResponseDesc(queryParameter4);
                    this.n.callback.onServiceResponse(serviceResponse);
                } else {
                    ValidateTransaction3DResult validateTransaction3DResult = new ValidateTransaction3DResult();
                    validateTransaction3DResult.setToken(queryParameter3);
                    this.n.callback.onSuccess(validateTransaction3DResult);
                }
            } else {
                return;
            }
            MasterPassWebView masterPassWebView2 = this.n;
            masterPassWebView2.startedJobs--;
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        String str2 = MasterPassInfo.TAG;
        PageLoadListener pageLoadListener = this.n.pageLoadListener;
        if (pageLoadListener != null) {
            pageLoadListener.onPageStartLoad();
        }
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        return false;
    }
}
