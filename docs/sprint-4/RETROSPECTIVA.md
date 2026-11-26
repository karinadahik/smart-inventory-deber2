# Sprint 4 - Retrospectiva

## Objetivo del sprint

Incorporar observabilidad, pruebas de carga,
correcciones operativas y preparar el release final.

## Que funciono bien

- La API respondio HTTP 200 en la prueba baseline.
- k6 completo 30 solicitudes con 0 % de errores.
- El p95 fue de 18.73 ms bajo carga ligera.
- Prometheus valido cinco reglas de alerta.
- Las pruebas simuladas de alertas finalizaron SUCCESS.
- Se corrigio la recoleccion detallada de RabbitMQ.
- Se comprobo la correlacion con X-Request-Id.
- Jaeger recibio trazas reales de Smart Inventory.

## Problemas identificados

1. Faltaban metricas detalladas por cola de RabbitMQ.
2. OpenTelemetry intentaba exportar a localhost:4318
   sin un receptor disponible.
3. Las evidencias estaban distribuidas entre
   Codespaces y capturas locales.

## Acciones correctivas

- Incorporar el job rabbitmq-detailed en Prometheus.
- Incorporar Jaeger mediante Compose complementario.
- Configurar OTLP_ENDPOINT para exportar a Jaeger.
- Centralizar evidencias en docs/sprint-4.

## Limitaciones conocidas

- La prueba k6 realizada es una linea base,
  no demuestra capacidad maxima.
- Se probaron con datos simulados tres alertas
  y condiciones normales.
- No se ha demostrado envio de notificaciones.
- Las trazas observadas no prueban un recorrido
  distribuido completo entre multiples servicios.
- El release y la aprobacion de produccion
  requieren verificacion final.

## Acciones futuras

- Incorporar pruebas de carga escalonadas.
- Probar notificaciones y procedimientos de incidente.
- Agregar almacenamiento persistente para trazas.
- Mantener evidencias reproducibles por version.
