#  Gestor de Biblioteca

### Autores:
- Gerardo Adrian Ramos Valdivia, U23273502  
- Rojas Gamonal Samir Angel, U23243868  
- Salazar Camacho Sandro Piero, U23266038  
- Tapia Canta Adriana Lucía, U23202658  

### Docente:
Jose Luis Milla Flores  

### Universidad:
Universidad Tecnológica del Perú 

### Fecha de inicio y finalización:
- Inicio: 11 de abril de 2026  
- Fin: 15 de julio de 2026  


## Agradecimiento
Agradecemos al docente del curso por su orientación durante el desarrollo de este proyecto y los conocimientos adquiridos, así como a nuestra universidad por brindarnos los recursos necesarios para su realización.

## Dedicatoria
Dedicamos este trabajo a nuestras familias y a todas las personas que apoyaron nuestro proceso de formación académica.

## Resumen
El presente proyecto consiste en el diseño e implementación de una plataforma web orientada a la gestión personalizada de bibliotecas digitales, desarrollada bajo el framework Spring Boot y una arquitectura modular. El sistema permite a los usuarios autenticados interactuar con una API pública de libros para la búsqueda, interacción de comentarios y catalogación de obras en listas personalizadas (e.g., "por leer", "completado"). El núcleo de la aplicación reside en la persistencia de metadatos bibliográficos y la capacidad de los usuarios para registrar calificaciones y reseñas críticas, facilitando un seguimiento detallado de su actividad lectora. El diseño de la base de datos optimiza la relación entre usuarios y libros mediante una tabla de asociación que garantiza la integridad de los comentarios. La implementación destaca por el uso de WebClient para el consumo asíncrono de servicios externos y una estrategia de gestión de imágenes basada en el lado del cliente para optimizar el rendimiento del servidor.

Palabras clave: Spring Boot, API de Libros, JPA, Persistencia de Datos, Gestión Bibliográfica, WebClient, Arquitectura de Software.


## Abstract
The present project involves the design and implementation of a web platform for personalized digital library management, developed using the Spring Boot framework and a modular architecture. The system enables authenticated users to interact with a public book API to search, leave comments, catalog literary works into customized lists (e.g., "to read", "completed"). The core of the application lies in the persistence of bibliographic metadata and the ability for users to record ratings and critical reviews, facilitating a detailed tracking of their reading activity. The database design optimizes the relationship between users and books through an associative table that ensures comment integrity. The implementation stands out for its use of WebClient for asynchronous consumption of external services and a client-side image management strategy to optimize server performance.

Keywords: Spring Boot, Book API, JPA, Data Persistence, Bibliographic Management, WebClient, Software Architecture.

##  Realidad problemática
En la era de la sobreinformación digital, la retención de conocimientos provenientes de la lectura profunda ha disminuido drásticamente. Según artículos del CERLALC (Centro Regional para el Fomento del Libro en América Latina y el Caribe), aunque el uso de textos digitales en la población peruana ha incrementado de 20,0 % al 25,6 % en los últimos años, la capacidad de síntesis y memoria crítica de los lectores jóvenes puede haber disminuido. De la teoría del 'Ahorro de Reaprendizaje' de Ebbinghaus, se puede deducir que si un usuario invirtió 10 horas en comprender un libro, pero meses después necesita las mismas 10 horas para recordarlo, su ahorro es del 0%, lo que quiere decir que olvidó el libro por completo.

El problema detectado no es la falta de lectura, sino la falta de gestión del conocimiento personal. Los lectores actuales carecen de una herramienta centralizada que combine la organización bibliográfica con la capacidad de almacenar resúmenes y opiniones propias de manera estructurada que les permita recordar lo aprendido. Las aplicaciones genéricas de notas son demasiado dispersas, y las redes sociales de libros se enfocan más en la calificación que en la reflexión profunda del lector.


##  Justificación

###  Relevancia Social
Este proyecto fomenta la alfabetización funcional. Al facilitar un espacio para opiniones y resúmenes, se incentiva al usuario a procesar la información en lugar de consumirla pasivamente. Socialmente, esto contribuye a ciudadanos con mayor capacidad crítica frente a problemáticas.  

