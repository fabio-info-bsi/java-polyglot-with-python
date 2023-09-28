# java-polyglot-with-python-graalvm

[![en](https://img.shields.io/badge/lang-en-red.svg)](README.md)

Este projeto tem o objetivo de implementar código `Python` incorporado no `Java`através de programação polyglota no GraalVM.

---

## Implementações de Python código incorporado em Java

 Classe                                                                                                            | Descrição                                                                                             
-------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------
 [`br.com.fabex.HelloWorld`](src/main/java/br/com/fabex/HelloWorld.java)                                           | Implementação de várias opções para fazer um simples “Hello World”.                                   
 [`br.com.fabex.MathOperators`](src/main/java/br/com/fabex/MathOperators.java)                                     | Implementação de operações matemáticas: `+`, `-`, `*` & `/`.                                          
 [`br.com.fabex.SortArrayWithNumpy`](src/main/java/br/com/fabex/SortArrayWithNumpy.java)                           | Implementação de array de ordenação simples com dependência `numpy`.                                  
 [`br.com.fabex.CodeCachingByEngineInContexts`](src/main/java/br/com/fabex/CodeCachingByEngineInContexts.java)     | Exemplos de código com e sem cache em contextos (Engine Graal).                                       
 [`br.com.fabex.ControllingAccessToHostFunction`](src/main/java/br/com/fabex/ControllingAccessToHostFunction.java) | Exemplo de como controlar o acesso ao host (Java) por função.                                         
 [`br.com.fabex.SharedValueInContext`](src/main/java/br/com/fabex/SharedValueInContext.java)                       | A simple example sharing values though languages: `Java`, `Js`and `Python` with api polyglot GraalVM. 
 [`br.com.fabex.Multithreading`](src/main/java/br/com/fabex/Multithreading.java)                                   | A simple example how create and run threads `Python` though interoperability with `Java`.             
 [`br.com.fabex.ModifyExecutablePython`](src/main/java/br/com/fabex/ModifyExecutablePython.java)                   | Changing executable `Python` for specific environment.                                                
 [`br.com.fabex.TaskAnalyzerUserRollout`](src/main/java/br/com/fabex/TaskAnalyzerUserRollout.java)                 | A almost real world Task with use dependency `pandas` for analyze many datas in file `.csv`           

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

### Via Sdkman

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

### Instalação de ambientes e pacotes Python

`$JAVA_HOME/languages/python/bin/graalpy -m venv .env `

`source .env/bin/activate`

`$JAVA_HOME/languages/python/bin/graalpy -m ensurepip --upgrade`

`graalpy -m pip install -r requirements.txt` or `graalpy -m ginstall install <package>==<version>`

> Se o seu código tiver pacotes externos de dependência, você precisará instalar com esta etapa. Exemplos que precisam instalar os pacotes Numpy e Pandas são:
> - [`br.com.fabex.SortArrayWithNumpy`](src/main/java/br/com/fabex/SortArrayWithNumpy.java)
> - [`br.com.fabex.TaskAnalyzerUserRollout`](src/main/java/br/com/fabex/TaskAnalyzerUserRollout.java)

---

