package com.example.curso.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.curso.entities.PagamentoBoleto;

@Service
public class BoletoService {

	public static void preencherPagamentoComBoleto(PagamentoBoleto pagto, Date instante) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instante);
		cal.add(Calendar.DAY_OF_WEEK, 7);
		pagto.setDataVencimento(cal.getTime());
	}
}
