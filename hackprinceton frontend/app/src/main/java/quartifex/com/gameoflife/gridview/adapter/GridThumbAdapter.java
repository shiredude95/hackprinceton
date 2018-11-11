package quartifex.com.gameoflife.gridview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import quartifex.com.gameoflife.R;
import quartifex.com.gameoflife.gridservice.model.Cell;

public class GridThumbAdapter extends RecyclerView.Adapter<GridThumbAdapter.ViewHolder> {

	private final LayoutInflater mInflater;
	private final Context context;
	private List<Cell> liveCells;
	private ItemClickListener itemClickListener;


	public GridThumbAdapter(Context context) {

		this.context = context;
		this.mInflater = LayoutInflater.from(this.context);


	}

	public void setLiveCells(List<Cell> newCells) {

		if (liveCells != null) {

			CellDiffCallBack cellDiffCallBack = new CellDiffCallBack(liveCells, newCells);
			DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(cellDiffCallBack);

			liveCells.clear();
			liveCells.addAll(newCells);
			diffResult.dispatchUpdatesTo(this);

		} else {

			//first init
			liveCells = newCells;
			notifyItemRangeInserted(0, liveCells.size());
		}
	}


	@NonNull
	@Override
	public GridThumbAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
		View view = mInflater.inflate(R.layout.recycle_grid_thumb, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull GridThumbAdapter.ViewHolder viewHolder, int position) {

		//adaoter that will be set will have all the cells in this
		//in length, we denote the live cells with positive value
		//and other with negative
		//our list will look like [-1,-2,3,-4] if cells -1,-2,-4 are dead
		//and cell 3 is alive
		//we then switch what to load based on if our value is negative or positive
		//if 0 is alive we treat it as 1000
		viewHolder.bind(liveCells.get(position));

	}

	@Override
	public int getItemCount() {
		return liveCells == null ? 0 : liveCells.size();
	}


	public Cell getCell(int id) {
		return liveCells.get(id);
	}


	public void setItemClickListener(ItemClickListener itemClickListener) {
		this.itemClickListener = itemClickListener;
	}

	// parent activity will implement this method to respond to click events
	public interface ItemClickListener {
		void onItemClick(View view, int position);
	}

	class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


		final ImageView imageView;

		ViewHolder(@NonNull View itemView) {
			super(itemView);
			imageView = itemView.findViewById(R.id.grid_thumb);

			imageView.setOnClickListener(this);
		}


		void bind(Cell cell) {
			if (cell != null) {
				if(cell.getCellColor()==1){
					imageView.setBackgroundResource(R.drawable.bg_black_color);
				}
				else {
					imageView.setBackgroundResource(R.drawable.bg_color_white);
				}
			}
		}

		@Override
		public void onClick(View v) {
			if (itemClickListener != null) {
				itemClickListener.onItemClick(v, getAdapterPosition());
			}

		}
	}

	class CellDiffCallBack extends DiffUtil.Callback {

		private final List<Cell> oldCells, newCells;


		CellDiffCallBack(List<Cell> oldCells, List<Cell> newCells) {

			this.oldCells = oldCells;
			this.newCells = newCells;
		}

		@Override
		public int getOldListSize() {
			return oldCells.size();
		}

		@Override
		public int getNewListSize() {
			return newCells.size();
		}

		@Override
		public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
			return oldCells.get(oldItemPosition).getCellColor() == newCells.get(newItemPosition).getCellColor();
		}

		@Override
		public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
			return oldCells.get(oldItemPosition).getCellColor() == newCells.get(newItemPosition).getCellColor();
		}
	}
}
