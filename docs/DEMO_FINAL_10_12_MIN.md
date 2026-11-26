# Guion de demostración final — 10 a 12 minutos

**Estado de la evidencia audiovisual:** no se adjunta grabación ni enlace verificado. Este archivo es un guion de demostración, **no acredita su ejecución**. Si el docente exige un enlace a video, debe añadirse uno real antes de entregar.

| Tiempo sugerido | Acción en la demostración | Evidencia |
|---|---|---|
| 0:00–1:00 | Problema, alcance vertical y US-01 a US-04 | `README.md`, `docs/BACKLOG_TRAZABILIDAD.md` |
| 1:00–2:30 | API versionada, seguridad por scopes, errores `problem+json` | `openapi/openapi.yaml`, Swagger UI |
| 2:30–4:00 | Reserva FEFO e idempotencia; Outbox y consumidores | `docs/adr/`, `docs/events/`, `scripts/sat.sh` |
| 4:00–5:30 | Pruebas de dominio, integración, contrato y SAT | `src/test/`, capturas `docs/sprint-3/` |
| 5:30–7:00 | Seguridad DevSecOps, CI/CD y prueba controlada de fallo | `.github/workflows/ci.yml`, capturas del Sprint 3 |
| 7:00–8:30 | Grafana, Prometheus, RabbitMQ y alertas | `docs/sprint-4/capturas/`, `ops/alerts.yml` |
| 8:30–9:30 | k6 baseline, latencias y limitación del ensayo | `docs/sprint-4/resultados/ANALISIS_K6.md` |
| 9:30–10:30 | Jaeger, request ID y runbook ante mensaje retrasado | `docs/sprint-4/resultados/ANALISIS_TRAZABILIDAD.md`, `docs/runbook.md` |
| 10:30–12:00 | PR #9 fusionado, CI verde y release v1.0.1; límites pendientes | [PR #9](https://github.com/karinadahik/smart-inventory-deber2/pull/9), [release v1.0.1](https://github.com/karinadahik/smart-inventory-deber2/releases/tag/v1.0.1), `docs/ENTREGA_Y_RUBRICA.md` (capturas E08–E10 muestran v1.0.0) |

## Preguntas que conviene preparar

- ¿Por qué Outbox evita perder un evento entre commit y publicación? ¿Por qué aún puede haber duplicados?
- ¿Por qué FEFO usa bloqueo pesimista? ¿Cómo se verifica la no sobreasignación?
- ¿Por qué GitHub Actions y no Jenkins/Argo CD en este laboratorio?
- ¿Qué demuestra la prueba k6 y qué **no** demuestra?
- ¿Qué diferencia hay entre bloqueo de un smoke test y rollback real?

**Recomendación:** si se graba la demo, vincular aquí el video real y fecha de grabación; no reemplazar con un enlace ficticio.
