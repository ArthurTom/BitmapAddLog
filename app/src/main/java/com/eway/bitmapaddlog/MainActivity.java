package com.eway.bitmapaddlog;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 给图片加水印
 */
public class MainActivity extends AppCompatActivity {


	private ImageView show;
	private Bitmap sourceBitmap;
	private Bitmap waterMarketBitmap;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		show = findViewById(R.id.show);
		findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//1.获取原图 、获取水印图片
				initImage();
				//2. 将水印图片加入到原图中
				Bitmap mBitMap = addWaterMarket(sourceBitmap, waterMarketBitmap);
				//3. 将图片保存到本地

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				show.setImageBitmap(mBitMap);

				saveMarketPhoto(mBitMap);

			}
		});


	}

	/**
	 * 初始化源图片和水印图片
	 */
	private void initImage() {
		// 源图片
		Drawable sourcePhoto = getResources().getDrawable(R.drawable.photo);
		BitmapDrawable drawable1Bitmap = (BitmapDrawable) sourcePhoto;
		sourceBitmap = drawable1Bitmap.getBitmap();
		// 水印图片
		Drawable waterMarketPhoto = getResources().getDrawable(R.drawable.zhang);
		BitmapDrawable bitmapDrawable = (BitmapDrawable) waterMarketPhoto;
		waterMarketBitmap = bitmapDrawable.getBitmap();

	}


	/**
	 * 添加水印图片
	 *
	 * @param sourceBitmap 原图
	 * @param photoBitmap  水印图片
	 * @return Bitmap
	 */
	private Bitmap addWaterMarket(Bitmap sourceBitmap, Bitmap photoBitmap) {
		if (sourceBitmap == null || photoBitmap == null) {
			return null;
		}

		int sourcePhotoHeight = sourceBitmap.getHeight();
		int sourcePhotoWidth = sourceBitmap.getWidth();

		Bitmap newBitMap = Bitmap.createBitmap(sourcePhotoWidth, sourcePhotoHeight, Bitmap.Config.ARGB_8888);

		Canvas canvas = new Canvas(newBitMap);
		canvas.drawBitmap(sourceBitmap, 0, 0, null);

		canvas.drawBitmap(waterMarketBitmap, 0, 0, null);

		canvas.save();

		canvas.restore();

		return newBitMap;


	}


	public boolean saveMarketPhoto(Bitmap newBitmap) {

		String newPhotoPath = Environment.getExternalStorageDirectory().getPath() + File.separator + "zwk";

		File file = new File(newPhotoPath);

		if (!file.exists()) {
			boolean isTrue = file.mkdirs();
		}


		File photoFile = new File(newPhotoPath + File.separator + "zwk.jpg");

		if (!photoFile.exists()) {
			try {
				photoFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			FileOutputStream outputStream = new FileOutputStream(photoFile);
			newBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
			outputStream.flush();
			outputStream.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}


	}

}
