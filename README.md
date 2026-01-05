# JavaQuest: O Despertar do Código

O JavaQuest é um RPG (Role-Playing Game) tradicional executado via interface de linha de comando (CLI). Este projeto foi concebido como um ambiente de prática para o aprimoramento de competências na linguagem Java e a aplicação de fundamentos de Programação Orientada a Objetos (POO).

O sistema gerencia o controle de um herói, o enfrentamento de oponentes gerados de forma procedimental, a gestão de recursos e a progressão de atributos através de um sistema de experiência e níveis.

## Status do Desenvolvimento

### Implementado
- [x] Estrutura Inicial: Configuração da classe Main e processamento de entrada via Scanner.
- [x] Modelagem de Entidades: Implementação da classe abstrata Entity e das especializações Hero e Monster.
- [x] Mecânica de Combate: Sistema de turnos com lógica de dano baseada em força e defesa (mitigação).
- [x] Evolução de Personagem: Sistema de XP e Level Up com curva de progressão multiplicativa.
- [x] Escalonamento Dinâmico: Geração de monstros com atributos vinculados ao nível atual do jogador.
- [x] Interface de Usuário (CLI): Menu de ações para seleção de comandos em tempo real.
- [x] Gestão de Recursos: Sistema de itens consumíveis (poções) com lógica de limitação de cura.
- [x] Polimorfismo: Centralização da lógica de ataque na classe base Entity para escalabilidade de combate.

### Em Desenvolvimento (Sprint 5+)
- [ ] Sistema de Energia (Mana): Introdução de pontos de energia para limitação de habilidades especiais.
- [ ] Habilidades Especiais: Implementação do método de ataque especial com custo de recurso.
- [ ] Especialização de Inimigos: Criação da classe Boss com multiplicadores de atributos e recompensas.
- [ ] Regeneração Passiva: Recuperação de pontos de energia por iteração de turno.
- [ ] Refatoração de Arquitetura: Implementação do padrão Static Factory Method para geração de monstros.

## Tecnologias Utilizadas
- Java 17+: Utilização de métodos modernos da API de Random e formatação avançada de strings.
- Programação Orientada a Objetos: Aplicação prática de Herança, Abstração, Encapsulamento e Polimorfismo.
- CLI (Command Line Interface): Interação direta via terminal de sistema.

## Requisitos de Execução
Para compilar e rodar o projeto, são necessários:
1. Java JDK 17 ou superior.
2. Terminal de linha de comando (Bash, CMD ou PowerShell).
3. Ambiente de desenvolvimento (opcional): IntelliJ IDEA, Eclipse ou VS Code.

## Instruções de Instalação e Execução
1. Clonar o repositório:
   git clone https://github.com/iagoscandido/JavaQuest.git

2. Acessar o diretório do projeto:
   cd java-quest

3. Compilar o código fonte:
   javac *.java

4. Iniciar a aplicação:
   java Main

---
Este projeto é mantido como parte de um portfólio de estudos em engenharia de software e desenvolvimento Java.