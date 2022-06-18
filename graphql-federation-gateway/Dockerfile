# First stage
FROM node:alpine as FirstStage

# Create app directory
WORKDIR /usr/src/app

# Install dependencies
COPY package*.json ./
COPY tsconfig.json ./

RUN npm install --frozen-lockfile

# Copy source files
COPY . .

# Build app and remove Dev Dependencies
RUN npm install

# Final stage
FROM node:alpine

WORKDIR /usr/src/app

# Copy all files from builder
COPY --from=FirstStage /usr/src/app /usr/src/app

EXPOSE 9000

# Run the app
CMD [ "npm", "start" ]