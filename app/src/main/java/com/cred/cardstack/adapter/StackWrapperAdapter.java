package com.cred.cardstack.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.cred.cardstack.CardStackView;
import com.cred.cardstack.R;

public class StackWrapperAdapter extends StackAdapter<Integer> {

  public StackWrapperAdapter(Context context) {
    super(context);
  }

  @Override
  public void bindView(Integer data, int position, CardStackView.ViewHolder holder) {
    if (holder instanceof ItemViewHolder) {
      ItemViewHolder h = (ItemViewHolder) holder;
      h.onBind(data, position);
    }
  }

  @Override
  protected CardStackView.ViewHolder onCreateView(ViewGroup parent, int viewType) {
    View view = getLayoutInflater().inflate(R.layout.list_card_item, parent, false);
    return new ItemViewHolder(view);
  }

  @Override
  public int getItemViewType(int position) {
    return R.layout.list_card_item;
  }

  static class ItemViewHolder extends CardStackView.ViewHolder {
    View mLayout;
    View mContainerContent;
    TextView mTextTitle;

    public ItemViewHolder(View view) {
      super(view);
      mLayout = view.findViewById(R.id.frame_list_card_item);
      mContainerContent = view.findViewById(R.id.container_list_content);
      mTextTitle = view.findViewById(R.id.text_list_card_title);
    }

    @Override
    public void onItemExpand(boolean b) {
      mContainerContent.setVisibility(b ? View.VISIBLE : View.GONE);
    }

    public void onBind(Integer data, int position) {
      mLayout.getBackground().setColorFilter(ContextCompat.getColor(getContext(), data), PorterDuff.Mode.SRC_IN);
      mTextTitle.setText(String.valueOf(position));
    }

  }

}
