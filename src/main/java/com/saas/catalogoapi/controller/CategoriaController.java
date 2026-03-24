package com.saas.catalogoapi.controller;

import com.saas.catalogoapi.dto.CategoriaRequest;
import com.saas.catalogoapi.dto.CategoriaResponse;
import com.saas.catalogoapi.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST que expõe os endpoints da API de categorias.
 */
@RestController
@RequestMapping("/api/v1/categorias")
@Tag(name = "Categorias", description = "Endpoints para gerenciamento de categorias do catálogo")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    /**
     * GET /api/v1/categorias
     * Retorna todas as categorias. Status 200.
     */
    @GetMapping
    @Operation(summary = "Listar todas as categorias", description = "Retorna a lista completa de categorias cadastradas")
    public ResponseEntity<List<CategoriaResponse>> listarTodas() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    /**
     * GET /api/v1/categorias/{id}
     * Retorna uma categoria pelo ID. Status 200 ou 404.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar categoria por ID", description = "Retorna uma única categoria pelo seu identificador")
    public ResponseEntity<CategoriaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    /**
     * POST /api/v1/categorias
     * Cria uma nova categoria. Status 201.
     */
    @PostMapping
    @Operation(summary = "Criar nova categoria", description = "Cadastra uma nova categoria no catálogo")
    public ResponseEntity<CategoriaResponse> criar(@RequestBody CategoriaRequest request) {
        CategoriaResponse criada = categoriaService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }
}