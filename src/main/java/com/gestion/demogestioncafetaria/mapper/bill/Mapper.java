package com.gestion.demogestioncafetaria.mapper.bill;

import com.gestion.demogestioncafetaria.entity.Bill;
import com.gestion.demogestioncafetaria.resource.bill.BillPfdRequest;
import com.gestion.demogestioncafetaria.resource.bill.BillRequest;
import com.gestion.demogestioncafetaria.resource.bill.BillResponse;
import com.gestion.demogestioncafetaria.utils.CafeUtils;


public class Mapper {

    private Mapper() {}

    public static Bill map(BillRequest request, String user) {
        return Bill.builder()
                .uuid(CafeUtils.getUUid())
                .name(request.name())
                .email(request.email())
                .contact(request.contact())
                .payementMethod(request.paymentMethod())
                .productDetail(request.productDetail())
                .total(request.total())
                .createBy(user)
                .build();
    }

    public static BillRequest map(BillPfdRequest request){
        return new BillRequest(
                request.name(),
                request.email(),
                request.contact(),
                request.paymentMethod(),
                request.total(),
                request.productDetail()
        );
    }

    public static BillResponse map(Bill bill){
        return new BillResponse(
                bill.getId(),
                bill.getName(),
                bill.getEmail(),
                bill.getContact(),
                bill.getPayementMethod(),
                bill.getTotal(),
                bill.getProductDetail(),
                bill.getCreateBy()
        );
    }


}
