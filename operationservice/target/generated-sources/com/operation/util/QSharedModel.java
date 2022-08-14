package com.operation.util;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSharedModel is a Querydsl query type for SharedModel
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class QSharedModel extends EntityPathBase<SharedModel> {

    private static final long serialVersionUID = -489098260L;

    public static final QSharedModel sharedModel = new QSharedModel("sharedModel");

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final StringPath lastModifiedBy = createString("lastModifiedBy");

    public final DateTimePath<java.util.Date> lastModifiedDate = createDateTime("lastModifiedDate", java.util.Date.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public QSharedModel(String variable) {
        super(SharedModel.class, forVariable(variable));
    }

    public QSharedModel(Path<? extends SharedModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSharedModel(PathMetadata metadata) {
        super(SharedModel.class, metadata);
    }

}

