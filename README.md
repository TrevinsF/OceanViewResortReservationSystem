# Ocean View Resort Reservation System

![Java](https://img.shields.io/badge/Java-F89820?style=for-the-badge&logo=coffeescript)
![IDEA](https://img.shields.io/badge/IntelliJ%20IDEA-B1428A?style=for-the-badge&logo=intellijidea)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Tomcat](https://img.shields.io/badge/Apache%20Tomcat-F8DC75?style=for-the-badge&logo=apache-tomcat&logoColor=black)

---

## 🌌 Overview

**Ocean View Resort Reservation System** is a web-based application. Built using Java, JSP and MySQL, it offers a secure, user-friendly interface for creating and viewing reservations, and billing operations with a modern and responsive design.

---

## ✨ Key Features

- **User Authentication**: A secure login system.
- **Dashboard**: Centralized hub for seamless navigation to all core functionalities.
- **Create New Reservation**: Create operation for adding reservation records.
- **Find Reservation**: View all reservations or one based on the number.
- **Bill Generation & Printing**: displays and prints professionally formatted bills, based on a reservation number.

---

## 🛠️ Technology Stack

- **Backend**: Java
- **Frontend**: JSP, HTML, CSS
- **Database**: MySQL
- **Build Tool**: Maven
- **Server**: Apache Tomcat 11

---

## ⚙️ Setup Instructions

This guide provides a step-by-step process for setting up and running the project within IntelliJ IDEA, using Apache Tomcat 11, OpenJDK 24, and a MySQL database.

### Prerequisites

- **[IntelliJ IDEA](https://www.jetbrains.com/idea/) (Ultimate Edition)**
- **[MySQL](https://dev.mysql.com/downloads/installer/)**
- **[Apache Tomcat 11](https://tomcat.apache.org/download-11.cgi)**
- **OpenJDK 24 or compatible version**

---

### 1. Project Setup
1. **Download the [Latest Release](https://github.com/TrevinsF/OceanViewResortReservationSystem/releases).**


2. **Extract the contents of the ZIP file to a preferred location on your computer.**


3. **Open IntelliJ IDEA and select Open from the welcome screen.**


4. **Navigate to the extracted project folder and click OK. IntelliJ will import the project.**

### 2. Database Configuration

Execute the following SQL script in your MySQL client to set up the database and required tables:

```sql
DROP DATABASE IF EXISTS oceanview_db;
CREATE DATABASE oceanview_db;
USE oceanview_db;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL
);

INSERT INTO users (username, password) VALUES ('admin', '123');

CREATE TABLE reservations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    reservation_number INT UNIQUE NOT NULL,
    guest_name VARCHAR(100) NOT NULL,
    address VARCHAR(200) NOT NULL,
    contact_number VARCHAR(20) NOT NULL,
    room_type VARCHAR(50) NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL
);
```

### 3. Database Connection Setup

Update the database credentials in IntelliJ IDEA under `src/main/java/com/oceanview/util/DBConnection.java` to match your MySQL configuration:

```java
// src/main/java/com/oceanview/util/DBConnection.java
private static final String JDBC_URL = "jdbc:mysql://localhost:3306/oceanview_db?useSSL=false";
private static final String JDBC_USERNAME = "root"; // Replace with your MySQL username
private static final String JDBC_PASSWORD = "1234"; // Replace with your MySQL password
```

### 4. Configure IntelliJ IDEA and Tomcat
1. In IntelliJ IDEA, go to **File > Project Structure** (`Ctrl+Alt+Shift+S`).


2. Under **Project Settings**, select **Project.**


3. Set the **Project SDK** to your installed `OpenJDK 24`.


4. Next, under **Project Settings**, select **Artifacts.**


5. `If an artifact is not already created`, click the `+` button, select **Web Application: Exploded > From Modules**, and choose the main project module.


6. The default settings should be fine, but make sure the **Output directory** is set to a valid location.

### 5. Add and Configure the Tomcat Server
1. Go to **Run > Edit Configurations.**


2. Click the `+` icon in the top-left corner and select **Tomcat Server > Local.**


3. In the configuration dialog:
    - Under **Application Server**, click **Configure.**
    - Browse to the root directory where you extracted Apache Tomcat 11.
    - Set the **HTTP port** `8080`.


4. Next, switch to the **Deployment** tab.


5. Click the `+` button and select **Artifact > OceanViewResortReservationSystem:war exploded.**


6. Change the **Application context** below to `/OceanViewResortReservationSystem` or simply `/`.


7. Click **Apply** and **OK.**

### 6. Run the Application `▷`️

---

## 📁 Project Structure

```
OceanViewResortReservationSystem/
├── src
│   └── main
│       ├── java/com/oceanview
│       │   ├── dao       # Data Access Objects for database operations
│       │   ├── filter    # Servlet filters for request processing
│       │   ├── model     # Data models for entities
│       │   ├── servlet   # Servlets for handling requests
│       │   └── util      # Utility classes for shared functionality
│       └── webapp
│           ├── css/      # custom styles
│           └── *.jsp     # JSP view files
│
└── pom.xml               # Maven configuration file
```

---

## 🔮 Future Roadmap

The following enhancements are planned to further improve the Ocean View Resort Reservation System:

- **Advanced Reporting**: Implement detailed analytics and reporting features.
- **Cloud Deployment**: Add support for deployment on cloud platforms like AWS, Azure, or Google Cloud for improved scalability.
- **Mobile Application**: Develop native iOS and Android apps for on-the-go access to reservation and billing management.
- **Automated Notifications**: Introduce email and SMS notifications for bill generation.
- **Internationalization**: Support multiple languages to cater to a global user base.

---

## 🤝 Contributing

We welcome contributions to enhance the Ocean View Resort Reservation System. To contribute:

1. Fork the repository.
2. Create a feature branch (`git checkout -b feature/YourFeature`).
3. Commit your changes (`git commit -m 'Add YourFeature'`).
4. Push to the branch (`git push origin feature/YourFeature`).
5. Submit a pull request for review.

Please ensure your code adheres to the project's coding standards and includes appropriate documentation.

---


## 📧 Contact

For questions or support, please contact the project maintainers at [support@oceanview.com](mailto:support@oceanview.com).

---

<div align="center">
  <p>© 2026 Ocean View Resort. All rights reserved.</p>
</div>
