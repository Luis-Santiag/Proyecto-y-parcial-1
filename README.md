video del proyecto 

https://drive.google.com/file/d/1cXhuMBfpDTIZHD3S4ygTTW15JV8Tqw1r/view?usp=sharing 


Sistema de Procesamiento de Transacciones
Descripción

Este proyecto implementa un sistema de procesamiento de transacciones bancarias utilizando colas de mensajes.
Las transacciones se obtienen desde una API, se distribuyen en colas según el banco destino y posteriormente son procesadas por un consumidor que las envía a otra API para su almacenamiento.

El sistema está desarrollado en Java y utiliza RabbitMQ para la gestión de colas de mensajes.

Arquitectura        
El flujo del sistema es el siguiente:        

API de Transacciones        
        ↓        
     Producer        
        ↓        
     RabbitMQ        
        ↓        
     Consumer                
        ↓        
API Guardar Transacciones        

El Producer obtiene un lote de transacciones desde la API.
Cada transacción se envía a una cola según el campo bancoDestino.
El Consumer escucha las colas.
Cada mensaje recibido se envía mediante un POST a la API de almacenamiento.

Colas Utilizadas

Las transacciones se distribuyen en las siguientes colas:        
-BAC        
-BANRURAL        
-BI        
-GYT        


Tecnologías

-Java        
-RabbitMQ        
-OkHttp        
-Jackson (Java library)        

Ejecución

-Iniciar RabbitMQ.        
-Ejecutar el Producer para obtener y enviar transacciones a las colas.        
-Ejecutar el Consumer para procesar los mensajes y enviarlos a la API.        

