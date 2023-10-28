import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;

public class GuiPrototype extends JFrame implements ActionListener{
    StringBuilder r = new StringBuilder();
    boolean pass = false;
    boolean pass2 = true;
    private JButton btn;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPasswordField jPasswordField1;
    private JTextField jTextField1;

    public GuiPrototype() {
        setLayout(null);
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jTextField1 = new JTextField();
        jPasswordField1 = new JPasswordField();
        btn = new JButton();

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        jLabel1.setFont(new Font("Tahoma", 1, 18)); 
        jLabel1.setForeground(new Color(0, 0, 0));
        jLabel1.setText("Usuario:");
        jLabel1.setBounds(60, 125, 150, 50);
        add(jLabel1);

        jLabel2.setFont(new Font("Tahoma", 1, 18)); 
        jLabel2.setForeground(new Color(0, 0, 0));
        jLabel2.setText("Password:");
        jLabel2.setBounds(60, 195, 150, 50);
        add(jLabel2);

        jTextField1.setBackground(new Color(0, 0, 51));
        jTextField1.setFont(new Font("Tahoma", 1, 14)); 
        jTextField1.setForeground(new Color(255, 255, 255));
        jTextField1.setBounds(205, 140, 220, 30);
        add(jTextField1);

        jPasswordField1.setBackground(new Color(0, 0, 51));
        jPasswordField1.setFont(new Font("Tahoma", 1, 14)); 
        jPasswordField1.setForeground(new Color(255, 255, 255));
        jPasswordField1.setBounds(205, 200, 220, 30);
        add(jPasswordField1);

        btn.setBackground(new Color(0, 0, 51));
        btn.setFont(new Font("Tahoma", 1, 16)); 
        btn.setForeground(new Color(255, 255, 255));
        btn.setText("Iniciar Sesión");
        btn.setBounds(170, 260, 150, 30);
        add(btn);
        btn.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn) {
            char[] passwordChars = jPasswordField1.getPassword();
            String password = new String(passwordChars);

            if (password.equals("1234")) {
                pass = true;
                openNewWindow();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void relojGui(JFrame frame) {
        JPanel panel = new JPanel();
        
        panel.setLayout(new BorderLayout());
        frame.setLayout(new BorderLayout());

        JLabel label = new JLabel();
        label.setFont(new Font("Arial", Font.PLAIN, 50));

        panel.add(label, BorderLayout.EAST);
        frame.add(panel, BorderLayout.NORTH);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e) {
                LocalTime currentTime = LocalTime.now();
                String time = String.format("%02d:%02d:%02d",
                        currentTime.getHour(),
                        currentTime.getMinute(),
                        currentTime.getSecond());
                label.setText(time);
            }
        });

        timer.start();
    }

    private static void openNewWindow() {
        JFrame newFrame = new JFrame("Concesionaria");
        newFrame.setSize(1920, 1080);

        SwingUtilities.invokeLater(() -> {
            relojGui(newFrame);
        });

        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setResizable(false);
        newFrame.setVisible(true);
    }

    public static void main(String args[]) {
        
        GuiPrototype frame = new GuiPrototype();
        int width = 500;
        int height = 500;
        
        int screenWidth = 1920;
        int screenHeight = 1080;
        int xPos = (screenWidth -  width) / 2;
        int yPos = (screenHeight - height) / 2;

        frame.setBounds(xPos, yPos, width, height);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
