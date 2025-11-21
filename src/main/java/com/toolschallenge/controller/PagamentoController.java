package com.toolschallenge.controller;

import com.toolschallenge.domain.Transacao;
import com.toolschallenge.dto.TransacaoRequestDTO;
import com.toolschallenge.dto.TransacaoResponseDTO;
import com.toolschallenge.service.PagamentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pagamentos")
@Tag(name = "Pagamentos", description = "Operações relacionadas a transações de pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @Operation(
            summary = "Criar pagamento",
            description = "Autoriza uma nova transação de pagamento.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Pagamento criado com sucesso",
                            content = @Content(schema = @Schema(implementation = TransacaoResponseDTO.class)))
            }
    )
    @PostMapping
    public ResponseEntity<TransacaoResponseDTO> pagar(
            @Valid @RequestBody TransacaoRequestDTO request) {
        TransacaoResponseDTO response = pagamentoService.criarPagamento(request);
        var location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(response.getId())
            .toUri();
        return ResponseEntity.created(location).body(response);
    }

     @Operation(
            summary = "Listar pagamentos",
            description = "Retorna todas as transações registradas.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            }
    )
    @GetMapping
    public ResponseEntity<List<TransacaoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(pagamentoService.listarTodos());
    }

    @Operation(
            summary = "Consultar pagamento por ID",
            description = "Retorna os detalhes de uma transação específica.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Transação encontrada",
                            content = @Content(schema = @Schema(implementation = TransacaoResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Transação não encontrada")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<TransacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<Transacao> transacao = pagamentoService.buscarPorId(id);
        if(transacao.isEmpty()) {
               return ResponseEntity.notFound().build();
        } 
        return ResponseEntity.ok(pagamentoService.toResponseDTO(transacao.get()));
    }

    @Operation(
            summary = "Estornar pagamento",
            description = "Realiza o estorno de uma transação previamente autorizada.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Estorno realizado com sucesso",
                            content = @Content(schema = @Schema(implementation = TransacaoResponseDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Transação não encontrada")
            }
    )
    @GetMapping("/estornar/{id}")
    public ResponseEntity<TransacaoResponseDTO> estornarPagamento(@PathVariable Long id) {
        Optional<Transacao> transacao = pagamentoService.buscarPorId(id);
        if(transacao.isEmpty()) {
               return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pagamentoService.estornarPagamento(transacao.get()));
    }
}
