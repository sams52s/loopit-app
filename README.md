# loopit-app

Loopit is a social marketplace platform designed to enable users to rent, buy, and share products seamlessly. This
project is built with a modular architecture leveraging Spring Boot, GraphQL, React, and Apollo to ensure
maintainability, scalability, and a smooth user experience. The development roadmap focuses on secure authentication (
JWT), robust DTO-based backend layers, modular GraphQL schemas, reusable and state-managed frontend components, and
comprehensive testing and documentation for production-ready deployment.

---

## 🚀 To-Do Planning

### ✅ Phase 1: Project Setup & Architecture

- [x] Initialize Spring Boot backend and React frontend with modular structure
- [x] Set up GraphQL server and Apollo Client integration
- [x] Configure PostgreSQL database with Docker support for local development
- [x] Define folder structure, naming conventions, and base configurations

### 🔒 Phase 2: User Module (Backend)

- [x] Create `User` entity with validation annotations and audit fields (createdAt, updatedAt)
- [x] Implement secure password hashing using BCrypt
- [x] Build CRUD operations with GraphQL mutations and queries (`createUser`, `updateUser`, `softDeleteUser`,
  `getUsers`)
- [x] Add `login` mutation with credential validation and error handling
- [ ] Implement JWT-based authentication with token generation and validation
- [ ] Manage session and token storage using HttpOnly cookies or localStorage strategies
- [x] Setup `UserDto` and `UserMapper` for clean and secure data output
- [ ] Add field-level validations and global exception handling for user operations
- [x] Write unit tests covering user creation, login, and validation logic

### 🛠️ Phase 3: Product Module (Backend)

- [ ] Design `Product` entity with UUID primary keys and relationships to User, Category, and Transaction entities
- [ ] Implement DTOs and Mappers for product input and output with validation constraints
- [ ] Enforce entity-level constraints such as unique fields, non-nullable attributes, and business rules
- [ ] Normalize category data and manage category relationships
- [ ] Develop GraphQL queries and mutations (`createProduct`, `updateProduct`, `getProducts`, etc.)
- [ ] Establish and enforce relationships between Product, User (owner/renter), Category, and transaction tables
- [ ] Write unit and integration tests to cover product logic and data consistency

### 🎨 Phase 4: Frontend Integration

- [ ] Create user registration and login forms using React and Apollo Client
- [ ] Integrate GraphQL queries and mutations with frontend UI components
- [ ] Develop reusable UI components such as product cards, input fields, and multi-step forms
- [ ] Implement error handling and loading states for all asynchronous operations
- [ ] Add state management for authentication and user data using React Context API or Zustand
- [ ] Implement redirection logic after login, registration, and logout flows
- [ ] Display product list and detail views with role-based UI controls and permissions

### 📦 Phase 5: Enhancements & Add-ons

- [ ] Add user roles (Admin, Renter, Owner) and implement role-based access control on backend and frontend
- [ ] Enable product search, filtering, and pagination features
- [ ] Add image upload support for products with validation and storage handling
- [ ] Implement email verification or OTP-based signup confirmation
- [ ] Add notifications, activity logs, and audit trails for user and product actions
- [ ] Implement verification and approval steps for product creation and sensitive operations
- [ ] Implement soft delete functionality for products and users with appropriate filtering in queries
- [ ] Write comprehensive tests covering new features and edge cases

### 🧪 Phase 6: Testing & Documentation

- [ ] Write unit tests for backend services, GraphQL resolvers, and mappers
- [ ] Write integration tests for end-to-end GraphQL queries and mutations
- [ ] Add component and UI tests for key frontend parts using testing libraries
- [ ] Create architectural documentation including schema overviews and module interactions (`Part_4_documentation.md`)
- [ ] Document environment variables, Docker setup, and deployment instructions
- [ ] Maintain up-to-date README and developer guides for onboarding and contribution

---

## 📘 Documentation & Deployment

The project includes detailed architectural documentation outlining the modular backend and frontend design, GraphQL
schema structure, and data flow. Environment variables and configuration files are documented for seamless deployment
using Docker containers. Testing strategies cover unit, integration, and UI tests to ensure robustness and
maintainability. The documentation also covers deployment instructions for cloud or local environments, ensuring the
project is production-ready and easy to extend.
