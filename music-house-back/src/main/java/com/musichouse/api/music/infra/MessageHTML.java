package com.musichouse.api.music.infra;

public class MessageHTML {
    public static final String MESSAGE_HTML = "<!DOCTYPE html>" +
            "<html lang=\"es\">" +
            "<head>" +
            "<meta charset=\"UTF-8\">" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
            "<title>Bienvenido a Music House</title>" +
            "<style>" +
            "body {font-family: Arial, sans-serif; background-color: #f8f8f8; margin: 0; padding: 0;}" +
            ".container {max-width: 600px; margin: 20px auto; padding: 20px; background-color: #fff; border-radius: 5px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);}" +
            ".header {text-align: center; margin-bottom: 20px;}" +
            ".content {margin-bottom: 20px;}" +
            "</style>" +
            "</head>" +
            "<body>" +
            "<div class=\"container\">" +
            "<div class=\"header\">" +
            "<h1 style=\"color: #1e88e5;\">¡Bienvenido a Music House!</h1>" +
            "</div>" +
            "<div class=\"content\">" +
            "<p>Hola, {nombre} {apellido}</p>" +
            "<p>Tu cuenta ha sido creada con éxito. Nos alegra tenerte con nosotros.</p>" +
            "<p>Disfruta de nuestros servicios y si tienes alguna pregunta, no dudes en contactarnos.</p>" +
            "</div>" +
            "<div class=\"footer\">" +
            "<p>Atentamente,<br>Equipo de Music House</p>" +
            "</div>" +
            "</div>" +
            "</body>" +
            "</html>";
}
