package com.ecommercial.shopping.adminservice.global.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import java.util.List;
import java.util.Map;

public class RoutingDataSource extends AbstractRoutingDataSource {

    private static final String READ = "read";
    private static final String WRITE = "write";
    private final ReadOnlyDataSourceCycle<String> readOnlyDataSourceCycle = new ReadOnlyDataSourceCycle<>();


    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        List<String> readOnlyDataSourceLookupKeys = targetDataSources.keySet()
                .stream()
                .map(String::valueOf)
                .filter(lookupKey -> lookupKey.contains(READ)).toList();
        readOnlyDataSourceCycle.setReadOnlyDataSourceLookupKeys(readOnlyDataSourceLookupKeys);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        System.out.println(">>> [Routing] Transaction readOnly = " + isReadOnly);
        String lookupKey = isReadOnly
                ? readOnlyDataSourceCycle.getReadOnlyDataSourceLookupKey()
                : WRITE;
        System.out.println(">>> [Routing] Using DataSource = " + lookupKey);
        return lookupKey;
    }
}
