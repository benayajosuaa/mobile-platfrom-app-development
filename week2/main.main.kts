#!/usr/bin/env kotlin

/*
============================================================
Ch02 — Lembar Kode Latihan (Kotlin Essentials)
============================================================

BAGIAN A — VARIABEL, TIPE DATA & NULL SAFETY
*/


// ============================================================
// _01.kt — Deklarasi Variabel (val/var)
// ============================================================

val name: String = "Budi"      // immutable (tidak bisa diubah)
var age: Int = 21              // mutable (bisa diubah)
val pi = 3.14                  // tipe inferensi: Double



// ============================================================
// _02.kt — Tipe Data Dasar
// ============================================================

val isActive: Boolean = true
val score: Int = 100
val price: Double = 49.99f
val initial: Char = 'A'
val message: String = "Hello"



// ============================================================
// _03.kt — String Template
// ============================================================

val name = "Siti"
val age = 22

val greeting = "Nama: $name, Umur: $age tahun"
val info = "Nama terdiri dari ${name.length} karakter"



// ============================================================
// _04.kt — Null Safety: Nullable vs Non-nullable
// ============================================================

var name: String = "Budi"
name = null      // ERROR: compile time

var name: String? = "Budi"     // nullable dengan tanda tanya
name = null                     // OK



// ============================================================
// _05.kt — Empat Cara Mengakses Nullable
// ============================================================

val name: String? = getName()

// 1. Safe call — tidak crash, hasilnya null kalau name null
val length = name?.length

// 2. Elvis operator — fallback kalau null
val length = name?.length ?: 0

// 3. Non-null assertion — crash kalau null (hindari kalau bisa)
val length = name!!.length

// 4. Smart cast setelah null check
if (name != null) {
    println(name.length)        // di sini name sudah otomatis String, bukan String?
}



/*
============================================================
BAGIAN B — FUNGSI & CONTROL FLOW
============================================================
*/


// ============================================================
// _06.kt — Deklarasi Fungsi
// ============================================================


// Function biasa
fun greet(name: String): String {
    return "Hello, $name!"
}


// Single-expression function
fun greet(name: String): String = "Hello, $name!"


// Default parameter
fun greet(name: String = "Dunia"): String = "Hello, $name!"


// Named argument
greet(name = "Budi")


// Unit = void dalam Kotlin
fun logMessage(message: String): Unit {
    println(message)
}


// Unit bisa dihilangkan
fun logMessage(message: String) {
    println(message)
}



// ============================================================
// _07.kt — if sebagai Expression
// ============================================================

val max = if (a > b) a else b

val grade = if (score >= 90) "A"
else if (score >= 80) "B"
else if (score >= 70) "C"
else "D"



// ============================================================
// _08.kt — when Expression
// ============================================================

val day = 3

val dayName = when (day) {
    1 -> "Senin"
    2 -> "Selasa"
    3 -> "Rabu"
    4, 5 -> "Kamis atau Jumat"
    in 6..7 -> "Weekend"
    else -> "Tidak valid"
}


// when tanpa argumen (menggantikan if-else chain)

when {
    score >= 90 -> println("Lulus dengan pujian")
    score >= 70 -> println("Lulus")
    else -> println("Tidak lulus")
}



// ============================================================
// _09.kt — Loop (for, while, do-while)
// ============================================================

for (i in 1..5) println(i)             // 1, 2, 3, 4, 5

for (i in 1 until 5) println(i)        // 1, 2, 3, 4

for (i in 5 downTo 1) println(i)       // 5, 4, 3, 2, 1

for (i in 0..10 step 2) println(i)     // 0, 2, 4, 6, 8, 10


val names = listOf("Ali", "Budi", "Cici")

for (name in names) println(name)

names.forEachIndexed { index, name ->
    println("$index: $name")
}


while (condition) {
    // ...
}


do {
    // ...
} while (condition)



/*
============================================================
BAGIAN C — COLLECTIONS & OPERASI FUNGSIONAL
============================================================
*/


// ============================================================
// _10.kt — Collections: List, Set, Map
// ============================================================


