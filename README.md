# Smart Inventory · Proyecto completo (Deber 2 — Desarrollo de Software Moderno, USFQ)

Implementación de un recorrido vertical: **consultar inventario → reservar con FEFO e idempotencia → persistir evento mediante Outbox → consumirlo en dashboard y reposición**, con CI/CD DevSecOps, métricas, pruebas de carga y trazabilidad.

## Estado verificado de la entrega

- **Versión técnica publicada:** [v1.0.0](https://github.com/karinadahik/smart-inventory-deber2/releases/tag/v1.0.0), commit `1cabba8` (26-11-2026).
- **[PR #8 fusionado](https://github.com/karinadahik/smart-inventory-deber2/pull/8)**; [CI de PR](https://github.com/karinadahik/smart-inventory-deber2/actions/runs/45861766455) exitoso.
- **[CI de main](https://github.com/karinadahik/smart-inventory-deber2/actions/runs/45863252563)** exitoso; [CI del tag/release](https://github.com/karinadahik/smart-inventory-deber2/actions/runs/45864706766) exitoso.
- Este README y la documentación adicional de Sprints 1/2 son una **revisión documental posterior al release**. Si se utiliza este ZIP corregido antes de integrarla en GitHub, el repositorio etiquetado `v1.0.0` conserva la documentación original.

## Ejecutar y verificar localmente

Se requiere Java 21, Maven, Docker/Compose y, para SAT, `curl` y `jq`.

```bash
docker compose up -d --build     # postgres, rabbitmq, app, prometheus, grafana
curl -fsS http://localhost:8080/actuator/health
mvn -B verify                   # unitarias, contrato e integración con Testcontainers (requiere Docker)
bash scripts/sat.sh             # SAT-1..8 (ver docs/sat.md)
# opcional: tracing con Jaeger
docker compose -f docker-compose.yml -f docker-compose.tracing.yml up -d
# opcional: k6, con la API iniciada y k6 instalado
API_KEY=dev-ops-key k6 run scripts/k6/inventory-read.js
```

**Servicios locales:** Swagger UI `http://localhost:8080/swagger-ui.html`; Prometheus `http://localhost:9090`; RabbitMQ `http://localhost:15672`; Grafana `http://localhost:3000`; Jaeger con override `http://localhost:16686` (el puerto de Jaeger puede estar mapeado solo a loopback). Para correr SAT de nuevo, comenzar con un entorno de datos de prueba limpio, ya que muta el stock y las sugerencias.

**Solo credenciales de desarrollo:** `X-Api-Key: dev-ops-key`, `dev-fulfillment-key`, `dev-purchasing-key`. **No usar en producción.** Las credenciales reales se inyectan desde secretos/variables de entorno; no se entregan `.env` ni credenciales privadas.

## Sprints y evidencias

| Etapa | Evidencia y alcance |
|---|---|
| **Sprint 1** — requisitos, backlog, arquitectura y contratos | [`docs/sprint-1/README.md`](docs/sprint-1/README.md), [`docs/BACKLOG_TRAZABILIDAD.md`](docs/BACKLOG_TRAZABILIDAD.md), `openapi/openapi.yaml`, `docs/adr/`, `docs/sat.md` |
| **Sprint 2** — implementación y pruebas | [`docs/sprint-2/README.md`](docs/sprint-2/README.md), `src/main/`, `src/test/`, esquema de evento en `docs/events/` |
| **Sprint 3** — DevSecOps, resiliencia, SAT/DAST | [`docs/sprint-3/README.md`](docs/sprint-3/README.md), [`docs/sprint-3/CIERRE.md`](docs/sprint-3/CIERRE.md), `docs/experiments/`, capturas |
| **Sprint 4** — métricas, trazas, k6, alertas, release | [`docs/sprint-4/README.md`](docs/sprint-4/README.md), resultados, capturas E00–E10, Grafana JSON |

**Índice de rúbrica y enlaces comprobables:** [`docs/ENTREGA_Y_RUBRICA.md`](docs/ENTREGA_Y_RUBRICA.md). **Guion para demostración de 10–12 minutos:** [`docs/DEMO_FINAL_10_12_MIN.md`](docs/DEMO_FINAL_10_12_MIN.md) (**no es una grabación; falta enlace real si se exige**).

Los documentos de Sprint 1 y 2 son reconstrucciones de cierre basadas en artefactos verificables, **no evidencia original de reuniones de sprint**.

## Historias y trazabilidad

| Historia | Endpoint / evento | Código | Prueba |
|---|---|---|---|
| US-01 — stock | `GET /api/v1/inventory` | `InventoryController`, `InventoryQueryService` | `OpenApiContractTest`, SAT-1 |
| US-02 — reserva FEFO | `POST /api/v1/reservations`, evento `inventory.stock-reserved` | `ReservationService`, `FefoAllocationPolicy`, `OutboxWriter` | `FefoAllocationPolicyTest`, `ReservationFlowIT`, SAT-3..5 |
| US-03 — reposición | `GET /api/v1/replenishment/suggestions`, aprobación | `ReplenishmentConsumer`, `ReorderPointRule` | `ReorderPointRuleTest`, SAT-7/8 |
| US-04 — dashboard | `GET /api/v1/dashboard/inventory` | `DashboardConsumer`, `DashboardQueryService` | SAT-6 |

## Arquitectura, decisiones y pruebas

Paquetes: `web` (filtros, API, errores), `application` (casos de uso y transacciones), `domain` (reglas FEFO/reposición), `messaging` (Outbox/RabbitMQ/consumidores), `ratelimit` (token bucket y ventana fija). Las decisiones están justificadas en [`docs/adr/`](docs/adr/). Contrato HTTP: [`openapi/openapi.yaml`](openapi/openapi.yaml); evento v1: [`docs/events/stock-reserved.v1.json`](docs/events/stock-reserved.v1.json).

El workflow [`.github/workflows/ci.yml`](.github/workflows/ci.yml) ejecuta `mvn -B verify`, escaneo de secretos, SAST, SCA, empaquetado, SBOM y análisis de imagen; en PR ejecuta staging/SAT/DAST pasivo; en `main`, staging y DAST; con tag `v*`, release. `mvn verify` ha aprobado dentro de CI para este corte. Las 14 verificaciones SAT-1..8 están respaldadas por captura en `docs/sprint-3/capturas/E05_SAT_14PASS.png`.

## Observabilidad, operación y límites de la evidencia

- Dashboard Prometheus/Grafana, cinco reglas de alerta y tests sintéticos de tres escenarios: `docs/sprint-4/`, `ops/`.
- k6 GET inventario, **2 usuarios virtuales / 30 s / 30 peticiones**, p95 **18,73 ms**, 0 % HTTP errores: carga ligera, **no** máxima capacidad y **no** prueba completa de reserva.
- Jaeger recibe trazas HTTP y de tareas Outbox; **no demuestra** cadena distribuida completa entre servicios. Logs y `X-Request-Id` permiten correlación.
- [`docs/runbook.md`](docs/runbook.md): diagnóstico de retrasos en dashboard. Fallo deliberado en CI y recuperación verificados; **no hubo rollback real a una versión persistente anterior**.
- SAT-9 (parada y recuperación real de broker) y SAT-10 (consulta **y reserva** bajo carga) figuran en el guion, pero **no tienen evidencia de ejecución integral adjunta**.
- La semilla `V2__seed_demo.sql` tiene fechas de expiración fijas en 2026/2027; para ejecutar FEFO después de esas fechas deben actualizarse los datos de demo en un entorno nuevo, sin alterar pruebas históricas.
- No se adjuntó una **grabación** de la demo ni actas originales de los Sprints 1/2; si el docente las exige, deben aportarse las evidencias reales.

**Reproducibilidad del release:** [repositorio](https://github.com/karinadahik/smart-inventory-deber2) · [PR #8](https://github.com/karinadahik/smart-inventory-deber2/pull/8) · [tag/release v1.0.0](https://github.com/karinadahik/smart-inventory-deber2/releases/tag/v1.0.0).
