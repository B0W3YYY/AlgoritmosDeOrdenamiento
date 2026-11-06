import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Tamano definido por el usuario
        System.out.print("Tamano del arreglo: ");
        int n = Integer.parseInt(sc.nextLine().trim());
        String[] palabras = new String[n];

        // Llenado del arreglo estatico
        for (int i = 0; i < n; i++) {
            System.out.print("Palabra " + (i + 1) + ": ");
            palabras[i] = sc.nextLine().trim();
        }

        // Menu de ordenamiento
        System.out.println("\n1. Seleccion\n2. Insercion\n3. Burbuja\n4. Mezcla\n5. Rapido");
        System.out.print("Elija un algoritmo: ");
        int op = Integer.parseInt(sc.nextLine().trim());

        switch (op) {
            case 1: seleccion(palabras); break;
            case 2: insercion(palabras); break;
            case 3: burbuja(palabras); break;
            case 4: mezcla(palabras, 0, n - 1); break;
            case 5: rapido(palabras, 0, n - 1); break;
            default:
                System.out.println("Opcion invalida.");
                return;
        }

        System.out.println("\nArreglo ordenado:");
        for (String p : palabras) System.out.println(" - " + p);

        // Busqueda binaria (requiere el arreglo ordenado)
        String seguir = "s";
        while (seguir.equalsIgnoreCase("s")) {
            System.out.print("\nPalabra a buscar: ");
            String clave = sc.nextLine().trim();
            int pos = busquedaBinaria(palabras, clave);
            if (pos >= 0) System.out.println("Encontrada en la posicion " + pos);
            else System.out.println("No se encontro.");
            System.out.print("Buscar otra? (s/n): ");
            seguir = sc.nextLine().trim();
        }
    }

    // SELECCION - O(n^2) en todos los casos
    static void seleccion(String[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            // Buscar el menor en la parte no ordenada
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j].compareTo(a[min]) < 0) min = j;
            }
            // Colocarlo al inicio de esa parte
            String tmp = a[i];
            a[i] = a[min];
            a[min] = tmp;
        }
    }

    // INSERCION - O(n^2) peor/promedio, O(n) si ya esta ordenado
    static void insercion(String[] a) {
        for (int i = 1; i < a.length; i++) {
            String actual = a[i];
            int j = i - 1;
            // Desplazar a la derecha los mayores que 'actual'
            while (j >= 0 && a[j].compareTo(actual) > 0) {
                a[j + 1] = a[j];
                j--;
            }
            // Insertar en su lugar
            a[j + 1] = actual;
        }
    }

    // BURBUJA - O(n^2) peor/promedio, O(n) si ya esta ordenado
    static void burbuja(String[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            boolean huboCambio = false;
            // Comparar vecinos; el mayor "sube" al final
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j].compareTo(a[j + 1]) > 0) {
                    String tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    huboCambio = true;
                }
            }
            // Sin cambios = ya esta ordenado
            if (!huboCambio) break;
        }
    }

    // MEZCLA (Merge Sort) - O(n log n) en todos los casos
    static void mezcla(String[] a, int ini, int fin) {
        if (ini >= fin) return;          // Caso base: 0 o 1 elemento
        int mid = (ini + fin) / 2;
        mezcla(a, ini, mid);             // Dividir: mitad izquierda
        mezcla(a, mid + 1, fin);         // Dividir: mitad derecha
        unir(a, ini, mid, fin);          // Combinar ambas mitades
    }

    // Une dos mitades ya ordenadas en una sola
    static void unir(String[] a, int ini, int mid, int fin) {
        String[] tmp = new String[fin - ini + 1];
        int i = ini, j = mid + 1, k = 0;
        // Tomar siempre el menor de ambas mitades
        while (i <= mid && j <= fin) {
            if (a[i].compareTo(a[j]) <= 0) tmp[k++] = a[i++];
            else tmp[k++] = a[j++];
        }
        // Copiar lo que sobre
        while (i <= mid) tmp[k++] = a[i++];
        while (j <= fin) tmp[k++] = a[j++];
        // Devolver al arreglo original
        for (k = 0; k < tmp.length; k++) a[ini + k] = tmp[k];
    }

    // RAPIDO (Quick Sort) - O(n log n) promedio, O(n^2) peor caso
    static void rapido(String[] a, int ini, int fin) {
        if (ini >= fin) return;
        int p = particion(a, ini, fin);  // Pivote queda en su lugar final
        rapido(a, ini, p - 1);           // Ordenar menores
        rapido(a, p + 1, fin);           // Ordenar mayores
    }

    // Deja a la izquierda los menores al pivote y a la derecha los mayores
    static int particion(String[] a, int ini, int fin) {
        String pivote = a[fin];          // Ultimo elemento como pivote
        int i = ini - 1;
        for (int j = ini; j < fin; j++) {
            if (a[j].compareTo(pivote) <= 0) {
                i++;
                String tmp = a[i]; a[i] = a[j]; a[j] = tmp;
            }
        }
        // Colocar el pivote tras los menores
        String tmp = a[i + 1]; a[i + 1] = a[fin]; a[fin] = tmp;
        return i + 1;
    }

    // BUSQUEDA BINARIA - O(log n): cada paso descarta la mitad del rango
    static int busquedaBinaria(String[] a, String clave) {
        int ini = 0, fin = a.length - 1;
        while (ini <= fin) {
            int mid = (ini + fin) / 2;               // Elemento central
            int cmp = clave.compareTo(a[mid]);
            if (cmp == 0) return mid;                // Encontrado
            if (cmp < 0) fin = mid - 1;              // Buscar a la izquierda
            else ini = mid + 1;                      // Buscar a la derecha
        }
        return -1;                                   // No esta
    }
}
