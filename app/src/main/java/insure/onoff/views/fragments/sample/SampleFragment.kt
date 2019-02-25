/**
 * @copyright ©2019 Onoff Insurance All rights reserved. Trade Secret, Confidential and Proprietary.
 *            Any dissemination outside of Onoff Insurance is strictly prohibited.
 */
package insure.onoff.views.fragments.sample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import insure.onoff.data.models.sample.Request
import insure.onoff.databinding.FragmentSampleBinding
import insure.onoff.utilities.InjectorUtils
import insure.onoff.viewmodels.sample.SampleViewModel

/**
 * SampleFragment
 *
 * This class is responsible to display Fragment
 *
 * Note : This class is temporary to understand Jetpack architecture as simple as possible
 *
 * @author    Andika Kurniawan  <andikakurniawan@onoff.insure>
 */

class SampleFragment : Fragment() {
    private lateinit var viewModel: SampleViewModel
    private lateinit var binding: FragmentSampleBinding

    /*
     * To display fragment and configure needed variables
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSampleBinding.inflate(inflater, container, false)

        val context = context ?: return binding.root
        val factory = InjectorUtils.provideSampleViewModelFactory(context)

        viewModel = ViewModelProviders.of(this, factory).get(SampleViewModel::class.java)

        displayAccountDetail()

        return binding.root
    }

    /*
     * To call sample API by calling view model
     */
    private fun displayAccountDetail() {
        val request: Request = Request("morpheus", "leader")

        viewModel.register(request).observe(viewLifecycleOwner, Observer { responseData ->
            if (responseData != null) {
                binding.userDetail = responseData
            }
        })
    }
}
