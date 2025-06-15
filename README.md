# loopit-app
A social marketplace to rent, buy, and share products — built with Spring Boot, GraphQL, React, and Apollo.


---

## 🚀 To-Do Planning

### ✅ Phase 1: Project Setup & Architecture

- [x] Initialize Spring Boot backend and React frontend
- [x] Set up GraphQL with Apollo Client and Server
- [x] Configure PostgreSQL database and Docker support
- [x] Define base folder structure and naming conventions

### 🔒 Phase 2: User Module (Backend)

- [x] Create `User` entity with validation and audit fields
- [x] Implement secure password hashing with BCrypt
- [x] Build CRUD operations with GraphQL (`createUser`, `updateUser`, `softDeleteUser`, `getUsers`)
- [x] Add `login` mutation with credential validation
- [x] Setup `UserDto` and `UserMapper` for clean data output
- [x] Write unit tests for create and login

### 🛠️ Phase 3: Product Module (Backend)

- [ ] Design `Product` entity and relationships (User, Category, etc.)
- [ ] Implement GraphQL queries and mutations (createProduct, getProducts, etc.)
- [ ] Add DTOs, Mapper, and validation
- [ ] Write unit and integration tests

### 🎨 Phase 4: Frontend Integration

- [ ] Create user registration and login forms (React + Apollo)
- [ ] Integrate GraphQL queries and mutations with UI
- [ ] Add state management for authentication (e.g., token or session state)
- [ ] Display product list and detail views

### 📦 Phase 5: Enhancements & Add-ons

- [ ] Add user roles and access control (Admin, Renter, Owner)
- [ ] Enable product search and filtering
- [ ] Add image upload support
- [ ] Email verification or OTP for signup
- [ ] Add notifications and activity logs

---
