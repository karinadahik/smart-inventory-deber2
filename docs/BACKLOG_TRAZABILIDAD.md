# Backlog priorizado y matriz de trazabilidad — Smart Inventory

**Fuente:** historias US-01 a US-04 y requisitos no funcionales de la consigna académica; correspondencia revisada contra los archivos existentes del proyecto. **Esta es una reconstrucción documental de la entrega**, no un export de tablero histórico de Jira/Trello ni un registro de estimaciones reales del equipo.

## Prioridad y vínculos

| Prioridad | Historia | Aceptación verificable | Endpoint o evento | Implementación | Pruebas/evidencias |
|---|---|---|---|---|---|
| P1 | **US-01** Consultar stock por SKU, ubicación y lote | Filtros, orden/paginación; físico, reservado, disponible; respuesta documentada si no hay stock | `GET /api/v1/inventory` | `InventoryController`, `InventoryQueryService` | `OpenApiContractTest`, SAT-1, `openapi/openapi.yaml` |
| P1 | **US-02** Reserva FEFO e idempotente | Reserva atómica; primero lote de vencimiento anterior y no vencido; repetición no duplica reserva; concurrencia no sobreasigna | `POST /api/v1/reservations` y `inventory.stock-reserved` | `ReservationService`, `FefoAllocationPolicy`, `OutboxWriter` | `FefoAllocationPolicyTest`, `ReservationFlowIT`, SAT-3/4/5 |
| P2 | **US-03** Sugerencias de reposición | Regla de umbral, una sugerencia OPEN por SKU, aprobación con transición válida, idempotencia por evento | `GET/POST /api/v1/replenishment/suggestions` | `ReorderPointRule`, `ReplenishmentConsumer`, `ReplenishmentService` | `ReorderPointRuleTest`, SAT-7/8 |
| P2 | **US-04** Dashboard actualizado por eventos | Consumo eventual, `lastUpdatedAt`, `processingDelayMs`, `stale` | `GET /api/v1/dashboard/inventory` | `DashboardConsumer`, `DashboardQueryService` | SAT-6, [runbook](runbook.md) |

## NFR prioritarios

| NFR | Evidencia implementada | Estado de verificación y límite |
|---|---|---|
| Seguridad por cliente/scope y errores coherentes | `ApiKeyAuthFilter`, `GlobalExceptionHandler`, `openapi/openapi.yaml` | SAT-2 y CI/SAST/SCA; API keys solo de desarrollo, no OIDC de producción |
| Integridad bajo concurrencia | Bloqueo SQL FEFO, `CHECK`, `ReservationFlowIT` | Suite CI `mvn verify` verde; test de 50 hilos existe |
| Idempotencia y entrega de eventos | `OutboxWriter`, `ProcessedEvents`, colas/DLQ | Outbox probado transaccionalmente; recuperación real de broker/DLQ no cubierta por SAT-9 ejecutado |
| Rate limit y resiliencia | `RateLimitFilter`, `TokenBucketLimiter`, `FixedWindowLimiter` | Test comparativo y captura E14 de Sprint 3; limitación por instancia |
| Rendimiento p95 | `scripts/k6/inventory-read.js`, `perf/reserve.js` | Línea base GET: p95 18,73 ms, 30 respuestas 200; **sin evidencia adjunta de ejecución de carga de reserva** |
| Operabilidad | Actuator, Prometheus, Grafana, Jaeger, alertas y `docs/runbook.md` | Paneles/logs/trazas recibidas; no se probó cadena distribuida completa ni envío de alertas |

## Calidad y limitaciones complementarias

Consultar [calidad del pipeline y límites no acreditados](CALIDAD_Y_LIMITACIONES.md), [triaje DAST](security/DAST_TRIAGE.md) y [topología de eventos](events/README.md).

## Plan de verificación y prioridades pendientes

1. **Comprobado:** CI de PR #8 y `main` (#45863252563), staging temporal, SAT-1 a SAT-8, DAST pasivo, trazas recibidas y release etiquetado.
2. **Pendiente o limitado:** SAT-10 de carga de reserva, notificación real de alertas, rollback persistente, demostración grabada si el docente la exige.
3. **Defectos tratados:** cabeceras HTTP detectadas con ZAP y corregidas en Sprint 3; exportación OTLP y métricas detalladas de RabbitMQ corregidas en Sprint 4. Ver documentación de esos sprints.

[Historial de los sprints](ENTREGA_Y_RUBRICA.md) · [Guion de demostración](DEMO_FINAL_10_12_MIN.md)
