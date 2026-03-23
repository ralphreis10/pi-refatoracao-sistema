# PI Professor-Killer - Sistema de Personagens Refatorado

Projeto pronto para entrega no NetBeans, com separação em camadas, aplicação de SOLID, CRUD completo e documentação para o relatório.

## Estrutura
- `model`: entidade `Personagem`
- `dao`: conexão com banco
- `repository`: acesso a dados via interface + implementação JDBC
- `service`: regras de negócio e validações
- `controller`: ponte entre interface e regras
- `view`: telas Swing
- `testes`: classe `MainTeste` para validação básica

## Melhorias implementadas
- Separação real entre interface, regra de negócio e persistência
- CRUD completo: cadastrar, listar, filtrar, atualizar e excluir
- Validações centralizadas no `PersonagemService`
- Uso de interface no repository para favorecer DIP
- Views desacopladas do banco
- Tratamento de mensagens de erro mais amigável

## Como executar
1. Abra a pasta `PI_Professor_Killer` no NetBeans.
2. Ajuste usuário e senha em `src/dao/Conexao.java`, se necessário.
3. Execute o script `sql/script_banco.sql` no MySQL.
4. Rode `view.MainMenuVIEW`.

## Teste simples
Também é possível executar `testes.MainTeste` para validar a listagem.

## Sugestão de repositório GitHub
`pi-professor-killer-refatorado`

## Comandos Git
```bash
git init
git add .
git commit -m "Projeto refatorado com SOLID e CRUD completo"
git branch -M main
git remote add origin https://github.com/SEUUSUARIO/pi-professor-killer-refatorado.git
git push -u origin main
```
