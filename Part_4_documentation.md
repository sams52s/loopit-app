# 📚 Part 4: Project Documentation

This document provides an overview of the Loopit full-stack GraphQL application including its architecture, schema,
design decisions, and limitations.

---

## 🏗️ 1. System Architecture Overview

```
[Frontend - React + Apollo Client] ↔ [GraphQL API - Spring Boot + Spring Security] ↔ [PostgreSQL DB in Docker]
```

- Frontend communicates via GraphQL queries/mutations to backend
- Backend manages logic, authentication, and data interaction
- PostgreSQL stores persistent data including users, items, and rentals

---

## 🔧 2. GraphQL Schema Documentation

### Mutations

- `createUser(input: CreateUserInput): User`
- `login(email: String!, password: String!): AuthPayload`
- `createItem(input: CreateItemInput): Item`
- `createRental(input: CreateRentalInput): Rental`

### Queries

- `users: [User]`
- `items: [Item]`
- `rentals: [Rental]`

### Types

- `User { id, username, email, role, rentals }`
- `Item { id, title, category, owner, isRented }`
- `Rental { id, item, renter, startDate, endDate }`

### Input Types

- `CreateUserInput { username, email, password }`
- `CreateItemInput { title, category, ownerId }`
- `CreateRentalInput { itemId, renterId, startDate, endDate }`

---

## ✅ 3. Validation & Edge Case Handling

- Duplicate Email: throws `DataIntegrityViolationException` with proper messaging
- Rental Date Overlap: rental logic ensures no conflict with existing rentals
- Login: checks credentials via `AuthenticationManager` and password hash

---

## 🔐 4. Role-Based Permission Control

- Role: `USER`, `ADMIN`
- Currently: All registered users default to `USER`
- Admin features can be extended for:
    - Item approval
    - Rental report access
    - User banning or data control

---

## 🔎 5. Design Decisions

- **GraphQL over REST**: Chosen for flexible and efficient data querying
- **PostgreSQL**: Easy Docker integration, robust relational support
- **Spring Security (non-JWT)**: Simple `AuthenticationManager` based login for demo simplicity
- **No Pagination**: As per the given exam instruction and Whimsical diagram
- **Custom Error Handling**: Ensures user-friendly GraphQL error messages

---

## ⚠️ 6. Known Limitations

- No refresh token/JWT-based session handling
- No client-side form validation
- No file/image upload support
- Admin role logic not fully implemented
- Login endpoint may need improvement for production readiness

---

## 📌 7. Future Improvements (Optional)

- Add JWT-based auth and refresh token support
- Implement file upload (e.g., item photos)
- Add pagination for long lists (optional per instruction)
- Add E2E tests with Cypress or Playwright
- Use role-based directives in GraphQL schema for field-level access control