###  Relevancia Económica y Científica
Desde una perspectiva científica, el proyecto se alinea con las teorías de la psicología cognitiva sobre la repetición espaciada y el auto-procesamiento de la información. Económicamente, el desarrollo de este software permite optimizar el tiempo de investigadores y estudiantes, quienes suelen gastar horas valiosas buscando apuntes dispersos y tratando de recordar lo leído. Centralizar esta información reduce la deuda cognitiva y mejora la productividad académica.  


## Objetivos

### Objetivo General
Diseñar e implementar un software de gestión de libros favoritos que integre una API externa para organizar lecturas, almacenar resúmenes y opiniones personales, con el fin de fortalecer la memoria crítica y la gestión del conocimiento en la era digital.

### Objetivos Específicos
- Analizar las necesidades de los lectores en relación con la gestión del conocimiento personal, elaborando un informe de requerimientos funcionales y no funcionales.
- Diseñar la arquitectura del sistema y la base de datos relacional que soporte usuarios, libros, listas y reseñas, documentando un esquema técnico documentado.
- Implementar la integración con una API externa de libros para permitir la búsqueda y registro de títulos, desarrollando un módulo funcional de conexión.
- Desarrollar un prototipo interactivo (MVP) que permita a los usuarios crear listas de favoritos, añadir reseñas y almacenar resúmenes, entregando una versión inicial utilizable dentro del curso.


## Marco Teórico

El presente marco teórico tiene como finalidad sustentar el desarrollo del sistema propuesto a partir de teorías e investigaciones relacionadas con la lectura digital, la memoria y la gestión del conocimiento. A través del análisis del estado del arte y conceptos clave, se busca comprender cómo han cambiado los hábitos de lectura y qué herramientas pueden contribuir a mejorar la retención y organización de la información.

### 1. Estado del arte
En los últimos años, la lectura se ha ido digitalizando progresivamente, permitiendo a los usuarios acceder a contenidos desde distintos dispositivos. Este cambio ha facilitado el acceso a la información, pero también ha generado nuevos retos en la comprensión y retención de lo leído. Diversos estudios recientes indican que la lectura en entornos digitales tiende a ser más fragmentada, ya que los usuarios interactúan con múltiples fuentes al mismo tiempo. Esto puede afectar la concentración y dificultar la comprensión profunda del contenido. Asimismo, Cordón y Jarvio (2015) señalan que la transformación digital de la lectura ha modificado los hábitos de los lectores, quienes ahora priorizan la rapidez y la accesibilidad sobre el análisis detallado. Por otro lado, investigaciones más recientes evidencian que la lectura digital puede mejorar ciertos aspectos del aprendizaje si se utilizan herramientas adecuadas, como sistemas de organización, anotación y seguimiento del contenido (Pérez et al., 2023). También se ha observado que los jóvenes consumen mayor cantidad de información en formato digital, pero muchas veces sin procesarla de manera crítica (Figueroa et al., 2016). Esto refuerza la idea de que el problema no es el acceso a la lectura, sino la forma en que se gestiona la información. En conjunto, estos estudios evidencian la necesidad de desarrollar herramientas que permitan no solo acceder a libros, sino también organizar, reflexionar y retener el conocimiento obtenido.

### 2. Memoria y proceso de aprendizaje
La memoria es un proceso fundamental en el aprendizaje, ya que permite almacenar y recuperar información. Sin embargo, la retención del conocimiento depende de cómo se procese la información. Baddeley (2015) explica que la memoria humana requiere procesos activos para consolidar la información, como la repetición, la organización y la asociación de ideas. Esto implica que la lectura pasiva no es suficiente para garantizar el aprendizaje. En el contexto actual, donde existe una gran cantidad de información disponible, las personas tienden a olvidar con mayor facilidad aquello que no utilizan o refuerzan. Por ello, es necesario contar con herramientas que faciliten la organización y recuperación del conocimiento.

