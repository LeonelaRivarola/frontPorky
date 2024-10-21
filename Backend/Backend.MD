# Backend readme

JSON PARA PROBAR EL POSTMAN: 

{
    "receta": {
        "nombre": "Pizza Margarita",
        "tiempoPrep": "30 minutos"
    },
    "ingredientes": [
        {
            "materiaPrima_id": 1,
            "cantidad": 500
        },
        {
            "materiaPrima_id": 2,
            "cantidad": 300
        },
        {
            "materiaPrima_id": 3,
            "cantidad": 10
        }
    ],
    "pasos": [
        {
            "numPaso": 1,
            "descripcion": "Mezclar la harina con la levadura."
        },
        {
            "numPaso": 2,
            "descripcion": "Agregar agua y amasar hasta obtener una masa homogénea."
        },
        {
            "numPaso": 3,
            "descripcion": "Dejar reposar la masa durante 1 hora."
        }
    ],
    "recetaxReceta": {
        "recetaBase_id": 5
    }
} 

