
# Turnos Rotativos API

A continuación se detalla de que forma realizar las request para acceder a las distintas funcionalidades de la API.

## Get All Empleados
```http
  GET    http://localhost:8080/empleados
```
| Descripción                |
| :------------------------- | 
| Devuelve una colección de Empleados |

Ejemplo de la devolución:

```json
[
  {
    "id": 1,
    "nroDocumentro": 30415654,
    "nombre": "German",
    "apellido": "Zotella",
    "email": "gzotella@gmail.com",
    "fechaNacimiento": "1998-08-06",
    "fechaIngreso": "2019-06-04",
    "fechaCreacion": "2023-02-22"
  },
  {
    "id": 2,
    "nroDocumentro": 3565454321,
    "nombre": "Jorge",
    "apellido": "Rolon",
    "email": "jrolon@gmail.com",
    "fechaNacimiento": "1998-08-06",
    "fechaIngreso": "2019-06-04",
    "fechaCreacion": "2023-02-22"
  }


```

## Get Empleado

```http
  GET   http://localhost:8080/empleados/{id}
```

| Parametro | Tipo     | Descripción                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | Devuelve una instancia del Empleado indicado por ID |

Ejemplo de la devolución:

```json

  {
    "id": 1,
    "nroDocumentro": 30415654,
    "nombre": "German",
    "apellido": "Zotella",
    "email": "gzotella@gmail.com",
    "fechaNacimiento": "1998-08-06",
    "fechaIngreso": "2019-06-04",
    "fechaCreacion": "2023-02-22"
  }

```

## Crear Empleado

```http
  POST    http://localhost:8080/empleados
```

| Descripción                       |
| :-------------------------------- |
| Se debe enviar el empleado en formato JSON. Este se crea y se obtiene un Response con el Empleado ya creado |

Ejemplo del Body:

```json
  {
    "nroDocumentro": 30415654,
    "nombre": "German",
    "apellido": "Zotella",
    "email": "gzotella@gmail.com",
    "fechaNacimiento": "1998-08-06",
    "fechaIngreso": "2019-06-04"
  }

```
## Eliminar Empleado

```http
  Delete    http://localhost:8080/empleados/{id}
```

| Parametro | Tipo     | Descripción                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | Elimina el Empleado indicado por ID, Devuelve un 204 en caso de exito y 404 si no lo encuentra |

## Get All Conceptos
```http
  GET    http://localhost:8080/conceptos
```
| Descripción                |
| :------------------------- | 
| Devuelve una colección de Conceptos (Cuando los valores de hs_minimo y hs_maximo poseen el valor null,
no se devuelve esa instancia). |

Ejemplo de la devolución:

```json
[
  {
    "id": 1,
    "nombre": "Turno Normal",
    "hsMinimo": 6,
    "hsMaximo": 8,
    "laborable": true
  },
  {
    "id": 2,
    "nombre": "Turno Extra",
    "hsMinimo": 2,
    "hsMaximo": 6,
    "laborable": true
  } 
]
  
```



