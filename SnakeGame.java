import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;

class SnakeGame extends JPanel implements ActionListener{
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 800;
    public static final int UNIT_SIZE = 23;
    public static final int GAME_UNITS = (int) (SCREEN_WIDTH/UNIT_SIZE) * (SCREEN_HEIGHT/UNIT_SIZE);
    public static final int HORIZONTAL_UNITS = SCREEN_WIDTH/UNIT_SIZE;
    public static final int VERTICAL_UNITS = SCREEN_HEIGHT/UNIT_SIZE;
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
    
    SnakeGame(JFrame frame){

        frame.setFocusable(true);
		frame.requestFocus();
        frame.addKeyListener(new MyKeyAdapter());
        this.setSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.DARK_GRAY);
    }
	
    public void startGame(){
        snakeSize = INITIAL_SNAKE_SIZE;
        applesEaten = 0;
        for(int i = 0; i < snakeSize; i++){
            snakeX[i] = 0;
            snakeY[i] = 0;
        }
        direction = 'R';
        timer.start();
        newApple();
		randomGameOverMessage = "Perdiste :(";
    }

    public void actionPerformed(ActionEvent ev){
        move();
        checkCollision();
        eatApple();
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g); 
        //Manzana en X y Y
        g.setColor(Color.red);
        g.fillOval(appleX , appleY, UNIT_SIZE, UNIT_SIZE);
        //Cabeza serpiente
        g.setColor(Color.blue);
        g.fillRect(snakeX[0], snakeY[0], UNIT_SIZE, UNIT_SIZE);
        //Cuerpo de serpiente
        for(int i = 1; i < snakeSize; i++){
            g.fillRect(snakeX[i], snakeY[i], UNIT_SIZE, UNIT_SIZE);
        }

        g.setColor(Color.white);
        g.setFont(new Font("Liberation Mono", Font.PLAIN, 25));
        FontMetrics fontSize = g.getFontMetrics();
        int fontX = SCREEN_WIDTH - fontSize.stringWidth("Score: " + applesEaten + "  ") - 10;
        int fontY = fontSize.getHeight();
        g.drawString("Score: " + applesEaten, fontX, fontY);
        
        if(!timer.isRunning()){
            g.setColor(Color.white);
            g.setFont(new Font("Liberation Mono", Font.PLAIN, 58));
            
            String message = randomGameOverMessage;
            fontSize = g.getFontMetrics();
            fontX = (SCREEN_WIDTH - fontSize.stringWidth(message)) / 2 ;
            fontY = (SCREEN_HEIGHT - fontSize.getHeight()) /2;
            g.drawString(message, fontX, fontY);

            g.setFont(new Font("Liberation Mono", Font.PLAIN, 24));
            message = "Presiona R para reiniciar el juego";
            fontSize = g.getFontMetrics();
            fontX = (SCREEN_WIDTH - fontSize.stringWidth(message)) / 2 ;
            fontY = fontY + fontSize.getHeight() + 20;
            g.drawString(message, fontX, fontY);
        }
    }
	
    public void newApple(){
        //numero random entre 0 y 23 * unit size
        int x = random(HORIZONTAL_UNITS) * UNIT_SIZE;
        int y = random(VERTICAL_UNITS) * UNIT_SIZE;
        Point provisional = new Point(x,y);
        Point snakePos = new Point();
        boolean newApplePermission = true;
        for(int i = 0; i < snakeSize; i++){
            snakePos.setLocation(snakeX[i], snakeY[i]);
            if(provisional.equals(snakePos)){
                newApplePermission = false;
            }
        }
        if(newApplePermission){
            appleX = x;
            appleY = y;
        }else{
            newApple();
        }
    }

    public void checkCollision(){
        if(snakeX[0] >= (SCREEN_WIDTH) || snakeX[0] < 0 || snakeY[0] >= (SCREEN_HEIGHT-20) || snakeY[0] < 0){
            gameOver();
        }
        for(int i = 1; i < snakeSize; i++){
            if((snakeX[0] == snakeX[i]) && (snakeY[0] == snakeY[i])){
                gameOver();
            }
        }
    }
    
	public void eatApple(){
        if(snakeX[0] == appleX && snakeY[0] == appleY){
            snakeSize++;
            applesEaten++;
            newApple();
        }
    }
    
	public void move(){
        for(int i = snakeSize; i > 0; i--){
            snakeX[i] = snakeX[i-1]; 
            snakeY[i] = snakeY[i-1]; 
        }
        switch(direction){
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
    
	public void gameOver(){
        timer.stop();
    }

    public int random(int range){
        //returns an int from 0 to range
        return (int)(Math.random() * range);
    }
    
    class MyKeyAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent k){
            
            switch(k.getKeyCode()){
                case (KeyEvent.VK_5) :
                    snakeSize++;
                    applesEaten++;
                break;
                case (KeyEvent.VK_DOWN):
                    if(direction != 'U' && keyInput == false){
                        direction = 'D';
                        keyInput = true;
                    }
                    break;
                case (KeyEvent.VK_UP):
                    if(direction != 'D' && !keyInput){
                        direction = 'U';
                        keyInput = true;
                    }
                    break;
                case (KeyEvent.VK_LEFT):
                    if(direction != 'R' && keyInput == false){
                        direction = 'L';
                        keyInput = true;
                    }
                    break;
                case (KeyEvent.VK_RIGHT):
                    if(direction != 'L' && keyInput == false){
                        direction = 'R';
                        keyInput = true;
                    }
                    break;
                case (KeyEvent.VK_R):
                    if(!timer.isRunning()){
                        startGame();
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
					break;
            }
        }
    }

    public static class InnerSnakeGame extends JFrame {
        SnakeGame SnakeGame = new SnakeGame(this);
        InnerSnakeGame(){
            this.setTitle("Snake"); 
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pack();
            this.setSize(new Dimension(SCREEN_WIDTH + 20,SCREEN_HEIGHT + 20));
            this.setResizable(false);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            this.getContentPane().setBackground(Color.DARK_GRAY);
            
            this.add(SnakeGame);
            SnakeGame.startGame();
        } 
    }

    public static void main(String[] args) {
        new InnerSnakeGame();
    }
    
}