package edu.uic.ids517.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.apache.myfaces.custom.fileupload.UploadedFile;

public class UploadFileActionBean {
	private MessageBean messageBean;
	private DBAccessBean dBAccessBean;
	private UploadedFile uploadedFile;
	private String fileLabel;
	private String fileName;
	private long fileSize;
	private String fileContentType;
	private int numberRows;
	private int numberColumns;
	private String uploadedFileContents;
	private boolean fileImport;
	private boolean fileImportError;
	private FacesContext context;
	private String contextPath;

	private int crn;
	private String code;
	private String description;
	private int uin;
	private String last_name;
	private String first_name;
	private String user_name;
	@PostConstruct
	public void init() {
		context = FacesContext.getCurrentInstance();
		System.out.println(context);
		Map<String, Object> m = context.getExternalContext().getSessionMap();
		dBAccessBean = (DBAccessBean) m.get("dBAccessBean");
		//messageBean = (MessageBean) m.get("messageBean");
		contextPath = context.getExternalContext().getRealPath("/");
	}

	public String processFileUpload() {
		String status = "SUCCESS";
		uploadedFileContents = null;
		//messageBean.setErrorMessage("Error");
		// test println only
		System.out.println("context path: " + contextPath);
		String path = contextPath + "temp";
		System.out.println("path: " + path);
		File tempFile = null;
		FileOutputStream fos = null;
		int n = 0;
		System.out.println("Message Bean =" + messageBean);
		System.err.println(messageBean.getErrorMessage());
		//messageBean.setErrorMessage("");
		fileImport = false;
		fileImportError = true;
		try {
			fileName = uploadedFile.getName();
			fileName=fileName.substring((fileName.lastIndexOf("\\")+1), fileName.length());
			fileSize = uploadedFile.getSize();
			fileContentType = uploadedFile.getContentType();
			// next line if want upload in String for memory processing
			// uploadedFileContents = new String(uploadedFile.getBytes());
			tempFile = new File(path + "/" + fileName);

			fos = new FileOutputStream(tempFile);

			// next line if want file uploaded to disk

			fos.write(uploadedFile.getBytes());

			fos.close();

			Scanner s;

			s = new Scanner(tempFile);

			String input;
			String[] values;
			input = s.nextLine();
			while (s.hasNext()) {
				input = s.nextLine();
				values=input.split("\t");
				System.out.println(Arrays.toString(values)+" "+values.length);
				if(values.length==7){
				executeCourseRoster(values,n);
				}else if(values.length==10){
					test(values,n);
				}
				n++;
				// test only println
				// Process data//
				//System.out.println(input);
			}
//			String query ="INSERT INTO f16g321_student (uin,last_name,first_name,user_name) VALUES (664481655,'Jhawar','Ankita','ajhawa2')";
//			dbaseBean.execute(query);
//			
			s.close();
			numberRows = n;
			fileImport = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fileImportError = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fileImportError = true;
		}
		return status;
	}
	
	public void executeCourseRoster(String[] values,int n){
		last_name=values[3];
		first_name = values[4];
		user_name=values[5];
		uin=Integer.parseInt(values[6]);
		
		if(n==0){
		crn = Integer.parseInt(values[0]);
		code = values[1];
		description = values[2];
		String course = "Insert into f16g321_course(crn,code,description) values("+crn+",'"+code+"','"+description+"')";
		dBAccessBean.execute(course);
		}
		String query ="INSERT INTO f16g321_student (uin,last_name,first_name,user_name) VALUES ("+uin+",'"+last_name+"','"+first_name+"','"+user_name+"')";
		dBAccessBean.execute(query);
		}
	
	public void test(String[] values,int n){
		Random random = new Random();
		crn = Integer.parseInt(values[0]);
		code = values[1];
		String test_id = values[2];
		String start_time = values[3];
		String end_time = values[4];
		String duartion = values[5];
		int question_id=random.nextInt(999999);
		String question_type = values[6];
		String question_text = values[7];
		String correct_ans = values[8];
		double tolerance=Double.parseDouble(values[9]);
		
		if(n==0){
			String test="Insert into f16g321_test(test_id,crn,start_time,end_time,duration) values ('"+test_id+"',"+crn+",'"+start_time+"','"+end_time+"','"+duartion+"')";
		
			dBAccessBean.execute(test);
		}
			String question = "Insert into f16g321_questions(question_id,test_id,question_type,question_text,correct_ans,tolerance) values("+question_id+",'"+test_id+"','"+question_type+"','"+question_text+"','"+correct_ans+"',"+tolerance+")";
			dBAccessBean.execute(question);
		}
	public boolean isFileImport() {
		return fileImport;
	}

	public void setFileImport(boolean fileImport) {
		this.fileImport = fileImport;
	}

	public boolean isFileImportError() {
		return fileImportError;
	}

	public void setFileImportError(boolean fileImportError) {
		this.fileImportError = fileImportError;
	}

	public String getFileLabel() {
		return fileLabel;
	}

	public void setFileLabel(String fileLabel) {
		this.fileLabel = fileLabel;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public MessageBean getMessageBean() {
		return messageBean;
	}

	public void setMessageBean(MessageBean messageBean) {
		this.messageBean = messageBean;
	}

	public DBAccessBean getDbaseBean() {
		return dBAccessBean;
	}

	public void setDbaseBean(DBAccessBean dbaseBean) {
		this.dBAccessBean = dbaseBean;
	}

	public FacesContext getContext() {
		return context;
	}

	public void setContext(FacesContext context) {
		this.context = context;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public int getNumberRows() {
		return numberRows;
	}

	public void setNumberRows(int numberRows) {
		this.numberRows = numberRows;
	}

	public int getNumberColumns() {
		return numberColumns;
	}

	public void setNumberColumns(int numberColumns) {
		this.numberColumns = numberColumns;
	}

	public String getUploadedFileContents() {
		return uploadedFileContents;
	}

	public void setUploadedFileContents(String uploadedFileContents) {
		this.uploadedFileContents = uploadedFileContents;
	}

}
