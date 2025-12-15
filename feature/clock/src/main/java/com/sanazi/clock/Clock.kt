package com.sanazi.clock

import android.icu.util.Calendar
import android.os.CountDownTimer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Clock(modifier: Modifier = Modifier) {
    val time = remember { mutableLongStateOf(5407) }
    val second = remember { derivedStateOf { time.longValue % 60 } }
    val minute = remember { derivedStateOf { time.longValue / 60f % 60 } }
    val hour = remember { derivedStateOf { time.longValue / 60f / 60f % 12 } }
    LaunchedEffect(Unit) {
        object : CountDownTimer(1000, 1000) {
            override fun onFinish() {
                this.start()
            }

            override fun onTick(p0: Long) {
                time.longValue = Calendar.getInstance().time.time
            }
        }.start()
    }
    Box(
        modifier.size(300.dp)
            .border(2.dp, DarkGrey, RoundedCornerShape(50))
            .padding(5.dp)
            .background(DarkGrey, androidx.compose.foundation.shape.RoundedCornerShape(50))
    ) {
        repeat(60) {
            Box(
                Modifier.height(6.dp).width(1.dp)
                    .rotate(-it * 360f / 60)
                    .background(Grey, androidx.compose.foundation.shape.RoundedCornerShape(50))
                    .align { size, space, layoutDirection ->
                        val padding = 0.03f
                        IntOffset(
                            ((space.width - size.width) * ((1f - 2 * padding) * (0.5f + sin(it * PI / 60 * 2) / 2) + padding)).toInt(),
                            ((space.height - size.height) * ((1f - 2 * padding) * (0.5f + cos(it * PI / 60 * 2) / 2) + padding)).toInt()
                        )
                    }
            )
        }
        repeat(12) {
            Box(
                Modifier.height(10.dp).width(2.dp)
                    .rotate(-it * 360f / 12)
                    .background(LightGrey, androidx.compose.foundation.shape.RoundedCornerShape(50))
                    .align { size, space, layoutDirection ->
                        val padding = 0.03f
                        IntOffset(
                            ((space.width - size.width) * ((1f - 2 * padding) * (0.5f + sin(it * PI / 12 * 2) / 2) + padding)).toInt(),
                            ((space.height - size.height) * ((1f - 2 * padding) * (0.5f + cos(it * PI / 12 * 2) / 2) + padding)).toInt()
                        )
                    }
            )
        }
        repeat(12) {
            Text(
                (it + 1).toString(),
                Modifier
                    .align { size, space, layoutDirection ->
                        val padding = 0.08f
                        IntOffset(
                            ((space.width - size.width) * ((1f - 2 * padding) * (0.5f + sin((it + 1) * PI / 12 * 2) / 2) + padding)).toInt(),
                            ((space.height - size.height) * ((1f - 2 * padding) * (0.5f - cos((it + 1) * PI / 12 * 2) / 2) + padding)).toInt()
                        )
                    },
                fontSize = 20.sp,
                color = White
            )
        }
        Box(
            Modifier.fillMaxHeight().align(Alignment.Center)
                .rotate(minute.value * 360f / 60)
        ) {
            Image(
                painterResource(R.drawable.triatangle), null,
                Modifier.fillMaxHeight(0.52f).width(5.dp)
                    .rotate(180f),
                contentScale = ContentScale.FillBounds,
                colorFilter = ColorFilter.tint(MinuteColor)
            )
        }
        Box(
            Modifier.fillMaxHeight().align(Alignment.Center)
                .rotate(hour.value * 360f / 12)
        ) {
            Image(
                painterResource(R.drawable.triatangle), null,
                Modifier.fillMaxHeight(0.32f).width(7.dp)
                    .align { size, space, layoutDirection ->
                        IntOffset(
                            (space.width - size.width) / 2,
                            ((space.height - size.height) * 0.3f).toInt()
                        )
                    }
                    .rotate(180f),
                contentScale = ContentScale.FillBounds,
                colorFilter = ColorFilter.tint(HourColor)
            )
        }
        Box(
            Modifier.fillMaxHeight().align(Alignment.Center)
                .rotate(second.value * 360f / 12)
        ) {
            Box(
                Modifier.fillMaxHeight(0.44f).width(2.dp)
                    .align { size, space, layoutDirection ->
                        IntOffset(
                            (space.width - size.width) / 2,
                            ((space.height - size.height) * 0.2f).toInt()
                        )
                    }
                    .rotate(180f)
                    .background(SecondColor),
            )
        }
    }
}