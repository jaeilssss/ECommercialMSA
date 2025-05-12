package com.ecommercial.shopping.adminservice.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAdmin_Address is a Querydsl query type for Address
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QAdmin_Address extends BeanPath<Admin.Address> {

    private static final long serialVersionUID = 192632358L;

    public static final QAdmin_Address address = new QAdmin_Address("address");

    public final StringPath city = createString("city");

    public final StringPath firstAddress = createString("firstAddress");

    public final StringPath secondAddress = createString("secondAddress");

    public final StringPath zipCode = createString("zipCode");

    public QAdmin_Address(String variable) {
        super(Admin.Address.class, forVariable(variable));
    }

    public QAdmin_Address(Path<? extends Admin.Address> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdmin_Address(PathMetadata metadata) {
        super(Admin.Address.class, metadata);
    }

}

