package Livraria.Livros;

import Livraria.Livros.GenericLivroDAO;
import Livraria.Livros.LivroDAO;
import Livraria.Livros.Livro;

import java.util.List;


//Recebe como herança as informações do DAO e atribui a Classe para simplificar o sistema.
@SuppressWarnings("unused")
public class LivroFacade {

    private GenericLivroDAO dao;

    public LivroFacade() {
        this.dao = new LivroDAO();
    }

    public int save(Livro livro) {
        return dao.save(livro);
    }

    public int update(Livro livro) {
        return dao.update(livro);
    }

    public int remove(Long id) {
        return dao.remove(id);
    }

    public List<Livro> findAll() {
        return dao.findAll();
    }
}
