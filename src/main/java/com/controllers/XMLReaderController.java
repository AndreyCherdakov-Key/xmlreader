package com.controllers;

import com.config.Config;
import com.domain.Product;
import com.services.IXMLReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class XMLReaderController {

    private final IXMLReader xmlReader;

    @Autowired
    public XMLReaderController(IXMLReader xmlReader) {
        this.xmlReader = xmlReader;
    }

    @GetMapping(value = Config.GET_INSURED_ID)
    List<Product> getProducts(@RequestParam("insureId") int insureId) {
        return xmlReader.findProducts(insureId);
    }
}
