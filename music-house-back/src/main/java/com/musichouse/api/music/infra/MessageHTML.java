package com.musichouse.api.music.infra;

public class MessageHTML {
    public static final String MESSAGE_HTML = "<!DOCTYPE html>" +
            "<html>" +
            "<head>" +
            "<style>" +
            "body {font-family: Arial, sans-serif;}" +
            ".container {padding: 20px; text-align: center;}" +
            ".content {margin: 20px 0;}" +
            "</style>" +
            "</head>" +
            "<body>" +
            "<div class=\"container\">" +
            "<h1>¡Bienvenido a Music House!</h1>" +
            "<div class=\"content\">" +
            "<p>Hola, {0} {1} </p>" +  // Marcador de posición para el nombre del usuario
            "<p>Tu cuenta ha sido creada con éxito. Nos alegra tenerte con nosotros.</p>" +
            "<p>Disfruta de nuestros servicios y si tienes alguna pregunta, no dudes en contactarnos.</p>" +
            "</div>" +
            "</div>" +
            "</body>" +
            "</html>";
}
