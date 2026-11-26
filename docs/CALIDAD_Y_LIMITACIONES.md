# Cobertura de calidad y límites explícitos del laboratorio

Este documento identifica criterios de la consigna que **no quedan demostrados plenamente** solo con un pipeline en verde.

| Aspecto | Evidencia existente | Limitación o acción pendiente |
|---|---|---|
| Lint / formato | `.github/workflows/ci.yml` ejecuta build, pruebas y escaneos | No se identificó gate explícito de lint o formateador (Checkstyle/Spotless). Añadir y verificar una regla si la rúbrica lo exige |
| Cobertura | `mvn -B verify` y reportes Surefire/Failsafe en artefactos | No se observó umbral o reporte porcentual JaCoCo en el `pom.xml` inspeccionado; no inventar porcentaje |
| Protección de `main` | PR #8 y #9 y sus checks | Una rama fusionada y CI verde no prueban que `main` tenga reglas obligatorias de aprobación/branch protection configuradas |
| Alertas del pipeline | GitHub Actions muestra estados de jobs | No se identificó una notificación/alerta automatizada de fallos de CI a un canal de guardia |
| Circuit breaker | No se identificó en el código inspeccionado | No se ha demostrado una dependencia externa síncrona que obligue a introducirlo; justificar su omisión y reevaluar si cambia el diseño |
| Reintentos con jitter | `spring.rabbitmq.listener.simple.retry` con multiplicador 2, máximo 3 intentos; Outbox periódico | Hay reintentos y escalamiento de espera en listener, pero no se evidenció jitter. No declarar backoff con jitter implementado |
| Recuperación real del broker/DLQ | Outbox y DLQ configurados | SAT-9 (caída/recuperación real) no ejecutado ni adjuntado |
| Rollback persistente | Fallo de smoke test controlado y recuperación CI | No se probó revertir un despliegue persistente a un artefacto previo |
| Demo final | [`DEMO_FINAL_10_12_MIN.md`](DEMO_FINAL_10_12_MIN.md) | Guion disponible; no hay video ni confirmación de presentación en vivo |

**Criterio de rigor:** en la defensa distinguir funcionalidad implementada, prueba automatizada, resultado observado y capacidad que aún carece de validación. Si el profesor pide evidencia extra, añadirla después de ejecutar realmente las pruebas.
