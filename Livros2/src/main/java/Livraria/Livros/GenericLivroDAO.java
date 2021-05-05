package Livraria.Livros;

import java.util.List;


// Busca todos os valores e retorna todas as informações e modificações feitas.
public interface GenericLivroDAO {

    int save(Livro livro);

    int update(Livro livro);

    int remove(Long id);

    List<Livro> findAll();
}


