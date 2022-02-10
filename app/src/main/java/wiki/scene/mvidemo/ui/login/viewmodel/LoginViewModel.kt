package wiki.scene.mvidemo.ui.login.viewmodel

import android.util.Log
import com.airbnb.mvrx.MavericksViewModel
import wiki.scene.mvidemo.ui.login.viewmodel.status.LoginState

class LoginViewModel(state: LoginState) : MavericksViewModel<LoginState>(state) {
    fun login(username: String, password: String) = setState {
        Log.e("LoginViewModel", "login")
        if (username == "admin" && password == "123456") {
            copy(userInfo = "登录成功")
        } else {
            copy(userInfo = "登录失败")
        }
    }
}