package Livraria.Livros;

import Livraria.Livros.LivroController;
import Livraria.Livros.Livro;
import Livraria.Livros.LivroCellRenderer;
import Livraria.Livros.LivroTableModel;
//import sun.deploy.panel.JavaPanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;



//Implementa caracteristicas gráficos da interface e retorna e os atributos e quando são feitas as modificações.
@SuppressWarnings({ "unused", "serial" })
public class LivroForm extends JFrame {

    private JLabel lbEditora, lbTitulo, lbIsbn;
    private JTextField txtEditora, txtTitulo, txtIsbn;
    private JPanel panelAdd, panelTable, panelButtons;
    private JButton btnNew, btnSave, btnUpdate, btnRemove, btnCancel;
    private JTable table;
    private JScrollPane scrollPane;
    private List<Livro> livroList;
    private Long idLivro;

    public LivroForm() throws HeadlessException {
        super("Cadastro de Livros");
        setContentPane(new JPanel());
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelAdd = new JPanel(new MigLayout());
        panelAdd.setBorder(BorderFactory.createTitledBorder("Adicionar Livros"));
        panelAdd.setBounds(5, 0, 480, 100);

        lbEditora = new JLabel("Editora");
        lbTitulo = new JLabel("Título");
        lbIsbn = new JLabel("ISBN");

        txtEditora = new JTextField(50);
        txtTitulo = new JTextField(50);
        txtIsbn = new JTextField(15);

        panelAdd.add(lbEditora);
        panelAdd.add(txtEditora, "span, growx");

        panelAdd.add(lbTitulo);
        panelAdd.add(txtTitulo, "span, growx");

        panelAdd.add(lbIsbn);
        panelAdd.add(txtIsbn, "growx");

        panelButtons = new JPanel(new MigLayout());
        panelButtons.setBorder(BorderFactory.createEtchedBorder());
        panelButtons.setBounds(5, 105, 480, 40);

        ClassLoader loader = getClass().getClassLoader();
        btnNew = new JButton(new ImageIcon(loader.getResource("img/new.png")));
        btnSave = new JButton(new ImageIcon(loader.getResource("img/save.png")));
        btnCancel = new JButton(new ImageIcon(loader.getResource("img/cancel.png")));
        btnRemove = new JButton(new ImageIcon(loader.getResource("img/trash.png")));
        btnUpdate = new JButton(new ImageIcon(loader.getResource("img/edit.png")));

        panelButtons.add(btnNew, "gapleft 90");
        panelButtons.add(btnCancel);
        panelButtons.add(btnSave, "gap unrelated");
        panelButtons.add(btnUpdate, "gap unrelated");
        panelButtons.add(btnRemove);

        panelTable = new JPanel(new MigLayout());
        panelTable.setBorder(BorderFactory.createTitledBorder("Lista de Livros"));
        panelTable.setBounds(5, 150, 480, 240);

        table = new JTable();

        scrollPane = new JScrollPane(table);

        panelTable.add(scrollPane);

        refreshTable();
        enableFields(false);

        add(panelAdd);
        add(panelButtons);
        add(panelTable);
        setMinimumSize(new Dimension(500,420));
        setVisible(true);

        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSaveLivro();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancelar();
            }
        });

        btnNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onNovoLivro();
            }
        });

        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onRemoverLivro();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onAlterarLivro();
            }
        });
    }

    private void onRemoverLivro() {
        int rowIndex = table.getSelectedRow();

        if (rowIndex == -1) {
            JOptionPane.showMessageDialog(this, "Selecione o livro a ser removido!");
            return;
        }

        Livro livro = new LivroTableModel(livroList).get(rowIndex);

        int confirm = JOptionPane.showConfirmDialog(this, "Confirmar exclusão?", "Excluir Livro", JOptionPane.YES_NO_OPTION);

        if (confirm != 0) {
            return;
        }

        int result = new LivroController().excluirLivro(livro.getId());

        if (result == 1) {
            JOptionPane.showMessageDialog(this, "Valor removido com sucesso!");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Tente novamente!");
        }
    }

    private void onAlterarLivro() {
        int rowIndex = table.getSelectedRow();

        if (rowIndex == -1) {
            JOptionPane.showMessageDialog(this, "Selecione o livro a ser alterado!");
            return;
        }

        Livro livro = new LivroTableModel(livroList).get(rowIndex);

        idLivro = livro.getId();

        txtEditora.setText(livro.getEditora());
        txtTitulo.setText(livro.getTitulo());
        txtIsbn.setText(livro.getIsbn());

        enableFields(true);
    }

    private void onNovoLivro() {
        enableFields(true);
    }

    private void onSaveLivro() {
        Livro livro = new Livro();

        if (txtEditora.getText().length() > 0 && txtTitulo.getText().length() > 0 && txtIsbn.getText().length() > 0) {
            livro.setEditora(txtEditora.getText());
            livro.setTitulo(txtTitulo.getText());
            livro.setIsbn(txtIsbn.getText());
        } else {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!");
            return;
        }

        int result = 0;
        if (idLivro == null) {
            result = new LivroController().addLivro(livro);
        } else {
            livro.setId(idLivro);
            result = new LivroController().alterarLivro(livro);
            idLivro = null;
        }

        if (result == 1) {
            JOptionPane.showMessageDialog(this, "Valor inserido com sucesso!");
            enableFields(false);
            onCancelar();
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Tente novamente!");
        }
    }

    private void onCancelar() {
        txtEditora.setText("");
        txtTitulo.setText("");
        txtIsbn.setText("");
        enableFields(false);
    }

    private void enableFields(boolean b) {
        txtEditora.setEnabled(b);
        txtTitulo.setEnabled(b);
        txtIsbn.setEnabled(b);
    }

    private void refreshTable() {
        livroList = new LivroController().findLivros();
        if (livroList != null) {
            table.setModel(new LivroTableModel(livroList));
            table.setDefaultRenderer(Object.class, new LivroCellRenderer());
        }
    }
}

