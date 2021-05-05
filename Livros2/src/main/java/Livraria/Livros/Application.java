package Livraria.Livros;


import Livraria.Livros.DBConnection;
import Livraria.Livros.LivroForm;

@SuppressWarnings("unused")
public class Application {
    public static void main(String[] args) {
        DBConnection.createTable(); // Puxa a classe do banco de dados que conecta com a tabela.
        new LivroForm();    // Carrega a interface construida do codigo ao mesmo tempo que interage com elas. 
    }
}