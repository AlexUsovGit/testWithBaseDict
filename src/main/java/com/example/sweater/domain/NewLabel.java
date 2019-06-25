package com.example.sweater.domain;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class NewLabel  {
private String str;
private Product product;
//public static final String FONT = "c:\\Windows\\Fonts\\times.ttf";


    public NewLabel() {
    }


    public NewLabel(String str) {
        this.str = str;
    }

    public NewLabel(Product product) {
        this.product = product;
    }

    public void createPdf() throws DocumentException, IOException {
        PdfWriter writer = null;

        Rectangle one = new Rectangle( 113.0F,159.0F);
        DateFormat df = new SimpleDateFormat( "HH-mm-ss" ) ;
        Document document = new Document(one);
        PdfWriter.getInstance(document, new FileOutputStream("D:\\label " + product.getBarcode() +" "+  df.format( new Date( ))+".pdf"));
        document.setMargins(2, 2, 2, 2);

        document.open();

    //    BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    //    Font font = new Font(bf, 5, Font.NORMAL);
         Font font = new Font(Font.FontFamily.HELVETICA, 5, Font.NORMAL);
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


        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Products");
        Row headerRow = sheet.createRow(0);
        Cell cellE1 = headerRow.createCell(0);
        cellE1.setCellValue("Новый ве");
        Cell cellE2 = headerRow.createCell(1);
        cellE2.setCellValue("Что-то");

        Row row = sheet.createRow(1);
        row.createCell(0).setCellValue(product.getArticle());
        row.createCell(1).setCellValue(product.getBarcode());
        row.createCell(2).setCellValue(product.getGender());
        row.createCell(3).setCellValue(product.getRetailPrice());
        FileOutputStream fileOut = new FileOutputStream("D:\\contacts.xlsx");
        workbook.write(fileOut);
        fileOut.close();

    }
    public byte [] getPDF(){




        PdfWriter writer = null;

        FileOutputStream fos = null;
        byte [] myByteFile = new byte[0];
        Rectangle one = new Rectangle( 113.0F,159.0F);
        DateFormat df = new SimpleDateFormat( "HH-mm-ss" ) ;
        Document document = new Document(one);

        try {
            fos = new FileOutputStream("D:\\label " + product.getBarcode() +" "+  df.format( new Date( ))+".pdf");
            writer =    PdfWriter.getInstance(document, fos);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        document.setMargins(2, 2, 2, 2);

        document.open();
        Font font = new Font(Font.FontFamily.HELVETICA, 5, Font.NORMAL);
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


        try {
            document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        //document.add(chunk);


        document.close();





        try {
            myByteFile =Files.readAllBytes(Paths.get("d:\\2.pdf"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //---------------------------------------------------------------





        return myByteFile;

    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
