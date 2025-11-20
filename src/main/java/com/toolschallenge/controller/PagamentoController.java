package com.toolschallenge.controller;

import com.toolschallenge.dto.TransacaoRequestDTO;
import com.toolschallenge.dto.TransacaoResponseDTO;
import com.toolschallenge.service.PagamentoService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<TransacaoResponseDTO> pagar(
            @Valid @RequestBody TransacaoRequestDTO request) {
        TransacaoResponseDTO response = pagamentoService.criarPagamento(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TransacaoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(pagamentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pagamentoService.buscarPorId(id));
    }

    @GetMapping("/estornar/{id}")
    public ResponseEntity<TransacaoResponseDTO> estornarPagamento(@PathVariable Long id) {
        return ResponseEntity.ok(pagamentoService.estornarPagamento(id));
    }
}
