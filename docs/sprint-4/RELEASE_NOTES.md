# Smart Inventory - Notas de version

## Version candidata: v1.0.0

Estado: PENDIENTE DE PUBLICACION.

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
- Publicacion de release pendiente de CI y aprobacion.

### Limitaciones
El entorno evaluado es un laboratorio.
Los resultados de carga no representan un
compromiso de rendimiento en produccion.
