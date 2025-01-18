# Use an official Node.js runtime as a parent image
FROM node:18

# Set the working directory
WORKDIR /src/app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the Angular app source code
COPY . .

# Expose the development server port
EXPOSE 4200

# Run ng serve (use host binding to access it)
CMD ["npm", "run", "start"]
