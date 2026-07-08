package com.proje.mobilesales.features.sales.view.campaign;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.FrameMetricsAggregator;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErpActivity;
import com.proje.mobilesales.core.enums.ErpType;
import com.proje.mobilesales.core.enums.FicheType;
import com.proje.mobilesales.core.enums.LineType;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlip;
import com.proje.mobilesales.core.netsis.sendmodel.sales.ItemSlipLine;
import com.proje.mobilesales.core.utils.FicheTypeControlUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.core.utils.XmlHelper;
import com.proje.mobilesales.databinding.ShowCampaignBinding;
import com.proje.mobilesales.features.product.model.database.ItemUnits;
import com.proje.mobilesales.features.sales.model.SalesCampaign;
import com.proje.mobilesales.features.sales.model.SalesCampaignDetail;
import com.proje.mobilesales.features.sales.model.SalesCampaignPointDetail;
import com.proje.mobilesales.features.sales.repository.SalesCampaignRepository;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public final class SalesCampaignActivity extends BaseErpActivity {
    public static final Companion Companion = new Companion(null);
    private static final String EXTRA_FICHE_TYPE = SalesCampaignActivity.class.getName() + ".EXTRA_FICHE_TYPE";
    private static final String EXTRA_XML = SalesCampaignActivity.class.getName() + ".EXTRA_XML";
    private static final String STATE_FICHE_TYPE = "state:ficheType";
    private static final String STATE_XML = "state:xml";
    private ShowCampaignBinding binding;
    private SalesCampaign campaignView;
    private SalesCampaign campaignViewCache;
    private ProgressDialog dialog;
    public LinearLayout lnSurplusDiscount;
    public LinearLayout lnTotalGeneralDiscount;
    public LinearLayout lnTotalLineDiscount;
    public ListView lvShowCampaign;
    private FicheType mFicheType;
    private String mXml;
    private final SalesCampaignRepository repository;
    public AppCompatTextView tvNetTotal;
    public AppCompatTextView tvSurplusDiscount;
    public AppCompatTextView tvTotal;
    public AppCompatTextView tvTotalDiscount;
    public AppCompatTextView tvTotalGeneralDiscount;
    public AppCompatTextView tvTotalKdv;
    public AppCompatTextView tvTotalLineDiscount;
    private final SalesCampaignViewModel viewModel;
    public SalesCampaignActivity() {
        SalesCampaignRepository salesCampaignRepository = new SalesCampaignRepository();
        this.repository = salesCampaignRepository;
        this.viewModel = new SalesCampaignViewModel(salesCampaignRepository);
    }
    public ListView getLvShowCampaign() {
        ListView listView = this.lvShowCampaign;
        if (listView != null) {
            return listView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("lvShowCampaign");
        return null;
    }
    public void setLvShowCampaign(ListView listView) {
        Intrinsics.checkNotNullParameter(listView, "<set-?>");
        this.lvShowCampaign = listView;
    }
    public AppCompatTextView getTvTotalDiscount() {
        AppCompatTextView appCompatTextView = this.tvTotalDiscount;
        if (appCompatTextView != null) {
            return appCompatTextView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("tvTotalDiscount");
        return null;
    }
    public void setTvTotalDiscount(AppCompatTextView appCompatTextView) {
        Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
        this.tvTotalDiscount = appCompatTextView;
    }
    public AppCompatTextView getTvTotal() {
        AppCompatTextView appCompatTextView = this.tvTotal;
        if (appCompatTextView != null) {
            return appCompatTextView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("tvTotal");
        return null;
    }
    public void setTvTotal(AppCompatTextView appCompatTextView) {
        Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
        this.tvTotal = appCompatTextView;
    }
    public AppCompatTextView getTvTotalKdv() {
        AppCompatTextView appCompatTextView = this.tvTotalKdv;
        if (appCompatTextView != null) {
            return appCompatTextView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("tvTotalKdv");
        return null;
    }
    public void setTvTotalKdv(AppCompatTextView appCompatTextView) {
        Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
        this.tvTotalKdv = appCompatTextView;
    }
    public AppCompatTextView getTvNetTotal() {
        AppCompatTextView appCompatTextView = this.tvNetTotal;
        if (appCompatTextView != null) {
            return appCompatTextView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("tvNetTotal");
        return null;
    }
    public void setTvNetTotal(AppCompatTextView appCompatTextView) {
        Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
        this.tvNetTotal = appCompatTextView;
    }
    public AppCompatTextView getTvTotalLineDiscount() {
        AppCompatTextView appCompatTextView = this.tvTotalLineDiscount;
        if (appCompatTextView != null) {
            return appCompatTextView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("tvTotalLineDiscount");
        return null;
    }
    public void setTvTotalLineDiscount(AppCompatTextView appCompatTextView) {
        Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
        this.tvTotalLineDiscount = appCompatTextView;
    }
    public AppCompatTextView getTvSurplusDiscount() {
        AppCompatTextView appCompatTextView = this.tvSurplusDiscount;
        if (appCompatTextView != null) {
            return appCompatTextView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("tvSurplusDiscount");
        return null;
    }
    public void setTvSurplusDiscount(AppCompatTextView appCompatTextView) {
        Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
        this.tvSurplusDiscount = appCompatTextView;
    }
    public AppCompatTextView getTvTotalGeneralDiscount() {
        AppCompatTextView appCompatTextView = this.tvTotalGeneralDiscount;
        if (appCompatTextView != null) {
            return appCompatTextView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("tvTotalGeneralDiscount");
        return null;
    }
    public void setTvTotalGeneralDiscount(AppCompatTextView appCompatTextView) {
        Intrinsics.checkNotNullParameter(appCompatTextView, "<set-?>");
        this.tvTotalGeneralDiscount = appCompatTextView;
    }
    public LinearLayout getLnTotalLineDiscount() {
        LinearLayout linearLayout = this.lnTotalLineDiscount;
        if (linearLayout != null) {
            return linearLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("lnTotalLineDiscount");
        return null;
    }
    public void setLnTotalLineDiscount(LinearLayout linearLayout) {
        Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
        this.lnTotalLineDiscount = linearLayout;
    }
    public LinearLayout getLnSurplusDiscount() {
        LinearLayout linearLayout = this.lnSurplusDiscount;
        if (linearLayout != null) {
            return linearLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("lnSurplusDiscount");
        return null;
    }
    public void setLnSurplusDiscount(LinearLayout linearLayout) {
        Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
        this.lnSurplusDiscount = linearLayout;
    }
    public LinearLayout getLnTotalGeneralDiscount() {
        LinearLayout linearLayout = this.lnTotalGeneralDiscount;
        if (linearLayout != null) {
            return linearLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("lnTotalGeneralDiscount");
        return null;
    }
    public void setLnTotalGeneralDiscount(LinearLayout linearLayout) {
        Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
        this.lnTotalGeneralDiscount = linearLayout;
    }
    public ProgressDialog getDialog() {
        return this.dialog;
    }
    public void setDialog(ProgressDialog progressDialog) {
        this.dialog = progressDialog;
    }
    public String getMXml() {
        return this.mXml;
    }
    public void setMXml(String str) {
        this.mXml = str;
    }
    public FicheType getMFicheType() {
        return this.mFicheType;
    }
    public void setMFicheType(FicheType ficheType) {
        this.mFicheType = ficheType;
    }
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ShowCampaignBinding inflate = ShowCampaignBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        setToolbar();
        setResult(-1);
        initialize();
        if (bundle != null && bundle.containsKey("campaignViewCache")) {
            SalesCampaign salesCampaign = bundle.getParcelable("campaignViewCache");
            this.campaignViewCache = salesCampaign;
            this.campaignView = salesCampaign;
            this.mFicheType = FicheType.Companion.fromFicheType(bundle.getInt(STATE_FICHE_TYPE, -1));
        } else {
            this.mFicheType = FicheType.Companion.fromFicheType(getIntent().getIntExtra(EXTRA_FICHE_TYPE, -1));
        }
        if (FicheTypeControlUtils.isFicheTypeOrder(this.mFicheType)) {
            setTitle(getString(R.string.str_order_details));
        } else if (FicheTypeControlUtils.isFicheTypeInvoiceOrRetailInvoice(this.mFicheType)) {
            setTitle(getString(R.string.str_invoice_details));
        } else if (FicheTypeControlUtils.isFicheTypeDispatchOrOneToOne(this.mFicheType)) {
            setTitle(getString(R.string.str_dispatch_details));
        }
        if (this.viewModel.erpType() != ErpType.NETSIS) {
            onCreateForTiger(bundle);
        } else {
            onCreateForNetsis();
        }
    }
    private void onCreateForTiger(Bundle bundle) {
        String stringExtra;
        if (bundle != null && bundle.containsKey("campaignViewCache")) {
            stringExtra = bundle.getString(STATE_XML);
        } else {
            stringExtra = getIntent().getStringExtra(EXTRA_XML);
        }
        this.mXml = stringExtra;
        showCampaign(stringExtra);
    }
    private void onCreateForNetsis() {
        int intExtra = getIntent().getIntExtra("bigdata:synccode", -1);
        if (intExtra != -1) {
            showCampaignForNetsis(this.viewModel.getObjectForItemSlip(intExtra, true));
        }
    }
    private void showCampaign(String str) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        this.dialog = progressDialog;
        Intrinsics.checkNotNull(progressDialog);
        progressDialog.setMessage(getString(R.string.str_please_wait));
        ProgressDialog progressDialog2 = this.dialog;
        Intrinsics.checkNotNull(progressDialog2);
        progressDialog2.setCancelable(false);
        ProgressDialog progressDialog3 = this.dialog;
        Intrinsics.checkNotNull(progressDialog3);
        progressDialog3.show();
        SalesCampaign salesCampaign = this.campaignView;
        if (salesCampaign == null) {
            init(str);
        } else {
            setList(salesCampaign);
        }
    }
    private void showCampaignForNetsis(ItemSlip itemSlip) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        this.dialog = progressDialog;
        Intrinsics.checkNotNull(progressDialog);
        progressDialog.setMessage(getString(R.string.str_please_wait));
        ProgressDialog progressDialog2 = this.dialog;
        Intrinsics.checkNotNull(progressDialog2);
        progressDialog2.setCancelable(false);
        ProgressDialog progressDialog3 = this.dialog;
        Intrinsics.checkNotNull(progressDialog3);
        progressDialog3.show();
        SalesCampaign salesCampaign = this.campaignView;
        if (salesCampaign == null) {
            initForNetsis(itemSlip);
        } else {
            setList(salesCampaign);
        }
    }
    private void initialize() {
        ShowCampaignBinding showCampaignBinding = this.binding;
        ShowCampaignBinding showCampaignBinding2 = null;
        if (showCampaignBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            showCampaignBinding = null;
        }
        ListView lvShowCampaign = showCampaignBinding.lvShowCampaign;
        Intrinsics.checkNotNullExpressionValue(lvShowCampaign, "lvShowCampaign");
        setLvShowCampaign(lvShowCampaign);
        ShowCampaignBinding showCampaignBinding3 = this.binding;
        if (showCampaignBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            showCampaignBinding3 = null;
        }
        AppCompatTextView tvTotalDiscount = showCampaignBinding3.tvTotalDiscount;
        Intrinsics.checkNotNullExpressionValue(tvTotalDiscount, "tvTotalDiscount");
        setTvTotalDiscount(tvTotalDiscount);
        ShowCampaignBinding showCampaignBinding4 = this.binding;
        if (showCampaignBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            showCampaignBinding4 = null;
        }
        AppCompatTextView tvTotal = showCampaignBinding4.tvTotal;
        Intrinsics.checkNotNullExpressionValue(tvTotal, "tvTotal");
        setTvTotal(tvTotal);
        ShowCampaignBinding showCampaignBinding5 = this.binding;
        if (showCampaignBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            showCampaignBinding5 = null;
        }
        AppCompatTextView tvTotalKdv = showCampaignBinding5.tvTotalKdv;
        Intrinsics.checkNotNullExpressionValue(tvTotalKdv, "tvTotalKdv");
        setTvTotalKdv(tvTotalKdv);
        ShowCampaignBinding showCampaignBinding6 = this.binding;
        if (showCampaignBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            showCampaignBinding6 = null;
        }
        AppCompatTextView tvNetTotal = showCampaignBinding6.tvNetTotal;
        Intrinsics.checkNotNullExpressionValue(tvNetTotal, "tvNetTotal");
        setTvNetTotal(tvNetTotal);
        ShowCampaignBinding showCampaignBinding7 = this.binding;
        if (showCampaignBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            showCampaignBinding7 = null;
        }
        AppCompatTextView tvTotalLineDiscount = showCampaignBinding7.tvTotalLineDiscount;
        Intrinsics.checkNotNullExpressionValue(tvTotalLineDiscount, "tvTotalLineDiscount");
        setTvTotalLineDiscount(tvTotalLineDiscount);
        ShowCampaignBinding showCampaignBinding8 = this.binding;
        if (showCampaignBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            showCampaignBinding8 = null;
        }
        AppCompatTextView tvSurplusDiscount = showCampaignBinding8.tvSurplusDiscount;
        Intrinsics.checkNotNullExpressionValue(tvSurplusDiscount, "tvSurplusDiscount");
        setTvSurplusDiscount(tvSurplusDiscount);
        ShowCampaignBinding showCampaignBinding9 = this.binding;
        if (showCampaignBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            showCampaignBinding9 = null;
        }
        AppCompatTextView tvTotalGeneralDiscount = showCampaignBinding9.tvTotalGeneralDiscount;
        Intrinsics.checkNotNullExpressionValue(tvTotalGeneralDiscount, "tvTotalGeneralDiscount");
        setTvTotalGeneralDiscount(tvTotalGeneralDiscount);
        ShowCampaignBinding showCampaignBinding10 = this.binding;
        if (showCampaignBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            showCampaignBinding10 = null;
        }
        LinearLayout lnTotalSurplusDiscount = showCampaignBinding10.lnTotalSurplusDiscount;
        Intrinsics.checkNotNullExpressionValue(lnTotalSurplusDiscount, "lnTotalSurplusDiscount");
        setLnSurplusDiscount(lnTotalSurplusDiscount);
        ShowCampaignBinding showCampaignBinding11 = this.binding;
        if (showCampaignBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            showCampaignBinding11 = null;
        }
        LinearLayout lnTotalLineDiscount = showCampaignBinding11.lnTotalLineDiscount;
        Intrinsics.checkNotNullExpressionValue(lnTotalLineDiscount, "lnTotalLineDiscount");
        setLnTotalLineDiscount(lnTotalLineDiscount);
        ShowCampaignBinding showCampaignBinding12 = this.binding;
        if (showCampaignBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            showCampaignBinding2 = showCampaignBinding12;
        }
        LinearLayout lnTotalGeneralDiscount = showCampaignBinding2.lnTotalGeneralDiscount;
        Intrinsics.checkNotNullExpressionValue(lnTotalGeneralDiscount, "lnTotalGeneralDiscount");
        setLnTotalGeneralDiscount(lnTotalGeneralDiscount);
        LinearLayout lnTotalLineDiscount2 = getLnTotalLineDiscount();
        ErpType erpType = this.viewModel.erpType();
        ErpType erpType2 = ErpType.NETSIS;
        lnTotalLineDiscount2.setVisibility(erpType == erpType2 ? View.VISIBLE : View.GONE);
        getLnTotalGeneralDiscount().setVisibility(this.viewModel.erpType() == erpType2 ? View.VISIBLE : View.GONE);
        getLnSurplusDiscount().setVisibility(this.viewModel.erpType() == erpType2 ? View.VISIBLE : View.GONE);
    }
    public  void init(String str) {
        if (!TextUtils.isEmpty(str)) {
            SalesCampaign listFromXML = setListFromXML(str);
            this.campaignView = listFromXML;
            setList(listFromXML);
        }
        ProgressDialog progressDialog = this.dialog;
        if (progressDialog != null) {
            Intrinsics.checkNotNull(progressDialog);
            if (progressDialog.isShowing()) {
                ProgressDialog progressDialog2 = this.dialog;
                Intrinsics.checkNotNull(progressDialog2);
                progressDialog2.dismiss();
            }
        }
    }
    private void initForNetsis(ItemSlip itemSlip) {
        Intrinsics.checkNotNull(itemSlip);
        SalesCampaign listFromSales = setListFromSales(itemSlip);
        this.campaignView = listFromSales;
        setList(listFromSales);
        ProgressDialog progressDialog = this.dialog;
        if (progressDialog != null) {
            Intrinsics.checkNotNull(progressDialog);
            if (progressDialog.isShowing()) {
                ProgressDialog progressDialog2 = this.dialog;
                Intrinsics.checkNotNull(progressDialog2);
                progressDialog2.dismiss();
            }
        }
    }
    private void setList(SalesCampaign salesCampaign) {
        if (salesCampaign != null) {
            getTvTotalDiscount().setText(StringUtils.fFormat(salesCampaign.getTotalDiscount()));
            getTvTotal().setText(StringUtils.fFormat(salesCampaign.getTotal()));
            getTvTotalKdv().setText(StringUtils.fFormat(salesCampaign.getTotalKdv()));
            getTvNetTotal().setText(StringUtils.fFormat(salesCampaign.getNetTotal()));
            if (this.viewModel.erpType() == ErpType.NETSIS) {
                getTvTotalLineDiscount().setText(StringUtils.fFormat(salesCampaign.getTotalLineDiscount()));
                getTvSurplusDiscount().setText(StringUtils.fFormat(salesCampaign.getTotalSurplusDiscount()));
                getTvTotalGeneralDiscount().setText(StringUtils.fFormat(salesCampaign.getTotalGeneralDiscount()));
                getLnSurplusDiscount().setVisibility(salesCampaign.getTotalSurplusDiscount() == 0.0f ? View.GONE : View.VISIBLE);
                getLnTotalLineDiscount().setVisibility(salesCampaign.getTotalLineDiscount() == 0.0f ? View.GONE : View.VISIBLE);
                getLnTotalGeneralDiscount().setVisibility(salesCampaign.getTotalGeneralDiscount() == 0.0f ? View.GONE : View.VISIBLE);
            }
            SalesCampaignAdapter salesCampaignAdapter = new SalesCampaignAdapter(salesCampaign.getDetails(), this);
            getLvShowCampaign().setAdapter(salesCampaignAdapter);
            salesCampaignAdapter.notifyDataSetChanged();
        }
    }
    private SalesCampaign setListFromXML(String str) {
        try {
            DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
            try {
                newInstance.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
                newInstance.setFeature("http://xml.org/sax/features/external-general-entities", false);
                newInstance.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
                newInstance.setXIncludeAware(false);
                newInstance.setExpandEntityReferences(false);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            Document parse = newInstance.newDocumentBuilder().parse(new InputSource(new StringReader(str)));
            Intrinsics.checkNotNull(parse);
            try {
                NodeList resultLineByFicheType = getResultLineByFicheType(null, parse);
                if (resultLineByFicheType == null || resultLineByFicheType.getLength() <= 0) {
                    return null;
                }
                SalesCampaign salesCampaign = new SalesCampaign(null, 0.0f, 0.0f, 0.0f, 0.0f, false, 0.0f, 0.0f, 0.0f, FrameMetricsAggregator.EVERY_DURATION, null);
                XmlHelper xmlHelper = new XmlHelper();
                Node item = resultLineByFicheType.item(0);
                Intrinsics.checkNotNull(item, "null cannot be cast to non-null type org.w3c.dom.Element");
                Element element = (Element) item;
                salesCampaign.setTotalDiscount(xmlHelper.getElementFloat(element, "TOTAL_DISCOUNTS"));
                salesCampaign.setTotal(xmlHelper.getElementFloat(element, "TOTAL_GROSS"));
                salesCampaign.setTotalKdv(xmlHelper.getElementFloat(element, "TOTAL_VAT"));
                salesCampaign.setNetTotal(xmlHelper.getElementFloat(element, "TOTAL_NET"));
                if (salesCampaign.getTotalDiscount() > 0.0f) {
                    salesCampaign.setTotal(salesCampaign.getTotal() - salesCampaign.getTotalDiscount());
                }
                NodeList elementsByTagName = element.getElementsByTagName("TRANSACTIONS");
                Intrinsics.checkNotNull(elementsByTagName);
                return controlNodeList(elementsByTagName, item, element, xmlHelper, salesCampaign);
            } catch (Exception e3) {
               // e = e3;
               // e.printStackTrace();
                return null;
            }
        } catch (Exception e4) {
           // e = e4;
           // e.printStackTrace();
            return null;
        }
    }
    private NodeList getResultLineByFicheType(NodeList nodeList, Document document) {
        if (FicheTypeControlUtils.isFicheTypeOrder(this.mFicheType)) {
            return document.getElementsByTagName("ORDER_SLIP");
        }
        if (FicheTypeControlUtils.isFicheTypeInvoiceOrRetailInvoice(this.mFicheType)) {
            return document.getElementsByTagName("INVOICE");
        }
        return FicheTypeControlUtils.isFicheTypeDispatchOrOneToOne(this.mFicheType) ? document.getElementsByTagName("DISPATCH") : nodeList;
    }
    private SalesCampaign controlNodeList(NodeList nodeList, Node node, Element element, XmlHelper xmlHelper, SalesCampaign salesCampaign) {
        if (nodeList.getLength() <= 0) {
            return salesCampaign;
        }
        Node item = nodeList.item(0);
        Intrinsics.checkNotNull(item, "null cannot be cast to non-null type org.w3c.dom.Element");
        NodeList elementsByTagName = ((Element) item).getElementsByTagName("TRANSACTION");
        Intrinsics.checkNotNull(elementsByTagName);
        return controlTranscationForSalesList(elementsByTagName, node, element, xmlHelper, salesCampaign);
    }
    private SalesCampaign controlTranscationForSalesList(NodeList nodeList, Node node, Element element, XmlHelper xmlHelper, SalesCampaign salesCampaign) {
        if (nodeList.getLength() > 0) {
            ArrayList<SalesCampaignDetail> arrayList = new ArrayList<>();
            int length = nodeList.getLength();
            for (int i2 = 0; i2 < length; i2++) {
                Node item = nodeList.item(i2);
                Intrinsics.checkNotNull(item, "null cannot be cast to non-null type org.w3c.dom.Element");
                Element element2 = (Element) item;
                SalesCampaignDetail salesCampaignDetail = new SalesCampaignDetail(null, null, null, 0.0f, 0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, null, false, 16383, null);
                salesCampaignDetail.setCode(xmlHelper.getElement(element2, "MASTER_CODE"));
                salesCampaignDetail.setName(this.viewModel.getItemNameFromSqlHelper(salesCampaignDetail.getCode()));
                salesCampaignDetail.setType(StringUtils.convertStringToInt(xmlHelper.getElement(element2, "TYPE")));
                salesCampaignDetail.setUnit(xmlHelper.getElement(element2, "UNIT_CODE"));
                salesCampaignDetail.setQuantity(xmlHelper.getElementFloat(element2, "QUANTITY"));
                salesCampaignDetail.setDiscountRate(xmlHelper.getElementFloat(element2, "DISCOUNT_RATE"));
                salesCampaignDetail.setCampaignPoint(xmlHelper.getElementFloat(element2, "CAMPAIGN_POINT"));
                controlCampaignPoint(salesCampaignDetail, element2, element2, xmlHelper);
                setListFilterByLineType(salesCampaignDetail, arrayList);
            }
            salesCampaign.setDetails(arrayList);
        }
        return salesCampaign;
    }
    private void controlCampaignPoint(SalesCampaignDetail salesCampaignDetail, Element element, Element element2, XmlHelper xmlHelper) {
        if (salesCampaignDetail.getCampaignPoint() > 0.0f) {
            Node item = element.getElementsByTagName("CAMPAIGN_INFOS").item(0);
            Intrinsics.checkNotNull(item, "null cannot be cast to non-null type org.w3c.dom.Element");
            NodeList elementsByTagName = ((Element) item).getElementsByTagName("CAMPAIGN_INFO");
            Intrinsics.checkNotNull(elementsByTagName);
            controlCapmaingInfo(elementsByTagName, element2, salesCampaignDetail, xmlHelper);
        }
    }
    private void controlCapmaingInfo(NodeList nodeList, Element element, SalesCampaignDetail salesCampaignDetail, XmlHelper xmlHelper) {
        if (nodeList.getLength() > 0) {
            salesCampaignDetail.setPointDetails(new ArrayList<>());
            int length = nodeList.getLength();
            for (int i2 = 0; i2 < length; i2++) {
                Element element2 = (Element) nodeList.item(i2);
                SalesCampaignPointDetail salesCampaignPointDetail = new SalesCampaignPointDetail(null, null, 3, null);
                Intrinsics.checkNotNull(element2);
                salesCampaignPointDetail.setCampLineNo(xmlHelper.getElement(element2, "PCAMPCODE"));
                salesCampaignPointDetail.setPCampCode(xmlHelper.getElement(element2, "CAMP_LN_NO"));
                salesCampaignDetail.getPointDetails().add(salesCampaignPointDetail);
            }
        }
    }
    private void setListFilterByLineType(SalesCampaignDetail salesCampaignDetail, ArrayList<SalesCampaignDetail> arrayList) {
        if (salesCampaignDetail.getType() == LineType.PRODUCT.value) {
            if (Intrinsics.areEqual(salesCampaignDetail.getCode(), "") || Intrinsics.areEqual(salesCampaignDetail.getName(), "")) {
                return;
            }
            arrayList.add(salesCampaignDetail);
            return;
        }
        arrayList.add(salesCampaignDetail);
    }
    public SalesCampaign setListFromSales(ItemSlip itemSlip) {
        Intrinsics.checkNotNullParameter(itemSlip, "itemSlip");
        SalesCampaign salesCampaign = new SalesCampaign(null, 0.0f, 0.0f, 0.0f, 0.0f, false, 0.0f, 0.0f, 0.0f, FrameMetricsAggregator.EVERY_DURATION, null);
        salesCampaign.setForNetsis(true);
        double generalDiscount = itemSlip.getSlipHeader().getGeneralDiscount() + itemSlip.getSlipHeader().getGeneralDiscount2() + itemSlip.getSlipHeader().getGeneralDiscount3();
        double fazDiscount = itemSlip.getSlipHeader().getFazDiscount() + itemSlip.getSlipHeader().getGeneralDiscount() + itemSlip.getSlipHeader().getGeneralDiscount2() + itemSlip.getSlipHeader().getGeneralDiscount3() + itemSlip.getSlipHeader().getDetailDiscount();
        salesCampaign.setTotal(StringUtils.convertDoubleToFloat(itemSlip.getSlipHeader().getGrossTotal() - fazDiscount));
        salesCampaign.setNetTotal(StringUtils.convertDoubleToFloat(itemSlip.getSlipHeader().getGeneralTotal()));
        salesCampaign.setTotalDiscount(StringUtils.convertDoubleToFloat(fazDiscount));
        salesCampaign.setTotalKdv(StringUtils.convertDoubleToFloat(itemSlip.getSlipHeader().getVat()));
        salesCampaign.setTotalLineDiscount(StringUtils.convertDoubleToFloat(itemSlip.getSlipHeader().getDetailDiscount()));
        salesCampaign.setTotalSurplusDiscount(StringUtils.convertDoubleToFloat(itemSlip.getSlipHeader().getFazDiscount()));
        salesCampaign.setTotalGeneralDiscount(StringUtils.convertDoubleToFloat(generalDiscount));
        salesCampaign.setDetails(new ArrayList<>());
        for (ItemSlipLine itemSlipLine : itemSlip.getLines()) {
            SalesCampaignDetail salesCampaignDetail = new SalesCampaignDetail(null, null, null, 0.0f, 0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, null, false, 16383, null);
            salesCampaignDetail.setForNetsis(true);
            String stokKodu = itemSlipLine.getStokKodu();
            Intrinsics.checkNotNullExpressionValue(stokKodu, "getStokKodu(...)");
            salesCampaignDetail.setCode(stokKodu);
            salesCampaignDetail.setName(this.viewModel.getItemNameFromSqlHelper(salesCampaignDetail.getCode()));
            salesCampaignDetail.setQuantity(StringUtils.convertDoubleToFloat(itemSlipLine.getSTraGCMIK()));
            salesCampaignDetail.setType(((itemSlipLine.getPROMASYONKODU() == 0 && itemSlipLine.isKoliStok()) ? LineType.COMPOSITE_COLI : LineType.PRODUCT).value);
            SalesCampaignViewModel salesCampaignViewModel = this.viewModel;
            String code = salesCampaignDetail.getCode();
            String obr1 = itemSlipLine.getOBR1();
            Intrinsics.checkNotNullExpressionValue(obr1, "getOBR1(...)");
            List<ItemUnits> tableForItemUnits = salesCampaignViewModel.getTableForItemUnits(ItemUnits.class, "ITEMCODE=? AND CODE=?", new String[]{code, obr1});
            if (!tableForItemUnits.isEmpty()) {
                String str = tableForItemUnits.get(0).code;
                Intrinsics.checkNotNull(str);
                salesCampaignDetail.setUnit(str);
            }
            salesCampaignDetail.setSurplus(StringUtils.convertDoubleToFloat(itemSlipLine.getSTraMALFISK()));
            salesCampaignDetail.setSurplusDiscount(StringUtils.convertDoubleToFloat(salesCampaignDetail.getSurplus() * (itemSlipLine.getSTraDOVTIP() == 0 ? itemSlipLine.getSTraBF() : itemSlipLine.getSTraDOVFIAT())));
            salesCampaignDetail.setDiscount1(StringUtils.convertDoubleToFloat(itemSlipLine.getSTraSatIsk()));
            salesCampaignDetail.setDiscount2(StringUtils.convertDoubleToFloat(itemSlipLine.getSTraSatIsk2()));
            salesCampaignDetail.setDiscount3(StringUtils.convertDoubleToFloat(itemSlipLine.getSTraSatIsk3()));
            salesCampaign.getDetails().add(salesCampaignDetail);
        }
        return salesCampaign;
    }
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        if (this.campaignViewCache == null) {
            this.campaignViewCache = new SalesCampaign(null, 0.0f, 0.0f, 0.0f, 0.0f, false, 0.0f, 0.0f, 0.0f, FrameMetricsAggregator.EVERY_DURATION, null);
        }
        SalesCampaign salesCampaign = this.campaignView;
        this.campaignViewCache = salesCampaign;
        outState.putParcelable("campaignViewCache", salesCampaign);
        outState.putString(STATE_XML, this.mXml);
        FicheType ficheType = this.mFicheType;
        Intrinsics.checkNotNull(ficheType);
        outState.putInt(STATE_FICHE_TYPE, ficheType.getmValue());
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(savedInstanceState, "savedInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("campaignViewCache")) {
            SalesCampaign salesCampaign = savedInstanceState.getParcelable("campaignViewCache");
            this.campaignViewCache = salesCampaign;
            this.campaignView = salesCampaign;
            this.mXml = savedInstanceState.getString(STATE_XML);
            this.mFicheType = FicheType.Companion.fromFicheType(savedInstanceState.getInt(STATE_FICHE_TYPE, -1));
        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public String getEXTRA_FICHE_TYPE() {
            return SalesCampaignActivity.EXTRA_FICHE_TYPE;
        }

        public String getEXTRA_XML() {
            return SalesCampaignActivity.EXTRA_XML;
        }
    }
}
