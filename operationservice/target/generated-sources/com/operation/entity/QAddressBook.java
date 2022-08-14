package com.operation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAddressBook is a Querydsl query type for AddressBook
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAddressBook extends EntityPathBase<AddressBook> {

    private static final long serialVersionUID = -1854194554L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAddressBook addressBook = new QAddressBook("addressBook");

    public final com.operation.util.QSharedModel _super = new com.operation.util.QSharedModel(this);

    public final NumberPath<Integer> addressBookId = createNumber("addressBookId", Integer.class);

    public final StringPath addressOne = createString("addressOne");

    public final StringPath addressTwo = createString("addressTwo");

    public final QCountry country;

    public final NumberPath<Integer> countryId = createNumber("countryId", Integer.class);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final StringPath email = createString("email");

    public final StringPath fax = createString("fax");

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final QLocation location;

    public final NumberPath<Integer> locationId = createNumber("locationId", Integer.class);

    public final StringPath mobile = createString("mobile");

    public final StringPath postalCode = createString("postalCode");

    //inherited
    public final NumberPath<Integer> status = _super.status;

    public final StringPath telephone = createString("telephone");

    public final StringPath website = createString("website");

    public QAddressBook(String variable) {
        this(AddressBook.class, forVariable(variable), INITS);
    }

    public QAddressBook(Path<? extends AddressBook> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAddressBook(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAddressBook(PathMetadata metadata, PathInits inits) {
        this(AddressBook.class, metadata, inits);
    }

    public QAddressBook(Class<? extends AddressBook> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.country = inits.isInitialized("country") ? new QCountry(forProperty("country")) : null;
        this.location = inits.isInitialized("location") ? new QLocation(forProperty("location"), inits.get("location")) : null;
    }

}

