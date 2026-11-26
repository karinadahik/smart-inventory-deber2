# Sprint 4 - Logs y trazabilidad

## Objetivo
Demostrar la correlacion de solicitudes y la
recepcion de trazas mediante OpenTelemetry.

## Componentes
- Spring Boot y Micrometer Tracing.
- Logs estructurados JSON (ECS).
- CorrelationIdFilter con X-Request-Id.
- MDC para correlacion de eventos.
- Exportacion OTLP HTTP.
- Jaeger para consulta de trazas.

## Problema identificado
La aplicacion intentaba exportar trazas a
localhost:4318 desde su contenedor Docker.
No existia un receptor disponible en esa
direccion.

## Solucion implementada
Se incorporo un servicio Jaeger mediante
docker-compose.tracing.yml.

La API utiliza el endpoint:
http://jaeger:4318/v1/traces

## Evidencia obtenida
- La API respondio HTTP 200.
- X-Request-Id se devolvio correctamente.
- Jaeger mostro 20 trazas de smart-inventory.
- Se observaron trazas HTTP y tareas Outbox.
- Una traza HTTP visible duro 3.1 ms.

## Alcance
Se verifico la recepcion y consulta de trazas
de Smart Inventory.

No se demostro una traza de extremo a extremo
entre multiples servicios.

## Evidencias
- correlation-headers.txt
- jaeger-verificacion.log, si se genero
- Capturas de Jaeger
