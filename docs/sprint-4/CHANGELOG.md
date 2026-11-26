# Smart Inventory - Changelog

## [v1.0.0] - Candidata a release

Estado: pendiente de aprobacion de CI/CD
y publicacion en GitHub Releases.

### Observabilidad
- Dashboard Grafana con cuatro paneles.
- Monitoreo de disponibilidad de servicios.
- Metricas de CPU y memoria JVM.
- Metricas de solicitudes HTTP.
- Scrape detallado de colas RabbitMQ.

### Alertas
- Cinco reglas validadas con Promtool.
- Pruebas simuladas de Outbox, DLQ y consumidores.
- Verificacion de condiciones normales.

### Rendimiento
- Prueba baseline reproducible con k6.
- 30 solicitudes y 0 % de errores.
- Latencia p50: 6.76 ms.
- Latencia p95: 18.73 ms.
- Throughput: 0.995356 req/s.

### Trazabilidad
- Correlacion mediante X-Request-Id.
- Logs estructurados JSON.
- Jaeger incorporado como servicio opcional.
- Recepcion de trazas HTTP y tareas Outbox.

### Documentacion
- Analisis de rendimiento.
- Analisis de trazabilidad.
- Comparacion de herramientas DevOps.
- Retrospectiva del Sprint 4.
- Evidencias reproducibles.

### Limitaciones
- La prueba k6 es de carga ligera.
- No se verifico el envio de notificaciones.
- No existe rollback automatico persistente.
- El release requiere los controles de CI/CD.
