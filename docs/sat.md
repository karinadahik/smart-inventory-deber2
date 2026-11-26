# Guion SAT — aceptación del sistema

**Precondición:** `docker compose up -d --build` con datos semilla adecuados a la fecha de ejecución. `scripts/sat.sh` automatiza los escenarios SAT-1 a SAT-8 y valida determinados códigos y valores; **no valida por sí solo todos los subcriterios descritos**. Evidencia disponible: [14 comprobaciones PASS](sprint-3/capturas/E05_SAT_14PASS.png) y trabajos SAT de GitHub Actions.

**Responsable registrado:** para SAT-1 a SAT-8 se consigna el **rol equipo de desarrollo / ejecutor de CI**, no un nombre personal porque no existe asignación nominativa acreditada. SAT-9/10 están pendientes de ejecución y de asignación.

| ID | Historia | Paso | Resultado esperado | Observado / evidencia | Responsable (rol) |
|---|---|---|---|---|---|
| SAT-1 | US-01 | `GET /api/v1/inventory?sku=SKU-1001` | 200; cantidades por lote | **PASS HTTP 200** en `scripts/sat.sh`; la comprobación automatizada no asegura todos los campos de la respuesta | Equipo de desarrollo / CI |
| SAT-2 | Seguridad | Sin `X-Api-Key` / scope insuficiente | 401 / 403 con error documentado | **PASS 401 y 403**; el script no verifica el `Content-Type` de ambas respuestas | Equipo de desarrollo / CI |
| SAT-3 | US-02 | Reservar 35 u. de SKU-1001 | 201, asignación FEFO L-B antes que L-A, excluye vencidos | **PASS HTTP 201**; el script no inspecciona lotes asignados (FEFO respaldado además por test unitario) | Equipo de desarrollo / CI |
| SAT-4 | US-02 | Repetir la petición con misma clave | 200, replay, mismo ID, sin duplicar reserva | **PASS 200 y mismo `reservationId`**; el script no comprueba cabecera `Idempotent-Replay` ni conteo reservado | Equipo de desarrollo / CI |
| SAT-5 | US-02 | Reservar 99 999 u. | 409 y stock intacto | **PASS HTTP 409 y `INSUFFICIENT_STOCK`**; el script no comprueba saldo posterior | Equipo de desarrollo / CI |
| SAT-6 | US-04 | Consultar dashboard tras reservar (≤15 s) | Fila actualizada y `lastUpdatedAt`, `processingDelayMs`, `stale=false` | **PASS presencia de SKU-1001**; no comprueba los tres campos ni `stale=false` | Equipo de desarrollo / CI |
| SAT-7 | US-03 | Reserva SKU-2002 y consultar sugerencias | Única sugerencia OPEN y duplicados sin efecto | **PASS sugerencia OPEN y cardinalidad 1**; script permite 201/409 de la reserva, sin reinyectar duplicados | Equipo de desarrollo / CI |
| SAT-8 | US-03 | Aprobar dos veces | 200 primera, 409 segunda | **PASS 200 y 409** | Equipo de desarrollo / CI |
| SAT-9 | Disponibilidad | Detener RabbitMQ; reservar y restablecer broker | 201 y evento Outbox entregado tras recuperación | **NO EJECUTADO**: sin registro de caída, recuperación y publicación | Pendiente de asignar |
| SAT-10 | Rendimiento | `k6 run perf/reserve.js` | p95 de consulta < 200 ms; reserva < 400 ms | **NO EJECUTADO integralmente**: se midió solo GET con k6 (2 VUs, 30 s; p95 18,73 ms) | Pendiente de asignar |

## Interpretación de resultados

- **14 PASS** es el conteo de *aserciones del script SAT-1..8*, no 14 casos completamente verificados contra todos sus criterios.
- Los resultados existentes de CI confirman que el script configurado se ejecutó con éxito, sin ampliar su cobertura declarativa.
- SAT-9 y SAT-10 deben seguir en estado pendiente hasta obtener evidencia real. No inventar un resultado.
- Para ejecutar FEFO después del **1 de diciembre de 2026**, revisar la semilla con fechas fijas (`V2__seed_demo.sql`). No modificar `V2` aplicada; utilizar una base de prueba nueva o migración posterior controlada.
