package org.amrendra.barcode.services;

import org.springframework.stereotype.Service;

@Service
public interface Barcode
{
    public String generateBarcode( String uniqueId );
}
