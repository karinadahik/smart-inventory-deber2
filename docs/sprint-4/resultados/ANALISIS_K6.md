# Sprint 4 — Analisis de rendimiento con k6

## Objetivo
Establecer una linea base de latencia, throughput
y errores del endpoint GET /api/v1/inventory.

## Entorno
- Aplicacion: Smart Inventory, Spring Boot.
- Entorno: GitHub Codespaces con Docker Compose.
- Herramienta: k6 ejecutado en Docker.
- Usuarios virtuales: 2.
- Duracion: 30 segundos.
- Pausa por iteracion: 2 segundos.
- Operacion: lectura autenticada de inventario.

## Resultados reales
| Indicador | Resultado |
|---|---|
| Solicitudes totales | 30 |
| Solicitudes HTTP 200 | 30 |
| Checks exitosos | 100 % |
| Errores HTTP | 0 % |
| Latencia promedio | 8.16 ms |
| Latencia p50 | 6.76 ms |
| Latencia p95 | 18.73 ms |
| Latencia maxima | 23.64 ms |
| Throughput | 0.995356 req/s |

## Umbrales
- p95 menor que 1000 ms: APROBADO.
- Errores menores que 1 %: APROBADO.
- Checks exitosos mayores que 99 %: APROBADO.

## Interpretacion
El sistema respondio correctamente a la carga ligera
evaluada. La latencia p95 se mantuvo por debajo del
umbral propuesto y no se registraron errores.

Este resultado es una linea base, no una prueba de
capacidad maxima ni una garantia de rendimiento
en produccion.

## Evidencias
- k6-baseline.log
- k6-baseline.json
- scripts/k6/inventory-read.js
