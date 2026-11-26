# Índice de entrega integral y rúbrica — Smart Inventory

**Versión publicada más reciente:** consultar [GitHub Releases/latest](https://github.com/karinadahik/smart-inventory-deber2/releases/latest). **Corte técnico auditado:** [v1.0.2](https://github.com/karinadahik/smart-inventory-deber2/releases/tag/v1.0.2), commit `f3884bf`, originado en el [PR #10](https://github.com/karinadahik/smart-inventory-deber2/pull/10) (26-11-2026). El PR #9 corresponde al historial de documentación de Sprints 1/2. No se modifican tags existentes; el ZIP y su release deben identificar el mismo commit.

## Evidencia disponible para los cuatro sprints

| Sprint | Requisito de la consigna | Evidencias navegables | Alcance |
|---|---|---|---|
| 1 | Requisitos, contratos, backlog, ADR y plan de pruebas | [`sprint-1/README.md`](sprint-1/README.md), [`BACKLOG_TRAZABILIDAD.md`](BACKLOG_TRAZABILIDAD.md), [`../openapi/openapi.yaml`](../openapi/openapi.yaml), `adr/`, `sat.md` | Reconstrucción documental con artefactos reales; no hay acta original de revisión |
| 2 | API, reserva, eventos, persistencia, pruebas | [`sprint-2/README.md`](sprint-2/README.md), `../src/main/`, `../src/test/`, [`sprint-3/capturas/E05_SAT_14PASS.png`](sprint-3/capturas/E05_SAT_14PASS.png) | SAT documentado posteriormente, broker desactivado en IT de Outbox |
| 3 | DevSecOps, staging, SAT/DAST, resiliencia | [`sprint-3/README.md`](sprint-3/README.md), [`sprint-3/CIERRE.md`](sprint-3/CIERRE.md), [CI de main](https://github.com/karinadahik/smart-inventory-deber2/actions/runs/45870488534) | Staging temporal, bloqueo seguro demostrado; no rollback persistente |
| 4 | Observabilidad, carga, logs/trazas, runbook, release | [`sprint-4/README.md`](sprint-4/README.md), `runbook.md`, [`sprint-4/RETROSPECTIVA.md`](sprint-4/RETROSPECTIVA.md), [Último release](https://github.com/karinadahik/smart-inventory-deber2/releases/latest) | k6 de consulta ligera; trazas recibidas, pero no cadena completa entre servicios |

## Publicación y sincronización

- [Repositorio](https://github.com/karinadahik/smart-inventory-deber2) y [PR #10 fusionado](https://github.com/karinadahik/smart-inventory-deber2/pull/10) — commit de merge `f3884bf`.
- [Revisión del PR #10](https://github.com/karinadahik/smart-inventory-deber2/pull/10): siete checks aprobados, cero fallidos (estado observado al fusionarlo).
- [CI de `main`](https://github.com/karinadahik/smart-inventory-deber2/actions/runs/45896689331): `completed/success` sobre el merge del PR #10.
- [CI del tag `v1.0.2`](https://github.com/karinadahik/smart-inventory-deber2/actions/runs/45897569340): `completed/success`.
- [Release `v1.0.2`](https://github.com/karinadahik/smart-inventory-deber2/releases/tag/v1.0.2): publicado y verificado. Versiones posteriores: [última publicación](https://github.com/karinadahik/smart-inventory-deber2/releases/latest).
- **Historial:** [PR #8](https://github.com/karinadahik/smart-inventory-deber2/pull/8) / [v1.0.0](https://github.com/karinadahik/smart-inventory-deber2/releases/tag/v1.0.0) y [PR #9](https://github.com/karinadahik/smart-inventory-deber2/pull/9) / [v1.0.1](https://github.com/karinadahik/smart-inventory-deber2/releases/tag/v1.0.1) se conservan como versiones anteriores.
- **Sin evidencia adjunta:** ZIP integral como *asset* del release, aprobación humana obligatoria del entorno `production-approval`, rollback a versión persistente anterior o grabación real de la demo.

## Rubrica — límites que deben declararse

| Criterio | Referencia verificable / reserva |
|---|---|
| Requisitos y trazabilidad | `BACKLOG_TRAZABILIDAD.md`, US-01 a US-04, OpenAPI y ADR |
| APIs y contratos | `openapi/openapi.yaml`, controladores y `OpenApiContractTest` |
| Mensajería | `OutboxWriter`, `OutboxPublisher`, `ProcessedEvents`, `RabbitConfig`; SAT-9 PASS en laboratorio; evidencia en sprint-4/resultados/SAT9_ejecucion.txt |
| Pruebas y SAT | Testcontainers y `mvn verify` con CI verde; SAT-1..8 y SAT-9 en laboratorio, DAST pasivo; véase [triaje de hallazgos](security/DAST_TRIAGE.md); no equivale a todas las pruebas posibles |
| CI/CD y DevSecOps | `.github/workflows/ci.yml`, staging temporal, fallo de smoke test y recuperación; sin gate formal de lint ni cobertura cuantificada; **sin rollback real de versión desplegada** |
| Observabilidad | `docs/sprint-4/`, prometheus/alertas, k6, Jaeger, `runbook.md` |
| Calidad de implementación | arquitectura por paquetes, manejo de excepciones, roles/scopes y Docker Compose |
| Gestión/versiones/demo | PR #10 y corte técnico v1.0.2 (última publicación enlazada arriba); **guion de demo** disponible, **grabación/enlace aún no provistos** |

**No se atribuye puntuación numérica ni se afirma cumplimiento del 100%**: corresponde al docente evaluar la cobertura de los pendientes.
