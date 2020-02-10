package com.example.jewell.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.jewell.R

import kotlinx.android.synthetic.main.fragment_screen_slide_page.*

private const val ARG_TITLE = "arg_title"
private const val ARG_BG_COLOR = "arg_bg_color"

class ScreenSlidePageFragment : Fragment() {
    private var title: String? = "Default title."
    private var bgColorResId: Int = R.color.blue_inactive

    private lateinit var inflatedView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            bgColorResId = it.getInt(ARG_BG_COLOR)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflatedView = inflater.inflate(R.layout.fragment_screen_slide_page, container, false)
        return inflatedView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        inflatedView.setBackgroundColor(ContextCompat.getColor(context!!, bgColorResId))
        screen_slide_title.text = title
    }

    companion object {
        @JvmStatic
        fun newInstance(title: String, bgColorId: Int) =
            ScreenSlidePageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putInt(ARG_BG_COLOR, bgColorId)
                }
            }
    }
}