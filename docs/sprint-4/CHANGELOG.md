# Smart Inventory - Changelog

**Última publicación:** [GitHub Releases/latest](https://github.com/karinadahik/smart-inventory-deber2/releases/latest). Las entradas numeradas describen cortes históricos y no se reescriben al publicar versiones posteriores.

## [v1.0.2] - Publicada el 26-11-2026

- Corregidos los códigos HTTP 404/405/415 que antes devolvían 500; agregada prueba de regresión `HttpErrorMappingTest`.
- Completada la documentación SAT y triaje DAST sin atribuir resultados no ejecutados.
- Incorporados esquemas versionados `suggestion-opened` y `suggestion-approved`; sin declarar consumidores no implementados.
- PR [#10](https://github.com/karinadahik/smart-inventory-deber2/pull/10), commit `f3884bf`, [release](https://github.com/karinadahik/smart-inventory-deber2/releases/tag/v1.0.2) y [CI del tag](https://github.com/karinadahik/smart-inventory-deber2/actions/runs/45897569340) aprobados.
- **Límites pendientes:** SAT-9, SAT-10 integral, rollback persistente, grabación de demo, cobertura cuantificada y notificaciones reales de alertas.

## [v1.0.1] - Publicada el 26-11-2026

- Cierre documental de Sprints 1 y 2 a partir de código y pruebas existentes (sin atribuir ceremonias históricas no acreditadas).
- Backlog y matriz de trazabilidad US-01 a US-04; índice de rúbrica y guion de demostración de 10–12 minutos.
- Actualización de README y evidencias gráficas de CI/CD y release; PR [#9](https://github.com/karinadahik/smart-inventory-deber2/pull/9), commit `3833600`.
- **Sin modificaciones de lógica funcional** respecto al corte `v1.0.0`.
- [Release `v1.0.1`](https://github.com/karinadahik/smart-inventory-deber2/releases/tag/v1.0.1), [CI de publicación aprobada](https://github.com/karinadahik/smart-inventory-deber2/actions/runs/45871563250).

## [v1.0.0] - Publicada el 26-11-2026

Estado técnico: publicada en GitHub Releases (tag `v1.0.0`, commit `1cabba8`). CI de PR #8, main y release con conclusión `success`. Esta versión representa la entrega técnica original del Sprint 4; su ampliación documental se publicó en `v1.0.1`.

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
- El release pasó los controles configurados; eso no demuestra aprobación manual de un revisor.
- No hay evidencia de rollback persistente, SAT-9 ejecutado ni carga de reserva completa.
