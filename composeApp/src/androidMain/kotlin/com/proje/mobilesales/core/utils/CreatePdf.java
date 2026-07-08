package com.proje.mobilesales.core.utils;

import android.content.Context;
import android.print.PdfPrint;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.io.File;
import kotlin.jvm.internal.Intrinsics;


public class CreatePdf {
    private final String ENCODING;
    private final String MimeType;
    private boolean doPrint;
    private String mBaseURL;
    private PdfCallbackListener mCallbacks;
    private String mContent;
    private final Context mContext;
    private PrintAttributes.MediaSize mMediaSize;
    private String mPdfFilePath;
    private String mPdfName;
    private WebView mWebView;
    public interface PdfCallbackListener {
        void onFailure(String str);

        void onSuccess(String str);
    }
    public CreatePdf(Context mContext) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        this.mContext = mContext;
        this.MimeType = "text/html";
        this.ENCODING = "utf-8";
        this.mBaseURL = "";
        this.mPdfName = "";
        this.mContent = "";
        this.mPdfFilePath = "";
    }
    public final CreatePdf setPdfName(String pdfName) {
        Intrinsics.checkNotNullParameter(pdfName, "pdfName");
        this.mPdfName = pdfName + ".pdf";
        return this;
    }
    public final CreatePdf setContentBaseUrl(String str) {
        this.mBaseURL = str;
        return this;
    }
    public final CreatePdf setContent(String content) {
        Intrinsics.checkNotNullParameter(content, "content");
        this.mContent = content;
        return this;
    }
    public final CreatePdf openPrintDialog(boolean z) {
        this.doPrint = z;
        return this;
    }
    public final CreatePdf setFilePath(String pdfFilePath) {
        Intrinsics.checkNotNullParameter(pdfFilePath, "pdfFilePath");
        this.mPdfFilePath = pdfFilePath;
        return this;
    }
    public final CreatePdf setPageSize(PrintAttributes.MediaSize size) {
        Intrinsics.checkNotNullParameter(size, "size");
        this.mMediaSize = size;
        return this;
    }
    public final CreatePdf setCallbackListener(PdfCallbackListener callbacks) {
        Intrinsics.checkNotNullParameter(callbacks, "callbacks");
        this.mCallbacks = callbacks;
        return this;
    }
    public final void create() {
        if (this.mPdfName.length() == 0) {
            PdfCallbackListener pdfCallbackListener = this.mCallbacks;
            if (pdfCallbackListener != null) {
                pdfCallbackListener.onFailure("Pdf name must not be empty.");
                return;
            }
            return;
        }
        if (this.mMediaSize == null) {
            PdfCallbackListener pdfCallbackListener2 = this.mCallbacks;
            if (pdfCallbackListener2 != null) {
                pdfCallbackListener2.onFailure("Page Size must not be empty.");
                return;
            }
            return;
        }
        if (this.mContent.length() == 0) {
            PdfCallbackListener pdfCallbackListener3 = this.mCallbacks;
            if (pdfCallbackListener3 != null) {
                pdfCallbackListener3.onFailure("Empty or null content.");
                return;
            }
            return;
        }
        WebView webView = new WebView(this.mContext);
        this.mWebView = webView;
        WebSettings settings = webView.getSettings();
        if (settings != null) {
            settings.setJavaScriptEnabled(true);
        }
        WebView webView2 = this.mWebView;
        if (webView2 != null) {
            webView2.clearCache(true);
        }
        WebView webView3 = this.mWebView;
        if (webView3 != null) {
            webView3.setVisibility(View.INVISIBLE);
            webView3.getSettings().setJavaScriptEnabled(true);
            webView3.clearCache(true);
        }
        WebView webView4 = this.mWebView;
        if (webView4 != null) {
            webView4.setWebViewClient(new CreatePdfcreate2(this));
        }
        WebView webView5 = this.mWebView;
        if (webView5 != null) {
            webView5.loadDataWithBaseURL(this.mBaseURL, this.mContent, this.MimeType, this.ENCODING, null);
        }
    }
    public final void savePdf(final PrintDocumentAdapter printDocumentAdapter) {
        String str;
        if (this.mPdfFilePath.length() == 0) {
            str = this.mContext.getCacheDir().getAbsolutePath();
            Intrinsics.checkNotNull(str);
        } else {
            str = this.mPdfFilePath;
        }
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        PrintAttributes.Builder builder = new PrintAttributes.Builder();
        PrintAttributes.MediaSize mediaSize = this.mMediaSize;
        Intrinsics.checkNotNull(mediaSize);
        PrintAttributes build = builder.setMediaSize(mediaSize).setResolution(new PrintAttributes.Resolution("pdf", "pdf", 600, 600)).setMinMargins(PrintAttributes.Margins.NO_MARGINS).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        new PdfPrint(build).print(printDocumentAdapter, file, this.mPdfName, new PdfPrint.CallbackPrint() {
            public void success(String path) {
                CreatePdf.PdfCallbackListener pdfCallbackListener;
                boolean z;
                Context context;
                String str2;
                Intrinsics.checkNotNullParameter(path, "path");
                pdfCallbackListener = CreatePdf.this.mCallbacks;
                if (pdfCallbackListener != null) {
                    pdfCallbackListener.onSuccess(path);
                }
                z = CreatePdf.this.doPrint;
                if (z) {
                    context = CreatePdf.this.mContext;
                    Object systemService = context.getSystemService(Context.PRINT_SERVICE);
                    Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.print.PrintManager");
                    str2 = CreatePdf.this.mPdfName;
                    ((PrintManager) systemService).print(str2, printDocumentAdapter, new PrintAttributes.Builder().build());
                }
                CreatePdf.this.mWebView = null;
            }
            public void onFailure(String errorMsg) {
                CreatePdf.PdfCallbackListener pdfCallbackListener;
                Intrinsics.checkNotNullParameter(errorMsg, "errorMsg");
                pdfCallbackListener = CreatePdf.this.mCallbacks;
                if (pdfCallbackListener != null) {
                    pdfCallbackListener.onFailure(errorMsg);
                }
                CreatePdf.this.mWebView = null;
            }
        });
    }
}
