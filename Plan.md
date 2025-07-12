# Teebay Project README

## Introduction

Welcome to the Teebay project! This application aims to provide a secure and user-friendly platform for listing, buying,
and renting products. Our structured improvement roadmap focuses on enhancing architecture, maintainability, and user
experience through clear modularization, robust backend services, and responsive frontend interfaces. The project
emphasizes best practices in authentication, data modeling, GraphQL API design, and comprehensive testing strategies.

---

## Phase 1: Functional Scope Refinement

- Implement secure and intuitive user registration and login using JWT-based authentication.
- Enable users to create, edit, and delete products via multi-step forms with input validation.
- Provide capabilities for buying, renting, and viewing product listings.
- Track ownership, borrowing, and sales history accurately.
- Add input validation both on the frontend (React Hook Form/Yup) and backend (DTO validation).

---

## Phase 2: Backend Architecture & Code Quality

- Use Spring Boot (v3.4+) with GraphQL and PostgreSQL for backend services.
- Implement JWT-based authentication with proper session and token management (e.g., HttpOnly cookies or localStorage).
- Create dedicated DTOs for mutation input and output to enforce data integrity.
- Add centralized exception handling and logging for GraphQL endpoints.
- Use `@Transactional` annotations for service methods where needed.
- Enforce database constraints and input validations at the entity level.
- Normalize categories into a separate table.
- Use UUIDs for entity IDs to ensure uniqueness and scalability.

---

## Phase 3: GraphQL API Best Practices

- Modularize GraphQL schema into Query, Mutation, and Types for clarity.
- Add schema documentation and use enums for category types.
- Implement pagination for product listings to improve performance.
- Support partial updates via optional GraphQL input fields.
- Enforce database constraints and handle edge cases such as duplicate entries.

---

## Phase 4: Frontend (React + Apollo)

- Build reusable components for product cards, input fields, and multi-step forms.
- Add global loading and error handling UI to enhance user experience.
- Store authenticated user context using React Context API or Zustand for state management.
- Automatically redirect users upon successful login or registration.
- Implement role-based UI control to show or hide buy/rent buttons based on user roles.
- Manage authentication tokens securely and persist session state.

---

## Phase 5: Data Modeling Enhancements & Additional Features

- Normalize product categories into a dedicated table.
- Add a transaction table to log all buy/rent activities.
- Include timestamps on products to track listing history.
- Use UUIDs for all entity IDs for consistency.
- Handle edge cases such as renting overlaps with timestamp conflict resolution.
- Implement soft deletes or restrict deletion for in-use products.
- Add pagination support and form step navigation state persistence.

---

## Phase 6: Testing & Quality Assurance

- Write unit tests for service layer logic using Mockito.
- Add GraphQL endpoint tests with `spring-graphql-test`.
- Perform component and UI tests using `@testing-library/react`.
- Cover edge cases such as database constraint violations (e.g., duplicate emails).
- Create comprehensive documentation (`Part_4_documentation.md`) covering architecture, endpoints, schema, and known
  edge cases.

---

## 📘 Documentation & Deployment

- Provide a clear architectural overview and detailed documentation of GraphQL schema and API endpoints.
- Include `.env` and `.env.sample` files for environment configuration.
- Use Docker for local deployment with separate services for frontend, backend, and database.
- Add health checks and document port configurations in the README.
- Maintain well-organized commits with descriptive messages and inline comments/Javadoc.

---

Thank you for exploring the Teebay project! We strive to maintain a clean, scalable, and user-centric application
aligned with modern development best practices.