# Teebay - Product Renting and Buying/Selling Application

Welcome to the Teebay project! This application is designed to enable users to rent, buy, and sell products through a
simple and intuitive interface. The development is structured into multiple phases, each focusing on incremental
improvements in functionality, code quality, and user experience. Below is a detailed roadmap and checklist to guide the
implementation and evaluation of the project.

---

## Phase 1: Initial Setup and Basic Features

- [ ] Implement user registration and login with simple string matching authentication.
- [ ] Set up the backend with Spring Boot and GraphQL.
- [ ] Establish the frontend using React and Apollo Client for GraphQL communication.
- [ ] Create basic UI components for login and registration.
- [ ] Connect frontend and backend with GraphQL queries and mutations.
- [ ] Configure PostgreSQL database and basic data models.

---

## Phase 2: Authentication & Data Validation Enhancements

- [ ] Implement JWT-based authentication for secure user sessions.
- [ ] Store JWT tokens securely on the client side (e.g., HttpOnly cookies or localStorage).
- [ ] Add comprehensive field validations on both frontend and backend (e.g., email format, password strength).
- [ ] Implement exception handling and meaningful error messages in backend GraphQL resolvers.
- [ ] Secure GraphQL endpoints to ensure only authenticated users can access relevant queries and mutations.
- [ ] Improve UI feedback for authentication and validation errors.

---

## Phase 3: Data Modeling & Business Logic Improvements

- [ ] Introduce UUIDs as unique identifiers for users and products.
- [ ] Enforce database constraints such as unique fields and foreign key relationships.
- [ ] Normalize product categories and enable multiple categories per product.
- [ ] Implement product management features: add, edit, delete products with multi-page forms matching wireframe design.
- [ ] Ensure data integrity and consistency in product operations.
- [ ] Handle edge cases such as rent time overlaps and product availability.

---

## Phase 4: UI/UX Refinement & State Management

- [ ] Develop reusable React components for forms, product cards, buttons, and navigation.
- [ ] Implement loading and error handling states in the UI.
- [ ] Use React Context or state management libraries to manage global application state.
- [ ] Enhance form navigation allowing users to go back and forth between multi-page forms without losing data.
- [ ] Improve user experience with input validation feedback and confirmation dialogs.
- [ ] Optimize Apollo Client cache management for efficient data updates and removals.

---

## Phase 5: Advanced Features & Role-Based Access

- [ ] Add testing for frontend components and backend GraphQL resolvers.
- [ ] Implement role-based UI and permissions for different user types.
- [ ] Introduce pagination for product listings to handle large datasets efficiently.
- [ ] Implement soft deletes for products to maintain data history.
- [ ] Enhance security measures and input sanitization.
- [ ] Provide comprehensive user activity views for bought, sold, rented, and lent products.

---

## Phase 6: Testing Strategy & Documentation

- [ ] Write unit and integration tests for backend services and GraphQL endpoints.
- [ ] Develop frontend tests covering React components and Apollo queries/mutations.
- [ ] Perform end-to-end testing to validate full user workflows.
- [ ] Prepare `Part_4_documentation.md` detailing architecture, design decisions, corner cases, and implementation
  notes.
- [ ] Document any assumptions, limitations, and future improvement plans.

---

## 📘 Documentation & Deployment

- Provide a clear architecture overview including frontend, backend, and database interactions.
- Include a `.env` file template with necessary environment variables.
- Supply Docker configuration files to enable easy local deployment.
- Document setup and run instructions in the README.
- Ensure test running instructions and coverage reports are included.
- Provide a short demonstration video link showcasing key features and workflows.

---

Thank you for exploring the Teebay project roadmap. We encourage you to follow this structured plan to build a robust,
maintainable, and user-friendly product renting and selling platform.