# Contratos de eventos y topología de mensajería

Los contratos se basan en `EventEnvelope`, los payloads enviados por `ReservationService`, `ReplenishmentConsumer` y `ReplenishmentService`, y las constantes de `RabbitConfig`. Todos usan `schemaVersion: 1` y `producer: smart-inventory`.

| Evento y contrato v1 | Emisor del código | Routing key (`smartinventory.events`) | Destino configurado |
|---|---|---|---|
| [`inventory.stock-reserved`](stock-reserved.v1.json) | `ReservationService` vía Outbox | `inventory.stock-reserved` | `dashboard.q` y `replenishment.q` |
| [`replenishment.suggestion-opened`](suggestion-opened.v1.json) | `ReplenishmentConsumer` vía Outbox | `replenishment.suggestion-opened` | **Sin binding de cola definido en `RabbitConfig`** |
| [`replenishment.suggestion-approved`](suggestion-approved.v1.json) | `ReplenishmentService` vía Outbox | `replenishment.suggestion-approved` | **Sin binding de cola definido en `RabbitConfig`** |

**Importante:** los eventos de sugerencias están escritos y pueden publicarse mediante Outbox, pero la topología actual **no declara una cola consumidora** para esas routing keys. En un exchange `topic`, un evento publicado sin binding compatible no se entregará a las dos colas que escuchan `inventory.stock-reserved`. No afirmar que haya consumo confirmado ni inventar un consumidor externo. Si el negocio necesita procesarlos, diseñar una cola/binding y un consumidor con su prueba correspondiente.

La entrega es al menos una vez para los eventos que tienen consumidores; se exige deduplicación por `eventId`. No interpretar la existencia del esquema como una prueba de entrega.
