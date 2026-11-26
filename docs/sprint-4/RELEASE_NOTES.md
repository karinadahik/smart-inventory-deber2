# Smart Inventory - Notas de version

## Corte técnico validado: v1.0.2 — 26-11-2026 (ver [último release](https://github.com/karinadahik/smart-inventory-deber2/releases/latest))

[Release `v1.0.2`](https://github.com/karinadahik/smart-inventory-deber2/releases/tag/v1.0.2) · Commit `f3884bf` · [CI de publicación aprobada](https://github.com/karinadahik/smart-inventory-deber2/actions/runs/45897569340) · [PR correctivo #10](https://github.com/karinadahik/smart-inventory-deber2/pull/10).

**Cambios propios de `v1.0.2`:** corrección de respuestas HTTP 404/405/415 con prueba de regresión; documentación SAT, triaje DAST, contratos JSON de eventos de sugerencia y correcciones de trazabilidad documental.

**Historial de `v1.0.1`:** documentación retrospectiva de Sprints 1/2, backlog y trazabilidad, índice integral, guion de demo, notas y capturas E08–E10. No se modificó código de aplicación.

### Funcionalidad técnica heredada de v1.0.0

La implementación de observabilidad, alertas y pruebas indicada a continuación corresponde al corte `v1.0.0` y permanece en `v1.0.2`.

### Observabilidad
- Dashboard de Grafana con disponibilidad,
  CPU, memoria JVM y solicitudes HTTP.
- Metricas detalladas de colas RabbitMQ.
- Cinco reglas de alerta validadas.
- Pruebas unitarias de escenarios de alerta.
- Correlacion de solicitudes HTTP.
- Jaeger y exportacion de trazas mediante OTLP.

### Rendimiento
- Script reproducible de k6.
- 2 usuarios virtuales durante 30 segundos.
- 30 solicitudes, sin errores HTTP.
- p50: 6.76 ms.
- p95: 18.73 ms.
- Throughput: 0.995356 solicitudes/s.

### Entrega
- Configuracion complementaria de tracing.
- Evidencias y documentacion del Sprint 4.
- Release publicado por GitHub Actions; CI de main y del tag aprobados. No está acreditada una aprobación manual de producción.

### Limitaciones
El entorno evaluado es un laboratorio.
Los resultados de carga no representan un
compromiso de rendimiento en produccion.
