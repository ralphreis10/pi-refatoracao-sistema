package model;

public class Personagem {
    private Integer id;
    private String nome;
    private int vida;
    private String raca;
    private int idade;

    public Personagem() {
    }

    public Personagem(Integer id, String nome, int vida, String raca, int idade) {
        this.id = id;
        this.nome = nome;
        this.vida = vida;
        this.raca = raca;
        this.idade = idade;
    }

    public Personagem(String nome, int vida, String raca, int idade) {
        this(null, nome, vida, raca, idade);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
