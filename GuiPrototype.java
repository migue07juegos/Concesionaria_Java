import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;

public class GuiPrototype extends JFrame implements ActionListener{
    
    JButton btn;
    JLabel jLabel1;
    JLabel jLabel2;
    JPasswordField jPasswordField1;
    JTextField jTextField1;
    Border lineBorder = new LineBorder(Color.RED, 1);
    int i = 0;

    public GuiPrototype() {
        setLayout(null);
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jTextField1 = new JTextField();
        jPasswordField1 = new JPasswordField();
        btn = new JButton();

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        jLabel1.setFont(new Font("Tahoma", 1, 18)); 
        jLabel1.setForeground(new Color(255,255,255));
        jLabel1.setText("Usuario:");
        jLabel1.setBounds(40, 60, 150, 50);
        add(jLabel1);

        jTextField1.setBackground(new Color(0, 0, 0));
        jTextField1.setBorder(lineBorder);
        jTextField1.setFont(new Font("Tahoma", 1, 14)); 
        jTextField1.setForeground(new Color(255, 255, 255));
        jTextField1.setBounds(40, 105, 220, 30);
        add(jTextField1);

        jLabel2.setFont(new Font("Tahoma", 1, 18)); 
        jLabel2.setForeground(new Color(255,255,255));
        jLabel2.setText("Contraseña:");
        jLabel2.setBounds(40, 150, 150, 50);
        add(jLabel2);
        
        jPasswordField1.setBackground(new Color(0, 0, 0));
        jPasswordField1.setBorder(lineBorder);
        jPasswordField1.setFont(new Font("Tahoma", 1, 14)); 
        jPasswordField1.setForeground(new Color(255, 255, 255));
        jPasswordField1.setBounds(40, 195, 220, 30);
        add(jPasswordField1);

        btn.setBackground(new Color(0, 0, 0));
        btn.setBorder(lineBorder);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Tahoma", 1, 16)); 
        btn.setForeground(new Color(255, 255, 255));
        btn.setText("Iniciar Sesión");
        btn.setBounds(75, 260, 150, 30);
        add(btn);
        btn.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        // Personalizar la apariencia del JOptionPane
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);

        // Personalizar el aspecto del botón "OK" en todos los JOptionPanes
        int espacioInterno = 3;
        Border buttonBorder = new CompoundBorder(new LineBorder(Color.RED), new EmptyBorder(espacioInterno, espacioInterno, espacioInterno, espacioInterno));
        UIManager.put("Button.border", new BorderUIResource(buttonBorder));
        UIManager.put("Button.background", new Color(0, 0, 0));  // Fondo negro
        UIManager.put("Button.foreground", new Color(255, 255, 255));  // Texto blanco
        UIManager.put("Button.focus", false);  // No mostrar foco

        if (e.getSource() == btn) {
            char[] passwordChars = jPasswordField1.getPassword();
            String password = new String(passwordChars);

            if (password.equals("1234")) {
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
        GuiPrototype frame = new GuiPrototype();
        JFrame newFrame = new JFrame("Concesionaria");

        newFrame.setLayout(new BorderLayout());

        SwingUtilities.invokeLater(() -> {
            relojGui(newFrame);
        });

        JPanel contentPanel = new JPanel();
        JButton boton = new JButton("Hola");
        JButton boton2 = new JButton("nop");

        contentPanel.setLayout(null);

        boton.setBounds(0, 0, 150, 30);
        boton.setFocusPainted(false);
        contentPanel.add(boton);
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent y) {
                frame.i++;
                System.out.println("¡Se hizo clic en el botón: " + frame.i);
            }
        });

        boton2.setBounds(0, 250, 150, 30);
        boton2.setFocusPainted(false);
        contentPanel.add(boton2);
        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent z) {
                System.out.println("¡Se hizo clic en el botón!");
            }
        });

        JScrollPane scrollPane = new JScrollPane(contentPanel);

        // Personalizar el aspecto del JScrollPane
        scrollPane.getViewport().setBackground(Color.BLACK);  // Fondo del área visible
        scrollPane.setBorder(null);  // Borde del JScrollPane

        // Cambiar el color del JScrollBar
        scrollPane.getVerticalScrollBar().setBackground(Color.BLACK);

        // Cambiar el color del thumb (la parte móvil del JScrollBar)
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.RED;
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = super.createDecreaseButton(orientation);
                button.setBackground(Color.BLACK);
                button.setForeground(Color.RED);
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = super.createIncreaseButton(orientation);
                button.setBackground(Color.BLACK);
                button.setForeground(Color.RED);
                return button;
            }
        });
        scrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.RED;
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = super.createDecreaseButton(orientation);
                button.setBackground(Color.BLACK);
                button.setForeground(Color.RED);
                button.setFocusPainted(false);
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = super.createIncreaseButton(orientation);
                button.setBackground(Color.BLACK);
                button.setForeground(Color.RED);
                button.setFocusPainted(false);
                return button;
            }
        });

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        newFrame.add(scrollPane, BorderLayout.CENTER);

        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setResizable(false);
        newFrame.setSize(1920, 1080);
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

        frame.getContentPane().setBackground(Color.BLACK);
        frame.setBounds(xPos, yPos, width, height);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
