import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class FileManager {
	
	private static FileManager instance = null;
	
	private String filename;
	
	private FileManager() {
		// Initialization
		filename = "data.dat";
		
	}
	
	// ------------------------------------------------- gets / sets
	
	public void setFilename(String value) {
		filename = value;
	}
	
	public String getFilename() {
		return filename;
	}
	
	// ------------------------------------------------- Public methods
	public void save(Object object) {
		
		try {

			// Construct the file object that represents the file in our app
			File file = new File(filename);
			// Construct the Stream Objects 
			FileOutputStream fileStream = new FileOutputStream(file);
			ObjectOutputStream dataStream = new ObjectOutputStream(fileStream);
			
			// Write the bicycle object to the output stream!
			dataStream.writeObject(object);

			// Close the stream
			dataStream.close();
			
		}
		catch (Exception e) {
			System.out.println("Binary file output error: " + e.getMessage());
		}
	}
	
	public Object load() {
		try {

			// Construct the file object that represents the file in our app
			File file = new File(filename);
			// Does the file exists to be read from
			if (file.exists()) {
				// Construct the Stream objects
				FileInputStream fileStream = new FileInputStream(file);
				ObjectInputStream dataStream = new ObjectInputStream(fileStream);
				
				Object object = dataStream.readObject();
				
				// Close the Stream
				dataStream.close();
				
				return object;
			}
			else {
				
				return null;
			}
			
		}
		catch (Exception e) {
			System.out.println("Binary file input error: " + e.getMessage());
			
			return null;
		}
	}
	
	
	
	
	public static FileManager getInstance() {
		if (instance == null) {
			instance = new FileManager();
		}
		
		return instance;
	}
	
	
	
	
}