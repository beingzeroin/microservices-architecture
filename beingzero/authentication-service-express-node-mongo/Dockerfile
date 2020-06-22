FROM node:lts-alpine3.9

WORKDIR /app/src

COPY ./package.json .
COPY ./server.js .
ADD ./backend /app/src/backend

RUN npm install

EXPOSE 9000
