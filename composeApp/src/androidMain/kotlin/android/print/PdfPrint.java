package android.print;

import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.File;
import java.io.IOException;
  
public class PdfPrint {
    private static final String TAG = "PdfPrint";
    private final PrintAttributes printAttributes;
    public interface CallbackPrint {
        void onFailure(String str);

        void success(String str);
    }
    public PdfPrint(PrintAttributes printAttributes) {
        this.printAttributes = printAttributes;
    }
    public void print(final PrintDocumentAdapter printDocumentAdapter, final File file, final String str, final CallbackPrint callbackPrint) {
        printDocumentAdapter.onLayout(null, this.printAttributes, null, new PrintDocumentAdapter.LayoutResultCallback() {  
            public void onLayoutFinished(PrintDocumentInfo printDocumentInfo, boolean z) {
                try {
                    printDocumentAdapter.onWrite(new PageRange[]{PageRange.ALL_PAGES}, PdfPrint.this.getOutputFile(file, str), new CancellationSignal(), new PrintDocumentAdapter.WriteResultCallback() {

                        public void onWriteFinished(PageRange[] pageRangeArr) {
                            super.onWriteFinished(pageRangeArr);
                            if (pageRangeArr.length > 0) {
                                callbackPrint.success(new File(file, str).getAbsolutePath());
                                return;
                            }
                            callbackPrint.onFailure("Pages length not found");
                        }
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }, null);
    } 
    public ParcelFileDescriptor getOutputFile(File file, String str) throws IOException {
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, str);
        try {
            file2.createNewFile();
            return ParcelFileDescriptor.open(file2, 805306368);
        } catch (Exception e2) {
            Log.e(TAG, "Failed to open ParcelFileDescriptor", e2);
            return null;
        }
    }
}
