package net.naoponju.stockr.common

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object LoggerDelegate : ReadOnlyProperty<Any, Logger> {
    override fun getValue(
        thisRef: Any,
        property: KProperty<*>,
    ): Logger {
        val klass = thisRef.javaClass
        val loggerClass = klass.enclosingClass?.takeIf { it.kotlin.isCompanion } ?: klass
        return LoggerFactory.getLogger(loggerClass)
    }
}
