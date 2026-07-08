package com.proje.mobilesales.core.utils;

import android.os.Handler;
import android.os.Looper;
import android.print.PrintDocumentAdapter;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import kotlin.jvm.internal.Intrinsics;


public final class CreatePdfcreate2 extends WebViewClient {
    final CreatePdf this0;

    CreatePdfcreate2(CreatePdf createPdf) {
        this.this0 = createPdf;
    }
    public void onPageFinished(final WebView view, String str) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.evaluateJavascript("(function() {\n    var tables = document.querySelectorAll('table');\n    var pageWidth = 793; // A4 yatay sayfa geni\u015fli\u011fi\n\n    tables.forEach(function(table) {\n        var numCols = table.rows[0].cells.length;\n        var colWidths = [];\n        var totalWidth = 0;\n\n        for (var i = 0; i < numCols; i++) {\n            var cellWidth = table.rows[0].cells[i].offsetWidth;\n            colWidths.push(cellWidth);\n            totalWidth += cellWidth;\n        }\n\n        if (totalWidth <= pageWidth) {\n            return;\n        }\n\n        var currentPageWidth = 0;\n        var maxColsPerPage = [];\n        var tempPageCols = [];\n\n        for (var j = 0; j < numCols; j++) {\n            currentPageWidth += colWidths[j];\n            tempPageCols.push(j);\n            if (currentPageWidth > pageWidth) {\n                tempPageCols.pop();\n                maxColsPerPage.push(tempPageCols); \n                currentPageWidth = colWidths[j];\n                tempPageCols = [j];\n            }\n        }\n\n        if (tempPageCols.length > 0) {\n            maxColsPerPage.push(tempPageCols);\n        }\n\n        var pageTables = [];\n        for (var k = 0; k < maxColsPerPage.length; k++) {\n            var newTable = table.cloneNode(false);\n            var tbody = document.createElement('tbody');\n            newTable.appendChild(tbody);\n            var rows = table.rows;\n\n            for (var i = 0; i < rows.length; i++) {\n                var newRow = document.createElement('tr');\n                for (var j = 0; j < maxColsPerPage[k].length; j++) {\n                    var cell = rows[i].cells[maxColsPerPage[k][j]].cloneNode(true);\n                    newRow.appendChild(cell);\n                }\n                tbody.appendChild(newRow);\n            }\n            pageTables.push(newTable);\n        }\n\n        var pageContainer = document.createElement('div');\n        pageTables.forEach(function(table) {\n            var pageDiv = document.createElement('div');\n            pageDiv.classList.add('page');\n            pageDiv.style.pageBreakAfter = 'always';\n            pageDiv.appendChild(table);\n            pageContainer.appendChild(pageDiv);\n        });\n\n        table.parentNode.replaceChild(pageContainer, table);\n    });\n})();", null);
        Handler handler = new Handler(Looper.getMainLooper());
        final CreatePdf createPdf = this.this0;
        handler.postDelayed(new Runnable() {
            public void run() {
                CreatePdfcreate2.onPageFinishedlambda0(CreatePdf.this, view);
            }
        }, 500L);
    }
    public static void onPageFinishedlambda0(CreatePdf this0, WebView view) {
        WebView webView;
        String str;
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(view, "view");
        webView = this0.mWebView;
        if (webView != null) {
            webView.setVisibility(View.VISIBLE);
        }
        str = this0.mPdfName;
        PrintDocumentAdapter createPrintDocumentAdapter = view.createPrintDocumentAdapter(str);
        Intrinsics.checkNotNullExpressionValue(createPrintDocumentAdapter, "createPrintDocumentAdapter(...)");
        this0.savePdf(createPrintDocumentAdapter);
    }
}
