package com.ntconcepts.gcpdemo2.models

import com.google.api.services.bigquery.model.TableRow
import com.ntconcepts.gcpdemo2.utils.BigQueryField
import java.io.Serializable
import kotlin.reflect.full.createType
import kotlin.reflect.full.findAnnotation

abstract class OutputBase : OutputInterface, Serializable {

    abstract override var Encoded: MutableMap<String, Int>

    companion object : OutputCompanionInterface {
        override fun getValueAsString(name: String, obj: OutputInterface): String? {
            var value: String? = null
            obj::class.members.forEach {
                if (it.name == name) {
                    value = when (it.returnType) {

                        Int::class.createType(emptyList(), it.returnType.isMarkedNullable) -> {
                            val num = it.call(obj) as Int
                            if (num < 10) {
                                "%02d".format(it.call(obj))
                            } else {
                                num.toString()
                            }
                        }
                        Double::class.createType(emptyList(), it.returnType.isMarkedNullable) -> {
                            val num = it.call(obj) as Double
                            if (num < 10) {
                                "%02d".format(it.call(obj))
                            } else {
                                num.toString()
                            }
                        }
                        else -> it.call(obj).toString()
                    }
                }
            }
            return value
        }

        override fun toTableRow(obj: OutputInterface): TableRow {
            val tableRow = TableRow()

            obj::class.members.forEach {
                val bqa = it.findAnnotation<BigQueryField>()
                if (bqa != null) {
                    val fieldName = it.name
                    val pairVal = it.call(obj) to it.returnType
                    var fieldValue: Any? = null
                    if (pairVal != null) {
                        pairVal
                        fieldValue = pairVal.first
                    }

                    tableRow.set(fieldName, fieldValue)
                }

            }

            obj.Encoded.forEach {
                tableRow.set(it.key, it.value)
            }

            return tableRow
        }
    }
}