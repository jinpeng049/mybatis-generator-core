package test;

import java.io.File;

public class RenameFile {

    public static void main(String[] args) {
	renameFile();
    }

    public static void renameFile() {
	String path = "C:\\work\\java\\eclipse\\eclipse_mars\\workspace\\pay-db\\pay-dbprovider\\src\\main\\java\\com\\bwair\\pay\\db\\service\\impl";
	File file = new File(path);
	if(file.isDirectory()){
	    File[] fileList = file.listFiles();
	    if(fileList != null ){
		for (File file2 : fileList) {
//		    System.err.println(file2.getName());
//		    String newName = file2.getName().substring(0, file2.getName().lastIndexOf("."));
//		    newName += "Impl";
//		    System.err.println(newName);
//		    String newName = "Provider" + file2.getName();
		    boolean  b = file2.renameTo(new File(path, file2.getName() + ".java"));
		    System.err.println(b);
		}
	    }
	}
    }

}
