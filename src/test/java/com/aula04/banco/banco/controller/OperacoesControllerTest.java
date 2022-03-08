package com.aula04.banco.banco.controller;


import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.dto.RequestDeposito;
import com.aula04.banco.banco.dto.RequestSaque;
import com.aula04.banco.banco.model.Cliente;
import com.aula04.banco.banco.service.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.UUID;

public class OperacoesControllerTest {

    OperacoesController operacoesController = new OperacoesController();
    ClienteService clienteService = new ClienteService();


    @Test
    void depositarValorSuccess() throws Exception {

        RequestCliente requestCliente = new RequestCliente(
                "cinthia",
                "cinthiaqcarsoso@teste.com",
                "44934586719",
                "54353",
                3
        );

        Cliente cliente = clienteService.cadastraCliente(requestCliente);
        Double valorAnterior = cliente.getContas().get(0).getSaldo();

        Double valorAdicional = 100.00;
        RequestDeposito requestDeposito = new RequestDeposito();
        requestDeposito.setValor(valorAdicional);
        requestDeposito.setConta(cliente.getContas().get(0).getId());

        operacoesController.deposita(cliente.getId(), requestDeposito);

        Cliente cliente1 = clienteService.detalhesCliente(cliente.getId());

        Assertions.assertEquals(valorAnterior + valorAdicional, cliente1.getContas().get(0).getSaldo());
    }

    @Test
    void depositarValorThrowsException() {
        RequestCliente requestCliente = new RequestCliente(
                "cinthia",
                "cinthiaqcarsoso@teste.com",
                "44934586719",
                "54353",
                3
        );

        Cliente cliente = clienteService.cadastraCliente(requestCliente);
        UUID conta = UUID.randomUUID();

        RequestDeposito requestDeposito = new RequestDeposito();
        requestDeposito.setValor(100.00);
        requestDeposito.setConta(conta);

        Assertions.assertThrows(Exception.class, ()-> operacoesController.deposita(cliente.getId(), requestDeposito));
    }

    @Test
    void sacarValorSuccess() throws Exception {

        RequestCliente requestCliente = new RequestCliente(
                "cinthia",
                "cinthiaqcarsoso@teste.com",
                "44934586719",
                "54353",
                3
        );

        Cliente cliente = clienteService.cadastraCliente(requestCliente);
        Double valorAnterior = cliente.getContas().get(0).getSaldo();

        Double valorRetirado = 100.00;
        RequestSaque requestSaque = new RequestSaque();
        requestSaque.setValor(valorRetirado);
        requestSaque.setConta(cliente.getContas().get(0).getId());

        operacoesController.sacar(cliente.getId(), requestSaque);

        Cliente cliente1 = clienteService.detalhesCliente(cliente.getId());

        Assertions.assertEquals(valorAnterior - valorRetirado, cliente1.getContas().get(0).getSaldo());
    }

    @Test
    void sacarValorThrowsException() {
        RequestCliente requestCliente = new RequestCliente(
                "cinthia",
                "cinthiaqcarsoso@teste.com",
                "44934586719",
                "54353",
                3
        );

        Cliente cliente = clienteService.cadastraCliente(requestCliente);
        UUID conta = UUID.randomUUID();

        RequestSaque requestSaque = new RequestSaque();
        requestSaque.setValor(100.00);
        requestSaque.setConta(conta);

        Assertions.assertThrows(Exception.class, ()-> operacoesController.sacar(conta, requestSaque));
    }
}
