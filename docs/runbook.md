# Runbook: "Las reservas no se ven en el dashboard"

**Alertas relacionadas:** `OutboxBacklog`, `ConsumerQueueAging`, `DeadLetterQueueNotEmpty` (ver `ops/alerts.yml`).
**Responsable de guardia:** rol de guardia designado por el equipo. **Criterio de escalamiento propuesto:** `stale=true` por mas de 5 minutos. Esta condicion aun no esta configurada como alerta automatica.

## 1. Síntoma y alcance
- `GET /api/v1/dashboard/inventory` devuelve filas con `stale: true` o un `processingDelayMs` creciente.
- Alcance: ¿un SKU o todos? Si solo falta uno, sospechar de un mensaje envenenado; si faltan todos, del broker o del consumidor.

## 2. Diagnóstico (en este orden; **no purgar mensajes como primer paso**)
1. **Correlación:** tomar el `requestId` de la reserva (cabecera `X-Request-Id` de la respuesta) y buscarlo en los logs JSON: consultar los logs disponibles de la API y de `DashboardConsumer`, junto con el `correlation_id` del outbox. La ausencia de un log no demuestra por si sola donde se detuvo el procesamiento.
2. **Salud de dependencias:** `GET /actuator/health/readiness` (DB y broker).
3. **Outbox:** `select count(*) from outbox where published_at is null;` o la métrica `smartinventory_outbox_pending`. Si crece → el publicador no llega al broker.
4. **Broker:** consola `:15672`: profundidad y antigüedad de `dashboard.q` / `replenishment.q`, tasa de reintentos y contenido de `inventory.dlq`.
5. **Trazas:** si hay un backend de trazas configurado, consultar el `traceId`. En caso contrario, utilizar logs estructurados y el identificador de correlacion.

## 3. Mitigación segura
| Causa | Acción |
|---|---|
| Broker caído | Restaurar broker. El outbox conserva los eventos; el publicador reanuda solo. No reiniciar la API por esto. |
| Consumidor saturado | Subir `max-concurrency` o pausar productores temporalmente (bajar el rate limit). |
| Mensajes en DLQ | Inspeccionar el cuerpo y la causa en logs; corregir el consumidor o el dato **antes** de re-procesar. |
| Despliegue defectuoso | En el staging temporal de GitHub Actions, detener la validacion y limpiar el entorno. No existe rollback automatico a una version anterior. Un staging persistente requeriria desplegar nuevamente un tag previamente verificado. |

## 4. Recuperación
- Re-procesar la DLQ con *shovel* o re-publicación hacia el exchange `smartinventory.events`. Conservar el `eventId` original, verificar primero la causa del fallo y controlar los reintentos. La tabla `processed_event` proporciona deduplicacion, pero no garantiza que cualquier reprocesamiento sea seguro.
- Reconciliar: `reserved` por SKU en `batch` frente a `reservation_line`; el dashboard debe converger.
- Registrar aprendizaje: causa raíz, tiempo de detección/recuperación, mejora de alerta o prueba añadida.
