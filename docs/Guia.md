# Guía

## Menú lateral
El botón superor izquierdo despliega un menú lateral con más botones.


### Realizar Venta
La página de venta consiste en una descripción del auto y de su imágen.
Al hacer click en la imágen se accede al formulario de auto.
Primero se puede selecciónar si se quiere vender o eliminar el auto, o cancelar la operación.
#### Venta
Primero hay que ingresar unos datos para realizar la venta, primero el nombre del comprador, su edad, el enganche que va a dar
en porcentaje (20-80) o 100 en caso de pago de contado, el plazo en años en caso de que sea financiado, y seleccionar
el método de pago (tarjeta, cheque o efectivo).
Al darle "Aceptar" se realiza la venta, muestra la mensualidad a pagar cada mes y quita el auto del inventario.
#### Eliminar
Elimina el auto del inventario.

#### Cancelar
Cancela la operación

### Información Compradores
Muestra la información de los compradores:
 - Nombre
 - Edad
 - Método de pago
 - Monto de enganche
 - Adeudo
 - Modelo de auto

### Ventas Realizadas
Muestra la información sobre las ventas:
 - Nombre del comprador
 - Número de venta
 - Número del auto
 - Color del auto
 - Marca del auto
 - Model del auto
 - Precio del auto

### Información de pago
Muestra la información de pago de una venta
 - Nombre del comprador
 - Método de pago
 - Monto de enganche
 - Adeudo
 - Plazo
 - Costo de mensualidad

### Recibo Personal
Muestra los nombres de los compradores y un botón para realizar el recibo.
Al presionar el botón se muestra una ventana con entradas de texto.
La primera se debe escribir el nombre del recibo, en la segunda el nombre del comprador del cual se requiere el recibo.
Al presionar "Aceptar" se genera el recibo con los datos de la venta y el comprador seleccionados en la carpeta donde se esté ejecutando el programa como un archivo de texto (.txt).

### Recibo general
Muestra un botón que al ser presionado muestra una ventana con una entrada de texto y un botón de "Aceptar".
En la entrada de texto se debe escribir el nombre del recibo.
Al presionar "Aceptar" se genera el recibo con los datos de todas las ventas realizadas en la carpeta donde se esté ejecutando el programa como un archivo de texto (.txt).

### Reproductor
Muestra la ventana del reproductor de música.
En la entrada de texto se debe escribir la ruta de la canción deseada en caso de ser un archivo descargado o la URL de la canción si se desea reproducir desde Youtube.
Después de escribir, se debe presionar el botón de agregar, que agrega la canción a la lista de reproducción y si es la primera canción en ser agregada, se inicia la reproducción.
El botón de pausa detiene la reproducción hasta ser presionado nuevamente, no borra las canciones agregadas.
El botón de siguiente salta a la canción siguiente en la lista. En caso de no haber una canción siguiente, el botón no hace nada.
El botón de anterior se regresa a la canción previa en la lista. En caso de no haber una canción anterior, el botón no hace nada.
El botón de salir detiene la reproducción y elimina la lista de reproducción.
El deslizador controla el volumen del reproductor, entre mayor sea el valor en el deslizador, mayor será el volumen.

### Agregar productor
Muestra un botón para agregar un auto.
Al presionar el botón se muestran 4 cuadros de texto, donde se debe ingresar:
 - Marca del auto
 - Color del auto
 - Modelo del auto
 - Precio del auto
Se muestra un botón de "Imagen" que al presionarlo se abre un explorador de archivos para seleccionar la imágen del auto a agregar.
Al presionar en "Aceptar" se agrega el auto al inventario y aparece en la ventana de "Realizar venta".