# Sprint 1 — Requisitos, arquitectura y contratos iniciales

**Tipo de documento:** reconstrucción de evidencias a partir de los archivos versionados del proyecto. No se dispone en este paquete de un acta histórica de Sprint 1 ni de capturas de su revisión original. Esta clasificación **no afirma** que existió una ceremonia documentada.

## Objetivo de Sprint 1 (según consigna)
Definir alcance vertical, historias US-01 a US-04, criterios de aceptación, contrato de API, evento y decisiones de arquitectura antes de implementar.

## Artefactos existentes y trazabilidad

| Entregable | Evidencia real en el repositorio |
|---|---|
| Historias, prioridad, criterio de aceptación, ruta, pruebas | [Backlog y trazabilidad](../BACKLOG_TRAZABILIDAD.md) |
| Contrato HTTP versionado | [`openapi/openapi.yaml`](../../openapi/openapi.yaml) |
| Contrato del evento | [`docs/events/stock-reserved.v1.json`](../events/stock-reserved.v1.json) |
| ADR de Outbox e idempotencia | [`ADR-0001`](../adr/0001-outbox-y-consumidores-idempotentes.md) |
| ADR de FEFO y concurrencia | [`ADR-0002`](../adr/0002-fefo-con-bloqueo-pesimista.md) |
| ADR de rate limiting | [`ADR-0003`](../adr/0003-estrategia-de-rate-limit.md) |
| Plan de aceptación | [`docs/sat.md`](../sat.md) |
| Requisitos no funcionales | [Backlog](../BACKLOG_TRAZABILIDAD.md) y [README](../../README.md) |

## Alcance y decisiones

- Recorrido vertical: consultar inventario → reservar con FEFO → persistir evento en Outbox → consumidores de dashboard y reposición.
- API HTTP v1 y esquema de evento JSON; acceso por API key y scopes (diseño de laboratorio).
- Separar reglas de dominio de adaptadores web y mensajería.
- Registrar decisiones y costes en ADR, sin atribuir a esa documentación una fecha de aprobación que no esté respaldada.

## Cierre documental retrospectivo

**Artefactos encontrados:** contratos, ADR y guion SAT. **Por comprobar con el equipo/docente:** si requieren la evidencia original de planificación, una captura del backlog de la herramienta ágil o el acta de revisión de Sprint 1. Los documentos enlazados son la evidencia técnica disponible, **no una captura histórica de la ceremonia de sprint**.
