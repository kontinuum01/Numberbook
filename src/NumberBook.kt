import java.lang.IndexOutOfBoundsException

sealed interface Command {
    fun command()
}

val personList = mutableListOf<Person>()

fun readCommand(choice: String): Command {
    return when (choice.lowercase()) {
        "addperson" -> {
            println("Введите имя: ")
            val name = readln()
            Persons(name)
        }

        "addemail" -> {
            println("Для добавления имейла выберите персону из списка: ")
            println(personList)
            println("Введите имя: ")
            val name = readln()
            Email(name)

        }

        "addphone" -> {
            println("Для добавления телефона выберите персону из списка: ")
            println(personList)
            println("Введите имя: ")
            val name = readln()
            Phone(name)
        }

        "show" -> {
            println("Введите имя: ")
            val name = readln()
            Show(name)
        }

        "find" -> {
            println("Введите телефон: ")
            val phone = readln().toInt()
            Find(phone)
        }

        else -> Help()
    }
}

open class Persons(private var name: String) : Command {

    override fun command() {
//        println("${isValid()}")
        if (!isValid()) {
            println("Поле не может быть пустым")
            Help().command()
            return
        } else  {
            personList.add(Person(name))
            println("Добавлено")
        }
        main()
    }

    private fun isValid(): Boolean {
        return name.isNotBlank() ?: false
    }
}

open class Email(private val name: String) : Command {

    override fun command() {
//        println("${isValid()}")
        if (!isValid()) {
            println("Поле не может быть пустым")
            Help().command()
            return
        } else {
            val index = personList.indexOf(Person(name))
            if (index in personList.indices) {
                println("Введите имейл: ")
            } else {
                println("Индекс вне диапазона")
            }
            val email = readlnOrNull()
            if (email != null) {
                personList[index].email.add(name)
                println("Успешно добавлено")
            } else Help().command()
        }
        main()
        return

    }

    private fun isValid(): Boolean {
        return name.isNotBlank() ?: false
    }
}

open class Phone(private val name: String) : Command {
    override fun command() {
//        println("${isValid()}")
        if (!isValid()) {
            println("Неккоректный ввод")
            Help().command()
            return
        } else {
            val index = personList.indexOf(Person(name))
            if (index in personList.indices) {
                println("Введите номер телефона: ")
            } else {
                println("Индекс вне диапазона")
                main()
            }
            val phone = readlnOrNull()?.toIntOrNull()
            if (phone != null) {
                personList[index].phone.add(phone)
                println("Успешно добавлено")
            } else Help().command()

        }
        main()
        return
    }

    private fun isValid(): Boolean {
        return name.isNotBlank()
    }
}

open class Help() : Command {
    override fun command() {
        println(
            "Записная книжка.Для того чтобы добавить данные вводите следующие команды: " +
                    "<addperson>, <addemail>, <addphone>, <addpersons>/ Работа с программой, выберите <help>/" +
                    " Чтобы отобразить добавленные данные выберите <show>/ <find> " +
                    "Чтобы выйти из программы выберите <exit>"
        )
        main()
        return
    }
}

open class Show(private val name: String) : Command {

    override fun command() {
        val persons = personList.indexOf(Person(name))
        if (persons in personList.indices) {
            val element = personList[persons]
//            println(personList)
            println(element)
        } else {
            println("Индекс вне диапазона!")
        }
        main()
        return

    }
}

open class Find(private val phone: Int) : Command {
    override fun command() {
        val persons = personList.filter { it.phone = mutableListOf(phone) }
        println(persons)
        main()
    }

    private fun <E> List<E>.filter(predicate: (E) -> Unit): List<Person> {

        return listOf()
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
    var phone: MutableList<Int> = mutableListOf(),
    var email: MutableList<String> = mutableListOf()
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Person

        if (phone != other.phone) return false
        if (name != other.name) return false
        if (email != other.email) return false

        return true
    }

    override fun toString(): String {
        return "name = $name, phone = $phone, email = $email "
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}





