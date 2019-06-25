package com.example.sweater.controller;

import com.example.sweater.domain.NewLabel;
import com.example.sweater.domain.Product;
import com.example.sweater.repos.ProductRepo;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

@Controller

public class PrintController {

    @Autowired
    private ProductRepo productRepo;



    @PostMapping("createpdf")
    public String myPrint(@RequestParam Product product) throws IOException {
        NewLabel documentPdf = new NewLabel(product);
        try {
            documentPdf.createPdf();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return "documentpdf";
    }



    

    @RequestMapping(value="/getpdf", method=RequestMethod.POST)
    public ResponseEntity<byte[]> getPDF(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Product> products;
        ResponseEntity<byte[]> response = null;

        if(filter!=null && !filter.isEmpty()){
            products = productRepo.findByBarcode(filter);
            for (Product product : products) {
                NewLabel documentPdf = new NewLabel(product);
                byte[] contents = documentPdf.getPDF();

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                // Here you have to set the actual filename of your pdf
                String filename = "output.pdf";
                headers.setContentDispositionFormData(filename, filename);
                headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
                response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
                return response;
            }
        }else {
            products = productRepo.findAll();


        }

        return response;
    }
    
    

}
