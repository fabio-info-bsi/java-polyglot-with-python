# java-polyglot-with-python-graalvm

---
## Prerequisites
- [GraalVM 17 SDK](https://www.graalvm.org/jdk17/docs/)
  - [Graalpy(Python)](https://www.graalvm.org/jdk17/reference-manual/python/)
- Maven >= 3
---
## Install tools
- [SDKMAN!](https://sdkman.io/install)
---
## GraalVM SDK 17
GraalVM compiles your Java applications ahead of time into standalone binaries. These binaries are smaller, start up to 100x faster, provide peak performance with no warmup, and use less memory and CPU than applications running on a Java Virtual Machine (JVM).

https://www.graalvm.org/jdk17/docs/introduction/
## Install
https://www.graalvm.org/jdk17/docs/getting-started/

### Via Sdkman
>GraalVM Sdk 17(GraalVM Community)

`sdk install java 17.y.z-graalce`

>GraalVM (Oracle)

`sdk install java 17.y.z-graal`

---
## Graalpy(Python)
GraalPy provides a Python 3.10 compliant runtime.
https://www.graalvm.org/jdk17/reference-manual/python/
https://github.com/oracle/graalpython
### Install graalpy
`$JAVA_HOME/bin/gu install python`

### Install dependency Python
`$JAVA_HOME/languages/python/bin/graalpy -m venv .env `

`source .env/bin/activate`

`$JAVA_HOME/languages/python/bin/graalpy -m ensurepip --upgrade`

`graalpy -m pip install -r requirements.txt` or `graalpy -m ginstall install <package>==<version>`

---
## Run
