package com.ksp.kspm.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.ksp.kspm.base.viewModel.BaseViewModel
import com.ksp.kspm.listener.BackPressListener
import com.ksp.kspm.utils.Helper.isLocationPermissionAvailable
import com.ksp.kspm.utils.interfaces.NetworkValidListener
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel, VDB : ViewDataBinding> : DaggerFragment(),
    BackPressListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: VM

    lateinit var dataBinding: VDB

    @get:LayoutRes
    protected abstract val layoutRes: Int

    protected abstract val bindingVariable: Int

    protected abstract val viewModelClass: Class<VM>

    protected lateinit var networkValidListener: NetworkValidListener

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(viewModelClass)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        dataBinding.lifecycleOwner = this
        return dataBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showActionBar()
        dataBinding.setVariable(bindingVariable, viewModel)
        dataBinding.executePendingBindings()
    }

    private fun showActionBar() {
        if (activity is AppCompatActivity) {
            if (hasActionBar()) {
                (activity as AppCompatActivity).supportActionBar?.show()
            } else {
                (activity as AppCompatActivity).supportActionBar?.hide()
            }
        }
    }

    protected fun initProgress() {
        (viewModel as BaseViewModel).apiRequestInProgress.observe(viewLifecycleOwner, {
            if (it) {
                showProgress()
            } else {
                hideProgress()
            }
        })
    }

    abstract fun showProgress()
    abstract fun hideProgress()

    override fun onBackPressed(): Boolean {
        return false
    }

    fun isLocationPermissionAvailable() =
        dataBinding.root.context.isLocationPermissionAvailable()


    open fun updateFavouriteStatus(restaurantId: String, newStatus: Int) {
        Timber.d("============ $restaurantId anddd --------- $newStatus")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity is NetworkValidListener) {
            networkValidListener = activity as NetworkValidListener
        }
    }

    fun networkValidListenerInitialized() = ::networkValidListener.isInitialized

    open fun hasActionBar() = true
}