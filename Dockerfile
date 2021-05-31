FROM maven:3.8.1-openjdk-11

RUN mkdir -p /usr/repo/target
WORKDIR /usr/repo
COPY ./src /usr/repo/src
COPY ./pom.xml /usr/repo/pom.xml

CMD mvn clean verify -Dcucumber.filter.tags="@functional-checks"