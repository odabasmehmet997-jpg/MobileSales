package com.google.zxing.client.result;

import java.util.regex.Pattern;

public abstract class ResultParser {
    private static final Pattern AMPERSAND = Pattern.compile("&");
    private static final Pattern DIGITS = Pattern.compile("\\d+");
    private static final Pattern EQUALS = Pattern.compile("=");
    private static final ResultParser[] PARSERS;
    static {
        BookmarkDoCoMoResultParser bookmarkDoCoMoResultParser = new BookmarkDoCoMoResultParser();
        AddressBookDoCoMoResultParser addressBookDoCoMoResultParser = new AddressBookDoCoMoResultParser();
        EmailDoCoMoResultParser emailDoCoMoResultParser = new EmailDoCoMoResultParser();
        AddressBookAUResultParser addressBookAUResultParser = new AddressBookAUResultParser();
        VCardResultParser vCardResultParser = new VCardResultParser();
        BizcardResultParser bizcardResultParser = new BizcardResultParser();
        VEventResultParser vEventResultParser = new VEventResultParser();
        EmailAddressResultParser emailAddressResultParser = new EmailAddressResultParser();
        SMTPResultParser sMTPResultParser = new SMTPResultParser();
        TelResultParser telResultParser = new TelResultParser();
        SMSMMSResultParser sMSMMSResultParser = new SMSMMSResultParser();
        SMSTOMMSTOResultParser sMSTOMMSTOResultParser = new SMSTOMMSTOResultParser();
        GeoResultParser geoResultParser = new GeoResultParser();
        WifiResultParser wifiResultParser = new WifiResultParser();
        URLTOResultParser uRLTOResultParser = new URLTOResultParser();
        URIResultParser uRIResultParser = new URIResultParser();
        URIResultParser uRIResultParser2 = uRIResultParser;
        PARSERS = new ResultParser[]{bookmarkDoCoMoResultParser, addressBookDoCoMoResultParser, emailDoCoMoResultParser, addressBookAUResultParser, vCardResultParser, bizcardResultParser, vEventResultParser, emailAddressResultParser, sMTPResultParser, telResultParser, sMSMMSResultParser, sMSTOMMSTOResultParser, geoResultParser, wifiResultParser, uRLTOResultParser, uRIResultParser2, new ISBNResultParser(), new ProductResultParser(), new ExpandedProductResultParser(), new VINResultParser()};
    }
}
