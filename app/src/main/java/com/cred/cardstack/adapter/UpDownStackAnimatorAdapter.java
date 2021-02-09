package com.cred.cardstack.adapter;

import android.animation.ObjectAnimator;
import android.view.View;

import com.cred.cardstack.CardStackView;

public class UpDownStackAnimatorAdapter extends AnimatorAdapter {

    public UpDownStackAnimatorAdapter(CardStackView cardStackView) {
        super(cardStackView);
    }

    protected void itemExpandAnimatorSet(final CardStackView.ViewHolder viewHolder, int position) {
        final View itemView = viewHolder.itemView;
        itemView.clearAnimation();
        ObjectAnimator oa = ObjectAnimator.ofFloat(itemView, View.Y, itemView.getY(), mCardStackView.getChildAt(0).getY());
        mAnimatorSet.play(oa);
        int collapseShowItemCount = 0;
        for (int i = 0; i < mCardStackView.getChildCount(); i++) {
            int childTop;
            if (i == mCardStackView.getSelectPosition()) continue;
            final View child = mCardStackView.getChildAt(i);
            child.clearAnimation();
            if (i > mCardStackView.getSelectPosition() && collapseShowItemCount < mCardStackView.getNumBottomShow()) {
                childTop = mCardStackView.getShowHeight() - getCollapseStartTop(collapseShowItemCount);
                ObjectAnimator oAnim = ObjectAnimator.ofFloat(child, View.Y, child.getY(), childTop);
                mAnimatorSet.play(oAnim);
                collapseShowItemCount++;
            } else if (i < mCardStackView.getSelectPosition()) {
                ObjectAnimator oAnim = ObjectAnimator.ofFloat(child, View.Y, child.getY(), mCardStackView.getChildAt(0).getY());
                mAnimatorSet.play(oAnim);
            } else {
                ObjectAnimator oAnim = ObjectAnimator.ofFloat(child, View.Y, child.getY(), mCardStackView.getShowHeight());
                mAnimatorSet.play(oAnim);
            }
        }
    }

    @Override
    protected void itemCollapseAnimatorSet(CardStackView.ViewHolder viewHolder) {
        int childTop = mCardStackView.getPaddingTop();
        for (int i = 0; i < mCardStackView.getChildCount(); i++) {
            View child = mCardStackView.getChildAt(i);
            child.clearAnimation();
            final CardStackView.LayoutParams lp =
                    (CardStackView.LayoutParams) child.getLayoutParams();
            childTop += lp.topMargin;
            if (i != 0) {
                childTop -= mCardStackView.getOverlapGaps() * 2;
            }
            ObjectAnimator oAnim = ObjectAnimator.ofFloat(child, View.Y, child.getY(),
                    childTop - mCardStackView.getScrollDelegate().getViewScrollY() < mCardStackView.getChildAt(0).getY()
                            ? mCardStackView.getChildAt(0).getY() : childTop - mCardStackView.getScrollDelegate().getViewScrollY());
            mAnimatorSet.play(oAnim);
            childTop += lp.mHeaderHeight;
        }
    }

}
