package cn.tedu.tab_menu_2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends FragmentActivity {
	private Button btnTabMenuDeal;
	private Button btnTabMenuNearby;
	private Button btnTabMenuMy;
	private Button btnTabMenuMore;
	private FrameLayout flContainer;
	private FragmentPagerAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnTabMenuDeal = (Button) findViewById(R.id.btn_tab_menu_deal);
		btnTabMenuNearby = (Button) findViewById(R.id.btn_tab_menu_nearby);
		btnTabMenuMy = (Button) findViewById(R.id.btn_tab_menu_my);
		btnTabMenuMore = (Button) findViewById(R.id.btn_tab_menu_more);
		flContainer = (FrameLayout) findViewById(R.id.fl_container);

		InnerOnClickListener listener = new InnerOnClickListener();
		btnTabMenuDeal.setOnClickListener(listener);
		btnTabMenuNearby.setOnClickListener(listener);
		btnTabMenuMy.setOnClickListener(listener);
		btnTabMenuMore.setOnClickListener(listener);

		adapter = new InnerFragmentPagerAdapter(getSupportFragmentManager());

		// Ĭ��ѡ�е�1����ť
		selectButton(btnTabMenuDeal);
	}

	private class InnerFragmentPagerAdapter extends FragmentPagerAdapter {

		public InnerFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment frag = null;
			switch (position) {
			case 0:
				frag = new DealFragment();
				break;
			case 1:
				frag = new NearbyFragment();
				break;
			case 2:
				frag = new MyFragment();
				break;
			case 3:
				frag = new MoreFragment();
				break;
			}
			return frag;
		}

		@Override
		public int getCount() {
			return 4;
		}

	}

	/**
	 * ѡ��ĳ����ť
	 * 
	 * @param button
	 *            ��ѡ���Button����
	 */
	private void selectButton(Button button) {
		btnTabMenuDeal.setEnabled(true);
		btnTabMenuNearby.setEnabled(true);
		btnTabMenuMy.setEnabled(true);
		btnTabMenuMore.setEnabled(true);

		button.setEnabled(false);

		// ��ȡ��Ҫ��ʾ��Fragment��position
		int position = 0;
		if (button == btnTabMenuDeal) {
			position = 0;
		} else if (button == btnTabMenuNearby) {
			position = 1;
		} else if (button == btnTabMenuMy) {
			position = 2;
		} else if (button == btnTabMenuMore) {
			position = 3;
		}
		
		//
		if(lastFragment != null) {
			adapter.destroyItem(flContainer, lastPosition, lastFragment);
			lastFragment = null;
		}

		//
		Object fragment = adapter.instantiateItem(flContainer, position);
		//
		adapter.setPrimaryItem(flContainer, 0, fragment);
		//
		adapter.finishUpdate(flContainer);
		//
		lastFragment = fragment;
		lastPosition = position;
	}
	
	private Object lastFragment;
	private int lastPosition;

	private class InnerOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_tab_menu_deal:
				// �������1����ť
				selectButton(btnTabMenuDeal);
				break;

			case R.id.btn_tab_menu_nearby:
				// �������2����ť
				selectButton(btnTabMenuNearby);
				break;

			case R.id.btn_tab_menu_my:
				// �������3����ť
				selectButton(btnTabMenuMy);
				break;

			case R.id.btn_tab_menu_more:
				// �������4����ť
				selectButton(btnTabMenuMore);
				break;
			}
		}

	}

}
