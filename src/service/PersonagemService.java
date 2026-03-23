package service;

import exception.RegraNegocioException;
import java.util.List;
import model.Personagem;
import repository.PersonagemRepository;

public class PersonagemService {

    private final PersonagemRepository repository;

    public PersonagemService(PersonagemRepository repository) {
        this.repository = repository;
    }

    public List<Personagem> listarTodos() {
        return repository.listarTodos();
    }

    public List<Personagem> filtrar(String nome, String raca) {
        return repository.filtrar(nome, raca);
    }

    public void cadastrar(Personagem personagem) {
        validar(personagem);

        if (repository.buscarPorNome(personagem.getNome()) != null) {
            throw new RegraNegocioException("Já existe um personagem com esse nome.");
        }

        repository.inserir(personagem);
    }

    public void atualizar(Personagem personagem) {
        validar(personagem);

        if (personagem.getId() == null) {
            throw new RegraNegocioException("O personagem precisa ter um id para ser atualizado.");
        }

        Personagem existente = repository.buscarPorNome(personagem.getNome());
        if (existente != null && !existente.getId().equals(personagem.getId())) {
            throw new RegraNegocioException("Já existe outro personagem com esse nome.");
        }

        repository.atualizar(personagem);
    }

    public void remover(Integer id) {
        if (id == null) {
            throw new RegraNegocioException("Selecione um personagem para remover.");
        }
        repository.remover(id);
    }

    private void validar(Personagem personagem) {
        if (personagem == null) {
            throw new RegraNegocioException("Personagem inválido.");
        }

        if (personagem.getNome() == null || personagem.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("O nome é obrigatório.");
        }

        if (personagem.getNome().trim().length() < 3) {
            throw new RegraNegocioException("O nome deve ter pelo menos 3 caracteres.");
        }

        if (personagem.getVida() <= 0) {
            throw new RegraNegocioException("A vida deve ser maior que zero.");
        }

        if (personagem.getIdade() < 0) {
            throw new RegraNegocioException("A idade não pode ser negativa.");
        }

        if (personagem.getRaca() == null || personagem.getRaca().trim().isEmpty()) {
            throw new RegraNegocioException("A raça é obrigatória.");
        }
    }
}
