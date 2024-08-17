# ScoopsNsmile - Online Ice Cream Shop

![ScoopsNsmile Logo](Frontend\public\images\title.png) <!-- Add the logo image link here -->

## Project Overview

**ScoopsNsmile** is an online ice cream shop platform that allows customers to browse and order their favorite ice creams, track orders, and manage deliveries. The platform provides separate panels for Admins, Customers, and Delivery Persons, ensuring a smooth and personalized experience for each role.

### Tech Stack

- **Frontend:** React.js, Redux, Material UI
- **Backend:** Java Spring Boot
- **Database:**  MySQL
- **Authentication:** Spring Security with JWT
- **Build Tools:** Maven
- **Version Control:** Git

## Table of Contents

- [Installation](#installation)
- [Features](#features)
<!-- - [Usage](#usage)
- [Contributing](#contributing)
- [License](#license) -->

## Installation

### Prerequisites

- **Node.js:** v14.x or higher
- **Java:** JDK 11 or higher
- **Maven:** 3.x
- **MySQL:** 8

### Setup Instructions

1. **Clone the repository:**

    ```bash
    git clone https://github.com/shaileshkale244/Scoops-n-Smile.git
    cd scoopsnsmile
    ```

2. **Backend Setup:**

    - Navigate to the backend directory:

        ```bash
        cd backend
        ```

    - Configure the database connection in `application.properties`:

        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/scoopsnsmile
        spring.datasource.username=root
        spring.datasource.password=yourpassword
        ```

    - Run the backend application:

        ```bash
        mvn spring-boot:run
        ```

3. **Frontend Setup:**

    - Navigate to the frontend directory:

        ```bash
        cd frontend
        ```

    - Install dependencies:

        ```bash
        npm install
        ```

    - Start the development server:

        ```bash
        npm start
        ```
## Features

### Admin Panel:

-   **Manage ice cream products**
-   **Track orders and delivery status**
-   **View sales analytics**
        
### Customer Panel:

        
-   **Browse and search for ice creams**
-   **Add items to cart and place orders**
-   **View order history**
        
### Delivery Person Panel:

        
-   **View assigned deliveries**
-   **Mark deliveries as completed**
-   **OTP verification for deliveries**
        
### Authentication:

        
-   **JWT-based authentication and authorization**
-   **Role-based access control**
        
