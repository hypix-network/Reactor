package dev.hypix.dataparser;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MinecraftHashToPath {

    public static void main(String[] args) {
        final String hash = "0ad27c4bc62913a7814bcd1655c8bd5c9d0e1db0"; // Ejemplo de hash (sounds.json) en 1.21.1

        // Convertir el hash a la ruta esperada
        final Path filePath = getMinecraftAssetPath(hash);

        // Mostrar la ruta resultante
        System.out.println("La ruta al archivo es: " + filePath.toString());
    }

    /**
     * Convierte un hash SHA-1 de Minecraft en una ruta dentro de 'assets/objects'.
     *
     * @param hash el hash en formato hexadecimal
     * @return la ruta al archivo correspondiente
     */
    public static Path getMinecraftAssetPath(final String hash) {
        if (hash == null || hash.length() < 2) {
            throw new IllegalArgumentException("El hash debe tener al menos 2 caracteres.");
        }
        final String subDir = hash.substring(0, 2); // Los primeros 2 caracteres del hash
        final String fileName = hash; // El hash completo como nombre del archivo

        // Ruta base al directorio 'assets/objects'
        final String basePath = "~/.minecraft/assets/objects";

        return Paths.get(basePath, subDir, fileName);
    }
}
