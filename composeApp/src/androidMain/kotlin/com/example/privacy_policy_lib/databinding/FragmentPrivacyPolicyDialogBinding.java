package com.example.privacy_policy_lib.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.privacy_policy_lib.R;
 
public final class FragmentPrivacyPolicyDialogBinding implements ViewBinding {
    
    public final LoadingItemBinding llProgressBar;
    
    public final AppCompatButton privacyPolicyBtnRead;
    
    public final LinearLayout privacyPolicyPdfContainer;
    
    public final ScrollView privacyPolicyScrollView;
    
    public final WebView privacyPolicyWebView;
    
    private final ConstraintLayout rootView;

    private FragmentPrivacyPolicyDialogBinding( ConstraintLayout constraintLayout,  LoadingItemBinding loadingItemBinding,  AppCompatButton appCompatButton,  LinearLayout linearLayout,  ScrollView scrollView,  WebView webView) {
        this.rootView = constraintLayout;
        this.llProgressBar = loadingItemBinding;
        this.privacyPolicyBtnRead = appCompatButton;
        this.privacyPolicyPdfContainer = linearLayout;
        this.privacyPolicyScrollView = scrollView;
        this.privacyPolicyWebView = webView;
    }
    
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    
    public static FragmentPrivacyPolicyDialogBinding inflate( LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    
    public static FragmentPrivacyPolicyDialogBinding inflate( LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_privacy_policy_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
    public static FragmentPrivacyPolicyDialogBinding bind( View view) {
        int i2 = R.id.llProgressBar;
        View findChildViewById = ViewBindings.findChildViewById(view, i2);
        if (findChildViewById != null) {
            LoadingItemBinding bind = LoadingItemBinding.bind(findChildViewById);
            i2 = R.id.privacy_policy_btn_read;
            AppCompatButton appCompatButton = ViewBindings.findChildViewById(view, i2);
            if (appCompatButton != null) {
                i2 = R.id.privacy_policy_pdf_container;
                LinearLayout linearLayout = ViewBindings.findChildViewById(view, i2);
                if (linearLayout != null) {
                    i2 = R.id.privacy_policy_scroll_view;
                    ScrollView scrollView = ViewBindings.findChildViewById(view, i2);
                    if (scrollView != null) {
                        i2 = R.id.privacy_policy_web_view;
                        WebView webView = ViewBindings.findChildViewById(view, i2);
                        if (webView != null) {
                            return new FragmentPrivacyPolicyDialogBinding((ConstraintLayout) view, bind, appCompatButton, linearLayout, scrollView, webView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }
}
