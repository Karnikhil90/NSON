package src.utility;
/**
 * =============================================================================
 * FileAccess.java
 * =============================================================================
 * 
 * A lightweight utility class for handling basic text-based file operations
 * such as reading, writing, and saving `.txt` files.
 * 
 * PURPOSE:
 * --------
 * - Acts as a foundation for file-based input/output operations.
 * - Can be extended in future to support other formats like .csv or .xml.
 * - Helpful in projects where file-based persistence is required temporarily 
 *   or for configuration, logging, or simple data storage.
 * 
 * SUPPORTED FEATURES:
 * -------------------
 * ✅ Create a new file if it doesn't exist  
 * ✅ Check if file exists  
 * ✅ Read all lines or a specific line  
 * ✅ Write to file (append or overwrite)  
 * ✅ Save data (alias for write)  
 * ✅ Gracefully handle exceptions and print debug messages  
 * 
 * SUGGESTED EXTENSIONS:
 * ---------------------
 * ➕ deleteLine(int lineNumber)   — Remove a specific line  
 * ➕ clearFile()                  — Erase all contents of the file  
 * ➕ countLines()                 — Return number of lines  
 * ➕ updateLine(int lineNumber, String newData) — Modify a specific line  
 * ➕ Support .csv, .xml parsing/writing using format-specific methods  
 * 
 * EXAMPLE USAGE:
 * --------------
 * FileAccess fa = new FileAccess("data.txt");
 * fa.createFile();
 * fa.write("Hello World");
 * String line = fa.read(0);
 * 
 * =============================================================================
 */


import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileAccess {
    private String filepath;
    private File file;

    public FileAccess(String filepath) {
        this.filepath = filepath;
        this.file = new File(filepath);
    }
    public FileAccess(File object) {
        this.filepath = object.getPath();
        this.file = object;
    }

    public File getFile() {
        return file;
    }

    public boolean isFileExists() {
        return file.exists();
    }

    public boolean isDirExists() {
        File parentDir = file.getParentFile();
        return parentDir != null && parentDir.exists() && parentDir.isDirectory();
    }

    public String getFileName() {
        return file.getName();
    }

    public String getAbsolutePath() {
        return file.getAbsolutePath();
    }

    public boolean deleteFile() {
        return file.delete();
    }

    public long getFileSize() {
        return file.length();
    }

    public void overwriteLines(List<String> lines) {
        try (FileWriter writer = new FileWriter(file, false)) {
            for (String line : lines) {
                writer.write(line + System.lineSeparator());
            }
        } catch (Exception e) {
            System.out.println("Error overwriting file: " + e.getMessage());
        }
    }

    public void appendLines(List<String> lines) {
        try (FileWriter writer = new FileWriter(file, true)) {
            for (String line : lines) {
                writer.write(line + System.lineSeparator());
            }
        } catch (Exception e) {
            System.out.println("Error appending lines: " + e.getMessage());
        }
    }

    public int countLines() {
        return read().size();
    }

    public String read_raw() {
        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append(System.lineSeparator());
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return content.toString();
    }


    public void close() {
        System.out.println("File operations completed.");
    }

    public List<String> read() {
        List<String> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return data;
    }

    public String read(int line_number) {
        List<String> data = read();
        if (line_number < 0 || line_number >= data.size()) {
            System.out.println("Line number out of bounds.");
            return null;
        }
        return data.get(line_number);
    }

    public void write(String data, boolean append) {
        try (FileWriter fileWriter = new FileWriter(file, append)) {
            fileWriter.write(data + System.lineSeparator());
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void write(String data) {
        write(data, true);
    }

    public void createFile() {
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (Exception e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    public void save(String data, boolean append) {
        write(data, append);
        System.out.println("Data saved to file.");
    }

    public void save(String data) {
        save(data, true);
    }

}
