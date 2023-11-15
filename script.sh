#!/bin/bash

# Cambia la ventana activa
echo "key alt+Tab" | dotool
sleep 1

# Bucle para realizar la acción 10 veces
for ((i=0; i<10; i++)); do
    # Simula clic y presiona Enter en las coordenadas específicas
    echo "mousemove 1691 273 click 1" | dotool
    echo "type 'Miguel'" | dotool
    echo "key Return" | dotool

    echo "mousemove 429 58 click 1" | dotool
    echo "type 'Miguel'" | dotool

    echo "mousemove 429 114 click 1" | dotool
    echo "type '52'" | dotool

    echo "mousemove 429 154 click 1" | dotool
    echo "type '25'" | dotool

    echo "mousemove 429 212 click 1" | dotool
    echo "type '5'" | dotool

    echo "mousemove 18 262 click 1" | dotool
    echo "mousemove 429 315 click 1" | dotool
    echo "key Return" | dotool
done
