package com.example.privacy_policy_lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.webkit.WebViewAssetLoader;
import com.example.privacy_policy_lib.core.AgreementTypes;
import com.example.privacy_policy_lib.core.model.AgreementContentResponse;
import com.example.privacy_policy_lib.core.model.AgreementContentResult;
import com.example.privacy_policy_lib.core.model.ApproveAgreementContentResponse;
import com.example.privacy_policy_lib.core.model.ApproveAgreementContentResult;
import com.example.privacy_policy_lib.core.model.ApproveAgreementRequest;
import com.example.privacy_policy_lib.core.model.ApproveAgreementResponse;
import com.example.privacy_policy_lib.core.model.ApproveAgreementResponseBody;
import com.example.privacy_policy_lib.core.model.GetAgreementContentResponse;
import com.example.privacy_policy_lib.core.model.GetAgreementContentResponseBody;
import com.example.privacy_policy_lib.core.model.PrivacyPolicyState;
import com.example.privacy_policy_lib.core.utils.ContextUtils;
import com.example.privacy_policy_lib.core.utils.IntentExtraName;
import com.example.privacy_policy_lib.core.utils.PreferencesHelper;
import com.example.privacy_policy_lib.databinding.FragmentPrivacyPolicyDialogBinding;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

public final class PrivacyPolicyDialogFragment extends Fragment {
    public static final Companion Companion = new Companion(null);
    private FragmentPrivacyPolicyDialogBinding _binding;
    private String beginDate;
    private int checkboxPosition;
    private String contentHash;
    private String mPrivacyPolicyFile;
    private final Lazy<PrivacyPolicyViewModel> viewModeldelegate;

    public static   PrivacyPolicyDialogFragment newInstance(int r1) {
        return Companion.newInstance(r1);
    }

