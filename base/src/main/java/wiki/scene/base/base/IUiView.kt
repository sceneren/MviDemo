package wiki.scene.base.base

interface IUiView {
    fun initView()
    fun beforeLoadData()
    fun loadData()
    fun lazyLoadTime(): Long
}