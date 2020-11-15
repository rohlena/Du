package mypackage1;

import java.io.FileInputStream;		
import java.io.FileNotFoundException;		
import java.io.IOException;		
import java.util.Properties;

public class objectRepository {
	public static String filePath; //"user.dir")+"\\application.properties"
	public static int indexRow;
	public String keyName;
	public String keyValue;
	
	public objectRepository() {
		this.filePath = "";
		this.indexRow = 0;
		this.keyName = "";
		this.keyValue = "";
	}
	public objectRepository(String parFilePath, String parKey, String parValue) {
		this.filePath = parFilePath;
		this.keyName = parKey;
		this.keyValue = parValue;
	}
	public objectRepository(objectRepository or) {
		this(or.filePath,or.keyName,or.keyValue);
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	public String[] getKeyValue() {
		return new String[] {this.keyName , this.keyValue};
	}
	public String loadKeyValue(String keyName) {
		if (!(keyName.isBlank() || keyName.isEmpty())) {
			this.keyName = keyName;
		}
		String[] tmp = getProperty();
		if (tmp[0].contentEquals("")){
			return "";
		}
		else {
			this.keyName = tmp[0];
			this.keyValue = tmp[1];
			return tmp[1];
		}
		//return new String[] {this.keyName , this.keyValue};
	}
	static int getIndexRow() {
		//this.filePath;
		//todo
		return 0;
	}
	
	public String[] getProperty() { //Load the properties from File	
		Properties o = new Properties();
		FileInputStream ob;
		try {
			ob = new FileInputStream(this.filePath);
			try {
				o.load(ob);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.keyValue = o.getProperty(this.keyName);
			return new String[] {this.keyName , this.keyValue};
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new String[] {"",""};
	}
	public void setProperty() {
		Properties o = new Properties();
		FileInputStream ob;
		try {
			ob = new FileInputStream(System.getProperty(this.filePath));
			try {
				o.load(ob);
				if (o.containsKey(this.keyName)){
					//todo
					int oSize = o.size();
					String propNames = o.stringPropertyNames().toString();
					//o.entrySet();
					int nSize = o.size();
				}
				else {
					//todo
					int oldSize = o.size();
					o.setProperty(keyName, keyValue);
					int newSize = o.size();
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String errorMessage = e.getMessage();
		}
		
		
	}
	
	
	
	public static void main(String[] args) {
		System.out.println("Test start");
		objectRepository tor = new objectRepository();
		tor.filePath = "/Proj1/ObjectRepository.properties";
		tor.keyName = "MobileTesting";
		String tmpValue = tor.loadKeyValue("MobileTesting");
		System.out.println("Test Passed!" + tor.keyValue);
	}
}
