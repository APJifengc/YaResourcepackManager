package io.github.apjifengc.yaresourcepackmanager.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Util class for file methods.
 *
 * @author APJifengc
 */
public class FileUtils {

    public static final String EXT = ".zip";
    private static final String BASE_DIR = "";

    private static final String PATH = File.separator;
    private static final int BUFFER = 1024;

    /**
     * Compress a folder's content without the folder.
     *
     * @param srcFile  The folder to compress.
     * @param destFile The ZIP file.
     * @throws IOException Throw when a file error occurred.
     */
    public static void compressWithoutRoot(File srcFile, File destFile) throws IOException {
        CheckedOutputStream cos = new CheckedOutputStream(new FileOutputStream(destFile), new CRC32());
        ZipOutputStream zos = new ZipOutputStream(cos);
        File[] files = srcFile.listFiles();
        for (File file : files) {
            compress(file, zos, "");
        }
        zos.flush();
        zos.close();
    }

    /**
     * Compress a folder's content into a ZIP file.
     *
     * @param srcFile The folder to compress.
     * @throws IOException Throw when a file error occurred.
     */
    public static void compress(File srcFile) throws IOException {
        String name = srcFile.getName();
        String basePath = srcFile.getParent();
        String destPath = basePath + name + EXT;
        compress(srcFile, destPath);
    }

    /**
     * Compress a folder's content into a ZIP file.
     *
     * @param srcFile  The folder to compress.
     * @param destFile The ZIP file.
     * @throws IOException Throw when a file error occurred.
     */
    public static void compress(File srcFile, File destFile) throws IOException {
        CheckedOutputStream cos = new CheckedOutputStream(new FileOutputStream(destFile), new CRC32());
        ZipOutputStream zos = new ZipOutputStream(cos);
        compress(srcFile, zos, BASE_DIR);
        zos.flush();
        zos.close();
    }

    /**
     * Compress a folder's content into a ZIP file.
     *
     * @param srcFile  The folder to compress.
     * @param destPath The ZIP file.
     * @throws IOException Throw when a file error occurred.
     */
    public static void compress(File srcFile, String destPath) throws IOException {
        compress(srcFile, new File(destPath));
    }

    private static void compress(File srcFile, ZipOutputStream zos, String basePath) throws IOException {
        if (srcFile.isDirectory()) {
            compressDir(srcFile, zos, basePath);
        } else {
            compressFile(srcFile, zos, basePath);
        }
    }

    /**
     * Compress a folder's content into a ZIP file.
     *
     * @param srcPath The folder to compress.
     * @throws IOException Throw when a file error occurred.
     */
    public static void compress(String srcPath) throws IOException {
        File srcFile = new File(srcPath);
        compress(srcFile);
    }

    /**
     * Compress a folder's content into a ZIP file.
     *
     * @param srcPath  The folder to compress.
     * @param destPath The ZIP file.
     * @throws IOException Throw when a file error occurred.
     */
    public static void compress(String srcPath, String destPath) throws IOException {
        File srcFile = new File(srcPath);
        compress(srcFile, destPath);
    }

    private static void compressDir(File dir, ZipOutputStream zos, String basePath) throws IOException {
        File[] files = dir.listFiles();
        if (files.length < 1) {
            ZipEntry entry = new ZipEntry(basePath + dir.getName() + PATH);
            zos.putNextEntry(entry);
            zos.closeEntry();
        }
        for (File file : files) {
            compress(file, zos, basePath + dir.getName() + PATH);
        }
    }

    private static void compressFile(File file, ZipOutputStream zos, String dir) throws IOException {
        ZipEntry entry = new ZipEntry(dir + file.getName());
        zos.putNextEntry(entry);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        int count;
        byte[] data = new byte[BUFFER];
        while ((count = bis.read(data, 0, BUFFER)) != -1) {
            zos.write(data, 0, count);
        }
        bis.close();
        zos.closeEntry();
    }

    /**
     * Delete a file or a folder with the contents in it.
     *
     * @param file The file or folder to delete.
     * @throws IOException Throw when a file error occurred.
     */
    public static void deleteFile(File file) throws IOException {
        if (file.isFile()) {
            file.delete();
            return;
        }
        for (File f : file.listFiles()) {
            deleteFile(f);
        }
        file.delete();
    }

    /**
     * Get a file's SHA1 value.
     *
     * @param file The file.
     * @return SHA1 value.
     * @throws IOException Throws on error reading from the file.
     */
    public static byte[] getFileSHA1(File file) throws IOException {
        return DigestUtils.sha1(new FileInputStream(file));
    }

    /**
     * Write a input stream into a file.
     *
     * @param inputStream The input stream.
     * @param file        The file,
     * @throws IOException Throw when an error occurred,
     */
    public static void writeFile(InputStream inputStream, File file) throws IOException {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        FileOutputStream output = new FileOutputStream(file);
        while (inputStream.available() > 0) {
            output.write(inputStream.read());
        }
        output.close();
    }
}