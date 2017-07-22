package test.bawei.com.photoselector;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.List;

public class MainActivity extends AppCompatActivity {
//    gaiguode
    List<Uri> mSelected;
    private static final int REQUEST_CODE_CHOOSE = 23;//定义请求码常量
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Matisse
                .from(MainActivity.this)
                .choose(MimeType.ofAll())//照片视频全部显示
                .countable(true)//有序选择图片
                .maxSelectable(9)//最大选择数量为9
                .gridExpectedSize(240)//图片显示表格的大小
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)//图像选择和预览活动所需的方向。
                .thumbnailScale(0.85f)//缩放比例
                .theme(R.style.Matisse_Zhihu)//主题  暗色主题 R.style.Matisse_Dracula
                .imageEngine(new GlideEngine())//加载方式

                 .capture(true) //是否提供拍照功能
                .captureStrategy(new CaptureStrategy(true, "test.bawei.com.photoselector.filefrovider"))//存储到哪里
                .forResult(REQUEST_CODE_CHOOSE);//请求码
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);

            Log.e("Matisse", "mSelected: " + mSelected);
        }

    }
}
