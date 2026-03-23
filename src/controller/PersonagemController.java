package controller;

import java.util.List;
import model.Personagem;
import repository.PersonagemRepository;
import repository.PersonagemRepositoryJdbc;
import service.PersonagemService;

public class PersonagemController {

    private final PersonagemService service;

    public PersonagemController() {
        PersonagemRepository repository = new PersonagemRepositoryJdbc();
        this.service = new PersonagemService(repository);
    }

    public List<Personagem> listar() {
        return service.listarTodos();
    }

    public List<Personagem> filtrar(String nome, String raca) {
        return service.filtrar(nome, raca);
    }

    public void adicionar(Personagem personagem) {
        service.cadastrar(personagem);
    }

    public void atualizar(Personagem personagem) {
        service.atualizar(personagem);
    }

    public void remover(Integer id) {
        service.remover(id);
    }
}
