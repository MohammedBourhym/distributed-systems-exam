# Docker Compose Startup Guide

## Prerequisites
- Docker and Docker Compose installed
- At least 8GB RAM available
- Ports 5432, 8024, 8080, 8081, 8082, 8088, 8124, 8761, 8888, 9092, 9093 available

## Services Overview

### Infrastructure Services
1. **PostgreSQL** (port 5432) - Database for all services
2. **pgAdmin4** (port 8088) - Database management UI
3. **Axon Server** (ports 8024, 8124) - Event Store and Message Routing
4. **Kafka** (ports 9092, 9093) - Event streaming platform

### Spring Cloud Services
5. **Config Service** (port 8888) - Centralized configuration
6. **Discovery Service** (port 8761) - Eureka service registry
7. **Gateway** (port 8080) - API Gateway with load balancing

### Business Services
8. **Inventory Service** (port 8081) - Product and Category management
9. **Order Service** (port 8082) - Order management

## Startup Order (Automatic)
The docker-compose.yml includes health checks and dependencies to ensure proper startup order:
1. Infrastructure services (postgres, axonserver, kafka)
2. Config Service
3. Discovery Service
4. Gateway
5. Business Services (inventory, order)

## Commands

### Build and Start All Services
```bash
docker-compose up --build
```

### Start in Detached Mode
```bash
docker-compose up -d --build
```

### View Logs
```bash
# All services
docker-compose logs -f

# Specific service
docker-compose logs -f inventory-service
docker-compose logs -f order-service
```

### Stop All Services
```bash
docker-compose down
```

### Stop and Remove Volumes (Clean Start)
```bash
docker-compose down -v
```

### Rebuild Specific Service
```bash
docker-compose up -d --build inventory-service
```

## Access Points

### Web UIs
- **Eureka Dashboard**: http://localhost:8761
- **Axon Server Dashboard**: http://localhost:8024
- **pgAdmin**: http://localhost:8088 (med@gmail.com / azer)
- **API Gateway**: http://localhost:8080

### Service Endpoints (via Gateway)
- **Inventory API**: http://localhost:8080/inventory/**
- **Order API**: http://localhost:8080/orders/**

### Direct Service Access (for debugging)
- **Config Server**: http://localhost:8888
- **Inventory Service**: http://localhost:8081
- **Order Service**: http://localhost:8082

### Swagger/OpenAPI
- **Inventory**: http://localhost:8081/swagger-ui.html
- **Order**: http://localhost:8082/swagger-ui.html

## Troubleshooting

### Service Won't Start
1. Check logs: `docker-compose logs -f <service-name>`
2. Verify dependencies are healthy: `docker-compose ps`
3. Check port conflicts: `netstat -an | grep <port>`

### Database Connection Issues
1. Wait for postgres health check to pass
2. Verify connection: `docker-compose exec postgres psql -U admin -d servicesdb`

### Axon Server Connection Issues
1. Check Axon Server is running: `docker-compose ps axonserver`
2. Verify health: `curl http://localhost:8024/actuator/health`

### Config Server Issues
1. Verify config-repo is mounted: `docker-compose exec config-service ls /config-repo`
2. Test config endpoint: `curl http://localhost:8888/inventory/default`

### Memory Issues
If services crash due to memory:
```bash
# Increase Docker memory limit in Docker Desktop settings
# Or reduce Kafka memory in docker-compose.yml
```

## Testing the Setup

### 1. Check All Services Are Up
```bash
docker-compose ps
```
All services should show "Up" or "Up (healthy)"

### 2. Verify Service Registration
Visit http://localhost:8761 - You should see:
- GATEWAY
- INVENTORY
- ORDER

### 3. Test API via Gateway
```bash
# Create a category
curl -X POST http://localhost:8080/inventory/categories \
  -H "Content-Type: application/json" \
  -d '{"name":"Electronics","description":"Electronic items"}'

# Create a product
curl -X POST http://localhost:8080/inventory/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Laptop","price":999.99,"quantity":10,"categoryId":"<category-id>"}'

# Get all products
curl http://localhost:8080/inventory/products
```

## Development Workflow

### Make Changes and Rebuild
```bash
# 1. Make code changes
# 2. Rebuild specific service
docker-compose up -d --build inventory-service

# 3. Check logs
docker-compose logs -f inventory-service
```

### Clean Restart
```bash
docker-compose down -v
docker-compose up --build
```

## Production Considerations
- Use environment-specific configuration files
- Implement proper secrets management
- Add monitoring (Prometheus, Grafana)
- Configure resource limits
- Set up log aggregation (ELK stack)
- Implement backup strategies for databases
