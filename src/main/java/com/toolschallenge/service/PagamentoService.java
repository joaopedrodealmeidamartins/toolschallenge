package com.toolschallenge.service;

import com.toolschallenge.domain.Descricao;
import com.toolschallenge.domain.FormaPagamento;
import com.toolschallenge.domain.Transacao;
import com.toolschallenge.domain.enums.StatusTransacao;
import com.toolschallenge.dto.*;
import com.toolschallenge.repository.TransacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PagamentoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Transactional
    public TransacaoResponseDTO criarPagamento(TransacaoRequestDTO request) {
        Transacao transacao = new Transacao();
        Descricao descricao = new Descricao();
        descricao.setDataHora(request.getDescricao().getDataHora());
        descricao.setEstabelecimento(request.getDescricao().getEstabelecimento());
        descricao.setValor(request.getDescricao().getValor());
        transacao.setDescricao(descricao);

        FormaPagamento forma = new FormaPagamento();
        forma.setTipo(request.getFormaPagamento().getTipo());
        forma.setParcelas(request.getFormaPagamento().getParcelas());
        transacao.setFormaPagamento(forma);

        transacao.setNumeroCartao(request.getCartao());
        String numero = request.getCartao().replaceAll("\\D", "");
        char ultimoDigito = numero.charAt(numero.length() - 1);
        boolean par = (ultimoDigito - '0') % 2 == 0;
        transacao.setStatus(par ? StatusTransacao.AUTORIZADO : StatusTransacao.NEGADO);

        Transacao salva = transacaoRepository.save(transacao);
        return toResponseDTO(salva);
    }

    @Transactional(readOnly = true)
    public List<TransacaoResponseDTO> listarTodos() {
        return transacaoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<Transacao> buscarPorId(Long id) {
        return transacaoRepository.findById(id);
    }

    @Transactional
    public TransacaoResponseDTO estornarPagamento(Transacao transacao) {
        transacao.setEstornado(true);
        transacao.setStatus(StatusTransacao.CANCELADO);

        return toResponseDTO(transacao);
    }

    public TransacaoResponseDTO toResponseDTO(Transacao transacao) {
        TransacaoResponseDTO dto = new TransacaoResponseDTO();
        dto.setId(transacao.getId());
        String numeroCartao = transacao.getNumeroCartao();
        String mascarado = numeroCartao.replaceAll(".(?=.{4})", "*");
        dto.setCartao(mascarado);

        DescricaoDTO descricaoDTO = new DescricaoDTO();
        descricaoDTO.setDataHora(transacao.getDescricao().getDataHora());
        descricaoDTO.setValor(transacao.getDescricao().getValor());
        descricaoDTO.setEstabelecimento(transacao.getDescricao().getEstabelecimento());
        descricaoDTO.setStatus(transacao.getStatus());
        descricaoDTO.setNsu(transacao.getDescricao().getNsu());
        descricaoDTO.setCodigoAutorizacao(transacao.getDescricao().getCodigoAutorizacao());
        dto.setDescricao(descricaoDTO);


        FormaPagamentoDTO formaDTO = new FormaPagamentoDTO();
        formaDTO.setTipo(transacao.getFormaPagamento().getTipo());
        formaDTO.setParcelas(transacao.getFormaPagamento().getParcelas());
        dto.setFormaPagamento(formaDTO);

        return dto;
    }
}
