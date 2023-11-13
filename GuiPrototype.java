import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;

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
    public int getNumeroControl() { return numeroControl; }
    public String getColor() { return color; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getMonto() { return monto; }
    public void setNumeroControl(int numeroControl) {
      this.numeroControl = numeroControl;
    }
    public void setColor(String color) { this.color = color; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setMonto(int monto) { this.monto = monto; }
  }

  public static int[] array = new int[10];
  public static int[] mesesAdeudo = new int[10];
  public static int i, l, valores = 9, a = 0, inicio, nuevoValor, valorEliminar,
                          posicion;
  public static char eliminar;
  public static String[] color = {"Blanco", "Azul",  "Blanco",   "Naranja",
                                  "Gris",   "Plata", "Amarillo", "Naranja",
                                  "Plata",  "Cobre"};
  public static String[] marca = {
      "Audi",   "BMW",       "Mercedes-Benz", "McLaren", "Lamborghini",
      "Toyota", "Chevrolet", "Nissan",        "Mazda",   "Renault"};
  public static String[] modelo = {
      "A1",         "325i",   "Clase A", "650S",    "Aventador",
      "Highlander", "Camaro", "Altima",  "Mazda 3", "Kwid"};
  public static int[] monto = {599900, 975000,  831900, 2123472, 25243345,
                               865900, 1342000, 737900, 388900,  230100};
  public static String[] nombreComprador = new String[10];
  public static int[] edad = new int[10];
  public static String[] metodoPago = new String[10];
  public static double[] montoEnganche = new double[10];
  public static double[] liquidacion = new double[10];
  public static double[] adeudo = new double[10];
  public static double[] pagoPorMes = new double[10];
  public static Carro carroSeleccionado = new GuiPrototype().new Carro();
  public static Carro[] carrosVendidos = new Carro[10];
  public static Carro[] compradores = new Carro[10];
  public static int numCompradores = 0;
  public static int numCarrosVendidos = 0;
  public static int w = 0;
  public JButton toggleButton;
  public static boolean menuExpandido = false;
  public JPanel menuPanel;
  public static JTextField nombreCompradorTXT = new JTextField();
  public static JTextField edadTXT = new JTextField();
  public static JTextField engancheTXT = new JTextField();
  public static JTextField plazoTXT = new JTextField();
  public static JTextField archivo_txt = new JTextField();
  public static JTextField nombre_txt = new JTextField();
  public static JTextField abrir_txt = new JTextField();
  public static boolean confirm2 = true;

  public GuiPrototype() {}

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
      mostrarDatosStr[i] = (String.format(
          "\t\tCarro no.%d:\n\t\tColor: %s\n\t\tMarca: %s\n\t\tModelo: %s\n\t\tPrecio: %d",
          array[i], color[i], marca[i], modelo[i], monto[i]));
    }

    return mostrarDatosStr;
  }

  public static String[] mostrarCarrosVendidos(Carro[] carrosVendidos,
                                               int numCarrosVendidos) {
    String[] CarrosVendidosHastaElMomento = new String[10];

    for (int i = 0; i < numCarrosVendidos; i++) {
      CarrosVendidosHastaElMomento[i] = (String.format(
          "Venta no.%d\n\nComprador: %s\nCarro no.%d:\nColor: %s\nMarca: %s\nModelo: %s\nPrecio: %d\n",
          i + 1, nombreComprador[i], carrosVendidos[i].getNumeroControl(),
          carrosVendidos[i].getColor(), carrosVendidos[i].getMarca(),
          carrosVendidos[i].getModelo(), carrosVendidos[i].getMonto()));
    }

    return CarrosVendidosHastaElMomento;
  }

  public static String[] mostrarDatosComprador(Carro[] compradores,
                                               int numCompradores) {
    String[] datosCompradorF = new String[10];
    for (int i = 0; i < numCompradores; i++) {

      if (montoEnganche[i] == liquidacion[i]) {
        datosCompradorF[i] =
            ((String.format("Comprador %d: %s", i + 1, nombreComprador[i])) +
             (String.format("\nEdad: %d", edad[i])) +
             (String.format("\nMétodo de pago: %s", metodoPago[i])) +
             (String.format("\nSe realizará el pago completo: %.2f",
                            liquidacion[i])) +
             (String.format("\nModelo solicitado: %s %s\n\n",
                            compradores[i].getMarca(),
                            compradores[i].getModelo())));
      } else {
        datosCompradorF[i] =
            ((String.format("Comprador %d: %s", i + 1, nombreComprador[i])) +
             (String.format("\nEdad: %d", edad[i])) +
             (String.format("\nMétodo de pago: %s", metodoPago[i])) +
             (String.format("\nMonto de Enganche: %.2f", montoEnganche[i])) +
             (String.format("\nAdeudo: %.2f", adeudo[i])) +
             (String.format("\nModelo solicitado: %s %s\n\n",
                            compradores[i].getMarca(),
                            compradores[i].getModelo())));
      }
    }
    return datosCompradorF;
  }

  public static String[] mostrarInfoPago(int numCompradores) {
    String[] InfoDePagoF = new String[10];
    for (int i = 0; i < numCompradores; i++) {

      if (montoEnganche[i] == liquidacion[i]) {
        InfoDePagoF[i] =
            ((String.format("Comprador %d: %s", i + 1, nombreComprador[i]) +
              String.format("\nMétodo de pago: %s", metodoPago[i]) +
              String.format("\nSe realizará el pago completo: %.2f\n\n",
                            liquidacion[i])));
      } else {
        InfoDePagoF[i] =
            ((String.format("Comprador %d: %s", i + 1, nombreComprador[i]) +
              String.format("\nMétodo de pago: %s", metodoPago[i]) +
              String.format("\nMonto de Enganche: %.2f", montoEnganche[i]) +
              String.format("\nAdeudo: %.2f", adeudo[i]) +
              String.format("\nPlazo: %d años \nMensualidad: $%.2f\n\n",
                            mesesAdeudo[i], pagoPorMes[i])));
      }
    }
    return InfoDePagoF;
  }

  public static void buscar(Carro carroSeleccionado, Carro[] carrosVendidos,
                            int numCarrosVendidos, Carro[] compradores,
                            int numCompradores, int numBtn) {
    do {
      int valorEliminar = 0;
      int truE = 0;

      valorEliminar = numBtn;

      for (int i = 0; i <= valores; i++) {
        if (array[i] == valorEliminar) {
          truE = 1;
          a = 1;
          inicio = i;
          registroAutos(carroSeleccionado, carrosVendidos, numCarrosVendidos,
                        compradores, numCompradores);
          break;
        }
      }
      if (truE == 1) {
        break;
      }
    } while (true);
  }

  public static void registroAutos(Carro carro, Carro[] carrosVendidos,
                                   int numCarrosVendidos, Carro[] compradores,
                                   int numCompradores) {
    carro.setNumeroControl(array[inicio]);
    carro.setColor(color[inicio]);
    carro.setMarca(marca[inicio]);
    carro.setModelo(modelo[inicio]);
    carro.setMonto(monto[inicio]);

    carrosVendidos[numCarrosVendidos] = new GuiPrototype().new Carro();
    carrosVendidos[numCarrosVendidos].setNumeroControl(
        carro.getNumeroControl());
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

    nombreComprador[i] = nombreCompradorTXT.getText();

    String str = edadTXT.getText();
    edad[i] = Integer.parseInt(str);

    metodoPago[i] =
        JOptionPane.showInputDialog(null, "Ingresa el método de pago: ");

    String str2 = engancheTXT.getText();
    b = Double.parseDouble(str2);

    a = b / 100;
    montoEnganche[i] = a * carro.monto;
    adeudo[i] = carro.monto - montoEnganche[i];
    liquidacion[i] = carro.monto;

    if (b > 19 && b < 81) {
      String str3 = plazoTXT.getText();
      try {
        mesesAdeudo[i] = Integer.parseInt(str3);
      } catch (Exception e) {
        System.out.println("Error");
      }

      c = mesesAdeudo[i] * 12;
      pagoPorMes[i] = adeudo[i] / c;

      JOptionPane.showMessageDialog(
          null, String.format("Mensualidad: %.2f", pagoPorMes[i]));
    }
  }

  public static void recibo_personal(Carro[] compradores, int num_compradores,
                                     JPanel informacion, JCheckBox check)
      throws IOException {

    try {

      LocalDateTime date = LocalDateTime.now();
      DateTimeFormatter fecha_formateada =
          DateTimeFormatter.ofPattern("dd/MMM/yyyy");
      DateTimeFormatter hora_formateada =
          DateTimeFormatter.ofPattern("HH:mm:ss");

      String fecha = date.format(fecha_formateada);
      String hora = date.format(hora_formateada);

      for (i = 0; i < num_compradores; i++) {

        if (nombre_txt.getText().equals(nombreComprador[i])) {
          FileWriter fileWriter =
              new FileWriter(new File(archivo_txt.getText() + ".txt"), true);

          BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

          bufferedWriter.write("Fecha de impresion: " + fecha +
                               "             " + hora);
          bufferedWriter.newLine();

          bufferedWriter.write(String.format(
              "\n\nComprador %d: %s", i + 1,
              (nombreComprador[i] == null ? "" : nombreComprador[i])));
          bufferedWriter.newLine();
          bufferedWriter.write(String.format("Edad: %d", edad[i]));
          bufferedWriter.newLine();
          bufferedWriter.write(
              String.format("Método de pago: %s",
                            (metodoPago[i] == null ? "" : metodoPago[i])));
          bufferedWriter.newLine();

          if (montoEnganche[i] == liquidacion[i]) {

            bufferedWriter.write(String.format(
                "Se realizará el pago completo: %.2f", liquidacion[i]));
          } else {
            bufferedWriter.write(
                String.format("Monto de Enganche: %.2f", montoEnganche[i]));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Adeudo: %.2f", adeudo[i]));
            bufferedWriter.newLine();
            bufferedWriter.write(
                String.format("Plazo: %d años\nMensualidad %.2f",
                              mesesAdeudo[i], pagoPorMes[i]));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format(
                "Si tarda mas de 3 meses en pagar su mensualidad se le embargará"));
          }

          bufferedWriter.newLine();
          bufferedWriter.write(String.format("Modelo solicitado: %s %s",
                                             compradores[i].marca,
                                             compradores[i].modelo));
          bufferedWriter.close();
        }
      }

      if (check.isSelected() == true) {
        try {
          File file = new File(archivo_txt.getText() + ".txt");
          if (!Desktop.isDesktopSupported()) {

            JOptionPane.showMessageDialog(null, "not supported");
          }
          Desktop desktop = Desktop.getDesktop();
          if (file.exists())
            desktop.open(file);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void button(JPanel informacion, boolean funcion) {

    JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 0, 50));

    JButton button = new JButton("Realizar recibo");
    button.setAlignmentX(Component.CENTER_ALIGNMENT);
    button.setFocusPainted(false);
    button.setPreferredSize(new Dimension(300, 100));
    button.setVerticalTextPosition(SwingConstants.BOTTOM);
    button.setHorizontalTextPosition(SwingConstants.RIGHT);
    button.getVerifyInputWhenFocusTarget();
    button.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {

        button.setBackground(Color.DARK_GRAY);
      }

      @Override
      public void mouseExited(MouseEvent e) {

        button.setBackground(null);
      }
    });

    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {

        pedir_recibo(informacion, funcion);
      }
    });

    panel.add(button, BorderLayout.EAST);
    informacion.add(panel);
  }

  public static void pedir_recibo(JPanel informacion, boolean pregunta) {

    JFrame frame = new JFrame();
    frame.setLayout(new GridLayout(1, 2));

    JLabel nombre_recibo = new JLabel("Nombre del Recibo:");
    JLabel nombre_comprador = new JLabel("Nombre comprador:");
    JCheckBox check = new JCheckBox("Abrir archivo");

    Color labelColor = new Color(155, 155, 155);
    Font labelFont = new Font("Arial", Font.PLAIN, 16);

    nombre_recibo.setForeground(labelColor);
    nombre_recibo.setFont(labelFont);

    if (pregunta) {
      nombre_comprador.setForeground(labelColor);
      nombre_comprador.setFont(labelFont);
    }

    check.setForeground(labelColor);
    check.setFont(labelFont);
    check.setFocusPainted(false);
    check.setSize(new Dimension(1000, 1000));

    JPanel labelsPanel = new JPanel();
    labelsPanel.setLayout(new GridLayout(4, 1));
    labelsPanel.setBackground(Color.black);
    labelsPanel.add(nombre_recibo);
    if (pregunta) {
      labelsPanel.add(nombre_comprador);
    }
    labelsPanel.add(check);

    JPanel componentesPanel = new JPanel();
    componentesPanel.setLayout(new GridLayout(4, 1));
    componentesPanel.add(archivo_txt);
    if (pregunta) {
      componentesPanel.add(nombre_txt);
    }
    componentesPanel.add(check);

    archivo_txt.setForeground(labelColor);
    archivo_txt.setBackground(Color.black);

    if (pregunta) {
      nombre_txt.setForeground(labelColor);
      nombre_txt.setBackground(Color.black);
    }

    check.setForeground(labelColor);
    check.setBackground(Color.black);

    frame.add(labelsPanel);
    frame.add(componentesPanel);
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

    JButton aceptarButton = new JButton("Aceptar");
    aceptarButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {

        aceptarButton.setBackground(Color.DARK_GRAY);
      }

      @Override
      public void mouseExited(MouseEvent e) {

        aceptarButton.setBackground(null);
      }
    });

    aceptarButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        try {
          if (pregunta) {
            recibo_personal(compradores, numCompradores, informacion, check);
          } else if (!pregunta) {
            recibo(compradores, numCompradores, check);
          }
        } catch (IOException e1) {
          e1.printStackTrace();
        }

        archivo_txt.setText("");
        nombre_txt.setText("");
        abrir_txt.setText("");
        frame.dispose();
        return;
      }
    });

    frame.add(aceptarButton);

    frame.setVisible(true);
    frame.setResizable(false);
    frame.setSize(new Dimension(500, 300));
    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    return;
  }

  public static StringBuilder mostrar_compradores(int num) {

    StringBuilder compradores = new StringBuilder();

    for (i = 0; i < num; i++) {
      if (nombreComprador[i] != null) {
        compradores.append(
            String.format("\nComprador %d: %s\n", i + 1, nombreComprador[i]));
      }
    }
    return compradores;
  }

  public static void recibo(Carro[] compradores, int num_compradores,
                            JCheckBox check) {

    try {

      LocalDateTime date = LocalDateTime.now();
      DateTimeFormatter fecha_formateada =
          DateTimeFormatter.ofPattern("dd/MMM/yyyy");
      DateTimeFormatter hora_formateada =
          DateTimeFormatter.ofPattern("HH:mm:ss");

      String fecha = date.format(fecha_formateada);
      String hora = date.format(hora_formateada);

      for (i = 0; i < num_compradores; i++) {

        FileWriter fileWriter =
            new FileWriter(new File(archivo_txt.getText() + ".txt"), true);

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        if (i == 0) {
          bufferedWriter.write("Fecha de impresion: " + fecha +
                               "             " + hora);
          bufferedWriter.newLine();
        }
        bufferedWriter.write(String.format(
            "\n\nComprador %d: %s", i + 1,
            (nombreComprador[i] == null ? "" : nombreComprador[i])));
        bufferedWriter.newLine();
        bufferedWriter.write(String.format("Edad: %d", edad[i]));
        bufferedWriter.newLine();
        bufferedWriter.write(
            String.format("Método de pago: %s",
                          (metodoPago[i] == null ? "" : metodoPago[i])));
        bufferedWriter.newLine();

        if (montoEnganche[i] == liquidacion[i]) {

          bufferedWriter.write(String.format(
              "Se realizará el pago completo: %.2f", liquidacion[i]));
        } else {
          bufferedWriter.write(
              String.format("Monto de Enganche: %.2f", montoEnganche[i]));
          bufferedWriter.newLine();
          bufferedWriter.write(String.format("Adeudo: %.2f", adeudo[i]));
          bufferedWriter.newLine();
          bufferedWriter.write(String.format("Plazo: %d años\nMensualidad %.2f",
                                             mesesAdeudo[i], pagoPorMes[i]));
          bufferedWriter.newLine();
          bufferedWriter.write(String.format(
              "Si tarda mas de 3 meses en pagar su mensualidad se le embargará"));
        }
        bufferedWriter.newLine();
        bufferedWriter.write(String.format("Modelo solicitado: %s %s",
                                           compradores[i].marca,
                                           compradores[i].modelo));
        bufferedWriter.close();
      }

      if (check.isSelected() == true) {
        try {
          File file = new File(archivo_txt.getText() + ".txt");
          if (!Desktop.isDesktopSupported()) {

            JOptionPane.showMessageDialog(null, "not supported");
          }
          Desktop desktop = Desktop.getDesktop();
          if (file.exists())
            desktop.open(file);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public GuiPrototype(boolean Ventana) {

    if (Ventana) {

      UIManager.put("OptionPane.background", Color.BLACK);
      UIManager.put("Panel.background", Color.BLACK);
      UIManager.put("OptionPane.messageForeground", new Color(155, 155, 155));

      int espacioInterno = 3;
      Border buttonBorder =
          new CompoundBorder(new LineBorder(new Color(0, 85, 119)),
                             new EmptyBorder(espacioInterno, espacioInterno,
                                             espacioInterno, espacioInterno));
      UIManager.put("Button.border", new BorderUIResource(buttonBorder));
      UIManager.put("Button.background", new Color(0, 0, 0));
      UIManager.put("Button.foreground", new Color(155, 155, 155));
      UIManager.put("Button.focus", false);

      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLayout(new BorderLayout());

      JPanel inicioPanel = new JPanel();
      JPanel infoPanel = new JPanel();
      JPanel panelPrincipal = new JPanel();

      panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

      if (infoPanel.getComponentCount() == 0) {
        Dimension emptyPanelSize = new Dimension(0, 0);
        infoPanel.setPreferredSize(emptyPanelSize);
        infoPanel.setMinimumSize(emptyPanelSize);
      }

      panelPrincipal.add(inicioPanel);
      panelPrincipal.add(infoPanel);

      JScrollPane scrollPane = new JScrollPane(panelPrincipal);
      scrollPane.getViewport().setBackground(Color.BLACK);
      scrollPane.setBorder(null);
      scrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
      scrollPane.setVerticalScrollBarPolicy(
          JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

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

      scrollPane.getVerticalScrollBar().setUnitIncrement(40);

      SwingUtilities.invokeLater(
          () -> { relojGui(this, inicioPanel, infoPanel); });
      Realizar_venta(inicioPanel, scrollPane);

      add(scrollPane, BorderLayout.CENTER);
    }
  }

  public static void Realizar_venta(JPanel contentPanel,
                                    JScrollPane scrollPane) {

    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

    ImageIcon images[] = {new ImageIcon("images/Audi.png"),
                          new ImageIcon("images/bmw.png"),
                          new ImageIcon("images/Mercedes.png"),
                          new ImageIcon("images/Mclaren.png"),
                          new ImageIcon("images/Lamborghini.png"),
                          new ImageIcon("images/Toyota.png"),
                          new ImageIcon("images/Chevrolet.png"),
                          new ImageIcon("images/nissan.png"),
                          new ImageIcon("images/Mazda.png"),
                          new ImageIcon("images/Renault.png")};
    String[] moDaStr = mostrarDatos();

    for (int h = 0; h < 10; h++) {

      JPanel panel = new JPanel(new BorderLayout());
      panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 0, 50));
      panel.setBorder(
          BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(0, 85, 119)));

      JLabel label =
          new JLabel("<html><font color='#9B9B9B'> " +
                     moDaStr[h].replace("\n", "<br>").replace("\t", "&nbsp;") +
                     "</font></html>");
      label.setPreferredSize(new Dimension(300, 300));
      label.setFont(new Font("Arial", Font.PLAIN, 25));
      panel.add(label, BorderLayout.CENTER);

      JButton button = new JButton(images[h]);

      button.setPreferredSize(new Dimension(300, 300));
      button.addActionListener(new BotonListener(button, label, h, panel));
      button.setVerticalTextPosition(SwingConstants.BOTTOM);
      button.setHorizontalTextPosition(SwingConstants.CENTER);
      button.setBorder(
          BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(0, 85, 119)));
      button.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {

          button.setBackground(Color.DARK_GRAY);
        }

        @Override
        public void mouseExited(MouseEvent e) {

          button.setBackground(null);
        }
      });

      panel.add(button, BorderLayout.EAST);

      contentPanel.add(panel);
    }
  }

  public static void
  informacion_compradores(boolean ventana, JPanel informacion, int indexButton)
      throws IOException {
    informacion.removeAll();
    informacion.revalidate();
    informacion.repaint();

    String resStr = "";
    String[] resStrArr = new String[10];

    switch (indexButton) {
    case 2:
      resStrArr = mostrarDatosComprador(compradores, numCompradores);
      labels(informacion, resStr, false, resStrArr);
      break;
    case 3:
      resStrArr = mostrarCarrosVendidos(carrosVendidos, numCarrosVendidos);
      labels(informacion, resStr, false, resStrArr);
      break;
    case 4:
      resStrArr = mostrarInfoPago(numCompradores);
      labels(informacion, resStr, false, resStrArr);
      break;
    case 5:
      resStr = mostrar_compradores(numCompradores).toString();
      labels(informacion, resStr, true, resStrArr);
      button(informacion, true);
      break;
    case 6:
      labels(informacion, resStr, true, resStrArr);
      button(informacion, false);
      break;
    }
  }

  public static void labels(JPanel informacion, String resStr, boolean recibo,
                            String[] resStrArr) {
    JLabel label = new JLabel("<html><font color='#9B9B9B'>" +
                              resStr.replace("\n", "<br>") + "</font></html>");
    label.setFont(new Font("Arial", Font.PLAIN, 25));

    if (!recibo) {
      informacion.setLayout(new BoxLayout(informacion, BoxLayout.X_AXIS));

      int numColumnas = 3;

      ArrayList<JPanel> columnas = new ArrayList<>();
      for (int i = 0; i < numColumnas; i++) {
        JPanel columna = new JPanel();
        columna.setLayout(new BoxLayout(columna, BoxLayout.Y_AXIS));
        columnas.add(columna);
      }

      for (int h = 0; h < numCompradores; h++) {
        JPanel columnaActual = columnas.get(h % numColumnas);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 0, 50));

        JLabel label2 =
            new JLabel("<html><font color='#9B9B9B'> " +
                       resStrArr[h].replace("\n", "<br>") + "</font></html>");
        label2.setPreferredSize(new Dimension(250, 250));
        label2.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(label2, BorderLayout.CENTER);

        columnaActual.add(panel);
      }

      for (JPanel columna : columnas) {
        informacion.add(columna);
      }
    }

    else {
      informacion.setLayout(new FlowLayout());
      informacion.add(label);
    }
  }

  public static void relojGui(JFrame frame, JPanel panelInicio,
                              JPanel infoPanel) {

    JPanel panel = new JPanel();
    JButton button = new JButton(" ≡ ");
    JPanel menuPanel = new JPanel();

    menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
    menuPanel.setBorder(null);
    menuPanel.setBackground(Color.black);
    menuPanel.setPreferredSize(new Dimension(60, menuPanel.getHeight()));
    menuPanel.setBorder(
        BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(0, 85, 119)));

    frame.setLayout(new BorderLayout());
    frame.add(menuPanel, BorderLayout.WEST);
    frame.add(panel, BorderLayout.NORTH);
    Dimension buttonSize = new Dimension(60, 50);
    button.setPreferredSize(buttonSize);
    button.setMaximumSize(buttonSize);
    button.setFocusPainted(false);
    button.setFont(new Font("Arial", Font.PLAIN, 30));
    button.setOpaque(isDefaultLookAndFeelDecorated());
    button.setBackground(Color.gray);
    button.setBorder(
        BorderFactory.createMatteBorder(0, 1, 0, 1, new Color(0, 85, 119)));
    button.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        toggleMenu(menuPanel, panelInicio, infoPanel);
      }
    });

    panel.setLayout(new BorderLayout());

    JLabel label = new JLabel();
    label.setForeground(new Color(155, 155, 155));
    label.setFont(new Font("Arial", Font.PLAIN, 50));
    panel.setBorder(
        BorderFactory.createMatteBorder(1, 0, 1, 1, new Color(0, 85, 119)));
    panel.add(label, BorderLayout.EAST);
    panel.add(button, BorderLayout.WEST);

    LocalTime currentTime = LocalTime.now();
    label.setText(String.format("%02d:%02d:%02d ", currentTime.getHour(),
                                currentTime.getMinute(),
                                currentTime.getSecond()));

    Timer timer = new Timer(1000, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        LocalTime currentTime = LocalTime.now();
        String time =
            String.format("%02d:%02d:%02d ", currentTime.getHour(),
                          currentTime.getMinute(), currentTime.getSecond());
        label.setText(time);
      }
    });

    timer.start();
  }

  public static void agregarElementoMenu(JPanel menuPanel, String funcion,
                                         int indexButton, JPanel panelInicio,
                                         JPanel infoPanel) {

    JButton button = new JButton(funcion);
    button.setAlignmentX(Component.CENTER_ALIGNMENT);
    button.setText(funcion);
    button.addActionListener(
        new MenuBotonListener(indexButton, panelInicio, infoPanel));
    button.setFocusPainted(false);
    button.getVerifyInputWhenFocusTarget();
    button.setBorder(
        BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(0, 85, 119)));
    button.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {

        button.setBackground(Color.DARK_GRAY);
      }

      @Override
      public void mouseExited(MouseEvent e) {

        button.setBackground(null);
      }
    });

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout());
    buttonPanel.add(button);
    menuPanel.add(buttonPanel);
  }

  public static void toggleMenu(JPanel panel, JPanel panelInicio,
                                JPanel infoPanel) {

    if (menuExpandido) {
      ocultarMenu(panel);
    } else {
      menuExpandido = true;
      mostrarMenu(panel, panelInicio, infoPanel);
    }
  }

  public static void mostrarMenu(JPanel panel, JPanel panelInicio,
                                 JPanel infoPanel) {

    panel.setPreferredSize(new Dimension(200, panel.getHeight()));
    panel.removeAll();

    agregarElementoMenu(panel, "Realizar venta", 1, panelInicio, infoPanel);
    agregarElementoMenu(panel, "Informacion compradores", 2, panelInicio,
                        infoPanel);
    agregarElementoMenu(panel, "Ventas realizadas", 3, panelInicio, infoPanel);
    agregarElementoMenu(panel, "Informacion de pago", 4, panelInicio,
                        infoPanel);
    agregarElementoMenu(panel, "Recibo personal", 5, panelInicio, infoPanel);
    agregarElementoMenu(panel, "Recibo General", 6, panelInicio, infoPanel);

    panel.setBorder(
        BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(0, 0, 0)));

    panel.revalidate();
    panel.repaint();
  }

  public static void ocultarMenu(JPanel panel) {

    panel.setBorder(
        BorderFactory.createMatteBorder(0, 1, 0, 1, new Color(0, 85, 119)));

    panel.setPreferredSize(new Dimension(60, panel.getHeight()));
    menuExpandido = false;
    panel.removeAll();
    panel.revalidate();
    panel.repaint();
  }

  static class MenuBotonListener implements ActionListener {
    public int indexButton;
    public JPanel panelInicio;
    public JPanel infoPanel;

    public MenuBotonListener(int indexButton, JPanel panelInicio,
                             JPanel infoPanel) {
      this.indexButton = indexButton;
      this.panelInicio = panelInicio;
      this.infoPanel = infoPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (indexButton == 1) {
        panelInicio.setVisible(true);
        infoPanel.setVisible(false);
      } else {
        panelInicio.setVisible(false);
        try {
          informacion_compradores(menuExpandido, infoPanel, indexButton);
        } catch (IOException e1) {
          e1.printStackTrace();
        }
        infoPanel.setVisible(true);
      }
    }
  }

  public static void pedirDatos(int ho) {
    JFrame frame = new JFrame();
    frame.setLayout(new GridLayout(1, 2));

    JLabel nombreLabel = new JLabel("Nombre del Comprador:");
    JLabel edadLabel = new JLabel("Edad:");
    JLabel engancheLabel = new JLabel("Enganche: %");
    JLabel plazoLabel = new JLabel("Plazo:");

    Color labelColor = new Color(155, 155, 155);
    Font labelFont = new Font("Arial", Font.PLAIN, 16);

    nombreLabel.setForeground(labelColor);
    nombreLabel.setFont(labelFont);
    edadLabel.setForeground(labelColor);
    edadLabel.setFont(labelFont);
    engancheLabel.setForeground(labelColor);
    engancheLabel.setFont(labelFont);
    plazoLabel.setForeground(labelColor);
    plazoLabel.setFont(labelFont);

    JPanel labelsPanel = new JPanel();
    labelsPanel.setLayout(new GridLayout(4, 1));
    labelsPanel.setBackground(Color.black);
    labelsPanel.add(nombreLabel);
    labelsPanel.add(edadLabel);
    labelsPanel.add(engancheLabel);
    labelsPanel.add(plazoLabel);

    JPanel componentesPanel = new JPanel();
    componentesPanel.setLayout(new GridLayout(4, 1));
    componentesPanel.add(nombreCompradorTXT);
    componentesPanel.add(edadTXT);
    componentesPanel.add(engancheTXT);
    componentesPanel.add(plazoTXT);

    nombreCompradorTXT.setForeground(labelColor);
    nombreCompradorTXT.setBackground(Color.black);
    edadTXT.setForeground(labelColor);
    edadTXT.setBackground(Color.black);
    engancheTXT.setForeground(labelColor);
    engancheTXT.setBackground(Color.black);
    plazoTXT.setForeground(labelColor);
    plazoTXT.setBackground(Color.black);

    frame.add(labelsPanel);
    frame.add(componentesPanel);

    JButton aceptarButton = new JButton("Aceptar");

    aceptarButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {

        aceptarButton.setBackground(Color.DARK_GRAY);
      }

      @Override
      public void mouseExited(MouseEvent e) {

        aceptarButton.setBackground(null);
      }
    });

    aceptarButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        boolean[] confirmBuscar = new boolean[3];
        int aC = 0;
        double bC = 0;
        int cC = 0;
        String str = edadTXT.getText();
        String str2 = engancheTXT.getText();
        String str3 = plazoTXT.getText();

        confirmBuscar[0] = false;
        confirmBuscar[1] = false;
        confirmBuscar[2] = false;

        try {
          aC = Integer.parseInt(str);
          if (aC < 18 || aC > 100) {
            JOptionPane.showMessageDialog(
                null,
                "El cliente no puede ser menor de edad o tener más de 100 años");
          } else {
            confirmBuscar[0] = true;
          }
        } catch (NumberFormatException p) {
          JOptionPane.showMessageDialog(
              null,
              "Entrada no válida. Ingresa un número válido para la edad.");
        }

        try {
          bC = Double.parseDouble(str2);

          if (bC == 100) {
            confirmBuscar[1] = true;
            confirmBuscar[2] = true;
          } else {
            if (bC < 20 || bC > 80) {
              JOptionPane.showMessageDialog(
                  null,
                  "Entrada no válida. Ingresa un número entre 20 y 80 ó 100.");
            } else {
              confirmBuscar[1] = true;
            }
          }
        } catch (NumberFormatException p) {
          JOptionPane.showMessageDialog(
              null,
              "Entrada no válida. Ingresa un número válido para el porcentaje de enganche.");
        }

        if (!confirmBuscar[2]) {
          try {
            cC = Integer.parseInt(str3);
            if (cC > 10 || cC <= 0) {
              JOptionPane.showMessageDialog(
                  null, "Entrada no válida. Ingresa un número entre 1 y 10.");
            } else {
              confirmBuscar[2] = true;
            }
          } catch (NumberFormatException p) {
            JOptionPane.showMessageDialog(
                null,
                "Entrada no válida. Ingresa un número válido para el plazo de pago.");
          }
        }

        if (confirmBuscar[0] && confirmBuscar[1] && confirmBuscar[2]) {
          buscar(carroSeleccionado, carrosVendidos, numCarrosVendidos,
                 compradores, numCompradores, array[ho]);
          datos(w, carroSeleccionado);
          numCompradores++;
          numCarrosVendidos++;
          w++;
          nombreCompradorTXT.setText("");
          edadTXT.setText("");
          engancheTXT.setText("");
          plazoTXT.setText("");
          frame.dispose();
          return;
        }
      }
    });

    frame.add(aceptarButton);

    frame.setVisible(true);
    frame.setResizable(false);
    frame.setSize(new Dimension(500, 300));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    return;
  }

  static class BotonListener implements ActionListener {
    private JButton button;
    private JLabel associatedLabel;
    private int ho;
    private JPanel associatedPanel;

    public BotonListener(JButton button, JLabel associatedLabel, int ho,
                         JPanel associatedPanel) {
      this.button = button;
      this.associatedLabel = associatedLabel;
      this.ho = ho;
      this.associatedPanel = associatedPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      boolean confirm = true;

      String[] opciones = {"Realizar venta", "Cancelar"};

      int seleccion = JOptionPane.showOptionDialog(
          null, "Selecciona una opción", "Venta del auto seleccionado",
          JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
          opciones, opciones[0]);

      switch (seleccion) {
      case 0:
        confirm = false;
        pedirDatos(ho);

        break;
      case 1:
        JOptionPane.showMessageDialog(null, "Venta cancelada", "Advertencia",
                                      JOptionPane.WARNING_MESSAGE);
        break;
      }
      if (seleccion == JOptionPane.CLOSED_OPTION) {
        JOptionPane.showMessageDialog(null, "Venta cancelada", "Advertencia",
                                      JOptionPane.WARNING_MESSAGE);
      }

      button.setVisible(confirm);
      associatedLabel.setVisible(confirm);
      associatedPanel.setVisible(confirm);
    }
  }

  public static void main(String[] args) {
    System.setProperty("awt.useSystemAAFontSettings", "on");
    GuiPrototype frame = new GuiPrototype(true);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setVisible(true);
    frame.setBounds(0, 0, screenSize.width, screenSize.height);
    frame.setResizable(false);
  }
}