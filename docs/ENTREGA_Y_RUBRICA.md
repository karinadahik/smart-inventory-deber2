# Índice de entrega integral y rúbrica — Smart Inventory

**Corte técnico:** commit `1cabba8ee950177817314687d2b414e44dc1e8f4`, etiquetado y publicado como `v1.0.0` el **26-11-2026**. **Importante:** los documentos de esta carpeta se completaron *después* de publicar ese tag: el ZIP corregido es un suplemento documental al código de `v1.0.0`; no se debe afirmar que ya está en el tag GitHub hasta hacer un commit/PR de documentación.

## Evidencia disponible para los cuatro sprints

| Sprint | Requisito de la consigna | Evidencias navegables | Alcance |
|---|---|---|---|
| 1 | Requisitos, contratos, backlog, ADR y plan de pruebas | [`sprint-1/README.md`](sprint-1/README.md), [`BACKLOG_TRAZABILIDAD.md`](BACKLOG_TRAZABILIDAD.md), [`../openapi/openapi.yaml`](../openapi/openapi.yaml), `adr/`, `sat.md` | Reconstrucción documental con artefactos reales; no hay acta original de revisión |
| 2 | API, reserva, eventos, persistencia, pruebas | [`sprint-2/README.md`](sprint-2/README.md), `../src/main/`, `../src/test/`, [`sprint-3/capturas/E05_SAT_14PASS.png`](sprint-3/capturas/E05_SAT_14PASS.png) | SAT documentado posteriormente, broker desactivado en IT de Outbox |
| 3 | DevSecOps, staging, SAT/DAST, resiliencia | [`sprint-3/README.md`](sprint-3/README.md), [`sprint-3/CIERRE.md`](sprint-3/CIERRE.md), [CI en main](https://github.com/karinadahik/smart-inventory-deber2/actions/runs/45863252563) | Staging temporal, bloqueo seguro demostrado; no rollback persistente |
| 4 | Observabilidad, carga, logs/trazas, runbook, release | [`sprint-4/README.md`](sprint-4/README.md), `runbook.md`, [`sprint-4/RETROSPECTIVA.md`](sprint-4/RETROSPECTIVA.md), [Release](https://github.com/karinadahik/smart-inventory-deber2/releases/tag/v1.0.0) | k6 de consulta ligera; trazas recibidas, pero no cadena completa entre servicios |

## Publicación y sincronización

- [Repositorio](https://github.com/karinadahik/smart-inventory-deber2)
- [PR #8 fusionado](https://github.com/karinadahik/smart-inventory-deber2/pull/8) — commit de merge `1cabba8`.
- [CI de PR](https://github.com/karinadahik/smart-inventory-deber2/actions/runs/45861766455) — éxito según comprobación por GitHub CLI.
- [CI de main](https://github.com/karinadahik/smart-inventory-deber2/actions/runs/45863252563) — éxito según comprobación por GitHub CLI.
- [Workflow disparado por el tag v1.0.0](https://github.com/karinadahik/smart-inventory-deber2/actions/runs/45864706766) — `completed/success`.
- [Release público de v1.0.0](https://github.com/karinadahik/smart-inventory-deber2/releases/tag/v1.0.0) — publicado, no borrador ni prerelease según salida `gh release view`.
- **No probado:** que el ZIP de entrega completo haya sido adjuntado como *asset* al release; publicar el tag y publicar el ZIP son operaciones distintas.
- **No probado:** aprobación manual obligatoria en `production-approval`. La conclusión verde del job no demuestra por sí sola que hubo revisor humano.

## Rubrica — límites que deben declararse

| Criterio | Referencia verificable / reserva |
|---|---|
| Requisitos y trazabilidad | `BACKLOG_TRAZABILIDAD.md`, US-01 a US-04, OpenAPI y ADR |
| APIs y contratos | `openapi/openapi.yaml`, controladores y `OpenApiContractTest` |
| Mensajería | `OutboxWriter`, `OutboxPublisher`, `ProcessedEvents`, `RabbitConfig`; SAT-9 no ejecutado en las evidencias |
| Pruebas y SAT | Testcontainers y `mvn verify` con CI verde; SAT-1..8, DAST pasivo; no equivale a todas las pruebas posibles |
| CI/CD y DevSecOps | `.github/workflows/ci.yml`, staging temporal, fallo de smoke test y recuperación; **sin rollback real de versión desplegada** |
| Observabilidad | `docs/sprint-4/`, prometheus/alertas, k6, Jaeger, `runbook.md` |
| Calidad de implementación | arquitectura por paquetes, manejo de excepciones, roles/scopes y Docker Compose |
| Gestión/versiones/demo | PR #8, tag/release v1.0.0; **guion de demo** disponible, **grabación/enlace aún no provistos** |

**No se atribuye puntuación numérica ni se afirma cumplimiento del 100%**: corresponde al docente evaluar la cobertura de los pendientes.