### 3. Lectura digital y comprensión
La lectura digital ha introducido nuevas formas de interacción con el texto, modificando los procesos tradicionales de comprensión. A diferencia de la lectura en formato impreso, los entornos digitales suelen presentar información de manera no lineal, lo que obliga al lector a tomar decisiones constantes sobre qué leer, cómo hacerlo y en qué orden. Según Delgado, Vargas, Ackerman y Salmerón (2018), la comprensión lectora puede verse afectada en medios digitales debido a factores como la distracción, la navegación hipertextual y la menor profundidad en el procesamiento de la información. Estos elementos pueden generar una lectura más rápida, pero menos reflexiva. Además, destacan que la comprensión en entornos digitales mejora cuando el lector aplica estrategias metacognitivas, como planificar la lectura, monitorear la comprensión y evaluar la información encontrada. Asimismo, la Organización para la Cooperación y el Desarrollo Económicos (OCDE, 2019) señala que los estudiantes con mejores habilidades de navegación digital logran interpretar y relacionar información de múltiples fuentes con mayor eficacia. En este contexto, la comprensión lectora en entornos digitales no depende únicamente del acceso a la información, sino de la capacidad del usuario para gestionarla activamente. Por ello, resulta fundamental contar con herramientas que faciliten la organización, el análisis y la reflexión del contenido leído.

### 4. Gestión del conocimiento personal
En la era digital, las personas manejan grandes cantidades de información, lo que hace necesario contar con estrategias para organizarla. La gestión del conocimiento personal se refiere al proceso mediante el cual un individuo recopila, organiza y utiliza información de manera eficiente. En este contexto, las herramientas digitales juegan un papel fundamental. Según Montoya et al. (2019), los sistemas digitales pueden facilitar la organización del conocimiento cuando permiten estructurar la información y acceder a ella fácilmente. En el caso de la lectura, esto implica contar con una herramienta que permita registrar libros, almacenar resúmenes, guardar opiniones y organizar contenido de manera estructurada. El sistema propuesto responde a esta necesidad, ofreciendo una solución centralizada para la aplicación del conocimiento lector.

### 5. Lectura digital y pensamiento crítico
La lectura no solo implica comprender información, sino también analizarla y reflexionar sobre ella. Investigaciones recientes indican que la lectura digital puede contribuir al desarrollo del pensamiento crítico cuando se acompaña de estrategias de análisis y reflexión (González-Ramírez et al., 2020). Sin embargo, el consumo rápido de información en entornos digitales puede limitar este proceso, favoreciendo una lectura superficial. Por ello, es importante fomentar prácticas como la escritura de resúmenes y opiniones, las cuales permiten procesar la información de manera más profunda.

##  Metodología
El presente proyecto corresponde a una investigación de tipo aplicada, ya que tiene como finalidad desarrollar una solución tecnológica orientada a mejorar la gestión del conocimiento en la lectura digital. Asimismo, se adopta un enfoque cualitativo, debido a que se busca comprender el problema a partir del análisis del comportamiento del usuario frente al uso de herramientas digitales, especialmente en la organización y retención de la información leída.

Para la recolección de información se empleará la técnica de observación, la cual permitirá analizar cómo los usuarios interactúan con aplicaciones actuales de lectura y toma de notas, identificando dificultades en la organización, recuperación y comprensión de la información. Esta técnica se complementará con la revisión documental de estudios académicos relacionados con la memoria, la lectura digital y la gestión del conocimiento, lo que permitirá sustentar teóricamente el desarrollo del proyecto.

En cuanto a los materiales e instrumentos, se utilizará una computadora personal, el entorno de desarrollo Visual Studio Code, el framework Angular para el desarrollo del frontend, Spring Boot para la construcción del backend y PostgreSQL como sistema de gestión de base de datos. Asimismo, se integrará una API externa de libros que permitirá la búsqueda y registro de información bibliográfica dentro del sistema.

El diseño del sistema se desarrollará bajo un enfoque de ingeniería de software iterativo, basado en la construcción de un producto mínimo viable (MVP). Este proceso incluirá la etapa de análisis, en la que se identificarán los requerimientos del sistema; diseño, donde se definirá la arquitectura cliente-servidor y el modelo de base de datos; implementación, en la que se desarrollarán los componentes del frontend y backend, además de la integración con la API externa; y finalmente la etapa de pruebas, donde se verificará el correcto funcionamiento de las funcionalidades principales del sistema.

Finalmente, la validación del sistema se realizará comprobando que el usuario pueda buscar libros mediante la API, registrar sus libros favoritos, almacenar resúmenes y opiniones, así como verificar que la información se guarde correctamente en la base de datos. También se evaluará que el sistema sea funcional y fácil de utilizar, asegurando que cumpla con el objetivo de mejorar la gestión del conocimiento del usuario.



