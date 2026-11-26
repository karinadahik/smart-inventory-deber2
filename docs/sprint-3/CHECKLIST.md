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
- [ ] Confirmar último workflow de `main` posterior al Merge del PR #5
- [ ] Incorporar/verificar prueba reproducible y evidencia específica de rate limiting (HTTP 429)
- [ ] Verificar y enlazar evidencia específica de resiliencia (Outbox, reintentos, DLQ)
- [ ] Confirmar con el docente si el staging temporal cumple la exigencia de entorno permitido
- [ ] Revisar condiciones de release, versionado semántico y alcance del rollback (puede quedar para cierre del proyecto según rúbrica)

**Nota:** los checks con [x] se basan en las capturas y salidas compartidas, no son una certificación ni evaluación del profesor.
