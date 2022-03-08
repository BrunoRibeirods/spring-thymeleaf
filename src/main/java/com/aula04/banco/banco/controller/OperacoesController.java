package com.aula04.banco.banco.controller;

import com.aula04.banco.banco.BancoAula04Application;
import com.aula04.banco.banco.dto.RequestDeposito;
import com.aula04.banco.banco.dto.RequestSaque;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/operacoes")
public class OperacoesController {

    @PatchMapping("/deposita")
    public ResponseEntity deposita(
            @RequestHeader("id") UUID id,
            @RequestBody RequestDeposito requestDeposito
    ) throws Exception {
        BancoAula04Application.bancoCliente.deposita(id, requestDeposito);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(RuntimeException.class)
    @PatchMapping("/sacar")
    public ResponseEntity sacar (
            @RequestHeader("id") UUID id,
            @RequestBody RequestSaque requestSaque
    ) throws Exception {
        BancoAula04Application.bancoCliente.sacar(id, requestSaque);
        return ResponseEntity.ok().build();
    }

}
