package com.operation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPetAppointmentMapping is a Querydsl query type for PetAppointmentMapping
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPetAppointmentMapping extends EntityPathBase<PetAppointmentMapping> {

    private static final long serialVersionUID = -1880634377L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPetAppointmentMapping petAppointmentMapping = new QPetAppointmentMapping("petAppointmentMapping");

    public final com.operation.util.QSharedModel _super = new com.operation.util.QSharedModel(this);

    public final NumberPath<Integer> appointmentId = createNumber("appointmentId", Integer.class);

    public final NumberPath<Integer> appointmentTypeId = createNumber("appointmentTypeId", Integer.class);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final QPet pet;

    public final NumberPath<Integer> petAppointmentMappingId = createNumber("petAppointmentMappingId", Integer.class);

    public final NumberPath<Integer> petId = createNumber("petId", Integer.class);

    //inherited
    public final NumberPath<Integer> status = _super.status;

    public QPetAppointmentMapping(String variable) {
        this(PetAppointmentMapping.class, forVariable(variable), INITS);
    }

    public QPetAppointmentMapping(Path<? extends PetAppointmentMapping> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPetAppointmentMapping(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPetAppointmentMapping(PathMetadata metadata, PathInits inits) {
        this(PetAppointmentMapping.class, metadata, inits);
    }

    public QPetAppointmentMapping(Class<? extends PetAppointmentMapping> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pet = inits.isInitialized("pet") ? new QPet(forProperty("pet"), inits.get("pet")) : null;
    }

}

