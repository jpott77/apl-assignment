package compress;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import pages.fileManagement;

public class Compress {

	/*public static void main(String[] args) {

		
		String source_filepath = "/Users/carlingtonpalmer/Documents/workspace_new/UnZip/file.txt";
		String destinaton_zip_filepath = "/Users/carlingtonpalmer/Documents/workspace_new/UnZip/file.gzip";

		CompressFileGzip gZipFile = new CompressFileGzip();
		gZipFile.gzipFile(source_filepath, destinaton_zip_filepath);
	}
*/
	public String gzipFile(String source_filepath) {

		byte[] buffer = new byte[1024];
		String destinaton_zip_filepath = "";

		try {
			
			destinaton_zip_filepath = getDestinationZipFilePath(source_filepath);
			System.out.println("Source1 " +source_filepath);
			System.out.println("See1 " +destinaton_zip_filepath);
			FileOutputStream fileOutputStream =new FileOutputStream(destinaton_zip_filepath);
			System.out.println("See " +destinaton_zip_filepath);

			GZIPOutputStream gzipOuputStream = new GZIPOutputStream(fileOutputStream);

			FileInputStream fileInput = new FileInputStream(source_filepath);

			int bytes_read;
			
			while ((bytes_read = fileInput.read(buffer)) > 0) {
				gzipOuputStream.write(buffer, 0, bytes_read);
			}

			fileInput.close();

			gzipOuputStream.finish();
			gzipOuputStream.close();

			System.out.println("The file was compressed successfully!");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return destinaton_zip_filepath;
	}
	
	public String getDestinationZipFilePath(String file)
	{
		String[] parts = file.split("/");
    	String part1 = parts[0];
    	String part2 = parts[1];
    	String part3 = parts[2];
    	
    	String[] fileName = part3.split("\\.");
    	
    	String name = fileName[0];
    	
    	String[] breakName = name.split(":");
    	
    	name = breakName[0];
    	
    	System.out.println(name);
    	
    	file = part1+"/"+part2+"/"+name+".gzip";
    	
    	return file;
	}

}