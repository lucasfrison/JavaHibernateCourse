package br.com.alura.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.model.Categoria;

public class CategoriaDAO {
	
	private EntityManager entityManager;

	public CategoriaDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void cadastrar(Categoria categoria) {
		this.entityManager.persist(categoria);
	}
	
	public void atualizar(Categoria categoria) {
	    this.entityManager.merge(categoria);
	}
	
	public void remover(Categoria categoria) {
		categoria = entityManager.merge(categoria);
	    this.entityManager.remove(categoria);
	}
	
	public Categoria buscarPorId(Integer id) {
		return entityManager.find(Categoria.class, id);
	}
	
	public List<Categoria> buscarTodos() {
		String jpql = "SELECT c FROM Categoria c";
		return entityManager.createQuery(jpql, Categoria.class).getResultList();
	}
	
	public List<Categoria> buscarPorNome(String nome) {
		String jpql = "SELECT c FROM Categoria c WHERE c.nome = :nome";
		return entityManager.createQuery(jpql, Categoria.class)
				.setParameter("nome", nome)
				.getResultList();
	}

}
