package com.neihanjetpack.base.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.neihanjetpack.MyApplication
import com.neihanjetpack.R
import de.hdodenhof.circleimageview.CircleImageView


/**
 *
 * @Author:        xiegang
 * @CreateDate:     2020/1/9
 * @Description:
 */
class BingDingAdapter {
    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(view: CircleImageView, url: String) {
            url?.apply {
                GlideUtil.loadUrlOrPath(
                    MyApplication.getInstance().applicationContext,
                    url,
                    view,
                    null,
                    null
                )
            }

        }
    }
}