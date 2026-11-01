# Sistema de Nómina (Java)

## 📌 Descripción del Proyecto
Este es un sistema integral de nómina desarrollado en Java aplicando Programación Orientada a Objetos y principios SOLID. El sistema calcula los salarios de diferentes tipos de empleados (Asalariados, Temporales, Por Horas y Por Comisión), aplicando sus respectivos bonos y deducciones obligatorias.

**Regla de negocio principal:** El sistema cuenta con una validación global en la clase integradora que garantiza que ningún empleado pueda registrar un salario neto negativo.

## ⚙️ Metodología de Desarrollo
Para este proyecto utilizamos la metodología ágil **Scrum** adaptada a nuestro equipo.
* Dividimos el trabajo en módulos independientes asignados a cada desarrollador mediante ramas de características (`feature branches`).
* Trabajamos de forma paralela y utilizamos GitHub para el control de versiones, realizando *commits* frecuentes y atómicos.
* La integración final se centralizó en una rama de revisión (`feature/nomina`) resolviendo conflictos de fusión, para posteriormente realizar *Pull Requests* hacia la rama `main`, garantizando un código limpio y funcional.

## 👥 Equipo de Trabajo y Módulos
* **Mary (Persona 1):** Estructura base, Empleado Asalariado y Empleado Temporal.
* **Gerónimo (Persona 2):** Empleado por Horas y módulo de Deducciones.
* **Yhon (Persona 3):** Empleado por Comisión y Beneficios adicionales.
* **Jorge (Persona 4):** Integración del sistema (Calculadora de Nómina), resolución de dependencias globales y documentación.

## 🚀 Cómo ejecutar el proyecto
1. Clonar el repositorio: `git clone https://github.com/Mapeflo/SistemaNomina.git`
2. Abrir el proyecto en un IDE compatible con Java (IntelliJ IDEA, Eclipse, NetBeans).
3. Ejecutar la clase principal (`Main.java`) para visualizar el reporte de la nómina global en consola.