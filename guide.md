## 4. Customer-Service (CQRS + Event Sourcing)

### 4.1 Command Side

#### Step 1: Define Aggregate
- `CustomerAggregate`
- Aggregate Identifier: `customerId`
- Business attributes:
  - name
  - address
  - email
  - phone

#### Step 2: Define Commands
- `CreateCustomerCommand`
- `UpdateCustomerCommand`

#### Step 3: Define Events
- `CustomerCreatedEvent`
- `CustomerUpdatedEvent`

#### Step 4: Implement Aggregate Logic
- Validate business rules
- Apply events using `AggregateLifecycle.apply()`
- Handle events with `@EventSourcingHandler`

#### Step 5: Command REST Controller
- Expose endpoints:
  - POST /customers
  - PUT /customers/{id}

---

### 4.2 Query Side

#### Step 6: Define Projection Entity
- `CustomerEntity`
- Annotated with `@Entity`

#### Step 7: Handle Events
- `@EventHandler` for customer events
- Update MySQL read model

#### Step 8: Define Queries
- `GetCustomerByIdQuery`
- `GetAllCustomersQuery`

#### Step 9: Implement Query Handlers
- Fetch data from MySQL
- Return optimized read data

---


## 5. Inventory-Service (CQRS + Event Sourcing)

### 5.1 Command Side

#### Step 1: Define Aggregates
- `ProductAggregate`
- `CategoryAggregate`

#### Step 2: Define Commands
- `CreateCategoryCommand`
- `CreateProductCommand`
- `UpdateStockCommand`

#### Step 3: Define Events
- `CategoryCreatedEvent`
- `ProductCreatedEvent`
- `StockUpdatedEvent`

#### Step 4: Implement Aggregates
- Validate stock availability
- Apply state transitions
- Handle events with Event Sourcing Handlers

---

### 5.2 Query Side

#### Step 5: Define Projection Entities
- `ProductEntity`
- `CategoryEntity`

#### Step 6: Event Handlers
- Listen to inventory events
- Update read database

#### Step 7: Queries
- `GetProductByIdQuery`
- `GetProductsByCategoryQuery`

#### Step 8: Query Handlers
- Read from MySQL

---

## 6. Order-Service (CQRS + Event Sourcing)

### 6.1 Command Side

#### Step 1: Define Aggregate
- `OrderAggregate`

#### Step 2: Define Commands
- `CreateOrderCommand`
- `AddOrderLineCommand`
- `ValidateOrderCommand`
- `CancelOrderCommand`

#### Step 3: Define Events
- `OrderCreatedEvent`
- `OrderLineAddedEvent`
- `OrderValidatedEvent`
- `OrderCancelledEvent`

#### Step 4: Implement Aggregate Logic
- Order lifecycle management
- State transitions
- Business validations

---

### 6.2 Query Side

#### Step 5: Projection Entities
- `OrderEntity`
- `OrderLineEntity`

#### Step 6: Event Handlers
- Update order read models

#### Step 7: Queries
- `GetOrderByIdQuery`
- `GetOrdersByCustomerQuery`

#### Step 8: Query Handlers
- Serve frontend requests

---

## 7. Inter-Microservice Communication

### 7.1 Event Propagation
- Domain events published through Axon
- Events forwarded to Kafka
## 8. Real-Time Analytics Service (Kafka Streams)

### Step 1: Consume Kafka Topics
- Order events
- Order validated events

### Step 2: Kafka Streams Processing
- Group by time window (5 seconds)
- Count orders
- Calculate total amount

### Step 3: Store Results
- In-memory store or database
- Expose REST endpoint

---

## 9. Frontend Application

### Step 1: Connect to API Gateway
- All calls go through Gateway

### Step 2: Read Data
- Query APIs

### Step 3: Real-Time Updates
- SSE or WebSocket
- Live analytics dashboard

---

## 10. Security

### Step 1: Authentication
- OAuth2 / OIDC with Keycloak
- Or JWT with Spring Security

### Step 2: Authorization
- Role-based access
- Secure Gateway and microservices

---

## 11. Deployment

### Step 1: Dockerize All Services
- One Dockerfile per microservice

### Step 2: docker-compose.yml
- Axon Server
- Kafka + Zookeeper
- MySQL
- All microservices
- Gateway
- Discovery Service
- Keycloak

---
