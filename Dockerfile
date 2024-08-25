FROM ubuntu:latest
LABEL authors="jiann"

ENTRYPOINT ["top", "-b"]

FROM ubuntu:20.04

# prepare , install some utils
RUN apt-get --yes --force-yes update && \
apt-get --yes --force-yes install software-properties-common && \
add-apt-repository ppa:linuxuprising/java && \
apt-get --yes --force-yes update && \
apt install unzip && \
apt install zip
RUN apt-get --yes --force-yes update
RUN apt-get --yes --force-yes  install git
RUN apt-get --yes --force-yes install libxrender1

RUN apt-get --yes --force-yes install libxtst6
RUN apt-get --yes --force-yes install libxi6
RUN apt-get --yes --force-yes install fonts-dejavu fontconfig


# install Java
RUN apt-get update && apt-get --yes install openjdk-21-jdk
#RUN apt-get --yes install openjdk-20-jdk

# install build tools
RUN apt-get --yes --force-yes install maven
RUN apt-get --yes --force-yes install locales locales-all
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8
CMD echo 'Maven version'
CMD mvn -version
RUN apt-get --yes --force-yes install libgtk-3-0

#Copy source and files into the new directory
COPY . /

## Run maven default installation (including test goals)
CMD mvn clean test