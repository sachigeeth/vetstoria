package com.operation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAppointment is a Querydsl query type for Appointment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAppointment extends EntityPathBase<Appointment> {

    private static final long serialVersionUID = -1951373528L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAppointment appointment = new QAppointment("appointment");

    public final com.operation.util.QSharedModel _super = new com.operation.util.QSharedModel(this);

    public final DateTimePath<java.util.Date> appointmentDate = createDateTime("appointmentDate", java.util.Date.class);

    public final NumberPath<Integer> appointmentId = createNumber("appointmentId", Integer.class);

    public final NumberPath<Integer> appointmentStatusId = createNumber("appointmentStatusId", Integer.class);

    public final NumberPath<Integer> companyProfileId = createNumber("companyProfileId", Integer.class);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final QCustomer customer;

    public final NumberPath<Integer> customerId = createNumber("customerId", Integer.class);

    public final QDoctor doctor;

    public final NumberPath<Integer> doctorId = createNumber("doctorId", Integer.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.util.Date> lastModifiedDate = _super.lastModifiedDate;

    public final ListPath<PetAppointmentMapping, QPetAppointmentMapping> petAppointmentMappings = this.<PetAppointmentMapping, QPetAppointmentMapping>createList("petAppointmentMappings", PetAppointmentMapping.class, QPetAppointmentMapping.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Integer> status = _super.status;

    public QAppointment(String variable) {
        this(Appointment.class, forVariable(variable), INITS);
    }

    public QAppointment(Path<? extends Appointment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAppointment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAppointment(PathMetadata metadata, PathInits inits) {
        this(Appointment.class, metadata, inits);
    }

    public QAppointment(Class<? extends Appointment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new QCustomer(forProperty("customer"), inits.get("customer")) : null;
        this.doctor = inits.isInitialized("doctor") ? new QDoctor(forProperty("doctor"), inits.get("doctor")) : null;
    }

}

