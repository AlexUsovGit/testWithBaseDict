package com.example.sweater.controller;


import com.example.sweater.domain.NewLabel;
import com.example.sweater.domain.Product;
import com.example.sweater.repos.ProductRepo;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

@Controller
public class ProductController {
    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/producttable")
    public String producttable(Map<String, Object> model) {
        Iterable<Product> products = productRepo.findAll();
        model.put("products",products);

        return "producttable";
    }

    @PostMapping("/producttable")
    public String add(String productName, String gender, String size, String trademark,
                      String importer, String manufacturer, String article,
                      String code, String composition, String season, String barcode, String note,
                      String quantity,  String dateArrive, String importPrice,String coefficient,
                      String retailPrice, String countryOfEntry,String currency, String course,
                      Map<String, Object> model) throws ParseException {

        Product product = new Product(productName,gender,size,trademark,importer,
                manufacturer,article, code, composition, season,barcode,note,
                quantity,   dateArrive,  importPrice,coefficient,retailPrice, countryOfEntry,currency, course);
        productRepo.save(product);

        Iterable<Product> products = productRepo.findAll();
        model.put("products",products);

        return "producttable";
    }

    @PostMapping("/searchdoc")
    public String filter (@RequestParam String filter, Map<String, Object> model) throws IOException {
        Iterable<Product> products;

         if(filter!=null && !filter.isEmpty()){
            products = productRepo.findByBarcode(filter);
             for (Product product : products) {


             NewLabel documentPdf = new NewLabel(product);
             try {
                 documentPdf.createPdf();
             } catch (DocumentException e) {
                 e.printStackTrace();
             } catch (FileNotFoundException e) {
                 e.printStackTrace();
             }
             }



         }else {
            products = productRepo.findAll();


        }
        model.put("products", products);

        return "documentpdf";
    }

}
