#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <string.h>
#include <ctype.h>
#include <locale.h>
typedef struct {
    int numero_control;
    char* color;
    char* marca;
    char* modelo;
    int monto;
    char nombre_comprador;
    int edad;
    char metodo_pago;
    float monto_enganche;
    float liquidacion;
    char* mensualidad;
} Carro;

void valoresAleatorios(void);
void mostrarDatos(void);
void buscar(Carro* carro_seleccionado, Carro* carros_vendidos, int* num_carros_vendidos, Carro* compradores, int* num_compradores);
void registroAutos(Carro* carro, Carro* carros_vendidos, int* num_carros_vendidos, Carro* compradores, int* num_compradores);
void mostrarDatosSeleccionados(Carro* carro);
void mostrarCarrosVendidos(Carro* carros_vendidos, int num_carros_vendidos);
void datos(int i,Carro* carro);
void mostrarDatosComprador(Carro* compradores, int num_compradores);
int recibo(Carro* compradores, int num_compradores);
int reciboPersonal(Carro* compradores, int num_compradores);
void mostrarInfoPago(Carro* compradores, int num_compradores);
void mostrar_nombre_comprador(Carro* compradores, int num_compradores);

int array[10], meses_adeudo[10], i, l, valores = 9, a = 0, inicio, nuevo_valor, valor_eliminar, posicion;
char eliminar, *color[10] = {"Blanco", "Negro", "Azul", "Rojo", "Verde", "Amarillo", "Plata", "Gris", "Rosa", "Naranja"};
char *marca[10] = {"Audi", "BMW", "MercedesBenz", "Mclaren", "Lamborghini", "Toyota", "Chevrolet", "Nissan", "Mazda", "Renoult"};
char *modelo[10] = {"A1", "A2", "A3", "A4", "Aventador", "A6", "A7", "A8", "A9", "A10"};
int monto[10] = {1087000,1000000,900000,800000,600000,500000,400000,300000,200000,100000};
char nombre_comprador[10][100];
int edad[10];
char metodo_pago[10][100];
float monto_enganche[10];
float liquidacion[10];
float adeudo[10];
float pagoPorMes[10];

void valoresAleatorios(void) {
    srand(time(NULL));
    for (i = 0; i < 10; i++) {
        array[i] = (rand() % 9000) + 1000;
    }
}

void mostrarDatos(void) {
    printf("\n\tInventario:\n\n");
    for (i = 0; i <= valores; i++) {
        printf("\nCarro no.%i:\nColor: %s\nMarca: %s\nModelo: %s\nPrecio: %i\n", array[i], color[i], marca[i], modelo[i], monto[i]);
    }
    printf("\n\n");
}

void mostrarDatosSeleccionados(Carro* carro) {
    printf("\nElemento seleccionado:\n");
    printf("Carro no.%i:\nColor: %s\nMarca: %s\nModelo: %s\nPrecio: %i\n", carro->numero_control, carro->color, carro->marca, carro->modelo, carro->monto);
    printf("\n\n");
}

void mostrarCarrosVendidos(Carro* carros_vendidos, int num_carros_vendidos) {
    printf("\nCarros vendidos hasta el momento:\n");
    for (i = 0; i < num_carros_vendidos; i++) {
        printf("\n\t\tVenta no.%i\n\nComprador: %sCarro no.%i:\nColor: %s\nMarca: %s\nModelo: %s\nPrecio: %i\n", i+1, nombre_comprador[i], carros_vendidos[i].numero_control, carros_vendidos[i].color, carros_vendidos[i].marca, carros_vendidos[i].modelo,carros_vendidos[i].monto);
    }
    printf("\n\n");
}
//
void mostrarDatosComprador(Carro* compradores, int num_compradores){
    setlocale(LC_ALL,"");
    for (i = 0; i < num_compradores; i++){
        printf("\n\nComprador %i: %s",i+1,nombre_comprador[i]);
        printf("Edad: %i",edad[i]);
        printf("\nMétodo de pago: %s",metodo_pago[i]);
        if (monto_enganche[i]==liquidacion[i]){
            printf("Se realizará el pago completo: %.2f",liquidacion[i]);
        }
        else{
            printf("Monto de Enganche: %.2f",monto_enganche[i]);
            printf("\nAdeudo: %.2f",adeudo[i]);
        }        
        printf("\nModelo solicitado: %s %s",compradores[i].marca,compradores[i].modelo);
    }
    printf("\n\n");
}

