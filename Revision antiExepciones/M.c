#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <locale.h>
#include <windows.h>
#include "F.h"

int main() {
    setlocale(LC_ALL,"");
    int TRUe=1, A=0,w=0;
    char x;
    Carro carro_seleccionado;
    Carro carros_vendidos[10];
    Carro compradores[10];
    int num_compradores = 0;
    int num_carros_vendidos = 0;
    

    do {
        int Salir = 0;
        char str[1000];
        char *ptr = str;
        fflush(stdin);
        printf("\n\t\t Menú: \n");
        printf("\n  1.-Selecciona la venta que se realizará");
        printf("\n  2.-Información de los compradores");
        printf("\n  3.-Ventas realizadas");
        printf("\n  4.-Consultar el inventario");
        printf("\n  5.-Información de pago");
        printf("\n  6.-Recibo personal");
        printf("\n  7.-Salir\n");
        printf("\nSelecciona una opción: ");
        fgets(str,sizeof(str),stdin);
        while (*ptr != '\0') {
            if (isdigit(*ptr)) {
                Salir = Salir * 10 + (*ptr - '0');
                ptr++;
            } else {
                ptr++;
            }
        }
        printf("%i",Salir);
        switch (Salir) {
            case 1:
                system("cls");
                valoresAleatorios();
                printf("\t\tAdvertencia el carro seleccionado se eliminará del inventario\n\t\tLa acción no se podra revertir hasta finalizar el programa\n");
                mostrarDatos();
                buscar(&carro_seleccionado, carros_vendidos, &num_carros_vendidos,compradores,&num_compradores);
                system("cls");
                mostrarDatosSeleccionados(&carro_seleccionado);
                datos(w,&carro_seleccionado);
                w++;
                system("PAUSE");
                break;
            case 2:
                system("cls");
                mostrarDatosComprador(compradores, num_compradores);
                break;
            case 3:
                system("cls");
                mostrarCarrosVendidos(carros_vendidos, num_carros_vendidos);
                break;
            case 4:
                system("cls");
                mostrarDatos();
                break;
            case 5:
                system("cls");
                mostrarInfoPago(compradores, num_compradores);
                break;
            case 6:
                system("cls");
                reciboPersonal(compradores, num_compradores);
                getchar();
                break;
            case 7:
                system("cls");
                getchar();
                printf("Desea imprimir la información de los compradores? (S/N): ");
                x=getchar();
                if(x=='S'||x=='s'){
                    recibo(compradores, num_compradores);
                    A = TRUe;
                }
                else if(x=='N'||x=='n'){
                    A = TRUe;   
                }
                break;
            default:
                printf("\n\n\t\tERROR 404");
                Beep(48000,500);
                break;
        }
        if (A == TRUe) {
            break;
        }
    } while (1);

    return 0;
}