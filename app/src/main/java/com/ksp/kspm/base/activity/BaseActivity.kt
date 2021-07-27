package com.ksp.kspm.base.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.ksp.kspm.base.viewModel.BaseViewModel
 import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
import javax.inject.Inject

private const val TAG = "BaseActivity"

abstract class BaseActivity<VM : BaseViewModel, VDB : ViewDataBinding> : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: VM

    lateinit var dataBinding: VDB

    @get:LayoutRes
    abstract val layoutRes: Int

    abstract val bindingVariable: Int

    abstract val viewModelClass: Class<VM>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, layoutRes)
        dataBinding.lifecycleOwner = this
        viewModel = ViewModelProvider(this, viewModelFactory).get(viewModelClass)
        dataBinding.setVariable(bindingVariable, viewModel)
        dataBinding.executePendingBindings()
        // observe title event
        observeTitleEvent()
        // observe api progress event
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (supportFragmentManager.fragments.size > 0) {
            supportFragmentManager.fragments.forEach {
                it.onActivityResult(requestCode, resultCode, data)
            }
        }
    }*/

    private fun observeTitleEvent() {
        (viewModel as BaseViewModel).title.observe(this, {
            setTitle(it)
        })
    }

    protected fun initProgress() {
        (viewModel as BaseViewModel).apiRequestInProgress.observe(this, {
            Timber.d("OBSERVER ----------------- showProgress $it")
            if (it) {
                Timber.d("----------------- showProgress $it")
                showProgress()
            } else {
                Timber.d("----------------- showProgress $it")
                hideProgress()
            }
        })
    }

    private fun setTitle(title: String) {
        supportActionBar?.let {
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowTitleEnabled(true)
            it.setLogo(null)
            it.title = title
        }
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        Log.d(TAG, "onOptionsItemSelected: ")
//        if (item.itemId == android.R.id.home)
//            super.onBackPressed()
//        return true
//    }

    abstract fun showProgress()
    abstract fun hideProgress()
    //override fun supportFragmentInjector() = dispatchingAndroidInjector


    open fun updateFavouriteStatus(restaurantId: String,newStatus:Int) {
        Timber.d("============ $restaurantId anddd --------- $newStatus")
    }
}