// Immutable (read-only)

val fruits = listOf("Apple", "Banana", "Cherry")

val uniqueIds = setOf(1, 2, 3, 2)      // {1, 2, 3}

val scores = mapOf(
    "Ali" to 90,
    "Budi" to 85
)


// Mutable

val mutableFruits = mutableListOf(
    "Apple",
    "Banana"
)

mutableFruits.add("Cherry")

mutableFruits.removeAt(0)


val mutableScores = mutableMapOf(
    "Ali" to 90
)

mutableScores["Budi"] = 85



// ============================================================
// _11.kt — Operasi Fungsional pada Collection
// ============================================================

val numbers = listOf(
    1, 2, 3, 4, 5,
    6, 7, 8, 9, 10
)


// filter — ambil yang memenuhi kondisi

val evens = numbers.filter { it % 2 == 0 }

// [2, 4, 6, 8, 10]


// map — transformasi setiap elemen

val doubled = numbers.map { it * 2 }

// [2, 4, 6, 8, 10, ...]


// find — elemen pertama yang cocok

val firstEven = numbers.find { it % 2 == 0 }

// 2


// any / all / none

val hasNegative = numbers.any { it < 0 }

// false

val allPositive = numbers.all { it > 0 }

// true


// groupBy

val students = listOf(
    Student("Ali", "A"),
    Student("Budi", "B"),
    Student("Cici", "A")
)

val byGrade = students.groupBy { it.grade }

// {"A": [Ali, Cici], "B": [Budi]}


// sortedBy

val sorted = students.sortedBy { it.name }


// Chaining

val result = numbers
    .filter { it % 2 == 0 }
    .map { it * it }
    .sortedDescending()
    .take(3)



/*
============================================================
BAGIAN D — CLASS, DATA CLASS, SEALED CLASS & OBJECT
============================================================
*/


// ============================================================
// _12.kt — Class Dasar
// ============================================================

class Car(
    val brand: String,
    var speed: Int = 0
) {

    fun accelerate(amount: Int) {
        speed += amount
    }

    fun brake() {
        speed = 0
    }

    override fun toString(): String =
        "$brand at $speed km/h"
}


val car = Car("Toyota")

car.accelerate(60)

println(car)

// Toyota at 60 km/h



// ============================================================
// _13.kt — Data Class
// ============================================================

data class User(
    val id: Int,
    val name: String,
    val email: String
)


val user1 = User(
    1,
    "Budi",
    "budi@email.com"
)


val user2 = user1.copy(
    name = "Ali"
)

// copy dengan perubahan


println(user1 == user2)

// false — perbandingan berdasarkan isi


println(user1)

// User(id=1, name=Budi, email=budi@email.com)


// Destructuring

val (id, name, email) = user1



// ============================================================
// _14.kt — Sealed Class untuk UI State
// ============================================================

sealed class UiState<out T> {

    data object Loading :
        UiState<Nothing>()

    data class Success<T>(
        val data: T
    ) : UiState<T>()

    data class Error(
        val message: String
    ) : UiState<Nothing>()
}


// Penggunaan — compiler memaksa kita handle semua kasus

fun handleState(state: UiState<User>) {

    when (state) {

        is UiState.Loading ->
            showLoadingSpinner()

        is UiState.Success ->
            showUser(state.data)

        is UiState.Error ->
            showError(state.message)
    }
}



// ============================================================
// _15.kt — Singleton & Companion Object
// ============================================================


// Singleton — hanya ada satu instance

object AppConfig {

    const val BASE_URL =
        "https://api.example.com"

    const val TIMEOUT = 30L
}


// Companion object — seperti static method di Java

class UserRepository {

    companion object {

        fun create(): UserRepository =
            UserRepository()
    }
}


val repo = UserRepository.create()



/*
============================================================
BAGIAN E — EXTENSION FUNCTION, LAMBDA & SCOPE FUNCTION
============================================================
*/


// ============================================================
// _16.kt — Extension Function
// ============================================================


// Menambahkan fungsi ke String

