# Sprint 4 — Evidencias de observabilidad

## Estado: observabilidad básica verificada

Evidencias recogidas el 26 de noviembre de 2026 para el proyecto **Smart Inventory**.
El dashboard de Grafana se exportó con el nombre **Smart Inventory - Sprint 4 Observabilidad**.

### Evidencias

- [E00 — Endpoints de salud](capturas/E00_endpoints_observabilidad_ok.png): salida previa de Grafana, Prometheus y la API de métricas.
- [E01 — Prometheus recolectando métricas](capturas/E01_prometheus_api_rabbitmq_up.png): consulta `up` mostrando que las instancias `app:8080` y `rabbitmq:15692` pasan a `1`.
- [E02a — Disponibilidad y CPU](capturas/E02_dashboard_disponibilidad_cpu.png): paneles del dashboard con datos.
- [E02b — HTTP y memoria JVM](capturas/E02_dashboard_http_memoria.png): paneles del dashboard con datos.
- [Dashboard Grafana (JSON)](grafana/smart-inventory-observabilidad.json): configuración exportada de los cuatro paneles.

### Consultas PromQL verificadas en el dashboard

| Panel | Consulta |
|---|---|
| Disponibilidad de servicios | `up{job=~"smart-inventory|rabbitmq"}` |
| CPU Java (%) | `process_cpu_usage{job="smart-inventory"} * 100` |
| Solicitudes HTTP por segundo | `sum(rate(http_server_requests_seconds_count{job="smart-inventory"}[5m]))` |
| Memoria JVM (MB) | `sum(jvm_memory_used_bytes{job="smart-inventory",area="heap"}) / 1024 / 1024` |

### Reproducibilidad y límites

El JSON exportado **contiene las consultas y los paneles, no los datos históricos de Prometheus**.
Para reconstruir los gráficos hay que tener Prometheus accesible desde Grafana y reconfigurar/asignar
la fuente de datos si su UID difiere del exportado (`bg5h2yhmercw0a`).

El hecho de que `up` sea `1` demuestra recolección exitosa en el momento observado;
no significa que se hayan probado alertas, SLO, trazas distribuidas ni tolerancia ante fallos.

**Pendiente de Sprint 4:** alertas, prueba de carga k6, análisis de resultados, versionado/release,
retrospectiva y su evidencia. Aún no se ha realizado ningún commit ni Pull Request
para incorporar este paquete al repositorio remoto.
