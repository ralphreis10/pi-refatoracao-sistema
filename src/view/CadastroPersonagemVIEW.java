package view;

import controller.PersonagemController;
import exception.RegraNegocioException;
import java.awt.Font;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Personagem;

public class CadastroPersonagemVIEW extends JFrame {

    private final PersonagemController controller;
    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtVida;
    private JTextField txtIdade;
    private JComboBox<String> cbRaca;
    private JTable tabela;
    private DefaultTableModel modelo;
    private JButton btnSalvar;
    private JButton btnEditar;
    private JButton btnExcluir;
    private JButton btnLimpar;
    private JButton btnVoltar;

    public CadastroPersonagemVIEW() {
        this.controller = new PersonagemController();

        setTitle("Cadastro e Edição de Personagens");
        setSize(760, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        criarComponentes();
        carregarTabela();
    }

    private void criarComponentes() {
        Font fonte = new Font("Arial", Font.PLAIN, 15);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20, 20, 100, 25);
        lblId.setFont(fonte);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(110, 20, 80, 28);
        txtId.setEditable(false);
        txtId.setFont(fonte);
        add(txtId);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 60, 100, 25);
        lblNome.setFont(fonte);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(110, 60, 230, 28);
        txtNome.setFont(fonte);
        add(txtNome);

        JLabel lblVida = new JLabel("Vida:");
        lblVida.setBounds(20, 100, 100, 25);
        lblVida.setFont(fonte);
        add(lblVida);

        txtVida = new JTextField();
        txtVida.setBounds(110, 100, 110, 28);
        txtVida.setFont(fonte);
        add(txtVida);

        JLabel lblIdade = new JLabel("Idade:");
        lblIdade.setBounds(20, 140, 100, 25);
        lblIdade.setFont(fonte);
        add(lblIdade);

        txtIdade = new JTextField();
        txtIdade.setBounds(110, 140, 110, 28);
        txtIdade.setFont(fonte);
        add(txtIdade);

        JLabel lblRaca = new JLabel("Raça:");
        lblRaca.setBounds(20, 180, 100, 25);
        lblRaca.setFont(fonte);
        add(lblRaca);

        cbRaca = new JComboBox<>(new String[]{"Anão", "Elfo", "Bruxo", "Humano"});
        cbRaca.setBounds(110, 180, 140, 28);
        cbRaca.setFont(fonte);
        add(cbRaca);

        btnSalvar = new JButton("Adicionar");
        btnSalvar.setBounds(380, 25, 150, 32);
        btnSalvar.setFont(fonte);
        add(btnSalvar);

        btnEditar = new JButton("Carregar Selecionado");
        btnEditar.setBounds(380, 70, 190, 32);
        btnEditar.setFont(fonte);
        add(btnEditar);

        btnExcluir = new JButton("Excluir Selecionado");
        btnExcluir.setBounds(380, 115, 190, 32);
        btnExcluir.setFont(fonte);
        add(btnExcluir);

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(380, 160, 120, 32);
        btnLimpar.setFont(fonte);
        add(btnLimpar);

        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(510, 160, 120, 32);
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
        scroll.setBounds(20, 240, 700, 190);
        add(scroll);

        btnSalvar.addActionListener(e -> salvarOuAtualizar());
        btnEditar.addActionListener(e -> carregarSelecionadoParaFormulario());
        btnExcluir.addActionListener(e -> excluirSelecionado());
        btnLimpar.addActionListener(e -> limparCampos());
        btnVoltar.addActionListener(e -> dispose());
    }

    private void carregarTabela() {
        String[] colunas = {"ID", "Nome", "Vida", "Raça", "Idade"};
        modelo = new DefaultTableModel(colunas, 0);

        List<Personagem> lista = controller.listar();
        for (Personagem p : lista) {
            modelo.addRow(new Object[]{p.getId(), p.getNome(), p.getVida(), p.getRaca(), p.getIdade()});
        }

        tabela.setModel(modelo);
    }

    private void salvarOuAtualizar() {
        try {
            Personagem personagem = lerFormulario();

            if (txtId.getText().trim().isEmpty()) {
                controller.adicionar(personagem);
                JOptionPane.showMessageDialog(this, "Personagem cadastrado com sucesso.");
            } else {
                personagem.setId(Integer.parseInt(txtId.getText()));
                controller.atualizar(personagem);
                JOptionPane.showMessageDialog(this, "Personagem atualizado com sucesso.");
            }

            limparCampos();
            carregarTabela();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vida e idade devem ser números válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (RegraNegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Regra de negócio", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar personagem: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Personagem lerFormulario() {
        String nome = txtNome.getText().trim();
        int vida = Integer.parseInt(txtVida.getText().trim());
        int idade = Integer.parseInt(txtIdade.getText().trim());
        String raca = cbRaca.getSelectedItem().toString();

        return new Personagem(nome, vida, raca, idade);
    }

    private void carregarSelecionadoParaFormulario() {
        int linha = tabela.getSelectedRow();

        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um personagem na tabela.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        txtId.setText(tabela.getValueAt(linha, 0).toString());
        txtNome.setText(tabela.getValueAt(linha, 1).toString());
        txtVida.setText(tabela.getValueAt(linha, 2).toString());
        cbRaca.setSelectedItem(tabela.getValueAt(linha, 3).toString());
        txtIdade.setText(tabela.getValueAt(linha, 4).toString());
        btnSalvar.setText("Salvar Alterações");
    }

    private void excluirSelecionado() {
        int linha = tabela.getSelectedRow();

        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um personagem para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(this,
                "Deseja realmente excluir o personagem selecionado?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            try {
                Integer id = Integer.parseInt(tabela.getValueAt(linha, 0).toString());
                controller.remover(id);
                JOptionPane.showMessageDialog(this, "Personagem excluído com sucesso.");
                limparCampos();
                carregarTabela();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir personagem: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtVida.setText("");
        txtIdade.setText("");
        cbRaca.setSelectedIndex(0);
        btnSalvar.setText("Adicionar");
        tabela.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CadastroPersonagemVIEW().setVisible(true));
    }
}
