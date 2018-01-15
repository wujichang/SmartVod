package com.hhzt.vod.smartvod.mvp.presenter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.hhzt.vod.api.CommonRspRetBean;
import com.hhzt.vod.api.IHttpRetCallBack;
import com.hhzt.vod.api.repBean.SimpleRepBean;
import com.hhzt.vod.api.repData.CategoryBoDatasRep;
import com.hhzt.vod.smartvod.BaseFragment;
import com.hhzt.vod.smartvod.MovieFactory;
import com.hhzt.vod.smartvod.R;
import com.hhzt.vod.smartvod.mvp.link.HomeMovieTypeContract;
import com.hhzt.vod.smartvod.mvp.model.IHomeType;
import com.hhzt.vod.smartvod.utils.FragmentUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengxiaoping on 2018/1/9.
 *
 * @Author zengxiaoping
 */

public class HomeMovieTypeLinkPresenter implements HomeMovieTypeContract.HomeMovieTypePresenter {
	private Context mContext;
	private IHomeType mIHomeType;
	private HomeMovieTypeContract.IHomeMovieTypeView mIHomeMovieTypeView;

	private List<SimpleRepBean> mMovieTypeNames = new ArrayList<>();

	public HomeMovieTypeLinkPresenter(
			Context context,
			IHomeType IHomeType,
			HomeMovieTypeContract.IHomeMovieTypeView IHomeMovieTypeView) {
		mContext = context;
		mIHomeType = IHomeType;
		mIHomeMovieTypeView = IHomeMovieTypeView;

		mIHomeMovieTypeView.setPresenter(this);
	}

	@Override
	public void start() {

	}

	@Override
	public void init() {

	}

	@Override
	public void showData(int programGroupId) {
		mIHomeType.requestMovieType(programGroupId, new IHttpRetCallBack<CategoryBoDatasRep>() {
			@Override
			public void onResponseSuccess(CommonRspRetBean bean, CategoryBoDatasRep categoryBoDatasRep) {
				mMovieTypeNames.addAll(categoryBoDatasRep.getCategoryBoList());
				mIHomeMovieTypeView.showData(mMovieTypeNames);

				SimpleRepBean simpleRepBean = new SimpleRepBean();
				simpleRepBean.setName(mContext.getResources().getString(R.string.recommond));
				mMovieTypeNames.add(0, simpleRepBean);
			}

			@Override
			public void onResponseFailed(CommonRspRetBean bean) {

			}

			@Override
			public void onError(String result) {

			}

			@Override
			public void onCancelled() {

			}

			@Override
			public void onFinish() {

			}
		});
	}

	@Override
	public void clearData() {
		mMovieTypeNames.clear();
	}

	@Override
	public void destoryInit() {
		mContext = null;
	}

	@Override
	public void switchFragment(FragmentActivity activity, int layoutId, int position) {
		int movieShowType;
		BaseFragment moviePictureListFragment;
		switch (position) {
			case 0:
				movieShowType = MovieFactory.MOVIE_SHOW_TYPE_BIG_PICTURE;
				break;
			case 1:
				movieShowType = MovieFactory.MOVIE_SHOW_TYPE_MIX_PICTURE;
				break;
			case 2:
			default:
				movieShowType = MovieFactory.MOVIE_SHOW_TYPE_SMALL_PICTURE;
				break;
		}
		moviePictureListFragment = MovieFactory.getFragment(movieShowType, mMovieTypeNames.get(position).getId());
		FragmentUtil.replace(activity, false, R.id.fragment_movie_container, moviePictureListFragment);
	}

	public List<SimpleRepBean> getMovieTypeNames() {
		return mMovieTypeNames;
	}
}
