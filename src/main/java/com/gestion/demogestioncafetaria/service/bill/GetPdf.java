package com.gestion.demogestioncafetaria.service.bill;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gestion.demogestioncafetaria.constent.CafeConstent;
import com.gestion.demogestioncafetaria.resource.bill.BillPfdRequest;
import com.gestion.demogestioncafetaria.mapper.bill.Mapper;
import com.gestion.demogestioncafetaria.utils.CafeUtils;
import com.itextpdf.text.DocumentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.io.IOUtils;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetPdf {

    private final InserBill inserBill;

    public byte[] execute(BillPfdRequest request) throws DocumentException, JSONException {
        log.info("Inside Get pft : {}", request);
        byte[] bytes = new byte[0];
        String fileName = CafeConstent.STORE_LOCATION + request.uuid() + ".pdf";
        if (CafeUtils.isFileExist(fileName)) {
            try {
                bytes = getButeArray(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            try {
                bytes = getButeArray(this.inserBill.execute(Mapper.map(request)).substring(7));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    private byte[] getButeArray(String fileName) throws IOException {
        File initialFile = new File(fileName);
        InputStream target = new FileInputStream(initialFile);
        byte[] bytes = IOUtils.toByteArray(target);
        target.close();
        return bytes;
    }
}
