# Resolución de Conflicto 1

### Objetivo

Simular y resolver un conflicto de merge entre ramas feature utilizando GitHub Pull Requests.

### Ramas involucradas

* develop
* feature/Exception_JWT
* feature/test-conflict

### Archivo afectado

* UserServiceImpl.java

### Motivo del conflicto

El conflicto ocurrió porque dos ramas modificaron la misma línea del método `obtenerPorId()` utilizando diferentes excepciones.

### Cambios realizados

### Rama feature/Exception_JWT

```java
.orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
```

### Rama feature/test-conflict

```java
.orElseThrow(() -> new IllegalStateException("Usuario inválido"));
```

### Detección del conflicto

GitHub detectó el conflicto en el Pull Request `feature/Exception_JWT → develop` mostrando el mensaje:

```text
This branch has conflicts that must be resolved
```

### Resolución

Se utilizó Visual Studio Code para resolver manualmente el conflicto conservando la excepción personalizada `ResourceNotFoundException`.

### Comandos utilizados

```bash
git checkout feature/Exception_JWT
git merge develop
git add .
git commit -m "fix: resolver conflicto con develop"
git push origin feature/Exception_JWT
```

### Resultado final

El conflicto fue resuelto exitosamente y el Pull Request pudo continuar el proceso de revisión y merge.


# Resolución de Conflicto 2

### Objetivo

Simular y resolver un conflicto local de merge entre ramas locales.

### Ramas involucradas

* develop
* feature/local-a
* feature/local-b

### Archivo afectado

* README.md

### Motivo del conflicto

El conflicto ocurrió porque dos ramas modificaron la misma línea del archivo.

### Cambios realizados

### Rama feature/local-a

```Markdown
#book-tracker ejemplo conflicto ver. A
```

### Rama feature/lcoal-b

```Markdown
#book-tracker ejemplo conflicto ver. B
```

### Detección del conflicto

Git detectó el conflicto al intentar hacer merge de `feature/lcoal-b → feature/lcoal-a` mostrando el mensaje:

```git
Auto-merging README.md
CONFLICT (content): Merge conflict in README.md
Automatic merge failed; fix conflicts and then commit the result.
```

### Resolución

Se utilizó Visual Studio Code para resolver manualmente el conflicto dejando `#book-tracker`.

### Comandos utilizados

```bash
git checkout feature/local-a
git merge feature/local-b
git add .
git commit -m "fix: resolver conflicto local"
```

### Resultado final

El conflicto fue resuelto exitosamente y se pudo continuar con el proyecto.
