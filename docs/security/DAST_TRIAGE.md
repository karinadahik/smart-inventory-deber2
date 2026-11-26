# Triaje de hallazgos DAST — OWASP ZAP

## Contexto y fuentes de evidencia

Se utilizó ZAP en modo pasivo sobre el contrato OpenAPI, tanto localmente como en el pipeline. Las capturas [E06](../sprint-3/capturas/E06_ZAP_antes.png) y [E08](../sprint-3/capturas/E08_ZAP_despues.png) muestran respectivamente **118 PASS / 2 WARN / 0 FAIL** y **120 PASS / 0 WARN / 0 FAIL**, sobre 14 URL. **WARN es una clasificación del resultado del escaneo, no equivale necesariamente a severidad de riesgo.** Los reportes originales se conservaron localmente. Los JSON revisados muestran riesgo bajo en ambas alertas. Ver DAST_COMPARACION.md y DAST_EVIDENCIA_SANITIZADA.json.

| Hallazgo previo | Resultado/alerta observada | Severidad de riesgo ZAP | Acción correctiva | Verificación posterior | Responsable |
|---|---|---|---|---|---|
| Ausencia de `X-Content-Type-Options` | WARN en escaneo previo | **Baja (verificada en JSON ZAP)** | `SecurityHeadersFilter` agrega `nosniff` | Cabecera observada (E07) y 0 WARN totales en reescaneo E08 | Rol: desarrollo/API; nombre no registrado |
| Ausencia de `Cross-Origin-Resource-Policy` | WARN en escaneo previo | **Baja (verificada en JSON ZAP)** | `SecurityHeadersFilter` agrega `same-origin` | Cabecera observada (E07) y 0 WARN totales en reescaneo E08 | Rol: desarrollo/API; nombre no registrado |

**Disposición:** mitigación implementada y reescaneo sin alertas WARN registradas en la evidencia disponible. Esto **no certifica ausencia de vulnerabilidades** ni constituye un pentest exhaustivo.

## Recuperación de reportes HTML/JSON reales

El workflow almacena los resultados en artefactos de GitHub Actions (`pr-staging-evidence` y `zap-baseline-reports`), **si todavía están disponibles**. Consultarlos con `gh run view ID` y, cuando corresponda, `gh run download ID -n NOMBRE --dir /tmp/zap-evidencia`. Revisar que el archivo descargado pertenezca a la ejecución y al commit citados antes de adjuntarlo al repositorio.

Si se recuperan, registrar aquí la severidad indicada por ZAP (campo de riesgo del hallazgo), evidencia de falso positivo/corrección, fecha, ejecución de origen y responsable humano si el equipo lo identifica. No atribuir una severidad numérica ni un nombre sin evidencia.
