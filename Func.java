import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

import java.awt.*;

public class Func {

    public class Carro {
        int numeroControl;
        String color;
        String marca;
        String modelo;
        int monto;
        String nombreComprador;
        int edad;
        char metodoPago;
        double montoEnganche;
        double liquidacion;
        String mensualidad;
        /*gets */
        public int getNumeroControl() {
            return numeroControl;
        }
        public String getColor() {
            return color;
        }
        public String getMarca() {
            return marca;
        }
        public String getModelo() {
            return modelo;
        }
        public int getMonto() {
            return monto;
        }
        /*sets */
        public void setNumeroControl(int numeroControl) {
            this.numeroControl = numeroControl;
        }
        public void setColor(String color) {
            this.color = color;
        }
        public void setMarca(String marca) {
            this.marca = marca;
        }
        public void setModelo(String modelo) {
            this.modelo = modelo;
        }
        public void setMonto(int monto) {
            this.monto = monto;
        }
    }
    
    public static int[] array = new int[10];
    public static int[] mesesAdeudo = new int[10];
    public static int i, l, valores = 9, a = 0, inicio, nuevoValor, valorEliminar, posicion;
    public static char eliminar;
    public static String[] color = {"Blanco", "Negro", "Azul", "Rojo", "Verde", "Amarillo", "Plata", "Gris", "Rosa", "Naranja"};
    public static String[] marca = {"Audi", "BMW", "MercedesBenz", "Mclaren", "Lamborghini", "Toyota", "Chevrolet", "Nissan", "Mazda", "Renoult"};
    public static String[] modelo = {"A1", "A2", "A3", "A4", "Aventador", "A6", "A7", "A8", "A9", "A10"};
    public static int[] monto = {1087000, 1000000, 900000, 800000, 600000, 500000, 400000, 300000, 200000, 100000};
    public static String[] nombreComprador = new String[10];
    public static int[] edad = new int[10];
    public static String[] metodoPago = new String[10];
    public static double[] montoEnganche = new double[10];
    public static double[] liquidacion = new double[10];
    public static double[] adeudo = new double[10];
    public static double[] pagoPorMes = new double[10];
    
