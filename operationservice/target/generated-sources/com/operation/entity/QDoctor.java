package com.operation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDoctor is a Querydsl query type for Doctor
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDoctor extends EntityPathBase<Doctor> {

    private static final long serialVersionUID = 410760182L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDoctor doctor = new QDoctor("doctor");

    public final com.operation.util.QSharedModel _super = new com.operation.util.QSharedModel(this);

    public final QAddressBook addressBook;

    public final NumberPath<Integer> companyProfileId = createNumber("companyProfileId", Integer.class);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final NumberPath<Integer> doctorId = createNumber("doctorId", Integer.class);

    public final StringPath firstName = createString("firstName");

    public final StringPath genderDescription = createString("genderDescription");

    public final NumberPath<Integer> genderTypeId = createNumber("genderTypeId", Integer.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath lastName = createString("lastName");

    //inherited
    public final NumberPath<Integer> status = _super.status;

    public QDoctor(String variable) {
        this(Doctor.class, forVariable(variable), INITS);
    }

    public QDoctor(Path<? extends Doctor> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDoctor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDoctor(PathMetadata metadata, PathInits inits) {
        this(Doctor.class, metadata, inits);
    }

    public QDoctor(Class<? extends Doctor> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.addressBook = inits.isInitialized("addressBook") ? new QAddressBook(forProperty("addressBook"), inits.get("addressBook")) : null;
    }

}

