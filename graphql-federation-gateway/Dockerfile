###### With best practices ######
# First stage
FROM node:alpine as FirstStage

# Create app directory
WORKDIR /usr/src/app

# Install dependencies
COPY package*.json ./
COPY tsconfig.json ./

# Copy source files
COPY index.ts .

# Build app and remove Dev Dependencies
RUN npm ci --production

# Final stage
FROM node:alpine

WORKDIR /usr/src/app

# Copy all files from builder
COPY --from=FirstStage /usr/src/app /usr/src/app

EXPOSE 9000

# Run the app
CMD [ "npm", "start" ]

# ###### Without best practices ######
# FROM node

# # Create app directory
# WORKDIR /usr/src/app

# # Copy source files
# COPY . .

# # Build app
# RUN npm install

# EXPOSE 9000

# # Run the app
# CMD [ "npm", "start" ]