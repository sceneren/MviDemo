package wiki.scene.mvidemo.ui.tab4

import android.view.LayoutInflater
import android.view.ViewGroup
import com.chad.library.adapter.base.binder.QuickViewBindingItemBinder
import wiki.scene.mvidemo.databinding.FragmentTab4ItemBinding

class Tab4ItemBinder : QuickViewBindingItemBinder<String, FragmentTab4ItemBinding>() {

    override fun convert(holder: BinderVBHolder<FragmentTab4ItemBinding>, data: String) {
        holder.viewBinding.btnTest.text = data
    }

    override fun onCreateViewBinding(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): FragmentTab4ItemBinding {
        return FragmentTab4ItemBinding.inflate(layoutInflater, parent, false)
    }


}