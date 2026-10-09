# 📱 PhoneVerdict - Smartphone Review, Comparison & Verdict Web Application

A full-stack, enterprise-grade Java web application designed and built for college project demonstration and real-world smartphone purchasing guidance. Built with **Java 21**, **Spring Boot 3**, **Spring Data JPA**, **Spring Security 6**, **Thymeleaf**, and **MySQL**.

---

## 📌 1. Project Overview

**PhoneVerdict** is a smart decision platform for technology buyers. Navigating modern smartphones can be challenging with countless specs, marketing hype, and complex configurations. PhoneVerdict bridges this gap by delivering:
- In-depth, verified smartphone specifications across Display, Hardware, Camera, Battery, and Connectivity.
- An **Algorithmic Verdict Engine** that calculates an unbiased weighted score out of 10.
- A **Head-to-Head Comparison Engine** that highlights category winners and crowns an overall champion.
- An interactive **Community Review System** with administrative moderation.
- A dedicated **Admin Control Panel** for complete catalog, user, and review lifecycle management.

---

## ✨ 2. Key Features

### 👤 User Dashboard & Public Experience
- **Modern Homepage:** Eye-catching hero section, search bar, latest phones, highest-rated devices, and real reviews.
- **Advanced Phone Catalog & Filters:** Filter phones by Brand (Samsung, Apple, OnePlus, Google, Xiaomi, Vivo, Realme, Motorola, etc.), Price brackets (<₹20k, ₹20k-₹40k, ₹40k-₹60k, >₹60k), and Sort by (Latest, Highest Rated, Price Low-to-High, Price High-to-Low).
- **Comprehensive Phone Details:** Detailed spec breakdown, categorized score meters, pros and cons, and verified reviews.
- **Interactive Review Submission:** Users and guests can post ratings (1–5 stars), headlines, and detailed feedback.
- **Smart 2-3 Phone Comparison:** Compare devices side-by-side with visual winner badges (`🏆`, `✓`) and comparison verdicts.
- **Targeted Categories:** Quick shortcuts for *Best Overall*, *Best Camera*, *Best Gaming*, *Best Battery*, *Best Display*, and *Best Value for Money*.
- **Live Search:** Instant case-insensitive search by Brand, Model name, or Processor chip.
- **Floating Compare Tray:** Pin up to 3 phones from any page for easy comparison.

### 🛡️ Secure Admin Panel
- **Protected Routes:** Enforced by Spring Security (`hasRole('ADMIN')`).
- **Dashboard Analytics:** Live metrics tracking Total Phones, Registered Users, Total Reviews, and Average Ratings.
- **Review Moderation Queue:** Approve, reject, or permanently delete user reviews before public display.
- **Smartphone CRUD:** Add new devices with full technical specs and verdict parameters, or edit/delete existing records.
- **User Directory:** View all registered accounts, security roles, and registration dates.

---

## 💻 3. Technology Stack

| Layer | Technologies |
|---|---|
| **Backend Language** | Java 21 (LTS) |
| **Framework** | Spring Boot 3.2.5 (Spring MVC, Spring Data JPA, Spring Security) |
| **ORM / Persistence**| Hibernate / JPA |
| **Database** | MySQL 8.0 (with H2 fallback support) |
| **Frontend Templates** | Thymeleaf 3 with Spring Security Dialect |
| **Styling & Icons** | Bootstrap 5.3, Bootstrap Icons, Custom Modern CSS theme |
| **Client Scripting** | JavaScript (ES6+), LocalStorage compare state |
| **Build Tool** | Apache Maven 3.9+ |

---

## 📐 4. Architecture & Project Structure

The project implements a clean **MVC (Model-View-Controller)** pattern with distinct separation of concerns:

