package com.proje.mobilesales.core.netsis;

import com.google.firebase.messaging.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NetsisTokenManager {

    @SerializedName("access_token")
    @Expose
    private String accessToken;

    @SerializedName("as:client_id")
    @Expose
    private String asClientId;

    @SerializedName("branchcode")
    @Expose
    private String branchcode;

    @SerializedName("dbname")
    @Expose
    private String dbname;

    @SerializedName(Constants.IPC_BUNDLE_KEY_SEND_ERROR)
    @Expose
    private String error;

    @SerializedName("error_description")
    @Expose
    private String errorDescription;

    @SerializedName(".expires")
    @Expose
    private String expires;

    @SerializedName("expires_in")
    @Expose
    private long expiresIn;

    @SerializedName(".issued")
    @Expose
    private String issued;

    @SerializedName("jlogin")
    @Expose
    private String jlogin;

    @SerializedName("refresh_token")
    @Expose
    private String refreshToken;

    @SerializedName("token_type")
    @Expose
    private String tokenType;

    @SerializedName("username")
    @Expose
    private String username;

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public void setTokenType(String str) {
        this.tokenType = str;
    }

    public long getExpiresIn() {
        return this.expiresIn;
    }

    public void setExpiresIn(long j2) {
        this.expiresIn = j2;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(String str) {
        this.refreshToken = str;
    }

    public String getAsClientId() {
        return this.asClientId;
    }

    public void setAsClientId(String str) {
        this.asClientId = str;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getBranchcode() {
        return this.branchcode;
    }

    public void setBranchcode(String str) {
        this.branchcode = str;
    }

    public String getDbname() {
        return this.dbname;
    }

    public void setDbname(String str) {
        this.dbname = str;
    }

    public String getJlogin() {
        return this.jlogin;
    }

    public void setJlogin(String str) {
        this.jlogin = str;
    }

    public String getIssued() {
        return this.issued;
    }

    public void setIssued(String str) {
        this.issued = str;
    }

    public String getExpires() {
        return this.expires;
    }

    public void setExpires(String str) {
        this.expires = str;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String str) {
        this.error = str;
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

    public void setErrorDescription(String str) {
        this.errorDescription = str;
    }
}
