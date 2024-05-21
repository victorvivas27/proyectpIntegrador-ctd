# integrador-ctd

app de reserva de instrumentos musicales

# DTOs de Entrada

# Clases DTO de Entrada para Usuario, Dirección, Teléfono, Categoría, Temática, Instrumento e Imágenes

## CategoryDtoEntrance

Esta clase DTO representa los datos de entrada para una categoría en el sistema.

### Atributos

- **categoryName**: `String`
    - **Requerido**: Sí
    - **Longitud**: máximo 100 caracteres.
    - **Descripción**: Nombre de la categoría.

- **description**: `String`
    - **Longitud**: máximo 255 caracteres.
    - **Descripción**: Descripción de la categoría.

---

## ThemeDtoEntrance

Esta clase DTO representa los datos de entrada para una temática en el sistema.

### Atributos

- **themeName**: `String`
    - **Requerido**: Sí
    - **Longitud**: máximo 100 caracteres.
    - **Descripción**: Nombre de la temática.

- **description**: `String`
    - **Longitud**: máximo 255 caracteres.
    - **Descripción**: Descripción de la temática.

---

## InstrumentDtoEntrance

Esta clase DTO representa los datos de entrada para un instrumento en el sistema.

### Atributos

- **name**: `String`
    - **Requerido**: Sí
    - **Longitud**: máximo 100 caracteres.
    - **Descripción**: Nombre del instrumento.

- **description**: `String`
    - **Longitud**: máximo 255 caracteres.
    - **Descripción**: Descripción del instrumento.

- **rentalPrice**: `BigDecimal`
    - **Requerido**: Sí
    - **Restricciones**: Debe ser positivo o cero.
    - **Descripción**: Precio de alquiler del instrumento.

- **weight**: `BigDecimal`
    - **Requerido**: Sí
    - **Restricciones**: Debe ser positivo o cero.
    - **Descripción**: Peso del instrumento.

- **measures**: `String`
    - **Requerido**: Sí
    - **Descripción**: Medidas del instrumento.

- **idCategory**: `Long`
    - **Requerido**: Sí
    - **Descripción**: ID de la categoría a la que pertenece el instrumento.

- **idTheme**: `Long`
    - **Requerido**: Sí
    - **Descripción**: ID de la temática a la que pertenece el instrumento.

- **imageUrls**: `List<String>`
    - **Requerido**: Sí
    - **Descripción**: Lista de URLs de las imágenes del instrumento.

---

## ImageUrlsDtoEntrance

Esta clase DTO representa los datos de entrada para las URLs de las imágenes de un instrumento en el sistema.

### Atributos

- **idInstrument**: `Long`
    - **Requerido**: Sí
    - **Descripción**: ID del instrumento al que pertenece la imagen.

- **imageUrl**: `String`
    - **Requerido**: Sí
    - **Longitud**: máximo 255 caracteres.
    - **Descripción**: URL de la imagen.

---

# CharacteristicDtoEntrance

Esta clase DTO representa los datos de entrada para las características de un instrumento musical en el sistema.

## Atributos

- **material**: `String`
    - **Requerido**: Sí
    - **Longitud**: máximo 50 caracteres.
    - **Descripción**: Material del instrumento.

- **frets**: `Long`
    - **Requerido**: Sí
    - **Valor mínimo**: 0.
    - **Descripción**: Número de trastes del instrumento.

- **scaleLength**: `String`
    - **Requerido**: Sí
    - **Longitud**: máximo 50 caracteres.
    - **Descripción**: Longitud de la escala del instrumento.

- **numberOfStrings**: `Long`
    - **Requerido**: Sí
    - **Valor mínimo**: 0.
    - **Descripción**: Número de cuerdas del instrumento.

- **typeOfStrings**: `String`
    - **Requerido**: Sí
    - **Longitud**: máximo 50 caracteres.
    - **Descripción**: Tipo de cuerdas del instrumento.

- **originCountry**: `String`
    - **Requerido**: Sí
    - **Longitud**: máximo 50 caracteres.
    - **Descripción**: País de origen del instrumento.

## UserDtoEntrance

Esta clase representa los datos de entrada para un usuario.

### Atributos:

- **name**: String
    - Requerido: Sí
    - Longitud: entre 2 y 50 caracteres
    - Restricciones: solo letras, acentos y espacios

- **lastName**: String
    - Requerido: Sí
    - Longitud: entre 2 y 50 caracteres
    - Restricciones: solo letras, acentos y espacios

- **email**: String
    - Requerido: Sí
    - Formato: email válido

- **password**: String
    - Requerido: Sí
    - Longitud mínima: 6 caracteres

- **addresses**: List<AddressDtoEntrance>
    - Requerido: Sí
    - Descripción: lista de direcciones asociadas al usuario

- **phones**: List<PhoneDtoEntrance>
    - Requerido: Sí
    - Descripción: lista de números de teléfono asociados al usuario

## UserAdminDtoEntrance

Esta clase representa los datos de entrada para un usuario administrador.

### Atributos:

- **name**: String
    - Requerido: Sí
    - Longitud: entre 2 y 50 caracteres

- **lastName**: String
    - Requerido: Sí
    - Longitud: entre 2 y 50 caracteres

- **email**: String
    - Requerido: Sí
    - Formato: email válido

- **password**: String
    - Requerido: Sí
    - Longitud mínima: 6 caracteres

## AddressDtoEntrance

Esta clase representa los datos de entrada para una dirección.

### Atributos:

- **street**: String
    - Requerido: Sí
    - Longitud: entre 2 y 100 caracteres
    - Restricciones: solo letras, acentos y espacios

- **number**: Long
    - Requerido: Sí
    - Restricciones: número positivo o cero

- **city**: String
    - Requerido: Sí
    - Longitud: entre 2 y 100 caracteres
    - Restricciones: solo letras, acentos y espacios

- **state**: String
    - Requerido: Sí
    - Longitud: entre 2 y 100 caracteres
    - Restricciones: solo letras, acentos y espacios

- **country**: String
    - Requerido: Sí
    - Longitud: entre 2 y 100 caracteres
    - Restricciones: solo letras, acentos y espacios

## AddressAddDtoEntrance

Esta clase representa los datos de entrada para agregar una dirección a un usuario.

### Atributos:

- **idUser**: Long
    - Requerido: Sí
    - Descripción: ID del usuario al que se agregará la dirección

- **street**: String
    - Requerido: Sí
    - Longitud: entre 2 y 100 caracteres
    - Restricciones: solo letras, acentos y espacios

- **number**: Long
    - Requerido: Sí
    - Restricciones: número positivo o cero

- **city**: String
    - Requerido: Sí
    - Longitud: entre 2 y 100 caracteres
    - Restricciones: solo letras, acentos y espacios

- **state**: String
    - Requerido: Sí
    - Longitud: entre 2 y 100 caracteres
    - Restricciones: solo letras, acentos y espacios

- **country**: String
    - Requerido: Sí
    - Longitud: entre 2 y 100 caracteres
    - Restricciones: solo letras, acentos y espacios

## PhoneDtoEntrance

Esta clase representa los datos de entrada para un número de teléfono.

### Atributos:

- **phoneNumber**: String
    - Requerido: Sí
    - Formato: número de teléfono válido

## PhoneAddDtoEntrance

Esta clase representa los datos de entrada para agregar un número de teléfono a un usuario.

### Atributos:

- **idUser**: Long
    - Requerido: Sí
    - Descripción: ID del usuario al que se agregará el número de teléfono

- **phoneNumber**: String
    - Requerido: Sí
    - Formato: número de teléfono válido