package com.example.battlepowerproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.battlepowerproject.ui.theme.BattlePowerProjectTheme

data class Personagem(val nome: String, val poderLuta: Int, val imagem: Painter)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BattlePowerProjectTheme {
                BattlePowerCalculator()
            }
        }
    }
}

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    width: Dp = 150.dp,
    height: Dp = 50.dp,
    backgroundColor: Color = Color.Red,
    fontSize: TextUnit = 16.sp,
    textColor: Color = Color.White,
    fontFamily: FontFamily = FontFamily.Monospace,
    fontWeight: FontWeight = FontWeight.SemiBold,
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(width)
            .height(height),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = text, color = textColor,
            fontSize = fontSize,
            fontFamily = fontFamily,
            fontWeight = fontWeight,
        )
    }
}

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    valueOf: String,
    changeValue: (String) -> Unit,
    labelText: String
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0x66FF0000),
                        Color(0x33FF0000)
                    )
                )
            )
            .border(
                width = 2.dp,
                brush = Brush.horizontalGradient(
                    colors = listOf(Color.Red, Color.Yellow)
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                value = valueOf,
                label = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = labelText, color = Color.White, textAlign = TextAlign.Center,
                        fontSize = 24.sp
                    )
                },
                modifier = modifier.fillMaxSize(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Red,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(fontSize = 24.sp, color = Color.White), maxLines = 1,
                onValueChange = {
                    changeValue(it)
                }
            )
        }
    }
}

@Composable
fun customTitle(): AnnotatedString {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Serif,
                fontSize = 128.sp,
            )
        ) {
            append("DBZ ")
        }
        withStyle(
            style = SpanStyle(
                color = Color.White,
                fontWeight = FontWeight.Normal,
            )
        ) {
            append("Power Calculator ")
        }
    }
    return annotatedString
}

@Composable
private fun BattlePowerCalculator() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Plano de fundo",
            modifier = Modifier
                .fillMaxSize()
                .blur(2.dp),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = customTitle(),
                fontSize = 36.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(6.0f, 9.0f),
                        blurRadius = 20f
                    )
                ),
                modifier = Modifier
                    .padding(12.dp)
                    .shadow(4.dp, shape = RoundedCornerShape(16.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(Color.Red, Color.Yellow),
                        ), shape = RoundedCornerShape(16.dp)
                    )
            )

            var poderLutaUsuario by remember {
                mutableStateOf("")
            }

            var mostrarPersonagens by remember { mutableStateOf(false) }

            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                valueOf = poderLutaUsuario,
                changeValue = { novoValor -> poderLutaUsuario = novoValor },
                labelText = "Insira o seu poder de luta:",
            )
            CustomButton(
                "Calcular",
                { mostrarPersonagens = true },
                200.dp,
                50.dp,
                Color.Red,
                24.sp
            )

            if (mostrarPersonagens) {
                Text(
                    text = "Você consegue derrotar esses personagens: ",
                    fontSize = 36.sp, color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = 32.sp
                )

                MostrarPersonagem(
                    poderLutaUsuario = poderLutaUsuario,
                    listaPersonagens()
                )

            }
        }
    }
}

@Preview
@Composable
private fun BattlePowerCalculatorPreview() {
    BattlePowerCalculator()
}

@Composable
fun listaPersonagens(): List<Personagem> {
    return listOf(
        Personagem("Bulma", 12, painterResource(id = R.drawable.bulma)),
        Personagem("Kuririn", 1770, painterResource(id = R.drawable.kuririn_early)),
        Personagem("Gohan (Early)", 2800, painterResource(id = R.drawable.gohan_early)),
        Personagem("Piccolo (Early)", 3500, painterResource(id = R.drawable.piccolo_early)),
        Personagem("Nappa", 4000, painterResource(id = R.drawable.nappa)),
        Personagem("Goku (Early)", 8001, painterResource(id = R.drawable.goku_early)),
        Personagem("Vegeta (Early)", 18000, painterResource(id = R.drawable.vegeta_early)),
        Personagem("Kuririn (Freeza)", 75000, painterResource(id = R.drawable.kuririn_freeza)),
        Personagem("Gohan (Freeza)", 200000, painterResource(id = R.drawable.gohan_freeza)),
        Personagem("Ginyu", 120000, painterResource(id = R.drawable.ginyu)),
        Personagem("Freeza (Base)", 520000, painterResource(id = R.drawable.freeza_base)),
        Personagem("Freeza (2° Forma)", 1000000, painterResource(id = R.drawable.freeza_2form)),
        Personagem("Piccolo (Freeza)", 1400000, painterResource(id = R.drawable.piccolo_freeza)),
        Personagem("Vegeta (Freeza)", 2400000, painterResource(id = R.drawable.vegeta_freeza)),
        Personagem("Freeza (3° Forma)", 2400000, painterResource(id = R.drawable.freeza_3form)),
        Personagem("Freeza (100%)", 120000000, painterResource(id = R.drawable.freeza_100)),
        Personagem("Goku (SSJ)", 150000000, painterResource(id = R.drawable.goku_ssj1)),
    )
}

@Composable
private fun MostrarPersonagem(poderLutaUsuario: String, lista: List<Personagem>) {
    val poderUsuarioInt = poderLutaUsuario.toIntOrNull()

    if (poderUsuarioInt == null) {
        Text(
            text = "Insira um valor numérico válido para o poder de luta!",
            fontSize = 36.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            lineHeight = 64.sp
        )
        return

    } else if (poderUsuarioInt < listaPersonagens().get(0).poderLuta) {
        Text(
            text = "Você deveria treinar mais na sala do tempo",
            fontSize = 36.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            lineHeight = 32.sp
        )
        return
    }

    LazyColumn {
        lista.forEach { personagem ->
            if (personagem.poderLuta <= poderLutaUsuario.toInt()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Spacer(modifier = Modifier.height(75.dp))

                        Text(
                            text = personagem.nome,
                            fontSize = 64.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            minLines = 2,
                            lineHeight = 64.sp,
                            modifier = Modifier.fillMaxWidth(),
                        )


                        Text(
                            text = "Poder: ${personagem.poderLuta}",
                            fontSize = 48.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )

                        Spacer(modifier = Modifier.height(25.dp))

                        Image(
                            painter = personagem.imagem,
                            contentDescription = personagem.nome,
                            modifier = Modifier.size(300.dp)
                        )
                    }
                }
            }
        }
    }
}