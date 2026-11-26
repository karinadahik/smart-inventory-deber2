# Sprint 4 - Comparacion de herramientas DevOps

## 1. GitHub Actions vs Jenkins

| Criterio | GitHub Actions | Jenkins |
|---|---|---|
| Definicion del pipeline | Workflows YAML | Jenkinsfile |
| Integracion con GitHub | Nativa | Mediante integraciones |
| Ejecucion | Runners gestionados o propios | Agentes gestionados por el equipo |
| Extensibilidad | Actions reutilizables | Plugins y pipelines |
| Mantenimiento | Menor carga con runners alojados | Mayor responsabilidad de infraestructura |

### Decision para Smart Inventory

Se utiliza GitHub Actions porque el repositorio
se encuentra en GitHub y el pipeline ya incluye:

- Validacion de secretos.
- Construccion y pruebas automatizadas.
- Analisis SAST y SCA.
- Empaquetado y escaneo de imagenes.
- Validacion de staging temporal.
- Proceso de release versionado.

Jenkins seria una alternativa para organizaciones
que necesiten gestionar infraestructura y pipelines
personalizados. No se implemento Jenkins en el laboratorio.

## 2. Argo CD vs despliegue controlado actual

| Criterio | Argo CD | Solucion actual |
|---|---|---|
| Modelo | GitOps declarativo | Pipeline GitHub Actions |
| Entorno objetivo | Kubernetes | Docker Compose temporal |
| Fuente de verdad | Configuracion declarada en Git | Codigo y workflows versionados |
| Sincronizacion | Reconciliacion con el cluster | Ejecucion de trabajos CI/CD |
| Rollback | Requiere estrategia y configuracion | Sin rollback automatico persistente |

### Decision para Smart Inventory

Se mantiene el enfoque actual basado en
GitHub Actions y Docker Compose para el laboratorio.

Argo CD seria una evolucion adecuada si Smart
Inventory se desplegara en Kubernetes y necesitara
reconciliacion continua del estado deseado.

No se implemento Argo CD ni un cluster Kubernetes.
La validacion actual usa un staging temporal.

## 3. Justificacion arquitectonica

La decision favorece la simplicidad operativa,
la reproducibilidad y el menor esfuerzo de
mantenimiento para el alcance academico.

No se debe confundir un pipeline exitoso con
un despliegue persistente o un rollback probado.

## 4. Fuentes oficiales

- GitHub Actions:
  https://docs.github.com/en/actions/get-started/understand-github-actions
- Jenkins Pipeline:
  https://www.jenkins.io/doc/book/pipeline/
- Argo CD:
  https://argo-cd.readthedocs.io/en/stable/
- Argo CD y automatizacion CI:
  https://argo-cd.readthedocs.io/en/stable/user-guide/ci_automation/
