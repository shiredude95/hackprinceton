package quartifex.com.gameoflife.gridviewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import quartifex.com.gameoflife.gridservice.model.Cell;

public class GridViewModel extends ViewModel {
	// TODO: Implement the ViewModel
	private MutableLiveData<List<Cell>> liveCells=new MutableLiveData<>();
	private List<Cell> testCells=new ArrayList<>();

	public GridViewModel(){
		super();
		//TODO:
		//populate from onMessage

		//liveCells=EWSL.getCellGrid()

		for(int i=0;i<100;i++){
			if(i%2==0){
				testCells.add(new Cell(1,i,i,true));
			}
			else{
				testCells.add(new Cell(0,i,i,false));
			}
		}
		liveCells.setValue(testCells);
	}

	//To be called from the UI in gridview.fragment
	public MutableLiveData<List<Cell>> getLiveCellsObservable(){
		return liveCells;
	}







}
