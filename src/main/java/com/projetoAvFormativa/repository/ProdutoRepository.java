package com.projetoAvFormativa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoAvFormativa.entities.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Long> {

}