void mostrarInfoPago(Carro* compradores, int num_compradores){
    setlocale(LC_ALL,"");
    for (i = 0; i < num_compradores; i++){
        printf("\n\nComprador %i: %s",i+1,nombre_comprador[i]);
        printf("\nMétodo de pago: %s",metodo_pago[i]);
        if (monto_enganche[i]==liquidacion[i]){
            printf("Se realizará el pago completo: %.2f",liquidacion[i]);
        }
        else{
            printf("Monto de Enganche: %.2f",monto_enganche[i]);
            printf("\nAdeudo: %.2f",adeudo[i]);
            printf("\nPlazo: %i años \nMensualidad: $%.2f",meses_adeudo[i],pagoPorMes[i]);
        }        
    }
    printf("\n\n");
}

void buscar(Carro* carro_seleccionado ,Carro* carros_vendidos, int* num_carros_vendidos, Carro* compradores, int* num_compradores) {
    setlocale(LC_ALL,"");
    do {
        int valor_eliminar = 0;
        int truE = 0;
        char str[1000];
        char *ptr = str;
        fflush(stdin);
        printf("\nSelecciona un carro a traves de su número de control: ");
        fgets(str,sizeof(str),stdin);

        while (*ptr != '\0') {
            if (isdigit(*ptr)) {
                valor_eliminar = valor_eliminar * 10 + (*ptr - '0');
                ptr++;
            } else {
                ptr++;
            }
        }

        for (i = 0; i <= valores; i++) {
            if (array[i] == valor_eliminar) {
                truE = 1;
                a = 1;
                inicio = i;
                registroAutos(carro_seleccionado, carros_vendidos, num_carros_vendidos,compradores, num_compradores);
                break;
            }
        }
        if (truE == 1) {
            break;
        }
    } while (1);
}
//
void registroAutos(Carro* carro, Carro* carros_vendidos, int* num_carros_vendidos, Carro* compradores, int* num_compradores) {
    carro->numero_control = array[inicio];
    carro->color = color[inicio];
    carro->marca = marca[inicio];
    carro->modelo = modelo[inicio];
    carro->monto = monto[inicio];
    for (i = inicio; i < valores; i++) {
        array[i] = array[i + 1];
        color[i] = color[i + 1];
        marca[i] = marca[i + 1];
        modelo[i] = modelo[i + 1];
        monto[i] = monto[i+1];
    }
    array[valores] = 0;
    color[valores] = NULL;
    marca[valores] = NULL;
    modelo[valores] = NULL;
    monto[valores] = 0;
    valores = valores - 1;

    carros_vendidos[*num_carros_vendidos] = *carro;
    (*num_carros_vendidos)++;
    compradores[*num_compradores] = *carro;
    (*num_compradores)++;
}

