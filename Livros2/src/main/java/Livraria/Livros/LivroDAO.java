package Livraria.Livros;

import Livraria.Livros.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@SuppressWarnings("unused")

// gerenciamento da conecção para obter e armazer as informações.
public class LivroDAO implements GenericLivroDAO {

    private static final Logger LOGGER = Logger.getLogger(LivroDAO.class.getName());

    private static final String SQL_INSERT =
            "insert into LIVROS (EDITORA, TITULO, ISBN) values (?,?,?)";

    private static final String SQL_UPDATE =
            "update LIVROS set EDITORA = ?, TITULO = ?, ISBN = ? where ID = ?";

    private static final String SQL_REMOVE =
            "delete from LIVROS where ID = ?";

    private static final String SQL_FIND_ALL =
            "select * from LIVROS";


    public int save(Livro livro) {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstm = null;
        int result = 0;
        try {
            pstm = conn.prepareStatement(SQL_INSERT);
            pstm.setString(1, livro.getEditora());
            pstm.setString(2, livro.getTitulo());
            pstm.setString(3, livro.getIsbn());
            result = pstm.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Um erro ocorreu ao tentar salvar o registro.", e);
        } finally {
            DBConnection.close(conn, pstm, null);
        }
        return result;
    }

    public int update(Livro livro) {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstm = null;
        int result = 0;
        try {
            pstm = conn.prepareStatement(SQL_UPDATE);
            pstm.setString(1, livro.getEditora());
            pstm.setString(2, livro.getTitulo());
            pstm.setString(3, livro.getIsbn());
            pstm.setLong(4, livro.getId());
            result = pstm.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Um erro ocorreu ao tentar editar o registro.", e);
        } finally {
            DBConnection.close(conn, pstm, null);
        }
        return result;
    }

    public int remove(Long id) {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstm = null;
        int result = 0;
        try {
            pstm = conn.prepareStatement(SQL_REMOVE);
            pstm.setLong(1, id);
            result = pstm.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Um erro ocorreu ao tentar excluir o registro.", e);
        } finally {
            DBConnection.close(conn, pstm, null);
        }
        return result;
    }

    public List<Livro> findAll() {
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstm = null;
        List<Livro> livros = new ArrayList<Livro>();
        ResultSet rs = null;
        try {
            pstm = conn.prepareStatement(SQL_FIND_ALL);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getLong("ID"));
                livro.setEditora(rs.getString("EDITORA"));
                livro.setTitulo(rs.getString("TITULO"));
                livro.setIsbn(rs.getString("ISBN"));

                livros.add(livro);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Um erro ocorreu ao tentar localizar os registros.", e);
        } finally {
            DBConnection.close(conn, pstm, rs);
        }
        return livros;
    }
}

