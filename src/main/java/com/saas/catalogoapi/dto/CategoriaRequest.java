
package com.saas.catalogoapi.dto;

/**
 * DTO de entrada para criação de uma nova categoria.
 * Record gera automaticamente construtor, getters, equals e toString.
 */
public record CategoriaRequest(
        String nome,
        String descricao
) { }