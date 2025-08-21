package com.ecommercial.shopping.userservice.global.config
import com.ecommercial.shopping.userservice.global.database.ReadOnlyDataSourceCycle
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.transaction.support.TransactionSynchronizationManager

class RoutingDataSource : AbstractRoutingDataSource() {

    companion object {
        private const val READ = "read"
        private const val WRITE = "write"
    }

    private val readOnlyDataSourceCycle = ReadOnlyDataSourceCycle<String>()

    override fun setTargetDataSources(targetDataSources: MutableMap<Any, Any>) {
        super.setTargetDataSources(targetDataSources)
        val readOnlyDataSourceLookupKeys = targetDataSources.keys
            .map { it.toString() }
            .filter { it.contains(READ) }
        readOnlyDataSourceCycle.readOnlyDataSourceLookupKeys = readOnlyDataSourceLookupKeys
    }

    override fun determineCurrentLookupKey(): Any {
        val isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly()
        println(">>> [Routing] Transaction readOnly = $isReadOnly")
        val lookupKey = if (isReadOnly) {
            readOnlyDataSourceCycle.getReadOnlyDataSourceLookupKey()
        } else {
            WRITE
        }
        println(">>> [Routing] Using DataSource = $lookupKey")
        return lookupKey
    }
}