```
d:/newjava/
├── pom.xml
├── mvn.cmd
├── README.md
├── src/
│   └── main/
│       ├── java/com/phoneverdict/
│       │   ├── PhoneVerdictApplication.java
│       │   ├── config/
│       │   │   ├── SecurityConfig.java
│       │   │   ├── CustomUserDetailsService.java
│       │   │   ├── DataInitializer.java
│       │   │   └── WebConfig.java
│       │   ├── controller/
│       │   │   ├── HomeController.java
│       │   │   ├── PhoneController.java
│       │   │   ├── ReviewController.java
│       │   │   ├── CompareController.java
│       │   │   ├── CategoryController.java
│       │   │   ├── SearchController.java
│       │   │   ├── AuthController.java
│       │   │   ├── AdminController.java
│       │   │   └── ApiController.java
│       │   ├── dto/
│       │   │   ├── UserDTO.java
│       │   │   ├── ReviewDTO.java
│       │   │   ├── VerdictDTO.java
│       │   │   └── ComparisonResult.java
│       │   ├── model/
│       │   │   ├── Role.java
│       │   │   ├── ReviewStatus.java
│       │   │   ├── User.java
│       │   │   ├── Phone.java
│       │   │   └── Review.java
│       │   ├── repository/
│       │   │   ├── UserRepository.java
│       │   │   ├── PhoneRepository.java
│       │   │   └── ReviewRepository.java
│       │   └── service/
│       │       ├── UserService.java
│       │       ├── PhoneService.java
│       │       ├── ReviewService.java
│       │       └── VerdictService.java
│       └── resources/
│           ├── application.properties
│           ├── static/
│           │   ├── css/style.css
│           │   └── js/main.js
│           └── templates/
│               ├── fragments/
│               │   ├── header.html
│               │   └── footer.html
│               ├── index.html
│               │   ├── phones.html
│               │   ├── phone-detail.html
│               │   ├── compare.html
│               │   ├── compare-result.html
│               │   ├── categories.html
│               │   ├── category-detail.html
│               │   ├── search-results.html
│               │   ├── login.html
│               │   ├── register.html
│               │   ├── error.html
│               │   └── admin/
│               │       ├── dashboard.html
│               │       ├── phones.html
│               │       ├── add-phone.html
│               │       ├── edit-phone.html
│               │       ├── reviews.html
│               │       └── users.html
```

---

## ⚖️ 5. Verdict Engine Math & Scoring Formula

PhoneVerdict calculates an aggregate score out of 10 based on standard tech reviewing criteria:

$$\text{Verdict Score} = (P \times 0.25) + (C \times 0.20) + (B \times 0.15) + (D \times 0.15) + (V \times 0.15) + (U \times 0.10)$$

Where:
- $P$ = Performance Score (0–10) [25%]
- $C$ = Camera & Video Score (0–10) [20%]
- $B$ = Battery & Charging Score (0–10) [15%]
- $D$ = Display Quality Score (0–10) [15%]
- $V$ = Value for Money Score (0–10) [15%]
- $U$ = User Community Rating (Normalized 0–10) [10%]

### Classification Bands:
- **9.0 – 10.0:** `Excellent — Highly Recommended`
- **8.0 – 8.9:** `Very Good — Recommended`
- **7.0 – 7.9:** `Good — Worth Considering`
- **6.0 – 6.9:** `Average`
- **Below 6.0:** `Not Recommended`

---

## 🗄️ 6. Database Setup & Configuration

Configure your database connection inside `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/phone_verdict?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

> **Note:** Hibernate's `ddl-auto=update` and `createDatabaseIfNotExist=true` automatically build the tables (`users`, `phones`, `reviews`) upon application startup. Pre-seeded smartphone data and credentials will load automatically via `DataInitializer.java`.

---

## 🚀 7. How to Run the Application

### Option A: Using Maven (Recommended)
Open a terminal in the project directory:
```bash
./mvn spring-boot:run
```
Or:
```bash
mvn clean spring-boot:run
```

### Option B: Build a JAR and Run
```bash
./mvn clean package
java -jar target/phone-verdict-1.0.0.jar
```

Once started, open your web browser at:
👉 **`http://localhost:8080`**

---

## 🔑 8. Pre-Configured Demo Accounts

| Role | Email Address | Password | Permissions |
|---|---|---|---|
| **Administrator** | `admin@phoneverdict.com` | `admin123` | Full access to `/admin/**`, smartphone catalog CRUD, review approvals/rejections |
| **Regular User** | `user@phoneverdict.com` | `user123` | Submit reviews, browse catalog, compare devices |

---

## 📸 9. Application Showcase (Screenshots)

- **Home Page:** Hero banner, smart device search, and real-time community verdicts.
- **Phone Catalog:** Responsive 3-column grid with live brand & budget filters.
- **Specification & Verdict Sheet:** Interactive score progress bars, pros & cons, verified reviews.
- **Comparison Engine:** Dynamic winner highlighting across RAM, Processor, Camera, and Battery.
- **Admin Dashboard:** Control room for adding smartphones, managing users, and moderating reviews.

---

## 🔮 10. Future Enhancements

- Price history tracking graph with drop notifications.
- Direct benchmark score integration from Geekbench and AnTuTu APIs.
- AI-assisted camera comparison using computer vision side-by-side analysis.
- Multi-currency support (USD, EUR, GBP, INR).
