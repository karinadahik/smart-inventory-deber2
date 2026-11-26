#!/usr/bin/env bash
set -euo pipefail

: "${STAGING_IMAGE:?Missing STAGING_IMAGE}"

echo "Starting temporary staging from: $STAGING_IMAGE"
docker pull "$STAGING_IMAGE"

docker compose -f docker-compose.yml -f docker-compose.ci.yml \
  up -d --no-build postgres rabbitmq app

echo "Checking application health..."
for i in $(seq 1 60); do
  if curl -fsS --max-time 3 \
    http://127.0.0.1:8080/actuator/health >/dev/null 2>&1; then
    echo "PASS: Smart Inventory is healthy"
    docker compose -f docker-compose.yml -f docker-compose.ci.yml ps
    exit 0
  fi
  sleep 5
done

echo "ERROR: application did not become healthy"
docker compose -f docker-compose.yml -f docker-compose.ci.yml logs \
  --no-color --tail=150
exit 1
