// src/main/java/com/gutendex/console/ConsoleApplicationRunner.java
package com.gutendex.literaturas.console;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleApplicationRunner implements CommandLineRunner {
    
    private final ConsoleMenu consoleMenu;
    
    public ConsoleApplicationRunner(ConsoleMenu consoleMenu) {
        this.consoleMenu = consoleMenu;
    }
    
    @Override
    public void run(String... args) {
        System.out.println("\n".repeat(3));
        System.out.println("🚀 Iniciando Sistema de Literaturas Gutendex...");
        System.out.println("📁 Base de datos: PostgreSQL");
        System.out.println("🌐 API: Gutendex.com");
        System.out.println("⏳ Cargando componentes...");
        
        // Pequeña pausa para mostrar el mensaje de inicio
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Iniciar el menú de consola
        consoleMenu.showMenu();
    }
}