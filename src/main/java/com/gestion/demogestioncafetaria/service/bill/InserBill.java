package com.gestion.demogestioncafetaria.service.bill;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.demogestioncafetaria.mapper.bill.Mapper;
import com.gestion.demogestioncafetaria.repository.BillRepository;
import com.gestion.demogestioncafetaria.resource.bill.BillRequest;
import com.gestion.demogestioncafetaria.resource.bill.BillTest;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.gestion.demogestioncafetaria.constent.CafeConstent.STORE_LOCATION;
import static com.gestion.demogestioncafetaria.utils.CafeUtils.getJsonArrayFromString;
import static com.gestion.demogestioncafetaria.utils.CafeUtils.getMapFromJson;

@Service
@RequiredArgsConstructor
@Slf4j
public class InserBill {

    private final BillRepository billRepository;

    public String execute(BillRequest request) throws FileNotFoundException, DocumentException, JSONException, JsonProcessingException {
        log.info("Inside InsertBill : {}", request);
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        var bill = Mapper.map(request, user);
        String fileName = bill.getUuid();
        this.billRepository.save(bill);
        String data = "Name : " + request.name() + "\n" +
                "Contact Number : " + request.contact() + "\n" +
                "Email : " + request.email() + "\n" +
                "Payment Method : " + request.paymentMethod();

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(STORE_LOCATION + "\\" + fileName + ".pdf"));

        document.open();
        setRectangleInPfd(document);

        Paragraph chunk = new Paragraph("Cafe Management System", getFont("Header"));
        chunk.setAlignment(Element.ALIGN_CENTER);
        document.add(chunk);

        Paragraph paragraph = new Paragraph("\n \n"+data+"\n \n", getFont("Data"));
        document.add(paragraph);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        addTableHeader(table);

        JSONArray jsonArray = getJsonArrayFromString(request.productDetail());
        for (int i = 0; i < jsonArray.length(); i++) {
            addRow(table, getMapFromJson(jsonArray.getString(i)));
        }
        document.add(table);


        Paragraph footer = new Paragraph("\n Total : "+request.total()+"\n"+
                "Thank you for visiting. Please visit again !!", getFont("Data"));
        document.add(footer);

        document.close();

        return "uuid : "+fileName;
    }


    private void addRow(PdfPTable table, Map<String, String> data) {
        log.info("Inside addRow");
        table.addCell(data.get("name"));
        table.addCell(data.get("category"));
        table.addCell(data.get("quantity"));
        table.addCell(String.valueOf(Double.parseDouble(data.get("price"))));
        table.addCell(String.valueOf(Double.parseDouble(data.get("total"))));
    }

    private void addTableHeader(PdfPTable table) {
        log.info("AddTableHeader");
        Stream.of("Name","Category","Quantity","Price", "Sub Total")
                .forEach(columnTitle ->{
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    header.setBackgroundColor(BaseColor.YELLOW);
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    header.setVerticalAlignment(Element.ALIGN_CENTER);
                    table.addCell(header);
                });
    }

    private Font getFont(String type) {
        log.info("Inside getFont");
        switch (type) {
            case "Header":
                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 24, BaseColor.BLACK);
                headerFont.setStyle(Font.BOLD);
                return headerFont;
            case "Data":
                Font dataFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, BaseColor.BLACK);
                dataFont.setStyle(Font.BOLD);
                return dataFont;
            default:
                return new Font();
        }
    }

    private void setRectangleInPfd(Document document) throws DocumentException {
        log.info("Inside setRectangleInPfd");
        Rectangle rectangle = new Rectangle(577, 825, 18, 15);
        rectangle.enableBorderSide(1);
        rectangle.enableBorderSide(2);
        rectangle.enableBorderSide(4);
        rectangle.enableBorderSide(8);
        rectangle.setBackgroundColor(BaseColor.WHITE);
        rectangle.setBorderWidth(1);
        document.add(rectangle);
    }
}
