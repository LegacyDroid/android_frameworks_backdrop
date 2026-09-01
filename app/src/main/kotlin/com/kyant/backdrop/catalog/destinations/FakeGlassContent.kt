package com.kyant.backdrop.catalog.destinations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kyant.backdrop.catalog.BackdropDemoScaffold
import com.kyant.backdrop.catalog.components.FakeGlass
import com.kyant.shapes.Capsule
import com.kyant.shapes.RoundedRectangle

@Composable
fun FakeGlassContent() {
    BackdropDemoScaffold { backdrop ->
        Column(
            Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(24f.dp),
            verticalArrangement = Arrangement.spacedBy(16f.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BasicText(
                "Fake Glass",
                style = TextStyle(Color.White, 22f.sp)
            )
            BasicText(
                "Battery saver · Low-end friendly",
                style = TextStyle(Color.White.copy(alpha = 0.6f), 14f.sp)
            )

            FakeGlass(
                modifier = Modifier
                    .width(280f.dp)
                    .height(80f.dp),
                surfaceColor = Color.White.copy(alpha = 0.12f),
                highlightColor = Color.White.copy(alpha = 0.38f),
                highlightWidth = 0.5f.dp,
                cornerRadius = 24f.dp
            ) {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    BasicText(
                        "Translucent Surface",
                        style = TextStyle(Color.White, 15f.sp)
                    )
                }
            }

            FakeGlass(
                modifier = Modifier
                    .size(120f.dp),
                shape = Capsule(),
                surfaceColor = Color(0xFF0088FF).copy(alpha = 0.2f),
                highlightColor = Color.White.copy(alpha = 0.5f),
                highlightWidth = 1f.dp,
                cornerRadius = 60f.dp
            ) {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    BasicText(
                        "Capsule",
                        style = TextStyle(Color.White, 13f.sp)
                    )
                }
            }

            FakeGlass(
                modifier = Modifier
                    .width(280f.dp)
                    .height(80f.dp),
                surfaceColor = Color.Black.copy(alpha = 0.3f),
                highlightColor = Color.White.copy(alpha = 0.25f),
                highlightWidth = 0.5f.dp,
                cornerRadius = 16f.dp
            ) {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    BasicText(
                        "Dark Tint",
                        style = TextStyle(Color.White, 15f.sp)
                    )
                }
            }

            FakeGlass(
                modifier = Modifier
                    .width(280f.dp)
                    .height(80f.dp),
                surfaceColor = Color(0xFFFF8D28).copy(alpha = 0.15f),
                highlightColor = Color.White.copy(alpha = 0.45f),
                highlightWidth = 1f.dp,
                cornerRadius = 32f.dp
            ) {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    BasicText(
                        "Warm Accent",
                        style = TextStyle(Color.White, 15f.sp)
                    )
                }
            }
        }
    }
}
