# Radix Sort y Shell Sort

## Radix Sort
**Qué hace:** ordena números (o cadenas) sin compararlos entre sí; los agrupa dígito por dígito.

**Cómo funciona (LSD, del dígito menos significativo):**
1. Toma el dígito de las unidades de cada número.
2. Reparte los números en 10 "cubetas" (0–9) según ese dígito.
3. Los recoge en orden de cubeta (manteniendo el orden relativo: es estable).
4. Repite con decenas, centenas, ... hasta el dígito más largo.

**Ejemplo:** `170, 45, 75, 90, 2`
- Unidades → `170, 90, 2, 45, 75`
- Decenas → `2, 45, 170, 75, 90`
- Centenas → `2, 45, 75, 90, 170` ✔

**Complejidad:** O(n · k), donde k = cantidad de dígitos. Usa memoria extra para las cubetas.
**Uso típico:** muchos enteros o claves de longitud fija (cédulas, códigos postales).

## Shell Sort
**Qué hace:** es una mejora del ordenamiento por inserción. Compara elementos lejanos primero
para mover rápido los que están muy fuera de lugar.

**Cómo funciona:**
1. Elige un salto (gap), por ejemplo n/2.
2. Aplica inserción entre elementos separados por ese salto.
3. Reduce el salto (gap/2) y repite.
4. Con gap = 1 es una inserción normal, pero el arreglo ya está casi ordenado, así que es rápida.

**Ejemplo:** `[9, 8, 3, 7, 5, 6, 4, 1]`, gap = 4 → compara (9,5), (8,6), (3,4), (7,1) y
los intercambia si hace falta; luego gap = 2, luego gap = 1.

**Complejidad:** depende de la secuencia de saltos; con n/2, n/4... es O(n²) en el peor caso,
aprox. O(n^1.5) en la práctica. No usa memoria extra (in-place), no es estable.
**Uso típico:** arreglos medianos donde se quiere algo más rápido que inserción sin recursión.

## Diferencia clave
- Radix: **no compara**, distribuye por dígitos.
- Shell: **compara**, es inserción con saltos decrecientes.
