# Sistema Integrado de Gestión de Pedidos

---

# Arquitectura del Proyecto

El sistema fue organizado utilizando una arquitectura por capas inspirada en principios de arquitectura limpia.

```txt
src/main/java/com/empresa/pedidos/

├── dominio/
├── aplicacion/
├── infraestructura/
├── adaptadores/
├── eventos/
```

---

# Descripción de las Capas

## Dominio

Contiene las entidades principales y las interfaces (puertos) del sistema.

### Responsabilidades

- Definir reglas del negocio
- Definir contratos
- Mantener independencia tecnológica

### Ejemplos

- Pedido
- TipoPedido
- EstadoPedido
- ProcesadorPedido
- RepositorioPedidos

---

## Aplicación

Coordina el flujo principal del sistema y conecta los componentes.

### Responsabilidades

- Orquestar procesos
- Ejecutar casos de uso

---

## Infraestructura

Implementa detalles técnicos y externos.

### Responsabilidades

- Persistencia
- Base de datos
- Notificaciones

### Ejemplos

- RepositorioPedidosJpa
- NotificacionEmail
- NotificacionLog

---

## Adaptadores

Contiene las implementaciones concretas de los patrones de diseño y el acceso REST.

### Responsabilidades

- Implementar lógica desacoplada
- Conectar el sistema con clientes externos

### Ejemplos

- Procesadores Strategy
- Factory
- Facade
- Controller REST

---

# Patrones de Diseño Implementados

## 1. Strategy

### Problema identificado

El sistema original utilizaba múltiples condicionales `if/else` para calcular costos según el tipo de pedido, generando:

- Alto acoplamiento
- Difícil mantenimiento
- Violación del principio Open/Closed

### Solución aplicada

Se creó una estrategia independiente para cada tipo de pedido:

- ProcesadorPedidoEstandar
- ProcesadorPedidoExpress
- ProcesadorPedidoInternacional

Cada clase implementa:

```java
ProcesadorPedido
```

### Beneficios obtenidos

- Eliminación de condicionales complejos
- Mayor extensibilidad
- Bajo acoplamiento
- Código más limpio

---

## 2. Factory

### Problema identificado

Después de implementar múltiples estrategias, el sistema necesitaba decidir dinámicamente cuál utilizar según el tipo de pedido.

Sin una Factory, el sistema seguiría dependiendo de múltiples condicionales.

### Solución aplicada

Se implementó:

```java
ProcesadorPedidoFactory
```

La Factory selecciona automáticamente el Strategy correcto utilizando el tipo de pedido.

### Beneficios obtenidos

- Centralización de creación
- Eliminación de lógica repetitiva
- Mejor mantenibilidad
- Mayor desacoplamiento

---

## 3. Observer

### Problema identificado

El sistema original mezclaba la lógica principal con las notificaciones, provocando:

- Dependencias innecesarias
- Código difícil de extender
- Violación del principio de responsabilidad única

### Solución aplicada

Se implementó un sistema de eventos utilizando:

```java
PedidoProcesadoEvent
```

y dos listeners:

- NotificacionEmail
- NotificacionLog

### Beneficios obtenidos

- Desacoplamiento de notificaciones
- Escalabilidad
- Facilidad para agregar nuevos listeners
- Mejor separación de responsabilidades

---

## 4. Facade

### Problema identificado

El controlador REST debía interactuar directamente con múltiples componentes internos, aumentando la complejidad y el acoplamiento.

### Solución aplicada

Se implementó:

```java
FachadaPedidos
```

La fachada centraliza todo el flujo de procesamiento del pedido.

### Beneficios obtenidos

- Simplificación del controlador
- Reducción de complejidad
- Mejor organización del flujo
- Punto único de acceso al sistema

---

# Flujo Completo del Sistema

1. El cliente realiza un POST al endpoint REST.
2. El Controller delega la solicitud a la Fachada.
3. La Factory selecciona el Strategy adecuado.
4. El pedido es procesado.
5. El pedido es almacenado en la base de datos H2.
6. Se publica un evento.
7. Los listeners responden automáticamente.

---

# Pruebas Implementadas

Se realizaron pruebas para validar:

- Procesamiento Strategy
- Selección Factory
- Publicación Observer
- Funcionamiento Facade
- Reglas arquitectónicas con ArchUnit
- Integración SpringBootTest

---

# Resultados SonarQube

Se realizó análisis estático de calidad utilizando SonarQube.

## Resultados obtenidos

| Métrica | Resultado |
|---|---|
| Quality Gate | PASSED |
| Maintainability | A |
| Reliability | A |
| Security | D |
| Duplications | 0% |

---

# Evidencias Visuales

Todas las Evidencias Visuales se encuentran en la carpeta
```txt
EvidenciaVisual/
```
---

# Ejecución del Proyecto

## Ejecutar aplicación

```bash
./mvnw spring-boot:run
```

---

## Ejecutar pruebas

```bash
./mvnw test
```

---

## Ejecutar SonarQube

```bash
./mvnw verify sonar:sonar \
-Dsonar.host.url=http://localhost:9000 \
-Dsonar.token=TOKEN \
-Dsonar.projectKey=pedidos
```

---

