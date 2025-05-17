package com.ecommercial.shopping.adminservice.admin.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAdmin is a Querydsl query type for Admin
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAdmin extends EntityPathBase<Admin> {

    private static final long serialVersionUID = -97591391L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAdmin admin = new QAdmin("admin");

    public final com.ecommercial.shopping.adminservice.admin.domain.vo.QAddress address;

    public final StringPath birthday = createString("birthday");

    public final com.ecommercial.shopping.adminservice.company.domain.entity.QCompany company;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final EnumPath<com.ecommercial.shopping.adminservice.global.enums.AdminRoleEnum> role = createEnum("role", com.ecommercial.shopping.adminservice.global.enums.AdminRoleEnum.class);

    public QAdmin(String variable) {
        this(Admin.class, forVariable(variable), INITS);
    }

    public QAdmin(Path<? extends Admin> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAdmin(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAdmin(PathMetadata metadata, PathInits inits) {
        this(Admin.class, metadata, inits);
    }

    public QAdmin(Class<? extends Admin> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new com.ecommercial.shopping.adminservice.admin.domain.vo.QAddress(forProperty("address")) : null;
        this.company = inits.isInitialized("company") ? new com.ecommercial.shopping.adminservice.company.domain.entity.QCompany(forProperty("company"), inits.get("company")) : null;
    }

}

