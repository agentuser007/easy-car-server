# easy-car-server

This is the server application for the Android easy car App, which provides the backend functionality and data management for the app.

## Technologies Used

- Spring Boot
- MyBatis
- MySQL

## Features

- User authentication and password reset
- Vehicle information management
- Traffic violation query
- Annual inspection query
- Dynamic message publishing and viewing
- Like and comment functionality on dynamic messages

## Database Design

The server app uses a MySQL database with the following tables:
- User table for storing user information
- Vehicle table for storing vehicle information
- Violation table for storing traffic violation data
- Annual inspection table for storing annual inspection data
- Dynamic table for storing dynamic message data
- Like table for storing likes on dynamic messages
- Comment table for storing comments on dynamic messages

## Model-View-Controller (MVC) Architecture

The server app follows the MVC pattern for organizing its codebase. The model layer handles data access and manipulation using MyBatis, while the view layer handles data presentation. The controller layer receives requests from the Android client app, processes them, interacts with the model layer, and sends back responses to the client.

