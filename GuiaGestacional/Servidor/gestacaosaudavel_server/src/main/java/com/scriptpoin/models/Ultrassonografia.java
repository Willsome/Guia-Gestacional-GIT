package com.scriptpoin.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Calendar;

@Entity
public class Ultrassonografia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long solicitacao;

    private Long numeroConsultaSolicitacao;
    private Long numeroConsultaResultado;

    private Calendar data;
    //    private Calendar igDum;
    //    private Calendar igUsg;
    private int igDumSemanas;
    private int igDumDias;
    private int igUsgSemanas;
    private int igUsgDias;
    private int pesoFetal;
    private String placenta;
    private Double liquidoAmniotico;

}
