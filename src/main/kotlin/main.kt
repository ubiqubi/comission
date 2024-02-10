package ru.netology

fun main() {
    val cardType1 = "visa"
    val cardType2 = "mastercard"
    val cardType3 = "mir"
    val amount = 300
    val totalPreviousTransfer = 74000


    // Проверяем по всем картам
    calculateCommission(cardType1, totalPreviousTransfer, amount)

    calculateCommission(cardType2, totalPreviousTransfer, amount)

    calculateCommission(cardType3, totalPreviousTransfer, amount)
}

// Функция расчета комиссии
fun calculateCommission(cardType: String, totalPreviousTransfer: Int, transferAmount: Int) {
    val dailyLimit = 150000
    val monthlyLimit = 600000
    var commission = 0

    // Проверяем лимит по карте
    if (transferAmount > dailyLimit || (transferAmount + totalPreviousTransfer) > monthlyLimit) {
        println("Операция не выполнена, превышен лимит.")
        return
    }

    // Расчет комиссии для mastercard
    when (cardType) {
        "mastercard" -> {
            val noLimit = 75000
            val changeCommission = 0.006
            val minCommission = 20

            commission = if (totalPreviousTransfer > noLimit) {
                (transferAmount.toDouble() * changeCommission + minCommission).toInt()
            } else if (transferAmount > noLimit - totalPreviousTransfer){
                ((transferAmount.toDouble() - (noLimit - totalPreviousTransfer)) * changeCommission + minCommission).toInt()
            }
            else {
                0
            }
        }

        // Расчет комиссии для visa
        "visa" -> {
            val changeCommission = 0.0075
            val minCommission = 35
            commission = (transferAmount.toDouble() * changeCommission).toInt()
            if (commission < minCommission) {
                commission = minCommission
            }
        }

        // Расчет комиссии для mir
        "mir" -> {
            // Bсё для народа
        }

        else -> {
            println("Карта не обслуживается")
            return
        }
    }

// Расчет общей суммы и вывод соообщения
    var resultAmount = transferAmount + commission
    println("Сумма перевода: $transferAmount")
    println("Комиссия: $commission")
    println("Общая сумма: $resultAmount \n")
}
