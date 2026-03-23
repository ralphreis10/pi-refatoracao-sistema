package repository;

import java.util.List;
import model.Personagem;

public interface PersonagemRepository {

    List<Personagem> listarTodos();

    List<Personagem> filtrar(String nome, String raca);

    Personagem buscarPorId(Integer id);

    Personagem buscarPorNome(String nome);

    void inserir(Personagem personagem);

    void atualizar(Personagem personagem);

    void remover(Integer id);
}
