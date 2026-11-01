package aurapixelfy.app1hint.digital.ui.composable.screen.profile.fields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import aurapixelfy.app1hint.digital.R
import aurapixelfy.app1hint.digital.data.model.Difficulty
import aurapixelfy.app1hint.digital.ui.composable.screen.profile.field.ProfileTextField
import aurapixelfy.app1hint.digital.ui.theme.LocalDimens
import aurapixelfy.app1hint.digital.ui.viewmodel.ProfileForm

@Composable
fun ProfileFields(
    form: ProfileForm,
    onNameChanged: (String) -> Unit,
    onAgeChanged: (String) -> Unit,
    onFitnessLevelChanged: (Difficulty) -> Unit,
    onWeeklyGoalChanged: (String) -> Unit,
    onHeightChanged: (String) -> Unit,
    onWeightChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val nextAction = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Next) })

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
    ) {
        ProfileTextField(
            value = form.name,
            onValueChange = onNameChanged,
            label = stringResource(R.string.profile_name_label),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            keyboardActions = nextAction,
        )

        ProfileTextField(
            value = form.ageText,
            onValueChange = onAgeChanged,
            label = stringResource(R.string.profile_age_label),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = nextAction,
        )

        FitnessLevelDropdown(
            selected = form.fitnessLevel,
            onSelected = onFitnessLevelChanged,
            modifier = Modifier.fillMaxWidth(),
        )

        ProfileTextField(
            value = form.weeklyGoalText,
            onValueChange = onWeeklyGoalChanged,
            label = stringResource(R.string.profile_screen_weekly_goal_label),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = nextAction,
        )

        ProfileTextField(
            value = form.heightText,
            onValueChange = onHeightChanged,
            label = stringResource(R.string.profile_screen_height_label),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = nextAction,
        )

        ProfileTextField(
            value = form.weightText,
            onValueChange = onWeightChanged,
            label = stringResource(R.string.profile_screen_weight_label),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                },
            ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FitnessLevelDropdown(
    selected: Difficulty,
    onSelected: (Difficulty) -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier,
    ) {
        OutlinedTextField(
            value = stringResource(selected.titleRes),
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            label = { Text(stringResource(R.string.profile_fitness_level_label)) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            shape = RoundedCornerShape(dimens.controlCornerRadius),
            modifier = Modifier
                .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                .fillMaxWidth(),
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            Difficulty.entries.forEach { level ->
                DropdownMenuItem(
                    text = { Text(stringResource(level.titleRes)) },
                    onClick = {
                        onSelected(level)
                        expanded = false
                    },
                )
            }
        }
    }
}
