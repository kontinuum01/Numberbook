fun numberBook() {
    do {
        println(
            "Выберите команду: " +
                    "addPerson/ " + "addEmail/ " + "addPhone/ " + "Help/ " + "Exit"
        )
        val scn = readlnOrNull()

        if (scn == "addPerson") {
            addPerson()
        }
        if (scn == "addEmail") {
            addEmail()
        }
        if (scn == "addPhone") {
            addPhone()
        }
        if (scn == "Help") {
            help()
        }
    } while (scn != "Exit")
}

fun addPerson() {
    println("Введите имя:")
    val person = readlnOrNull()
    if (person != null) {
        println("name-$person")
    } else {
        println("Поле не может быть пустым!")
        addPerson()
    }
}


fun addEmail() {
    println("Введите емеил:")
    val email = readlnOrNull()
    if (email != null) {
        println("email-$email")
    } else {
        println("Поле не может быть пустым!")
        addEmail()

    }
}

fun addPhone() {
    println("Введите номер:")
    val phone = readlnOrNull()?.toIntOrNull()
    if (phone != null) {
        println("phone-$phone")
    } else {
        println("Некорректный ввод!")
        addPhone()
    }
}

fun help() {
    println(
        "Записная книжка.Для того чтобы добавить данные вводите следующие команды: " +
                "addPerson/ addEmail/ addPhone/ Help/ Exit"
    )
}