package dev.weiqi.hichy

import android.graphics.Typeface
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.TypedValue
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity


internal class HichyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this).apply {
            setBackgroundColor(ContextCompat.getColor(this@HichyActivity, android.R.color.white))
            setTextColor(ContextCompat.getColor(this@HichyActivity, android.R.color.black))
            setTypeface(Typeface.MONOSPACE, Typeface.BOLD)
            setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.hichy_dimen_20dp)
            )
            setHorizontallyScrolling(true)
            movementMethod = ScrollingMovementMethod()
        }
        setContentView(textView)
        setFinishOnTouchOutside(true)
        val stack = Hichy.activityStack
        if (stack.isEmpty()) {
            finish()
            return
        }
        val sb = StringBuilder()
        stack.forEach { activity ->
            sb.append("\n")
                .append("◆")
                .append(activity.javaClass.simpleName)
            if (activity is FragmentActivity) {
                val fm = activity.supportFragmentManager
                val fragments = fm.fragments
                fragments.forEach { fragment ->
                    val simpleName = fragment.javaClass.simpleName
                    if (simpleName != GLIDE_FRAGMENT_NAME) {
                        sb.append("\n")
                            .append("┗╸")
                            .append(simpleName)
                        appendChildFragmentNameRecursively(1, sb, fragment)
                    }
                }
            }
        }

        textView.text = sb.toString()
    }

    private fun appendChildFragmentNameRecursively(
        depth: Int,
        sb: StringBuilder,
        parentFragment: Fragment
    ) {
        val childFragments = parentFragment.childFragmentManager.fragments
        childFragments.forEach { childFragment ->
            val simpleName = childFragment.javaClass.simpleName
            if (simpleName != GLIDE_FRAGMENT_NAME) {
                sb.append("\n")
                repeat(depth) {
                    sb.append(' ')
                }
                sb.append("┗╸")
                sb.append(simpleName)
                appendChildFragmentNameRecursively(depth + 1, sb, childFragment)
            }
        }
    }

    companion object {
        private const val GLIDE_FRAGMENT_NAME = "SupportRequestManagerFragment"
    }
}