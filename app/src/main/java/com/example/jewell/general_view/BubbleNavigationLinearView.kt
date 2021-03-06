package com.example.jewell.general_view

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.util.SparseArray
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.example.jewell.general_view.listener.BubbleNavigationChangeListener
import java.util.*

class BubbleNavigationLinearView : LinearLayout, View.OnClickListener, IBubbleNavigation {
    override fun getCurrentActiveItemPosition(): Int {
        return currentActiveItemPosition
    }

    private var bubbleNavItems: ArrayList<BubbleToggleView>? = null
    private var navigationChangeListener: BubbleNavigationChangeListener? = null

    private var currentActiveItemPosition = 0
    private var loadPreviousState: Boolean = false

    private var currentTypeface: Typeface? = null

    private var pendingBadgeUpdate: SparseArray<String>? = null

    constructor(@NonNull context: Context) : super(context) {
        init(context, null)
    }

    constructor(@NonNull context: Context, @Nullable attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(@NonNull context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    override fun onSaveInstanceState(): Parcelable? {
        val bundle = Bundle()
        bundle.putParcelable("superState", super.onSaveInstanceState())
        bundle.putInt("current_item", currentActiveItemPosition)
        bundle.putBoolean("load_prev_state", true)
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        var state = state
        if (state is Bundle) {
            val bundle = state as Bundle?
            currentActiveItemPosition = bundle!!.getInt("current_item")
            loadPreviousState = bundle.getBoolean("load_prev_state")
            state = bundle.getParcelable("superState")
        }
        super.onRestoreInstanceState(state)
    }

    private fun init(context: Context, attrs: AttributeSet?) {

        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER

        post { updateChildNavItems() }
    }

    private fun updateChildNavItems() {
        bubbleNavItems = ArrayList<BubbleToggleView>()
        for (index in 0 until childCount) {
            val view = getChildAt(index)
            if (view is BubbleToggleView)
                bubbleNavItems!!.add(view as BubbleToggleView)
            else {
                return
            }
        }

        if (bubbleNavItems!!.size < MIN_ITEMS) {
            Log.w(
                TAG,
                "The bubbleNavItems list should have at least 2 bubbleNavItems of BubbleToggleView"
            )
        } else if (bubbleNavItems!!.size > MAX_ITEMS) {
            Log.w(
                TAG,
                "The bubbleNavItems list should not have more than 5 bubbleNavItems of BubbleToggleView"
            )
        }

        setClickListenerForItems()
        setInitialActiveState()
        updateMeasurementForItems()

        val curTF = currentTypeface
        if (curTF != null) {
            setTypeface(curTF)
        }

        if (pendingBadgeUpdate != null && bubbleNavItems != null) {
            for (i in 0 until pendingBadgeUpdate!!.size())
                setBadgeValue(pendingBadgeUpdate!!.keyAt(i), pendingBadgeUpdate!!.valueAt(i))
            pendingBadgeUpdate!!.clear()
        }
    }


    private fun setInitialActiveState() {

        if (bubbleNavItems == null) return

        var foundActiveElement = false

        // find the initial state
        if (!loadPreviousState) {
            for (i in bubbleNavItems!!.indices) {
                if (bubbleNavItems!![i].isActive() && !foundActiveElement) {
                    foundActiveElement = true
                    currentActiveItemPosition = i
                } else {
                    bubbleNavItems!![i].setInitialState(false)
                }
            }
        } else {
            for (i in bubbleNavItems!!.indices) {
                bubbleNavItems!![i].setInitialState(false)
            }
        }
        //set the active element
        if (!foundActiveElement)
            bubbleNavItems!![currentActiveItemPosition].setInitialState(true)
    }

    private fun updateMeasurementForItems() {
        val numChildElements = bubbleNavItems!!.size
        if (numChildElements > 0) {
            val calculatedEachItemWidth =
                (measuredWidth - (paddingRight + paddingLeft)) / numChildElements
            for (btv in bubbleNavItems!!)
                btv.updateMeasurements(calculatedEachItemWidth)
        }
    }


    private fun setClickListenerForItems() {
        for (btv in bubbleNavItems!!)
            btv.setOnClickListener(this)
    }


    private fun getItemPositionById(id: Int): Int {
        for (i in bubbleNavItems!!.indices)
            if (id == bubbleNavItems!![i].getId())
                return i
        return -1
    }


    override fun setNavigationChangeListener(navigationChangeListener: BubbleNavigationChangeListener) {
        this.navigationChangeListener = navigationChangeListener
    }


    override fun setTypeface(typeface: Typeface) {
        if (bubbleNavItems != null) {
            for (btv in bubbleNavItems!!)
                btv.setTitleTypeface(typeface)
        } else {
            currentTypeface = typeface
        }
    }


    override fun setCurrentActiveItem(position: Int) {

        if (bubbleNavItems == null) {
            currentActiveItemPosition = position
            return
        }

        if (currentActiveItemPosition == position) return

        if (position < 0 || position >= bubbleNavItems!!.size)
            return

        val btv = bubbleNavItems!![position]
        btv.performClick()
    }


    override fun setBadgeValue(position: Int, value: String?) {
        if (bubbleNavItems != null) {
            val btv = bubbleNavItems!![position]
            if (btv != null)
                btv!!.setBadgeText(value)
        } else {
            if (pendingBadgeUpdate == null)
                pendingBadgeUpdate = SparseArray()
            pendingBadgeUpdate!!.put(position, value)
        }
    }

    override fun onClick(v: View) {
        val changedPosition = getItemPositionById(v.id)
        if (changedPosition >= 0) {
            if (changedPosition == currentActiveItemPosition) {
                return
            }
            val currentActiveToggleView = bubbleNavItems!![currentActiveItemPosition]
            val newActiveToggleView = bubbleNavItems!![changedPosition]
            if (currentActiveToggleView != null)
                currentActiveToggleView!!.toggle()
            if (newActiveToggleView != null)
                newActiveToggleView!!.toggle()

            currentActiveItemPosition = changedPosition

            if (navigationChangeListener != null)
                navigationChangeListener!!.onNavigationChanged(v, currentActiveItemPosition)
        } else {
            Log.w(TAG, "Selected id not found! Cannot toggle")
        }
    }

    companion object {

        private val TAG = "BNLView"
        private val MIN_ITEMS = 2
        private val MAX_ITEMS = 5
    }
}
