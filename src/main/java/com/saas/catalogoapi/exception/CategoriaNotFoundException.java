package com.saas.catalogoapi.exception;

/**
 * Exceção lançada quando uma categoria não é encontrada pelo ID informado.
 */
public class CategoriaNotFoundException extends RuntimeException {

    public CategoriaNotFoundException(Long id) {
        super("Categoria não encontrada com o ID: " + id);
    }
}