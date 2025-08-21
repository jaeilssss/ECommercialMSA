package com.ecommercial.shopping.inventoryservice.inventory.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReservedInventoryLog is a Querydsl query type for ReservedInventoryLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservedInventoryLog extends EntityPathBase<ReservedInventoryLog> {

    private static final long serialVersionUID = -1817590946L;

    public static final QReservedInventoryLog reservedInventoryLog = new QReservedInventoryLog("reservedInventoryLog");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public QReservedInventoryLog(String variable) {
        super(ReservedInventoryLog.class, forVariable(variable));
    }

    public QReservedInventoryLog(Path<? extends ReservedInventoryLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReservedInventoryLog(PathMetadata metadata) {
        super(ReservedInventoryLog.class, metadata);
    }

}

