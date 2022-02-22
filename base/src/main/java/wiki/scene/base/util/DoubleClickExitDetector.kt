package wiki.scene.base.util

import com.blankj.utilcode.util.StringUtils
import wiki.scene.base.R
import wiki.scene.base.toast.showToast

class DoubleClickExitDetector constructor(// 提示消息
    private var hintMessage: String = StringUtils.getString(R.string.base_press_again_exit), // 有效的间隔时间，单位毫秒
    private var effectiveIntervalTime: Int = 2000
) {
    //上次点击时间
    private var lastClickTime: Long = 0

    constructor(hintContent: String) : this(hintContent, 2000)

    fun click(): Boolean {
        val currentTime = System.currentTimeMillis()
        val result = currentTime - lastClickTime < effectiveIntervalTime
        lastClickTime = currentTime
        if (!result) {
            showToast(hintMessage)
        }
        return result
    }

    fun setEffectiveIntervalTime(effectiveIntervalTime: Int) {
        this.effectiveIntervalTime = effectiveIntervalTime
    }

    fun setHintMessage(hintMessage: String) {
        this.hintMessage = hintMessage
    }

}