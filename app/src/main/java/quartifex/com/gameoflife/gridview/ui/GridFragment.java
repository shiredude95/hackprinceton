package quartifex.com.gameoflife.gridview.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import quartifex.com.gameoflife.R;
import quartifex.com.gameoflife.gridservice.model.Cell;
import quartifex.com.gameoflife.gridviewmodel.GridViewModel;
import quartifex.com.gameoflife.gridview.adapter.GridThumbAdapter;
import quartifex.com.gameoflife.util.Utils;

public class GridFragment extends Fragment implements GridThumbAdapter.ItemClickListener, View.OnClickListener{

	private GridViewModel mViewModel;
	private static final String TAG="debugGridFragment";
	private GridThumbAdapter gridThumbAdapter;



	public static GridFragment newInstance() {
		return new GridFragment();
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {



		setRetainInstance(true);
		View rootView=inflater.inflate(R.layout.grid_fragment, container, false);
		RecyclerView recyclerView=rootView.findViewById(R.id.grid_thumb_recycler);

		int numberOfCols = Utils.calculateNoOfColumns(getActivity());
		GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),10);
		gridLayoutManager.setSmoothScrollbarEnabled(true);
		recyclerView.setLayoutManager(gridLayoutManager);
		gridThumbAdapter=new GridThumbAdapter(getContext());
		gridThumbAdapter.setItemClickListener(this);
		recyclerView.setAdapter(gridThumbAdapter);
		return rootView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mViewModel = ViewModelProviders.of(this).get(GridViewModel.class);
		// TODO: Use the ViewModel
		observeGridModel(mViewModel);

	}

	private void observeGridModel(GridViewModel viewModel){

		viewModel.getLiveCellsObservable().observe(this, new Observer<List<Cell>>() {
			@Override
			public void onChanged(@Nullable List<Cell> cells) {
				if(cells!=null){
					gridThumbAdapter.setLiveCells(cells);
				}
			}
		});
	}

	//For Reset/Etc
	@Override
	public void onClick(View v) {
	}

	@Override
	public void onItemClick(View view, int position) {
		Cell cell=gridThumbAdapter.getCell(position);
		try{
			((GridActivity)getActivity()).sendEvent2Server(cell);
		}catch (NullPointerException e){
			e.printStackTrace();
		}

	}
}
