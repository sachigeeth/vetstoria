package com.operation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomer is a Querydsl query type for Customer
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCustomer extends EntityPathBase<Customer> {

    private static final long serialVersionUID = -651147019L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomer customer = new QCustomer("customer");

    public final com.operation.util.QSharedModel _super = new com.operation.util.QSharedModel(this);

    public final QAddressBook addressBook;

    public final NumberPath<Integer> companyProfileId = createNumber("companyProfileId", Integer.class);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final NumberPath<Integer> customerId = createNumber("customerId", Integer.class);

    public final StringPath firstName = createString("firstName");

    public final NumberPath<Integer> genderTypeId = createNumber("genderTypeId", Integer.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath lastName = createString("lastName");

    public final StringPath occupation = createString("occupation");

    public final ListPath<Pet, QPet> pets = this.<Pet, QPet>createList("pets", Pet.class, QPet.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Integer> status = _super.status;

    public QCustomer(String variable) {
        this(Customer.class, forVariable(variable), INITS);
    }

    public QCustomer(Path<? extends Customer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCustomer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCustomer(PathMetadata metadata, PathInits inits) {
        this(Customer.class, metadata, inits);
    }

    public QCustomer(Class<? extends Customer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.addressBook = inits.isInitialized("addressBook") ? new QAddressBook(forProperty("addressBook"), inits.get("addressBook")) : null;
    }

}

