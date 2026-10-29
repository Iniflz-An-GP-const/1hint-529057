package aurapixelfy.app1hint.digital.data.repository

import aurapixelfy.app1hint.digital.data.model.Difficulty
import aurapixelfy.app1hint.digital.data.model.ExerciseModel
import aurapixelfy.app1hint.digital.data.model.Intensity
import aurapixelfy.app1hint.digital.data.model.WorkoutModel
import aurapixelfy.app1hint.digital.data.model.WorkoutType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class WorkoutRepository {
    private val workouts = listOf<WorkoutModel>(
        WorkoutModel(
            id = 1,
            texts = mapOf("en" to "Calisthenics Basics", "de" to "Calisthenics-Grundlagen", "es" to "Calistenia básica", "fr" to "Bases de la callisthénie", "hi" to "कैलिस्थेनिक्स की बुनियाद", "id" to "Dasar Kalistenik", "pt" to "Calistenia básica", "ar" to "أساسيات تمارين وزن الجسم"),
            exercises = listOf(
                ExerciseModel(mapOf("en" to "Bodyweight Squat", "de" to "Kniebeuge", "es" to "Sentadilla", "fr" to "Squat au poids du corps", "hi" to "बॉडीवेट स्क्वाट", "id" to "Squat tanpa beban", "pt" to "Agachamento livre", "ar" to "قرفصاء بوزن الجسم"), null, 3, 12, 45_000L, Intensity.LOW),
                ExerciseModel(mapOf("en" to "Knee Push-Up", "de" to "Liegestütz auf Knien", "es" to "Flexión con rodillas", "fr" to "Pompe sur les genoux", "hi" to "घुटनों के बल पुश-अप", "id" to "Push-up lutut", "pt" to "Flexão com joelhos", "ar" to "تمرين ضغط على الركبتين"), null, 3, 10, 45_000L, Intensity.MODERATE),
                ExerciseModel(mapOf("en" to "Dead Bug", "de" to "Käfer", "es" to "Bicho muerto", "fr" to "Dead bug", "hi" to "डेड बग", "id" to "Dead bug", "pt" to "Inseto morto", "ar" to "تمرين الحشرة الميتة"), null, 3, 10, 30_000L, Intensity.LOW),
                ExerciseModel(mapOf("en" to "Forearm Plank", "de" to "Unterarmstütz", "es" to "Plancha de antebrazos", "fr" to "Planche sur les avant-bras", "hi" to "फोरआर्म प्लैंक", "id" to "Plank lengan bawah", "pt" to "Prancha de antebraço", "ar" to "بلانك على الساعدين"), 30_000L, null, null, 30_000L, Intensity.MODERATE),
            ),
            difficulty = Difficulty.BEGINNER,
            type = WorkoutType.STRENGTH,
            imageUrl = "https://images.pexels.com/photos/8401817/pexels-photo-8401817.jpeg",
        ),
        WorkoutModel(
            id = 2,
            texts = mapOf("en" to "Core Control", "de" to "Rumpfkontrolle", "es" to "Control del core", "fr" to "Contrôle du centre", "hi" to "कोर नियंत्रण", "id" to "Kontrol Inti", "pt" to "Controle do core", "ar" to "التحكم بالجذع"),
            exercises = listOf(
                ExerciseModel(mapOf("en" to "Hollow Body Hold", "de" to "Hollow Hold", "es" to "Posición hueca", "fr" to "Maintien hollow", "hi" to "हॉलो बॉडी होल्ड", "id" to "Hollow body hold", "pt" to "Hollow hold", "ar" to "ثبات الجسم المقعر"), 30_000L, null, null, 30_000L, Intensity.MODERATE),
                ExerciseModel(mapOf("en" to "Reverse Crunch", "de" to "Umgekehrter Crunch", "es" to "Abdominal inverso", "fr" to "Crunch inversé", "hi" to "रिवर्स क्रंच", "id" to "Reverse crunch", "pt" to "Abdominal reverso", "ar" to "تمرين بطن عكسي"), null, 3, 12, 30_000L, Intensity.MODERATE),
                ExerciseModel(mapOf("en" to "Side Plank", "de" to "Seitstütz", "es" to "Plancha lateral", "fr" to "Planche latérale", "hi" to "साइड प्लैंक", "id" to "Plank samping", "pt" to "Prancha lateral", "ar" to "بلانك جانبي"), 30_000L, null, null, 30_000L, Intensity.MODERATE),
                ExerciseModel(mapOf("en" to "Bird Dog", "de" to "Diagonalstrecken", "es" to "Perro de caza", "fr" to "Bird dog", "hi" to "बर्ड डॉग", "id" to "Bird dog", "pt" to "Perdigueiro", "ar" to "تمرين الطائر والكلب"), null, 3, 10, 30_000L, Intensity.LOW),
            ), difficulty = Difficulty.BEGINNER, type = WorkoutType.CORE,
            imageUrl = "https://images.pexels.com/photos/4720311/pexels-photo-4720311.jpeg",
        ),
        WorkoutModel(
            id = 3,
            texts = mapOf("en" to "Bar and Bars", "de" to "Stange und Barren", "es" to "Barra y paralelas", "fr" to "Barre et parallèles", "hi" to "बार और पैरेलल बार", "id" to "Palang dan Paralel", "pt" to "Barra e paralelas", "ar" to "العقلة والمتوازي"),
            exercises = listOf(
                ExerciseModel(mapOf("en" to "Pull-Up", "de" to "Klimmzug", "es" to "Dominada", "fr" to "Traction", "hi" to "पुल-अप", "id" to "Pull-up", "pt" to "Barra fixa", "ar" to "عقلة"), null, 4, 8, 75_000L, Intensity.HIGH),
                ExerciseModel(mapOf("en" to "Parallel Bar Dip", "de" to "Barrenstütz", "es" to "Fondo en paralelas", "fr" to "Dips aux parallèles", "hi" to "पैरेलल बार डिप", "id" to "Dip palang sejajar", "pt" to "Mergulho nas paralelas", "ar" to "غطس على المتوازي"), null, 4, 10, 75_000L, Intensity.HIGH),
                ExerciseModel(mapOf("en" to "Inverted Row", "de" to "Umgekehrtes Rudern", "es" to "Remo invertido", "fr" to "Rowing inversé", "hi" to "इनवर्टेड रो", "id" to "Row terbalik", "pt" to "Remada invertida", "ar" to "سحب أفقي مقلوب"), null, 3, 12, 60_000L, Intensity.MODERATE),
                ExerciseModel(mapOf("en" to "Hanging Knee Raise", "de" to "Hängendes Knieheben", "es" to "Elevación de rodillas colgado", "fr" to "Relevé de genoux suspendu", "hi" to "हैंगिंग नी रेज", "id" to "Angkat lutut menggantung", "pt" to "Elevação de joelhos suspenso", "ar" to "رفع الركبتين مع التعلق"), null, 3, 12, 60_000L, Intensity.HIGH),
            ), difficulty = Difficulty.INTERMEDIATE, type = WorkoutType.UPPER_BODY,
            imageUrl = "https://images.pexels.com/photos/7187955/pexels-photo-7187955.jpeg",
        ),
        WorkoutModel(
            id = 4,
            texts = mapOf("en" to "Floor Sprint", "de" to "Boden-Sprint", "es" to "Sprint de suelo", "fr" to "Sprint au sol", "hi" to "फ्लोर स्प्रिंट", "id" to "Sprint Lantai", "pt" to "Sprint no chão", "ar" to "سباق أرضي"),
            exercises = listOf(
                ExerciseModel(mapOf("en" to "Burpee", "de" to "Burpee", "es" to "Burpee", "fr" to "Burpee", "hi" to "बर्पी", "id" to "Burpee", "pt" to "Burpee", "ar" to "بيربي"), 30_000L, null, null, 30_000L, Intensity.HIGH),
                ExerciseModel(mapOf("en" to "Mountain Climber", "de" to "Bergsteiger", "es" to "Escalador", "fr" to "Mountain climber", "hi" to "माउंटेन क्लाइंबर", "id" to "Mountain climber", "pt" to "Escalador", "ar" to "متسلق الجبل"), 40_000L, null, null, 20_000L, Intensity.HIGH),
                ExerciseModel(mapOf("en" to "Jump Squat", "de" to "Sprungkniebeuge", "es" to "Sentadilla con salto", "fr" to "Squat sauté", "hi" to "जंप स्क्वाट", "id" to "Squat lompat", "pt" to "Agachamento com salto", "ar" to "قرفصاء مع قفز"), 30_000L, null, null, 30_000L, Intensity.HIGH),
                ExerciseModel(mapOf("en" to "Push-Up", "de" to "Liegestütz", "es" to "Flexión", "fr" to "Pompe", "hi" to "पुश-अप", "id" to "Push-up", "pt" to "Flexão", "ar" to "تمرين ضغط"), 30_000L, null, null, 30_000L, Intensity.MODERATE),
            ), difficulty = Difficulty.INTERMEDIATE, type = WorkoutType.HIIT,
            imageUrl = "https://images.pexels.com/photos/8401817/pexels-photo-8401817.jpeg",
        ),
        WorkoutModel(
            id = 5,
            texts = mapOf("en" to "Push Power", "de" to "Drückkraft", "es" to "Potencia de empuje", "fr" to "Puissance de poussée", "hi" to "पुश पावर", "id" to "Kekuatan Dorong", "pt" to "Força de empurrar", "ar" to "قوة الدفع"),
            exercises = listOf(
                ExerciseModel(mapOf("en" to "Diamond Push-Up", "de" to "Diamant-Liegestütz", "es" to "Flexión diamante", "fr" to "Pompe diamant", "hi" to "डायमंड पुश-अप", "id" to "Push-up berlian", "pt" to "Flexão diamante", "ar" to "ضغط ماسي"), null, 4, 12, 60_000L, Intensity.HIGH),
                ExerciseModel(mapOf("en" to "Pike Push-Up", "de" to "Pike-Liegestütz", "es" to "Flexión pica", "fr" to "Pompe en V", "hi" to "पाइक पुश-अप", "id" to "Pike push-up", "pt" to "Flexão pike", "ar" to "ضغط الرمح"), null, 4, 10, 60_000L, Intensity.HIGH),
                ExerciseModel(mapOf("en" to "Wide Push-Up", "de" to "Breiter Liegestütz", "es" to "Flexión abierta", "fr" to "Pompe large", "hi" to "वाइड पुश-अप", "id" to "Push-up lebar", "pt" to "Flexão aberta", "ar" to "ضغط واسع"), null, 3, 15, 45_000L, Intensity.MODERATE),
                ExerciseModel(mapOf("en" to "Pseudo Planche Push-Up", "de" to "Pseudo-Planche-Liegestütz", "es" to "Flexión pseudo plancha", "fr" to "Pompe pseudo-planche", "hi" to "स्यूडो प्लांच पुश-अप", "id" to "Pseudo planche push-up", "pt" to "Flexão pseudo planche", "ar" to "ضغط البلانش المساعد"), null, 3, 8, 75_000L, Intensity.MAXIMUM),
            ), difficulty = Difficulty.ADVANCED, type = WorkoutType.UPPER_BODY,
            imageUrl = "https://images.pexels.com/photos/7187955/pexels-photo-7187955.jpeg",
        ),
        WorkoutModel(
            id = 6,
            texts = mapOf("en" to "Hanging Core", "de" to "Rumpf am Reck", "es" to "Core colgado", "fr" to "Centre suspendu", "hi" to "हैंगिंग कोर", "id" to "Inti Menggantung", "pt" to "Core suspenso", "ar" to "جذع معلق"),
            exercises = listOf(
                ExerciseModel(mapOf("en" to "Hanging Leg Raise", "de" to "Hängendes Beinheben", "es" to "Elevación de piernas colgado", "fr" to "Relevé de jambes suspendu", "hi" to "हैंगिंग लेग रेज", "id" to "Angkat kaki menggantung", "pt" to "Elevação de pernas suspenso", "ar" to "رفع الساقين مع التعلق"), null, 4, 10, 75_000L, Intensity.HIGH),
                ExerciseModel(mapOf("en" to "Toes to Bar", "de" to "Zehen zur Stange", "es" to "Pies a la barra", "fr" to "Pieds à la barre", "hi" to "टोज़ टू बार", "id" to "Ujung kaki ke palang", "pt" to "Pés na barra", "ar" to "أصابع القدم إلى العقلة"), null, 4, 8, 90_000L, Intensity.MAXIMUM),
                ExerciseModel(mapOf("en" to "L-Sit", "de" to "L-Sitz", "es" to "L-sit", "fr" to "Équerre", "hi" to "एल-सिट", "id" to "L-sit", "pt" to "L-sit", "ar" to "ثبات حرف إل"), 25_000L, null, null, 60_000L, Intensity.MAXIMUM),
                ExerciseModel(mapOf("en" to "Dragon Flag", "de" to "Dragon Flag", "es" to "Bandera de dragón", "fr" to "Dragon flag", "hi" to "ड्रैगन फ्लैग", "id" to "Dragon flag", "pt" to "Bandeira do dragão", "ar" to "علم التنين"), null, 3, 8, 90_000L, Intensity.MAXIMUM),
            ), difficulty = Difficulty.ADVANCED, type = WorkoutType.CORE,
            imageUrl = "https://images.pexels.com/photos/8401817/pexels-photo-8401817.jpeg",
        ),
        WorkoutModel(
            id = 7,
            texts = mapOf("en" to "Bar Endurance", "de" to "Ausdauer am Reck", "es" to "Resistencia en barra", "fr" to "Endurance à la barre", "hi" to "बार एंड्योरेंस", "id" to "Daya Tahan Palang", "pt" to "Resistência na barra", "ar" to "تحمل العقلة"),
            exercises = listOf(
                ExerciseModel(mapOf("en" to "Chest-to-Bar Pull-Up", "de" to "Brust-zur-Stange-Klimmzug", "es" to "Dominada al pecho", "fr" to "Traction poitrine-barre", "hi" to "चेस्ट-टू-बार पुल-अप", "id" to "Pull-up dada ke palang", "pt" to "Barra ao peito", "ar" to "عقلة حتى الصدر"), null, 5, 6, 90_000L, Intensity.MAXIMUM),
                ExerciseModel(mapOf("en" to "Straight Bar Dip", "de" to "Stangendip", "es" to "Fondo en barra", "fr" to "Dips à la barre", "hi" to "स्ट्रेट बार डिप", "id" to "Dip palang lurus", "pt" to "Mergulho na barra", "ar" to "غطس على العقلة"), null, 4, 8, 75_000L, Intensity.HIGH),
                ExerciseModel(mapOf("en" to "Chin-Up", "de" to "Klimmzug im Untergriff", "es" to "Dominada supina", "fr" to "Traction en supination", "hi" to "चिन-अप", "id" to "Chin-up", "pt" to "Barra supinada", "ar" to "عقلة بقبضة معكوسة"), null, 4, 10, 75_000L, Intensity.HIGH),
                ExerciseModel(mapOf("en" to "Dead Hang", "de" to "Passiver Hang", "es" to "Suspensión pasiva", "fr" to "Suspension passive", "hi" to "डेड हैंग", "id" to "Gantung pasif", "pt" to "Suspensão passiva", "ar" to "تعلق ثابت"), 45_000L, null, null, 60_000L, Intensity.HIGH),
            ), difficulty = Difficulty.ADVANCED, type = WorkoutType.STRENGTH,
            imageUrl = "https://images.pexels.com/photos/8401817/pexels-photo-8401817.jpeg",
        ),
    )

    fun observeAll(): Flow<List<WorkoutModel>> {
        return flowOf(workouts)
    }

    fun observeById(id: Int): Flow<WorkoutModel?> {
        val service = workouts.firstOrNull { service -> service.id == id }
        return flowOf(service)
    }

    fun getById(id: Int): WorkoutModel? {
        return workouts.firstOrNull { service -> service.id == id }
    }
}
