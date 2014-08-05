package com.bliksem.scientificunitconverter;

public class UnitListViewRow {

	private String symbol;
	private String nicename;
	private String result;

	public UnitListViewRow() {
	}

	public UnitListViewRow(String nicename, String symbol) {
		this.nicename = nicename;
		this.symbol = symbol;
		this.result = "";
	}

	public String getNiceName() {
		return this.nicename;
	}

	public void setNiceName(String nicename) {
		this.nicename = nicename;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
