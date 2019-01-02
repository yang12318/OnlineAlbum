package util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletContext;

public class FileUtil {

	public FileUtil() {
		// TODO Auto-generated constructor stub
	}

	public static File createFileDir(File file) {
		File f;
		if (file.exists()) {
			f = file;
		} else {
			if (file.mkdirs()) {
				f = file;
			} else {
				f = null;
			}
		}
		return f;
	}

	public static String getFileSuffix(String fileName) {
		String suffix;
		suffix = fileName.trim().substring(fileName.trim().lastIndexOf(".") + 1).toLowerCase();
		return suffix;
	}

	public static String changePathToRelative(HttpServletRequest request,
			String path) {
		String relativePath = "";
		String contextPath = request.getContextPath();
		String webPath = path.substring(path.lastIndexOf(contextPath.substring(1)));
		relativePath = webPath.substring(request.getContextPath().length());
		return relativePath;
	}

	public static File changePathToAbsol(ServletContext context, String path){
		return FileUtil.createFileDir(new File(context.getRealPath("")+File.separator+changePath(path)));
	}

	public static String changePath(String path){
		return path.replace("//", File.separator).replace("\\", File.separator);
	}

	public static boolean deleteFile(File file){
		boolean ok=true;
		if(file.isFile()){
			if(file.exists()){
				file.delete();
			}
		}else if(file.isDirectory()){
			System.out.println(file.getName());
			if(file.listFiles() == null || file.listFiles().length == 0){
				file.delete();
			}else{
				File[] f=file.listFiles();
				for(int i=0;i<f.length;i++){
					deleteFile(f[i]);
					System.out.println(f[i].getName());
				}
			}
			file.delete();
		}
		return ok;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//File file = new File("C://TEST//", "t.txt");
		//createFileDir(file);
		//System.out.print(getFileName("c:\\update\\upd\\dd\\abc.txt"));
	}
	/**
	 * ��ȡ�ļ���
	 */
	public static String getFileName(String fileUrl){
		if (!"".equals(fileUrl.trim())){
		    return fileUrl.substring(fileUrl.lastIndexOf("\\"));
		}else{
			return "";
		}
	}
	
}
