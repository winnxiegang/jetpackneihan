package com.neihanjetpack;

import android.content.Context;
import android.os.Environment;

import androidx.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.AppGlideModule;

import java.util.Objects;

/**
 * @Author: xiegang
 * @CreateDate: 2020/1/9
 * @Description:
 */
@GlideModule
public class MyAppGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(@NonNull Context context, GlideBuilder builder) {
        try {
            int diskCacheSizeBytes = 1024 * 1024 * 1024; // 1GB
            MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context)
                    .setMemoryCacheScreens(2)
                    .build();
            builder.setMemoryCache(new LruResourceCache(calculator.getMemoryCacheSize()));
            builder.setDiskCache(new DiskLruCacheFactory(getDisCache(context), diskCacheSizeBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getDisCache(Context context) {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ?
                Objects.requireNonNull(context.getExternalCacheDir()).getAbsolutePath() : context.getCacheDir().getAbsolutePath();
    }
}