void datos(int i,Carro* carro) {
    setlocale(LC_ALL,"");
    float a,b,c;
    getchar();
    printf("\nIngresa el nombre del comprador: ");
    fgets(nombre_comprador[i],sizeof(nombre_comprador),stdin);
    while (1){
        edad[i]=0;
        char str[1000];
        char *ptr = str;
        getchar();
        fflush(stdin);
        printf("\nIngresa la edad del comprador: ");
        fgets(str,sizeof(str),stdin);

        while (*ptr != '\0') {
            if (isdigit(*ptr)) {
                edad[i] = edad[i] * 10 + (*ptr - '0');
                ptr++;
            } else {
                ptr++;
            }
        }

        if(edad[i]<18||edad[i]>100){
            printf("\nEl cliente no puede ser menor de edad o tener más de 100 años");
        }
        else{
            break;
        }
    }
    getchar();
    printf("\nIngresa el método de pago: ");
    fgets(metodo_pago[i],sizeof(metodo_pago),stdin);
    while (1){
        b = 0;
        char str2[1000];
        char *ptr2 = str2;
        double sum_float[100];
        int sum_int[100],x = 0;
        printf("\nDefina el porcentaje de enganche, IVA incluido: $%i",carro->monto);
        printf("\nSolo pude seleccionar porcentajes entre el 20 y 80 porciento, en caso de pago de contado ingrese 100: ");
        fgets(str2,sizeof(str2),stdin);

        while (*ptr2 != '\0') {
            if (isdigit(*ptr2) || (*ptr2 == '-' && isdigit(*(ptr2 + 1)))) {
                if (*ptr2 == '-') {
                    sscanf(ptr2, "%lf", &sum_float[x]);
                } else {
                    sscanf(ptr2, "%lf", &sum_float[x]);
                }
                if (sum_float[x]!=0){
                    x++;
                }
                while (isdigit(*ptr2) || *ptr2 == '.' || *ptr2 == '-') {
                    ptr2++;
                }
            } else {
                ptr2++;
            }
        }
        b = sum_float[0];

        if(b>=20&&b<=80){
            break;
            }
        if(b==100){
            break;
            }
    }
	a=b/100;
    monto_enganche[i]=a*(carro->monto);
    adeudo[i]=(carro->monto)-(monto_enganche[i]);
    liquidacion[i]=carro->monto;
    if(b>=20&&b<=80){
    	do{
            meses_adeudo[i] = 0;
            char str3[1000];
            char *ptr3 = str3;
            getchar();
            fflush(stdin);
            printf("\nA cuántos años se pagará el adeudo?(max. 10 años): ");
            fgets(str3,sizeof(str3),stdin);

            while (*ptr3 != '\0') {
                if (isdigit(*ptr3)) {
                    meses_adeudo[i] = meses_adeudo[i] * 10 + (*ptr3 - '0');
                    ptr3++;
                } else {
                    ptr3++;
                }
            }
            if (meses_adeudo[i]<10&&meses_adeudo[i]>0){
                break;
                }
            
		}while(1);
    	c = meses_adeudo[i] * 12;
		pagoPorMes[i] = adeudo[i]/c;
		printf("\nMensualidad: %.2f\n", pagoPorMes[i]);
	}
    

}

int recibo(Carro* compradores, int num_compradores){
    setlocale(LC_ALL,"");
    FILE *fpt;
    char nombre_del_archivo[1000];
    char filename[1000];
	time_t tiempo = time(NULL);
    struct tm *tm_info = localtime(&tiempo);
	char fecha[20];
   
    getchar();
    nombre_del_archivo[strcspn(nombre_del_archivo, " \n")] = '\0';
    printf("Escribe el nombre del archivo: ");
    fgets(nombre_del_archivo, sizeof(nombre_del_archivo), stdin);
    nombre_del_archivo[strcspn(nombre_del_archivo, " \n")] = '\0';
    getchar();
    nombre_del_archivo[strcspn(nombre_del_archivo, " \n")] = '\0';
    nombre_del_archivo[strcspn(nombre_del_archivo, " \n")] = '\0';
    sprintf(filename, "%s.txt", nombre_del_archivo);
    fpt = fopen(filename, "a+");
    if (fpt == NULL) {
        printf("No se pudo abrir el archivo.\n");
        return 1;
    }
    strftime(fecha, sizeof(fecha), "%d/%m/%Y %H:%M:%S", tm_info);
    fprintf(fpt,"\nFecha de impresion: %s", fecha);
    for (i = 0; i < num_compradores; i++){
	    
        fprintf(fpt,"\n\nComprador %i: %s",i+1,nombre_comprador[i], fecha);
        fprintf(fpt,"Edad: %i",edad[i]);
        fprintf(fpt,"\nMétodo de pago: %s",metodo_pago[i]);
        if (monto_enganche[i]==liquidacion[i]){
            fprintf(fpt,"Se realizará el pago completo: %.2f",liquidacion[i]);
        }
        else{
            fprintf(fpt,"Monto de Enganche: %.2f",monto_enganche[i]);
            fprintf(fpt,"\nAdeudo: %.2f",adeudo[i]);
        }        
        fprintf(fpt,"\nModelo solicitado: %s %s",compradores[i].marca,compradores[i].modelo);
    }
    fprintf(fpt,"\n\n");
    fprintf(fpt,"\n\n");
    
    fclose(fpt);
    printf("Archivo creado. Desea abrirlo en una pestaña nueva? (S/N): ");
    char opcion[2];
    scanf("%s", opcion);
    if (strcasecmp(opcion, "S") == 0 || strcasecmp(opcion, "Si") == 0) {
    	
        char comando[1000];
    	snprintf(comando, sizeof(comando), "start %s.txt", nombre_del_archivo);
    	system(comando);
    
	}
    return 1;
}

