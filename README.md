# Prueba Zara Prices

Servicio que obtiene el precio de un producto y una marca en un momento dado.



## API Reference

#### Obtener precio de un producto y una marcar.


#### Request

```http
  POST /api/prices/findPrices
```

| Parameter | Type     | Description                                      |
| :-------- | :------- | :----------------------------------------------- |
| `brandId` | `number` | **Required**. Foreign key de la cadena del grupo. |
| `productId` | `number` | **Required**. Identificador código de producto. |
| `date` | `date` | **Required**. Fecha de aplicación. Formato yyyy-MM-dd'T'HH:mm:ss. |


#### Response


| Field | Type     | Description                                      |
| :-------- | :------- | :----------------------------------------------- |
| `id` | `number` | Identificador de la tarifa de precios aplicable. |
| `brandId` | `number` | Foreign key de la cadena del grupo. |
| `startDate` | `date` | Fecha inicio en el que aplica el precio tarifa indicado. Formato yyyy-MM-dd'T'HH:mm:ss. |
| `endDate` | `date` | Fecha fin en el que aplica el precio tarifa indicado. Formato yyyy-MM-dd'T'HH:mm:ss.  |
| `productId` | `date` | Identificador código de producto. |
| `finalSalePrice` | `date` | Precio final de venta |



## Appendix


Se ha hecho el desarrollo con java 11 entendiendo que es la versión más estable LTS.

Para la carga de datos se ha usado H2 en memoria con una carga inicial utilizando el formato de fechas del ejemplo.

El servicio trabaja con fechas UTC en todo momento.

Es una petición Post en vez de Get porque resulta más fácil añadir parámetros a la consulta en caso de necesidad.

Formato de fecha aceptada como parámetro de entrada "yyyy-MM-dd'T'HH:mm:ss" .

Control básico de excepciones para precio no encontrado, errores de validaciones de parámetros y errores genéricos.

Para los test se ha utilizado Junit 5. Solo se han hecho los test de integración solicitados en la prueba, se podrían haber hecho test independientes por capas(controller, service, dao), o ambos.

Se han incluido las librerías lombok por el ahorro de código en la creación de pojos y mapstruct para el mapeo entre Dto y entidades, para la separación entre capas del servicio.

Se han incluido la librería springdoc-openapi-ui para poder ver la definición del API - http://localhost:8080/swagger-ui/index.html

El pom está configurado para poder ejecutar el aplicativo y los test tanto desde Eclipse (IDE utilizado) como con maven. Habría que instalar y configurar los plugins para lombok y mapstruct en elclipse.
