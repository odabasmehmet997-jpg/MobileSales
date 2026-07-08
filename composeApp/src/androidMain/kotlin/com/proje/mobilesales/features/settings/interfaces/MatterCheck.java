package com.proje.mobilesales.features.settings.interfaces;

import android.util.Log;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import java.lang.ref.WeakReference;

import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import kotlin.jvm.internal.Intrinsics;
public interface MatterCheck {
    void checkMatter();

    void onMatterCheck(String str);

    void onMatterError(String str);
    final class MatterCheckListener implements ResponseListener<String> {
        private final WeakReference<MatterCheck> mMatterCheckWeakReference;

        public MatterCheckListener(MatterCheck matterCheck) {
            this.mMatterCheckWeakReference = new WeakReference<>(matterCheck);
        }

        public void onResponse(PrintSlipModel str) {
            if (null != mMatterCheckWeakReference.get()) {
                MatterCheck matterCheck = this.mMatterCheckWeakReference.get();
                Intrinsics.checkNotNull(matterCheck);
                matterCheck.onMatterCheck(str);
            }
        }

        @Override
        public void onFailure(Throwable throwable) {

        }

        public void onError(String errorMessage) {
            Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
            if (null != mMatterCheckWeakReference.get()) {
                Log.d("MatterCheck", "onError: " + errorMessage);
                MatterCheck matterCheck = this.mMatterCheckWeakReference.get();
                Intrinsics.checkNotNull(matterCheck);
                matterCheck.onMatterCheck(errorMessage);
            }
        }
    }
}
