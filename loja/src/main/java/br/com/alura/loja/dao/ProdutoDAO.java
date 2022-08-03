package br.com.alura.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.model.Produto;

public class ProdutoDAO {
	
	private EntityManager entityManager;

	public ProdutoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void cadastrar(Produto produto) {
		this.entityManager.persist(produto);
	}
	
	public void atualizar(Produto produto) {
	    this.entityManager.merge(produto);
	}
	
	public void remover(Produto produto) {
		produto = entityManager.merge(produto);
	    this.entityManager.remove(produto);
	}
	
	public Produto buscarPorId(Integer id) {
		return entityManager.find(Produto.class, id);
	}
	
	public List<Produto> buscarTodos() {
		String jpql = "SELECT p FROM Produto p";
		return entityManager.createQuery(jpql, Produto.class).getResultList();
	}
	
	public List<Produto> buscarPorNome(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
		return entityManager.createQuery(jpql, Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	
	public List<Produto> buscarPorNomeCategoria(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
		return entityManager.createQuery(jpql, Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}
	
	public Double buscarPrecoProduto(String nome) {
		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
		return entityManager.createQuery(jpql, Double.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}

}
