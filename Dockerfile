#FROM ghcr.io/graalvm/jdk-community:17
#FROM ghcr.io/graalvm/graalvm-ce:ol8-java11-22.3.3
#FROM ghcr.io/graalvm/jdk-community:21.0.0
#Not working yet
#FROM ghcr.io/graalvm/graalvm-community:21.0.0-ol9-20230919
FROM ghcr.io/graalvm/graalvm-community:17.0.8-ol7-20230725

EXPOSE 8080

# Add Certificado Zscaler
COPY ZscalerRootCertificate-2048-SHA256.crt ZscalerRootCertificate-2048-SHA256.crt
RUN keytool -keystore /opt/graalvm-community-java17/lib/security/cacerts -storepass changeit -noprompt -trustcacerts -importcert -alias EnterpriseRootCA -file ZscalerRootCertificate-2048-SHA256.crt

# Code
WORKDIR /app

COPY target/java-polyglot-with-python-1.0-SNAPSHOT.jar /app/app.jar
COPY target/classes ./classes
COPY components-graalvm/ ./components-graalvm

# Url download components:
#https://github.com/graalvm/graalvm-ce-builds/releases/download/graal-23.0.1/icu4j-installable-ce-java17-linux-amd64-23.0.1.jar

# Install components
RUN gu -L install components-graalvm/icu4j-installable-ce-java17-linux-amd64-23.0.1.jar
RUN gu -L install components-graalvm/regex-installable-ce-java17-linux-amd64-23.0.1.jar
RUN gu -L install components-graalvm/llvm-installable-svm-java17-linux-amd64-23.0.1.jar
RUN gu -L install components-graalvm/llvm-toolchain-installable-ce-java17-linux-amd64-23.0.1.jar
RUN gu -L install components-graalvm/python-installable-svm-java17-linux-amd64-23.0.1.jar

# Disable certificate yum
#RUN echo 'sslverify=false' >> /etc/yum.conf

#RUN yum install gcc glibc-devel zlib-devel libstdc++-static
#RUN yum install build-essential libz-dev zlib1g-dev

# env python
RUN $JAVA_HOME/languages/python/bin/graalpy -m venv classes/polyglot/python/src/.env
RUN source classes/polyglot/python/src/.env/bin/activate
RUN $JAVA_HOME/languages/python/bin/graalpy -m ensurepip --upgrade
#RUN yum install dnf
#RUN dnf install gcc glibc-devel zlib-devel libstdc++-static
#RUN yum install gcc
#RUN graalpy -m pip install -r classes/polyglot/python/src/requirements.txt
#RUN $JAVA_HOME/languages/python/bin/graalpy -m ginstall install numpy,pandas
RUN $JAVA_HOME/languages/python/bin/graalpy -m ginstall install pytz, six, numpy, python-dateutil, pandas
#RUN graalpy -m ginstall install numpy
#RUN graalpy -m ginstall install numpy==1.23.5
#RUN graalpy -m pip install numpy==1.23.5

CMD ["bash"]