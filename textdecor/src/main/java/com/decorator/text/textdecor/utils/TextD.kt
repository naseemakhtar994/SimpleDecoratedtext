package com.decorator.text.textdecor

import android.content.Context
import android.graphics.BlurMaskFilter
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.*
import com.decorator.text.textdecor.utils.CustomTypefaceSpan
import com.decorator.text.textdecor.utils.FontUtil
import java.util.*

/**
 * with this class you can create text decoration rules that you can aplly to you text
 */
class TextD(vararg decorations: Decoration) {
    var decors: Array<Decoration> = decorations as Array<Decoration>
    private val strings = ArrayList<String>()
    fun decorateText(spannableString: SpannableString, firstCharIndex: Int, lastCharIndex: Int) {
        for (characterStyle in decors) {
            spannableString.setSpan(characterStyle.newDecorInstance(), firstCharIndex, lastCharIndex, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        }
    }

    fun getText(): String {
      val text = strings[0]
        strings.removeAt(0)
        return text
    }

    fun withText(text: String):TextD {
        strings.add(text)
        return this
    }

    companion object {

        fun UNDERLINE() = object : Decoration {
            override fun newDecorInstance(): CharacterStyle {
                return UnderlineSpan()
            }
        }

        fun STRINKE() = object : Decoration {
            override fun newDecorInstance(): CharacterStyle {
                return StrikethroughSpan()
            }
        }

        fun SUBSCRIPT() = object : Decoration {
            override fun newDecorInstance(): CharacterStyle {
                return SubscriptSpan()
            }
        }

        fun SUPERSCRIPT() = object : Decoration {
            override fun newDecorInstance(): CharacterStyle {
                return SuperscriptSpan()
            }
        }

        fun BOLD() = object : Decoration {
            override fun newDecorInstance(): CharacterStyle {
                return StyleSpan(Typeface.BOLD)
            }
        }

        fun ITALIC() = object : Decoration {
            override fun newDecorInstance(): CharacterStyle {
                return StyleSpan(Typeface.ITALIC)
            }
        }

        fun ITALIC_BOLD() = object : Decoration {
            override fun newDecorInstance(): CharacterStyle {
                return StyleSpan(Typeface.BOLD_ITALIC)
            }
        }

        fun setBlur(radius: Int, style: BlurMaskFilter.Blur) = object : Decoration {
            override fun newDecorInstance(): CharacterStyle {
                return MaskFilterSpan(BlurMaskFilter(radius.toFloat(), style))
            }
        }

        fun absoluteTextSize(size: Int) = object : Decoration {
            override fun newDecorInstance(): CharacterStyle {
                return AbsoluteSizeSpan(size)
            }
        }

        fun relativeTextSize(size: Int) = object : Decoration {
            override fun newDecorInstance(): CharacterStyle {
                return AbsoluteSizeSpan(size)
            }
        }

        fun font(context: Context, font: String) = object : Decoration {
            override fun newDecorInstance(): CharacterStyle {
                return CustomTypefaceSpan(font, FontUtil.get(context, font))
            }
        }

        fun setTextColor(color: Int) = object : Decoration {
            override fun newDecorInstance(): CharacterStyle {
                return ForegroundColorSpan(color)
            }
        }

        fun setBackground(color: Int) = object : Decoration {
            override fun newDecorInstance(): CharacterStyle {
                return BackgroundColorSpan(color)
            }
        }

    }


}
