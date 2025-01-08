package com.gestion.demogestioncafetaria.mapper.bill;

import com.gestion.demogestioncafetaria.entity.Bill;
import com.gestion.demogestioncafetaria.resource.bill.BillRequest;
import com.gestion.demogestioncafetaria.utils.CafeUtils;

public class Mapper {

    private Mapper(){}

    public static Bill map(BillRequest request, String user){
        return Bill.builder()
                .uuid(CafeUtils.getUUid())
                .name(request.name())
                .email(request.email())
                .contact(request.contact())
                .payementMethod(request.payementMethod())
                .total(request.total())
                .productDetail(request.productDetail())
                .createBy(user)
                .build();
    }

}
