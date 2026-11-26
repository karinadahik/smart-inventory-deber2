# Sprint 4 — Observabilidad, carga, trazabilidad y publicación

**Corte técnico validado:** [release `v1.0.2`](https://github.com/karinadahik/smart-inventory-deber2/releases/tag/v1.0.2) y [PR #10](https://github.com/karinadahik/smart-inventory-deber2/pull/10). [CI de `main`](https://github.com/karinadahik/smart-inventory-deber2/actions/runs/45896689331) y [CI del tag](https://github.com/karinadahik/smart-inventory-deber2/actions/runs/45897569340) terminaron en `success`. Para la versión publicada más reciente consultar [Releases/latest](https://github.com/karinadahik/smart-inventory-deber2/releases/latest).

Las capturas **E08–E10** registran el release histórico `v1.0.0` (PR #8). `v1.0.1` consolidó documentación de cuatro sprints (PR #9) y `v1.0.2` añadió la corrección HTTP 404/405/415, su prueba de regresión, contratos de eventos de sugerencia y ajustes SAT/DAST (PR #10). Estas capturas no se presentan como evidencia visual de `v1.0.2`; esta se consulta en GitHub Actions.

## Capturas y resultados reales

| ID | Evidencia | Archivo |
|---|---|---|
| E00 | Endpoints operativos en el laboratorio | [`capturas/E00_endpoints_observabilidad_ok.png`](capturas/E00_endpoints_observabilidad_ok.png) |
| E01 | Prometheus: aplicación/RabbitMQ `up` | [`capturas/E01_prometheus_api_rabbitmq_up.png`](capturas/E01_prometheus_api_rabbitmq_up.png) |
| E02a | Grafana: disponibilidad y CPU | [`capturas/E02_dashboard_disponibilidad_cpu.png`](capturas/E02_dashboard_disponibilidad_cpu.png) |
| E02b | Grafana: HTTP y JVM | [`capturas/E02_dashboard_http_memoria.png`](capturas/E02_dashboard_http_memoria.png) |
| E07 | Jaeger: listado de trazas de `smart-inventory` | [`capturas/E07_jaeger_listado_trazas.png`](capturas/E07_jaeger_listado_trazas.png) |
| E08 | CI de `main` exitosa | [`capturas/E08_github_actions_main_success.png`](capturas/E08_github_actions_main_success.png) |
| E09 | CI y job de `release` en verde | [`capturas/E09_github_actions_release_success.png`](capturas/E09_github_actions_release_success.png) |
| E10 | Release GitHub `v1.0.0` publicado | [`capturas/E10_github_release_v1.0.0.png`](capturas/E10_github_release_v1.0.0.png) |

## Artefactos verificables

- [Dashboard Grafana (JSON)](grafana/smart-inventory-observabilidad.json): cuatro paneles, consultas PromQL; requiere fuente de datos funcional (UID según entorno).
- [`ops/prometheus.yml`](../../ops/prometheus.yml) + [`ops/alerts.yml`](../../ops/alerts.yml) + [`ops/alerts.test.yml`](../../ops/alerts.test.yml): recolección detallada RabbitMQ, cinco reglas válidas y tres alertas ensayadas con datos simulados.
- [Análisis k6](resultados/ANALISIS_K6.md), [JSON de resultados](resultados/k6-baseline.json) y [`scripts/k6/inventory-read.js`](../../scripts/k6/inventory-read.js): 30 GET, p95 18,73 ms, 0 % errores.
- [Análisis de trazabilidad](resultados/ANALISIS_TRAZABILIDAD.md): OpenTelemetry, OTLP, Jaeger, logs JSON y correlación; [`docker-compose.tracing.yml`](../../docker-compose.tracing.yml).
- [Triaje DAST](../security/DAST_TRIAGE.md), [calidad y límites](../CALIDAD_Y_LIMITACIONES.md), [contratos de eventos](../events/README.md).
- [Changelog](CHANGELOG.md), [notas de release](RELEASE_NOTES.md), [retrospectiva](RETROSPECTIVA.md), [comparación de herramientas](COMPARACION_HERRAMIENTAS.md) y [runbook](../runbook.md).

## Alcance y límites

1. La captura Jaeger confirma recepción de spans de un servicio, **no una traza distribuida end-to-end entre servicios**.
2. Prometheus `up=1` prueba disponibilidad de scraping al momento observado, no un SLO ni recuperación automática ante fallos.
3. Pruebas sintéticas de reglas de alerta **no significan notificación real** a un sistema de guardia.
4. La prueba k6 es de **carga ligera** sobre GET inventario, no un ensayo de capacidad máxima ni de reserva.
5. El release y los checks fueron publicados; no se afirma que existiera aprobación manual obligatoria del entorno `production-approval`.
