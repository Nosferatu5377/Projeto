package Livraria.Livros;


import Livraria.Livros.Livro;

import javax.swing.table.AbstractTableModel;
import java.util.List;


//executa e descobre seus atributos e age sobre eles.
@SuppressWarnings({ "unused", "serial" })
//Implementa metodos não diretamente relacionados ao armazenamento.
public class LivroTableModel extends AbstractTableModel {

    private static final int COL_ID = 0;
    private static final int COL_EDITORA = 1;
    private static final int COL_TITULO = 2;
    private static final int COL_ISBN = 3;

    private List<Livro> valores;

    public LivroTableModel(List<Livro> valores) {
        this.valores = valores;
    }

    public int getRowCount() {
        return valores.size();
    }

    public int getColumnCount() {
        return 4;
    }

    //Anotação de Campo e Metodos
    public Object getValueAt(int rowIndex, int columnIndex) {
        Livro livro = valores.get(rowIndex);
        if (columnIndex == COL_ID) {
            return livro.getId();
        } else if (columnIndex == COL_EDITORA) {
            return livro.getEditora();
        } else if (columnIndex == COL_TITULO) {
            return livro.getTitulo();
        } else if (columnIndex == COL_ISBN) {
            return livro.getIsbn();
        }
        return null;
    }
//alterar o nome da coluna
    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_ID:
                coluna = "Código";
                break;
            case COL_EDITORA:
                coluna = "Editora";
                break;
            case COL_TITULO:
                coluna = "Título";
                break;
            case COL_ISBN:
                coluna = "ISBN";
                break;
            default:
                throw new IllegalArgumentException("Coluna inválida!");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == COL_ID) {
            return Long.class;
        } else if (columnIndex == COL_EDITORA) {
            return String.class;
        } else if (columnIndex == COL_TITULO) {
            return String.class;
        } else if (columnIndex == COL_ISBN) {
            return String.class;
        }
        return null;
    }

    //Recebe os valores e os retorna
    public Livro get(int row) {
        return valores.get(row);
    }
}
