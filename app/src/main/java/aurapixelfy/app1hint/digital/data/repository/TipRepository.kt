package aurapixelfy.app1hint.digital.data.repository

import aurapixelfy.app1hint.digital.data.model.TipModel
import aurapixelfy.app1hint.digital.data.model.TipText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class TipRepository {
    private val tips = listOf(
        TipModel(
            id = 1,
            texts = mapOf(
                "en" to TipText(
                    title = "Stay Hydrated",
                    message = "Drink water before, during, and after training to keep your muscles performing.",
                ),
                "de" to TipText("Ausreichend trinken", "Trinke vor, während und nach dem Training Wasser, damit deine Muskeln leistungsfähig bleiben."),
                "es" to TipText("Mantente hidratado", "Bebe agua antes, durante y después de entrenar para que tus músculos rindan bien."),
                "fr" to TipText("Restez hydraté", "Buvez avant, pendant et après l’entraînement pour préserver les performances de vos muscles."),
                "hi" to TipText("पानी पीते रहें", "मांसपेशियों के अच्छे प्रदर्शन के लिए प्रशिक्षण से पहले, दौरान और बाद में पानी पिएँ।"),
                "id" to TipText("Tetap Terhidrasi", "Minumlah air sebelum, selama, dan setelah latihan agar otot tetap bekerja optimal."),
                "pt" to TipText("Mantenha-se hidratado", "Beba água antes, durante e depois do treino para manter o bom desempenho muscular."),
                "ar" to TipText("حافظ على الترطيب", "اشرب الماء قبل التدريب وأثناءه وبعده لتحافظ على أداء عضلاتك."),
            ),
        ),
        TipModel(
            id = 2,
            texts = mapOf(
                "en" to TipText(
                    title = "Warm Up First",
                    message = "Five minutes of light movement prepares your joints and lowers injury risk.",
                ),
                "de" to TipText("Erst aufwärmen", "Fünf Minuten leichte Bewegung bereiten deine Gelenke vor und senken das Verletzungsrisiko."),
                "es" to TipText("Calienta primero", "Cinco minutos de movimiento suave preparan tus articulaciones y reducen el riesgo de lesión."),
                "fr" to TipText("Échauffez-vous", "Cinq minutes de mouvements légers préparent vos articulations et réduisent le risque de blessure."),
                "hi" to TipText("पहले वार्म-अप", "पाँच मिनट की हल्की गतिविधि जोड़ों को तैयार करती है और चोट का जोखिम घटाती है।"),
                "id" to TipText("Pemanasan Dulu", "Lima menit gerakan ringan menyiapkan sendi dan mengurangi risiko cedera."),
                "pt" to TipText("Aqueça primeiro", "Cinco minutos de movimento leve preparam as articulações e reduzem o risco de lesão."),
                "ar" to TipText("ابدأ بالإحماء", "خمس دقائق من الحركة الخفيفة تهيئ مفاصلك وتقلل خطر الإصابة."),
            ),
        ),
        TipModel(
            id = 3,
            texts = mapOf(
                "en" to TipText(
                    title = "Sleep Well",
                    message = "Seven to nine hours of sleep is when your body actually builds strength.",
                ),
                "de" to TipText("Gut schlafen", "In sieben bis neun Stunden Schlaf baut dein Körper tatsächlich Kraft auf."),
                "es" to TipText("Duerme bien", "Tu cuerpo desarrolla fuerza durante las siete a nueve horas de sueño."),
                "fr" to TipText("Dormez bien", "C’est pendant sept à neuf heures de sommeil que votre corps développe sa force."),
                "hi" to TipText("अच्छी नींद लें", "सात से नौ घंटे की नींद में आपका शरीर वास्तव में ताकत बनाता है।"),
                "id" to TipText("Tidur Cukup", "Tubuh benar-benar membangun kekuatan selama tujuh hingga sembilan jam tidur."),
                "pt" to TipText("Durma bem", "É durante sete a nove horas de sono que seu corpo realmente ganha força."),
                "ar" to TipText("نم جيدًا", "يبني جسمك قوته فعليًا خلال سبع إلى تسع ساعات من النوم."),
            ),
        ),
        TipModel(
            id = 4,
            texts = mapOf(
                "en" to TipText(
                    title = "Progress Gradually",
                    message = "Add a little weight or time each week instead of jumping ahead too fast.",
                ),
                "de" to TipText("Langsam steigern", "Erhöhe Gewicht oder Zeit jede Woche ein wenig, statt zu schnell voranzugehen."),
                "es" to TipText("Progresa poco a poco", "Añade un poco de peso o tiempo cada semana en vez de avanzar demasiado rápido."),
                "fr" to TipText("Progressez doucement", "Ajoutez un peu de poids ou de temps chaque semaine sans aller trop vite."),
                "hi" to TipText("धीरे-धीरे बढ़ें", "बहुत तेज़ आगे बढ़ने के बजाय हर सप्ताह थोड़ा भार या समय बढ़ाएँ।"),
                "id" to TipText("Tingkatkan Bertahap", "Tambahkan sedikit beban atau waktu tiap minggu, jangan melaju terlalu cepat."),
                "pt" to TipText("Progrida aos poucos", "Adicione um pouco de peso ou tempo por semana sem avançar rápido demais."),
                "ar" to TipText("تقدم تدريجيًا", "زد قليلًا من الوزن أو الوقت كل أسبوع بدلًا من التقدم بسرعة كبيرة."),
            ),
        ),
        TipModel(
            id = 5,
            texts = mapOf(
                "en" to TipText(
                    title = "Rest Days Matter",
                    message = "Recovery days let your muscles repair and make the next workout stronger.",
                ),
                "de" to TipText("Ruhetage zählen", "Erholungstage lassen deine Muskeln regenerieren und machen das nächste Training stärker."),
                "es" to TipText("Descansar importa", "Los días de recuperación reparan tus músculos y mejoran el siguiente entrenamiento."),
                "fr" to TipText("Le repos compte", "Les jours de récupération réparent vos muscles et renforcent la séance suivante."),
                "hi" to TipText("आराम भी ज़रूरी", "आराम के दिन मांसपेशियों को सुधारते हैं और अगला प्रशिक्षण बेहतर बनाते हैं।"),
                "id" to TipText("Hari Istirahat Penting", "Hari pemulihan memperbaiki otot dan membuat latihan berikutnya lebih kuat."),
                "pt" to TipText("Descanso importa", "Os dias de recuperação reparam os músculos e fortalecem o próximo treino."),
                "ar" to TipText("أيام الراحة مهمة", "تتيح أيام التعافي لعضلاتك أن تتعافى وتقوي تمرينك التالي."),
            ),
        ),
        TipModel(
            id = 6,
            texts = mapOf(
                "en" to TipText(
                    title = "Fuel Your Body",
                    message = "Eat protein and carbs within two hours after training to speed up recovery.",
                ),
                "de" to TipText("Körper versorgen", "Iss innerhalb von zwei Stunden nach dem Training Eiweiß und Kohlenhydrate für eine schnellere Erholung."),
                "es" to TipText("Alimenta tu cuerpo", "Come proteínas y carbohidratos en las dos horas posteriores al entrenamiento para recuperarte antes."),
                "fr" to TipText("Nourrissez votre corps", "Mangez protéines et glucides dans les deux heures suivant l’entraînement pour mieux récupérer."),
                "hi" to TipText("शरीर को पोषण दें", "तेज़ रिकवरी के लिए प्रशिक्षण के दो घंटे के भीतर प्रोटीन और कार्ब्स खाएँ।"),
                "id" to TipText("Beri Tubuh Energi", "Makan protein dan karbohidrat dalam dua jam setelah latihan agar pemulihan lebih cepat."),
                "pt" to TipText("Alimente seu corpo", "Consuma proteínas e carboidratos até duas horas após o treino para acelerar a recuperação."),
                "ar" to TipText("غذ جسمك", "تناول البروتين والكربوهيدرات خلال ساعتين بعد التدريب لتسرع التعافي."),
            ),
        ),
    )

    fun observeAll(): Flow<List<TipModel>> {
        return flowOf(tips)
    }
}
