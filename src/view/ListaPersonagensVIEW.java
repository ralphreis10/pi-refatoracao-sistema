package view;

import controller.PersonagemController;
import java.awt.Font;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Personagem;

public class ListaPersonagensVIEW extends JFrame {

    private final PersonagemController controller;
    private JTable tabela;
    private JTextField txtFiltroNome;
    private JComboBox<String> cbRaca;
    private JButton btnFiltrar;
    private JButton btnLimpar;
    private JButton btnVoltar;

    public ListaPersonagensVIEW() {
        this.controller = new PersonagemController();

        setTitle("Lista de Personagens");
        setSize(700, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        criarComponentes();
        carregarTabela("", "Todas");
    }

    private void criarComponentes() {
        Font fonte = new Font("Arial", Font.PLAIN, 15);

        JLabel lblFiltroNome = new JLabel("Filtrar por nome:");
        lblFiltroNome.setBounds(20, 20, 130, 25);
        lblFiltroNome.setFont(fonte);
        add(lblFiltroNome);

        txtFiltroNome = new JTextField();
        txtFiltroNome.setBounds(150, 20, 180, 28);
        txtFiltroNome.setFont(fonte);
        add(txtFiltroNome);

        JLabel lblFiltroRaca = new JLabel("Filtrar por raça:");
        lblFiltroRaca.setBounds(350, 20, 120, 25);
        lblFiltroRaca.setFont(fonte);
        add(lblFiltroRaca);

        cbRaca = new JComboBox<>(new String[]{"Todas", "Anão", "Elfo", "Bruxo", "Humano"});
        cbRaca.setBounds(470, 20, 140, 28);
        cbRaca.setFont(fonte);
        add(cbRaca);

        btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setBounds(150, 65, 110, 32);
        btnFiltrar.setFont(fonte);
        add(btnFiltrar);

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(280, 65, 110, 32);
        btnLimpar.setFont(fonte);
        add(btnLimpar);

        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(410, 65, 110, 32);
        btnVoltar.setFont(fonte);
        add(btnVoltar);

        tabela = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabela.setFont(fonte);
        tabela.setRowHeight(24);
        tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 120, 640, 220);
        add(scroll);

        btnFiltrar.addActionListener(e -> carregarTabela(txtFiltroNome.getText(), cbRaca.getSelectedItem().toString()));
        btnLimpar.addActionListener(e -> {
            txtFiltroNome.setText("");
            cbRaca.setSelectedIndex(0);
            carregarTabela("", "Todas");
        });
        btnVoltar.addActionListener(e -> dispose());
    }

    private void carregarTabela(String nome, String raca) {
        String[] colunas = {"ID", "Nome", "Vida", "Raça", "Idade"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

        List<Personagem> lista = controller.filtrar(nome, raca);
        for (Personagem p : lista) {
            modelo.addRow(new Object[]{p.getId(), p.getNome(), p.getVida(), p.getRaca(), p.getIdade()});
        }

        tabela.setModel(modelo);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ListaPersonagensVIEW().setVisible(true));
    }
}
