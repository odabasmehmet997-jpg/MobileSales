package com.proje.mobilesales.core.base;

import com.proje.mobilesales.R;
import com.proje.mobilesales.core.tigerwcf.ResponseEnvelope;
import com.proje.mobilesales.core.tigerwcf.TigerSelectResult;
import com.proje.mobilesales.core.tigerwcf.xml.SaxResultParser;
import com.proje.mobilesales.core.utils.CompressUtil;
import com.proje.mobilesales.core.utils.ContextUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseCallback implements Callback<ResponseEnvelope> {
    private final SaxResultParser saxResultParser = new SaxResultParser();
    protected final TigerSelectResult selectResult;
    public void onFailure(Call<ResponseEnvelope> call, Throwable th) {
        throw new RuntimeException(th);
    }
    public BaseCallback(TigerSelectResult tigerSelectResult) {
        if (tigerSelectResult == null) {
            throw new IllegalArgumentException("TigerSelectResult cannot be null");
        }
        this.selectResult = tigerSelectResult;
    }
    public void onResponse(Call<ResponseEnvelope> call, Response<ResponseEnvelope> response) {
        if (response.isSuccessful()) {
            if (response.body().getBody().getQuery().getStatus().getValue() == 3) {
                this.selectResult.setSuccess(true);
                if (response.body().getBody().getQuery().getResultXML() != null) {
                    this.selectResult.setResultXML(CompressUtil.decompress(response.body().getBody().getQuery().getResultXML().getValue()));
                    TigerSelectResult tigerSelectResult = this.selectResult;
                    tigerSelectResult.setDataList(this.saxResultParser.tigerGetDataParser(tigerSelectResult.getResultXML(), this.selectResult.getProcessType().getaClass()));
                    return;
                }
                return;
            }
            this.selectResult.setSuccess(ContextUtils.getStringResource(R.string.exp_30_wcf_select_result_error, response.body().getBody().getQuery().getErrorString().getValue(), this.selectResult.getSql()));
            return;
        }
        this.selectResult.setSuccess(response.message());
    }
    public TigerSelectResult getSelectResult() {
        return this.selectResult;
    }
}
