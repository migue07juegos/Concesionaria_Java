public class Main {
    
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
