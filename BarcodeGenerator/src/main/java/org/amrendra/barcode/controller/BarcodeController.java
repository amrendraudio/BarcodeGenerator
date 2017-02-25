package org.amrendra.barcode.controller;

import org.amrendra.barcode.services.Barcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BarcodeController
{

    @Autowired
    private Barcode barcode;

    @RequestMapping( value = {"/generateBarcode" }, method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    public @ResponseBody String getGeneratedBarcode( @RequestParam String input )
    {
        if( input != null && !input.isEmpty() )
        {
            return barcode.generateBarcode( input );

        }
        return "INVALID INPUT";
    }

}
