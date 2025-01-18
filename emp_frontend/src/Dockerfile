# Step 1: Use the official Node.js image to build the Angular app
FROM node:16 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json to install dependencies
COPY package.json package-lock.json ./

# Install the dependencies
RUN npm install

# Copy the entire project files to the container
COPY . .

# Build the Angular application in production mode
RUN npm run build --prod

# Step 2: Serve the Angular app using NGINX
FROM nginx:alpine

# Copy the build output from the previous build stage to the NGINX server
COPY --from=build /app/dist/your-angular-app-name /usr/share/nginx/html

# Expose port 80 to access the application in the browser
EXPOSE 80

# Start the NGINX server
CMD ["nginx", "-g", "daemon off;"]
