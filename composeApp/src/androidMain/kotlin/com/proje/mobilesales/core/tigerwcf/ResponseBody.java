package com.proje.mobilesales.core.tigerwcf;

import com.proje.mobilesales.core.tigerwcf.edocument.GetEDocumentContent;
import com.proje.mobilesales.core.tigerwcf.edocument.SendEArchiveDocuments;
import com.proje.mobilesales.core.tigerwcf.edocument.SendRecvEDispatchDocuments;
import com.proje.mobilesales.core.tigerwcf.edocument.SendRecvEInvoiceDocuments;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementUnion;
import org.simpleframework.xml.Root;


@Root(name = "Body", strict = false)

public class ResponseBody {

    @ElementUnion({@Element(name = "AppendDataObjectResponse", required = false, type = DataQuery.class), @Element(name = "DeleteDataObjectResponse", required = false, type = DataQuery.class), @Element(name = "ReadDataObjectResponse", required = false, type = DataQuery.class), @Element(name = "CalculateDataObjectResponse", required = false, type = DataQuery.class), @Element(name = "UsableCampaignCardsResponse", required = false, type = DataQuery.class)})
    private DataQuery dataQuery;

    @Element(name = "GetEDocumentContentResponse", required = false)
    private GetEDocumentContent edocumentContent;

    @Element(name = "getValueResponse", required = false, type = GetValueResponse.class)
    private GetValueResponse getValueResponse;

    @Element(name = "ExecQueryResponse", required = false)
    @ElementUnion({@Element(name = "ExecQueryResponse", required = false, type = Query.class), @Element(name = "ExecSPResponse", required = false, type = Query.class), @Element(name = "DirectQueryResponse", required = false, type = Query.class), @Element(name = "getInfoResponse", required = false, type = Query.class)})
    private Query query;

    @Element(name = "SendEArchiveDocumentsResponse", required = false)
    private SendEArchiveDocuments sendEArchiveDocuments;

    @Element(name = "SendRecvEDispatchDocumentsResponse", required = false)
    private SendRecvEDispatchDocuments sendRecvEDispatchDocuments;

    @Element(name = "SendRecvEInvoiceDocumentsResponse", required = false)
    private SendRecvEInvoiceDocuments sendRecvEInvoiceDocuments;

    public Query getQuery() {
        return this.query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public DataQuery getDataQuery() {
        return this.dataQuery;
    }

    public void setDataQuery(DataQuery dataQuery) {
        this.dataQuery = dataQuery;
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

    public GetValueResponse getGetValueResponse() {
        return this.getValueResponse;
    }

    public void setGetValueResponse(GetValueResponse getValueResponse) {
        this.getValueResponse = getValueResponse;
    }
}
