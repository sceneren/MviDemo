package wiki.scene.mvidemo.ui.login.viewmodel.status

import com.airbnb.mvrx.MavericksState

data class LoginState(
    val userInfo: String = "",
    val position: Int = 0
) : MavericksState {
    //会自动调用并赋值
    constructor(position: Int) : this(userInfo = "", position = position)
}