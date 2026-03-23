package testes;

import controller.PersonagemController;
import model.Personagem;

public class MainTeste {

    public static void main(String[] args) {
        PersonagemController controller = new PersonagemController();

        System.out.println("=== TESTE DE LISTAGEM ===");
        for (Personagem personagem : controller.listar()) {
            System.out.println(personagem.getId() + " - " + personagem.getNome()
                    + " - " + personagem.getRaca()
                    + " - Vida: " + personagem.getVida()
                    + " - Idade: " + personagem.getIdade());
        }

        System.out.println("Teste executado. Verifique se a conexão com o banco e os dados estão corretos.");
    }
}
