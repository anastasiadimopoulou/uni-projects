# Food Ordering System

Distributed Systems Project (AUEB)

## Description
This project is a simple distributed food ordering system inspired by platforms like efood and Wolt.

It includes:
- Backend system in Java (TCP sockets)
- Master-Worker architecture
- Android application for users
- Manager console for store management
- MapReduce-like processing for search and filtering

## Features

### Manager
- Add / remove stores
- Add / remove products
- Update stock and prices
- View sales statistics

### Customer
- Search stores (distance, category, price, rating)
- Make purchases
- Rate stores

## Technologies
- Java (Backend)
- Android (Frontend)
- TCP Sockets
- Multithreading

## Architecture
- Master node handles requests
- Worker nodes store data in memory
- Communication via TCP