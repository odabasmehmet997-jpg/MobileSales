package com.example.privacy_policy_lib;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.privacy_policy_lib.core.model.*;
import com.example.privacy_policy_lib.core.service.AgreementService;
import com.example.privacy_policy_lib.core.service.AgreementServiceSingleton;
import com.google.firebase.messaging.Constants;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
 
public final class PrivacyPolicyViewModel extends ViewModel {
    private final AgreementService agreementService = AgreementServiceSingleton.INSTANCE.getInstance();
    private final MutableLiveData<GetAgreementContentResponse> _agreementResponse = new MutableLiveData<>();
    private final MutableLiveData<ApproveAgreementResponse> _approvalResult = new MutableLiveData<>();
    private final MutableLiveData<String> _error = new MutableLiveData<>();
    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public LiveData<GetAgreementContentResponse> getAgreementResponse() {
        return this._agreementResponse;
    }
    public LiveData<ApproveAgreementResponse> getApprovalResult() {
        return this._approvalResult;
    }
    public LiveData<String> getError() {
        return this._error;
    }
    public LiveData<Boolean> isLoading() {
        return this._isLoading;
    }
    public void getAgreementContent(int i2) {
        this._isLoading.postValue(Boolean.TRUE);
        this.agreementService.callGetAgreementContent(i2, new Function1<GetAgreementContentResponse, Unit>(this) {
            @Override
            public Unit invoke(Object p1) {
                return null;
            }

            final PrivacyPolicyViewModel this0 = null;
            public Unit invoke(GetAgreementContentResponse getAgreementContentResponse) {
                invoke(getAgreementContentResponse);
                return Unit.INSTANCE;
            }

        }, new Function1<Throwable, Unit>(this) {
            @Override
            public Unit invoke(Object p1) {
                return null;
            }

            final PrivacyPolicyViewModel this0 = null;

            public void invoke(Throwable th) {
                Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                PrivacyPolicyViewModel.accesshandleError(this.this0, th);
            }
        });
    }
    private static void accesshandleSuccess(PrivacyPolicyViewModel this0, GetAgreementContentResponse getAgreementContentResponse) {
    }
    private static void accesshandleError(PrivacyPolicyViewModel this0, Throwable th) {
    }
    public ApproveAgreementEnvelope createApproveAgreementEnvelope(ApproveAgreementRequest approveAgreementRequest) {
        Intrinsics.checkNotNullParameter(approveAgreementRequest, "approveAgreementRequest");
        return new ApproveAgreementEnvelope(new ApproveAgreementBody(new ApproveAgreementContent(approveAgreementRequest)));
    }
    public void approveAgreementContent(boolean z, ApproveAgreementEnvelope approveAgreementEnvelope) {
        Intrinsics.checkNotNullParameter(approveAgreementEnvelope, "request");
        this._isLoading.postValue(Boolean.TRUE);
        this.agreementService.callApproveAgreementContent(z, approveAgreementEnvelope,
                new Function1<ApproveAgreementResponse, Unit>(this) {
                    @Override
                    public Unit invoke(Object p1) {
                        return null;
                    }
            final PrivacyPolicyViewModel this0 = null;
            public Unit invoke(ApproveAgreementResponse approveAgreementResponse) {
                PrivacyPolicyViewModel.accesshandleApprovalSuccess(this.this0, approveAgreementResponse);
                return Unit.INSTANCE;
            }
        }, new Function1<Throwable, Unit>(this) {
            public Unit invoke(Object p1) {
                return null;
            }
            final PrivacyPolicyViewModel this0 = null;
            public void invoke(Throwable th) {
                Intrinsics.checkNotNullParameter(th, Constants.IPC_BUNDLE_KEY_SEND_ERROR);
                PrivacyPolicyViewModel.accesshandleError(this.this0, th);
            }
        });
    }
    private static void accesshandleApprovalSuccess(PrivacyPolicyViewModel this0, ApproveAgreementResponse approveAgreementResponse) {
        this0._approvalResult.postValue(approveAgreementResponse);
    }
    public Object getCurrentApprovedAgreementContentHashByToken(boolean r5, GetCurrentApprovedAgreementContentHashByTokenRequest r6, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> r7) {

        throw new UnsupportedOperationException("Method not decompiled: com.example.privacy_policy_lib.PrivacyPolicyViewModel.m128getCurrentApprovedAgreementContentHashByToken0E7RQCE(boolean, com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }
    public void handleSuccess(GetAgreementContentResponse getAgreementContentResponse) {
        AgreementContentResponse contentResponse;
        AgreementContentResult result;
        this._isLoading.postValue(Boolean.FALSE);
        if (getAgreementContentResponse != null) {
            GetAgreementContentResponseBody body = getAgreementContentResponse.getBody();
            String content = (body == null || (contentResponse = body.getContentResponse()) == null || (result = contentResponse.getResult()) == null) ? null : result.getContent();
            if (content == null || content.length() == 0) {
                this._error.postValue("Agreement content is empty.");
            } else {
                this._agreementResponse.postValue(getAgreementContentResponse);
            }
        }
    }
    public void handleApprovalSuccess(ApproveAgreementResponse approveAgreementResponse) {
        this._isLoading.postValue(Boolean.FALSE);
        this._approvalResult.postValue(approveAgreementResponse);
    }
    public void handleError(Throwable th) {
        this._isLoading.postValue(Boolean.FALSE);
        MutableLiveData<String> mutableLiveData = this._error;
        String message = th.getMessage();
        if (message == null) {
            message = "Unknown error occurred.";
        }
        mutableLiveData.postValue(message);
    }
}
