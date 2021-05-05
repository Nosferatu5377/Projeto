package Livraria.Livros;


//Classe dos Atributos dos objetos.
public class Livro {
    private Long id;
    private String editora;
    private String titulo;
    private String isbn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override  // Extende e/ou modifica os métodos sobrepostos.
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", editora='" + editora + '\'' +
                ", titulo='" + titulo + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
