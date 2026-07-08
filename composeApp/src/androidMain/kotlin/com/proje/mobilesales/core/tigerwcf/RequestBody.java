package com.proje.mobilesales.core.tigerwcf;

import com.proje.mobilesales.core.tigerwcf.edocument.GetEDocumentContent;
import com.proje.mobilesales.core.tigerwcf.edocument.SendEArchiveDocuments;
import com.proje.mobilesales.core.tigerwcf.edocument.SendRecvEDispatchDocuments;
import com.proje.mobilesales.core.tigerwcf.edocument.SendRecvEInvoiceDocuments;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root(name = "Body", strict = false)

public class RequestBody {

    @Element(name = "AppendDataObject", required = false)
    private DataQuery appendDataObject;

    @Element(name = "CalculateDataObject", required = false)
    private DataQuery calculateDataObject;

    @Element(name = "DeleteDataObject", required = false)
    private DataQuery deleteDataObject;

    @Element(name = "DirectQuery", required = false)
    private Query directQuery;

    @Element(name = "GetEDocumentContent", required = false)
    private GetEDocumentContent edocumentContent;

    @Element(name = "ExecQuery", required = false)
    private Query execQuery;

    @Element(name = "ExecSP", required = false)
    private Query execSP;

    @Element(name = "getInfo", required = false)
    private Query getInfoQuery;

    @Element(name = "getValue", required = false)
    private GetValueRequest getValue;

    @Element(name = "ReadDataObject", required = false)
    private DataQuery readDataObject;

    @Element(name = "SendEArchiveDocuments", required = false)
    private SendEArchiveDocuments sendEArchiveDocuments;

    @Element(name = "SendRecvEDispatchDocuments", required = false)
    private SendRecvEDispatchDocuments sendRecvEDispatchDocuments;

    @Element(name = "SendRecvEInvoiceDocuments", required = false)
    private SendRecvEInvoiceDocuments sendRecvEInvoiceDocuments;

    @Element(name = "UsableCampaignCards", required = false)
    private DataQuery usableCampaignCards;

    public void setExecQuery(Query query) {
        this.execQuery = query;
    }

    public Query getExecQuery() {
        return this.execQuery;
    }

    public Query getExecSP() {
        return this.execSP;
    }

    public void setExecSP(Query query) {
        this.execSP = query;
    }

    public Query getDirectQuery() {
        return this.directQuery;
    }

    public void setDirectQuery(Query query) {
        this.directQuery = query;
    }

    public DataQuery getAppendDataObject() {
        return this.appendDataObject;
    }

    public void setAppendDataObject(DataQuery dataQuery) {
        this.appendDataObject = dataQuery;
    }

    public DataQuery getDeleteDataObject() {
        return this.deleteDataObject;
    }

    public void setDeleteDataObject(DataQuery dataQuery) {
        this.deleteDataObject = dataQuery;
    }

    public DataQuery getReadDataObject() {
        return this.readDataObject;
    }

    public void setReadDataObject(DataQuery dataQuery) {
        this.readDataObject = dataQuery;
    }

    public DataQuery getCalculateDataObject() {
        return this.calculateDataObject;
    }

    public void setCalculateDataObject(DataQuery dataQuery) {
        this.calculateDataObject = dataQuery;
    }

    public Query getGetInfoQuery() {
        return this.getInfoQuery;
    }

    public void setGetInfoQuery(Query query) {
        this.getInfoQuery = query;
    }

    public GetEDocumentContent getEdocumentContent() {
        return this.edocumentContent;
    }

    public void setEdocumentContent(GetEDocumentContent getEDocumentContent) {
        this.edocumentContent = getEDocumentContent;
    }

    public SendRecvEInvoiceDocuments getSendRecvEInvoiceDocuments() {
        return this.sendRecvEInvoiceDocuments;
    }

    public void setSendRecvEInvoiceDocuments(SendRecvEInvoiceDocuments sendRecvEInvoiceDocuments) {
        this.sendRecvEInvoiceDocuments = sendRecvEInvoiceDocuments;
    }

    public SendEArchiveDocuments getSendEArchiveDocuments() {
        return this.sendEArchiveDocuments;
    }

    public void setSendEArchiveDocuments(SendEArchiveDocuments sendEArchiveDocuments) {
        this.sendEArchiveDocuments = sendEArchiveDocuments;
    }

    public SendRecvEDispatchDocuments getSendRecvEDispatchDocuments() {
        return this.sendRecvEDispatchDocuments;
    }

    public void setSendRecvEDispatchDocuments(SendRecvEDispatchDocuments sendRecvEDispatchDocuments) {
        this.sendRecvEDispatchDocuments = sendRecvEDispatchDocuments;
    }

    public DataQuery getUsableCampaignCards() {
        return this.usableCampaignCards;
    }

    public void setUsableCampaignCards(DataQuery dataQuery) {
        this.usableCampaignCards = dataQuery;
    }

    public GetValueRequest getGetValue() {
        return this.getValue;
    }

    public void setGetValue(GetValueRequest getValueRequest) {
        this.getValue = getValueRequest;
    }
}
