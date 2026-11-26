# Smart Inventory - Notas de version

## Versión publicada: v1.0.0 — 26-11-2026

Estado técnico: [GitHub Release publicado](https://github.com/karinadahik/smart-inventory-deber2/releases/tag/v1.0.0), commit `1cabba8`, CI de tag con `success`. Esta edición documental es posterior a la etiqueta; no está incluida en el snapshot original del tag.

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
