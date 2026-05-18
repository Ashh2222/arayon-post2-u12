# Validación Arquitectónica y ADRs — Sistema de Pedidos

---

# Introducción

Este proyecto corresponde al PostContenido 2 de la Unidad 12.

El objetivo principal fue extender el sistema de pedidos desarrollado previamente incorporando:

- Validación arquitectónica automática con ArchUnit
- Pipeline CI/CD con GitHub Actions
- Documentación arquitectónica mediante ADRs
- Verificación automática de restricciones arquitectónicas

El proyecto implementa reglas de arquitectura ejecutables que garantizan el desacoplamiento correcto entre capas y automatiza su validación en cada push mediante GitHub Actions.

---

# Tecnologías Utilizadas

- Java 21
- Spring Boot
- Maven
- JUnit 5
- ArchUnit 1.2.1
- GitHub Actions
- Git
- H2 Database

---

# Validación Arquitectónica con ArchUnit

Se implementó la clase:

```txt
src/test/java/com/empresa/pedidos/ReglasArquitectura.java
```

La cual contiene las reglas arquitectónicas ejecutables del sistema.

---

# Reglas Implementadas

## Regla 1 — Dominio Aislado

El dominio no puede depender de:

- infraestructura
- adaptadores
- Spring Framework

### Objetivo

Garantizar independencia del dominio y evitar acoplamiento tecnológico.

---

## Regla 2 — Controladores Desacoplados

Los controladores REST no pueden acceder directamente a:

- infraestructura
- procesadores Strategy

### Objetivo

Forzar el uso de la Facade como punto único de acceso.

---

## Regla 3 — Puertos como Interfaces

Todas las clases dentro de:

```txt
dominio.puertos
```

deben ser interfaces.

### Objetivo

Mantener arquitectura hexagonal basada en puertos y adaptadores.

---

## Regla 4 — Procesadores Implementan Puerto

Todos los procesadores deben implementar:

```java
ProcesadorPedido
```

### Objetivo

Garantizar consistencia entre estrategias de procesamiento.

---

## Regla 5 — Infraestructura Aislada de REST

La infraestructura no puede acceder directamente a los controladores REST.

### Objetivo

Mantener separación correcta entre capas.

---

# Ejecución de Reglas ArchUnit

## Ejecutar validación arquitectónica

```bash
./mvnw test -Dtest=ReglasArquitectura
```

---

# Resultado Esperado

```txt
Tests run: 5
Failures: 0
BUILD SUCCESS
```

---

# GitHub Actions

Se implementó integración continua mediante:

```txt
.github/workflows/arquitectura.yml
```

---

# Pipeline CI/CD

El pipeline ejecuta automáticamente:

- reglas ArchUnit
- pruebas unitarias
- validación completa del proyecto

en cada push y pull request.

---

# Workflow Implementado

```yaml
name: Validacion Arquitectonica
```

---

# Violación Arquitectónica Intencional

Se realizó una violación arquitectónica intencional agregando una dependencia de infraestructura dentro del dominio:

```java
private RepositorioPedidosJpa repositorio;
```

dentro de:

```txt
Pedido.java
```

---

# Resultado

ArchUnit detectó automáticamente la violación:

```txt
Architecture Violation
```

y GitHub Actions marcó el pipeline en rojo.

Posteriormente la violación fue revertida y el pipeline volvió a ejecutarse correctamente.

---

# ADRs

Se documentaron las decisiones arquitectónicas principales utilizando ADRs.

Ubicación:

```txt
docs/adr/
```

---

# ADR Implementados

## ADR-001

Arquitectura Hexagonal para aislar el dominio.

---

## ADR-002

Factory + Strategy para procesamiento de pedidos.

---

## ADR-003

Observer mediante Spring Events.

---

# Estructura Final del Proyecto

```txt
.github/workflows/
└── arquitectura.yml

docs/adr/
├── ADR-001.md
├── ADR-002.md
├── ADR-003.md

src/test/java/com/empresa/pedidos/
└── ReglasArquitectura.java
```

---

# Capturas

Las evidencias del laboratorio se encuentran en:

```txt
EvidenciaVisual/
```

---

# Ejecución del Proyecto

## Ejecutar validación arquitectónica

```bash
./mvnw test -Dtest=ReglasArquitectura
```

---

## Ejecutar pruebas completas

```bash
./mvnw verify
```

---