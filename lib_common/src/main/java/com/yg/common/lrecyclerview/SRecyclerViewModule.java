package com.yg.common.lrecyclerview;

import android.content.Context;

/**
 * 功能：全局的SRecyclerView配置
 */
public interface SRecyclerViewModule {

    AbsRefreshHeader getRefreshHeader(Context context);

    AbsLoadFooter getLoadingFooter(Context context);


}
