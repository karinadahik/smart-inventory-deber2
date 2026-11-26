# Comparacion DAST - OWASP ZAP

## Objetivo

Comparar los resultados de los escaneos DAST
antes y despues de las correcciones de seguridad.

## Resultados

| Severidad | Antes | Despues |
|---|---:|---:|
| Alta | 0 | 0 |
| Media | 0 | 0 |
| Baja | 2 | 0 |
| Informativa | 3 | 3 |
| Total | 5 | 3 |

## Hallazgos bajos que dejaron de aparecer

1. Cross-Origin-Resource-Policy Header Missing
   or Invalid.
2. X-Content-Type-Options Header Missing.

Estos dos hallazgos aparecen en el reporte
inicial y no aparecen en el reporte posterior.

## Hallazgos informativos restantes

1. A Client Error response code was returned
   by the server.
2. Non-Storable Content.
3. Storable and Cacheable Content.

## Evidencia de origen

- smart-inventory-dast.json
- smart-inventory-dast-after.json
- smart-inventory-dast.html
- smart-inventory-dast-after.html

Los reportes originales se conservan localmente
en zap-reports/ y deben revisarse antes de
publicarlos, para evitar exponer informacion sensible.

## Alcance y limitaciones

El analisis corresponde a los escaneos realizados
con OWASP ZAP.

La desaparicion de los hallazgos bajos confirma
una mejora observable en estos reportes.

No demuestra ausencia total de vulnerabilidades.
No sustituye pruebas de seguridad adicionales.
