package org.amrendra.barcode.services.impl;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import org.amrendra.barcode.services.Barcode;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class BarcodeImpl implements Barcode
{

    @Override
    public String generateBarcode( String uniqueId )
    {
        try
        {
            Document document = new Document( new Rectangle( PageSize.A4 ) );
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            /*PdfWriter writer = PdfWriter.getInstance( document, new FileOutputStream( "F:\\barcode.pdf" ) );*/
            PdfWriter writer = PdfWriter.getInstance( document, byteArray );
            document.open();
            document.add( new Paragraph( "Barcode Generated PDF" ) );
            Barcode128 code128 = new Barcode128();
            code128.setGenerateChecksum( true );
            code128.setCode( uniqueId );
            document.add( code128.createImageWithBarcode( writer.getDirectContent(), BaseColor.DARK_GRAY, BaseColor.BLACK ) );
            document.close();
            return new String( byteArray.toByteArray(), "UTF-8" );

        }
        catch( DocumentException | UnsupportedEncodingException de )
        {
            de.printStackTrace();
        }

        return null;
    }
}
