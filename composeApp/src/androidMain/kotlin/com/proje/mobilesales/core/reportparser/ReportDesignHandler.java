package com.proje.mobilesales.core.reportparser;

import android.content.res.Resources;
import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.model.USER;
import com.proje.mobilesales.features.reports.model.enums.ReportColumnDataType;
import com.proje.mobilesales.features.reports.model.enums.ReportDisplayType;
import com.proje.mobilesales.features.reports.model.enums.ReportLayoutItemType;
import com.proje.mobilesales.features.reports.model.enums.ReportSerieViewType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import org.springframework.http.HttpHeaders;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;



public class ReportDesignHandler extends DefaultHandler2 {
    private static final String REPORTDETAILS = "REPORTDETAILS";
    private static final String R_ALIAS = "Alias";
    private static final String R_APPEARANCENAMESERIALIZABLE = "AppearanceNameSerializable";
    private static final String R_ARGUMENTDATAMEMBER = "ArgumentDataMember";
    private static final String R_ARGUMENTSCALETYPE = "ArgumentScaleType";
    private static final String R_AXISX = "AxisX";
    private static final String R_AXISY = "AxisY";
    private static final String R_CHART = "Chart";
    private static final String R_COLUMNDATATYPE = "DataType";
    private static final String R_COLUMNS = "Columns";
    private static final String R_CULTURE = "CULTURE";
    private static final String R_DATATYPE = "DATATYPE";
    private static final String R_DGVCOLSWIDTH = "DGVCOLSWIDTH";
    private static final String R_DIAGRAM = "Diagram";
    private static final String R_DIMENSION = "Dimension";
    private static final String R_DISPLAYTYPE = "DISPLAYTYPE";
    private static final String R_ENUS = "ENUS";
    private static final String R_EXPLODEDPOINTSFILTERSCONJUNCTIONMODE = "ExplodedPointsFiltersConjunctionMode";
    private static final String R_FIELDLABEL = "FIELDLABEL";
    private static final String R_GRIDLAYOUT = "GRIDLAYOUT";
    private static final String R_HIDEFIRSTCOL = "HIDEFIRSTCOL";
    private static final String R_KEY = "KEY";
    private static final String R_KEYS = "KEYS";
    private static final String R_NAME = "name";
    private static final String R_PARAM = "PARAM";
    private static final String R_PARAMKEY = "ParamKey";
    private static final String R_PARAMVALUE = "ParamValue";
    private static final String R_PROPERTY = "property";
    private static final String R_PROPERTYCOLUMNEDITNAME = "ColumnEditName";
    private static final String R_PROPERTYFIELDNAME = "FieldName";
    private static final String R_PROPERTYNAME = "Name";
    private static final String R_PROPERTYVISIBLE = "Visible";
    private static final String R_PROPERTYVISIBLEINDEX = "VisibleIndex";
    private static final String R_PROPERTYWIDTH = "Width";
    private static final String R_REMOTOREPORT = "REMOTOREPORT";
    private static final String R_RURU = "RURU";
    private static final String R_SELECTIONMODE = "SelectionMode";
    private static final String R_SERIEITEM = "Item";
    private static final String R_SERIEITEMNAME = "Name";
    private static final String R_SERIESSELECTIONMODE = "SeriesSelectionMode";
    private static final String R_SERIESTEMPLATE = "SeriesTemplate";
    private static final String R_SQL = "SQL";
    private static final String R_SUBSQL = "SUBSQL";
    private static final String R_SUMMARIES = "SUMMARIES";
    private static final String R_SUMMARY = "SUMMARY";
    private static final String R_SUMMARYCOLUMNNAME = "COLUMNNAME";
    private static final String R_SUMMARYTYPE = "TYPE";
    private static final String R_SWEEPDIRECTION = "SweepDirection";
    private static final String R_TITLES = "Titles";
    private static final String R_TITLETEXT = "Text";
    private static final String R_TRANSLATION = "TRANSLATION";
    private static final String R_TRTR = "TRTR";
    private static final String R_TYPE = "TYPE";
    private static final String R_TYPENAMESERIALIZABLE = "TypeNameSerializable";
    private static final String R_VALUEDATAMEMBERSSERIALIZABLE = "ValueDataMembersSerializable";
    private static final String R_VALUES = "VALUES";
    private static final String R_VIEW = "View";
    private static final String R_VISIBLEINPANESSERIALIZABLE = "VisibleInPanesSerializable";
    private static final String TAG = "com.proje.mobilesales.core.reportparser.ReportDesignHandler";
    private static final String WCFORDERBYKEY = "WCFORDERBY";
    private StringBuilder builder;
    private boolean chartTitlesStarted;
    private ReportChart mChart;
    private Report mReport;
    private ReportLayout mReportLayout;
    private ReportParam mReportParam;
    private ReportProperty mReportProperty;
    private ReportTranslation mReportTranslation;
    private boolean seriesTemplateStarted;
    private Stack<ReportProperty> mReferences = new Stack<>();
    private String alias = "";
    private String paramKey = "";

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        super.startDocument();
        builder = new StringBuilder();
        mReport = new Report();
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        mReportProperty = null;
        mReferences.clear();
        mReferences = null;
        mReportTranslation = null;
        mReportParam = null;
        builder = null;
        super.endDocument();
        if (null != this.mReport.getSql() && !mReport.getSql().isEmpty()) {
            final String[] split = mReport.getSql().split("\\{\\{|\\}\\}|\\[|\\]");
            if (1 < split.length) {
                String str = "";
                for (int i2 = 0; i2 < split.length; i2++) {
                    if (0 == i2 % 2) {
                        str = str + split[i2];
                    } else {
                        if (0 < split[i2].indexOf("(")) {
                            split[i2] = StringUtils.convTrCharEN(split[i2].replace("(", ""));
                        }
                        if (0 < split[i2].indexOf(")")) {
                            split[i2] = StringUtils.convTrCharEN(split[i2].replace(")", ""));
                        }
                        str = str + StringUtils.convTrCharEN(split[i2].replace(" ", "_"));
                    }
                }
                mReport.setSql(str);
                mReport.setOrgSql(str);
            }
        }
        if (ReportDisplayType.Form == this.mReport.getDisplayType()) {
            this.ExtractFormReportInfo();
        }
        this.MapDataTypesToColumns();
        this.SetParameterValues();
        Collections.sort(mReport.getReportLayout().getReportColumns(), new Comparator() { // from class: com.proje.mobilesales.core.reportparser.ReportDesignHandlerExternalSyntheticLambda2
            @Override // java.util.Comparator
            public int compare(final Object obj, final Object obj2) {
                final int lambdaendDocument0;
                lambdaendDocument0 = lambdaendDocument0((ReportColumn) obj, (ReportColumn) obj2);
                return lambdaendDocument0;
            }
        });
        this.SetAdditionalParam();
    }

    
    public static int lambdaendDocument0(final ReportColumn reportColumn, final ReportColumn reportColumn2) {
        return reportColumn.getVisibleIndex() - reportColumn2.getVisibleIndex();
    }

    private void SetParameterValues() {
        int i2;
        int i3 = -1;
        if (null != this.mReport.getReportParams()) {
            for (final ReportParam reportParam : mReport.getReportParams()) {
                final String dataType = reportParam.getDataType();
                dataType.hashCode();
                switch (dataType.hashCode()) {
                    case -335760659:
                        if ("Numeric".equals(dataType)) {
                            i2 = 0;
                            break;
                        }
                        i2 = i3;
                        break;
                    case 2603341:
                        if (dataType.equals(ReportDesignHandler.R_TITLETEXT)) {
                            i2 = 1;
                            break;
                        }
                        i2 = i3;
                        break;
                    case 1857393595:
                        if (dataType.equals(ExifInterface.TAG_DATETIME)) {
                            i2 = 2;
                            break;
                        }
                        i2 = i3;
                        break;
                    default:
                        i2 = i3;
                        break;
                }
                switch (i2) {
                    case 0:
                        if (this.findConstantParam(reportParam.getName()) == ReportConstParam.AktifFirmaNo) {
                            reportParam.setValues(USER.firmano);
                            break;
                        } else if (this.findConstantParam(reportParam.getName()) == ReportConstParam.AktifDonemNo) {
                            reportParam.setValues(USER.periodNr);
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (this.findConstantParam(reportParam.getName()) == ReportConstParam.KullaniciSaticiKodu) {
                            reportParam.setValues(USER.usercode);
                            break;
                        } else if (this.findConstantParam(reportParam.getName()) == ReportConstParam.AktifDonemNo) {
                            reportParam.setValues(USER.periodNr);
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
                        final Calendar calendar = Calendar.getInstance();
                        final ReportConstParam findConstantParam = this.findConstantParam(reportParam.getName());
                        if (findConstantParam == ReportConstParam.Tomorrow) {
                            calendar.add(5, 1);
                        } else if (findConstantParam == ReportConstParam.Yesterday) {
                            calendar.add(5, i3);
                        } else if (findConstantParam == ReportConstParam.FirstDayOfThisWeek) {
                            calendar.set(11, 0);
                            calendar.clear(12);
                            calendar.clear(13);
                            calendar.clear(14);
                            calendar.set(7, calendar.getFirstDayOfWeek());
                        } else if (findConstantParam == ReportConstParam.LastDayOfThisWeek) {
                            calendar.set(11, 0);
                            calendar.clear(12);
                            calendar.clear(13);
                            calendar.clear(14);
                            calendar.set(7, calendar.getFirstDayOfWeek());
                            calendar.add(5, 6);
                        } else if (findConstantParam == ReportConstParam.FirstDayOfThisMonth) {
                            calendar.set(5, 1);
                        } else if (findConstantParam == ReportConstParam.LastDayOfThisMonth) {
                            calendar.set(5, calendar.getActualMaximum(5));
                        } else if (findConstantParam == ReportConstParam.FirstDayOfThisYear) {
                            calendar.set(6, 1);
                        } else if (findConstantParam == ReportConstParam.LastDayOfThisYear) {
                            calendar.set(6, calendar.getActualMaximum(6));
                        }
                        if (null != findConstantParam) {
                            reportParam.setValues(simpleDateFormat.format(calendar.getTime()));
                            break;
                        } else {
                            break;
                        }
                }
                i3 = -1;
            }
        }
    }

    private void SetAdditionalParam() {
        if (null == this.mReportLayout.getAdditionalParams() || 0 >= this.mReportLayout.getAdditionalParams().size()) {
            return;
        }
        for (final ReportAdditionalParam reportAdditionalParam : mReportLayout.getAdditionalParams()) {
            if (reportAdditionalParam.getParamKey().equals(ReportDesignHandler.WCFORDERBYKEY)) {
                mReport.setWcfOrderBy(reportAdditionalParam.getParamValue());
                return;
            } else if (reportAdditionalParam.getParamKey().equals(ReportDesignHandler.REPORTDETAILS)) {
                mReport.setReportDetails(reportAdditionalParam.getParamValue());
                return;
            }
        }
    }

    private void MapDataTypesToColumns() {
        for (final ReportDataType reportDataType : mReportLayout.getReportDataTypes()) {
            final String convTrCharEN = StringUtils.convTrCharEN(reportDataType.getAlias());
            for (final ReportColumn reportColumn : mReportLayout.getReportColumns()) {
                if (reportDataType.getAlias().replace(" ", "_").equalsIgnoreCase(reportColumn.getFieldName()) || convTrCharEN.replace(" ", "_").equalsIgnoreCase(reportColumn.getFieldName())) {
                    final String dataType = reportDataType.getDataType();
                    dataType.hashCode();
                    switch (dataType) {
                        case "System.Int16":
                        case "System.Int32":
                        case "System.Int64":
                        case "System.SByte":
                        case "System.UInt16":
                        case "System.UInt32":
                        case "System.UInt64":
                            reportColumn.setColumnDataType(ReportColumnDataType.Numeric);
                            break;
                        case "System.Decimal":
                        case "System.Double":
                        case "System.Single":
                            reportColumn.setColumnDataType(ReportColumnDataType.Decimal);
                            break;
                        case "System.DateTime":
                            reportColumn.setColumnDataType(ReportColumnDataType.DateTime);
                            break;
                        default:
                            reportColumn.setColumnDataType(ReportColumnDataType.String);
                            break;
                    }
                }
            }
        }
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void startElement(final String str, final String str2, final String str3, final Attributes attributes) throws SAXException {
        str3.hashCode();
        switch (str3) {
            case "SeriesTemplate":
                seriesTemplateStarted = true;
                break;
            case "Titles":
                chartTitlesStarted = true;
                break;
            case "SUMMARY":
                mReport.getSummaries().add(this.createSummary(attributes));
                break;
            case "property":
                final ReportProperty createProperty = this.createProperty(attributes);
                if (null == this.mReportProperty) {
                    mReportProperty = createProperty;
                    break;
                } else {
                    mReferences.push(createProperty);
                    break;
                }
            case "Diagram":
                mChart.setDiagram(this.createDiagram(attributes));
                break;
            case "View":
                this.createSerieView(attributes, seriesTemplateStarted);
                break;
            case "SUMMARIES":
                mReport.setSummaries(new ArrayList());
                break;
            case "AxisX":
                mChart.getDiagram().setAxisX(this.createAxis(attributes));
                break;
            case "AxisY":
                mChart.getDiagram().setAxisY(this.createAxis(attributes));
                break;
            case "Chart":
                mChart = this.createChart(attributes);
                break;
            case "PARAM":
                mReportParam = this.createReportParam(attributes);
                break;
            case "TRANSLATION":
                mReportTranslation = this.createTranslation(attributes);
                break;
            case "GRIDLAYOUT":
                mReportLayout = new ReportLayout();
                break;
        }
        if (str3.startsWith(ReportDesignHandler.R_SERIEITEM)) {
            if (!chartTitlesStarted) {
                final ReportChartSerie createSerie = this.createSerie(attributes);
                if (null != createSerie.getValueDataMembersSerializable() && !createSerie.getValueDataMembersSerializable().isEmpty()) {
                    mChart.getSeries().add(createSerie);
                }
            } else {
                this.createSerieTitle(attributes);
            }
        }
        super.startElement(str, str2, str3, attributes);
    }

    private ReportSummary createSummary(final Attributes attributes) {
        final ReportSummary reportSummary = new ReportSummary();
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            final String qName = attributes.getQName(i2);
            qName.hashCode();
            if (qName.equals(ReportDesignHandler.R_SUMMARYCOLUMNNAME)) {
                reportSummary.setColumnName(attributes.getValue(i2));
            } else if ("TYPE".equals(qName)) {
                reportSummary.setType(attributes.getValue(i2));
            }
        }
        return reportSummary;
    }

    private ReportConstParam findConstantParam(final String str) {
        for (final ReportConstParam reportConstParam : ReportConstParam.values()) {
            if (reportConstParam.name().equals(str)) {
                return reportConstParam;
            }
        }
        return null;
    }

    private ReportParam createReportParam(final Attributes attributes) {
        final ReportParam reportParam = new ReportParam();
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            final String qName = attributes.getQName(i2);
            qName.hashCode();
            switch (qName) {
                case "HIDEFIRSTCOL":
                    reportParam.setHideFirstCol(attributes.getValue(i2).equals(BuildConfig.NETSIS_DEMO_PASSWORD));
                    break;
                case "SUBSQL":
                    reportParam.setSubSql(attributes.getValue(i2));
                    break;
                case "VALUES":
                    reportParam.setValues(attributes.getValue(i2));
                    break;
                case "DATATYPE":
                    reportParam.setDataType(attributes.getValue(i2));
                    break;
                case "KEYS":
                    reportParam.setKeys(attributes.getValue(i2));
                    break;
                case "TYPE":
                    reportParam.setType(attributes.getValue(i2));
                    break;
                case "FIELDLABEL":
                    reportParam.setFieldLabel(attributes.getValue(i2));
                    break;
            }
        }
        return reportParam;
    }

    private ReportTranslation createTranslation(final Attributes attributes) {
        final ReportTranslation reportTranslation = new ReportTranslation();
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            final String qName = attributes.getQName(i2);
            qName.hashCode();
            switch (qName) {
                case "KEY":
                    reportTranslation.setKey(attributes.getValue(i2));
                    reportTranslation.setEnConvertedKey(StringUtils.convTrCharEN(attributes.getValue(i2)));
                    break;
                case "ENUS":
                    reportTranslation.setEn(attributes.getValue(i2));
                    break;
                case "RURU":
                    reportTranslation.setRu(attributes.getValue(i2));
                    break;
                case "TRTR":
                    reportTranslation.setTr(attributes.getValue(i2));
                    break;
            }
        }
        return reportTranslation;
    }

    private ReportProperty createProperty(final Attributes attributes) {
        return new ReportProperty(attributes.getValue(ReportDesignHandler.R_NAME));
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void characters(final char[] cArr, final int i2, final int i3) throws SAXException {
        super.characters(cArr, i2, i3);
        builder.append(cArr, i2, i3);
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
    public void endElement(final String str, final String str2, final String str3) throws SAXException {
        super.endElement(str, str2, str3);
        str3.hashCode();
        switch (str3) {
            case "SeriesTemplate":
                seriesTemplateStarted = false;
                break;
            case "DGVCOLSWIDTH":
                mReport.setColumnWidths(this.getValue());
                break;
            case "ParamValue":
                final ReportAdditionalParam reportAdditionalParam = new ReportAdditionalParam();
                reportAdditionalParam.setParamKey(paramKey);
                reportAdditionalParam.setParamValue(this.getValue());
                mReportLayout.getAdditionalParams().add(reportAdditionalParam);
                paramKey = "";
                break;
            case "Titles":
                chartTitlesStarted = false;
                break;
            case "SUMMARY":
                mReport.getSummaries().get(mReport.getSummaries().size() - 1).setText(this.getValue());
                break;
            case "property":
                if (0 < this.mReferences.size()) {
                    mReferences.peek().setValue(this.getValue());
                    if (1 < this.mReferences.size()) {
                        mReferences.peek().addProperty(mReferences.pop());
                        break;
                    } else if (mReportProperty.getName().equals(ReportDesignHandler.R_COLUMNS)) {
                        mReportLayout.getReportColumns().add(this.createFromReportProperty(mReferences.pop()));
                        break;
                    } else {
                        mReportProperty.addProperty(mReferences.pop());
                        break;
                    }
                } else {
                    mReportProperty.setValue(this.getValue());
                    mReportLayout.getReportProperties().add(mReportProperty);
                    mReportProperty = null;
                    break;
                }
            case "REMOTOREPORT":
                mReport.setRemoteReport(this.getValue());
                break;
            case "DISPLAYTYPE":
                mReport.setDisplayType(this.getValue());
                break;
            case "SQL":
                mReport.setSql(this.getValue());
                mReport.setOrgSql(this.getValue());
                break;
            case "Alias":
                alias = this.getValue();
                break;
            case "Chart":
                mReportLayout.setChart(mChart);
                break;
            case "PARAM":
                mReportParam.setName(this.getValue());
                mReport.addReportParam(mReportParam);
                break;
            case "TRANSLATION":
                mReport.addReportTranslation(mReportTranslation);
                break;
            case "ParamKey":
                paramKey = this.getValue();
                break;
            case "CULTURE":
                mReport.setDefaultLanguage(this.getValue());
                break;
            case "DataType":
                final ReportDataType reportDataType = new ReportDataType();
                reportDataType.setAlias(alias);
                reportDataType.setDataType(this.getValue());
                mReportLayout.getReportDataTypes().add(reportDataType);
                alias = "";
                break;
            case "GRIDLAYOUT":
                mReport.setReportLayout(mReportLayout);
                break;
        }
        builder.setLength(0);
    }

    private String getValue() {
        return builder.toString().trim();
    }

    private ReportColumn createFromReportProperty(final ReportProperty reportProperty) {
        final ReportColumn reportColumn = new ReportColumn();
        for (final ReportProperty reportProperty2 : reportProperty.getReportProperties()) {
            final String name = reportProperty2.getName();
            name.hashCode();
            switch (name) {
                case "FieldName":
                    reportColumn.setFieldName(reportProperty2.getValue());
                    break;
                case "ColumnEditName":
                    reportColumn.setColumnEditName(reportProperty2.getValue());
                    break;
                case "VisibleIndex":
                    reportColumn.setVisibleIndex(this.tryParseInt(reportProperty2.getValue()));
                    break;
                case "Name":
                    reportColumn.setName(reportProperty2.getValue());
                    break;
                case "Width":
                    reportColumn.setWidth(this.tryParseInt(reportProperty2.getValue()));
                    break;
                case "Visible":
                    reportColumn.setVisible(Boolean.parseBoolean(reportProperty2.getValue()));
                    break;
            }
        }
        this.setColumnTranslationIfExists(reportColumn);
        if (0 == reportColumn.getWidth()) {
            reportColumn.setWidth(100);
        }
        reportColumn.setCalculatedWidth((reportColumn.getWidth() * Resources.getSystem().getDisplayMetrics().densityDpi) / 160);
        return reportColumn;
    }

    private void setColumnTranslationIfExists(final ReportColumn reportColumn) {
        String en;
        final List<ReportTranslation> reportTranslations = mReport.getReportTranslations();
        if (null == reportTranslations) {
            reportColumn.setFieldName(reportColumn.getFieldName().replace(" ", "_"));
            return;
        }
        final String upperCase = mReport.getDefaultLanguage().replace("-", "").toUpperCase();
        for (final ReportTranslation reportTranslation : reportTranslations) {
            upperCase.hashCode();
            switch (upperCase) {
                case "ENUS":
                    en = reportTranslation.getEn();
                    break;
                case "RURU":
                    en = reportTranslation.getRu();
                    break;
                case "TRTR":
                    en = reportTranslation.getTr();
                    break;
                default:
                    en = "";
                    break;
            }
            final String convTrCharEN = StringUtils.convTrCharEN(en);
            if (en.equalsIgnoreCase(reportColumn.getFieldName()) || convTrCharEN.equalsIgnoreCase(reportColumn.getFieldName())) {
                reportColumn.setTranslation(reportTranslation);
                return;
            }
        }
        reportColumn.setFieldName(reportColumn.getFieldName().replace(" ", "_"));
    }

    private int tryParseInt(final String str) {
        try {
            return Integer.parseInt(str);
        } catch (final NumberFormatException unused) {
            return -1;
        }
    }

    private void createSerieTitle(final Attributes attributes) {
        if (null == this.mChart.getTitles()) {
            mChart.setTitles(new ArrayList());
        }
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            if (attributes.getQName(i2).equals(ReportDesignHandler.R_TITLETEXT)) {
                mChart.getTitles().add(attributes.getValue(i2));
            }
        }
    }

    private ReportChartSerie createSerie(final Attributes attributes) {
        final ReportChartSerie reportChartSerie = new ReportChartSerie();
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            final String qName = attributes.getQName(i2);
            qName.hashCode();
            switch (qName) {
                case "ArgumentScaleType":
                    reportChartSerie.setArgumentScaleType(attributes.getValue(i2));
                    break;
                case "ValueDataMembersSerializable":
                    reportChartSerie.setValueDataMembersSerializable(attributes.getValue(i2));
                    break;
                case "Name":
                    reportChartSerie.setName(attributes.getValue(i2));
                    break;
                case "ArgumentDataMember":
                    reportChartSerie.setArgumentDataMember(attributes.getValue(i2));
                    break;
            }
        }
        return reportChartSerie;
    }

    private void createSerieView(final Attributes attributes, final boolean z) {
        boolean z2;
        char c2 = 2;
        final ReportChartSerieView reportChartSerieView = new ReportChartSerieView();
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            final String qName = attributes.getQName(i2);
            qName.hashCode();
            switch (qName.hashCode()) {
                case -626479548:
                    if (qName.equals(ReportDesignHandler.R_EXPLODEDPOINTSFILTERSCONJUNCTIONMODE)) {
                        z2 = false;
                        break;
                    }
                    z2 = -1;
                    break;
                case -11219437:
                    if (qName.equals(ReportDesignHandler.R_SWEEPDIRECTION)) {
                        z2 = true;
                        break;
                    }
                    z2 = -1;
                    break;
                case 1380778660:
                    if (qName.equals(ReportDesignHandler.R_TYPENAMESERIALIZABLE)) {
                        z2 = 2;
                        break;
                    }
                    z2 = -1;
                    break;
                default:
                    z2 = -1;
                    break;
            }
            switch (z2) {
                case false:
                    reportChartSerieView.setExplodedPointsFiltersConjunctionMode(attributes.getValue(i2));
                    break;
                case true:
                    reportChartSerieView.setSweepDirection(attributes.getValue(i2));
                    break;
                case true:
                    reportChartSerieView.setTypeNameSerializable(attributes.getValue(i2));
                    break;
            }
        }
        if (!z) {
            if (0 == this.mChart.getSeries().size()) {
                return;
            }
            final ReportChartSerie reportChartSerie = mChart.getSeries().get(mChart.getSeries().size() - 1);
            final String typeNameSerializable = reportChartSerieView.getTypeNameSerializable();
            typeNameSerializable.hashCode();
            switch (typeNameSerializable.hashCode()) {
                case -916203448:
                    if ("PieSeriesView".equals(typeNameSerializable)) {
                        c2 = 0;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case -137769140:
                    if ("PointSeriesView".equals(typeNameSerializable)) {
                        c2 = 1;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 1216319728:
                    break;
                default:
                    c2 = '\uffff';
                    break;
            }
            switch (c2) {
                case 0:
                    reportChartSerie.setSerieViewType(ReportSerieViewType.Pie);
                    break;
                case 1:
                    reportChartSerie.setSerieViewType(ReportSerieViewType.Point);
                    break;
                case 2:
                    reportChartSerie.setSerieViewType(ReportSerieViewType.Line);
                    break;
                default:
                    reportChartSerie.setSerieViewType(ReportSerieViewType.Bar);
                    break;
            }
            reportChartSerie.setSeriesView(reportChartSerieView);
            return;
        }
        mChart.setSerieTemplate(reportChartSerieView);
    }

    private ReportChartDiagram createDiagram(final Attributes attributes) {
        final ReportChartDiagram reportChartDiagram = new ReportChartDiagram();
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            final String qName = attributes.getQName(i2);
            qName.hashCode();
            if (qName.equals(ReportDesignHandler.R_DIMENSION)) {
                reportChartDiagram.setDimension(attributes.getValue(i2));
            } else if (qName.equals(ReportDesignHandler.R_TYPENAMESERIALIZABLE)) {
                reportChartDiagram.setTypeNameSerializable(attributes.getValue(i2));
            }
        }
        return reportChartDiagram;
    }

    private ReportChartDiagramAxis createAxis(final Attributes attributes) {
        final ReportChartDiagramAxis reportChartDiagramAxis = new ReportChartDiagramAxis();
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            final String qName = attributes.getQName(i2);
            qName.hashCode();
            if (qName.equals(ReportDesignHandler.R_VISIBLEINPANESSERIALIZABLE)) {
                reportChartDiagramAxis.setVisibleInPanesSerializable(attributes.getValue(i2));
            }
        }
        return reportChartDiagramAxis;
    }

    private ReportChart createChart(final Attributes attributes) {
        final ReportChart reportChart = new ReportChart();
        for (int i2 = 0; i2 < attributes.getLength(); i2++) {
            final String qName = attributes.getQName(i2);
            qName.hashCode();
            switch (qName) {
                case "AppearanceNameSerializable":
                    reportChart.setAppearanceNameSerializable(attributes.getValue(i2));
                    break;
                case "SeriesSelectionMode":
                    reportChart.setSeriesSelectionMode(attributes.getValue(i2));
                    break;
                case "SelectionMode":
                    reportChart.setSelectionMode(attributes.getValue(i2));
                    break;
            }
        }
        return reportChart;
    }

    private void ExtractFormReportInfo() {
        ReportProperty reportProperty;
        final ReportPropertySearchResult FindReportProperty = this.FindReportProperty(null, "Items");
        if (null == FindReportProperty || null == FindReportProperty.foundProperty.getReportProperties()) {
            return;
        }
        for (ReportProperty reportProperty2 : FindReportProperty.foundProperty.getReportProperties()) {
            final String value = this.FindReportProperty(reportProperty2, "TypeName").foundProperty.getValue();
            value.hashCode();
            switch (value) {
                case "LayoutViewCard":
                    final ReportLayoutViewCard reportLayoutViewCard = new ReportLayoutViewCard();
                    this.FillReportItemInfo(reportLayoutViewCard, reportProperty2);
                    mReportLayout.setCard(reportLayoutViewCard);
                    break;
                case "LayoutGroup":
                    if (null == this.mReportLayout.getCard()) {
                        final ReportLayoutViewCard reportLayoutViewCard2 = new ReportLayoutViewCard();
                        this.FillReportItemInfo(reportLayoutViewCard2, reportProperty2);
                        mReportLayout.setCard(reportLayoutViewCard2);
                        break;
                    } else {
                        final ReportLayoutGroup reportLayoutGroup = new ReportLayoutGroup();
                        reportLayoutGroup.setItemType(ReportLayoutItemType.LayoutGroup);
                        this.FillReportItemInfo(reportLayoutGroup, reportProperty2);
                        if (reportLayoutGroup.getParentName().isEmpty()) {
                            final String GetPropertyValue = this.GetPropertyValue(reportProperty2, "TabbedGroupParentName");
                            if (!GetPropertyValue.isEmpty()) {
                                reportLayoutGroup.setParentName(GetPropertyValue);
                            }
                        }
                        if (ReportLayoutItemType.TabbedGroup == AddItemToParent(reportLayoutGroup).getItemType()) {
                            reportLayoutGroup.setTabGroup(true);
                            break;
                        } else {
                            break;
                        }
                    }
                case "LayoutControlItem":
                    final String GetPropertyValue2 = this.GetPropertyValue(reportProperty2, "Name");
                    if (!GetPropertyValue2.endsWith("-alias") && !GetPropertyValue2.endsWith("-data")) {
                        break;
                    } else if (GetPropertyValue2.endsWith("-alias")) {
                        final ReportLayoutItem reportLayoutDefaultItem = new ReportLayoutDefaultItem();
                        reportLayoutDefaultItem.setItemType(ReportLayoutItemType.Label);
                        if ("Customization".equals(GetPropertyValue(reportProperty2, "ParentName"))) {
                            break;
                        } else {
                            this.FillReportItemInfo(reportLayoutDefaultItem, reportProperty2);
                            this.AddItemToParent(reportLayoutDefaultItem);
                            break;
                        }
                    } else {
                        if (GetPropertyValue2.endsWith("-data")) {
                            final ReportPropertySearchResult FindReportProperty2 = this.FindReportProperty(FindReportProperty.foundProperty, "Name", GetPropertyValue2.replace("-data", "-alias"));
                            reportProperty = null == FindReportProperty2 ? null : FindReportProperty2.parentProperty;
                        } else {
                            reportProperty2 = null;
                            reportProperty = null;
                        }
                        final ReportLayoutColumn CreateReportLayoutColumn = this.CreateReportLayoutColumn(reportProperty, reportProperty2);
                        if (null != reportProperty2 && "Customization".equals(GetPropertyValue(reportProperty2, "ParentName"))) {
                            CreateReportLayoutColumn.getReportColumn().setVisible(false);
                            break;
                        } else {
                            this.AddItemToParent(CreateReportLayoutColumn);
                            break;
                        }
                    }
                case "TabbedGroup":
                    final ReportLayoutTabbedGroup reportLayoutTabbedGroup = new ReportLayoutTabbedGroup();
                    reportLayoutTabbedGroup.setItemType(ReportLayoutItemType.TabbedGroup);
                    reportLayoutTabbedGroup.setSelectedTabPageName(this.GetPropertyValue(reportProperty2, "SelectedTabPageName"));
                    this.FillReportItemInfo(reportLayoutTabbedGroup, reportProperty2);
                    this.AddItemToParent(reportLayoutTabbedGroup);
                    break;
                default:
                    final ReportLayoutItem reportLayoutDefaultItem2 = new ReportLayoutDefaultItem();
                    reportLayoutDefaultItem2.setItemType(ReportLayoutItemType.Label);
                    this.FillReportItemInfo(reportLayoutDefaultItem2, reportProperty2);
                    this.AddItemToParent(reportLayoutDefaultItem2);
                    break;
            }
        }
        this.InsertRowInfoInFormItems(mReportLayout.getCard());
    }

    private ReportPropertySearchResult FindReportPropertyWithParent(final ReportProperty reportProperty, final String str, final String str2) {
        if (null == reportProperty) {
            for (final ReportProperty reportProperty2 : mReport.getReportLayout().getReportProperties()) {
                if (reportProperty2.getName().equals(str)) {
                    return null;
                }
                final ReportPropertySearchResult FindReportProperty = this.FindReportProperty(reportProperty2, str, str2);
                if (null != FindReportProperty && FindReportProperty.foundProperty.getName().equals(str) && FindReportProperty.parentProperty.getName().equals(str2)) {
                    return FindReportProperty;
                }
            }
        } else {
            if (reportProperty.getName().equals(str) || null == reportProperty.getReportProperties()) {
                return null;
            }
            for (final ReportProperty reportProperty3 : reportProperty.getReportProperties()) {
                if (reportProperty3.getName().equals(str) && reportProperty.getName().equals(str2)) {
                    return new ReportPropertySearchResult(reportProperty3, reportProperty);
                }
                final ReportPropertySearchResult FindReportProperty2 = this.FindReportProperty(reportProperty3, str, str2);
                if (null != FindReportProperty2 && FindReportProperty2.foundProperty.getName().equals(str) && FindReportProperty2.parentProperty.getName().equals(str2)) {
                    return FindReportProperty2;
                }
            }
        }
        return null;
    }

    private ReportPropertySearchResult FindReportProperty(final ReportProperty reportProperty, final String str) {
        if (null == reportProperty) {
            for (final ReportProperty reportProperty2 : mReport.getReportLayout().getReportProperties()) {
                if (reportProperty2.getName().equals(str)) {
                    return new ReportPropertySearchResult(reportProperty2, null);
                }
                final ReportPropertySearchResult FindReportProperty = this.FindReportProperty(reportProperty2, str);
                if (null != FindReportProperty && FindReportProperty.foundProperty.getName().equals(str)) {
                    return FindReportProperty;
                }
            }
        } else {
            if (reportProperty.getName().equals(str)) {
                return new ReportPropertySearchResult(reportProperty, null);
            }
            if (null == reportProperty.getReportProperties()) {
                return null;
            }
            for (final ReportProperty reportProperty3 : reportProperty.getReportProperties()) {
                if (reportProperty3.getName().equals(str)) {
                    return new ReportPropertySearchResult(reportProperty3, reportProperty);
                }
                final ReportPropertySearchResult FindReportProperty2 = this.FindReportProperty(reportProperty3, str);
                if (null != FindReportProperty2 && FindReportProperty2.foundProperty.getName().equals(str)) {
                    return FindReportProperty2;
                }
            }
        }
        return null;
    }

    private ReportPropertySearchResult FindReportProperty(final ReportProperty reportProperty, final String str, final String str2) {
        if (null == reportProperty) {
            for (final ReportProperty reportProperty2 : mReport.getReportLayout().getReportProperties()) {
                if (reportProperty2.getName().equals(str) && reportProperty2.getValue().equals(str2)) {
                    return new ReportPropertySearchResult(reportProperty2, null);
                }
                final ReportPropertySearchResult FindReportProperty = this.FindReportProperty(reportProperty2, str, str2);
                if (null != FindReportProperty && FindReportProperty.foundProperty.getName().equals(str) && FindReportProperty.foundProperty.getValue().equals(str2)) {
                    return FindReportProperty;
                }
            }
        } else {
            if (reportProperty.getName().equals(str) && reportProperty.getValue().equals(str2)) {
                return new ReportPropertySearchResult(reportProperty, null);
            }
            if (null == reportProperty.getReportProperties()) {
                return null;
            }
            for (final ReportProperty reportProperty3 : reportProperty.getReportProperties()) {
                if (reportProperty3.getName().equals(str) && reportProperty3.getValue().equals(str2)) {
                    return new ReportPropertySearchResult(reportProperty3, reportProperty);
                }
                final ReportPropertySearchResult FindReportProperty2 = this.FindReportProperty(reportProperty3, str, str2);
                if (null != FindReportProperty2 && FindReportProperty2.foundProperty.getName().equals(str) && FindReportProperty2.foundProperty.getValue().equals(str2)) {
                    return FindReportProperty2;
                }
            }
        }
        return null;
    }

    private void FillReportItemInfo(final ReportLayoutItem reportLayoutItem, ReportProperty reportProperty, final ReportProperty reportProperty2) {
        final ReportPropertySearchResult FindReportPropertyWithParent;
        int i2 = 0;
        reportLayoutItem.setFormTextVisible(false);
        reportLayoutItem.setShowInCustomizationForm(false);
        if (null == reportProperty) {
            reportProperty = reportProperty2;
        }
        reportLayoutItem.setFormText(this.GetPropertyValue(reportProperty, ReportDesignHandler.R_TITLETEXT));
        reportLayoutItem.setName(this.GetPropertyValue(reportProperty2, "Name").replace("-alias", "").replace("-data", ""));
        reportLayoutItem.setParentName(this.GetPropertyValue(reportProperty2, "ParentName"));
        final String[] split = this.GetPropertyValue(reportProperty2, HttpHeaders.LOCATION).split(",|=|@");
        int i3 = 0;
        while (true) {
            if (i3 >= split.length) {
                break;
            }
            final String str = split[i3];
            if (null != str) {
                if ("X".equals(str)) {
                    reportLayoutItem.setFormX(Integer.valueOf(split[i3 + 1]).intValue());
                } else if ("Y".equals(split[i3])) {
                    reportLayoutItem.setFormY(Integer.valueOf(split[i3 + 1]).intValue());
                    break;
                }
            }
            i3++;
        }
        final String[] split2 = this.GetPropertyValue(reportProperty2, "Size").split(",|=|@");
        while (true) {
            if (i2 >= split2.length) {
                break;
            }
            final String str2 = split2[i2];
            if (null != str2) {
                if (str2.equals(ReportDesignHandler.R_PROPERTYWIDTH)) {
                    reportLayoutItem.setFormWidth(Integer.valueOf(split2[i2 + 1]).intValue() + reportLayoutItem.getTextWidth());
                } else if ("Height".equals(split2[i2])) {
                    reportLayoutItem.setFormHeight(Integer.valueOf(split2[i2 + 1]).intValue() + reportLayoutItem.getTextHeight());
                    break;
                }
            }
            i2++;
        }
        if (null == reportProperty2 || null == (FindReportPropertyWithParent = FindReportPropertyWithParent(reportProperty2, "AppearanceItemCaption", reportProperty2.getName()))) {
            return;
        }
        reportLayoutItem.setValueAppearanceItemCaption(this.FillAppearanceItemCaptionInfo(FindReportPropertyWithParent.foundProperty));
    }

    private ReportAppearanceItemCaption FillAppearanceItemCaptionInfo(final ReportProperty reportProperty) {
        final ReportAppearanceItemCaption reportAppearanceItemCaption = new ReportAppearanceItemCaption();
        if (null == reportProperty) {
            return reportAppearanceItemCaption;
        }
        reportAppearanceItemCaption.setForeColor(this.GetPropertyValue(reportProperty, "ForeColor"));
        final String[] split = this.GetPropertyValue(reportProperty, "Font").split(";|=|, ");
        if (0 < split.length) {
            reportAppearanceItemCaption.setFontName(split[0]);
            reportAppearanceItemCaption.setFontSize(Float.valueOf(split[1].replaceAll("[a-zA-Z]", "")).floatValue());
            final List asList = Arrays.asList(split);
            reportAppearanceItemCaption.setBold(asList.contains("Bold"));
            reportAppearanceItemCaption.setItalic(asList.contains("Italic"));
            reportAppearanceItemCaption.setUnderline(asList.contains("Underline"));
        }
        return reportAppearanceItemCaption;
    }

    private void FillReportItemInfo(final ReportLayoutItem reportLayoutItem, final ReportProperty reportProperty) {
        final ReportPropertySearchResult FindReportPropertyWithParent;
        reportLayoutItem.setFormTextVisible(!"false".equals(GetPropertyValue(reportProperty, "TextVisible")));
        reportLayoutItem.setShowInCustomizationForm(!"false".equals(GetPropertyValue(reportProperty, "ShowInCustomizationForm")));
        reportLayoutItem.setCustomizationFormText(this.GetPropertyValue(reportProperty, "CustomizationFormText"));
        reportLayoutItem.setFormText(this.GetPropertyValue(reportProperty, ReportDesignHandler.R_TITLETEXT));
        reportLayoutItem.setName(this.GetPropertyValue(reportProperty, "Name").replace("-alias", ""));
        reportLayoutItem.setParentName(this.GetPropertyValue(reportProperty, "ParentName"));
        final String[] split = this.GetPropertyValue(reportProperty, HttpHeaders.LOCATION).split(",|=|@");
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i3 >= split.length) {
                break;
            }
            final String str = split[i3];
            if (null != str) {
                if ("X".equals(str)) {
                    reportLayoutItem.setFormX(Integer.valueOf(split[i3 + 1]).intValue());
                } else if ("Y".equals(split[i3])) {
                    reportLayoutItem.setFormY(Integer.valueOf(split[i3 + 1]).intValue());
                    break;
                }
            }
            i3++;
        }
        final String[] split2 = this.GetPropertyValue(reportProperty, "Size").split(",|=|@");
        int i4 = 0;
        while (true) {
            if (i4 >= split2.length) {
                break;
            }
            final String str2 = split2[i4];
            if (null != str2) {
                if (str2.equals(ReportDesignHandler.R_PROPERTYWIDTH)) {
                    reportLayoutItem.setFormWidth(Integer.valueOf(split2[i4 + 1]).intValue());
                } else if ("Height".equals(split2[i4])) {
                    reportLayoutItem.setFormHeight(Integer.valueOf(split2[i4 + 1]).intValue());
                    break;
                }
            }
            i4++;
        }
        final String[] split3 = this.GetPropertyValue(reportProperty, "TextSize").split(",|=|@");
        while (true) {
            if (i2 >= split3.length) {
                break;
            }
            final String str3 = split3[i2];
            if (null != str3) {
                if (str3.equals(ReportDesignHandler.R_PROPERTYWIDTH)) {
                    reportLayoutItem.setTextWidth(Integer.valueOf(split3[i2 + 1]).intValue());
                } else if ("Height".equals(split3[i2])) {
                    reportLayoutItem.setTextHeight(Integer.valueOf(split3[i2 + 1]).intValue());
                    break;
                }
            }
            i2++;
        }
        if (null == reportProperty || null == (FindReportPropertyWithParent = FindReportPropertyWithParent(reportProperty, "AppearanceItemCaption", reportProperty.getName()))) {
            return;
        }
        reportLayoutItem.setTextAppearanceItemCaption(this.FillAppearanceItemCaptionInfo(FindReportPropertyWithParent.foundProperty));
    }

    private ReportLayoutColumn CreateReportLayoutColumn(final ReportProperty reportProperty, final ReportProperty reportProperty2) {
        final ReportLayoutColumn reportLayoutColumn = new ReportLayoutColumn();
        this.FillReportItemInfo(reportLayoutColumn, reportProperty, reportProperty2);
        reportLayoutColumn.setColumnName(this.GetPropertyValue(reportProperty, "ColumnName"));
        reportLayoutColumn.setItemType(ReportLayoutItemType.Column);
        final ReportColumn reportColumn = new ReportColumn();
        reportColumn.setFieldName(reportLayoutColumn.getName());
        reportColumn.setColumnEditName(reportLayoutColumn.getFormText());
        reportLayoutColumn.setReportColumn(reportColumn);
        reportColumn.setVisible(true);
        this.mReport.getReportLayout().getReportColumns().add(reportColumn);
        return reportLayoutColumn;
    }

    private String GetPropertyValue(final ReportProperty reportProperty, final String str) {
        final ReportPropertySearchResult FindReportProperty = this.FindReportProperty(reportProperty, str);
        if (null == FindReportProperty) {
            return "";
        }
        return FindReportProperty.foundProperty.getValue();
    }

    private ReportLayoutItem FindLayoutItemByName(final String str) {
        if (str.equals(mReportLayout.getCard().getName())) {
            return mReportLayout.getCard();
        }
        for (final ReportLayoutItem reportLayoutItem : mReportLayout.getCard().getChildItems()) {
            if (null != reportLayoutItem && reportLayoutItem.getName().equals(str)) {
                return reportLayoutItem;
            }
            final ReportLayoutItem FindInnerLayoutItemByName = this.FindInnerLayoutItemByName(reportLayoutItem, str);
            if (null != FindInnerLayoutItemByName && FindInnerLayoutItemByName.getName().equals(str)) {
                return FindInnerLayoutItemByName;
            }
        }
        return null;
    }

    private ReportLayoutItem FindInnerLayoutItemByName(final ReportLayoutItem reportLayoutItem, final String str) {
        if (null == reportLayoutItem.getChildItems()) {
            return null;
        }
        for (final ReportLayoutItem reportLayoutItem2 : reportLayoutItem.getChildItems()) {
            if (reportLayoutItem2.getName().equals(str)) {
                return reportLayoutItem2;
            }
            final ReportLayoutItem FindInnerLayoutItemByName = this.FindInnerLayoutItemByName(reportLayoutItem2, str);
            if (null != FindInnerLayoutItemByName && FindInnerLayoutItemByName.getName().equals(str)) {
                return FindInnerLayoutItemByName;
            }
        }
        return null;
    }

    private ReportLayoutItem AddItemToParent(final ReportLayoutItem reportLayoutItem) {
        ReportLayoutItem FindLayoutItemByName = this.FindLayoutItemByName(reportLayoutItem.getParentName());
        if (null == FindLayoutItemByName) {
            FindLayoutItemByName = mReportLayout.getCard();
        }
        if (null == FindLayoutItemByName.getChildItems()) {
            FindLayoutItemByName.setChildItems(new ArrayList());
        }
        FindLayoutItemByName.getChildItems().add(reportLayoutItem);
        if (0 != reportLayoutItem.getTextWidth()) {
            reportLayoutItem.setFormTextWeight(reportLayoutItem.getTextWidth() / reportLayoutItem.getFormWidth());
        }
        reportLayoutItem.setFormValueWeight(1.0f - reportLayoutItem.getFormTextWeight());
        Collections.sort(FindLayoutItemByName.getChildItems(), new Comparator() { // from class: com.proje.mobilesales.core.reportparser.ReportDesignHandlerExternalSyntheticLambda0
            @Override // java.util.Comparator
            public int compare(final Object obj, final Object obj2) {
                final int lambdaAddItemToParent1;
                lambdaAddItemToParent1 = lambdaAddItemToParent1((ReportLayoutItem) obj, (ReportLayoutItem) obj2);
                return lambdaAddItemToParent1;
            }
        });
        Collections.sort(FindLayoutItemByName.getChildItems(), new Comparator() { // from class: com.proje.mobilesales.core.reportparser.ReportDesignHandlerExternalSyntheticLambda1
            @Override // java.util.Comparator
            public int compare(final Object obj, final Object obj2) {
                final int lambdaAddItemToParent2;
                lambdaAddItemToParent2 = lambdaAddItemToParent2((ReportLayoutItem) obj, (ReportLayoutItem) obj2);
                return lambdaAddItemToParent2;
            }
        });
        if (1 == FindLayoutItemByName.getChildItems().size()) {
            FindLayoutItemByName.setTotalRows(1);
            return FindLayoutItemByName;
        }
        FindLayoutItemByName.setFormWidth(FindLayoutItemByName.getChildItems().get(0).getFormWidth());
        for (int i2 = 1; i2 < FindLayoutItemByName.getChildItems().size(); i2++) {
            final ReportLayoutItem reportLayoutItem2 = FindLayoutItemByName.getChildItems().get(i2 - 1);
            final ReportLayoutItem reportLayoutItem3 = FindLayoutItemByName.getChildItems().get(i2);
            if (reportLayoutItem3.getFormY() != reportLayoutItem2.getFormY()) {
                reportLayoutItem3.setFormRowIndex(reportLayoutItem2.getFormRowIndex() + 1);
                FindLayoutItemByName.setTotalRows(reportLayoutItem3.getFormRowIndex() + 1);
            } else {
                reportLayoutItem3.setFormRowIndex(reportLayoutItem2.getFormRowIndex());
            }
            if (reportLayoutItem3.getFormX() != reportLayoutItem2.getFormX()) {
                reportLayoutItem3.setFormColumnIndex(0 != reportLayoutItem3.getFormX() ? reportLayoutItem2.getFormColumnIndex() + 1 : 0);
            } else {
                reportLayoutItem3.setFormColumnIndex(reportLayoutItem2.getFormColumnIndex());
            }
        }
        return FindLayoutItemByName;
    }

    
    public static int lambdaAddItemToParent1(final ReportLayoutItem reportLayoutItem, final ReportLayoutItem reportLayoutItem2) {
        return reportLayoutItem.getFormX() - reportLayoutItem2.getFormX();
    }

    
    public static int lambdaAddItemToParent2(final ReportLayoutItem reportLayoutItem, final ReportLayoutItem reportLayoutItem2) {
        return reportLayoutItem.getFormY() - reportLayoutItem2.getFormY();
    }

    private void addRowItem(final ReportLayoutItem reportLayoutItem, final ReportLayoutItem reportLayoutItem2) {
        reportLayoutItem.setFormWidth(reportLayoutItem.getFormWidth() + reportLayoutItem2.getFormWidth());
        reportLayoutItem.getChildItems().add(reportLayoutItem2);
        final Iterator<ReportLayoutItem> it = reportLayoutItem.getChildItems().iterator();
        while (it.hasNext()) {
            it.next().setWeight(r0.getFormWidth() / reportLayoutItem.getFormWidth());
        }
    }

    private void InsertRowInfoInFormItems(final ReportLayoutItem reportLayoutItem) {
        if (null == reportLayoutItem.getChildItems() || 0 == reportLayoutItem.getTotalRows()) {
            return;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < reportLayoutItem.getTotalRows(); i3++) {
            final ReportLayoutRow reportLayoutRow = new ReportLayoutRow();
            reportLayoutRow.setChildItems(new ArrayList());
            final Iterator<ReportLayoutItem> it = reportLayoutItem.getChildItems().iterator();
            while (it.hasNext()) {
                final ReportLayoutItem next = it.next();
                if ((next instanceof ReportLayoutRow) || next.getFormRowIndex() != i3) {
                    break;
                }
                this.InsertRowInfoInFormItems(next);
                it.remove();
                this.addRowItem(reportLayoutRow, next);
                if (reportLayoutRow.getFormWidth() > i2) {
                    i2 = reportLayoutRow.getFormWidth();
                }
            }
            if (1 == reportLayoutRow.getChildItems().size()) {
                reportLayoutRow.getChildItems().get(0).setWeight(1.0f);
            }
            reportLayoutItem.getChildItems().add(reportLayoutRow);
        }
    }

    public Report getReport() {
        return mReport;
    }
}
