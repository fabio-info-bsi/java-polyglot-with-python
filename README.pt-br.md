# java-polyglot-with-python-graalvm

[![en](https://img.shields.io/badge/lang-en-red.svg)](README.md)

Este projeto tem o objetivo de implementar código `Python` incorporado no `Java` através de programação polyglota no GraalVM.

---

## Implementações de Python código incorporado em Java

 Classe                                                                                                            | Descrição                                                                                             
-------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------
 [`br.com.fabex.HelloWorld`](src/main/java/br/com/fabex/HelloWorld.java)                                           | Implementação de várias opções para fazer um simples “Hello World”.                                   
 [`br.com.fabex.MathOperators`](src/main/java/br/com/fabex/MathOperators.java)                                     | Implementação de operações matemáticas: `+`, `-`, `*` & `/`.                                          
 [`br.com.fabex.SortArrayWithNumpy`](src/main/java/br/com/fabex/SortArrayWithNumpy.java)                           | Implementação de array de ordenação simples com dependência `numpy`.                                  
 [`br.com.fabex.CodeCachingByEngineInContexts`](src/main/java/br/com/fabex/CodeCachingByEngineInContexts.java)     | Exemplos de código com e sem cache em contextos (Engine Graal).                                       
 [`br.com.fabex.ControllingAccessToHostFunction`](src/main/java/br/com/fabex/ControllingAccessToHostFunction.java) | Exemplo de como controlar o acesso ao host (Java) por função.                                         
 [`br.com.fabex.SharedValueInContext`](src/main/java/br/com/fabex/SharedValueInContext.java)                       | Um exemplo simples de compartilhamento de valores através de linguagens: `Java`, `Js` e `Python` com API poliglota GraalVM. 
 [`br.com.fabex.Multithreading`](src/main/java/br/com/fabex/Multithreading.java)                                   | Um exemplo simples de como criar e executar threads `Python` através da interoperabilidade com `Java`.             
 [`br.com.fabex.ModifyExecutablePython`](src/main/java/br/com/fabex/ModifyExecutablePython.java)                   | Alterando o executável `Python` para ambiente específico.                                                
 [`br.com.fabex.TaskAnalyzerUserRollout`](src/main/java/br/com/fabex/TaskAnalyzerUserRollout.java)                 | Uma tarefa quase do mundo real utilizando dependência `pandas` para analisar muitos dados em arquivo `.csv`.           

---

## Pré-requisitos

- [GraalVM 17 SDK](https://www.graalvm.org/jdk17/docs/)
    - [Graalpy(Python)](https://www.graalvm.org/jdk17/reference-manual/python/)
- Maven >= 3

---

## Ferramentas

- [SDKMAN!](https://sdkman.io/install)

---

## GraalVM SDK 17

GraalVM compila seus aplicativos Java antecipadamente em binários independentes. Esses binários são menores, começam até
100x mais rápido, fornece desempenho máximo sem aquecimento(jvm warmup) e usa menos memória e CPU do que aplicativos
executados em uma Java Virtual Machine (JVM).

https://www.graalvm.org/jdk17/docs/introduction/

## Instalação

https://www.graalvm.org/jdk17/docs/getting-started/

### Via Sdkman (CE ou EE)

> GraalVM Sdk 17(GraalVM Community)

`sdk install java 17.y.z-graalce`

> GraalVM (Oracle)

`sdk install java 17.y.z-graal`

---

## Graalpy(Python)

GraalPy fornece um ambiente de execução compatível com Python 3.10.
Referências:

- https://www.graalvm.org/jdk17/reference-manual/python/

- https://github.com/oracle/graalpython

### Instalação do graalpy

`$JAVA_HOME/bin/gu install python`

### Instalação de ambiente e pacotes Python

`$JAVA_HOME/languages/python/bin/graalpy -m venv .env `

`source .env/bin/activate`

`$JAVA_HOME/languages/python/bin/graalpy -m ensurepip --upgrade`

`graalpy -m pip install -r requirements.txt` or `graalpy -m ginstall install <package>==<version>`

> Se o seu código tiver pacotes externos de dependência, você precisará instalar com esta etapa. Exemplos que precisam instalar os pacotes Numpy e Pandas são:
> - [`br.com.fabex.SortArrayWithNumpy`](src/main/java/br/com/fabex/SortArrayWithNumpy.java)
> - [`br.com.fabex.TaskAnalyzerUserRollout`](src/main/java/br/com/fabex/TaskAnalyzerUserRollout.java)

---

