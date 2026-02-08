package net.naoponju.stockr.common

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

object LoggerDelegate: ReadOnlyProperty<Any, Logger> {
    override fun getValue(thisRef: Any, property: KProperty<*>): Logger {
        return LoggerFactory.getLogger(thisRef.javaClass)
    }
}
