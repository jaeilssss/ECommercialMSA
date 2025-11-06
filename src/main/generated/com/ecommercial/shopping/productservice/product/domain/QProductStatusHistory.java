package com.ecommercial.shopping.productservice.product.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductStatusHistory is a Querydsl query type for ProductStatusHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductStatusHistory extends EntityPathBase<ProductStatusHistory> {

    private static final long serialVersionUID = -1121781264L;

    public static final QProductStatusHistory productStatusHistory = new QProductStatusHistory("productStatusHistory");

    public final BooleanPath afterStatus = createBoolean("afterStatus");

    public final BooleanPath beforeStatus = createBoolean("beforeStatus");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public QProductStatusHistory(String variable) {
        super(ProductStatusHistory.class, forVariable(variable));
    }

    public QProductStatusHistory(Path<? extends ProductStatusHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductStatusHistory(PathMetadata metadata) {
        super(ProductStatusHistory.class, metadata);
    }

}

