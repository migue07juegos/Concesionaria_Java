import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;
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

  public static Dimension screenSize =
      Toolkit.getDefaultToolkit().getScreenSize();
  public static final int SCREEN_WIDTH = screenSize.width;
  public static final int SCREEN_HEIGHT = screenSize.height - 15;
  public static int i, l, a = 0, inicio, nuevoValor, valorEliminar, posicion;
  public static int numCompradores = 0;
  public static int numCarrosVendidos = 0;
  public static int w = 0;
  public static int switchBtn = 0;
  public static int get_img = 0;
  public static int reproductor_i = 0;
  public static int oX = 10;
  public static boolean menuExpandido = false;
  public static String switchStr = "";
  public static String x = "";
  public static Carro carroSeleccionado = new GuiPrototype().new Carro();
  public static Vector<Integer> array = new Vector<>();
  public static Vector<Integer> mesesAdeudo = new Vector<>();
  public static Vector<String> color = new Vector<>();
  public static Vector<String> marca = new Vector<>();
  public static Vector<String> modelo = new Vector<>();
  public static Vector<Integer> monto = new Vector<>();
  public static Vector<ImageIcon> images = new Vector<>();
  public static Vector<String> nombreComprador = new Vector<>();
  public static Vector<Integer> edad = new Vector<>();
  public static Vector<String> metodoPago = new Vector<>();
  public static Vector<Double> montoEnganche = new Vector<>();
  public static Vector<Double> liquidacion = new Vector<>();
  public static Vector<Double> adeudo = new Vector<>();
  public static Vector<Double> pagoPorMes = new Vector<>();
  public static Vector<Carro> carrosVendidos = new Vector<>();
  public static Vector<Carro> compradores = new Vector<>();
  public static Vector<String> canciones = new Vector<>();
  public static JTextField marcaTXT = new JTextField();
  public static JTextField colorTXT = new JTextField();
  public static JTextField modeloTXT = new JTextField();
  public static JTextField montoTXT = new JTextField();
  public static JTextField imgTXT = new JTextField();
  public static JTextField nombreCompradorTXT = new JTextField();
  public static JTextField edadTXT = new JTextField();
  public static JTextField engancheTXT = new JTextField();
  public static JTextField plazoTXT = new JTextField();
  public static JTextField archivo_txt = new JTextField();
  public static JTextField nombre_txt = new JTextField();
  public static JTextField abrir_txt = new JTextField();
  public static JTextField reproductorTxt = new JTextField();
  public static BufferedWriter writer;
  public static ProcessBuilder processBuilder;
  public static Process process;
  public static Process procesoReproductor;
  public static int volumen = 50;

  static {
    color.add("Blanco");
    color.add("Azul");
    color.add("Blanco");
    color.add("Naranja");
    color.add("Gris");
    color.add("Plata");
    color.add("Amarillo");
    color.add("Naranja");
    color.add("Plata");
    color.add("Cobre");

    marca.add("Audi");
    marca.add("BMW");
    marca.add("Mercedes-Benz");
    marca.add("McLaren");
    marca.add("Lamborghini");
    marca.add("Toyota");
    marca.add("Chevrolet");
    marca.add("Nissan");
    marca.add("Mazda");
    marca.add("Renault");

    modelo.add("A1");
    modelo.add("325i");
    modelo.add("Clase A");
    modelo.add("650S");
    modelo.add("Aventador");
    modelo.add("Highlander");
    modelo.add("Camaro");
    modelo.add("Altima");
    modelo.add("Mazda 3");
    modelo.add("Kwid");

    monto.add(599900);
    monto.add(975000);
    monto.add(831900);
    monto.add(2123472);
    monto.add(25243345);
    monto.add(865900);
    monto.add(1342000);
    monto.add(737900);
    monto.add(388900);
    monto.add(230100);

    images.add(new ImageIcon("images/Audi.png"));
    images.add(new ImageIcon("images/bmw.png"));
    images.add(new ImageIcon("images/Mercedes.png"));
    images.add(new ImageIcon("images/Mclaren.png"));
    images.add(new ImageIcon("images/Lamborghini.png"));
    images.add(new ImageIcon("images/Toyota.png"));
    images.add(new ImageIcon("images/Chevrolet.png"));
    images.add(new ImageIcon("images/nissan.png"));
    images.add(new ImageIcon("images/Mazda.png"));
    images.add(new ImageIcon("images/Renault.png"));
  }

  public boolean ctrlPressed = false;
  public boolean altPressed = false;
  public boolean sPressed = false;
  public boolean shiftPressed = false;
  public boolean xPressed = false;
  public boolean mPressed = false;

  public GuiPrototype() {}

  public static void valoresAleatorios() {
    Random rand = new Random();
    for (int i = 0; i < marca.size(); i++) {
      array.add(rand.nextInt(9000) + 1000);
    }
  }

  public static Vector<String> mostrarDatos() {
    Vector<String> mostrarDatosStr = new Vector<>();
    valoresAleatorios();

    for (int i = 0; i < marca.size(); i++) {
      mostrarDatosStr.add((String.format(
          "\t\tCarro no.%d:\n\t\tColor: %s\n\t\tMarca: %s\n\t\tModelo: %s\n\t\tPrecio: %d",
          array.get(i), color.get(i), marca.get(i), modelo.get(i),
          monto.get(i))));
    }

    return mostrarDatosStr;
  }

  public static Vector<String>
  mostrarCarrosVendidos(Vector<Carro> carrosVendidos, int numCarrosVendidos) {
    Vector<String> CarrosVendidosHastaElMomento = new Vector<>();

    for (int i = 0; i < numCarrosVendidos; i++) {
      CarrosVendidosHastaElMomento.add(
          String
              .format(
                  "Venta no.%d\n\nComprador: %s\nCarro no.%d:\nColor: %s\nMarca: %s\nModelo: %s\nPrecio: %d\n",
                  i + 1, nombreComprador.get(i),
                  carrosVendidos.get(i).getNumeroControl(),
                  carrosVendidos.get(i).getColor(),
                  carrosVendidos.get(i).getMarca(),
                  carrosVendidos.get(i).getModelo(),
                  carrosVendidos.get(i).getMonto())
              .toString());
    }

    return CarrosVendidosHastaElMomento;
  }

  public static Vector<String> mostrarDatosComprador(Vector<Carro> compradores,
                                                     int numCompradores) {
    Vector<String> datosCompradorF = new Vector<>();
    for (int i = 0; i < numCompradores; i++) {

      if (pagoPorMes.get(i) == 0 && mesesAdeudo.get(i) == 0) {
        datosCompradorF.add((
            (String.format("Comprador %d: %s", i + 1, nombreComprador.get(i))) +
            (String.format("\nEdad: %d", edad.get(i))) +
            (String.format("\nMétodo de pago: %s", metodoPago.get(i))) +
            (String.format("\nSe realizará el pago completo: %.2f",
                           liquidacion.get(i))) +
            (String.format("\nModelo solicitado: %s %s\n\n",
                           compradores.get(i).getMarca(),
                           compradores.get(i).getModelo()))));
      } else {
        datosCompradorF.add((
            (String.format("Comprador %d: %s", i + 1, nombreComprador.get(i))) +
            (String.format("\nEdad: %d", edad.get(i))) +
            (String.format("\nMétodo de pago: %s", metodoPago.get(i))) +
            (String.format("\nMonto de Enganche: %.2f", montoEnganche.get(i))) +
            (String.format("\nAdeudo: %.2f", adeudo.get(i))) +
            (String.format("\nModelo solicitado: %s %s\n\n",
                           compradores.get(i).getMarca(),
                           compradores.get(i).getModelo()))));
      }
    }
    return datosCompradorF;
  }

  public static Vector<String> mostrarInfoPago(int numCompradores) {
    Vector<String> InfoDePagoF = new Vector<>();
    for (int i = 0; i < numCompradores; i++) {

      if (pagoPorMes.get(i) == 0 && mesesAdeudo.get(i) == 0) {
        System.out.print("A");
        InfoDePagoF.add(
            ((String.format("Comprador %d: %s", i + 1, nombreComprador.get(i)) +
              String.format("\nMétodo de pago: %s", metodoPago.get(i)) +
              String.format("\nSe realizará el pago completo: %.2f\n\n",
                            liquidacion.get(i)))));
      } else {
        InfoDePagoF.add(
            ((String.format("Comprador %d: %s", i + 1, nombreComprador.get(i)) +
              String.format("\nMétodo de pago: %s", metodoPago.get(i)) +
              String.format("\nMonto de Enganche: %.2f", montoEnganche.get(i)) +
              String.format("\nAdeudo: %.2f", adeudo.get(i)) +
              String.format("\nPlazo: %d años \nMensualidad: $%.2f\n\n",
                            mesesAdeudo.get(i), pagoPorMes.get(i)))));
      }
    }
    return InfoDePagoF;
  }

  public static void buscar(Carro carroSeleccionado,
                            Vector<Carro> carrosVendidos, int numCarrosVendidos,
                            Vector<Carro> compradores, int numCompradores,
                            int numBtn) {
    do {
      int valorEliminar = 0;
      int truE = 0;

      valorEliminar = numBtn;

      for (int i = 0; i < marca.size(); i++) {
        if (array.get(i) == valorEliminar) {
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

  public static void registroAutos(Carro carro, Vector<Carro> carrosVendidos,
                                   int numCarrosVendidos,
                                   Vector<Carro> compradores,
                                   int numCompradores) {
    carro.setNumeroControl(array.get(inicio));
    carro.setColor(color.get(inicio));
    carro.setMarca(marca.get(inicio));
    carro.setModelo(modelo.get(inicio));
    carro.setMonto(monto.get(inicio));

    carrosVendidos.add(new GuiPrototype().new Carro());
    carrosVendidos.get(numCarrosVendidos)
        .setNumeroControl(carro.getNumeroControl());
    carrosVendidos.get(numCarrosVendidos).setColor(carro.getColor());
    carrosVendidos.get(numCarrosVendidos).setMarca(carro.getMarca());
    carrosVendidos.get(numCarrosVendidos).setModelo(carro.getModelo());
    carrosVendidos.get(numCarrosVendidos).setMonto(carro.getMonto());
    compradores.add(new GuiPrototype().new Carro());
    compradores.get(numCompradores).setMarca(carro.getMarca());
    compradores.get(numCompradores).setModelo(carro.getModelo());
  }

  public static void datos(int i, Carro carro) {
    double a, b, c;

    nombreComprador.add(nombreCompradorTXT.getText());

    String str = edadTXT.getText();
    edad.add(Integer.parseInt(str));

    metodoPago.add(switchStr);

    String str2 = engancheTXT.getText();
    b = Double.parseDouble(str2);

    a = b / 100;
    montoEnganche.add(a * carro.monto);
    adeudo.add(carro.monto - montoEnganche.get(i));
    liquidacion.add((double)carro.monto);

    if (b > 19 && b < 81) {
      String str3 = plazoTXT.getText();
      mesesAdeudo.add(Integer.parseInt(str3));

      c = mesesAdeudo.get(i) * 12;
      pagoPorMes.add(adeudo.get(i) / c);

      JOptionPane.showMessageDialog(
          null, String.format("Mensualidad: %.2f", pagoPorMes.get(i)));
    } else {
      pagoPorMes.add(0.0);
      mesesAdeudo.add(0);
    }
  }

  public static void recibo_personal(Vector<Carro> compradores,
                                     int num_compradores, JPanel informacion,
                                     JCheckBox check) throws IOException {
    try {

      LocalDateTime date = LocalDateTime.now();
      DateTimeFormatter fecha_formateada =
          DateTimeFormatter.ofPattern("dd/MMM/yyyy");
      DateTimeFormatter hora_formateada =
          DateTimeFormatter.ofPattern("HH:mm:ss");

      String fecha = date.format(fecha_formateada);
      String hora = date.format(hora_formateada);

      for (i = 0; i < num_compradores; i++) {

        if (nombre_txt.getText().equals(nombreComprador.get(i))) {
          FileWriter fileWriter =
              new FileWriter(new File(archivo_txt.getText() + ".txt"), true);

          BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

          bufferedWriter.write("Fecha de impresion: " + fecha +
                               "             " + hora);
          bufferedWriter.newLine();

          bufferedWriter.write(String.format(
              "\n\nComprador %d: %s", i + 1,
              (nombreComprador.get(i) == null ? "" : nombreComprador.get(i))));
          bufferedWriter.newLine();
          bufferedWriter.write(String.format("Edad: %d", edad.get(i)));
          bufferedWriter.newLine();
          bufferedWriter.write(String.format(
              "Método de pago: %s",
              (metodoPago.get(i) == null ? "" : metodoPago.get(i))));
          bufferedWriter.newLine();

          if (adeudo.get(i) == 0) {

            bufferedWriter.write(String.format(
                "Se realizará el pago completo: %.2f", liquidacion.get(i)));
          } else {
            bufferedWriter.write(
                String.format("Monto de Enganche: %.2f", montoEnganche.get(i)));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format("Adeudo: %.2f", adeudo.get(i)));
            bufferedWriter.newLine();
            bufferedWriter.write(
                String.format("Plazo: %d años\nMensualidad %.2f",
                              mesesAdeudo.get(i), pagoPorMes.get(i)));
            bufferedWriter.newLine();
            bufferedWriter.write(String.format(
                "Si tarda mas de 3 meses en pagar su mensualidad se le embargará"));
          }

          bufferedWriter.newLine();
          bufferedWriter.write(String.format("Modelo solicitado: %s %s",
                                             compradores.get(i).marca,
                                             compradores.get(i).modelo));
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

  public static void button(JPanel informacion, int funcion,
                            JFrame framePrincipal) {

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

        pedir_recibo(informacion, funcion, framePrincipal);
        framePrincipal.requestFocusInWindow();
      }
    });

    panel.add(button, BorderLayout.EAST);
    informacion.add(panel);
  }

  public static void pedir_recibo(JPanel informacion, int pregunta,
                                  JFrame framePrincipal) {

    JFrame frame = new JFrame();
    frame.setLayout(new GridLayout(1, 2));

    JLabel nombre_recibo = new JLabel("Nombre del Recibo:");
    JLabel nombre_comprador = new JLabel("Nombre comprador:");
    JCheckBox check = new JCheckBox("Abrir archivo");

    Color labelColor = new Color(155, 155, 155);
    Font labelFont = new Font("Arial", Font.PLAIN, 16);

    nombre_recibo.setForeground(labelColor);
    nombre_recibo.setFont(labelFont);

    if (pregunta == 1) {
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
    if (pregunta == 1) {
      labelsPanel.add(nombre_comprador);
    }
    labelsPanel.add(check);

    JPanel componentesPanel = new JPanel();
    componentesPanel.setLayout(new GridLayout(4, 1));
    componentesPanel.add(archivo_txt);
    if (pregunta == 1) {
      componentesPanel.add(nombre_txt);
    }
    componentesPanel.add(check);

    archivo_txt.setForeground(labelColor);
    archivo_txt.setBackground(Color.black);

    if (pregunta == 1) {
      nombre_txt.setForeground(labelColor);
      nombre_txt.setBackground(Color.black);
    }

    check.setForeground(labelColor);
    check.setBackground(Color.black);

    frame.add(labelsPanel);
    frame.add(componentesPanel);
    frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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
          if (pregunta == 1) {
            recibo_personal(compradores, numCompradores, informacion, check);
          } else if (pregunta == 2) {
            recibo(compradores, numCompradores, check);
          }
        } catch (IOException e1) {
          e1.printStackTrace();
        }

        archivo_txt.setText("");
        nombre_txt.setText("");
        abrir_txt.setText("");
        frame.dispose();
        framePrincipal.requestFocusInWindow();
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
      if (nombreComprador.get(i) != null) {
        compradores.append(String.format("\nComprador %d: %s\n", i + 1,
                                         nombreComprador.get(i)));
      }
    }
    return compradores;
  }

  public static void recibo(Vector<GuiPrototype.Carro> compradores2,
                            int num_compradores, JCheckBox check) {

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
          bufferedWriter.write("\nFecha de impresion: " + fecha +
                               "             " + hora);
          bufferedWriter.newLine();
        }
        bufferedWriter.write(String.format(
            "\nComprador %d: %s", i + 1,
            (nombreComprador.get(i) == null ? "" : nombreComprador.get(i))));
        bufferedWriter.newLine();
        bufferedWriter.write(String.format("Edad: %d", edad.get(i)));
        bufferedWriter.newLine();
        bufferedWriter.write(String.format(
            "Método de pago: %s",
            (metodoPago.get(i) == null ? "" : metodoPago.get(i))));
        bufferedWriter.newLine();

        if (adeudo.get(i) == 0) {

          bufferedWriter.write(String.format(
              "Se realizará el pago completo: %.2f", liquidacion.get(i)));
        } else {
          bufferedWriter.write(
              String.format("Monto de Enganche: %.2f", montoEnganche.get(i)));
          bufferedWriter.newLine();
          bufferedWriter.write(String.format("Adeudo: %.2f", adeudo.get(i)));
        }
        bufferedWriter.newLine();
        bufferedWriter.write(String.format("Modelo solicitado: %s %s\n",
                                           compradores2.get(i).marca,
                                           compradores2.get(i).modelo));
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

  public static class SnakeGame extends JPanel implements ActionListener {
    public static final int UNIT_SIZE = 23;
    public static final int GAME_UNITS =
        (int)(SCREEN_WIDTH / UNIT_SIZE) * (SCREEN_HEIGHT / UNIT_SIZE);
    public static final int HORIZONTAL_UNITS = SCREEN_WIDTH / UNIT_SIZE;
    public static final int VERTICAL_UNITS = SCREEN_HEIGHT / UNIT_SIZE;
    public static final int DELAY = 100;
    public static final int INITIAL_SNAKE_SIZE = 6;
    private int appleX;
    private int appleY;
    private Timer timer = new Timer(DELAY, this);
    private char direction;
    private int[] snakeX = new int[GAME_UNITS];
    private int[] snakeY = new int[GAME_UNITS];
    private int snakeSize;
    private int applesEaten;
    boolean keyInput = false;
    String randomGameOverMessage = "";

    SnakeGame(JFrame frame) {
      frame.setFocusable(true);
      frame.requestFocus();
      frame.addKeyListener(new MyKeyAdapter(frame));
      this.setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
      this.setBackground(Color.DARK_GRAY);
    }

    public void startGame() {
      snakeSize = INITIAL_SNAKE_SIZE;
      applesEaten = 0;
      for (int i = 0; i < snakeSize; i++) {
        snakeX[i] = 0;
        snakeY[i] = 0;
      }
      direction = 'R';
      timer.start();
      newApple();
      randomGameOverMessage = "Perdiste :(";
    }

    public void actionPerformed(ActionEvent ev) {
      move();
      checkCollision();
      eatApple();
      repaint();
    }

    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(Color.red);
      g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
      g.setColor(Color.blue);
      g.fillRect(snakeX[0], snakeY[0], UNIT_SIZE, UNIT_SIZE);
      for (int i = 1; i < snakeSize; i++) {
        g.fillRect(snakeX[i], snakeY[i], UNIT_SIZE, UNIT_SIZE);
      }

      g.setColor(Color.white);
      g.setFont(new Font("Liberation Mono", Font.PLAIN, 25));
      FontMetrics fontSize = g.getFontMetrics();
      int fontX = SCREEN_WIDTH -
                  fontSize.stringWidth("Score: " + applesEaten + "  ") - 10;
      int fontY = fontSize.getHeight();
      g.drawString("Score: " + applesEaten, fontX, fontY);

      if (!timer.isRunning()) {
        g.setColor(Color.white);
        g.setFont(new Font("Liberation Mono", Font.PLAIN, 58));

        String message = randomGameOverMessage;
        fontSize = g.getFontMetrics();
        fontX = (SCREEN_WIDTH - fontSize.stringWidth(message)) / 2;
        fontY = (SCREEN_HEIGHT - fontSize.getHeight()) / 2;
        g.drawString(message, fontX, fontY);

        g.setFont(new Font("Liberation Mono", Font.PLAIN, 24));
        message = "Presiona R para reiniciar el juego";
        fontSize = g.getFontMetrics();
        fontX = (SCREEN_WIDTH - fontSize.stringWidth(message)) / 2;
        fontY = fontY + fontSize.getHeight() + 20;
        g.drawString(message, fontX, fontY);

        g.setFont(new Font("Liberation Mono", Font.PLAIN, 24));
        message = "Presiona Esc para salir";
        fontSize = g.getFontMetrics();
        fontX = (SCREEN_WIDTH - fontSize.stringWidth(message)) / 2;
        fontY = fontY + fontSize.getHeight() + 20;
        g.drawString(message, fontX, fontY);
      }
    }

    public void newApple() {
      int x = random(HORIZONTAL_UNITS) * UNIT_SIZE;
      int y = random(VERTICAL_UNITS) * UNIT_SIZE;
      Point provisional = new Point(x, y);
      Point snakePos = new Point();
      boolean newApplePermission = true;
      for (int i = 0; i < snakeSize; i++) {
        snakePos.setLocation(snakeX[i], snakeY[i]);
        if (provisional.equals(snakePos)) {
          newApplePermission = false;
        }
      }
      if (newApplePermission) {
        appleX = x;
        appleY = y;
      } else {
        newApple();
      }
    }

    public void checkCollision() {
      if (snakeX[0] >= (SCREEN_WIDTH) || snakeX[0] < 0 ||
          snakeY[0] >= (SCREEN_HEIGHT - 20) || snakeY[0] < 0) {
        gameOver();
      }
      for (int i = 1; i < snakeSize; i++) {
        if ((snakeX[0] == snakeX[i]) && (snakeY[0] == snakeY[i])) {
          gameOver();
        }
      }
    }

    public void eatApple() {
      if (snakeX[0] == appleX && snakeY[0] == appleY) {
        snakeSize++;
        applesEaten++;
        newApple();
      }
    }

    public void move() {
      for (int i = snakeSize; i > 0; i--) {
        snakeX[i] = snakeX[i - 1];
        snakeY[i] = snakeY[i - 1];
      }
      switch (direction) {
      case 'R':
        snakeX[0] += UNIT_SIZE;
        break;
      case 'L':
        snakeX[0] -= UNIT_SIZE;
        break;
      case 'U':
        snakeY[0] -= UNIT_SIZE;
        break;
      case 'D':
        snakeY[0] += UNIT_SIZE;
        break;
      }

      keyInput = false;
    }

    public void gameOver() { timer.stop(); }

    public int random(int range) { return (int)(Math.random() * range); }

    class MyKeyAdapter extends KeyAdapter {
      JFrame frame;
      MyKeyAdapter(JFrame frame) { this.frame = frame; }
      public void keyPressed(KeyEvent k) {
        switch (k.getKeyCode()) {
        case (KeyEvent.VK_5):
          snakeSize++;
          applesEaten++;
          break;
        case (KeyEvent.VK_DOWN):
          if (direction != 'U' && keyInput == false) {
            direction = 'D';
            keyInput = true;
          }
          break;
        case (KeyEvent.VK_UP):
          if (direction != 'D' && !keyInput) {
            direction = 'U';
            keyInput = true;
          }
          break;
        case (KeyEvent.VK_LEFT):
          if (direction != 'R' && keyInput == false) {
            direction = 'L';
            keyInput = true;
          }
          break;
        case (KeyEvent.VK_RIGHT):
          if (direction != 'L' && keyInput == false) {
            direction = 'R';
            keyInput = true;
          }
          break;
        case (KeyEvent.VK_R):
          if (!timer.isRunning()) {
            startGame();
          }
          break;
        case KeyEvent.VK_ESCAPE:
          frame.dispose();
          break;
        }
      }
    }
  }

  public static class InnerSnakeGame extends JFrame {
    SnakeGame SnakeGame = new SnakeGame(this);
    InnerSnakeGame() {
      this.setTitle("Snake");
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.pack();
      this.setSize(new Dimension(SCREEN_WIDTH + 20, SCREEN_HEIGHT + 20));
      this.setResizable(false);
      this.setLocationRelativeTo(null);
      this.setVisible(true);
      this.getContentPane().setBackground(Color.DARK_GRAY);

      this.add(SnakeGame);
      SnakeGame.startGame();
    }
  }

  public static class Mine
      extends JFrame implements ActionListener, MouseListener {
    int nomines = 80;
    int perm[][];
    String tmp;
    boolean found = false;
    int row;
    int column;
    int guesses[][];
    JButton b[][];
    int[][] mines;
    boolean allmines;
    int n = 30;
    int m = 30;
    int deltax[] = {-1, 0, 1, -1, 1, -1, 0, 1};
    int deltay[] = {-1, -1, -1, 0, 0, 1, 1, 1};
    double starttime;
    double endtime;
    public Mine() {
      addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent k) {
          if (k.getKeyCode() == KeyEvent.VK_ESCAPE) {
            dispose();
          }
        }
      });
      getRootPane().setBackground(Color.BLACK);
      setTitle("Busca minas");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setResizable(false);
      perm = new int[n][m];
      boolean allmines = false;
      guesses = new int[n + 2][m + 2];
      mines = new int[n + 2][m + 2];
      b = new JButton[n][m];
      setLayout(new GridLayout(n, m));
      for (int y = 0; y < m + 2; y++) {
        mines[0][y] = 3;
        mines[n + 1][y] = 3;
        guesses[0][y] = 3;
        guesses[n + 1][y] = 3;
      }
      for (int x = 0; x < n + 2; x++) {
        mines[x][0] = 3;
        mines[x][m + 1] = 3;
        guesses[x][0] = 3;
        guesses[x][m + 1] = 3;
      }
      do {
        int check = 0;
        for (int y = 1; y < m + 1; y++) {
          for (int x = 1; x < n + 1; x++) {
            mines[x][y] = 0;
            guesses[x][y] = 0;
          }
        }
        for (int x = 0; x < nomines; x++) {
          mines[(int)(Math.random() * (n) + 1)]
               [(int)(Math.random() * (m) + 1)] = 1;
        }
        for (int x = 0; x < n; x++) {
          for (int y = 0; y < m; y++) {
            if (mines[x + 1][y + 1] == 1) {
              check++;
            }
          }
        }
        if (check == nomines) {
          allmines = true;
        }
      } while (allmines == false);
      for (int y = 0; y < m; y++) {
        for (int x = 0; x < n; x++) {
          if ((mines[x + 1][y + 1] == 0) || (mines[x + 1][y + 1] == 1)) {
            perm[x][y] = perimcheck(x, y);
          }
          b[x][y] = new JButton("?");
          b[x][y].setBackground(Color.BLACK);
          b[x][y].setForeground(new Color(155, 155, 155));
          b[x][y].setFocusPainted(false);
          b[x][y].addActionListener(this);
          b[x][y].addMouseListener(this);
          add(b[x][y]);
          b[x][y].setEnabled(true);
        }
      }
      pack();
      setFocusable(true);
      setSize(screenSize.width, screenSize.height);
      setVisible(true);

      starttime = System.nanoTime();
    }

    class MyKeyAdapter extends KeyAdapter {
      JFrame frame;
      MyKeyAdapter(JFrame frame) { this.frame = frame; }
      public void keyPressed(KeyEvent k) {
        switch (k.getKeyCode()) {
        case KeyEvent.VK_ESCAPE:
          frame.dispose();
          break;
        }
      }
    }

    public void actionPerformed(ActionEvent e) {
      found = false;
      JButton current = (JButton)e.getSource();
      for (int y = 0; y < m; y++) {
        for (int x = 0; x < n; x++) {
          JButton t = b[x][y];
          if (t == current) {
            row = x;
            column = y;
            found = true;
          }
        }
      }
      if (!found) {
        System.out.println("No se encontro el botón, hubo un error");
        System.exit(-1);
      }
      Component temporaryLostComponent = null;
      if (b[row][column].getBackground() == Color.orange) {
        return;
      } else if (mines[row + 1][column + 1] == 1) {
        JOptionPane.showMessageDialog(temporaryLostComponent, "Perdiste!!!!.");
        dispose();
      } else {
        tmp = Integer.toString(perm[row][column]);
        if (perm[row][column] == 0) {
          tmp = " ";
        }
        b[row][column].setText(tmp);
        b[row][column].setEnabled(false);
        checkifend();
        if (perm[row][column] == 0) {
          scan(row, column);
          checkifend();
        }
      }
    }

    public void checkifend() {
      int check = 0;
      for (int y = 0; y < m; y++) {
        for (int x = 0; x < n; x++) {
          if (b[x][y].isEnabled()) {
            check++;
          }
        }
      }
      if (check == nomines) {
        endtime = System.nanoTime();
        Component temporaryLostComponent = null;
        JOptionPane.showMessageDialog(
            temporaryLostComponent,
            "Felicidades Has Ganado!!! \nTe Tomo " +
                (int)((endtime - starttime) / 1000000000) + " segundos!");
      }
    }

    public void scan(int x, int y) {
      for (int a = 0; a < 8; a++) {
        if (mines[x + 1 + deltax[a]][y + 1 + deltay[a]] == 3) {

        } else if ((perm[x + deltax[a]][y + deltay[a]] == 0) &&
                   (mines[x + 1 + deltax[a]][y + 1 + deltay[a]] == 0) &&
                   (guesses[x + deltax[a] + 1][y + deltay[a] + 1] == 0)) {
          if (b[x + deltax[a]][y + deltay[a]].isEnabled()) {
            b[x + deltax[a]][y + deltay[a]].setText(" ");
            b[x + deltax[a]][y + deltay[a]].setEnabled(false);
            scan(x + deltax[a], y + deltay[a]);
          }
        } else if ((perm[x + deltax[a]][y + deltay[a]] != 0) &&
                   (mines[x + 1 + deltax[a]][y + 1 + deltay[a]] == 0) &&
                   (guesses[x + deltax[a] + 1][y + deltay[a] + 1] == 0)) {

          b[x + deltax[a]][y + deltay[a]].setText(
              Integer.toString(perm[x + deltax[a]][y + deltay[a]]));
          b[x + deltax[a]][y + deltay[a]].setEnabled(false);
        }
      }
    }

    public int perimcheck(int a, int y) {
      int minecount = 0;
      for (int x = 0; x < 8; x++) {
        if (mines[a + deltax[x] + 1][y + deltay[x] + 1] == 1) {
          minecount++;
        }
      }
      return minecount;
    }

    public void windowIconified(WindowEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent arg0) {}

    @Override
    public void mouseExited(MouseEvent arg0) {}

    @Override
    public void mousePressed(MouseEvent e) {
      if (e.getButton() == MouseEvent.BUTTON3) {
        found = false;
        Object current = e.getSource();
        for (int y = 0; y < m; y++) {
          for (int x = 0; x < n; x++) {
            JButton t = b[x][y];
            if (t == current) {
              row = x;
              column = y;
              found = true;
            }
          }
        }
        if (!found) {
          System.out.println("No se encontro el botón, hubo un error");
          System.exit(-1);
        }
        if ((guesses[row + 1][column + 1] == 0) &&
            (b[row][column].isEnabled())) {
          b[row][column].setText("x");
          guesses[row + 1][column + 1] = 1;
          b[row][column].setBackground(Color.orange);
        } else if (guesses[row + 1][column + 1] == 1) {
          b[row][column].setText("?");
          guesses[row + 1][column + 1] = 0;
          b[row][column].setBackground(Color.BLACK);
        }
      }
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {}
  }

  public GuiPrototype(boolean Ventana) {

    if (Ventana) {
      addKeyListener(new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
          if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            ctrlPressed = true;
          } else if (e.getKeyCode() == KeyEvent.VK_ALT) {
            altPressed = true;
          } else if (e.getKeyCode() == KeyEvent.VK_S) {
            sPressed = true;
          } else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shiftPressed = true;
          } else if (e.getKeyCode() == KeyEvent.VK_X) {
            xPressed = true;
          } else if (e.getKeyCode() == KeyEvent.VK_M) {
            mPressed = true;
          }
          if (ctrlPressed && altPressed && sPressed) {
            new InnerSnakeGame();
            ctrlPressed = false;
            altPressed = false;
            sPressed = false;
          } else if (shiftPressed && xPressed && mPressed) {
            new Mine();
            shiftPressed = false;
            xPressed = false;
            mPressed = false;
          }
        }

        @Override
        public void keyReleased(KeyEvent e) {
          if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
            ctrlPressed = false;
          } else if (e.getKeyCode() == KeyEvent.VK_ALT) {
            altPressed = false;
          } else if (e.getKeyCode() == KeyEvent.VK_S) {
            sPressed = false;
          } else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
          } else if (e.getKeyCode() == KeyEvent.VK_X) {
            xPressed = false;
          } else if (e.getKeyCode() == KeyEvent.VK_M) {
            mPressed = false;
          }
        }
      });

      setFocusable(true);
      requestFocus();

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
      Realizar_venta(inicioPanel, 0, this);

      add(scrollPane, BorderLayout.CENTER);

      addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
          detenerReproductor();
          System.exit(0);
        }
      });
    }
  }

  public static void Realizar_venta(JPanel contentPanel, int o,
                                    JFrame framePrincipal) {

    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

    Vector<String> moDaStr = mostrarDatos();

    for (int h = o; h < monto.size(); h++) {

      JPanel panel = new JPanel(new BorderLayout());
      panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 0, 50));
      panel.setBorder(
          BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(0, 85, 119)));

      JLabel label = new JLabel(
          "<html><font color='#9B9B9B'> " +
          moDaStr.get(h).replace("\n", "<br>").replace("\t", "&nbsp;") +
          "</font></html>");
      label.setPreferredSize(new Dimension(300, 300));
      label.setFont(new Font("Arial", Font.PLAIN, 25));
      panel.add(label, BorderLayout.CENTER);

      JButton button = new JButton(images.get(h));

      button.setPreferredSize(new Dimension(300, 300));

      button.addActionListener(
          new BotonListener(button, label, h, panel, framePrincipal));

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
      framePrincipal.requestFocusInWindow();
    }
  }

  public static void
  informacion_compradores(boolean ventana, JPanel informacion, int indexButton,
                          JPanel iniciox, JFrame framePrincipal)
      throws IOException {
    informacion.removeAll();
    informacion.revalidate();
    informacion.repaint();

    String resStr = "";
    Vector<String> resStrArr = new Vector<>();

    switch (indexButton) {
    case 2:
      resStrArr = mostrarDatosComprador(compradores, numCompradores);
      labels(informacion, resStr, false, resStrArr);
      framePrincipal.requestFocusInWindow();
      break;
    case 3:
      resStrArr = mostrarCarrosVendidos(carrosVendidos, numCarrosVendidos);
      labels(informacion, resStr, false, resStrArr);
      framePrincipal.requestFocusInWindow();
      break;
    case 4:
      resStrArr = mostrarInfoPago(numCompradores);
      labels(informacion, resStr, false, resStrArr);
      framePrincipal.requestFocusInWindow();
      break;
    case 5:
      resStr = mostrar_compradores(numCompradores).toString();
      labels(informacion, resStr, true, resStrArr);
      button(informacion, 1, framePrincipal);
      break;
    case 6:
      labels(informacion, resStr, true, resStrArr);
      button(informacion, 2, framePrincipal);
      break;
    case 7:
      reproductor(informacion, framePrincipal);
      break;
    case 8:
      agregar_elemento(informacion, iniciox, framePrincipal);
      break;
    }
  }

  public static void labels(JPanel informacion, String resStr, boolean recibo,
                            Vector<String> resStrArr) {
    JLabel label = new JLabel("<html><font color='#9B9B9B'>" +
                              resStr.replace("\n", "<br>") + "</font></html>");
    label.setFont(new Font("Arial", Font.PLAIN, 25));

    if (!recibo) {
      informacion.setLayout(new BoxLayout(informacion, BoxLayout.Y_AXIS));

      for (int j = 0; j < numCompradores; j++) {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 0, 50));

        JLabel label2 = new JLabel("<html><font color='#9B9B9B'> " +
                                   resStrArr.get(j).replace("\n", "<br>") +
                                   "</font></html>");
        label2.setPreferredSize(new Dimension(250, 250));
        label2.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(label2, BorderLayout.CENTER);

        informacion.add(panel);
      }
    }

    else {
      informacion.setLayout(new FlowLayout());
      informacion.add(label);
    }
  }

  public static void reproductor(JPanel panel, JFrame framePrincipal) {

    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    JButton btnAnterior = new JButton("Anterior");
    JButton btnSalir = new JButton("Salir");
    JButton btnPausa = new JButton("Pausa");
    JButton btnSig = new JButton("Siguiente");
    JButton btnAgregar = new JButton("Agregar");

    JPanel uno = new JPanel();
    JPanel dos = new JPanel();

    JSlider slider = new JSlider(JSlider.VERTICAL, 0, 100, 50);
    slider.setMajorTickSpacing(50);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);
    slider.setMinorTickSpacing(50);
    slider.setForeground(Color.white);
    slider.setBackground(Color.black);
    slider.setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 0));

    slider.addChangeListener(e -> {
      try {
        volumen = slider.getValue();
        if (System.getProperty("os.name").contains("Win")) {
          processBuilder = new ProcessBuilder(
              "cmd", "/c",
              String.format(
                  "echo { \"command\": [\"set_property\", \"volume\", %d] } > \\\\.\\pipe\\mpvsocket",
                  slider.getValue()));
          process = processBuilder.start();

        } else {
          String comando = String.format(
              "echo '{ \"command\": [\"set_property\", \"volume\", %d] }' | socat - /tmp/mpvsocket",
              slider.getValue());
          volumen1(comando, "volumen.sh");
        }

      } catch (IOException ex) {
        ex.printStackTrace();
      }
      framePrincipal.requestFocusInWindow();
    });

    btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnSalir.setFocusPainted(false);
    btnSalir.setPreferredSize(new Dimension(300, 100));
    btnSalir.setVerticalTextPosition(SwingConstants.BOTTOM);
    btnSalir.setHorizontalTextPosition(SwingConstants.RIGHT);
    btnSalir.getVerifyInputWhenFocusTarget();
    btnSalir.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {

        btnSalir.setBackground(Color.DARK_GRAY);
      }

      @Override
      public void mouseExited(MouseEvent e) {

        btnSalir.setBackground(null);
      }
    });

    btnSalir.addActionListener(e -> {
      detenerReproductor();
      btnPausa.setText("Pausa");
      framePrincipal.requestFocusInWindow();
    });

    reproductorTxt.setForeground(new Color(155, 155, 155));
    reproductorTxt.setBackground(Color.black);
    reproductorTxt.setPreferredSize(new Dimension(600, 100));

    btnPausa.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnPausa.setFocusPainted(false);
    btnPausa.setPreferredSize(new Dimension(300, 100));
    btnPausa.setVerticalTextPosition(SwingConstants.BOTTOM);
    btnPausa.setHorizontalTextPosition(SwingConstants.RIGHT);
    btnPausa.getVerifyInputWhenFocusTarget();
    btnPausa.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {

        btnPausa.setBackground(Color.DARK_GRAY);
      }

      @Override
      public void mouseExited(MouseEvent e) {

        btnPausa.setBackground(null);
      }
    });
    btnPausa.addActionListener(e -> {
      if (btnPausa.getText().equals("Reproducir")) {
        btnPausa.setText("Pausar");
      } else {
        btnPausa.setText("Reproducir");
      }
      if (System.getProperty("os.name").contains("Win")) {
        try {
          new ProcessBuilder(
              "cmd", "/c",
              "echo { \"command\": [\"cycle\", \"pause\"] } >\\\\.\\pipe\\mpvsocket")
              .start();
        } catch (IOException x) {
          x.printStackTrace();
        }

      } else {
        String comando =
            "echo '{ \"command\": [\"cycle\", \"pause\"] }' | socat - /tmp/mpvsocket";
        volumen1(comando, "pausa.sh");
      }
      framePrincipal.requestFocusInWindow();
    });

    btnSig.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnSig.setFocusPainted(false);
    btnSig.setPreferredSize(new Dimension(300, 100));
    btnSig.setVerticalTextPosition(SwingConstants.BOTTOM);
    btnSig.setHorizontalTextPosition(SwingConstants.RIGHT);
    btnSig.getVerifyInputWhenFocusTarget();
    btnSig.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {

        btnSig.setBackground(Color.DARK_GRAY);
      }

      @Override
      public void mouseExited(MouseEvent e) {

        btnSig.setBackground(null);
      }
    });
    btnSig.addActionListener(e -> {
      if (procesoReproductor != null && reproductor_i <= canciones.size() - 2) {
        procesoReproductor.destroy();
      }
      framePrincipal.requestFocusInWindow();
    });

    btnAnterior.setAlignmentX(Component.LEFT_ALIGNMENT);
    btnAnterior.setFocusPainted(false);
    btnAnterior.setPreferredSize(new Dimension(300, 100));
    btnAnterior.setVerticalTextPosition(SwingConstants.BOTTOM);
    btnAnterior.setHorizontalTextPosition(SwingConstants.RIGHT);
    btnAnterior.getVerifyInputWhenFocusTarget();
    btnAnterior.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {

        btnAnterior.setBackground(Color.DARK_GRAY);
      }

      @Override
      public void mouseExited(MouseEvent e) {

        btnAnterior.setBackground(null);
      }
    });
    btnAnterior.addActionListener(e -> {
      if (procesoReproductor != null && reproductor_i >= 1) {
        reproductor_i--;
        reproductor_i--;
        procesoReproductor.destroy();
      }
      framePrincipal.requestFocusInWindow();
    });

    btnAgregar.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnAgregar.setFocusPainted(false);
    btnAgregar.setPreferredSize(new Dimension(300, 100));
    btnAgregar.setVerticalTextPosition(SwingConstants.BOTTOM);
    btnAgregar.setHorizontalTextPosition(SwingConstants.RIGHT);
    btnAgregar.getVerifyInputWhenFocusTarget();
    btnAgregar.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {

        btnAgregar.setBackground(Color.DARK_GRAY);
      }

      @Override
      public void mouseExited(MouseEvent e) {

        btnAgregar.setBackground(null);
      }
    });

    btnAgregar.addActionListener(e -> {
      btnPausa.setText("Pausar");
      canciones.add(reproductorTxt.getText());
      reproductorTxt.setText(null);
      if (canciones.size() == 1) {
        Reproductor reproductor1 = new Reproductor();
        reproductor1.start();
      }
      framePrincipal.requestFocusInWindow();
    });

    uno.add(btnSalir);
    uno.add(reproductorTxt);
    uno.add(btnAgregar);
    uno.setBorder(BorderFactory.createEmptyBorder(50, 12, 300, 40));

    dos.add(btnAnterior);
    dos.add(btnPausa);
    dos.add(btnSig);
    dos.add(slider);
    dos.setBorder(BorderFactory.createEmptyBorder(0, 130, 500, 0));

    panel.add(uno);
    panel.add(dos);
  }

  public static void volumen1(String comando, String nombreArchivo) {
    try {

      String directorioTemp = System.getProperty("java.io.tmpdir");

      Path pathArchivoTemp = Path.of(directorioTemp, nombreArchivo);

      if (new File(pathArchivoTemp.toString()).exists()) {
        new File(pathArchivoTemp.toString()).delete();
      }
      BufferedWriter escribir =
          new BufferedWriter(new FileWriter(pathArchivoTemp.toString()));
      escribir.write(comando);
      escribir.close();

      Set<PosixFilePermission> perms = new HashSet<>();
      perms.add(PosixFilePermission.OTHERS_READ);
      perms.add(PosixFilePermission.OTHERS_WRITE);
      perms.add(PosixFilePermission.OTHERS_EXECUTE);
      perms.add(PosixFilePermission.OWNER_EXECUTE);
      perms.add(PosixFilePermission.OWNER_READ);

      Files.setPosixFilePermissions(pathArchivoTemp, perms);

      Process ejecutar = new ProcessBuilder(pathArchivoTemp.toString()).start();
      try {
        ejecutar.waitFor();
      } catch (InterruptedException e) {

        e.printStackTrace();
      }
      ejecutar.destroy();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void detenerReproductor() {
    if (procesoReproductor != null) {
      canciones.clear();
      reproductor_i = 0;
      procesoReproductor.destroy();
    }
  }

  static class Reproductor extends Thread {
    @Override

    public void run() {
      String mpvsocket;
      String logFilePath;
      if (System.getProperty("os.name").contains("Win")) {
        mpvsocket = "\\\\.\\"
                    + "pipe\\mpvsocket";
        logFilePath = "NUL";
      } else {
        mpvsocket = Paths.get(System.getProperty("java.io.tmpdir"), "mpvsocket")
                        .toString();
        logFilePath = "/dev/null";
      }

      while (reproductor_i <= canciones.size() - 1) {
        try {
          ProcessBuilder processBuilder;
          processBuilder = new ProcessBuilder(
              "mpv", "--no-video", "--volume=" + volumen, "--volume-max=100",
              "--input-ipc-server=" + mpvsocket, canciones.get(reproductor_i));
          processBuilder.redirectError(
              ProcessBuilder.Redirect.appendTo(new File(logFilePath)));
          procesoReproductor = processBuilder.start();
          procesoReproductor.waitFor();
          procesoReproductor.destroy();
          reproductor_i++;
        } catch (IOException | InterruptedException e) {
          e.printStackTrace();
        }
      }
      canciones.clear();
      reproductor_i = 0;
      System.err.println("listo para cerrar hilo");
      return;
    }
  }

  public static void agregar_elemento(JPanel panel, JPanel iniciox,
                                      JFrame framePrincipal) {

    JPanel panel2 = new JPanel();
    JButton button = new JButton("Agregar auto");
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

    button.addActionListener(e -> {
      pedirCarro(iniciox, framePrincipal);
      framePrincipal.requestFocusInWindow();
    });
    panel2.add(button);
    panel.add(panel2);
  }

  public static void pedirCarro(JPanel iniciox, JFrame framePrincipal) {
    JFrame frame = new JFrame();
    frame.setLayout(new GridLayout(1, 2));

    JLabel marcaLabel = new JLabel("Marca del carro:");
    JLabel colorLabel = new JLabel("Color del carro:");
    JLabel modeloLabel = new JLabel("Modelo del carro:");
    JLabel montoLabel = new JLabel("Monto del carro: ");
    JLabel rutaImgLabel = new JLabel("Ruta de la imagen del carro:");
    JButton btn_img = new JButton("Imagen");
    JFileChooser file = new JFileChooser();

    Color labelColor = new Color(155, 155, 155);
    Font labelFont = new Font("Arial", Font.PLAIN, 16);

    btn_img.addActionListener(e -> a = file.showOpenDialog(frame));
    btn_img.setFocusPainted(false);
    FileNameExtensionFilter filter =
        new FileNameExtensionFilter("Archivos de Imagen", "png");
    file.setFileFilter(filter);
    file.setFileSelectionMode(JFileChooser.FILES_ONLY);

    marcaLabel.setForeground(labelColor);
    marcaLabel.setFont(labelFont);
    colorLabel.setForeground(labelColor);
    colorLabel.setFont(labelFont);
    modeloLabel.setForeground(labelColor);
    modeloLabel.setFont(labelFont);
    montoLabel.setForeground(labelColor);
    montoLabel.setFont(labelFont);
    rutaImgLabel.setForeground(labelColor);
    rutaImgLabel.setFont(labelFont);

    JPanel labelsPanel = new JPanel();
    labelsPanel.setLayout(new GridLayout(7, 1));
    labelsPanel.setBackground(Color.black);
    labelsPanel.add(marcaLabel);
    labelsPanel.add(colorLabel);
    labelsPanel.add(modeloLabel);
    labelsPanel.add(montoLabel);
    labelsPanel.add(rutaImgLabel);

    JPanel componentesPanel = new JPanel();
    componentesPanel.setLayout(new GridLayout(7, 1));
    componentesPanel.add(marcaTXT);
    componentesPanel.add(colorTXT);
    componentesPanel.add(modeloTXT);
    componentesPanel.add(montoTXT);
    componentesPanel.add(btn_img);

    marcaTXT.setForeground(labelColor);
    marcaTXT.setBackground(Color.black);
    colorTXT.setForeground(labelColor);
    colorTXT.setBackground(Color.black);
    modeloTXT.setForeground(labelColor);
    modeloTXT.setBackground(Color.black);
    montoTXT.setForeground(labelColor);
    montoTXT.setBackground(Color.black);
    btn_img.setForeground(labelColor);
    btn_img.setBackground(Color.black);

    frame.add(labelsPanel);
    frame.add(componentesPanel);

    JButton aceptarButton = new JButton("Aceptar");
    aceptarButton.setFocusPainted(false);

    aceptarButton.addActionListener(e -> {
      String marcax = marcaTXT.getText();
      String colorx = colorTXT.getText();
      String modelox = modeloTXT.getText();
      String montox = montoTXT.getText();

      boolean itegerCorrect = false;

      try {
        Integer.parseInt(montox);
        itegerCorrect = true;
      } catch (Exception p) {
        JOptionPane.showMessageDialog(null,
                                      "Ingresa un número válido sin décimal");
      }

      if (marcax != null && colorx != null && modelox != null &&
          montox != null && a == JFileChooser.APPROVE_OPTION && itegerCorrect) {

        ImageIcon icono = new ImageIcon();

        try {
          icono = new ImageIcon(
              Redimensionar(ImageIO.read(file.getSelectedFile()), 300, 169));
        } catch (IOException e1) {
          e1.printStackTrace();
        }

        color.add(colorx);
        marca.add(marcax);
        modelo.add(modelox);
        monto.add(Integer.parseInt(montox));
        images.add(icono);
        Realizar_venta(iniciox, oX, framePrincipal);
        oX++;
        System.out.println(oX);
        marcaTXT.setText("");
        colorTXT.setText("");
        modeloTXT.setText("");
        montoTXT.setText("");
        imgTXT.setText("");
        frame.dispose();
        return;
      }
    });

    aceptarButton.setBorder(null);
    componentesPanel.add(new Label(""));
    componentesPanel.add(aceptarButton);

    frame.setVisible(true);
    frame.setResizable(false);
    frame.setSize(new Dimension(450, 320));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    framePrincipal.requestFocusInWindow();
    return;
  }

  public static void copiarImagen(Path rutaImagenOriginal) throws IOException {
    Path copied = Paths.get(System.getProperty("user.dir") + "/data/"
                            + "imagen.png");
    Path originalPath = rutaImagenOriginal;
    Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
  }

  static BufferedImage Redimensionar(BufferedImage originalImage,
                                     int targetWidth, int targetHeight)
      throws IOException {
    Image resultingImage = originalImage.getScaledInstance(
        targetWidth, targetHeight, Image.SCALE_DEFAULT);
    BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight,
                                                  BufferedImage.TYPE_INT_RGB);
    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
    return outputImage;
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
        toggleMenu(menuPanel, panelInicio, infoPanel, frame);
      }
      @Override
      public void mouseEntered(MouseEvent e) {

        button.setBackground(Color.DARK_GRAY);
      }

      @Override
      public void mouseExited(MouseEvent e) {

        button.setBackground(null);
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
    frame.requestFocusInWindow();
  }

  public static void agregarElementoMenu(JPanel menuPanel, String funcion,
                                         int indexButton, JPanel panelInicio,
                                         JPanel infoPanel,
                                         JFrame framePrincipal) {

    JButton button = new JButton(funcion);
    button.setAlignmentX(Component.CENTER_ALIGNMENT);
    button.setText(funcion);
    button.addActionListener(new MenuBotonListener(indexButton, panelInicio,
                                                   infoPanel, framePrincipal));
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
    framePrincipal.requestFocusInWindow();
  }

  public static void toggleMenu(JPanel panel, JPanel panelInicio,
                                JPanel infoPanel, JFrame framePrincipal) {

    if (menuExpandido) {
      ocultarMenu(panel, framePrincipal);
    } else {
      menuExpandido = true;
      mostrarMenu(panel, panelInicio, infoPanel, framePrincipal);
    }
    framePrincipal.requestFocusInWindow();
  }

  public static void mostrarMenu(JPanel panel, JPanel panelInicio,
                                 JPanel infoPanel, JFrame framePrincipal) {

    panel.setPreferredSize(new Dimension(200, panel.getHeight()));
    panel.removeAll();

    agregarElementoMenu(panel, "Realizar venta", 1, panelInicio, infoPanel,
                        framePrincipal);
    agregarElementoMenu(panel, "Informacion compradores", 2, panelInicio,
                        infoPanel, framePrincipal);
    agregarElementoMenu(panel, "Ventas realizadas", 3, panelInicio, infoPanel,
                        framePrincipal);
    agregarElementoMenu(panel, "Informacion de pago", 4, panelInicio, infoPanel,
                        framePrincipal);
    agregarElementoMenu(panel, "Recibo personal", 5, panelInicio, infoPanel,
                        framePrincipal);
    agregarElementoMenu(panel, "Recibo General", 6, panelInicio, infoPanel,
                        framePrincipal);
    agregarElementoMenu(panel, "Reproductor", 7, panelInicio, infoPanel,
                        framePrincipal);
    agregarElementoMenu(panel, "Agregar producto", 8, panelInicio, infoPanel,
                        framePrincipal);

    panel.setBorder(
        BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(0, 0, 0)));

    panel.revalidate();
    panel.repaint();
    framePrincipal.requestFocusInWindow();
  }

  public static void ocultarMenu(JPanel panel, JFrame framePrincipal) {

    panel.setBorder(
        BorderFactory.createMatteBorder(0, 1, 0, 1, new Color(0, 85, 119)));

    panel.setPreferredSize(new Dimension(60, panel.getHeight()));
    menuExpandido = false;
    panel.removeAll();
    panel.revalidate();
    panel.repaint();
    framePrincipal.requestFocusInWindow();
  }

  static class MenuBotonListener implements ActionListener {
    public int indexButton;
    public JPanel panelInicio;
    public JPanel infoPanel;
    public JFrame framePrincipal;

    public MenuBotonListener(int indexButton, JPanel panelInicio,
                             JPanel infoPanel, JFrame framePrincipal) {
      this.indexButton = indexButton;
      this.panelInicio = panelInicio;
      this.infoPanel = infoPanel;
      this.framePrincipal = framePrincipal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (indexButton == 1) {
        panelInicio.setVisible(true);
        infoPanel.setVisible(false);
      } else {
        panelInicio.setVisible(false);
        try {
          informacion_compradores(menuExpandido, infoPanel, indexButton,
                                  panelInicio, framePrincipal);
        } catch (IOException e1) {
          e1.printStackTrace();
        }
        infoPanel.setVisible(true);
      }
      framePrincipal.requestFocusInWindow();
    }
  }

  public static void pedirDatos(int ho, JFrame framePrincipal) {
    switchBtn = 0;
    JFrame frame = new JFrame();
    frame.setLayout(new GridLayout(1, 2));

    JLabel nombreLabel = new JLabel("Nombre del Comprador:");
    JLabel edadLabel = new JLabel("Edad:");
    JLabel engancheLabel = new JLabel("Enganche: %");
    JLabel plazoLabel = new JLabel("Plazo (años):");
    JRadioButton radio1 = new JRadioButton("Tarjeta");
    JRadioButton radio2 = new JRadioButton("Cheque");
    JRadioButton radio3 = new JRadioButton("Efectivo");
    ButtonGroup bg = new ButtonGroup();

    radio1.addChangeListener(e -> {
      if (radio1.isSelected()) {
        switchBtn = 1;
      }
    });

    radio2.addChangeListener(e -> {
      if (radio2.isSelected()) {
        switchBtn = 2;
      }
    });

    radio3.addChangeListener(e -> {
      if (radio3.isSelected()) {
        switchBtn = 3;
      }
    });

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
    radio1.setForeground(labelColor);
    radio1.setFont(labelFont);
    radio2.setForeground(labelColor);
    radio2.setFont(labelFont);
    radio3.setForeground(labelColor);
    radio3.setFont(labelFont);
    radio1.setBackground(Color.black);
    radio2.setBackground(Color.black);
    radio3.setBackground(Color.black);
    radio1.setFocusPainted(false);
    radio2.setFocusPainted(false);
    radio3.setFocusPainted(false);

    JPanel labelsPanel = new JPanel();
    labelsPanel.setLayout(new GridLayout(7, 1));
    labelsPanel.setBackground(Color.black);
    labelsPanel.add(nombreLabel);
    labelsPanel.add(edadLabel);
    labelsPanel.add(engancheLabel);
    labelsPanel.add(plazoLabel);
    labelsPanel.add(radio1);
    bg.add(radio1);
    labelsPanel.add(radio2);
    bg.add(radio2);
    labelsPanel.add(radio3);
    bg.add(radio3);

    JPanel componentesPanel = new JPanel();
    componentesPanel.setLayout(new GridLayout(7, 1));
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
    aceptarButton.setFocusPainted(false);
    aceptarButton.addActionListener(e -> {
      boolean[] confirmBuscar = new boolean[4];
      int aC = 0;
      double bC = 0;
      int cC = 0;
      String str = edadTXT.getText();
      String str2 = engancheTXT.getText();
      String str3 = plazoTXT.getText();

      confirmBuscar[0] = false;
      confirmBuscar[1] = false;
      confirmBuscar[2] = false;
      confirmBuscar[3] = false;

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
            null, "Entrada no válida. Ingresa un número válido para la edad.");
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

      switch (switchBtn) {
      case 0:
        JOptionPane.showMessageDialog(null, "Elija un método de pago.");
        break;
      case 1:
        switchStr = "Tarjeta";
        confirmBuscar[3] = true;
        break;
      case 2:
        switchStr = "Cheque";
        confirmBuscar[3] = true;
        break;
      case 3:
        switchStr = "Efectivo";
        confirmBuscar[3] = true;
        break;
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

      if (confirmBuscar[0] && confirmBuscar[1] && confirmBuscar[2] &&
          confirmBuscar[3]) {
        buscar(carroSeleccionado, carrosVendidos, numCarrosVendidos,
               compradores, numCompradores, array.get(ho));
        datos(w, carroSeleccionado);
        numCompradores++;
        numCarrosVendidos++;
        w++;
        nombreCompradorTXT.setText("");
        edadTXT.setText("");
        engancheTXT.setText("");
        plazoTXT.setText("");
        frame.dispose();
        framePrincipal.requestFocusInWindow();
        return;
      }
    });

    aceptarButton.setBorder(null);
    componentesPanel.add(new Label(""));
    componentesPanel.add(aceptarButton);

    frame.setVisible(true);
    frame.setResizable(false);
    frame.setSize(new Dimension(450, 320));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    return;
  }

  static class BotonListener implements ActionListener {
    private JButton button;
    private JLabel associatedLabel;
    private int ho;
    private JPanel associatedPanel;
    private JFrame framePrincipal;

    public BotonListener(JButton button, JLabel associatedLabel, int ho,
                         JPanel associatedPanel, JFrame framePrincipal) {
      this.button = button;
      this.associatedLabel = associatedLabel;
      this.ho = ho;
      this.associatedPanel = associatedPanel;
      this.framePrincipal = framePrincipal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      boolean confirm = true;

      String[] opciones = {"Realizar venta", "Eliminar auto", "Cancelar"};
      String[] opciones2 = {"Confirmar", "Cancelar"};

      int seleccion = JOptionPane.showOptionDialog(
          null, "Selecciona una opción", "Venta del auto seleccionado",
          JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
          opciones, opciones[0]);

      framePrincipal.requestFocusInWindow();

      switch (seleccion) {
      case 0:
        confirm = false;
        pedirDatos(ho, framePrincipal);
        break;
      case 1:
        int seleccion2 = JOptionPane.showOptionDialog(
            null, "Esta seguro que desea eliminar el auto?", null,
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
            opciones2, opciones[0]);

        if (seleccion2 == 0) {
          confirm = false;
        }
        break;
      case 2:
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
    frame.setVisible(true);
    frame.setBounds(0, 0, screenSize.width, screenSize.height);
    frame.setResizable(false);
  }
}