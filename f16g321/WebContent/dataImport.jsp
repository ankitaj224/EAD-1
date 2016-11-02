<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="t" uri="http://myfaces.apache.org/tomahawk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload File</title>
</head>
<body>
	<f:view>
		<h3 align="center">f16g321 Upload File</h3>
		<hr />
		<br />

		<div align="center">
			<h:form>
				<h:commandButton type="submit" value="Home" action="index" /> &nbsp;&nbsp;&nbsp;
				<h:commandButton type="submit" value="Change Role" action="selectRole" /> &nbsp;&nbsp;&nbsp;
				<h:commandButton type="submit" value="Logout"
					action="#{dBAccessActionBean.logout}" />&nbsp;&nbsp;&nbsp;
		</h:form>
			<br /> <br />
		</div>
		<hr />
		<br />
		<h:form enctype="multipart/form-data">
			<h:panelGrid columns="2"
				style="background-color: Beige;
border-bottom-style: solid;
border-top-style: solid;
border-left-style: solid;
border-right-style: solid">
				<h:outputLabel value="select file to upload:" />
				<t:inputFileUpload id="fileUpload"
					value="#{uploadFileActionBean.uploadedFile}" required="false"
					size="60" />
				<h:outputLabel value="file label:" />
				<h:inputText id="fileLabel"
					value="#{uploadFileActionBean.fileLabel}" size="60" />
				<h:outputLabel value=" " />
				<h:commandButton id="upload"
					action="#{uploadFileActionBean.processFileUpload}" value="Submit" />
			</h:panelGrid>
			<h:outputLabel rendered="#{uploadFileActionBean.fileImport }"
				value="Number of records imported: " />
			<h:outputText rendered="#{uploadFileActionBean.fileImport }"
				value="#{uploadFileActionBean.numberRows }" />
			<h:outputText rendered="#{uploadFileActionBean.fileImportError }"
				value="#{messageBean.errorMessage }" />
		</h:form>
	</f:view>
</body>
</html>