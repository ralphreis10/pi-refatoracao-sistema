package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class MainMenuVIEW extends JFrame {

    private JButton btnCadastro;
    private JButton btnLista;
    private JButton btnSair;

    public MainMenuVIEW() {
        setTitle("Sistema de Personagens - PI Refatorado");
        setSize(430, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lbl = new JLabel("Bem-vindo ao Sistema de Personagens");
        lbl.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lbl, gbc);

        btnCadastro = new JButton("Cadastro / Edição");
        btnCadastro.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(btnCadastro, gbc);

        btnLista = new JButton("Listagem / Filtros");
        btnLista.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridy = 2;
        add(btnLista, gbc);

        btnSair = new JButton("Sair");
        btnSair.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridy = 3;
        add(btnSair, gbc);

        btnCadastro.addActionListener(e -> new CadastroPersonagemVIEW().setVisible(true));
        btnLista.addActionListener(e -> new ListaPersonagensVIEW().setVisible(true));
        btnSair.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenuVIEW().setVisible(true));
    }
}
