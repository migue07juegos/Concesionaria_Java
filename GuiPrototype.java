import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GuiPrototype extends JFrame {
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

    Border lineBorder = new LineBorder(Color.RED, 1);

    public static int[] array = new int[10];
    public static int[] mesesAdeudo = new int[10];
    public static int i, l, valores = 9, a = 0, inicio, nuevoValor, valorEliminar, posicion;
    public static char eliminar;
    public static String[] color = {"Blanco", "Negro", "Azul", "Rojo", "Verde", "Amarillo", "Plata", "Gris", "Rosa", "Naranja"};
    public static String[] marca = {"Audi", "BMW", "Mercedes-Benz", "Mclaren", "Lamborghini", "Toyota", "Chevrolet", "Nissan", "Mazda", "Renault"};
    public static String[] modelo = {"A1", "A2", "A3", "A4", "Aventador", "A6", "Mustang", "A8", "A9", "A10"};
    public static int[] monto = {1087000, 1000000, 900000, 800000, 600000, 500000, 400000, 300000, 200000, 100000};
    public static String[] nombreComprador = new String[10];
    public static int[] edad = new int[10];
    public static String[] metodoPago = new String[10];
    public static double[] montoEnganche = new double[10];
    public static double[] liquidacion = new double[10];
    public static double[] adeudo = new double[10];
    public static double[] pagoPorMes = new double[10];
    public JButton toggleButton;
    public static boolean menuExpandido = false;
    public JPanel menuPanel;


    
    public static void valoresAleatorios() {
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(9000) + 1000;
        }
    }

    public static String[] mostrarDatos() {
        String[] mostrarDatosStr = new String[10];
        valoresAleatorios();

        for (int i = 0; i <= valores; i++) {
            mostrarDatosStr[i] = (String.format("Carro no.%d:\nColor: %s\nMarca: %s\nModelo: %s\nPrecio: %d", array[i], color[i], marca[i], modelo[i], monto[i]));
        }

        return mostrarDatosStr;
    }

    public static String mostrarCarrosVendidos(Carro[] carrosVendidos, int numCarrosVendidos) {
        String CarrosVendidosHastaElMomento = "";

        for (int i = 0; i < numCarrosVendidos; i++) {
            CarrosVendidosHastaElMomento = (String.format("Carros vendidos hasta el momento:\n\n\t\tVenta no.%d\n\nComprador: %s\nCarro no.%d:\nColor: %s\nMarca: %s\nModelo: %s\nPrecio: %d\n", i + 1, nombreComprador[i], carrosVendidos[i].getNumeroControl(), carrosVendidos[i].getColor(), carrosVendidos[i].getMarca(), carrosVendidos[i].getModelo(), carrosVendidos[i].getMonto()));
        }

        return CarrosVendidosHastaElMomento;
    }


    public static StringBuilder mostrarDatosComprador(Carro[] compradores, int numCompradores) {
        StringBuilder datosCompradorF = new StringBuilder();
        for (int i = 0; i < numCompradores; i++) {
            datosCompradorF.append((String.format("\n\nComprador %d: %s", i + 1, nombreComprador[i])));
            datosCompradorF.append((String.format("\nEdad: %d", edad[i])));
            datosCompradorF.append((String.format("\nMétodo de pago: %s", metodoPago[i])));

            if (montoEnganche[i] == liquidacion[i]) {
                datosCompradorF.append((String.format("\nSe realizará el pago completo: %.2f", liquidacion[i])));
            } else {
                datosCompradorF.append((String.format("\nMonto de Enganche: %.2f", montoEnganche[i])));
                datosCompradorF.append((String.format("\nAdeudo: %.2f", adeudo[i])));
            }

            datosCompradorF.append((String.format("\nModelo solicitado: %s %s", compradores[i].getMarca(), compradores[i].getModelo())));
        }
        return datosCompradorF;
    }

    public static StringBuilder mostrarInfoPago(int numCompradores) {
        StringBuilder InfoDePagoF = new StringBuilder();
        for (int i = 0; i < numCompradores; i++) {
            InfoDePagoF.append((String.format("\n\nComprador %d: %s", i + 1, nombreComprador[i])));
            InfoDePagoF.append((String.format("\nMétodo de pago: %s", metodoPago[i])));

            if (montoEnganche[i] == liquidacion[i]) {
                InfoDePagoF.append((String.format("\nSe realizará el pago completo: %.2f", liquidacion[i])));
            } else {
                InfoDePagoF.append((String.format("\nMonto de Enganche: %.2f", montoEnganche[i])));
                InfoDePagoF.append((String.format("\nAdeudo: %.2f", adeudo[i])));
                InfoDePagoF.append((String.format("\nPlazo: %d años \nMensualidad: $%.2f", mesesAdeudo[i], pagoPorMes[i])));
            }
        }
        return InfoDePagoF;
    }

    public static boolean buscar(Carro carroSeleccionado, Carro[] carrosVendidos, int numCarrosVendidos, Carro[] compradores, int numCompradores, int numBtn) {
        do {
            int valorEliminar = 0;
            int truE = 0;

            valorEliminar = numBtn;

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
        return false;
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

        carrosVendidos[numCarrosVendidos] = new GuiPrototype().new Carro();
        carrosVendidos[numCarrosVendidos].setNumeroControl(carro.getNumeroControl());
        carrosVendidos[numCarrosVendidos].setColor(carro.getColor());
        carrosVendidos[numCarrosVendidos].setMarca(carro.getMarca());
        carrosVendidos[numCarrosVendidos].setModelo(carro.getModelo());
        carrosVendidos[numCarrosVendidos].setMonto(carro.getMonto());
        compradores[numCompradores] = new GuiPrototype().new Carro();
        compradores[numCompradores].setMarca(carro.getMarca());
        compradores[numCompradores].setModelo(carro.getModelo());
    }

    public static void datos(int i, Carro carro) {
        double a, b, c;

        nombreComprador[i] = JOptionPane.showInputDialog(null, "Ingresa el nombre del comprador: ");

        while (true) {
            edad[i] = 0;
            String str = JOptionPane.showInputDialog(null, "Ingresa la edad del comprador: ");

            try {
                edad[i] = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada no válida. Ingresa un número válido para la edad.");
                continue;
            }

            if (edad[i] < 18 || edad[i] > 100) {
                JOptionPane.showMessageDialog(null, "El cliente no puede ser menor de edad o tener más de 100 años");
            } else {
                break;
            }
        }

        metodoPago[i] = JOptionPane.showInputDialog(null, "Ingresa el método de pago: ");

        while (true) {
            b = 0;
            
            String str2 = JOptionPane.showInputDialog(null, String.format("Defina el porcentaje de enganche, IVA incluido con el monto del carro: $%d", carro.monto) + "\nSolo puede seleccionar porcentajes entre el 20 y 80 porciento, en caso de pago de contado ingrese 100: ");

            try {
                b = Double.parseDouble(str2);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada no válida. Ingresa un número válido para el porcentaje de enganche.");
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
                String str3 = JOptionPane.showInputDialog(null, "A cuántos años se pagará el adeudo? (max. 10 años): ");

                try {
                    mesesAdeudo[i] = Integer.parseInt(str3);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,"Entrada no válida. Ingresa un número válido para el plazo de pago.");
                    continue;
                }

                if (mesesAdeudo[i] < 10 && mesesAdeudo[i] > 0) {
                    break;
                }

            } while (true);

            c = mesesAdeudo[i] * 12;
            pagoPorMes[i] = adeudo[i] / c;
            JOptionPane.showMessageDialog(null, String.format("Mensualidad: %.2f",pagoPorMes[i])); 
        }

    }

    public static void mensaje(String str, String variable, boolean True) {

        while (True) {
            
            try {
                variable = JOptionPane.showInputDialog(str);
                True = false;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error");
                True = true;
            }
        }
        True = true;
    }

    public static void recibo_personal(Carro[] compradores, int num_compradores) throws IOException{
        
        LocalDateTime date = LocalDateTime.now();

        DateTimeFormatter fecha_formateada = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        DateTimeFormatter hora_formateada = DateTimeFormatter.ofPattern("HH:mm:ss");

        String fecha = date.format(fecha_formateada);
        String hora = date.format(hora_formateada);
        String file_name="", nombre="",abrir = "";
        String x = "";
        boolean True = true;

        mensaje("Ingrese el nombre de su recibo", file_name, True);
        
        mostrar_compradores(num_compradores, x);
        
        mensaje(x+"\nIngrese el nombre del comprador para imprimir su recibo", nombre, True);

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
            
                mensaje("El recibo se ha creado exitosamente!\nDesea abrir el recibo? (s/n): ", abrir, True);

                if (abrir.equals("S") || abrir.equals("s")) {
                    try {
                        File file = new File(file_name+".txt");
                        if (!Desktop.isDesktopSupported()) {

                            JOptionPane.showMessageDialog(null, "not supported");
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
        }
    }

    public static void mostrar_compradores(int num, String x) {

        for (i = 0; i < num; i++){
            x += String.format("\nComprador %d: %s\n",i+1,nombreComprador[i]);
        }
    }


    public static void recibo(Carro[] compradores, int num_compradores) {

        LocalDateTime date = LocalDateTime.now();

        DateTimeFormatter fecha_formateada = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
        DateTimeFormatter hora_formateada = DateTimeFormatter.ofPattern("HH:mm:ss");

        String fecha = date.format(fecha_formateada);
        String hora = date.format(hora_formateada);
        String file_name="",abrir = "";
        boolean True = true;

        mensaje("Ingrese el nombre de su recibo", file_name, True);
        
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
            
                mensaje("El recibo se ha creado exitosamente!\nDesea abrir el recibo? (s/n): ", abrir, True);
                if (abrir.equals("S") || abrir.equals("s")) {
                    try {
                        File file = new File(file_name+".txt");
                        if (!Desktop.isDesktopSupported()) {
                            JOptionPane.showMessageDialog(null, "No soportado!");
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
        }
    }

    public GuiPrototype() {}

    public GuiPrototype(boolean Ventana) {

        if (Ventana) {
            JPanel gridMenuContent = new JPanel();
            gridMenuContent.setLayout(new GridLayout(0, 2));

            UIManager.put("OptionPane.background", Color.BLACK);
            UIManager.put("Panel.background", Color.BLACK);
            UIManager.put("OptionPane.messageForeground", new Color(155,155,155));

            int espacioInterno = 3;
            Border buttonBorder = new CompoundBorder(new LineBorder(new Color(0, 85, 119)), new EmptyBorder(espacioInterno, espacioInterno, espacioInterno, espacioInterno));
            UIManager.put("Button.border", new BorderUIResource(buttonBorder));
            UIManager.put("Button.background", new Color(0,0,0));
            UIManager.put("Button.foreground", new Color(155,155,155));
            UIManager.put("Button.focus", false);

            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            SwingUtilities.invokeLater(() -> {
                relojGui(this);
            });

            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

            ImageIcon images[] = {new ImageIcon("Audi.png"), new ImageIcon("bmw.png"), new ImageIcon("Mercedes.png"), new ImageIcon("Mclaren.png"), new ImageIcon("Lamborghini.png"), new ImageIcon("Toyota.png"), new ImageIcon("Chevrolet.png"), new ImageIcon("nissan.png"), new ImageIcon("Mazda.png"), new ImageIcon("Renault.png")};
            String[] moDaStr = mostrarDatos();

            for (int h = 0; h < 10; h++) {
                // Crear un JPanel para agrupar el JLabel y el JButton
                JPanel panel = new JPanel(new BorderLayout());
                panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 0, 50)); // Espaciado vertical de 50
                // panel.setBorder(BorderFactory.createLineBorder(new Color(54, 57, 63))); 

                JLabel label = new JLabel("<html><font color='#9B9B9B'>" + moDaStr[h].replace("\n", "<br>") + "</font></html>");
                label.setPreferredSize(new Dimension(300, 300));
                label.setFont(new Font("Arial", Font.PLAIN, 25)); 
                panel.add(label, BorderLayout.CENTER);

                JButton button = new JButton(images[h]);

                button.setPreferredSize(new Dimension(300, 300));
                button.addActionListener(new BotonListener(button, label, h));
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                panel.add(button, BorderLayout.EAST);

                contentPanel.add(panel);
            }

            JScrollPane scrollPane = new JScrollPane(contentPanel);
            scrollPane.getViewport().setBackground(Color.BLACK); 
            scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0, 85, 119)));

            scrollPane.getVerticalScrollBar().setBackground(Color.BLACK);

            scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
                @Override
                protected void configureScrollBarColors() {
                    this.thumbColor = new Color(0, 85, 119);
                }

                @Override
                protected JButton createDecreaseButton(int orientation) {
                    JButton button = super.createDecreaseButton(orientation);
                    button.setBackground(Color.BLACK);
                    button.setForeground(new Color(0, 85, 119));
                    return button;
                }

                @Override
                protected JButton createIncreaseButton(int orientation) {
                    JButton button = super.createIncreaseButton(orientation);
                    button.setBackground(Color.BLACK);
                    button.setForeground(new Color(0, 85, 119));
                    return button;
                }
            });

            scrollPane.getVerticalScrollBar().setUnitIncrement(15);

            add(scrollPane, BorderLayout.CENTER);
        }
    }

    public static void relojGui(JFrame frame) {

        JPanel panel = new JPanel();
        JButton button = new JButton("=");
        JPanel menuPanel = new JPanel();
        
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(Color.black); // Color de fondo del menú
        menuPanel.setPreferredSize(new Dimension(50, menuPanel.getHeight())); // Ancho del menú retraído
        menuPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 85, 119)));

        frame.setLayout(new BorderLayout());
        frame.add(menuPanel, BorderLayout.WEST); // Agregar el menú en la parte izquierda
        frame.add(panel, BorderLayout.NORTH);

        button.setSize(50, 50);
        button.setOpaque(isDefaultLookAndFeelDecorated());
        button.setBackground(Color.gray);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toggleMenu(menuPanel);
            }
        });

        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel();
        label.setForeground(new Color(155,155,155));
        label.setFont(new Font("Arial", Font.PLAIN, 50));
        panel.add(label, BorderLayout.EAST);
        panel.add(button, BorderLayout.WEST);
        
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalTime currentTime = LocalTime.now();
                String time = String.format("%02d:%02d:%02d ",
                        currentTime.getHour(),
                        currentTime.getMinute(),
                        currentTime.getSecond());
                label.setText(time);
            }
        });

        timer.start();
    }


    public static void agregarElementoMenu(JPanel menuPanel, boolean menuEx, String funcion) {
        
        JButton button = new JButton(funcion);
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar los iconos verticalmente
        button.setText(funcion);
        button.getVerifyInputWhenFocusTarget();

        if (menuEx) {

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout());
            buttonPanel.add(button);
            // buttonPanel.add(label, BorderLayout.EAST);
            menuPanel.add(buttonPanel);
            // button.addMouseListener(new MouseAdapter() {
            //     @Override
            //     public void mouseClicked(MouseEvent e) {
            //         toggleMenu(menuPanel);
            //     }
            // });
    
        } else {
            // Si el menú está retraído, agregar botones sin etiquetas
            menuPanel.add(button);
        }
        
    }


    public static void toggleMenu(JPanel panel) {

        if (menuExpandido) {
            ocultarMenu(panel);
        } else {
            menuExpandido = true;
            mostrarMenu(panel);
        }
    }


    public static void mostrarMenu(JPanel panel) {
        // Ajustar el ancho del menú al máximo
        panel.setPreferredSize(new Dimension(200, panel.getHeight()));
        panel.removeAll(); // Limpiar los componentes existentes

        
        agregarElementoMenu(panel, true, "Informacion compradores");
        agregarElementoMenu(panel, true, "Ventas realizadas");
        agregarElementoMenu(panel, true, "Consultar inventario");
        agregarElementoMenu(panel, true, "Informacion de pago");
        agregarElementoMenu(panel, true, "Recibo personal");
        agregarElementoMenu(panel, true, "Salir");

        panel.revalidate();
        panel.repaint();
    }

    public static void ocultarMenu(JPanel panel) {
        // Ajustar el ancho del menú al mínimo
        panel.setPreferredSize(new Dimension(50, panel.getHeight()));
        menuExpandido = false;
        panel.removeAll(); // Limpiar los componentes existentes
        panel.revalidate();
        panel.repaint();
    }


    static class BotonListener implements ActionListener {
        private JButton button;
        private JLabel associatedLabel;
        private int ho;

        public BotonListener(JButton button, JLabel associatedLabel, int ho) {
            this.button = button;
            this.associatedLabel = associatedLabel;
            this.ho = ho;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean confirm = true;
            int w = 0;
            Carro carroSeleccionado = new GuiPrototype().new Carro();
            Carro[] carrosVendidos = new Carro[10];
            Carro[] compradores = new Carro[10];
            int numCompradores = 0;
            int numCarrosVendidos = 0;
            String[] opciones = {"Realizar venta", "Cancelar"};

            int seleccion = JOptionPane.showOptionDialog(null, "Selecciona una opción", "Venta del auto seleccionado", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            switch (seleccion) {
                case 0:
                    confirm = buscar(carroSeleccionado, carrosVendidos, numCarrosVendidos, compradores, numCompradores, array[ho]);
                    datos(w, carroSeleccionado);
                    numCompradores++;
                    numCarrosVendidos++;    
                    w++;
                break;
            }
            if (seleccion == JOptionPane.CLOSED_OPTION) {
                JOptionPane.showMessageDialog(null, "Venta cancelada", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

            button.setVisible(confirm);
            associatedLabel.setVisible(confirm);
        }
    }

    public static void main(String[] args){
        GuiPrototype frame = new GuiPrototype(true);
        int width = 500;
        int height = 500;
        
        int screenWidth = 1920;
        int screenHeight = 1080;
        int xPos = (screenWidth -  width) / 2;
        int yPos = (screenHeight - height) / 2;

        frame.setBounds(xPos, yPos, width, height);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}
