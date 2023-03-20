package hu.zsoltkiss.myproducts.ui.crud

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TabContentCreate(
    onProductCreate: (String, String, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        val nameState = remember { mutableStateOf(TextFieldValue()) }
        val descriptionState = remember { mutableStateOf(TextFieldValue()) }
        val quantityState = remember { mutableStateOf(TextFieldValue()) }
        val nameError = remember { mutableStateOf(false) }
        val descriptionError = remember { mutableStateOf(false) }
        val quantityError = remember { mutableStateOf(false) }
        val errorMessageState = remember { mutableStateOf(TextFieldValue()) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Enter product details",
                style = TextStyle(
                    fontSize = 24.sp
                )
            )
        }

        TextField(
            value = nameState.value,
            onValueChange = { nameState.value = it },
            placeholder = {
                Text(
                    text = "Enter product name",
                    style = TextStyle(
                        fontStyle = FontStyle.Italic,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                )
            },
            isError = nameError.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        )

        Spacer(modifier = Modifier.height(5.dp))


        TextField(
            value = descriptionState.value,
            onValueChange = { descriptionState.value = it },
            placeholder = {
                Text(
                    text = "Enter product description",
                    style = TextStyle(
                        fontStyle = FontStyle.Italic,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                )
            },
            isError = descriptionError.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        )

        Spacer(modifier = Modifier.height(5.dp))


        TextField(
            value = quantityState.value,
            onValueChange = { quantityState.value = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = {
                Text(
                    text = "Enter quantity (numbers only)",
                    style = TextStyle(
                        fontStyle = FontStyle.Italic,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                )
            },
            isError = quantityError.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        )

        Text(
            text = errorMessageState.value.text,
            style = TextStyle(
                color = Color.Red,
                fontSize = 14.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 20.dp)
        )


        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {

                    nameError.value = nameState.value.text.isEmpty()
                    descriptionError.value = descriptionState.value.text.isEmpty()
                    quantityError.value = quantityState.value.text.isEmpty()

                    if (nameError.value || descriptionError.value || quantityError.value) {
                        errorMessageState.value = TextFieldValue("Mandatory field")
                    } else {
                        errorMessageState.value = TextFieldValue("")
                        onProductCreate(
                            nameState.value.text,
                            descriptionState.value.text,
                            quantityState.value.text.toInt()
                        )
                    }
                }
            ) {
                Text("Create Product")
            }
        }

    }
}