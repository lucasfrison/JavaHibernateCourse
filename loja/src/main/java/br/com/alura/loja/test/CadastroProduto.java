package br.com.alura.loja.test;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDAO;
import br.com.alura.loja.dao.ProdutoDAO;
import br.com.alura.loja.model.Categoria;
import br.com.alura.loja.model.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroProduto {

	public static void main(String[] args) {
		
		//cadastrarProduto();
		EntityManager entityManager = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
		
		Produto produto = produtoDAO.buscarPorId(1);
		System.out.println(produto.getPreco());
		
		List<Produto> todosList = produtoDAO.buscarPorNomeCategoria("celulares");
		todosList.forEach(p -> System.out.println(p.getNome()));
		
		Double precoDouble = produtoDAO.buscarPrecoProduto("Asus Zenforne");
		System.out.println(precoDouble);

	}

	public static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Xiaomi", "Novo", 1000.00, celulares );
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
		CategoriaDAO categoriaDAO = new CategoriaDAO(entityManager);
		
		entityManager.getTransaction().begin();
		
		categoriaDAO.cadastrar(celulares);
		produtoDAO.cadastrar(celular);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
