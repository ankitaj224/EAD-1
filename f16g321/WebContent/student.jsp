<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="t" uri="http://myfaces.apache.org/tomahawk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student</title>
</head>
<body>
	<f:view>
		<h:outputText>Courses </h:outputText>
		<t:dataTable styleClass="selectOneListbox_mono"
			value="#{actionStudentBean.courses}" var="rowNumber"
			rendered="#{actionStudentBean.renderCourseList}">

		</t:dataTable>
	</f:view>
</body>
</html>