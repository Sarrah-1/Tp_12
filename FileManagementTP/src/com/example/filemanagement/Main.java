package com.example.filemanagement;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class Main {

    public static void readFileWithBufferedReader(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

    public static void writeFileWithBufferedWriter(String filePath, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(content);
            bw.newLine();
            bw.write("Ligne ajoutée avec succès.");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du fichier : " + e.getMessage());
        }
    }

    public static void writeWithPrintWriter(String filePath) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(filePath))) {
            pw.println("Rapport des employés");
            pw.printf("Nom: %s, Âge: %d, Poste: %s%n", "sara", 23, "Engineer");
            pw.printf("Nom: %s, Âge: %d, Poste: %s%n", "amine", 24, "prof");
            pw.println("Fin du rapport.");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture : " + e.getMessage());
        }
    }

    public static void readWithFilesApi(String filePath) {
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture : " + e.getMessage());
        }
    }

    public static void writeWithFilesApi(String filePath, String content) {
        try {
            Path path = Paths.get(filePath);
            Files.write(path, content.getBytes());
            System.out.println("Fichier écrit avec succès : " + filePath);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture : " + e.getMessage());
        }
    }

    public static void copyFile(String source, String destination) {
        try {
            Path sourcePath = Paths.get(source);
            Path destPath = Paths.get(destination);
            Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Fichier copié de " + source + " vers " + destination);
        } catch (IOException e) {
            System.err.println("Erreur lors de la copie : " + e.getMessage());
        }
    }

    public static void moveFile(String source, String destination) {
        try {
            Path sourcePath = Paths.get(source);
            Path destPath = Paths.get(destination);
            Files.move(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Fichier déplacé de " + source + " vers " + destination);
        } catch (IOException e) {
            System.err.println("Erreur lors du déplacement : " + e.getMessage());
        }
    }

    public static void deleteFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                Files.delete(path);
                System.out.println("Fichier supprimé : " + filePath);
            } else {
                System.out.println("Le fichier n'existe pas : " + filePath);
            }
        } catch (NoSuchFileException e) {
            System.err.println("Le fichier a été supprimé entre-temps : " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erreur lors de la suppression : " + e.getMessage());
        }
    }

    public static void readBinaryFile(String filePath) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            long totalBytes = 0;
            while ((bytesRead = bis.read(buffer)) != -1) {
                totalBytes += bytesRead;
            }
            System.out.println("Fichier binaire lu : " + filePath);
            System.out.println("Taille totale : " + totalBytes + " octets");
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture binaire : " + e.getMessage());
        }
    }

    public static void writeBinaryFile(String filePath, byte[] data) {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            bos.write(data);
            bos.flush();
            System.out.println("Fichier binaire écrit : " + filePath);
            System.out.println("Taille écrite : " + data.length + " octets");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture binaire : " + e.getMessage());
        }
    }

    public static void getFileMetadata(String filePath) {
        try {
            Path path = Paths.get(filePath);
            BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
            System.out.println("Métadonnées du fichier : " + filePath);
            System.out.println("Taille : " + attrs.size() + " octets");
            System.out.println("Dernière modification : " + attrs.lastModifiedTime().toInstant());
            System.out.println("Est un répertoire : " + attrs.isDirectory());
            System.out.println("Est un fichier : " + attrs.isRegularFile());
        } catch (IOException e) {
            System.err.println("Erreur lors de la récupération des métadonnées : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("TP 12 : Gestion des fichiers en Java");
        System.out.println("Environnement prêt pour les tests.");

        System.out.println("\nLecture avec BufferedReader");
        readFileWithBufferedReader("data/employees.txt");
        readFileWithBufferedReader("data/inexistant.txt");

        System.out.println("\nÉcriture avec BufferedWriter");
        writeFileWithBufferedWriter("data/output.txt", "Contenu de test");
        readFileWithBufferedReader("data/output.txt");

        System.out.println("\nÉcriture avec PrintWriter");
        writeWithPrintWriter("data/report.txt");
        readFileWithBufferedReader("data/report.txt");

        System.out.println("\nAPI java.nio.file.Files");
        readWithFilesApi("data/employees.txt");
        writeWithFilesApi("data/nio_output.txt", "Contenu écrit avec l'API Files");
        readWithFilesApi("data/nio_output.txt");

        System.out.println("\nCopie et déplacement");
        copyFile("data/employees.txt", "data/employees_backup.txt");
        moveFile("data/nio_output.txt", "data/moved_output.txt");

        System.out.println("\nSuppression");
        deleteFile("data/moved_output.txt");
        deleteFile("data/fichier_inexistant.txt");

        System.out.println("\nFichiers binaires");
        readBinaryFile("data/test.jpg");
        try {
            byte[] octets = Files.readAllBytes(Paths.get("data/test.jpg"));
            writeBinaryFile("data/copy_binary.bin", octets);
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture des octets : " + e.getMessage());
        }

        System.out.println("\nMétadonnées");
        getFileMetadata("data/employees.txt");
    }
}
