package quartifex.com.gameoflife.gridservice.model;

import android.content.Context;

public class Cell {

	private int cellColor;
	private int mappedCellNumber;
	private int globalCellNumber;
	private boolean isAlive;

	public Cell(int colorFlag,int pMappedCellNumber, int pGlobalCellNumber,boolean isAlive){


		this.mappedCellNumber=pMappedCellNumber;
		this.globalCellNumber=pGlobalCellNumber;
		this.isAlive=isAlive;
		this.cellColor=colorFlag;
	}

	public int getCellColor() {
		return this.cellColor;
	}

	public void setCellColor(int color) {
		this.cellColor=color;
	}

	public int getMappedCellNumber() {
		return mappedCellNumber;
	}

	public void setMappedCellNumber(int mappedCellNumber) {
		this.mappedCellNumber = mappedCellNumber;
	}

	public int getGlobalCellNumber() {
		return globalCellNumber;
	}

	public void setGlobalCellNumber(int globalCellNumber) {
		this.globalCellNumber = globalCellNumber;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean alive) {
		isAlive = alive;
	}
}
