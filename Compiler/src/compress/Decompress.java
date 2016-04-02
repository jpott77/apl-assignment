package compress;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import pages.fileManagement;

public class Decompress {

	/*public static void main(String[] args) {

		String gzip_filepath = "/Users/carlingtonpalmer/Documents/workspace_new/UnZip/file.gzip";
		String decopressed_filepath = "/Users/carlingtonpalmer/Documents/workspace_new/UnZip/file2.txt";

		decompress gZipFile = new decompress();

		gZipFile.gUnzipFile(gzip_filepath, decopressed_filepath);
	}*/

	public String gUnzipFile(String compressedFile) {

		byte[] buffer = new byte[1024];
		
		String decompressedFile = "";

		try {

			decompressedFile = getDestinationUnZipFilePath(compressedFile);
			
			FileInputStream fileIn = new FileInputStream(compressedFile);

			GZIPInputStream gZIPInputStream = new GZIPInputStream(fileIn);

			FileOutputStream fileOutputStream = new FileOutputStream(decompressedFile);

			int bytes_read;

			while ((bytes_read = gZIPInputStream.read(buffer)) > 0) {

				fileOutputStream.write(buffer, 0, bytes_read);
			}

			gZIPInputStream.close();
			fileOutputStream.close();

			System.out.println("The file was decompressed successfully!");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return decompressedFile;
	}

	public String getDestinationUnZipFilePath(String file)
	{
		String[] parts = file.split("/");
    	String part1 = parts[0];
    	String part2 = parts[1];
    	String part3 = parts[2];
    	
    	String[] fileName = part3.split("\\.");
    	
    	String name = fileName[0];
    	
    	String[] breakName = name.split(":");
    	
    	name = breakName[0];
    	
    	file = part1+"/"+part2+"/"+name+":Decompressed.txt";
    	
    	return file;
	}
}