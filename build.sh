#!/bin/bash

VERSION="1.0.0"
CODEGEN="bin/swagger-codegen-cli-modified.jar"
SCHEMA="schema/$VERSION/swagger.json"

languages="android aspnet5 async-scala csharp cpprest dart flash python-flask go groovy java jaxrs jaxrs-cxf jaxrs-resteasy jaxrs-spec inflector javascript javascript-closure-angular jmeter nancyfx nodejs-server objc perl php python qt5cpp ruby scala scalatra sinatra rails5 slim spring swift tizen typescript-angular2 typescript-angular typescript-node typescript-fetch akka-scala clojure haskell lumen"
docs="swagger html html2 dynamic-html cwiki"

DetectJava() {

if type -p java; then
    echo Found Java executable in PATH
    _java=java
elif [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]];  then
    echo Found java executable in JAVA_HOME
    _java="$JAVA_HOME/bin/java"
else
    echo "ERROR: no Java installed, please install Java 1.7+"
fi

if [[ "$_java" ]]; then
    version=$("$_java" -version 2>&1 | awk -F '"' '/version/ {print $2}')
    echo version "$version"
    if [[ "$version" < "1.7" ]]; then
        echo ERROR: Java version is less than 1.7
	exit 255
    fi
fi
}

RunCodegen() {
    java -jar ${CODEGEN} generate -i ${SCHEMA} -l $1 -o $2 2>logs/$1-build.log
    if cat logs/$1-build.log|grep -E 'ERROR|Exception in'
    then
        echo ERROR during codegen run
        exit 255
    fi
}

Cleanup() {
    echo Cleaning result folders for API v.${VERSION}
	rm -rf api/${VERSION}/
	rm -rf docs/${VERSION}/
	rm -f logs/*
    echo DONE
}

if [ "$1" = "clean" ]
then
    Cleanup
    exit
fi


DetectJava

if [ "$1" = "test" ]
then
    echo Running swagger test generator
    RunCodegen swagger api/${VERSION}/swagger
    echo DONE
    exit
fi

echo Building APIs and DOCs from ${SCHEMA} file

echo Generating Documentation

for doc in ${docs}
do
    echo -e "\tDocumentation: ${doc}"
    RunCodegen ${doc} docs/${VERSION}/${doc}
done

echo Generating API

for lang in ${languages}
do
    echo -e "\tLanguage: ${lang}"
    RunCodegen ${lang} api/${VERSION}/${lang}
done

echo Swagger result schema located at docs/${VERSION}/swagger/swagger.json