    public static void valoresAleatorios() {
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(9000) + 1000;
        }
    }

    public static void mostrarDatos() {
        System.out.print("\n\tInventario:\n");

        for (int i = 0; i <= valores; i++) {
            System.out.print(String.format("\nCarro no.%d:\nColor: %s\nMarca: %s\nModelo: %s\nPrecio: %d\n", array[i], color[i], marca[i], modelo[i], monto[i]));
        }

        System.out.print("\n\n");
    }

    public static void mostrarDatosSeleccionados(Carro carro) {
        System.out.print("\nElemento seleccionado:\n");
        System.out.print(String.format("Carro no.%d:\nColor: %s\nMarca: %s\nModelo: %s\nPrecio: %d\n", carro.getNumeroControl(), carro.getColor(), carro.getMarca(), carro.getModelo(), carro.getMonto()));
        System.out.print("\n\n");
    }

    public static void mostrarCarrosVendidos(Carro[] carrosVendidos, int numCarrosVendidos) {

        System.out.print("\nCarros vendidos hasta el momento:\n");
        for (int i = 0; i < numCarrosVendidos; i++) {
            System.out.print(String.format("\n\t\tVenta no.%d\n\nComprador: %s\nCarro no.%d:\nColor: %s\nMarca: %s\nModelo: %s\nPrecio: %d\n", i + 1, nombreComprador[i], carrosVendidos[i].getNumeroControl(), carrosVendidos[i].getColor(), carrosVendidos[i].getMarca(), carrosVendidos[i].getModelo(), carrosVendidos[i].getMonto()));
        }

        System.out.print("\n\n");
    }

    public static Scanner scanner = new Scanner(System.in);

    public static void mostrarDatosComprador(Carro[] compradores, int numCompradores) {
        for (int i = 0; i < numCompradores; i++) {
            System.out.print(String.format("\n\nComprador %d: %s", i + 1, nombreComprador[i]));
            System.out.print(String.format("\nEdad: %d", edad[i]));
            System.out.print(String.format("\nMétodo de pago: %s", metodoPago[i]));

            if (montoEnganche[i] == liquidacion[i]) {
                System.out.print(String.format("\nSe realizará el pago completo: %.2f", liquidacion[i]));
            } else {
                System.out.print(String.format("\nMonto de Enganche: %.2f", montoEnganche[i]));
                System.out.print(String.format("\nAdeudo: %.2f", adeudo[i]));
            }

            System.out.print(String.format("\nModelo solicitado: %s %s", compradores[i].getMarca(), compradores[i].getModelo()));
        }
        System.out.print("\n\n");
    }

    public static void mostrarInfoPago(int numCompradores) {
        for (int i = 0; i < numCompradores; i++) {
            System.out.print(String.format("\n\nComprador %d: %s", i + 1, nombreComprador[i]));
            System.out.print(String.format("\nMétodo de pago: %s", metodoPago[i]));

            if (montoEnganche[i] == liquidacion[i]) {
                System.out.print(String.format("\nSe realizará el pago completo: %.2f", liquidacion[i]));
            } else {
                System.out.print(String.format("\nMonto de Enganche: %.2f", montoEnganche[i]));
                System.out.print(String.format("\nAdeudo: %.2f", adeudo[i]));
                System.out.print(String.format("\nPlazo: %d años \nMensualidad: $%.2f", mesesAdeudo[i], pagoPorMes[i]));
            }
        }
        System.out.print("\n\n");
    }

    public static void buscar(Carro carroSeleccionado, Carro[] carrosVendidos, int numCarrosVendidos, Carro[] compradores, int numCompradores) {
        do {
            int valorEliminar = 0;
            int truE = 0;
            System.out.print("\nSelecciona un carro a través de su número de control: ");
            String input = scanner.nextLine().trim();

            try {
                valorEliminar = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Entrada no válida. Introduce un número de control válido.");
                continue;
            }

            for (int i = 0; i <= valores; i++) {
                if (array[i] == valorEliminar) {
                    truE = 1;
                    a = 1;
                    inicio = i;
                    registroAutos(carroSeleccionado, carrosVendidos, numCarrosVendidos, compradores, numCompradores);
                    break;
                }
            }
            if (truE == 1) {
                break;
            }
        } while (true);
    }

    public static void registroAutos(Carro carro, Carro[] carrosVendidos, int numCarrosVendidos, Carro[] compradores, int numCompradores) {
        carro.setNumeroControl(array[inicio]);
        carro.setColor(color[inicio]);
        carro.setMarca(marca[inicio]);
        carro.setModelo(modelo[inicio]);
        carro.setMonto(monto[inicio]);

        for (int i = inicio; i < valores; i++) {
            array[i] = array[i + 1];
            color[i] = color[i + 1];
            marca[i] = marca[i + 1];
            modelo[i] = modelo[i + 1];
            monto[i] = monto[i + 1];
        }

        array[valores] = 0;
        color[valores] = null;
        marca[valores] = null;
        modelo[valores] = null;
        monto[valores] = 0;
        valores--;

        carrosVendidos[numCarrosVendidos] = new Func().new Carro();
        carrosVendidos[numCarrosVendidos].setNumeroControl(carro.getNumeroControl());
        carrosVendidos[numCarrosVendidos].setColor(carro.getColor());
        carrosVendidos[numCarrosVendidos].setMarca(carro.getMarca());
        carrosVendidos[numCarrosVendidos].setModelo(carro.getModelo());
        carrosVendidos[numCarrosVendidos].setMonto(carro.getMonto());
        compradores[numCompradores] = new Func().new Carro();
        compradores[numCompradores].setMarca(carro.getMarca());
        compradores[numCompradores].setModelo(carro.getModelo());
    }

    public static void datos(int i, Carro carro) {
        double a, b, c;

        System.out.println("\nIngresa el nombre del comprador: ");
        nombreComprador[i] = scanner.nextLine();

        while (true) {
            edad[i] = 0;
            System.out.println("\nIngresa la edad del comprador: ");
            String str = scanner.nextLine();

            try {
                edad[i] = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Ingresa un número válido para la edad.");
                continue;
            }

            if (edad[i] < 18 || edad[i] > 100) {
                System.out.println("\nEl cliente no puede ser menor de edad o tener más de 100 años");
            } else {
                break;
            }
        }

        System.out.println("\nIngresa el método de pago: ");
        metodoPago[i] = scanner.nextLine();

        while (true) {
            b = 0;
            System.out.printf("\nDefina el porcentaje de enganche, IVA incluido: $%d", carro.monto);
            System.out.println("\nSolo puede seleccionar porcentajes entre el 20 y 80 porciento, en caso de pago de contado ingrese 100: ");
            String str2 = scanner.nextLine();

            try {
                b = Double.parseDouble(str2);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Ingresa un número válido para el porcentaje de enganche.");
                continue;
            }

            if (b >= 20 && b <= 80) {
                break;
            }
            if (b == 100) {
                break;
            }
        }

        a = b / 100;
        montoEnganche[i] = a * carro.monto;
        adeudo[i] = carro.monto - montoEnganche[i];
        liquidacion[i] = carro.monto;

        if (b >= 20 && b <= 80) {
            do {
                mesesAdeudo[i] = 0;
                System.out.println("\nA cuántos años se pagará el adeudo? (max. 10 años): ");
                String str3 = scanner.nextLine();

                try {
                    mesesAdeudo[i] = Integer.parseInt(str3);
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida. Ingresa un número válido para el plazo de pago.");
                    continue;
                }

                if (mesesAdeudo[i] < 10 && mesesAdeudo[i] > 0) {
                    break;
                }

            } while (true);

            c = mesesAdeudo[i] * 12;
            pagoPorMes[i] = adeudo[i] / c;
            System.out.printf("\nMensualidad: %.2f\n", pagoPorMes[i]);
        }

    }

    public static void recibo_personal(Carro[] compradores, int num_compradores) throws IOException{
        
        LocalDateTime date = LocalDateTime.now();

        DateTimeFormatter fecha_formateada = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        DateTimeFormatter hora_formateada = DateTimeFormatter.ofPattern("HH:mm:ss");

        String fecha = date.format(fecha_formateada);
        String hora = date.format(hora_formateada);

        Scanner scanner3 = new Scanner(System.in);

        System.out.println("Ingrese el nombre de su recibo");
        String file_name = scanner3.nextLine();

        mostrar_compradores(num_compradores);
        
        System.out.println("Ingrese el nombre del comprador para imprimir su recibo");
        String nombre = scanner3.nextLine();
        
        while (!new File(file_name+".txt").exists()) {

            try {    
            
                FileWriter fileWriter = new FileWriter(new File(file_name+".txt"), true);
            
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);            
    
                bufferedWriter.write("Fecha de impresion: "+fecha+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hora);
                bufferedWriter.newLine(); 
    
                for (i = 0; i < num_compradores; i++){
    
                    if (nombre.equals(nombreComprador[i])) {
    
            
                        bufferedWriter.write(String.format("\n\nComprador %d: %s",i+1,(nombreComprador[i] == null ? "" : nombreComprador[i])));
                        bufferedWriter.newLine();
                        bufferedWriter.write(String.format("Edad: %d",edad[i]));
                        bufferedWriter.newLine();
                        bufferedWriter.write(String.format("M�todo de pago: %s",(metodoPago[i] == null ? "" : metodoPago[i])));
                        bufferedWriter.newLine();
    
                        if (montoEnganche[i]==liquidacion[i]){
                            
                            bufferedWriter.write(String.format("Se realizar� el pago completo: %.2f",liquidacion[i]));
                        }else{
                            bufferedWriter.write(String.format("Monto de Enganche: %.2f",montoEnganche[i]));
                            bufferedWriter.newLine();
                            bufferedWriter.write(String.format("Adeudo: %.2f",adeudo[i]));
                            bufferedWriter.newLine();
                            bufferedWriter.write(String.format("Plazo: %d años\nMensualidad %.2f", mesesAdeudo[i], pagoPorMes[i]));
                            bufferedWriter.newLine();
                            bufferedWriter.write(String.format("Si tarda mas de 3 meses en pagar su mensualidad se le embargar�"));
                        }        
                        bufferedWriter.newLine();
                        bufferedWriter.write(String.format("Modelo solicitado: %s %s",compradores[i].marca,compradores[i].modelo));
                    }
                }
            
                System.out.println("El recibo se ha creado exitosamente!\nDesea abrir el recibo? (s/n): ");
                String abrir = scanner3.nextLine();
                if (abrir.equals("S") || abrir.equals("s")) {
                    try {
                        File file = new File(file_name+".txt");
                        if (!Desktop.isDesktopSupported()) {
                            System.out.println("not supported");
                        }
                        Desktop desktop = Desktop.getDesktop();
                        if (file.exists())
                            desktop.open(file);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
    
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }    
            scanner3.close();
        }
    }

    public static void mostrar_compradores(int num) {

        for (i = 0; i < num; i++){
            System.out.println(String.format("\nComprador %d: %s\n",i+1,nombreComprador[i]));
        }
    }


    public static void recibo(Carro[] compradores, int num_compradores) {
        LocalDateTime date = LocalDateTime.now();

        DateTimeFormatter fecha_formateada = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        DateTimeFormatter hora_formateada = DateTimeFormatter.ofPattern("HH:mm:ss");

        String fecha = date.format(fecha_formateada);
        String hora = date.format(hora_formateada);

        Scanner scanner3 = new Scanner(System.in);

        System.out.println("Ingrese el nombre de su recibo");
        String file_name = scanner3.nextLine();
        
        while (!new File(file_name+".txt").exists()) {

            try {    
            
                FileWriter fileWriter = new FileWriter(new File(file_name+".txt"), true);
            
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);            
    
                bufferedWriter.write("Fecha de impresion: "+fecha+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+hora);
                bufferedWriter.newLine(); 
    
                for (i = 0; i < num_compradores; i++){
        
                        bufferedWriter.write(String.format("\n\nComprador %d: %s",i+1,(nombreComprador[i] == null ? "" : nombreComprador[i])));
                        bufferedWriter.newLine();
                        bufferedWriter.write(String.format("Edad: %d",edad[i]));
                        bufferedWriter.newLine();
                        bufferedWriter.write(String.format("M�todo de pago: %s",(metodoPago[i] == null ? "" : metodoPago[i])));
                        bufferedWriter.newLine();
    
                        if (montoEnganche[i]==liquidacion[i]){
                            
                            bufferedWriter.write(String.format("Se realizar� el pago completo: %.2f",liquidacion[i]));
                        }else{
                            bufferedWriter.write(String.format("Monto de Enganche: %.2f",montoEnganche[i]));
                            bufferedWriter.newLine();
                            bufferedWriter.write(String.format("Adeudo: %.2f",adeudo[i]));
                        }        
                        bufferedWriter.newLine();
                        bufferedWriter.write(String.format("Modelo solicitado: %s %s",compradores[i].marca,compradores[i].modelo));
                        bufferedWriter.newLine();
                }
            
                System.out.println("El recibo se ha creado exitosamente!\nDesea abrir el recibo? (s/n): ");
                String abrir = scanner3.nextLine();
                if (abrir.equals("S") || abrir.equals("s")) {
                    try {
                        File file = new File(file_name+".txt");
                        if (!Desktop.isDesktopSupported()) {
                            System.out.println("not supported");
                        }
                        Desktop desktop = Desktop.getDesktop();
                        if (file.exists())
                            desktop.open(file);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
    
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }    
            scanner3.close();
        }

    }
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        // int TRUe = 1, A = 0,
        int w = 0;
        //char x;
        Carro carroSeleccionado = new Func().new Carro();
        Carro[] carrosVendidos = new Carro[10];
        Carro[] compradores = new Carro[10];
        int numCompradores = 0;
        int numCarrosVendidos = 0;
        boolean True = true;
        int salir = 0;

        while (True) {

            System.out.println("\n\t\t Menú: ");
            System.out.println("\n  1.-Selecciona la venta que se realizará");
            System.out.println("  2.-Información de los compradores");
            System.out.println("  3.-Ventas realizadas");
            System.out.println("  4.-Consultar el inventario");
            System.out.println("  5.-Información de pago");
            System.out.println("  6.-Recibo personal");
            System.out.println("  7.-Salir\n");
            System.out.print("Selecciona una opción: ");

            String input = scanner.nextLine().trim();
            try {
                salir = Integer.parseInt(input);
                
            }catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Ingresa un número válido.");
                continue;
            }

            switch (salir) {
                case 1:
                    System.out.println("\n\t\tAdvertencia: el carro seleccionado se eliminará del inventario");
                    valoresAleatorios();
                    mostrarDatos();
                    buscar(carroSeleccionado, carrosVendidos, numCarrosVendidos, compradores, numCompradores);
                    mostrarDatosSeleccionados(carroSeleccionado);
                    datos(w, carroSeleccionado);
                    numCompradores++;
                    numCarrosVendidos++;
                    w++;
                    break;
                case 2:
                    mostrarDatosComprador(compradores, numCompradores);
                    break;
                case 3:
                    mostrarCarrosVendidos(carrosVendidos, numCarrosVendidos);
                    break;
                case 4:
                    mostrarDatos();
                    break;
                case 5:
                    mostrarInfoPago(numCompradores);
                    break;
                case 6:
                recibo_personal(compradores, numCompradores);
                    /*reciboPersonal(compradores, numCompradores);
                    scanner.nextLine();
                    break;/*/
                case 7:
                recibo(compradores, numCompradores);
                /*
                    System.out.print("Desea imprimir la información de los compradores? (S/N): ");
                    x = scanner.nextLine().charAt(0);
                    if (x == 'S' || x == 's') {
                        recibo(compradores, numCompradores);
                        A = TRUe;
                    } else if (x == 'N' || x == 'n') {
                        A = TRUe;
                    }*/
                    // A = TRUe;
                    True = false;
                    break;
                default:
                    System.out.println("\n\n\t\tERROR 404");
                    break;
            }
        }
        scanner.close();
    }
}