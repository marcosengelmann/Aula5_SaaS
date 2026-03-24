package com.saas.catalogoapi.service;

import com.saas.catalogoapi.dto.CategoriaRequest;
import com.saas.catalogoapi.dto.CategoriaResponse;
import com.saas.catalogoapi.exception.CategoriaNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Service responsável pelas regras de negócio das categorias.
 * Os dados ficam em memória (sem banco de dados).
 *
 * @Service faz o Spring gerenciar esta classe como um componente injetável.
 */
@Service
public class CategoriaService {

    // Lista que simula o banco de dados em memória
    private final List<CategoriaResponse> categorias = new ArrayList<>();

    // Gerador de IDs automático (começa em 1 e incrementa a cada novo item)
    private final AtomicLong contadorId = new AtomicLong(1);

    // Construtor: inicializa com 2 categorias de exemplo
    public CategoriaService() {
        categorias.add(new CategoriaResponse(contadorId.getAndIncrement(), "Eletrônicos", "Dispositivos eletrônicos em geral"));
        categorias.add(new CategoriaResponse(contadorId.getAndIncrement(), "Periféricos", "Acessórios e periféricos para computadores"));
    }

    /**
     * Retorna todas as categorias cadastradas.
     */
    public List<CategoriaResponse> listarTodas() {
        return categorias;
    }

    /**
     * Busca uma categoria pelo ID.
     * Lança CategoriaNotFoundException se não encontrar.
     */
    public CategoriaResponse buscarPorId(Long id) {
        return categorias.stream()
                .filter(c -> c.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }

    /**
     * Cria uma nova categoria e adiciona na lista em memória.
     */
    public CategoriaResponse criar(CategoriaRequest request) {
        CategoriaResponse nova = new CategoriaResponse(
                contadorId.getAndIncrement(),
                request.nome(),
                request.descricao()
        );
        categorias.add(nova);
        return nova;
    }
}