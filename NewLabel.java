package com.example.sweater.domain;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class NewLabel  {
private String str;
private Product product;
public static final String FONT = "c:\\Windows\\Fonts\\times.ttf";


    public NewLabel() {
    }


    public NewLabel(String str) {
        this.str = str;
    }

    public NewLabel(Product product) {
        this.product = product;
    }

    public void createPdf() throws DocumentException, IOException {

        Rectangle one = new Rectangle( 113.0F,159.0F);
        DateFormat df = new SimpleDateFormat( "HH-mm-ss" ) ;
        Document document = new Document(one);
        PdfWriter.getInstance(document, new FileOutputStream("label " + product.getBarcode() +" "+  df.format( new Date( ))+".pdf"));
        document.setMargins(2, 2, 2, 2);

        document.open();

        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(bf, 5, Font.NORMAL);
       // Chunk chunk = new Chunk("Hello World" + product.getBarcode(), font);
        PdfPCell cell;

        float[] columnWidths = {2, 4};


        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        // the cell object

        // we add a cell with colspan 3
        cell = new PdfPCell(new Phrase("Импортер в Беларусь",font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(product.getImporter(),font));
        table.addCell(cell);
          cell = new PdfPCell(new Phrase("Производитель",font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(product.getManufacturer(),font));
        table.addCell(cell);
          cell = new PdfPCell(new Phrase("Тоговая марка",font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(product.getTrademark(),font));
        table.addCell(cell);
          cell = new PdfPCell(new Phrase("Наименование",font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(product.getProductName() + " " + product.getGender(),font));
        table.addCell(cell);
          cell = new PdfPCell(new Phrase("Артикул",font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(product.getArticle(),font));
        table.addCell(cell);
          cell = new PdfPCell(new Phrase("Состав материала",font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(product.getComposition(),font));
        table.addCell(cell);
          cell = new PdfPCell(new Phrase("Дата выпуска",font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(product.getDateArrive(),font));
        table.addCell(cell);
          cell = new PdfPCell(new Phrase("Символы ухода",font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("символы",font));
        table.addCell(cell);
          cell = new PdfPCell(new Phrase("Цена",font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(product.getRetailPrice(),font));
        table.addCell(cell);








        document.add(table);

        //document.add(chunk);
        document.close();
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
