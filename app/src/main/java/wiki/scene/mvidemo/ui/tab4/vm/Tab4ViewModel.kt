package wiki.scene.mvidemo.ui.tab4.vm

import com.airbnb.mvrx.MavericksState
import com.blankj.utilcode.util.LogUtils
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toFlowResponse
import wiki.scene.base.MvRxViewModel
import wiki.scene.base.net.config.ApiConfig
import wiki.scene.base.net.errorMsg
import wiki.scene.mvidemo.ui.tab4.entity.BannerInfo

data class Tab4Status(val uiState: Int = 0) : MavericksState

class Tab4ViewModel(initialState: Tab4Status) : MvRxViewModel<Tab4Status>(initialState) {
    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            RxHttp.get(ApiConfig.ARTICLE)
                .toFlowResponse<MutableList<BannerInfo>>()
                .onStart {
                    setState { copy(uiState = 0) }
                }
                .catch {
                    LogUtils.e("===>${it.errorMsg}")
                    setState { copy(uiState = 2) }
                }.collect {
                    setState {
                        copy(
                            uiState = if (it.size > 0) {
                                3
                            } else {
                                1
                            }
                        )
                    }
                    LogUtils.e(it.toString())
                }
        }

    }
}