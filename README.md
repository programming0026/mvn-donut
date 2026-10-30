# Donut 3D

Un toro (donut) que gira en 3D dibujado con Java 2D, sin ninguna biblioteca gráfica: es una versión del famoso "donut.c" con proyección, iluminación y z-buffer.

## Características

- Animación a ~30 fotogramas por segundo.
- Proyección en perspectiva y rotación en dos ejes.
- Iluminación en escala de grises.
- Z-buffer para que las caras delanteras tapen a las traseras.
- Se puede empaquetar en un JAR ejecutable con `maven-shade-plugin`.

## Requisitos

- JDK 21 y Maven 3.9 o superior.

## Compilar y ejecutar

```bash
mvn compile exec:java

# o como JAR ejecutable
mvn clean package
java -jar target/mvn-donut-*.jar
```

## Licencia

Código de ejemplo de uso libre con fines educativos.
