package su.leff.businesssuccesshometest.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.FrameLayout

class CircleFrameLayout(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int?) :
    FrameLayout(context, attributeSet, defStyleAttr ?: 0) {

    constructor(context: Context) : this(context, null, null)

    constructor(context: Context, attributeSet: AttributeSet) : this(context, attributeSet, null)

    init {
        // https://stackoverflow.com/questions/7781892/own-defined-layout-ondraw-method-not-getting-called/7784369#7784369
        setWillNotDraw(false)
    }

    private val paint = Paint()

    var color: Int = Color.rgb(0, 0, 0)
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val x = width
        val y = height
        paint.style = Paint.Style.FILL
        paint.color = color
        canvas?.drawCircle(x / 2.toFloat(), y / 2.toFloat(), x / 2.toFloat(), paint)
    }
}