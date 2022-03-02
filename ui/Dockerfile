FROM node:alpine
WORKDIR /jmessage_ui
ENV PATH /jmessage_ui/node_modules/.bin:$PATH
COPY package.json ./
COPY package-lock.json ./
RUN npm i
COPY . ./
CMD ["npm", "start"]