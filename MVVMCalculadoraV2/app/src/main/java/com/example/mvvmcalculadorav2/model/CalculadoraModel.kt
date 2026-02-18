package com.example.mvvmcalculadorav2.model

import com.example.mvvmcalculadorav2.model.Dato

class CalculadoraModel {

    var dato = Dato("", "", "", "", false)

    fun inputNumber(number : String) {
        if (dato.res) clearAll()
        dato.num += number
    }

    fun setOperation(op : String) : String? {
        if (dato.num.isEmpty()) return "Debes ingresar un número primero"

        dato.acumulado = dato.num
        dato.operador = op
        dato.estado = "${dato.acumulado} $op"
        dato.num = ""
        return null
    }

    fun calculate() : Dato {
        if (dato.num.isEmpty() || dato.operador.isEmpty() || dato.acumulado.isEmpty()) {
            dato.estado = "error: incompleto"
            return dato
        }

        val num1 = dato.acumulado.toDouble()
        val num2 = dato.num.toDouble()
        val operador = dato.operador

        val resultado = when (operador) {
                "+" -> (num1 + num2).toString()
                "-" -> (num1 -num2).toString()
                "*" -> (num1 * num2).toString()
                "/" -> {
                    if (num2 == 0.0) {
                        dato.estado = "error: división por cero"
                        return dato
                    }
                    (num1 / num2).toString()
                }
            else -> {
                dato.estado = "error: operador desconocido"
                return dato
            }
        }

        dato.estado = "$num1 $operador $num2 ="
        dato.num = resultado
        dato.acumulado = ""
        dato.operador = ""
        dato.res = true

        return dato
    }

    fun clearAll() {
        dato = Dato("", "", "", "", false)
    }
}
