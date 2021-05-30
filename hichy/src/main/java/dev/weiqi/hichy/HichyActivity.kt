package dev.weiqi.hichy

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity


internal class HichyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hichy)
        setFinishOnTouchOutside(true)
        val stack = Hichy.activityStack
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
                        printChildFragmentRecursive(1, sb, fragment)
                    }
                }
            }
        }

        findViewById<TextView>(R.id.text_view_hierarchy)?.apply {
            text = sb.toString()
            movementMethod = ScrollingMovementMethod()
            setHorizontallyScrolling(true)
        }
    }

    private fun printChildFragmentRecursive(
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
                printChildFragmentRecursive(depth + 1, sb, childFragment)
            }
        }
    }

    companion object {
        private const val GLIDE_FRAGMENT_NAME = "SupportRequestManagerFragment"
    }
}