package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@Data
public class Mail {


    private final String mailTo;
    private final String subject;
    private final String message;
    private final String ToCc;
}
