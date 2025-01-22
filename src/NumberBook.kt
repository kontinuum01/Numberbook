
sealed interface Command {
    fun command()
}

val person = Person("", phone = 0,"")

fun readCommand(choice: String): Command {
    return when (choice.lowercase()) {
        "addperson" -> {
            println("Введите имя: ")
            val name = readln()
            person.name = name
            Persons(name)

        }

        "addemail" -> {
            println("Введите имейл: ")
            val email = readln()
            person.email = email
            Email(email)

        }

        "addphone" -> {
            println("Введите номер телефона: ")
            val phone = readlnOrNull()?.toIntOrNull()
            if (phone != null) {
                person.phone = phone
            }
            Phone(phone)

        }

        "show" -> {
            Show(person)
        }

        else -> Help()
    }
}

open class Persons(private val name: String?) : Command {

    override fun command() {
        println("${isValid()}")
        if (!isValid()) {
            println("Поле не может быть пустым")
            Help().command()
            return
        } else {
            println("Имя - $name")
        }
        main()
    }

    private fun isValid(): Boolean {
        return name?.isNotBlank() ?: false
    }
}

open class Email(private val email: String?) : Command {

    override fun command() {
        println("${isValid()}")
        if (!isValid()) {
            println("Поле не может быть пустым")
            Help().command()
            return
        } else {
            println("Имейл - $email")
        }
        main()

    }

    private fun isValid(): Boolean {
        return email?.isNotBlank() ?: false
    }
}


open class Phone(private val phone: Int?) : Command {
    override fun command() {
        println("${isValid()}")
        if (!isValid()) {
            println("Неккоректный ввод")
            Help().command()
            return
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

open class Show(person: Person) : Command {

    override fun command() {

        if (person.name == "" && person.phone == 0 && person.email == "") {
            println("Not Initialized")
            main()
        } else {
            println(person)
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
    var name: String,
    var phone: Int,
    var email: String
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