    public PrivacyPolicyDialogFragment() {
        final Function0<Fragment> function0 = new Function0<Fragment>() {

            public Fragment invoke() {
                return ( Fragment ) this;
            }
        };
        final Lazy<ViewModelStoreOwner> lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0<ViewModelStoreOwner>() {
            public ViewModelStoreOwner invoke() {
                return function0.invoke();
            }
        });
        final Function0<PrivacyPolicyViewModel> function02 = null;
        this.viewModeldelegate = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(PrivacyPolicyViewModel.class), new Function0<ViewModelStore>() {
            public ViewModelStore invoke() {
                return FragmentViewModelLazyKt.viewModelslambda(lazy).getViewModelStore();
            }
        }, new Function0<CreationExtras>() {
            public CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0<PrivacyPolicyViewModel> function03 = function02;
                if (function03 != null && (creationExtras = (CreationExtras) function03.invoke()) != null) {
                    return creationExtras;
                }
                ViewModelStoreOwner viewModelStoreOwnerM576viewModelslambda1 = FragmentViewModelLazyKt.viewModelslambda1(lazy);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM576viewModelslambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM576viewModelslambda1 : null;
                return hasDefaultViewModelProviderFactory != null ? hasDefaultViewModelProviderFactory.getDefaultViewModelCreationExtras() : CreationExtras.Empty.INSTANCE;
            }
        }, new Function0<ViewModelProvider.Factory>() {
            public   ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory;
                ViewModelStoreOwner viewModelStoreOwnerM576viewModelslambda1 = FragmentViewModelLazyKt.viewModelslambda1(lazy);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM576viewModelslambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM576viewModelslambda1 : null;
                return (hasDefaultViewModelProviderFactory == null ||
                        (defaultViewModelProviderFactory = hasDefaultViewModelProviderFactory.getDefaultViewModelProviderFactory()) == null)
                        ? this.getDefaultViewModelProviderFactory() : defaultViewModelProviderFactory;
            }
        });
        this.contentHash = "";
        this.beginDate = "";
    }
    public FragmentPrivacyPolicyDialogBinding getBinding() {
        FragmentPrivacyPolicyDialogBinding fragmentPrivacyPolicyDialogBinding = this._binding;
        Intrinsics.checkNotNull(fragmentPrivacyPolicyDialogBinding);
        return fragmentPrivacyPolicyDialogBinding;
    }
    private PrivacyPolicyViewModel getViewModel() {
        return this.viewModeldelegate.getValue();
    }
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public PrivacyPolicyDialogFragment newInstance(int r3) {
            PrivacyPolicyDialogFragment privacyPolicyDialogFragment = new PrivacyPolicyDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(IntentExtraName.ARG_POSITION, r3);
            privacyPolicyDialogFragment.setArguments(bundle);
            return privacyPolicyDialogFragment;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.checkboxPosition = arguments.getInt(IntentExtraName.ARG_POSITION);
        }
        Context context = getContext();
        if (context != null) {
            ContextUtils.Companion.setmContext(context);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this._binding = FragmentPrivacyPolicyDialogBinding.inflate(inflater, viewGroup, false);
        ConstraintLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        observeViewModel();
        loadPrivacyPolicy();
        OnBackPressedDispatcher onBackPressedDispatcher = requireActivity().getOnBackPressedDispatcher();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        onBackPressedDispatcher.addCallback(viewLifecycleOwner, new OnBackPressedCallback() {
            public void handleOnBackPressed() {
            }
        });
    }

    public void onStart() {
        super.onStart();
        getBinding().privacyPolicyBtnRead.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PrivacyPolicyDialogFragment.onStartlambda3(PrivacyPolicyDialogFragment.this, view);
            }
        });
    }
    public static void onStartlambda3(PrivacyPolicyDialogFragment this0, View view) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Context context = ContextUtils.Companion.getmContext();
        if (context != null) {
            PreferencesHelper.init(context);
        }
        PreferencesHelper.INSTANCE.markPrivacyPolicyAsRead();
        this0.sendApproveRequest();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this._binding = null;
    }

    private void observeViewModel() {
        getViewModel().getAgreementResponse().observe(getViewLifecycleOwner(),
                new PrivacyPolicyDialogFragmentsamandroidx_lifecycle_Observer0(new Function1<GetAgreementContentResponse, Unit>() {
                    public Unit invoke(Object p1) {
                        return null;
                    }

            public   Unit invoke(GetAgreementContentResponse getAgreementContentResponse) {
                invoke2(getAgreementContentResponse);
                return Unit.INSTANCE;
            }
            public   void invoke2(GetAgreementContentResponse getAgreementContentResponse) {
                String contentHash;
                String beginDate;
                AgreementContentResponse contentResponse;
                AgreementContentResult result;
                String endDate;
                AgreementContentResponse contentResponse2;
                AgreementContentResult result2;
                AgreementContentResponse contentResponse3;
                AgreementContentResult result3;
                GetAgreementContentResponseBody body;
                AgreementContentResponse contentResponse4;
                AgreementContentResult result4;
                String content = (getAgreementContentResponse == null || (body = getAgreementContentResponse.getBody()) == null || (contentResponse4 = body.getContentResponse()) == null || (result4 = contentResponse4.getResult()) == null) ? null : result4.getContent();
                if (content == null || content.length() == 0) {
                    PrivacyPolicyDialogFragment.this.switchToWebViewToShowLocalFile();
                    return;
                }
                AgreementTypes agreementTypes = PrivacyPolicyState.getParams().getAgreementTypes().get(PrivacyPolicyDialogFragment.this.checkboxPosition);
                PrivacyPolicyDialogFragment privacyPolicyDialogFragment = PrivacyPolicyDialogFragment.this;
                GetAgreementContentResponseBody body2 = getAgreementContentResponse.getBody();
                String str = "";
                if (body2 == null || (contentResponse3 = body2.getContentResponse()) == null || (result3 = contentResponse3.getResult()) == null || (contentHash = result3.getContentHash()) == null) {
                    contentHash = "";
                }
                privacyPolicyDialogFragment.contentHash = contentHash;
                PrivacyPolicyDialogFragment privacyPolicyDialogFragment2 = PrivacyPolicyDialogFragment.this;
                GetAgreementContentResponseBody body3 = getAgreementContentResponse.getBody();
                if (body3 == null || (contentResponse2 = body3.getContentResponse()) == null || (result2 = contentResponse2.getResult()) == null || (beginDate = result2.getBeginDate()) == null) {
                    beginDate = "";
                }
                privacyPolicyDialogFragment2.beginDate = beginDate;
                PrivacyPolicyDialogFragment.this.updateContentHashList(agreementTypes);
                GetAgreementContentResponseBody body4 = getAgreementContentResponse.getBody();
                if (body4 != null && (contentResponse = body4.getContentResponse()) != null && (result = contentResponse.getResult()) != null && (endDate = result.getEndDate()) != null) {
                    str = endDate;
                }
                PrivacyPolicyDialogFragment.this.updateEndDateList(agreementTypes, str);
                try {
                    PrivacyPolicyDialogFragment.this.displayPdf(content);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    PrivacyPolicyDialogFragment.this.switchToWebViewToShowLocalFile();
                }
            }
        }));
        getViewModel().getError().observe(getViewLifecycleOwner(), new PrivacyPolicyDialogFragmentsamandroidx_lifecycle_Observer0(new Function1<String, Unit>() { // from class: com.example.privacy_policy_lib.PrivacyPolicyDialogFragment.observeViewModel.2


            public Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }
            public void invoke2(String str) {
                Toast.makeText(PrivacyPolicyDialogFragment.this.requireContext(), str, Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putInt(PrivacyPolicyState.POSITION, PrivacyPolicyDialogFragment.this.checkboxPosition);
                bundle.putBoolean(PrivacyPolicyState.IS_APPROVED, false);
                PrivacyPolicyDialogFragment.this.getParentFragmentManager().setFragmentResult(PrivacyPolicyState.PRIVACY_POLICY, bundle);
                PrivacyPolicyDialogFragment.this.getParentFragmentManager().popBackStack();
            }
        }));
        getViewModel().getApprovalResult().observe(getViewLifecycleOwner(), new PrivacyPolicyDialogFragmentsamandroidx_lifecycle_Observer0(new Function1<ApproveAgreementResponse, Unit>() { // from class: com.example.privacy_policy_lib.PrivacyPolicyDialogFragment.observeViewModel.3

            public /* synthetic */ Unit invoke(ApproveAgreementResponse approveAgreementResponse) {
                invoke2(approveAgreementResponse);
                return Unit.INSTANCE;
            }

            public void invoke2(ApproveAgreementResponse approveAgreementResponse) {
                String agreementToken;
                ApproveAgreementContentResponse approveAgreementContentResponse;
                ApproveAgreementContentResult approveAgreementContentResult;
                if (approveAgreementResponse != null) {
                    AgreementTypes agreementTypes = PrivacyPolicyState.getParams().getAgreementTypes().get(PrivacyPolicyDialogFragment.this.checkboxPosition);
                    ApproveAgreementResponseBody body = approveAgreementResponse.getBody();
                    if (body == null || (approveAgreementContentResponse = body.getApproveAgreementContentResponse()) == null || (approveAgreementContentResult = approveAgreementContentResponse.getApproveAgreementContentResult()) == null || (agreementToken = approveAgreementContentResult.getAgreementToken()) == null) {
                        agreementToken = "";
                    }
                    Iterator<Pair<AgreementTypes, String>> it = PrivacyPolicyState.getParams().getAgreementTokenList().iterator();
                    int r2 = 0;
                    while (true) {
                        if (!it.hasNext()) {
                            r2 = -1;
                            break;
                        } else if (it.next().getFirst() == agreementTypes) {
                            break;
                        } else {
                            r2++;
                        }
                    }
                    if (r2 != -1) {
                        PrivacyPolicyState.getParams().getAgreementTokenList().set(r2, new Pair<>(agreementTypes, agreementToken));
                    } else {
                        PrivacyPolicyState.getParams().getAgreementTokenList().add(new Pair<>(agreementTypes, agreementToken));
                    }
                }
                Bundle bundle = new Bundle();
                bundle.putInt(PrivacyPolicyState.POSITION, PrivacyPolicyDialogFragment.this.checkboxPosition);
                bundle.putBoolean(PrivacyPolicyState.IS_APPROVED, true);
                PrivacyPolicyDialogFragment.this.getParentFragmentManager().setFragmentResult(PrivacyPolicyState.PRIVACY_POLICY, bundle);
                PrivacyPolicyDialogFragment.this.getParentFragmentManager().popBackStack();
            }
        }));
    }

    public void updateContentHashList(AgreementTypes agreementTypes) {
        Iterator<Pair<AgreementTypes, String>> it = PrivacyPolicyState.getParams().getContentHashList().iterator();
        int r1 = 0;
        while (true) {
            if (!it.hasNext()) {
                r1 = -1;
                break;
            } else if (it.next().getFirst() == agreementTypes) {
                break;
            } else {
                r1++;
            }
        }
        if (r1 != -1) {
            PrivacyPolicyState.getParams().getContentHashList().set(r1, new Pair<>(agreementTypes, this.contentHash));
        } else {
            PrivacyPolicyState.getParams().getContentHashList().add(new Pair<>(agreementTypes, this.contentHash));
        }
    }

    /*  INFO: Access modifiers changed from: private */
    public void updateEndDateList(AgreementTypes agreementTypes, String str) {
        Iterator<Pair<AgreementTypes, String>> it = PrivacyPolicyState.getParams().getEndDateList().iterator();
        int r0 = 0;
        while (true) {
            if (!it.hasNext()) {
                r0 = -1;
                break;
            } else if (it.next().getFirst() == agreementTypes) {
                break;
            } else {
                r0++;
            }
        }
        if (r0 != -1) {
            PrivacyPolicyState.getParams().getEndDateList().set(r0, new Pair<>(agreementTypes, str));
        } else {
            PrivacyPolicyState.getParams().getEndDateList().add(new Pair<>(agreementTypes, str));
        }
    }

    private void loadPrivacyPolicy() {
        getBinding().llProgressBar.getRoot().setVisibility(View.VISIBLE);
        getViewModel().getAgreementContent(this.checkboxPosition);
    }

    private void sendApproveRequest() {
        getViewModel().approveAgreementContent(PrivacyPolicyState.getParams().isProduction(), getViewModel().createApproveAgreementEnvelope(new ApproveAgreementRequest(PrivacyPolicyState.getParams().getServer() + "|" + PrivacyPolicyState.getParams().getErpType() + "|" + PrivacyPolicyState.getParams().getUserName() + "|" + PrivacyPolicyState.getParams().getPassword(), PrivacyPolicyState.getParams().getServer(), this.contentHash, "", PrivacyPolicyState.getParams().getItemCode(), PrivacyPolicyState.getParams().getAgreementTypes().get(this.checkboxPosition).toString(), PrivacyPolicyState.getParams().getContractor(), PrivacyPolicyState.getParams().getLanguage(), this.beginDate, "")));
    }
    public void displayPdf(String str) throws IOException {
        ParcelFileDescriptor parcelFileDescriptorOpen = ParcelFileDescriptor.open(base64ToPdf(str, "temp.pdf"), 268435456);
        PdfRenderer pdfRenderer = new PdfRenderer(parcelFileDescriptorOpen);
        getBinding().privacyPolicyScrollView.setVisibility(View.VISIBLE);
        getBinding().privacyPolicyWebView.setVisibility(View.GONE);
        int pageCount = pdfRenderer.getPageCount();
        for (int r2 = 0; r2 < pageCount; r2++) {
            PdfRenderer.Page pageOpenPage = pdfRenderer.openPage(r2);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(pageOpenPage.getWidth(), pageOpenPage.getHeight(), Bitmap.Config.ARGB_8888);
            Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(...)");
            pageOpenPage.render(bitmapCreateBitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
            pageOpenPage.close();
            ImageView imageView = new ImageView(requireContext());
            imageView.setImageBitmap(bitmapCreateBitmap);
            imageView.setAdjustViewBounds(true);
            getBinding().privacyPolicyPdfContainer.addView(imageView);
        }
        pdfRenderer.close();
        parcelFileDescriptorOpen.close();
        getBinding().llProgressBar.getRoot().setVisibility(View.GONE);
    }

    private File base64ToPdf(String str, String str2) throws IOException {
        byte[] bArrDecode = Base64.decode(str, 0);
        File file = new File(requireContext().getCacheDir(), str2);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bArrDecode);
        Unit unit = Unit.INSTANCE;
        CloseableKt CloseableKt = null;
        kotlin.io.CloseableKt.closeFinally(fileOutputStream, null);
        return file;
    }

    public void switchToWebViewToShowLocalFile() {
        final WebViewAssetLoader webViewAssetLoaderBuild;
        Context context = ContextUtils.Companion.getmContext();
        if (context != null) {
            webViewAssetLoaderBuild = new WebViewAssetLoader.Builder().addPathHandler("/assets/", new WebViewAssetLoader.AssetsPathHandler(context)).build();
        } else {
            webViewAssetLoaderBuild = null;
        }
        getBinding().privacyPolicyScrollView.setVisibility(View.GONE);
        getBinding().privacyPolicyWebView.setVisibility(View.VISIBLE);
        getBinding().privacyPolicyWebView.setWebViewClient(new WebViewClient() {
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(request, "request");
                WebViewAssetLoader webViewAssetLoader = webViewAssetLoaderBuild;
                if (webViewAssetLoader != null) {
                    return webViewAssetLoader.shouldInterceptRequest(request.getUrl());
                }
                return null;
            }

            public void onPageFinished(WebView webView, String str) {
                this.getBinding().llProgressBar.getRoot().setVisibility(8);
                this.delayReadButton();
                super.onPageFinished(webView, str);
            }

            private FragmentPrivacyPolicyDialogBinding getBinding() {
                return null;
            }

            private void delayReadButton() {
            }
        });
        String str = this.mPrivacyPolicyFile;
        if (str != null) {
            getBinding().privacyPolicyWebView.loadUrl(str);
        }
    }
    public void delayReadButton() {
        if (this._binding != null) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    PrivacyPolicyDialogFragment.delayReadButtonlambda10(this0);
                }
            }, 1000L);
        }
    }
    public static void delayReadButtonlambda10(PrivacyPolicyDialogFragment this0) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        this0.getBinding().privacyPolicyBtnRead.setVisibility(View.VISIBLE);
    }
}
