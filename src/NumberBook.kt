
sealed interface Command {
    fun command()
}

fun readCommand(choice: String): Command {
    return when (choice.lowercase()) {
        "addperson" -> {
            println("Введите имя: ")
            val name = readlnOrNull()
            Persons(name)

        }

        "addemail" -> {
            println("Введите имел: ")
            val email = readlnOrNull()
            Email(email)

        }

        "addphone" -> {
            println("Введите номер телефона: ")
            val phone = readlnOrNull()?.toIntOrNull()
            Phone(phone)

        }

        "show" -> {
            Show()
        }

        else -> Help()
    }
}

open class Persons(val name: String?) : Command {

    override fun command() {
        println("${isValid()}")
        if (!isValid()) {
            println("Поле не может быть пустым")
            Help().command()
        } else {
            println("Имя - $name")
        }
        main()
    }

    private fun isValid(): Boolean {
        return name?.isNotBlank() ?: false
    }
}

open class Email( val email: String?) : Command {

    override fun command() {
        println("${isValid()}")
        if (!isValid()) {
            println("Поле не может быть пустым")
            Help().command()
        } else {
            println("Имел - $email")
        }
        main()

    }

    private fun isValid(): Boolean {
        return email?.isNotBlank() ?: false
    }
}


open class Phone(val phone: Int?) : Command {
    override fun command() {
        println("${isValid()}")
        if (!isValid()) {
            println("Неккоректный ввод")
            Help().command()
        } else {
            println("Телефон = '$phone'")
        }
        main()
    }

    private fun isValid(): Boolean {
        return phone != null
    }
}

open class Help() : Command {
    override fun command() {
        println(
            "Записная книжка.Для того чтобы добавить данные вводите следующие команды: " +
                    "<addperson>, <addemail>, <addphone>/ Работа с программой, выберите <help>/" +
                    " Чтобы отобразить добавленные данные выберите <show>/ " +
                    "Чтобы выйти из программы выберите <exit>"
        )
        main()
    }
}

open class Show() : Command {
//Не передаются данные из классов
    override fun command() {

        val name = Persons("")
        val phone = Phone(0)
        val email = Email("")

        if (name == null && phone == null && email == null) {
            println("Not Initialized")
            main()
        } else {
            val persons = Person(name = name.toString(), phone = phone.hashCode(), email = email.toString())
            println(persons)
            main()
        }
    }
}

open class Exit {
    fun exit() {
        println("Выход из программы")
        return
    }
}

data class Person(
    val name: String,
    val phone: Int,
    val email: String
)  {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person

        if (phone != other.phone) return false
        if (name != other.name) return false
        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        var result = phone
        result = 31 * result + name.hashCode()
        result = 31 * result + email.hashCode()
        return result
    }

    override fun toString(): String {
        return "Person(name='$name', phone=$phone, email='$email')"
    }
}


