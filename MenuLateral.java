import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuLateral extends JFrame {
    private JPanel menuPanel;
    private JButton toggleButton;
    private boolean menuExpandido = false;

    public MenuLateral() {
        // Configuración del JFrame
        super("Menú Lateral");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Crear el botón para expandir/ocultar el menú
        toggleButton = new JButton("Toggle Menu");
        toggleButton.setPreferredSize(new Dimension(50, 15)); // Tamaño del botón igual al menú retraído
        toggleButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toggleMenu();
            }
        });

        // Crear el menú lateral
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(54, 57, 63)); // Color de fondo del menú
        menuPanel.setPreferredSize(new Dimension(50, getHeight())); // Ancho del menú retraído

        // Agregar elementos al menú
        agregarElementoMenu(new ImageIcon("icono1.png"), menuExpandido);
        agregarElementoMenu(new ImageIcon("icono2.png"), menuExpandido);
        agregarElementoMenu(new ImageIcon("icono3.png"), menuExpandido);

        // Configurar el diseño del JFrame principal con BorderLayout
        setLayout(new BorderLayout());
        add(toggleButton, BorderLayout.NORTH); // Agregar el botón en la parte superior
        add(menuPanel, BorderLayout.WEST); // Agregar el menú en la parte izquierda

        // Configurar el contenido principal del JFrame
        JPanel contenidoPanel = new JPanel();
        contenidoPanel.setBackground(Color.WHITE);
        add(contenidoPanel, BorderLayout.CENTER);

        // Configurar eventos de hover para la barra lateral
        menuPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!menuExpandido) {
                    mostrarMenu();
                }
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!menuExpandido) {
                    ocultarMenu();
                }

            }

        });
    }

    private void agregarElementoMenu(Icon icono, boolean menuEx) {
        JButton button = new JButton(icono);
        button.setAlignmentX(Component.LEFT_ALIGNMENT); // Centrar los iconos verticalmente

        if (menuEx) {
            // Si el menú está expandido, agregar etiquetas al lado de los botones
            JLabel label = new JLabel("Opción " + (menuPanel.getComponentCount() + 1));
            label.setForeground(Color.WHITE);
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BorderLayout());
            buttonPanel.add(button, BorderLayout.WEST);
            buttonPanel.add(label, BorderLayout.EAST);
            menuPanel.add(buttonPanel);
        } else {
            // Si el menú está retraído, agregar botones sin etiquetas
            menuPanel.add(button);
        }
    }

    private void toggleMenu() {
        if (menuExpandido) {
            ocultarMenu();
        } else {
            menuExpandido = true;
            mostrarMenu();
        }
    }

    private void mostrarMenu() {
        // Ajustar el ancho del menú al máximo
        menuPanel.setPreferredSize(new Dimension(200, getHeight()));
        menuPanel.removeAll(); // Limpiar los componentes existentes
        agregarElementoMenu(new ImageIcon("icono1.png"), true);
        agregarElementoMenu(new ImageIcon("icono2.png"), true);
        agregarElementoMenu(new ImageIcon("icono3.png"), true);
        menuPanel.revalidate();
        menuPanel.repaint();
    }

    private void ocultarMenu() {
        // Ajustar el ancho del menú al mínimo
        menuPanel.setPreferredSize(new Dimension(50, getHeight()));
        menuExpandido = false;
        menuPanel.removeAll(); // Limpiar los componentes existentes
        agregarElementoMenu(new ImageIcon("icono1.png"), false);
        agregarElementoMenu(new ImageIcon("icono2.png"), false);
        agregarElementoMenu(new ImageIcon("icono3.png"), false);
        menuPanel.revalidate();
        menuPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuLateral().setVisible(true));
    }
}