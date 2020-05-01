FROM mysql:8.0.18

RUN apt-get install bash

COPY ./import.sql /docker-entrypoint-initdb.d

EXPOSE 3306