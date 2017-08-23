package com.zj.util.file;

import com.alibaba.fastjson.JSON;
import com.zj.util.file.model.Files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	 /**
     * The Unix separator character.
     */
    private static final char UNIX_SEPARATOR = '/';

    /**
     * The Windows separator character.
     */
    private static final char WINDOWS_SEPARATOR = '\\';

    /**
     * The system separator character.
     */
    private static final char SYSTEM_SEPARATOR = File.separatorChar;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Files files = getFiles("D:/software/myWiki/");
		System.out.println(JSON.toJSONString(files));
	}
	
	public static Files getFiles(String path){
		Files files = new Files();
        File file = getFile(path);
        files.setName(file.getName());
        files.setPath(file.getPath());
        //单个文件
        if(!file.isDirectory()){
        	files.setType(Files.TYPE_FILE);
        	return files;
        }
        //文件夹
        files.setType(Files.TYPE_DIR);
        File[] array = file.listFiles();
        List<Files> allSubFiles = new ArrayList<Files>();
        for(int i=0;i<array.length;i++){   
        	Files subFiles = getFiles(array[i].getPath());
        	allSubFiles.add(subFiles);
        }
        files.setFiles(allSubFiles);
        return files;
    }    


	public static boolean deleteQuietly(File file) {
        if (file == null) {
            return false;
        }
        try {
            if (file.isDirectory()) {
                cleanDirectory(file);
            }
        } catch (Exception ignored) {
        }

        try {
            return file.delete();
        } catch (Exception ignored) {
            return false;
        }
    }
	
	public static void cleanDirectory(File directory) throws IOException {
        if (!directory.exists()) {
            String message = directory + " does not exist";
            throw new IllegalArgumentException(message);
        }

        if (!directory.isDirectory()) {
            String message = directory + " is not a directory";
            throw new IllegalArgumentException(message);
        }

        File[] files = directory.listFiles();
        if (files == null) {  // null if security restricted
            throw new IOException("Failed to list contents of " + directory);
        }

        IOException exception = null;
        for (File file : files) {
            try {
                forceDelete(file);
            } catch (IOException ioe) {
                exception = ioe;
            }
        }

        if (null != exception) {
            throw exception;
        }
    }
	
	public static void forceDelete(File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectory(file);
        } else {
            boolean filePresent = file.exists();
            if (!file.delete()) {
                if (!filePresent){
                    throw new FileNotFoundException("File does not exist: " + file);
                }
                String message =
                    "Unable to delete file: " + file;
                throw new IOException(message);
            }
        }
    }
	
	public static void deleteDirectory(File directory) throws IOException {
        if (!directory.exists()) {
            return;
        }

        if (!isSymlink(directory)) {
            cleanDirectory(directory);
        }

        if (!directory.delete()) {
            String message =
                "Unable to delete directory " + directory + ".";
            throw new IOException(message);
        }
    }
	
	public static boolean isSymlink(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException("File must not be null");
        }
        if (isSystemWindows()) {
            return false;
        }
        File fileInCanonicalDir = null;
        if (file.getParent() == null) {
            fileInCanonicalDir = file;
        } else {
            File canonicalDir = file.getParentFile().getCanonicalFile();
            fileInCanonicalDir = new File(canonicalDir, file.getName());
        }
        
        if (fileInCanonicalDir.getCanonicalFile().equals(fileInCanonicalDir.getAbsoluteFile())) {
            return false;
        } else {
            return true;
        }
    }
	private static boolean isSystemWindows() {
        return SYSTEM_SEPARATOR == WINDOWS_SEPARATOR;
    }
	public static boolean save(String path, byte[] buff) {
		boolean success = false;
		FileOutputStream fos = getFileOutputStream(path);
		if (fos != null) {
			try {
				fos.write(buff);
				fos.flush();
				fos.close();
				success = true;
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return success;
	}

	public static FileOutputStream getFileOutputStream(String path) {
		File file = getFile(path);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fos;
	}

	public static File getFile(String path) {
		File file = new File(path);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
}
