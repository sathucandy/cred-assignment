package com.cred.cardstack;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.cred.cardstack.adapter.StackWrapperAdapter;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements CardStackView.ItemExpandListener {

  public static Integer[] TEST_DATAS = new Integer[]{
    R.color.black, R.color.design_default_color_secondary_variant, R.color.design_default_color_on_secondary, R.color.design_default_color_secondary_variant, R.color.design_default_color_on_secondary, R.color.design_default_color_secondary_variant, R.color.design_default_color_on_secondary, R.color.design_default_color_secondary_variant, R.color.design_default_color_on_secondary
  };
  private CardStackView mStackView;
  private StackWrapperAdapter mStackWrapperAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mStackView = findViewById(R.id.card_stack_view);
    mStackView.setItemExpandListener(this);
    mStackWrapperAdapter = new StackWrapperAdapter(this);
    mStackView.setAdapter(mStackWrapperAdapter);

    new Handler().postDelayed(
      () -> mStackWrapperAdapter.updateData(Arrays.asList(TEST_DATAS))
      , 200
    );
  }

  @Override
  public void onItemExpand(boolean expend) {

  }
}