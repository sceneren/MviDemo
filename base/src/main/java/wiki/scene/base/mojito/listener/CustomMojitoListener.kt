package wiki.scene.base.mojito.listener

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import coil.imageLoader
import coil.request.ImageRequest
import com.blankj.utilcode.util.ImageUtils
import com.blankj.utilcode.util.LogUtils
import com.casad.common.resources.toXmlString
import com.kongzue.dialogx.dialogs.BottomMenu
import com.kongzue.dialogx.dialogs.TipDialog
import com.kongzue.dialogx.dialogs.WaitDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import net.mikaelzero.mojito.MojitoView
import net.mikaelzero.mojito.interfaces.OnMojitoListener
import wiki.scene.base.R

class CustomMojitoListener(private val imageUrls: List<String>) : OnMojitoListener {
    override fun onClick(view: View, x: Float, y: Float, position: Int) {
    }

    override fun onDrag(view: MojitoView, moveX: Float, moveY: Float) {
    }

    override fun onLongClick(
        fragmentActivity: FragmentActivity?,
        view: View,
        x: Float,
        y: Float,
        position: Int
    ) {
        BottomMenu.show(listOf(R.string.base_save_to_mobile.toXmlString()))
            .setCancelButton(R.string.base_cancel.toXmlString())
            .setOnMenuItemClickListener { _, _, _ ->
                fragmentActivity?.let { mActivity ->
                    mActivity.lifecycleScope.launch {
                        flow {
                            val request = ImageRequest.Builder(mActivity)
                                .data(imageUrls[position])
                                .build()
                            val drawable = mActivity.imageLoader.execute(request).drawable
                            emit(drawable)
                        }.mapNotNull {
                            val file = ImageUtils.save2Album(
                                (it as BitmapDrawable).bitmap,
                                Bitmap.CompressFormat.PNG
                            )
                            file
                        }
                            .flowOn(Dispatchers.IO)
                            .onStart {
                                WaitDialog.show(R.string.base_loading)
                            }
                            .catch {
                                TipDialog.show(R.string.base_save_failed, WaitDialog.TYPE.ERROR)
                            }
                            .collect { file ->
                                if (file.exists()) {
                                    LogUtils.e(file.absolutePath)
                                    TipDialog.show(
                                        R.string.base_save_successful,
                                        WaitDialog.TYPE.SUCCESS
                                    )
                                } else {
                                    TipDialog.show(
                                        R.string.base_save_failed,
                                        WaitDialog.TYPE.ERROR
                                    )
                                }
                            }
                    }
                }

                false
            }
    }

    override fun onLongImageMove(ratio: Float) {
    }

    override fun onMojitoViewFinish(pagePosition: Int) {
    }

    override fun onShowFinish(mojitoView: MojitoView, showImmediately: Boolean) {
    }

    override fun onStartAnim(position: Int) {
    }

    override fun onViewPageSelected(position: Int) {
    }


}