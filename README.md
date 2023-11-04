# java-polyglot-with-python-graalvm

[![en](https://img.shields.io/badge/lang-en-red.svg)](README.md)
[![pt-br](https://img.shields.io/badge/lang-pt--br-green.svg)](README.pt-br.md)

This project have objective to implement `Python` code embedded in `Java` though polyglot programming in GraalVM.

---

## Implementations Python embedded code in Java

 Class                                          | Description                                                                                           
------------------------------------------------|-------------------------------------------------------------------------------------------------------
 [`br.com.fabex.HelloWorld`](src/main/java/br/com/fabex/HelloWorld.java)| Implementation of the many options to do a simple "Hello World".                                      
 [`br.com.fabex.MathOperators`](src/main/java/br/com/fabex/MathOperators.java)| Implementation of maths operations: `+`, `-`, `*` & `/`.                                              
 [`br.com.fabex.SortArrayWithNumpy`](src/main/java/br/com/fabex/SortArrayWithNumpy.java)| Implementation simple ordenation array with dependency `numpy`.                                       
 [`br.com.fabex.CodeCachingByEngineInContexts`](src/main/java/br/com/fabex/CodeCachingByEngineInContexts.java)| Examples of cache and no-cache code in contexts(Engine Graal).                                        
 [`br.com.fabex.ControllingAccessToHostFunction`](src/main/java/br/com/fabex/ControllingAccessToHostFunction.java)| Example of how controlling access to host(Java) by Function.                                   
 [`br.com.fabex.SharedValueInContext`](src/main/java/br/com/fabex/SharedValueInContext.java)| A simple example sharing values though languages: `Java`, `Js`and `Python` with api polyglot GraalVM.
 [`br.com.fabex.Multithreading`](src/main/java/br/com/fabex/Multithreading.java)| A simple example how create and run threads `Python` though interoperability with `Java`.             
 [`br.com.fabex.ModifyExecutablePython`](src/main/java/br/com/fabex/ModifyExecutablePython.java)| Changing executable `Python` for specific environment.                                               
 [`br.com.fabex.TaskAnalyzerUserRollout`](src/main/java/br/com/fabex/TaskAnalyzerUserRollout.java)| A almost real world Task with use dependency `pandas` for analyze many datas in file `.csv`.          

---

## Prerequisites

- [GraalVM 21 SDK](https://www.graalvm.org/jdk21/docs/)
    - [Graalpy(Python)](https://www.graalvm.org/jdk21/reference-manual/python/)
- Maven >= 3

---

## Install tools

- [SDKMAN!](https://sdkman.io/install)

---

## GraalVM SDK 21

GraalVM compiles your Java applications ahead of time into standalone binaries. These binaries are smaller, start up to
100x faster, provide peak performance with no warmup, and use less memory and CPU than applications running on a Java
Virtual Machine (JVM).

https://www.graalvm.org/jdk21/docs/introduction/

## Install

https://www.graalvm.org/jdk21/docs/getting-started/

### Via Sdkman (CE or EE)

> GraalVM SDK 21(Community Edition)

`sdk install java 21.y.z-graalce`

> GraalVM SDK 21(Enterprise Edition)

`sdk install java 21.y.z-graal`

### For Windows
https://www.graalvm.org/jdk17/docs/getting-started/windows/

---

## Graalpy (Python)

GraalPy provides a Python 3.10 compliant runtime.

References:

- https://www.graalvm.org/jdk21/reference-manual/python/

- https://github.com/oracle/graalpython

### Install graalpy

#### Pyenv
`pyenv install graalpy-23.1.0`

#### Conda-Forge
`conda create -c conda-forge -n graalpy graalpy`

#### For Windows
There is a GraalPy preview build for Windows that you can [download](https://github.com/oracle/graalpython/releases/). It supports installation of pure Python packages via pip. Native extensions are a work in progress.

The Windows build has several known issues:

- JLine treats Windows a dumb terminal, no autocomplete and limited editing capabilities in the REPL
- Interactive help() in the REPL doesn’t work
- Inside venvs:
  - graalpy.cmd and graalpy.exe are broken
  - pip.exe cannot be used directly
  - pip has trouble with cache file loading, use `--no-cache-dir`
  - Only pure Python binary wheels can be installed, no native extensions or source builds
  - To install a package, use `myvenv/Scripts/python.cmd -m pip --no-cache-dir install <pkg>`
- Running from PowerShell works better than running from CMD, various scripts will fail on the latter

### Install dependency Python

`graalpy -m venv .env`

`source .env/bin/activate`

`graalpy -m pip install -r requirements.txt` or `graalpy -m ginstall install <package>==<version>`

> If your code have dependency external packages, you need install with this step. Examples then need install packages Numpy and Pandas are: 
> - [`br.com.fabex.SortArrayWithNumpy`](src/main/java/br/com/fabex/SortArrayWithNumpy.java)
> - [`br.com.fabex.TaskAnalyzerUserRollout`](src/main/java/br/com/fabex/TaskAnalyzerUserRollout.java)

---