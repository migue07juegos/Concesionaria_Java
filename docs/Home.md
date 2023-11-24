<html lang="en">
<head>
    <link rel="stylesheet" href="a.css">
</head>
<body>
    <div id="main-container">
        <div class="menu">
            <!-- Contenido HTML -->
        </div>
        <h1 id="markdownContent">aquí</h1>

        <script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>
        <script>
            // Contenido Markdown
            const markdownContent = `
# Funciones
- Venta y registro de autos
- Métodos de entretenimiento integrados
- Generación de recibos
- Reproductor de música

# Dependencias
### Multiplataforma
- [JRE](https://openjdk.org/)
- [mpv](https://mpv.io/)
- [yt-dlp](https://github.com/yt-dlp/yt-dlp/)

### Linux
- [socat](http://www.dest-unreach.org/socat/)

# Instalación
### Windows
- Descargar archivo [.zip](https://github.com/migue07juegos/Concesionaria_Java/releases/download/v1.0.0/Windows-x64.zip)
- Extraer .zip

### Linux
- Instalar dependencias desde el gestor de paquetes preferido
- Descargar archivo [.tar.gz](https://github.com/migue07juegos/Concesionaria_Java/releases/download/v1.0.0/Linux-x64.tar.gz)
- Extraer .tar.gz

# Ejecución
- \`java -jar Concesionaria.jar\`

# [Guía de usuario](https://migue07juegos.github.io/Concesionaria_Java/Howto.html)
`;

            // Convertir Markdown a HTML
            const htmlContent = marked(markdownContent);

            // Colocar el contenido HTML en el elemento
            document.getElementById('markdownContent').innerHTML = htmlContent;
        </script>
    </div>
</body>
</html>
