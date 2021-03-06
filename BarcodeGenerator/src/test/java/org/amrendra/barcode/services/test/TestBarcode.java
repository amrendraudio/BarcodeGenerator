package org.amrendra.barcode.services.test;

import org.amrendra.barcode.services.Barcode;
import org.amrendra.barcode.services.impl.BarcodeImpl;
import org.junit.Test;

public class TestBarcode
{
    @Test
    public void testBarcodeBlank( )
    {
       Barcode barcode = new BarcodeImpl();
       String uniqueId = "232343545";
       barcode.generateBarcode( uniqueId );
    }
    
    @Test
    public void testEmptyBarcode()
    {
        Barcode barcode = new BarcodeImpl();
        String uniqueId = "";
        barcode.generateBarcode( uniqueId );
    }
}