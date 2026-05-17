#  Hospital Patient Management — TP Spring Boot

A Spring Boot MVC web application for managing hospital patients, featuring full CRUD operations, keyword search, form validation, and paginated results.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Usage Guide](#usage-guide)
- [Endpoints Reference](#endpoints-reference)
- [Security](#security)

---

## Overview

This TP project is a hospital patient management web application built with **Spring Boot MVC** and **Thymeleaf**. It allows medical staff to list, search, add, edit, and delete patient records through a simple and intuitive interface. The app runs on `localhost:8050` and is secured with Spring Security.

---

## ✨ Features

| Feature | Description |
|---|---|
| 📋 Patient List | Paginated table of all patients (ID, Nom, Prenom, Date, Score, Malade) |
| 🔍 Search | Case-insensitive keyword search on `nom` or `prenom` |
| ➕ Add Patient | Form to create a new patient with validation |
| ✏️ Edit Patient | Pre-filled form to update an existing patient |
| 🗑️ Delete Patient | One-click deletion, returns to current page and keyword |
| 📄 Pagination | Navigate across pages, keyword preserved across page changes |
| 🔐 Security | Spring Security authentication via `SecurityConfig` |

---

## 🛠 Tech Stack

- **Language:** Java 17+
- **Framework:** Spring Boot (MVC, Data JPA, Security, Validation)
- **Template Engine:** Thymeleaf
- **Database:** H2 (in-memory) or MySQL — configured in `application.properties`
- **Build Tool:** Maven
- **Server Port:** `8050`

---

## 📁 Project Structure

```
src/
└── main/
    ├── java/com/sdia/hospital/
    │   ├── HospitalApplication.java       
    │   ├── entities/
    │   │   └── Patient.java               
    │   ├── repository/
    │   │   └── PatientRepository.java     
    │   ├── security/
    │   │   └── SecurityConfig.java        
    │   └── web/
    │       └── PatientController.java     
    └── resources/
        ├── static/                        
        ├── templates/
        │   ├── patients.html              
        │   ├── formPatients.html          
        │   ├── editPatients.html          
        │   └── template1.html             
        └── application.properties         
```

---

##  Getting Started

### Prerequisites

- Java 21
- Maven 3.x

### Run

```bash
# Clone the project
git clone https://github.com/MohamedAmine-BoussaiD/TP2_Spring_MVC.git
cd hospital-app

# Run with Maven
mvn spring-boot:run
```

Open your browser at:
```
http://localhost:8050/index
```

---

##  Usage Guide

###  View All Patients

The home page (`/index`) displays a paginated table with columns: **ID, NOM, PRENOM, DATE, SCORE, MALADE**, along with **Delete** and **Edit** buttons per row.

Use the numbered page buttons at the bottom to navigate.

![img_3.png](img_3.png)

---

###  Search Patients

Type a keyword in the search bar and click **Search**.

The search is **case-insensitive** and checks both `nom` and `prenom` fields simultaneously:

```java
patientRepository.findByNomContainsIgnoreCaseOrPrenomContainsIgnoreCase(
    keyword, keyword, PageRequest.of(page, size)
);
```

Example URL:
```
http://localhost:8050/index?keyword=amine&page=0
```
![img.png](img.png)

---

###  Add a Patient

Click **Patients → Add** in the navbar to open the creation form (`/formPatients`).

Fill in the fields and submit. If validation fails (via `@Valid`), the form is redisplayed with error messages.

![img_1.png](img_1.png)
---

###  Edit a Patient

Click the **Edit** button on any row. You are redirected to:
```
http://localhost:8050/editPatient?id={id}
```
![img_2.png](img_2.png)


The form is pre-filled with the patient's current data. Submit to save changes.

**Editable fields:** Nom, Prenom, Date Naissance, Malade (checkbox), Score.

---

###  Delete a Patient

Click the red **Delete** button on any row. The patient is deleted and you are redirected back to the same page and keyword:

```
redirect:/index?page={page}&keyword={keyword}
```

---

## Endpoints Reference

| Method | URL | Description |
|---|---|---|
| `GET` | `/index` | List patients — params: `page`, `size`, `keyword` |
| `GET` | `/deletePatient` | Delete a patient — params: `id`, `keyword`, `page` |
| `GET` | `/formPatients` | Show the add patient form |
| `POST` | `/savePatients` | Save a new patient (with `@Valid` validation) |
| `GET` | `/editPatient` | Show the edit form — param: `id` |

---

##  Security

Spring Security is configured in `SecurityConfig.java`. The authenticated username is displayed in the top-right corner of the navbar.

> You can configure in-memory users or database-backed authentication inside `SecurityConfig`.

![img_4.png](img_4.png)
---

## Patient Entity

```java
// com.sdia.hospital.entities.Patient
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private Date dateNaissance;
    private boolean malade;
    private int score;
}
```

---

##  application.properties (example)

```properties
server.port=8050
# mysql config
spring.datasource.url=jdbc:mysql://localhost:3306/patient-db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
```

---

*TP réalisé dans le cadre du cours de développement web avec Spring Boot — package `com.sdia.hospital`.*