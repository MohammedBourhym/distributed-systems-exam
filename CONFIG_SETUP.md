# Spring Cloud Config Setup - Summary

## Overview
Successfully configured centralized configuration management using Spring Cloud Config Server with local file-based storage.

## Components

### 1. Config Server (`config-service`)
- **Port**: 8888
- **Profile**: native (file-based)
- **Config Location**: `file://${user.home}/cqrs/config-repo`
- **Main Class**: `ConfigServiceApplication` with `@EnableConfigServer`

### 2. Configuration Repository (`/home/boo/cqrs/config-repo`)
Contains centralized configuration files:

#### `inventory.properties`
- Server port: 8081
- Database: PostgreSQL (localhost:5432/servicesdb)
- Axon Server: localhost:8124
- Eureka: localhost:8761

#### `order.properties`
- Server port: 8082
- Database: PostgreSQL (localhost:5432/servicesdb)
- Axon Server: localhost:8124
- Eureka: localhost:8761

### 3. Client Services

#### Inventory Service
- **application.properties**:
  ```properties
  spring.application.name=inventory
  spring.config.import=optional:configserver:http://localhost:8888
  ```
- Fetches configuration from Config Server on startup

#### Order Service
- **application.properties**:
  ```properties
  spring.application.name=order
  spring.config.import=optional:configserver:http://localhost:8888
  ```
- Fetches configuration from Config Server on startup

## Dependencies Added

### All Services (inventory, order)
- `spring-cloud-starter-config` - Config client
- `spring-cloud-starter-netflix-eureka-client` - Service discovery
- Spring Cloud BOM version: 2025.0.1

### Config Service
- `spring-cloud-config-server` - Config server

## How It Works

1. **Config Server Startup**: 
   - Run `./mvnw spring-boot:run` in `/home/boo/cqrs/config-service`
   - Serves configurations from `/home/boo/cqrs/config-repo`

2. **Service Startup**:
   - Services connect to Config Server at `http://localhost:8888`
   - Fetch their configuration based on `spring.application.name`
   - `inventory` service gets `inventory.properties`
   - `order` service gets `order.properties`

3. **Configuration Access**:
   - Config Server exposes: `http://localhost:8888/{application-name}/default`
   - Example: `http://localhost:8888/inventory/default`

## Verification Status
✅ All services compile successfully
✅ Config Server configured with native profile
✅ Configuration files created in config-repo
✅ Client services configured to use Config Server
✅ Spring Cloud dependencies properly managed

## Next Steps
1. Start Config Server first
2. Start Inventory and Order services
3. Verify configuration loading from logs
4. (Optional) Add Spring Cloud Bus for dynamic configuration refresh
