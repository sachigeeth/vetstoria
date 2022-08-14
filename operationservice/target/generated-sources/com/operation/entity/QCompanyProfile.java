package com.operation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCompanyProfile is a Querydsl query type for CompanyProfile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCompanyProfile extends EntityPathBase<CompanyProfile> {

    private static final long serialVersionUID = -1787123133L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCompanyProfile companyProfile = new QCompanyProfile("companyProfile");

    public final com.operation.util.QSharedModel _super = new com.operation.util.QSharedModel(this);

    public final QAddressBook addressBook;

    public final StringPath companyName = createString("companyName");

    public final NumberPath<Integer> companyProfileId = createNumber("companyProfileId", Integer.class);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final StringPath description = createString("description");

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath shortCode = createString("shortCode");

    //inherited
    public final NumberPath<Integer> status = _super.status;

    public QCompanyProfile(String variable) {
        this(CompanyProfile.class, forVariable(variable), INITS);
    }

    public QCompanyProfile(Path<? extends CompanyProfile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCompanyProfile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCompanyProfile(PathMetadata metadata, PathInits inits) {
        this(CompanyProfile.class, metadata, inits);
    }

    public QCompanyProfile(Class<? extends CompanyProfile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.addressBook = inits.isInitialized("addressBook") ? new QAddressBook(forProperty("addressBook"), inits.get("addressBook")) : null;
    }

}

