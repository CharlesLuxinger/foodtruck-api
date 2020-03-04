FROM mysql:8.0.18

RUN apt-get install bash

ENV MYSQL_ROOT_PASSWORD 12345678 \
    && MYSQL_DATABASE foodtruck

COPY ./import.sql /docker-entrypoint-initdb.d

EXPOSE 3306