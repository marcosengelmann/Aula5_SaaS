package com.saas.catalogoapi.dto;

/**
 * DTO de saída com os dados de uma categoria retornada pela API.
 * Inclui o id, que é gerado pelo servidor.
 */
public record CategoriaResponse(
        Long id,
        String nome,
        String descricao
) { }