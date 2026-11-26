# Checklist de cierre del Sprint 3

- [x] CI de PR con build-test, escaneo de secretos, SAST, SCA y package
- [x] Imagen Docker con tag basado en SHA y análisis de vulnerabilidades
- [x] Staging temporal con Docker Compose y smoke test
- [x] SAT automatizado en PR y en `main`
- [x] DAST pasivo automatizado con OWASP ZAP
- [x] Evidencias de DAST local antes/después de cabeceras HTTP
- [x] Fallo experimental HTTP 404 y bloqueo de la validación
- [x] Recuperación del pipeline en CI #12
- [x] Documentación del experimento y runbook corregido
- [x] CI posterior de `main` verificada; ejecución 45863252563 completada `success` tras PR #8
- [x] Captura E14 y test comparativo de token bucket / ventana fija presentes (`RateLimiterComparisonTest`)
- [x] Evidencia E15-E16 de Outbox y colas sanas. Posteriormente se ejecutó SAT-9 con PASS ([evidencia](../sprint-4/resultados/SAT9_ejecucion.txt)). La prueba específica de DLQ sigue pendiente.
- [ ] Confirmar con el docente si el staging temporal cumple la exigencia de entorno permitido
- [x] Tag y release `v1.0.0` publicados y CI de tag aprobada; **rollback real de despliegue persistente no demostrado**

**Nota:** los checks con [x] se basan en las capturas y salidas compartidas, no son una certificación ni evaluación del profesor.
