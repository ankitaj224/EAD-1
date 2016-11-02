<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="t" uri="http://myfaces.apache.org/tomahawk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>f16g321 Database Access</title>
</head>
<body>
	<f:view>
		<h3 align="center">f16g321 Database Access</h3>
		<hr />
		<br />

		<div align="center">

			<h:form>
				<h:commandButton type="submit" value="Home" action="index" /> &nbsp;&nbsp;&nbsp;
				<h:commandButton type="submit" value="Change Role"
					action="selectRole" /> &nbsp;&nbsp;&nbsp;
				<h:commandButton type="submit" value="Logout"
					action="#{dBAccessActionBean.logout}" />
			</h:form>
			<br />
			<a href="Documents/RequiredDatabaseTable.pdf" target="_blank">SQL
				Create Table Statements File</a>&nbsp;&nbsp;&nbsp; <br /> <br />
		</div>
		<hr />
		<br />
		<div align="left">
			<h:form>
				<h:panelGrid columns="5">
					<h:commandButton type="submit" value="Table List"
						action="#{dBAccessActionBean.listTables}" />
					<h:commandButton type="submit" value="Column List"
						action="#{dBAccessActionBean.listColumns}" />
					<h:commandButton type="submit" value="Display Table"
						action="#{dBAccessActionBean.selectAllColumn}" />
					<h:commandButton type="submit" value="Display Selected Columns"
						action="#{dBAccessActionBean.selectCustomColumn}" />
					<h:commandButton type="submit" value="Process SQL Query"
						action="#{dBAccessActionBean.processSQLQuery}" />
				</h:panelGrid>
				<h:panelGrid columns="80">

					<h:selectOneListbox size="10" styleClass="selectOneListbox_mono"
						value="#{dBAccessActionBean.tableName}"
						rendered="#{dBAccessActionBean.tableListRendered}">
						<f:selectItems value="#{dBAccessActionBean.tableViewList}" />
					</h:selectOneListbox>

					<h:selectManyListbox size="10" styleClass="selectManyListbox"
						value="#{dBAccessActionBean.columnNamesSelected}"
						rendered="#{dBAccessActionBean.columnListRendered}">
						<f:selectItems value="#{dBAccessActionBean.columnNames}" />
					</h:selectManyListbox>


					<h:inputTextarea cols="70" rows="10"
						value="#{dBAccessActionBean.sqlQuery}" />
				</h:panelGrid>
				<br />
				<hr />
				<br />

				<h:panelGrid columns="2"
					rendered="#{dBAccessActionBean.queryRendered}">
					<h:outputText value="SQL Query:" />
					<h:outputText id="sqlQuery" value="#{dBAccessActionBean.sqlQuery}" />
					<h:outputText value="No of Columns:" />
					<h:outputText id="noOfCol" value="#{dBAccessActionBean.noOfCols}" />
					<h:outputText value="No of Rows:" />
					<h:outputText id="noOfRows" value="#{dBAccessActionBean.noOfRows}" />
				</h:panelGrid>
				<hr />
				<div
					style="background-attachment: scroll; overflow: auto; height: 400px; background-repeat: repeat">
					<t:dataTable value="#{dBAccessActionBean.result}" var="row"
						rendered="#{dBAccessActionBean.queryRendered}" border="1"
						cellspacing="0" cellpadding="1"
						columnClasses="columnClass1 border" headerClass="headerClass"
						footerClass="footerClass" rowClasses="rowClass2"
						styleClass="dataTableEx" width="900">
						<t:columns var="col"
							value="#{dBAccessActionBean.columnNamesSelected}">
							<f:facet name="header">
								<t:outputText styleClass="outputHeader" value="#{col}" />
							</f:facet>
							<t:outputText styleClass="outputText" value="#{row[col]}" />
						</t:columns>
					</t:dataTable>
				</div>
			</h:form>
		</div>
	</f:view>
</body>
</html>