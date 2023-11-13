public class otro_archivo {

    public static void main(String[] args) {
        String url = "https://youtu.be/dQw4w9WgXcQ"; 
        reproducirMP3(url);
    }

    public static void reproducirMP3(String url) {
        String os = System.getProperty("os.name").toLowerCase();
        ProcessBuilder processBuilder;
        if (os.contains("win")) {
            processBuilder = new ProcessBuilder("cmd.exe", "/c", "mpv", "--no-video", url);
        } else {
            processBuilder = new ProcessBuilder("mpv", "--no-video", url);
        }
        try {
            Process proceso = processBuilder.start();
            proceso.waitFor();
        } catch (Exception e) {
            System.err.println("Error al reproducir musica");
        }
    }
}
