package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TraverseFolder {

    static final String FILE_EXTENSION = "JPG";

    public static void main(String[] args) {
        System.out.println("开始：");
        traverseFolder("D:\\test\\src");

    }


    public static void traverseFolder(String parentFolderPath) {
        File parentFolder = new File(parentFolderPath);
        File[] childrenFiles = null;
        if (parentFolder.isFile()) {
            if (isTextFile(parentFolder)) {
                try {
                    System.out.println("File Location: " + parentFolder.getAbsolutePath());
                    printFile(parentFolder);
                    System.out.println("");
                } catch (IOException ioe) {
                    System.exit(1);
                }
            } else {
                System.out.println("没有jpg文件");
                return;
            }
        } else {
            childrenFiles = parentFolder.listFiles();
            if (childrenFiles.length == 0) {

                return;
            }
            if (!containsDirectory(childrenFiles)) {
                for (File file : childrenFiles) {
                    if (isTextFile(file)) {
                        try {
                            System.out.println("File Location: " + file.getAbsolutePath());
                            printFile(file);
                            System.out.println("");
                        } catch (IOException ioe) {
                            System.exit(1);
                        }
                    }
                }
            } else {
                for (File file : childrenFiles) {
                    traverseFolder(file.getAbsolutePath());
                }
            }
        }
    }

    private static boolean containsDirectory(File[] files) {
        for (File f : files) {
            if (f.isDirectory()) {
                return true;
            }
        }

        return false;
    }

    private static void printFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while (line != null) {
            System.out.println(line);
            line = reader.readLine();
        }
    }

    private static boolean isTextFile(File file) {
        String fileName = file.getName();
        int i = fileName.lastIndexOf('.');
        if (i > 0 && fileName.substring(i + 1).toUpperCase().equals(FILE_EXTENSION)) {
            return true;
        }
        return false;
    }
}
