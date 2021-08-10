package com.config;

import org.springframework.beans.factory.annotation.Value;

public interface Config {

//    @Value("${xml.path}")
    String XML_PATH = "C:\\Users\\Key_Note\\Downloads\\Request.xml";
//    @Value("${duration}")
    int DURATION = 3600000;

    String ROOT = "Root";
    String EVENT = "Event";
    String REQUEST_DETAILS = "RequestDetails";
    String PRODUCT = "Product";
    String XML_ID = "Id";
    String REQUEST_DETAILS_ACCEPT_DATE = "AcceptDate";
    String REQUEST_DETAILS_SOURCE_COMPANY = "SourceCompany";
    String XML_TYPE = "Type";
    String PRODUCT_PRICE = "Price";
    String PRODUCT_START_DATE = "StartDate";
    String PRODUCT_END_DATE = "EndDate";
    String EVENT_INSURED_ID = "InsuredId";

    String GET_INSURED_ID = "/reader/insureId";
}
