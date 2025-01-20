fun main() {
    println("Выберите команду (addperson/ addemail/ addphone/ help/ show/ exit):")

    val choice = readlnOrNull() ?: ""

    val commands = readCommand(choice)

    // Выполняем команду, если она не null или не exit
    if (choice != "exit") {
        commands.command()
    } else Exit().exit()
}









