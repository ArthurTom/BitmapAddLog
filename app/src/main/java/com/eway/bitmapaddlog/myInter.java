package com.eway.bitmapaddlog;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 作者:zwk
 * 联系方式：497981239@qq.com
 * 时间：on 2019/4/12 10:36
 * 功能：
 */
public interface myInter {

	//
	@GET("syncCorpByCorpCode")
	Call<String> getMessage(@Query("CorpCode") String a, @Query("queryStatus") String b);

}
