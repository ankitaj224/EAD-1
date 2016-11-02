package edu.uic.ids517.model;

import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

public class DBAccessActionBean {

	private String tableName;
	private String sqlQuery;
	private int noOfCols = 0;
	private int noOfRows = 0;
	private Result result;
	private String tableinListColumn;
	private FacesContext context;
	private List<String> columnNames;
	private List<String> tableViewList;
	private List<String> columnNamesSelected;
	private boolean tableListRendered;
	private boolean columnListRendered = false;
	private boolean queryRendered = false;
	DBAccessBean dBAccessBean;
	private ResultSetMetaData resultSetMetaData;

	public DBAccessActionBean() {

	}

	@PostConstruct
	public void init() {
		context = FacesContext.getCurrentInstance();
		System.out.println(context);
		Map<String, Object> m = context.getExternalContext().getSessionMap();
		dBAccessBean = (DBAccessBean) m.get("dBAccessBean");
		// messageBean = (MessageBean) m.get("messageBean");
		listTables();

	}

	public Result getResult() {
		return result;
	}

	public boolean isTableListRendered() {
		return tableListRendered;
	}

	public List<String> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(List<String> columnNames) {
		this.columnNames = columnNames;
	}

	public List<String> getTableViewList() {
		return tableViewList;
	}

	public List<String> getColumnNamesSelected() {
		return columnNamesSelected;
	}

	public void setColumnNamesSelected(List<String> columnNamesSelected) {
		this.columnNamesSelected = columnNamesSelected;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getSqlQuery() {
		return sqlQuery;
	}

	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	public int getNoOfCols() {
		return noOfCols;
	}

	public int getNoOfRows() {
		return noOfRows;
	}

	public boolean isColumnListRendered() {
		return columnListRendered;
	}

	public boolean isQueryRendered() {
		return queryRendered;
	}

	public String listTables() {
		try {

			tableViewList = dBAccessBean.tableList();
			if (null != tableViewList) {
				tableListRendered = true;
			}
			return "SUCCESS";
		} catch (Exception e) {
			tableListRendered = false;
			e.printStackTrace();
			return "FAIL";
		}
	}

	public String listColumns() {

		try {
			columnNames = dBAccessBean.columnList(tableName);
			tableinListColumn = tableName;
			queryRendered = false;
			sqlQuery = "";
			if (null != columnNames) {
				columnListRendered = true;
			}
		} catch (Exception e) {
			columnListRendered = false;
			e.printStackTrace();
			return "FAIL";
		}

		return "SUCCESS";

	}

	public String selectAllColumn() {
		listColumns();
		sqlQuery = "select * from " + tableinListColumn + " ;";
		dBAccessBean.execute(sqlQuery);
		noOfCols = dBAccessBean.getNumOfCols();
		noOfRows = dBAccessBean.getNumOfRows();
		dBAccessBean.generateResult();
		result = dBAccessBean.getResult();
		columnNamesSelected = dBAccessBean.columnList(tableinListColumn);
		queryRendered = true;
		return "SUCCESS";
	}

	public String selectCustomColumn() {
		sqlQuery = "select " + columnNamesSelected.toString().replace("[", "").replace("]", "") + " from "
				+ tableinListColumn + " ;";
		dBAccessBean.execute(sqlQuery);
		noOfCols = dBAccessBean.getNumOfCols();
		noOfRows = dBAccessBean.getNumOfRows();
		dBAccessBean.generateResult();
		result = dBAccessBean.getResult();
		// columnNames = dBAccessBean.columnList(tableName);
		queryRendered = true;
		return "SUCCESS";
	}

	public String processSQLQuery() {
		try {
			dBAccessBean.execute(sqlQuery);
			System.out.println(sqlQuery);
			if (sqlQuery.toLowerCase().startsWith("select")) {
				noOfCols = dBAccessBean.getNumOfCols();
				noOfRows = dBAccessBean.getNumOfRows();
				dBAccessBean.generateResult();
				result = dBAccessBean.getResult();
				columnNamesSelected = new ArrayList<String>(noOfCols);
				for (int i = 0; i < noOfCols; i++) {
					resultSetMetaData = dBAccessBean.getResultSetMetaData();
					columnNamesSelected.add(resultSetMetaData.getColumnName(i + 1));
				}

				queryRendered = true;
			} else {
				queryRendered = false;
			}
			return "SUCCESS";
		} catch (Exception e) {
			return "FAIL";
		}
	}

	public String logout() {
		dBAccessBean.close();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "LOGOUT";
	}

}
