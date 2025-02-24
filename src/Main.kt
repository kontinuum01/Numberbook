fun main() {
    println("Выберите команду (addperson/ addemail/ addphone/ addpersons/ help/ show/ find/ exit):")

    val choice = readln()

    val commands = readCommand(choice)

    // Выполняем команду, если она не null или не exit
    if (choice != "exit") {
        commands.command()
    } else Exit().exit()
}









