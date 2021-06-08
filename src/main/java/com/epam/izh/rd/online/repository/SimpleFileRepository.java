package com.epam.izh.rd.online.repository;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {

        File fileCount = new File("src/main/resources/" + path);
        int count = 0;
        if (fileCount.isDirectory()) {
            for (File file : Objects.requireNonNull(fileCount.listFiles())) {
                count += countFilesInDirectory(path + "/" + file.getName());
            }
        } else {
            count++;
        }
        return count;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        File fileCount = new File("src/main/resources/" + path);
        int count = 0;
        if (fileCount.isDirectory()) {
            for (File file : Objects.requireNonNull(fileCount.listFiles())) {
                count += countDirsInDirectory(path + "/" + file.getName());
            }
            count++;
        }
        return count;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        File source = new File(from);
        File dest = new File(to);
        try {
            Files.copy(source.toPath(), dest.toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    @Override
    public boolean createFile(String path, String name) {
        File file = new File(Objects.requireNonNull(getClass().getResource("/")).getPath() + "/" + path + "/" + name);
        try {
            return file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        String stringFile;
        try (BufferedReader read = new BufferedReader(new FileReader("src/main/resources/" + fileName))) {
            stringFile = read.readLine();
        } catch (IOException e) {
            return e.getMessage();
        }
        return stringFile;
    }
}