fun String.titleCase(): String =

    split(" ")
        .joinToString(" ") { word ->

            word.replaceFirstChar {
                it.uppercase()
            }
        }


"hello world".titleCase()

// "Hello World"


// Menambahkan fungsi ke Int

fun Int.toRupiah(): String =

    "Rp ${"%,d".format(this)}"


50000.toRupiah()

// "Rp 50,000"


// Extension di Android sangat umum

fun View.show() {

    visibility = View.VISIBLE
}


fun View.hide() {

    visibility = View.GONE
}


fun Context.toast(message: String) {

    Toast
        .makeText(
            this,
            message,
            Toast.LENGTH_SHORT
        )
        .show()
}



// ============================================================
// _17.kt — Higher-Order Function & Lambda
// ============================================================


// Fungsi yang menerima lambda

fun doOperation(
    a: Int,
    b: Int,
    operation: (Int, Int) -> Int
): Int {

    return operation(a, b)
}


val sum = doOperation(5, 3) { x, y ->
    x + y
}

// 8


val product = doOperation(5, 3) { x, y ->
    x * y
}

// 15


// Lambda tersimpan di variabel

val multiply: (Int, Int) -> Int =
    { x, y ->
        x * y
    }


val greet: (String) -> String =
    { name ->
        "Hello, $name!"
    }


val double: (Int) -> Int =
    {
        it * 2
    }

// 'it' untuk parameter tunggal



// ============================================================
// _18.kt — Lima Scope Function:
// let, apply, run, also, with
// ============================================================


// let — transformasi atau null check

val result = user?.let {

    "${it.name} (${it.age} tahun)"
}


// apply — konfigurasi object,
// mengembalikan object itu sendiri

val textView = TextView(context).apply {

    text = "Hello"

    textSize = 16f

    setTextColor(Color.BLACK)
}


// run — seperti apply tapi mengembalikan nilai terakhir

val greeting = user.run {

    "Selamat datang, $name!"
}


// also — seperti let tapi mengembalikan object asli
// untuk side effect

val user = createUser().also {

    Log.d(
        "TAG",
        "User created: ${it.name}"
    )
}


// with — mirip run,
// tapi receiver di-pass sebagai argumen

val message = with(user) {

    "Nama: $name, Email: $email"
}



/*
============================================================
BAGIAN F — COROUTINES & FLOW
============================================================
*/


// ============================================================
// _19.kt — Evolusi Async: Callback vs Coroutine
// ============================================================


// SALAH:
// ini memblokir UI thread,
// aplikasi jadi freeze

fun loadData() {

    val data = networkCall()

    // ini bisa butuh waktu 3 detik

    updateUI(data)

    // selama 3 detik, UI tidak responsif
}



// SALAH (cara lama):
// callback hell

fun loadData() {

    networkCall(

        onSuccess = { data ->

            parseData(

                data,

                onSuccess = { parsed ->

                    saveToDb(

                        parsed,

                        onSuccess = {

                            updateUI(it)
                        },

                        onError = {

                            handleError(it)
                        }
                    )
                }
            )
        },

        onError = {

            handleError(it)
        }
    )
}



// BENAR dengan Coroutines:
// sequential, mudah dibaca

suspend fun loadData() {

    val data = networkCall()

    // suspend, tidak memblokir


    val parsed = parseData(data)


    saveToDb(parsed)


    updateUI(parsed)
}



// ============================================================
// _20.kt — launch, async/await, withContext
// ============================================================


// launch — fire & forget,
// tidak mengembalikan nilai

viewModelScope.launch {

    val users =
        repository.getUsers()

    // suspend


    _uiState.value =
        users
}



// async/await — mengembalikan nilai,
// bisa dijalankan paralel

viewModelScope.launch {

    val usersDeferred =
        async {

            repository.getUsers()
        }


    val postsDeferred =
        async {

            repository.getPosts()
        }


    val users =
        usersDeferred.await()


    val posts =
        postsDeferred.await()

    // tunggu keduanya


    updateUI(
        users,
        posts
    )
}



