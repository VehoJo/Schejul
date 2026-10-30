package com.vehojo.schejul.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import android.app.TimePickerDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vehojo.schejul.R
import com.vehojo.schejul.ui.theme.Roboto
import com.vehojo.schejul.ui.theme.SchejulTheme

@Preview
@Composable
fun NewLesson() {
    SchejulTheme {
        Column(modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 15.dp)) {
            AddLesson()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddLesson() {
    var endTime by remember { mutableStateOf(TextFieldValue("")) }
    var startTime by remember { mutableStateOf(TextFieldValue("")) }
    var cabinetNum by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.icon_back),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(25.dp),
                contentDescription = "Back icon"
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Добавить урок",
                fontFamily = Roboto,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Отмена",
                fontFamily = Roboto,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
        Spacer(modifier = Modifier.height(17.5.dp))
        Text(
            text = "Номер урока",
            fontFamily = Roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(10.dp))

        var expanded by remember { mutableStateOf(false) }
        var selectedNum by remember { mutableStateOf("1") }

        val numbers = (1..10).map { it.toString() }

        Column {
            Box {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.background)
                        .border(
                            1.dp,
                            color = MaterialTheme.colorScheme.onSurface,
                            RoundedCornerShape(12.dp)
                        )
                        .clickable{expanded = !expanded}
                        .padding(horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = selectedNum,
                        fontFamily = Roboto,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.secondary
                    ) }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(12.dp)),
                    shape = RoundedCornerShape(12.dp),
                    containerColor = MaterialTheme.colorScheme.background,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface)
                ) {
                    numbers.forEach { number ->
                        DropdownMenuItem(
                            text = { Text(
                                text = number,
                                fontFamily = Roboto,
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.secondary
                            )},
                            onClick = {
                                selectedNum = number
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Предмет",
            fontFamily = Roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = subject,
            singleLine = true,
            onValueChange = { subject = it },
            shape = RoundedCornerShape(12.dp),
            placeholder = { Text("Название предмета") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
                focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                focusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.tertiary,
                unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row {
            Column {
                Text(
                    text = "Начало",
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = startTime,
                    singleLine = true,
                    onValueChange = { input ->
                        val digits = input.text.filter { it.isDigit() }.take(4)

                        val formatted = when {
                            digits.length > 2 ->
                                "${digits.substring(0, 2)}:${digits.substring(2)}"

                            else -> digits
                        }

                        val cursorPosition = when {
                            formatted.length > input.text.length ->
                                (input.selection.end + 1).coerceAtMost(formatted.length)

                            else ->
                                input.selection.end.coerceAtMost(formatted.length)
                        }

                        startTime = TextFieldValue(
                            text = formatted,
                            selection = androidx.compose.ui.text.TextRange(cursorPosition)
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = RoundedCornerShape(12.dp),
                    placeholder = { Text("ЧЧ:ММ") },
                    modifier = Modifier.width(170.dp).height(56.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
                        focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                        focusedTextColor = MaterialTheme.colorScheme.primary,
                        unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedLabelColor = MaterialTheme.colorScheme.tertiary,
                        unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        unfocusedContainerColor = MaterialTheme.colorScheme.background
                    ),
                    trailingIcon = {
                        IconButton(onClick = {
                            TimePickerDialog(
                                context,
                                { _, hour, minute ->
                                    startTime = TextFieldValue("%02d:%02d".format(hour, minute))
                                },
                                8,
                                30,
                                true
                            ).show()
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_time),
                                tint = MaterialTheme.colorScheme.tertiary,
                                modifier = Modifier.size(20.dp),
                                contentDescription = "Time Picker Activator"
                            )
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Column {
                Text(
                    text = "Окончание",
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = endTime,
                    singleLine = true,
                    onValueChange = { input ->
                        val digits = input.text.filter { it.isDigit() }.take(4)

                        val formatted = when {
                            digits.length > 2 ->
                                "${digits.substring(0, 2)}:${digits.substring(2)}"

                            else -> digits
                        }

                        val cursorPosition = when {
                            formatted.length > input.text.length ->
                                (input.selection.end + 1).coerceAtMost(formatted.length)

                            else ->
                                input.selection.end.coerceAtMost(formatted.length)
                        }

                        endTime = TextFieldValue(
                            text = formatted,
                            selection = androidx.compose.ui.text.TextRange(cursorPosition)
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = RoundedCornerShape(12.dp),
                    placeholder = { Text("ЧЧ:ММ") },
                    modifier = Modifier.width(170.dp).height(56.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
                        focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                        focusedTextColor = MaterialTheme.colorScheme.primary,
                        unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        focusedLabelColor = MaterialTheme.colorScheme.tertiary,
                        unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        unfocusedContainerColor = MaterialTheme.colorScheme.background
                    ),
                    trailingIcon = {
                        IconButton(onClick = {
                            TimePickerDialog(
                                context,
                                { _, hour, minute ->
                                    endTime = TextFieldValue("%02d:%02d".format(hour, minute))
                                },
                                8,
                                30,
                                true
                            ).show()
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_time),
                                tint = MaterialTheme.colorScheme.tertiary,
                                modifier = Modifier.size(20.dp),
                                contentDescription = "Time Picker Activator"
                            )
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Кабинет (необязательно)",
            fontFamily = Roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = cabinetNum,
            singleLine = true,
            onValueChange = { cabinetNum = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(12.dp),
            placeholder = { Text("Номер кабинета") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface,
                focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                focusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.tertiary,
                unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.surface
            ),
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Сохранить урок",
                fontFamily = Roboto,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.tertiary
            ),
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Отмена",
                fontFamily = Roboto,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.navigationBarsPadding().height(20.dp))
    }
}


