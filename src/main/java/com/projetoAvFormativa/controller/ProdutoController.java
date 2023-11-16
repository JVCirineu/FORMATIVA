package com.projetoAvFormativa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoAvFormativa.entities.Produto;
import com.projetoAvFormativa.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag (name = "Produtos", description = "API REST DE GERNECIAMENTO DE PRODUTOS AV FORMATIVA")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	private final ProdutoService produtoService;

	@Autowired
	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@GetMapping("/{id}")
	@Operation (summary = "Localiza produto por ID")
	public ResponseEntity<Produto> buscaProdutoControlId(@PathVariable Long id){
		Produto produto = produtoService.buscaProdutoId(id);
		if(produto != null) {
			return ResponseEntity.ok(produto);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	@Operation (summary = "Apresenta todos os produtos")
	public ResponseEntity<List<Produto>> buscaTodosProdutoControl(){
		List<Produto> Produtos = produtoService.buscaTodosProdutos();
		return ResponseEntity.ok(Produtos);
	}

	@PostMapping
	@Operation (summary = "Cadastra um Produto")
	public ResponseEntity<Produto> salvaProdutosControl(@RequestBody @Valid Produto produto){
		Produto salvaProduto = produtoService.salvaProduto(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaProduto);
	}

	@PutMapping("/{id}")
	@Operation (summary = "Altera um Produto")
	public ResponseEntity<Produto> alteraProdutoControlId(@PathVariable Long id,@RequestBody @Valid Produto produto ){
		Produto alteraProduto = produtoService.alterarProduto(id, produto);
		if(alteraProduto != null) {
			return ResponseEntity.ok(produto);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}")
	@Operation (summary = "Apaga um Produto")
	public ResponseEntity<Produto> apagaProdutoControl(@PathVariable Long id){
		boolean apagar = produtoService.apagarProduto(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {	
			return ResponseEntity.notFound().build();
		}
	}
}

