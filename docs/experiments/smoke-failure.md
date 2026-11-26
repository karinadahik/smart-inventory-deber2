# Experimento de fallo controlado - Sprint 3

## Objetivo
Demostrar que GitHub Actions bloquea la entrega cuando
una comprobacion de smoke test no cumple su criterio.

## Ejecucion
- Rama: experiment/smoke-failure
- Ejecucion: CI #11
- Aplicacion: Smart Inventory
- Health check normal: HTTP 200
- Ruta experimental: /actuator/health/forced-failure
- Resultado observado: HTTP 404
- Resultado esperado: HTTP 200
- Resultado del job: FAILURE (exit code 1)

## Conclusion
La validacion automatizada bloqueo correctamente
el pipeline ante el fallo controlado.

El experimento no representa un rollback real:
el staging utilizado es temporal.

## Recuperacion
Se elimina el paso experimental y se restaura
el flujo normal de SAT y DAST.
La recuperacion se verifico exitosamente en CI #12. El job PR staging, SAT and DAST volvio a aprobarse. No se realizo rollback de un despliegue persistente.
