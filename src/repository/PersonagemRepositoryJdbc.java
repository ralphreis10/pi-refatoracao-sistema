package repository;

import dao.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Personagem;

public class PersonagemRepositoryJdbc implements PersonagemRepository {

    @Override
    public List<Personagem> listarTodos() {
        String sql = "SELECT p.id, p.nome, p.vida, r.nome AS raca, p.idade " +
                     "FROM Personagem p INNER JOIN Raca r ON p.raca_id = r.id " +
                     "ORDER BY p.nome";
        List<Personagem> lista = new ArrayList<>();

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar personagens: " + e.getMessage(), e);
        }

        return lista;
    }

    @Override
    public List<Personagem> filtrar(String nome, String raca) {
        StringBuilder sql = new StringBuilder(
            "SELECT p.id, p.nome, p.vida, r.nome AS raca, p.idade " +
            "FROM Personagem p INNER JOIN Raca r ON p.raca_id = r.id WHERE 1=1 "
        );

        List<Object> params = new ArrayList<>();

        if (nome != null && !nome.trim().isEmpty()) {
            sql.append("AND LOWER(p.nome) LIKE ? ");
            params.add("%" + nome.trim().toLowerCase() + "%");
        }

        if (raca != null && !raca.trim().isEmpty() && !"Todas".equalsIgnoreCase(raca)) {
            sql.append("AND LOWER(r.nome) = ? ");
            params.add(raca.trim().toLowerCase());
        }

        sql.append("ORDER BY p.nome");

        List<Personagem> lista = new ArrayList<>();

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao filtrar personagens: " + e.getMessage(), e);
        }

        return lista;
    }

    @Override
    public Personagem buscarPorId(Integer id) {
        String sql = "SELECT p.id, p.nome, p.vida, r.nome AS raca, p.idade " +
                     "FROM Personagem p INNER JOIN Raca r ON p.raca_id = r.id WHERE p.id = ?";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar personagem por id: " + e.getMessage(), e);
        }

        return null;
    }

    @Override
    public Personagem buscarPorNome(String nome) {
        String sql = "SELECT p.id, p.nome, p.vida, r.nome AS raca, p.idade " +
                     "FROM Personagem p INNER JOIN Raca r ON p.raca_id = r.id WHERE LOWER(p.nome) = ?";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, nome.trim().toLowerCase());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar personagem por nome: " + e.getMessage(), e);
        }

        return null;
    }

    @Override
    public void inserir(Personagem personagem) {
        String sql = "INSERT INTO Personagem (nome, vida, raca_id, idade) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, personagem.getNome());
            stmt.setInt(2, personagem.getVida());
            stmt.setInt(3, buscarIdRaca(con, personagem.getRaca()));
            stmt.setInt(4, personagem.getIdade());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir personagem: " + e.getMessage(), e);
        }
    }

    @Override
    public void atualizar(Personagem personagem) {
        String sql = "UPDATE Personagem SET nome = ?, vida = ?, raca_id = ?, idade = ? WHERE id = ?";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, personagem.getNome());
            stmt.setInt(2, personagem.getVida());
            stmt.setInt(3, buscarIdRaca(con, personagem.getRaca()));
            stmt.setInt(4, personagem.getIdade());
            stmt.setInt(5, personagem.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar personagem: " + e.getMessage(), e);
        }
    }

    @Override
    public void remover(Integer id) {
        String sql = "DELETE FROM Personagem WHERE id = ?";

        try (Connection con = Conexao.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover personagem: " + e.getMessage(), e);
        }
    }

    private int buscarIdRaca(Connection con, String nomeRaca) throws SQLException {
        String sql = "SELECT id FROM Raca WHERE LOWER(nome) = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nomeRaca.trim().toLowerCase());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }

        throw new SQLException("Raça não cadastrada: " + nomeRaca);
    }

    private Personagem mapear(ResultSet rs) throws SQLException {
        return new Personagem(
            rs.getInt("id"),
            rs.getString("nome"),
            rs.getInt("vida"),
            rs.getString("raca"),
            rs.getInt("idade")
        );
    }
}
