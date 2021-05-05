package Livraria.Livros;

import Livraria.Livros.Livro;
import Livraria.Livros.LivroFacade;

import java.util.List;


//Puxa as informações da Facade e recebe e realiza as operações.
@SuppressWarnings("unused")
public class LivroController {

    private LivroFacade facade;

    public LivroController() {
        this.facade = new LivroFacade();
    }

    public int addLivro(Livro livro) {
        return facade.save(livro);
    }

    public int alterarLivro(Livro livro) {
        return facade.update(livro);
    }

    public int excluirLivro(Long id) {
        return facade.remove(id);
    }

    public List<Livro> findLivros() {
        return facade.findAll();
    }
}

