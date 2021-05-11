# Simbler
#### Simulador de Linguagem Assembly
___

O Simbler foi desenvolvido em 2011 como parte do projeto final do curso de Ciência da Computação da Faculdade Municipal Professor Franco Montoro. 

![Simbler](https://intercienciaesociedade.francomontoro.com.br/images/simbler2.jpg "Simbler")

### Mais informações
O Assembly (ou linguagem de montagem) é uma representação legível da linguagem de máquina, utilizada por dispositivos como microprocessadores e microcontroladores. Apesar de legível para humanos, a linguagem de montagem caracteriza-se por ser uma codificação de difícil entendimento e aprendizado por possuir características muito diferentes quando comparada às linguagens de alto nível. A ausência de ferramentas para auxiliar neste aprendizado dificulta ainda mais o entendimento deste tipo de programação. Este artigo se propõe a apresentar uma aplicação que fornece meios para suprir esse tipo de necessidade, sendo muito simples e intuitiva, ela trás diversos recursos para facilitar o aprendizado da linguagem de montagem, além de também auxiliar no entendimento da arquitetura de Von Neumann, no qual o sistema foi baseado. Construída em Java, e de código fonte aberto (open-source), o Simbler pode vir a ser utilizado com alunos das disciplinas de “Organização e Arquitetura de Computadores” e “Microprocessadores e Microcontroladores” em um curso de Engenharia de Computação, Ciência da Computação ou Engenharia Elétrica.
 
**Artigos**: [1](https://www.researchgate.net/publication/266260775_SIMBLER_-_UM_SIMULADOR_DE_LINGUAGEM_DE_MONTAGEM_DIDATICO_APLICADO_AO_ENSINO_DE_INFORMATICA) & [2](https://intercienciaesociedade.francomontoro.com.br/projetos/simbler/simbler.pdf)

**Download do utilitário**: [Simbler Download](https://intercienciaesociedade.francomontoro.com.br/projetos/simbler/simbler.zip)

**Atenção**: Requer Java Runtime (versão máxima suportada: 8)

**Outras informações**: [Faculdade Municipal Professor Franco Montoro](https://intercienciaesociedade.francomontoro.com.br/projetos/simbler/simbler.html)

### Documentação

- **ADD**: Adiciona o valor especificado ao registrador acumulador (AX)
- **HLT**: Encerra a execução do programa
- **INC**: Incrementa em "1" o valor do campo definido
- **JMP**: Desvia o programa para o endereço especificado
- **JNZ**: Desvia o programa para o endereço especificado se o sinalizador "Zero" estiver em "0"
- **JZ**: Desvia o programa para o endereço especificado se o sinalizador "Zero" estiver em "1" 
- **LOAD**: Armazena o valor especificado no registrador acumulador (AX)
- **MUL**: Multiplica o valor do registrador acumulador pelo valor especificado
- **STORE**: Armazena o valor definido no registrador acumulador (AX) no campo especificado
- **SUB**: Subtrai o valor do registrador acumulador (AX) pelo valor especificado
- **JS**: Desvia o programa para o endereço especificado se o sinalizador "Signal" estiver em "1"
- **JNS**: Desvia o programa para o endereço especificado se o sinalizador "Signal" estiver em "0"
- **JO**: Desvia o programa para o endereço especificado se o sinalizador "Overflow" estiver em "1"
- **JNO**: Desvia o programa para o endereço especificado se o sinalizador "Overflow" estiver em "0"
- **DEC**: Decrementa em "1" o valor do campo definido
- **DIV**: Divide o valor do registrador acumulador (AX) pelo valor especificado
- **NOP**: NOP ou "No Operation Performed" é uma instrução normalmente usada para operações com temporizadores. Não efetua nenhuma operação
- **NOT**: Efetua a operação "NOT" com o valor atual do registrador acumulador (AX)
- **AND**: Efetua a operação "AND" com o valor atual do registrador acumulador (AX) e o valor especificado
- **OR**: Efetua a operação "OR" com o valor atual do registrador acumulador (AX) e o valor especificado 
- **CMP**: Compara o valor do registrador acumulador (AX) com o valor especificado. O resultado dessa operação afeta os sinalizadores "Z" e "S"
- **DB**: DB ou "Define Byte" aloca um valor numérico na posição de memória especificado

Documentação completa pode ser encontrado junto com o download do utilitário acima.

### Exemplos

1. Cálculo da área de um quadrado (tamanho 7)
```Assembly
DB 7 ;Define o valor do lado do quadrado
LOAD @0 ;Carrega em AX o valor do endereço "0" de memória
MUL @0 ;Multiplica AX pelo valor do endereço "0" de memória
HLT ;Finaliza o programa
```  

TBD
