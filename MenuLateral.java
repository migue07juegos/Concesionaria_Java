import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuLateral extends JFrame {
    private JPanel menuPanel;

    public MenuLateral() {
        // Configuración del JFrame
        super("Menú Lateral");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Crear el menú lateral
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(54, 57, 63)); // Color de fondo del menú
        menuPanel.setPreferredSize(new Dimension(200, getHeight())); // Ancho del menú

        // Agregar elementos al menú
        agregarElementoMenu(new ImageIcon("icono1.png"));
        agregarElementoMenu(new ImageIcon("icono2.png"));
        agregarElementoMenu(new ImageIcon("icono3.png"));

        // Configurar el evento de hover
        menuPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mostrarMenuCompleto();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ocultarMenuPequeno();
            }
        });

        // Configurar el evento de clic en el menú pequeño
        menuPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Lógica para manejar el clic en el menú pequeño
                System.out.println("Menú pequeño clicado");
            }
        });

        // Configurar el diseño del JFrame principal
        setLayout(new BorderLayout());
        add(menuPanel, BorderLayout.WEST);

        // Configurar el contenido principal del JFrame
        JPanel contenidoPanel = new JPanel();
        contenidoPanel.setBackground(Color.WHITE);
        add(contenidoPanel, BorderLayout.CENTER);
    }

    private void agregarElementoMenu(Icon icono) {
        JLabel label = new JLabel(icono);
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar los iconos verticalmente
        menuPanel.add(label);
    }

    private void mostrarMenuCompleto() {
        // Ajustar el ancho del menú al máximo
        menuPanel.setPreferredSize(new Dimension(200, getHeight()));
        menuPanel.revalidate();
        menuPanel.repaint();
    }

    private void ocultarMenuPequeno() {
        // Ajustar el ancho del menú al mínimo
        menuPanel.setPreferredSize(new Dimension(50, getHeight()));
        menuPanel.revalidate();
        menuPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuLateral().setVisible(true));
    }
}