// withContext — switch dispatcher
// di tengah coroutine

suspend fun fetchAndSave() {

    val data =
        withContext(
            Dispatchers.IO
        ) {

            api.fetchData()

            // jalankan di IO thread
        }


    // kembali ke dispatcher asal

    updateUI(data)

    // di Main thread
    // jika dipanggil dari viewModelScope
}



// ============================================================
// _21.kt — Flow
// ============================================================


// Membuat Flow

fun getUserUpdates():
        Flow<User> = flow {


    while (true) {


        val user =
            api.getLatestUser()


        emit(user)

        // kirim nilai ke collector


        delay(5000)

        // tunggu 5 detik
    }
}



// Mengkonsumsi Flow

viewModelScope.launch {


    getUserUpdates()

        .filter {

            it.isActive
        }

        .map {

            it.toDisplayModel()
        }

        .collect { user ->

            _uiState.value =
                user
        }
}



/*
============================================================
BAGIAN G — MINI-APP COROUTINES DI COMPOSE
(OPSIONAL)
============================================================
*/


// ============================================================
// _22.kt — StateFlow di ViewModel
// ============================================================

class UserViewModel :
    ViewModel() {


    private val _uiState =
        MutableStateFlow<UiState<User>>(
            UiState.Loading
        )


    val uiState:
            StateFlow<UiState<User>> =

        _uiState.asStateFlow()


    fun loadUser(
        id: Int
    ) {


        viewModelScope.launch {


            _uiState.value =
                UiState.Loading


            try {


                val user =
                    repository.getUser(id)


                _uiState.value =
                    UiState.Success(user)


            } catch (
                e: Exception
            ) {


                _uiState.value =
                    UiState.Error(
                        e.message
                            ?: "Unknown error"
                    )
            }
        }
    }
}



// ============================================================
// _23.kt — Mengkonsumsi StateFlow di Composable
// ============================================================

val uiState by

viewModel
    .uiState
    .collectAsStateWithLifecycle()


when (uiState) {


    is UiState.Loading ->

        CircularProgressIndicator()


    is UiState.Success ->

        UserCard(
            (uiState as UiState.Success).data
        )


    is UiState.Error ->

        ErrorMessage(
            (uiState as UiState.Error).message
        )
}



// ============================================================
// _24.kt — DownloaderScreen: Coroutine + Cancel
// ============================================================

class MainActivity :
    ComponentActivity() {


    override fun onCreate(
        savedInstanceState: Bundle?
    ) {


        super.onCreate(
            savedInstanceState
        )


        setContent {

            DownloaderScreen()
        }
    }
}



@Composable
fun DownloaderScreen() {


    var progress by remember {

        mutableFloatStateOf(0f)
    }


    var isDownloading by remember {

        mutableStateOf(false)
    }


    val scope =
        rememberCoroutineScope()


    Column(

        modifier =
            Modifier

                .fillMaxSize()

                .padding(32.dp),

        verticalArrangement =
            Arrangement.Center,

        horizontalAlignment =
            Alignment.CenterHorizontally

    ) {


        Text(

            text =

                if (isDownloading)

                    "Mengunduh..."

                else

                    "Siap",

            style =
                MaterialTheme
                    .typography
                    .headlineSmall
        )


        Spacer(

            modifier =
                Modifier.height(24.dp)
        )


        LinearProgressIndicator(

            progress = {
                progress
            },

            modifier =
                Modifier.fillMaxWidth()
        )


        Spacer(

            modifier =
                Modifier.height(8.dp)
        )


        Text(
            "${(progress * 100).toInt()}%"
        )


        Spacer(

            modifier =
                Modifier.height(32.dp)
        )


        Button(

            onClick = {


                scope.launch {


                    isDownloading =
                        true


                    progress =
                        0f


                    repeat(100) {


                        delay(50)


                        progress +=
                            0.01f
                    }


                    isDownloading =
                        false
                }
            },


            enabled =
                !isDownloading

        ) {


            Text(
                "Mulai Download"
            )
        }
    }
}