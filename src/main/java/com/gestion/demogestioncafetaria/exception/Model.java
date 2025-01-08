package com.gestion.demogestioncafetaria.exception;


import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
public class Model {
    private String message;
    private int code;
    private Instant date;

}
