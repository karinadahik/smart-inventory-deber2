# Sprint 2 — Implementación vertical, eventos y pruebas

**Tipo de documento:** reconstrucción técnica a partir del código, pruebas y evidencias posteriores. No sustituye un acta histórica de cierre del Sprint 2.

## Objetivo (según consigna)
Implementar inventario/reserva, persistencia, evento y consumidores, con pruebas unitarias, integración y contrato.

## Evidencia localizable

| Capacidad | Implementación | Evidencia de prueba |
|---|---|---|
| Inventario paginado, filtros | [`InventoryController`](../../src/main/java/ec/usfq/smartinventory/web/InventoryController.java), [`InventoryQueryService`](../../src/main/java/ec/usfq/smartinventory/application/InventoryQueryService.java) | [`OpenApiContractTest`](../../src/test/java/ec/usfq/smartinventory/contract/OpenApiContractTest.java), SAT-1 |
| Reserva transaccional FEFO | [`ReservationService`](../../src/main/java/ec/usfq/smartinventory/application/ReservationService.java), [`FefoAllocationPolicy`](../../src/main/java/ec/usfq/smartinventory/domain/FefoAllocationPolicy.java) | [`FefoAllocationPolicyTest`](../../src/test/java/ec/usfq/smartinventory/domain/FefoAllocationPolicyTest.java), [`ReservationFlowIT`](../../src/test/java/ec/usfq/smartinventory/ReservationFlowIT.java), SAT-3/4/5 |
| Outbox, evento versionado | [`OutboxWriter`](../../src/main/java/ec/usfq/smartinventory/messaging/OutboxWriter.java), [`OutboxPublisher`](../../src/main/java/ec/usfq/smartinventory/messaging/OutboxPublisher.java), [schema v1](../events/stock-reserved.v1.json) | `ReservationFlowIT` verifica registro y rollback transaccional; no prueba broker real en ese test |
| Consumidores e idempotencia | [`DashboardConsumer`](../../src/main/java/ec/usfq/smartinventory/messaging/DashboardConsumer.java), [`ReplenishmentConsumer`](../../src/main/java/ec/usfq/smartinventory/messaging/ReplenishmentConsumer.java), [`ProcessedEvents`](../../src/main/java/ec/usfq/smartinventory/messaging/ProcessedEvents.java) | SAT-6/7/8, [captura SAT posterior](../sprint-3/capturas/E05_SAT_14PASS.png) |
| Integridad y concurrencia | Migraciones [`V1__init.sql`](../../src/main/resources/db/migration/V1__init.sql), [`V2__seed_demo.sql`](../../src/main/resources/db/migration/V2__seed_demo.sql) | `ReservationFlowIT.concurrentReservationsNeverOversell` |
| Respuesta de error y seguridad | [`GlobalExceptionHandler`](../../src/main/java/ec/usfq/smartinventory/web/GlobalExceptionHandler.java), [`ApiKeyAuthFilter`](../../src/main/java/ec/usfq/smartinventory/web/ApiKeyAuthFilter.java) | SAT-2/5, OpenAPI |

Los contratos `replenishment.suggestion-opened` y `replenishment.suggestion-approved` están en [`docs/events/`](../events/README.md), pero el código actual **no configura una cola consumidora** para esas dos rutas.

## Qué está probado y qué no

- [`ci.yml`](../../.github/workflows/ci.yml) ejecuta `mvn -B verify`; se verificó el **éxito del pipeline de PR #8 y de `main`** en las ejecuciones enlazadas desde el README. Eso sustenta compilación y suite configurada, **no una afirmación sobre resultados aislados de cada test si no se consulta su reporte**.
- El registro original de Sprint 3 recoge 14 verificaciones PASS de SAT-1 a SAT-8. Posteriormente, SAT-9 se ejecutó en laboratorio y obtuvo PASS ([evidencia](../sprint-4/resultados/SAT9_ejecucion.txt)). SAT-10 integral continúa pendiente.
- Las pruebas de integración `ReservationFlowIT` desactivan la publicación a RabbitMQ: validan persistencia/Outbox, no un ciclo real de reintentos/DLQ ante fallos.

## Cierre documental retrospectivo

Evidencia posterior: [Sprint 3](../sprint-3/README.md), [trazabilidad consolidada](../BACKLOG_TRAZABILIDAD.md) y [CI de PR #8](https://github.com/karinadahik/smart-inventory-deber2/actions/runs/45861766455). No consta grabación propia o acta original de revisión del Sprint 2 en el ZIP.
