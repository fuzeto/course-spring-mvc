package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

	@Autowired
	private ProdutoDAO produtoDAO;

	@RequestMapping("/")
	@Cacheable(value="produtosHome")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("home");
		List<Produto> produtos = produtoDAO.listar();
		modelAndView.addObject("produtos", produtos);

		return modelAndView;
	}
}
