// src/main/java/com/gutendex/util/ConsoleMessages.java
package com.gutendex.literaturas.util;

public class ConsoleMessages {
    
    public static final String WELCOME_MESSAGE = 
            "\n📚 Bienvenido al Sistema de Gestión de Literaturas\n" +
            "   Este sistema utiliza la API de Gutendex.com para buscar\n" +
            "   y almacenar información sobre libros de dominio público.\n";
    
    public static final String INSTRUCTIONS = 
            "\n💡 INSTRUCCIONES:\n" +
            "1. Primero use la opción 1 para buscar libros por título\n" +
            "2. Los libros encontrados se guardarán automáticamente\n" +
            "3. Luego puede listar, filtrar y consultar los datos\n";
    
    public static final String SEPARATOR = "=".repeat(60);
    
    public static String formatBookCount(int count) {
        return String.format("📊 Se encontraron %d libro(s)", count);
    }
    
    public static String formatAuthorCount(int count) {
        return String.format("👥 Se encontraron %d autor(es)", count);
    }
}