LOCAL_POSTGRES_URL=jdbc:postgresql://localhost:5432/product_configurator
LOCAL_POSTGRES_USER=product_configurator
LOCAL_POSTGRES_PASSWORD=product_configurator

.PHONY=start-test-db migrate prune connect

start-db:
	docker-compose -f docker-compose.yml up -d --force-recreate


start-test-db:
	docker-compose -f docker-compose.test.yml up -d --force-recreate


migrate: export FLYWAY_URL=$(LOCAL_POSTGRES_URL)
migrate: export FLYWAY_USER=$(LOCAL_POSTGRES_USER)
migrate: export FLYWAY_PASSWORD=$(LOCAL_POSTGRES_PASSWORD)
migrate: start-db
	gradle flywayMigrate

prune: export FLYWAY_URL=$(LOCAL_POSTGRES_URL)
prune: export FLYWAY_USER=$(LOCAL_POSTGRES_USER)
prune: export FLYWAY_PASSWORD=$(LOCAL_POSTGRES_PASSWORD)
prune:
	gradle flywayClean

connect:
	docker-compose exec db psql -U $(LOCAL_POSTGRES_USER)