int reciboPersonal(Carro* compradores, int num_compradores){
    setlocale(LC_ALL,"");
    FILE *fpt;
    int   d=0;
    char nombre_del_archivo[1000];
    char filename[1000];
    char nombre[10][100];
    char opcion[2];
	time_t tiempo = time(NULL);
    struct tm *tm_info = localtime(&tiempo);
	char fecha[20], tempo[1000];
    getchar();
    nombre_del_archivo[strcspn(nombre_del_archivo, " \n")] = '\0';
    printf("Escribe el nombre del recibo: ");
    fgets(nombre_del_archivo, sizeof(nombre_del_archivo), stdin);
    nombre_del_archivo[strcspn(nombre_del_archivo, " \n")] = '\0';
    getchar();
    nombre_del_archivo[strcspn(nombre_del_archivo, " \n")] = '\0';
    sprintf(filename, "%s.txt", nombre_del_archivo);
	mostrar_nombre_comprador(compradores,num_compradores);
	printf("\nEscriba el nombre del comprador que desea su recibo: ");
    fgets(nombre[0],sizeof(nombre),stdin);
	getchar();
	nombre_del_archivo[strcspn(nombre_del_archivo, " \n")] = '\0';
    fpt = fopen(filename, "a+");
    if (fpt == NULL) {
        printf("No se pudo abrir el archivo.\n");
        return 1;
    }
    strftime(fecha, sizeof(fecha), "%d/%m/%Y %H:%M:%S", tm_info);
    fprintf(fpt,"Fecha de impresion: %s", fecha);
    for (i = 0; i < num_compradores; i++){
	    if(strcmp(nombre_comprador[i],nombre[0])==0){
            fprintf(fpt,"\n\nComprador %i: %s",i+1,nombre_comprador[i]);
            fprintf(fpt,"Edad: %i",edad[i]);
            fprintf(fpt,"\nMétodo de pago: %s",metodo_pago[i]);
            if (monto_enganche[i]==liquidacion[i]){
                fprintf(fpt,"Se realizará el pago completo: %.2f",liquidacion[i]);
            }else{
                fprintf(fpt,"Monto de Enganche: %.2f",monto_enganche[i]);
                fprintf(fpt,"\nAdeudo: %.2f",adeudo[i]);
                fprintf(fpt,"\nPlazo: %i años \nMensualidad: $%.2f",meses_adeudo[i],pagoPorMes[i]);
                fprintf(fpt,"\nSi tarda mas de 3 meses en pagar su mensualidad se le embargará");
            }        
            fprintf(fpt,"\nModelo solicitado: %s %s",compradores[i].marca,compradores[i].modelo);
        }
    }
    fprintf(fpt,"\n\n");
    fprintf(fpt,"\n\n");
    
    fclose(fpt);
    printf("Recibo creado. ñ//Desea abrirlo en una pestaña nueva? (S/N): ");
    scanf("%s", opcion);
    if (strcasecmp(opcion, "S") == 0 || strcasecmp(opcion, "Si") == 0) {
    	
        char comando[1000];
    	snprintf(comando, sizeof(comando), "start %s.txt", nombre_del_archivo);
    	system(comando);
    
	}
	return 1;
}

void mostrar_nombre_comprador(Carro* compradores, int num_compradores){
    setlocale(LC_ALL,"");
    for (i = 0; i < num_compradores; i++){
        printf("\nComprador %i: %s\n",i+1,nombre_comprador[i]);
    }
}
